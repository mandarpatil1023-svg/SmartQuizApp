package com.smartquiz.auth;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.smartquiz.R;
import com.smartquiz.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {
    private FragmentSplashBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        AuthViewModel viewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        binding.logoCard.animate().rotationY(360f).setDuration(1800).start();
        new Handler(Looper.getMainLooper()).postDelayed(() -> Navigation.findNavController(view).navigate(
                viewModel.isLoggedIn() ? R.id.action_splash_to_home : R.id.action_splash_to_login
        ), 3000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
