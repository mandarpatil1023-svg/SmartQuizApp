package com.smartquiz.quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizResultBundle {
    private final String category;
    private final int score;
    private final int correct;
    private final int wrong;
    private final long timeTakenMillis;
    private final List<QuizAnswerReview> reviews;

    public QuizResultBundle(String category, int score, int correct, int wrong, long timeTakenMillis, List<QuizAnswerReview> reviews) {
        this.category = category;
        this.score = score;
        this.correct = correct;
        this.wrong = wrong;
        this.timeTakenMillis = timeTakenMillis;
        this.reviews = new ArrayList<>(reviews);
    }

    public String getCategory() {
        return category;
    }

    public int getScore() {
        return score;
    }

    public int getCorrect() {
        return correct;
    }

    public int getWrong() {
        return wrong;
    }

    public long getTimeTakenMillis() {
        return timeTakenMillis;
    }

    public List<QuizAnswerReview> getReviews() {
        return reviews;
    }
}
