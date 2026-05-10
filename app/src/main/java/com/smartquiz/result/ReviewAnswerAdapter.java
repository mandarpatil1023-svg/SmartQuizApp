package com.smartquiz.result;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartquiz.databinding.ItemReviewAnswerBinding;
import com.smartquiz.quiz.QuizAnswerReview;

import java.util.ArrayList;
import java.util.List;

public class ReviewAnswerAdapter extends RecyclerView.Adapter<ReviewAnswerAdapter.ReviewAnswerViewHolder> {
    private final List<QuizAnswerReview> items = new ArrayList<>();

    public void submitList(List<QuizAnswerReview> reviews) {
        items.clear();
        if (reviews != null) {
            items.addAll(reviews);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewAnswerViewHolder(ItemReviewAnswerBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAnswerViewHolder holder, int position) {
        holder.bind(items.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ReviewAnswerViewHolder extends RecyclerView.ViewHolder {
        private final ItemReviewAnswerBinding binding;

        ReviewAnswerViewHolder(ItemReviewAnswerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(QuizAnswerReview item, int number) {
            binding.questionNumber.setText("Question " + number);
            binding.questionText.setText(item.getQuestion());
            binding.selectedAnswerText.setText("Your answer: " + item.getSelectedAnswer());
            binding.correctAnswerText.setText("Correct answer: " + item.getCorrectAnswer());
            binding.explanationText.setText(item.getExplanation());

            boolean correct = item.getCorrectAnswer().equals(item.getSelectedAnswer());
            binding.answerStatus.setText(correct ? "Correct" : "Needs review");
            binding.answerStatus.setTextColor(Color.parseColor(correct ? "#15803D" : "#B91C1C"));
        }
    }
}
