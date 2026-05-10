package com.smartquiz.result;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.smartquiz.R;
import com.smartquiz.databinding.FragmentResultBinding;
import com.smartquiz.quiz.QuizAnswerReview;

import java.util.ArrayList;
import java.util.List;

public class ResultFragment extends Fragment {
    private FragmentResultBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        int score = args != null ? args.getInt("score", 0) : 0;
        int correct = args != null ? args.getInt("correct", 0) : 0;
        int wrong = args != null ? args.getInt("wrong", 0) : 0;
        long timeTaken = args != null ? args.getLong("timeTaken", 0L) : 0L;
        String category = args != null ? args.getString("category", "Quiz") : "Quiz";
        ArrayList<QuizAnswerReview> reviews = args != null
                ? (ArrayList<QuizAnswerReview>) args.getSerializable("reviews")
                : new ArrayList<>();

        binding.resultTitle.setText(category + " Complete");
        binding.scoreText.setText(score + "%");
        binding.timeText.setText((timeTaken / 1000) + " sec");
        binding.messageText.setText(score >= 80 ? "Amazing run. You're exam-ready."
                : score >= 50 ? "Solid work. A couple more drills and you'll level up."
                : "Keep going. Every quiz sharpens your pace.");

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(correct, "Correct"));
        entries.add(new PieEntry(wrong, "Wrong"));
        PieDataSet set = new PieDataSet(entries, "Accuracy");
        set.setColors(Color.parseColor("#6C63FF"), Color.parseColor("#FF6584"));
        set.setValueTextColor(Color.WHITE);
        binding.resultChart.setData(new PieData(set));
        binding.resultChart.invalidate();

        if (score >= 80) {
            binding.confettiBadge.setVisibility(View.VISIBLE);
        }

        binding.retryButton.setOnClickListener(v -> Navigation.findNavController(v).popBackStack(R.id.quizListFragment, false));
        binding.reviewButton.setOnClickListener(v -> {
            Bundle reviewArgs = new Bundle();
            reviewArgs.putString("category", category);
            reviewArgs.putSerializable("reviews", reviews);
            Navigation.findNavController(v).navigate(R.id.action_result_to_reviewAnswers, reviewArgs);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
