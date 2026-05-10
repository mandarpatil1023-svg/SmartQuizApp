package com.smartquiz.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz_results")
public class QuizResultEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String userId;
    private String category;
    private int score;
    private int totalQuestions;
    private int correctCount;
    private int wrongCount;
    private long timeTakenMillis;
    private long createdAt;

    public QuizResultEntity(String userId, String category, int score, int totalQuestions,
                            int correctCount, int wrongCount, long timeTakenMillis, long createdAt) {
        this.userId = userId;
        this.category = category;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
        this.timeTakenMillis = timeTakenMillis;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCategory() {
        return category;
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public long getTimeTakenMillis() {
        return timeTakenMillis;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
