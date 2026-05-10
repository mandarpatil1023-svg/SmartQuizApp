package com.smartquiz.quiz;

import android.app.Application;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.CountDownTimer;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.smartquiz.core.util.Event;
import com.smartquiz.database.QuestionEntity;
import com.smartquiz.database.QuizRepository;
import com.smartquiz.database.QuizResultEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizViewModel extends AndroidViewModel {
    private final QuizRepository repository;
    private final MutableLiveData<List<QuestionEntity>> questions = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Integer> currentIndex = new MutableLiveData<>(0);
    private final MutableLiveData<Long> secondsRemaining = new MutableLiveData<>(30L);
    private final MutableLiveData<Event<QuizResultBundle>> resultEvent = new MutableLiveData<>();
    private final Map<Integer, String> answers = new HashMap<>();
    private CountDownTimer countDownTimer;
    private long startedAt;
    private String category = "General Knowledge";

    public QuizViewModel(@NonNull Application application) {
        super(application);
        repository = new QuizRepository(application);
    }

    public LiveData<List<QuestionEntity>> getQuestions() {
        return questions;
    }

    public LiveData<Integer> getCurrentIndex() {
        return currentIndex;
    }

    public LiveData<Long> getSecondsRemaining() {
        return secondsRemaining;
    }

    public LiveData<Event<QuizResultBundle>> getResultEvent() {
        return resultEvent;
    }

    public void start(String selectedCategory) {
        if (startedAt != 0L && category.equals(selectedCategory) && questions.getValue() != null && !questions.getValue().isEmpty()) {
            return;
        }
        category = selectedCategory;
        repository.loadQuestions(selectedCategory, 50).observeForever(questionEntities -> {
            questions.postValue(questionEntities);
            startedAt = SystemClock.elapsedRealtime();
            currentIndex.postValue(0);
            restartTimer();
        });
    }

    public void selectAnswer(String answer) {
        Integer index = currentIndex.getValue();
        if (index != null) {
            answers.put(index, answer);
        }
    }

    public String getSelectedAnswer() {
        Integer index = currentIndex.getValue();
        return index == null ? null : answers.get(index);
    }

    public void next() {
        Integer index = currentIndex.getValue();
        List<QuestionEntity> list = questions.getValue();
        if (index == null || list == null || list.isEmpty()) {
            return;
        }
        if (index < list.size() - 1) {
            currentIndex.setValue(index + 1);
            restartTimer();
        } else {
            finishQuiz();
        }
    }

    public void previous() {
        Integer index = currentIndex.getValue();
        if (index != null && index > 0) {
            currentIndex.setValue(index - 1);
            restartTimer();
        }
    }

    public void finishQuiz() {
        List<QuestionEntity> list = questions.getValue();
        if (list == null || list.isEmpty()) {
            return;
        }
        int correct = 0;
        List<QuizAnswerReview> reviews = new ArrayList<>();
        ToneGenerator toneGenerator = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 80);
        for (int i = 0; i < list.size(); i++) {
            QuestionEntity question = list.get(i);
            String selected = answers.get(i);
            if (question.getCorrectAnswer().equals(selected)) {
                correct++;
                toneGenerator.startTone(ToneGenerator.TONE_PROP_BEEP, 100);
            }
            reviews.add(new QuizAnswerReview(question.getQuestion(), selected == null ? "Not answered" : selected,
                    question.getCorrectAnswer(), question.getExplanation()));
        }
        int wrong = list.size() - correct;
        int score = Math.round(correct * 100f / list.size());
        long timeTaken = SystemClock.elapsedRealtime() - startedAt;
        String uid = FirebaseAuth.getInstance().getCurrentUser() != null ? FirebaseAuth.getInstance().getCurrentUser().getUid() : "guest";
        repository.saveResult(new QuizResultEntity(uid, category, score, list.size(), correct, wrong, timeTaken, System.currentTimeMillis()));
        resultEvent.setValue(new Event<>(new QuizResultBundle(category, score, correct, wrong, timeTaken, reviews)));
    }

    private void restartTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(30_000, 1_000) {
            @Override
            public void onTick(long millisUntilFinished) {
                secondsRemaining.postValue(millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                next();
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
