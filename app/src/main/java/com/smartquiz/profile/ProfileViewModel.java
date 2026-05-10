package com.smartquiz.profile;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smartquiz.core.util.Event;
import com.smartquiz.database.ProfileStats;
import com.smartquiz.database.QuizRepository;
import com.smartquiz.database.UserEntity;

import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private final QuizRepository repository;
    private final MutableLiveData<String> email = new MutableLiveData<>("guest@smartquiz.app");
    private final MutableLiveData<String> name = new MutableLiveData<>("SmartQuiz Learner");
    private final MutableLiveData<String> phone = new MutableLiveData<>("");
    private final MutableLiveData<String> location = new MutableLiveData<>("");
    private final MutableLiveData<String> targetExam = new MutableLiveData<>("CET");
    private final MutableLiveData<String> profileImageBase64 = new MutableLiveData<>("");
    private final MutableLiveData<Boolean> saving = new MutableLiveData<>(false);
    private final MutableLiveData<Event<String>> profileEvents = new MutableLiveData<>();
    private final FirebaseUser currentUser;
    private LiveData<ProfileStats> stats;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new QuizRepository(application);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            email.setValue(currentUser.getEmail());
            name.setValue(currentUser.getDisplayName() != null ? currentUser.getDisplayName() : "SmartQuiz Learner");
            stats = repository.getProfileStats(currentUser.getUid());
            repository.getUserProfile(currentUser.getUid()).observeForever(user -> {
                if (user == null) {
                    return;
                }
                name.postValue(user.getName());
                email.postValue(user.getEmail());
                phone.postValue(user.getPhone());
                location.postValue(user.getLocation());
                targetExam.postValue(TextUtils.isEmpty(user.getTargetExam()) ? "CET" : user.getTargetExam());
                profileImageBase64.postValue(user.getProfileImageBase64());
            });
        }
    }

    public LiveData<String> getEmail() {
        return email;
    }

    public LiveData<String> getName() {
        return name;
    }

    public LiveData<String> getPhone() {
        return phone;
    }

    public LiveData<String> getLocation() {
        return location;
    }

    public LiveData<String> getTargetExam() {
        return targetExam;
    }

    public LiveData<String> getProfileImageBase64() {
        return profileImageBase64;
    }

    public LiveData<Boolean> getSaving() {
        return saving;
    }

    public LiveData<Event<String>> getProfileEvents() {
        return profileEvents;
    }

    public LiveData<ProfileStats> getStats() {
        return stats;
    }

    public void saveProfile(String nameValue, String phoneValue, String locationValue,
                            String targetExamValue, String imageBase64) {
        if (currentUser == null) {
            profileEvents.setValue(new Event<>("No signed-in user found."));
            return;
        }
        if (TextUtils.isEmpty(nameValue)) {
            profileEvents.setValue(new Event<>("Name cannot be empty."));
            return;
        }
        saving.setValue(true);
        UserEntity userEntity = new UserEntity(
                currentUser.getUid(),
                nameValue.trim(),
                currentUser.getEmail() != null ? currentUser.getEmail() : "",
                phoneValue != null ? phoneValue.trim() : "",
                locationValue != null ? locationValue.trim() : "",
                TextUtils.isEmpty(targetExamValue) ? "CET" : targetExamValue.trim(),
                imageBase64 != null ? imageBase64 : currentImage()
        );
        repository.updateUserProfile(userEntity, (success, message) -> {
            saving.postValue(false);
            if (success) {
                syncProfileLiveData(userEntity);
            }
            profileEvents.postValue(new Event<>(message));
        });
    }

    public void updateAccountSecurity(String newEmailValue, String currentPassword,
                                      String newPassword, String confirmPassword) {
        if (currentUser == null) {
            profileEvents.setValue(new Event<>("No signed-in user found."));
            return;
        }

        String currentEmail = currentUser.getEmail() != null ? currentUser.getEmail() : "";
        String requestedEmail = newEmailValue != null ? newEmailValue.trim() : currentEmail;
        boolean emailChanged = !TextUtils.isEmpty(requestedEmail) && !requestedEmail.equals(currentEmail);
        boolean passwordChanged = !TextUtils.isEmpty(newPassword);

        if (!emailChanged && !passwordChanged) {
            profileEvents.setValue(new Event<>("Nothing to update in account security."));
            return;
        }
        if (!currentUserHasPasswordProvider(currentUser.getProviderData())) {
            profileEvents.setValue(new Event<>("For Google-linked accounts, change email or password from your Google account settings."));
            return;
        }
        if (TextUtils.isEmpty(currentPassword)) {
            profileEvents.setValue(new Event<>("Enter your current password to confirm account changes."));
            return;
        }
        if (passwordChanged) {
            if (newPassword.length() < 6) {
                profileEvents.setValue(new Event<>("New password must be at least 6 characters."));
                return;
            }
            if (!newPassword.equals(confirmPassword)) {
                profileEvents.setValue(new Event<>("New password and confirmation do not match."));
                return;
            }
        }

        saving.setValue(true);
        currentUser.reauthenticate(EmailAuthProvider.getCredential(currentEmail, currentPassword))
                .addOnSuccessListener(unused -> applySecurityChanges(requestedEmail, emailChanged, newPassword, passwordChanged))
                .addOnFailureListener(e -> {
                    saving.setValue(false);
                    profileEvents.setValue(new Event<>(e.getMessage() != null ? e.getMessage() : "Re-authentication failed."));
                });
    }

    private void applySecurityChanges(String requestedEmail, boolean emailChanged,
                                      String newPassword, boolean passwordChanged) {
        if (emailChanged) {
            currentUser.updateEmail(requestedEmail)
                    .addOnSuccessListener(unused -> updatePasswordIfNeeded(newPassword, passwordChanged, requestedEmail, false))
                    .addOnFailureListener(e -> {
                        saving.setValue(false);
                        profileEvents.setValue(new Event<>(e.getMessage() != null ? e.getMessage() : "Unable to update email."));
                    });
        } else {
            updatePasswordIfNeeded(newPassword, passwordChanged, requestedEmail, false);
        }
    }

    private void updatePasswordIfNeeded(String newPassword, boolean passwordChanged,
                                        String requestedEmail, boolean emailVerificationSent) {
        if (!passwordChanged) {
            finishSecurityUpdate(requestedEmail, emailVerificationSent);
            return;
        }
        currentUser.updatePassword(newPassword)
                .addOnSuccessListener(unused -> finishSecurityUpdate(requestedEmail, emailVerificationSent))
                .addOnFailureListener(e -> {
                    saving.setValue(false);
                    profileEvents.setValue(new Event<>(e.getMessage() != null ? e.getMessage() : "Unable to update password."));
                });
    }

    private void finishSecurityUpdate(String requestedEmail, boolean emailVerificationSent) {
        UserEntity userEntity = new UserEntity(
                currentUser.getUid(),
                valueOrDefault(name.getValue(), "SmartQuiz Learner"),
                requestedEmail,
                valueOrDefault(phone.getValue(), ""),
                valueOrDefault(location.getValue(), ""),
                valueOrDefault(targetExam.getValue(), "CET"),
                currentImage()
        );
        repository.updateUserProfile(userEntity, (success, message) -> {
            saving.postValue(false);
            if (success) {
                syncProfileLiveData(userEntity);
                String finalMessage = emailVerificationSent
                        ? "Profile saved. Check your email to confirm the new address."
                        : "Account security updated successfully.";
                profileEvents.postValue(new Event<>(finalMessage));
            } else {
                profileEvents.postValue(new Event<>(message));
            }
        });
    }

    private void syncProfileLiveData(UserEntity userEntity) {
        name.postValue(userEntity.getName());
        email.postValue(userEntity.getEmail());
        phone.postValue(userEntity.getPhone());
        location.postValue(userEntity.getLocation());
        targetExam.postValue(userEntity.getTargetExam());
        profileImageBase64.postValue(userEntity.getProfileImageBase64());
    }

    private boolean currentUserHasPasswordProvider(List<? extends com.google.firebase.auth.UserInfo> providers) {
        for (com.google.firebase.auth.UserInfo provider : providers) {
            if ("password".equals(provider.getProviderId())) {
                return true;
            }
        }
        return false;
    }

    private String currentImage() {
        return profileImageBase64.getValue() != null ? profileImageBase64.getValue() : "";
    }

    private String valueOrDefault(String value, String fallback) {
        return value != null ? value : fallback;
    }
}
