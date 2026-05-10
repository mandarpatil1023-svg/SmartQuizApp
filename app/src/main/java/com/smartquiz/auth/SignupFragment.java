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
import androidx.navigation.Navigation;

import com.smartquiz.R;
import com.smartquiz.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {
    private FragmentSignupBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AuthViewModel viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        binding.signupButton.setOnClickListener(v -> viewModel.signup(
                binding.nameInput.getText() != null ? binding.nameInput.getText().toString().trim() : "",
                binding.emailInput.getText() != null ? binding.emailInput.getText().toString().trim() : "",
                binding.passwordInput.getText() != null ? binding.passwordInput.getText().toString().trim() : "",
                binding.confirmPasswordInput.getText() != null ? binding.confirmPasswordInput.getText().toString().trim() : ""
        ));
        viewModel.getAuthEvents().observe(getViewLifecycleOwner(), event -> {
            String message = event.getContentIfNotHandled();
            if (message == null) {
                return;
            }
            if ("SIGNUP_SUCCESS".equals(message)) {
                Navigation.findNavController(view).navigate(R.id.action_signup_to_home);
            } else {
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
