package com.smartquiz.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.smartquiz.databinding.FragmentForgotPasswordBinding;

public class ForgotPasswordFragment extends Fragment {
    private FragmentForgotPasswordBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AuthViewModel viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        binding.resetButton.setOnClickListener(v -> viewModel.resetPassword(
                binding.emailInput.getText() != null ? binding.emailInput.getText().toString().trim() : ""
        ));
        viewModel.getAuthEvents().observe(getViewLifecycleOwner(), event -> {
            String message = event.getContentIfNotHandled();
            if (message != null) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
