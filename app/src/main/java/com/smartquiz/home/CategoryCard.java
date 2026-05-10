package com.smartquiz.home;

public class CategoryCard {
    private final String title;
    private final String description;
    private final String categoryKey;
    private int progressPercent;
    private int attemptedQuestions;

    public CategoryCard(String title, String description, String categoryKey) {
        this.title = title;
        this.description = description;
        this.categoryKey = categoryKey;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public int getProgressPercent() {
        return progressPercent;
    }

    public void setProgressPercent(int progressPercent) {
        this.progressPercent = progressPercent;
    }

    public int getAttemptedQuestions() {
        return attemptedQuestions;
    }

    public void setAttemptedQuestions(int attemptedQuestions) {
        this.attemptedQuestions = attemptedQuestions;
    }
}
