package com.smartquiz.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartquiz.databinding.ItemCategoryCardBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    public interface CategoryClickListener {
        void onCategoryClicked(CategoryCard categoryCard);
    }

    private final CategoryClickListener listener;
    private final List<CategoryCard> items = new ArrayList<>();

    public CategoryAdapter(CategoryClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<CategoryCard> categories) {
        items.clear();
        if (categories != null) {
            items.addAll(categories);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(ItemCategoryCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final ItemCategoryCardBinding binding;

        CategoryViewHolder(ItemCategoryCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CategoryCard item) {
            binding.titleText.setText(item.getTitle());
            binding.descriptionText.setText(item.getDescription());
            binding.progressText.setText(item.getProgressPercent() + "% ready");
            binding.attemptedText.setText(item.getAttemptedQuestions() + " attempted");
            binding.progressIndicator.setProgress(item.getProgressPercent());
            binding.getRoot().setOnClickListener(v -> listener.onCategoryClicked(item));
        }
    }
}
