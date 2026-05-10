package com.smartquiz.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smartquiz.R;
import com.smartquiz.auth.AuthViewModel;
import com.smartquiz.databinding.FragmentProfileBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private String selectedProfileImageBase64 = "";
    private ActivityResultLauncher<String[]> photoPickerLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        AuthViewModel authViewModel = new ViewModelProvider(requireActivity()).get(AuthViewModel.class);
        boolean isOwner = AdminAccessManager.isOwner(requireContext());
        registerPhotoPicker();

        binding.userDirectoryButton.setVisibility(isOwner ? View.VISIBLE : View.GONE);
        binding.adminPanelButton.setVisibility(isOwner ? View.VISIBLE : View.GONE);

        profileViewModel.getName().observe(getViewLifecycleOwner(), value -> {
            binding.headerNameText.setText(value);
            if (binding.nameInput.getText() == null || binding.nameInput.getText().length() == 0) {
                binding.nameInput.setText(value);
            }
        });
        profileViewModel.getEmail().observe(getViewLifecycleOwner(), value -> {
            binding.emailText.setText(value);
            binding.emailInput.setText(value);
            if (binding.newEmailInput.getText() == null || binding.newEmailInput.getText().length() == 0) {
                binding.newEmailInput.setText(value);
            }
        });
        profileViewModel.getPhone().observe(getViewLifecycleOwner(), value -> {
            if (binding.phoneInput.getText() == null || binding.phoneInput.getText().length() == 0) {
                binding.phoneInput.setText(value);
            }
        });
        profileViewModel.getLocation().observe(getViewLifecycleOwner(), value -> {
            if (binding.locationInput.getText() == null || binding.locationInput.getText().length() == 0) {
                binding.locationInput.setText(value);
            }
        });
        profileViewModel.getTargetExam().observe(getViewLifecycleOwner(), value -> {
            if (binding.targetExamInput.getText() == null || binding.targetExamInput.getText().length() == 0) {
                binding.targetExamInput.setText(value);
            }
        });
        profileViewModel.getProfileImageBase64().observe(getViewLifecycleOwner(), value -> {
            selectedProfileImageBase64 = value != null ? value : "";
            renderProfileImage(selectedProfileImageBase64);
        });
        if (profileViewModel.getStats() != null) {
            profileViewModel.getStats().observe(getViewLifecycleOwner(), stats -> {
                if (stats == null) {
                    return;
                }
                binding.totalQuizzesText.setText(String.valueOf(stats.totalQuizzes));
                binding.averageScoreText.setText(Math.round(stats.averageScore) + "%");
            });
        }
        profileViewModel.getProfileEvents().observe(getViewLifecycleOwner(), event -> {
            String message = event.getContentIfNotHandled();
            if (message != null) {
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        binding.choosePhotoButton.setOnClickListener(v -> photoPickerLauncher.launch(new String[]{"image/*"}));
        binding.saveProfileButton.setOnClickListener(v -> profileViewModel.saveProfile(
                textOf(binding.nameInput),
                textOf(binding.phoneInput),
                textOf(binding.locationInput),
                textOf(binding.targetExamInput),
                selectedProfileImageBase64
        ));
        binding.userDirectoryButton.setOnClickListener(v -> {
            if (!AdminAccessManager.isOwner(requireContext())) {
                Toast.makeText(requireContext(), R.string.owner_only_admin_message, Toast.LENGTH_SHORT).show();
                return;
            }
            Navigation.findNavController(v).navigate(R.id.action_profile_to_userDirectory);
        });
        binding.adminPanelButton.setOnClickListener(v -> {
            if (!AdminAccessManager.isOwner(requireContext())) {
                Toast.makeText(requireContext(), R.string.owner_only_admin_message, Toast.LENGTH_SHORT).show();
                return;
            }
            Navigation.findNavController(v).navigate(R.id.action_profile_to_adminAccess);
        });
        binding.updateAccountButton.setOnClickListener(v -> profileViewModel.updateAccountSecurity(
                textOf(binding.newEmailInput),
                textOf(binding.currentPasswordInput),
                textOf(binding.newPasswordInput),
                textOf(binding.confirmNewPasswordInput)
        ));
        binding.logoutButton.setOnClickListener(v -> authViewModel.logout());
        authViewModel.getAuthEvents().observe(getViewLifecycleOwner(), event -> {
            String content = event.getContentIfNotHandled();
            if ("LOGOUT_SUCCESS".equals(content)) {
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void registerPhotoPicker() {
        photoPickerLauncher = registerForActivityResult(
                new ActivityResultContracts.OpenDocument(),
                this::handlePhotoSelected
        );
    }

    private void handlePhotoSelected(Uri uri) {
        if (uri == null) {
            return;
        }
        try {
            requireContext().getContentResolver().takePersistableUriPermission(
                    uri,
                    android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
            );
        } catch (SecurityException ignored) {
        }

        try (InputStream inputStream = requireContext().getContentResolver().openInputStream(uri)) {
            if (inputStream == null) {
                return;
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            if (bitmap == null) {
                return;
            }
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, 320, 320, true);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 82, outputStream);
            selectedProfileImageBase64 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
            renderProfileImage(selectedProfileImageBase64);
        } catch (IOException e) {
            Toast.makeText(requireContext(), "Unable to read selected photo.", Toast.LENGTH_SHORT).show();
        }
    }

    private void renderProfileImage(String base64Image) {
        if (binding == null) {
            return;
        }
        if (base64Image == null || base64Image.isEmpty()) {
            binding.profileImageView.setImageResource(R.drawable.ic_launcher_foreground);
            return;
        }
        try {
            byte[] imageBytes = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if (bitmap != null) {
                binding.profileImageView.setImageBitmap(bitmap);
            }
        } catch (IllegalArgumentException ignored) {
            binding.profileImageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    private String textOf(com.google.android.material.textfield.TextInputEditText input) {
        return input.getText() != null ? input.getText().toString().trim() : "";
    }
}
