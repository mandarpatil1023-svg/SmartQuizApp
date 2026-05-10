package com.smartquiz.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.smartquiz.R;
import com.smartquiz.database.QuestionEntity;
import com.smartquiz.databinding.FragmentQuizBinding;

import java.util.ArrayList;
import java.util.List;

public class QuizFragment extends Fragment {
    private FragmentQuizBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        QuizViewModel viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        String category = getArguments() != null ? getArguments().getString("category", "General Knowledge") : "General Knowledge";
        viewModel.start(category);
        viewModel.getQuestions().observe(getViewLifecycleOwner(), questions -> renderQuestion(viewModel, questions));
        viewModel.getCurrentIndex().observe(getViewLifecycleOwner(), index -> renderQuestion(viewModel, viewModel.getQuestions().getValue()));
        viewModel.getSecondsRemaining().observe(getViewLifecycleOwner(), value -> binding.timerChip.setText(value + " sec"));
        viewModel.getResultEvent().observe(getViewLifecycleOwner(), event -> {
            QuizResultBundle bundle = event.getContentIfNotHandled();
            if (bundle == null) {
                return;
            }
            Bundle result = new Bundle();
            result.putString("category", bundle.getCategory());
            result.putInt("score", bundle.getScore());
            result.putInt("correct", bundle.getCorrect());
            result.putInt("wrong", bundle.getWrong());
            result.putLong("timeTaken", bundle.getTimeTakenMillis());
            result.putSerializable("reviews", new ArrayList<>(bundle.getReviews()));
            Navigation.findNavController(view).navigate(R.id.action_quiz_to_result, result);
        });
        binding.optionsGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == View.NO_ID) {
                return;
            }
            RadioButton selected = group.findViewById(checkedId);
            if (selected != null && selected.getTag() != null) {
                viewModel.selectAnswer(selected.getTag().toString());
            }
        });
        binding.nextButton.setOnClickListener(v -> viewModel.next());
        binding.previousButton.setOnClickListener(v -> viewModel.previous());
        binding.submitButton.setOnClickListener(v -> viewModel.finishQuiz());
    }

    private void renderQuestion(QuizViewModel viewModel, List<QuestionEntity> questions) {
        if (questions == null || questions.isEmpty()) {
            return;
        }
        Integer index = viewModel.getCurrentIndex().getValue();
        if (index == null) {
            index = 0;
        }
        QuestionEntity question = questions.get(index);
        binding.questionCounter.setText((index + 1) + "/" + questions.size());
        binding.quizProgress.setProgress(Math.round(((index + 1) * 100f) / questions.size()));
        binding.questionText.setText(question.getQuestion());
        binding.optionA.setText(question.getOptionA());
        binding.optionB.setText(question.getOptionB());
        binding.optionC.setText(question.getOptionC());
        binding.optionD.setText(question.getOptionD());
        binding.optionA.setTag("A");
        binding.optionB.setTag("B");
        binding.optionC.setTag("C");
        binding.optionD.setTag("D");
        binding.optionsGroup.clearCheck();
        String answer = viewModel.getSelectedAnswer();
        if ("A".equals(answer)) {
            binding.optionA.setChecked(true);
        } else if ("B".equals(answer)) {
            binding.optionB.setChecked(true);
        } else if ("C".equals(answer)) {
            binding.optionC.setChecked(true);
        } else if ("D".equals(answer)) {
            binding.optionD.setChecked(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
