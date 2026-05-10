package com.smartquiz.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.smartquiz.core.util.AppExecutors;
import com.smartquiz.profile.AdminAccessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizRepository {

    public interface EmailCheckCallback {
        void onResult(boolean exists);
    }

    public interface ProfileUpdateCallback {
        void onComplete(boolean success, String message);
    }

    public interface UsersCallback {
        void onResult(List<UserEntity> users);
    }

    private final QuizDao quizDao;
    private final FirebaseFirestore firestore;
    private final Context appContext;

    public QuizRepository(Context context) {
        appContext = context.getApplicationContext();
        quizDao = SmartQuizDatabase.getInstance(context).quizDao();
        firestore = FirebaseFirestore.getInstance();
    }

    public void saveUser(UserEntity userEntity) {
        AppExecutors.database().execute(() -> quizDao.insertUser(userEntity));
        firestore.collection("users")
                .document(userEntity.getUid())
                .set(buildUserMap(userEntity), SetOptions.merge());
    }

    public LiveData<UserEntity> getUserProfile(String uid) {
        MutableLiveData<UserEntity> liveData = new MutableLiveData<>();
        LiveData<UserEntity> roomSource = quizDao.getUserById(uid);
        roomSource.observeForever(liveData::postValue);

        firestore.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(document -> {
                    if (!document.exists()) {
                        return;
                    }
                    UserEntity user = new UserEntity(
                            uid,
                            valueOrEmpty(document.getString("name")),
                            valueOrEmpty(document.getString("email")),
                            valueOrEmpty(document.getString("phone")),
                            valueOrEmpty(document.getString("location")),
                            valueOrEmpty(document.getString("targetExam")),
                            valueOrEmpty(document.getString("profileImageBase64"))
                    );
                    saveUser(user);
                    liveData.postValue(user);
                });

        return liveData;
    }

    public LiveData<List<QuizResultEntity>> getResults(String userId) {
        return quizDao.getResults(userId);
    }

    public LiveData<ProfileStats> getProfileStats(String userId) {
        return quizDao.getProfileStats(userId);
    }

    public LiveData<List<CategoryProgress>> getCategoryProgress(String userId) {
        return quizDao.getCategoryProgress(userId);
    }

    public LiveData<List<QuestionEntity>> loadQuestions(String category, int limit) {
        MutableLiveData<List<QuestionEntity>> liveData = new MutableLiveData<>();
        firestore.collection("questions")
                .whereEqualTo("category", category)
                .limit(limit)
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<QuestionEntity> firestoreQuestions = new ArrayList<>();
                    for (QueryDocumentSnapshot document : snapshot) {
                        firestoreQuestions.add(new QuestionEntity(
                                getString(document, "category"),
                                getString(document, "question"),
                                getString(document, "optionA"),
                                getString(document, "optionB"),
                                getString(document, "optionC"),
                                getString(document, "optionD"),
                                getString(document, "correctAnswer"),
                                getString(document, "explanation")
                        ));
                    }
                    if (!firestoreQuestions.isEmpty()) {
                        liveData.postValue(firestoreQuestions);
                    } else {
                        AppExecutors.database().execute(() ->
                                liveData.postValue(quizDao.getQuestionsForCategory(category, limit)));
                    }
                })
                .addOnFailureListener(error ->
                        AppExecutors.database().execute(() ->
                                liveData.postValue(quizDao.getQuestionsForCategory(category, limit))));
        return liveData;
    }

    public void saveResult(QuizResultEntity resultEntity) {
        AppExecutors.database().execute(() -> quizDao.insertResult(resultEntity));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userId", resultEntity.getUserId());
        resultMap.put("category", resultEntity.getCategory());
        resultMap.put("score", resultEntity.getScore());
        resultMap.put("totalQuestions", resultEntity.getTotalQuestions());
        resultMap.put("correctCount", resultEntity.getCorrectCount());
        resultMap.put("wrongCount", resultEntity.getWrongCount());
        resultMap.put("timeTakenMillis", resultEntity.getTimeTakenMillis());
        resultMap.put("createdAt", resultEntity.getCreatedAt());

        firestore.collection("results")
                .add(resultMap);
    }

    public void updateUserProfile(UserEntity userEntity, ProfileUpdateCallback callback) {
        AppExecutors.database().execute(() -> quizDao.insertUser(userEntity));

        firestore.collection("users")
                .document(userEntity.getUid())
                .set(buildUserMap(userEntity), SetOptions.merge())
                .addOnSuccessListener(unused -> {
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        FirebaseAuth.getInstance().getCurrentUser()
                                .updateProfile(new UserProfileChangeRequest.Builder()
                                        .setDisplayName(userEntity.getName())
                                        .build());
                    }
                    callback.onComplete(true, "Profile updated successfully.");
                })
                .addOnFailureListener(e -> callback.onComplete(false,
                        e.getMessage() != null ? e.getMessage() : "Profile update failed."));
    }

    public void isRegisteredEmail(String email, EmailCheckCallback callback) {
        firestore.collection("users")
                .whereEqualTo("email", email)
                .limit(1)
                .get()
                .addOnSuccessListener(snapshot -> {
                    if (!snapshot.isEmpty()) {
                        callback.onResult(true);
                    } else {
                        AppExecutors.database().execute(() ->
                                callback.onResult(quizDao.getUserCountByEmail(email) > 0));
                    }
                })
                .addOnFailureListener(error ->
                        AppExecutors.database().execute(() ->
                                callback.onResult(quizDao.getUserCountByEmail(email) > 0)));
    }

    public void syncSeedQuestions(List<QuestionEntity> questions) {
        AppExecutors.database().execute(() -> {
            WriteBatch batch = firestore.batch();
            for (QuestionEntity question : questions) {
                Map<String, Object> questionMap = new HashMap<>();
                questionMap.put("category", question.getCategory());
                questionMap.put("question", question.getQuestion());
                questionMap.put("optionA", question.getOptionA());
                questionMap.put("optionB", question.getOptionB());
                questionMap.put("optionC", question.getOptionC());
                questionMap.put("optionD", question.getOptionD());
                questionMap.put("correctAnswer", question.getCorrectAnswer());
                questionMap.put("explanation", question.getExplanation());
                String documentId = buildQuestionDocumentId(question);
                batch.set(
                        firestore.collection("questions").document(documentId),
                        questionMap,
                        SetOptions.merge()
                );
            }
            batch.commit();
        });
    }

    public void fetchAllUsers(UsersCallback callback) {
        if (!AdminAccessManager.isOwner(appContext)) {
            callback.onResult(new ArrayList<>());
            return;
        }

        firestore.collection("users")
                .get()
                .addOnSuccessListener(snapshot -> {
                    List<UserEntity> users = new ArrayList<>();
                    for (QueryDocumentSnapshot document : snapshot) {
                        users.add(new UserEntity(
                                valueOrEmpty(document.getString("uid")),
                                valueOrEmpty(document.getString("name")),
                                valueOrEmpty(document.getString("email")),
                                valueOrEmpty(document.getString("phone")),
                                valueOrEmpty(document.getString("location")),
                                valueOrEmpty(document.getString("targetExam")),
                                valueOrEmpty(document.getString("profileImageBase64"))
                        ));
                    }
                    callback.onResult(users);
                })
                .addOnFailureListener(error -> callback.onResult(new ArrayList<>()));
    }

    private String buildQuestionDocumentId(QuestionEntity question) {
        String raw = question.getCategory() + "_" + question.getQuestion();
        return raw.toLowerCase()
                .replaceAll("[^a-z0-9]+", "_")
                .replaceAll("^_+|_+$", "");
    }

    private String getString(QueryDocumentSnapshot document, String key) {
        String value = document.getString(key);
        return value != null ? value : "";
    }

    private Map<String, Object> buildUserMap(UserEntity userEntity) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("uid", userEntity.getUid());
        userMap.put("name", userEntity.getName());
        userMap.put("email", userEntity.getEmail());
        userMap.put("phone", userEntity.getPhone());
        userMap.put("location", userEntity.getLocation());
        userMap.put("targetExam", userEntity.getTargetExam());
        userMap.put("profileImageBase64", userEntity.getProfileImageBase64());
        return userMap;
    }

    private String valueOrEmpty(String value) {
        return value != null ? value : "";
    }
}
