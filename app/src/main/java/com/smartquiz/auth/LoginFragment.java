package com.smartquiz.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.smartquiz.R;
import com.smartquiz.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private GoogleSignInClient googleSignInClient;
    private ActivityResultLauncher<Intent> googleSignInLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AuthViewModel viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        configureGoogleSignIn(viewModel);
        binding.loginButton.setOnClickListener(v -> viewModel.login(
                binding.emailInput.getText() != null ? binding.emailInput.getText().toString().trim() : "",
                binding.passwordInput.getText() != null ? binding.passwordInput.getText().toString().trim() : ""
        ));
        binding.signupText.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_login_to_signup));
        binding.forgotText.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_login_to_forgotPassword));
        binding.googleButton.setOnClickListener(v -> {
            if (googleSignInClient == null) {
                Toast.makeText(requireContext(), "Google sign-in is not configured yet. Check Firebase app config.", Toast.LENGTH_SHORT).show();
                return;
            }
            googleSignInLauncher.launch(googleSignInClient.getSignInIntent());
        });
        viewModel.getAuthEvents().observe(getViewLifecycleOwner(), event -> {
            String message = event.getContentIfNotHandled();
            if (message == null) {
                return;
            }
            if ("LOGIN_SUCCESS".equals(message)) {
                Navigation.findNavController(view).navigate(R.id.action_login_to_home);
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

    private void configureGoogleSignIn(AuthViewModel viewModel) {
        int webClientIdRes = requireContext().getResources()
                .getIdentifier("default_web_client_id", "string", requireContext().getPackageName());
        if (webClientIdRes == 0) {
            googleSignInClient = null;
        } else {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(webClientIdRes))
                    .requestEmail()
                    .build();
            googleSignInClient = GoogleSignIn.getClient(requireContext(), gso);
        }

        googleSignInLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() != Activity.RESULT_OK || result.getData() == null) {
                        return;
                    }
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        if (account != null && account.getIdToken() != null) {
                            viewModel.loginWithGoogle(account.getIdToken());
                        } else {
                            Toast.makeText(requireContext(), "Unable to get Google account token.", Toast.LENGTH_SHORT).show();
                        }
                    } catch (ApiException e) {
                        Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
