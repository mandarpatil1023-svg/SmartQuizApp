package com.smartquiz.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.smartquiz.R;
import com.smartquiz.databinding.FragmentUserDirectoryBinding;

public class UserDirectoryFragment extends Fragment {
    private FragmentUserDirectoryBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUserDirectoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!AdminAccessManager.isOwner(requireContext())) {
            Toast.makeText(requireContext(), R.string.owner_only_admin_message, Toast.LENGTH_SHORT).show();
            androidx.navigation.Navigation.findNavController(view).navigateUp();
            return;
        }

        UserDirectoryViewModel viewModel = new ViewModelProvider(this).get(UserDirectoryViewModel.class);
        UserDirectoryAdapter adapter = new UserDirectoryAdapter();
        binding.userRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.userRecycler.setAdapter(adapter);
        viewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            adapter.submitList(users);
            binding.countText.setText(users.size() + " users found");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
