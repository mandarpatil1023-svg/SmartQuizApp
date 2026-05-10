package com.smartquiz.quiz;

import java.io.Serializable;

public class QuizAnswerReview implements Serializable {
    private final String question;
    private final String selectedAnswer;
    private final String correctAnswer;
    private final String explanation;

    public QuizAnswerReview(String question, String selectedAnswer, String correctAnswer, String explanation) {
        this.question = question;
        this.selectedAnswer = selectedAnswer;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }
}
