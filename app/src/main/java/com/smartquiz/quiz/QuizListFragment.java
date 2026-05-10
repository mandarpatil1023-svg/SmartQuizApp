package com.smartquiz.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.smartquiz.R;
import com.smartquiz.databinding.FragmentQuizListBinding;

public class QuizListFragment extends Fragment {
    private FragmentQuizListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String category = getArguments() != null ? getArguments().getString("category", "General Knowledge") : "General Knowledge";
        binding.quizCategory.setText(category);
        binding.quizDescription.setText("50 quick questions, 30 seconds each, instant analytics and review.");
        binding.startQuizButton.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", category);
            Navigation.findNavController(v).navigate(R.id.action_quizList_to_quiz, bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
