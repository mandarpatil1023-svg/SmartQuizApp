package com.smartquiz.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.smartquiz.core.util.AppExecutors;

@Database(entities = {UserEntity.class, QuestionEntity.class, QuizResultEntity.class}, version = 3, exportSchema = false)
public abstract class SmartQuizDatabase extends RoomDatabase {

    public abstract QuizDao quizDao();

    private static volatile SmartQuizDatabase instance;

    public static SmartQuizDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (SmartQuizDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    SmartQuizDatabase.class, "smart_quiz.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    AppExecutors.database().execute(() ->
                                            getInstance(context).quizDao().insertQuestions(QuestionSeedData.createQuestions())
                                    );
                                }
                            })
                            .build();
                }
            }
        }
        return instance;
    }
}
