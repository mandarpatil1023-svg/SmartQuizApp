package com.smartquiz.auth;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.smartquiz.core.util.Event;
import com.smartquiz.database.QuizRepository;
import com.smartquiz.database.UserEntity;

public class AuthViewModel extends AndroidViewModel {
    private final FirebaseAuth firebaseAuth;
    private final QuizRepository repository;
    private final MutableLiveData<Event<String>> authEvents = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);

    public AuthViewModel(@NonNull Application application) {
        super(application);
        firebaseAuth = FirebaseAuth.getInstance();
        repository = new QuizRepository(application);
    }

    public LiveData<Event<String>> getAuthEvents() {
        return authEvents;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public boolean isLoggedIn() {
        return firebaseAuth.getCurrentUser() != null;
    }

    public void login(String email, String password) {
        if (!isValidEmailPassword(email, password)) {
            authEvents.setValue(new Event<>("Enter a valid email and password."));
            return;
        }
        loading.setValue(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            loading.setValue(false);
            if (task.isSuccessful()) {
                cacheUser(firebaseAuth.getCurrentUser());
                authEvents.setValue(new Event<>("LOGIN_SUCCESS"));
            } else {
                authEvents.setValue(new Event<>(task.getException() != null ? task.getException().getMessage() : "Login failed."));
            }
        });
    }

    public void signup(String name, String email, String password, String confirmPassword) {
        if (TextUtils.isEmpty(name)) {
            authEvents.setValue(new Event<>("Name is required."));
            return;
        }
        if (!password.equals(confirmPassword)) {
            authEvents.setValue(new Event<>("Passwords do not match."));
            return;
        }
        if (!isValidEmailPassword(email, password)) {
            authEvents.setValue(new Event<>("Enter a valid email and password."));
            return;
        }
        loading.setValue(true);
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            loading.setValue(false);
            if (task.isSuccessful()) {
                cacheUser(firebaseAuth.getCurrentUser(), name);
                authEvents.setValue(new Event<>("SIGNUP_SUCCESS"));
            } else {
                authEvents.setValue(new Event<>(task.getException() != null ? task.getException().getMessage() : "Signup failed."));
            }
        });
    }

    public void resetPassword(String email) {
        if (TextUtils.isEmpty(email)) {
            authEvents.setValue(new Event<>("Enter your email first."));
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            authEvents.setValue(new Event<>("Enter a valid email address."));
            return;
        }
        loading.setValue(true);
        repository.isRegisteredEmail(email, exists -> {
            if (!exists) {
                loading.postValue(false);
                authEvents.postValue(new Event<>("This email is not registered. Please sign up first."));
                return;
            }
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnSuccessListener(unused -> {
                        loading.setValue(false);
                        authEvents.setValue(new Event<>("Reset email sent."));
                    })
                    .addOnFailureListener(e -> {
                        loading.setValue(false);
                        authEvents.setValue(new Event<>(e.getMessage()));
                    });
        });
    }

    public void logout() {
        firebaseAuth.signOut();
        authEvents.setValue(new Event<>("LOGOUT_SUCCESS"));
    }

    public void loginWithGoogle(String idToken) {
        if (TextUtils.isEmpty(idToken)) {
            authEvents.setValue(new Event<>("Google sign-in token was empty."));
            return;
        }
        loading.setValue(true);
        firebaseAuth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                .addOnCompleteListener(task -> {
                    loading.setValue(false);
                    if (task.isSuccessful()) {
                        cacheUser(firebaseAuth.getCurrentUser());
                        authEvents.setValue(new Event<>("LOGIN_SUCCESS"));
                    } else {
                        authEvents.setValue(new Event<>(task.getException() != null
                                ? task.getException().getMessage()
                                : "Google sign-in failed."));
                    }
                });
    }

    private boolean isValidEmailPassword(String email, String password) {
        return !TextUtils.isEmpty(email)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                && !TextUtils.isEmpty(password)
                && password.length() >= 6;
    }

    private void cacheUser(FirebaseUser user) {
        cacheUser(user, user != null && user.getDisplayName() != null ? user.getDisplayName() : "SmartQuiz Learner");
    }

    private void cacheUser(FirebaseUser user, String fallbackName) {
        if (user != null) {
            repository.saveUser(new UserEntity(
                    user.getUid(),
                    fallbackName,
                    user.getEmail(),
                    "",
                    "",
                    "CET",
                    ""
            ));
        }
    }
}
