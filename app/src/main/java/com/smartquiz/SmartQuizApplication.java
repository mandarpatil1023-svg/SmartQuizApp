package com.smartquiz;

import android.app.Application;

import com.smartquiz.database.QuestionSeedData;
import com.smartquiz.database.QuizRepository;
import com.smartquiz.database.SmartQuizDatabase;

public class SmartQuizApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SmartQuizDatabase.getInstance(this);
        new QuizRepository(this).syncSeedQuestions(QuestionSeedData.createQuestions());
    }
}
