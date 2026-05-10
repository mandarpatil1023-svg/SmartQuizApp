package com.smartquiz.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.smartquiz.R;
import com.smartquiz.databinding.FragmentAdminAccessBinding;

public class AdminAccessFragment extends Fragment {
    private FragmentAdminAccessBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminAccessBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!AdminAccessManager.isOwner(requireContext())) {
            Toast.makeText(requireContext(), R.string.owner_only_admin_message, Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).navigateUp();
            return;
        }

        binding.enterAdminButton.setOnClickListener(v -> {
            String entered = binding.adminPasswordInput.getText() != null
                    ? binding.adminPasswordInput.getText().toString().trim()
                    : "";
            if (entered.equals(getString(R.string.admin_access_password))) {
                Navigation.findNavController(v).navigate(R.id.action_adminAccess_to_adminDashboard);
            } else {
                Toast.makeText(requireContext(), R.string.invalid_admin_password, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
