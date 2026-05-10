package com.smartquiz.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.smartquiz.R;
import com.smartquiz.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        HomeViewModel viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        CategoryAdapter adapter = new CategoryAdapter(card -> {
            Bundle bundle = new Bundle();
            bundle.putString("category", card.getCategoryKey());
            Navigation.findNavController(view).navigate(R.id.action_home_to_quizList, bundle);
        });
        String email = FirebaseAuth.getInstance().getCurrentUser() != null
                ? FirebaseAuth.getInstance().getCurrentUser().getEmail()
                : "Learner";
        String displayName = email;
        if (email != null && email.contains("@")) {
            displayName = email.substring(0, email.indexOf("@"));
        }
        binding.welcomeText.setText("Ready to train, " + displayName + "?");
        binding.welcomeMetaText.setText("4 active modules • Firestore sync online");
        binding.categoryRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.categoryRecycler.setAdapter(adapter);
        viewModel.getCategories().observe(getViewLifecycleOwner(), adapter::submitList);
        binding.profileShortcut.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.profileFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
