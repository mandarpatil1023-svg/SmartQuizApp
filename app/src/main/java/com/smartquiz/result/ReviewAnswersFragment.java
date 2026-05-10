package com.smartquiz.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smartquiz.databinding.FragmentReviewAnswersBinding;
import com.smartquiz.quiz.QuizAnswerReview;

import java.util.ArrayList;

public class ReviewAnswersFragment extends Fragment {
    private FragmentReviewAnswersBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentReviewAnswersBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<QuizAnswerReview> reviews = getArguments() != null
                ? (ArrayList<QuizAnswerReview>) getArguments().getSerializable("reviews")
                : new ArrayList<>();
        String category = getArguments() != null ? getArguments().getString("category", "Quiz Review") : "Quiz Review";

        binding.reviewTitle.setText(category + " Review");
        binding.reviewSubtitle.setText(reviews.size() + " questions with answers and explanations");

        ReviewAnswerAdapter adapter = new ReviewAnswerAdapter();
        binding.reviewRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.reviewRecycler.setAdapter(adapter);
        adapter.submitList(reviews);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
