package com.smartquiz.profile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartquiz.R;
import com.smartquiz.database.UserEntity;
import com.smartquiz.databinding.ItemUserDirectoryBinding;

import java.util.ArrayList;
import java.util.List;

public class UserDirectoryAdapter extends RecyclerView.Adapter<UserDirectoryAdapter.UserViewHolder> {
    private final List<UserEntity> items = new ArrayList<>();

    public void submitList(List<UserEntity> users) {
        items.clear();
        if (users != null) {
            items.addAll(users);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(ItemUserDirectoryBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        private final ItemUserDirectoryBinding binding;

        UserViewHolder(ItemUserDirectoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UserEntity user) {
            binding.nameText.setText(user.getName().isEmpty() ? "Unnamed User" : user.getName());
            binding.emailText.setText(user.getEmail());
            binding.phoneText.setText(user.getPhone().isEmpty() ? "Phone not added" : user.getPhone());
            binding.locationText.setText(user.getLocation().isEmpty() ? "Location not added" : user.getLocation());
            binding.examText.setText(user.getTargetExam().isEmpty() ? "Target exam not set" : user.getTargetExam());

            if (user.getProfileImageBase64() == null || user.getProfileImageBase64().isEmpty()) {
                binding.avatarView.setImageResource(R.drawable.ic_launcher_foreground);
                return;
            }
            try {
                byte[] imageBytes = Base64.decode(user.getProfileImageBase64(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                if (bitmap != null) {
                    binding.avatarView.setImageBitmap(bitmap);
                } else {
                    binding.avatarView.setImageResource(R.drawable.ic_launcher_foreground);
                }
            } catch (IllegalArgumentException ignored) {
                binding.avatarView.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }
    }
}
