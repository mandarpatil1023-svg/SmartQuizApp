package com.smartquiz.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smartquiz.database.CategoryProgress;
import com.smartquiz.database.QuizRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final QuizRepository repository;
    private final MediatorLiveData<List<CategoryCard>> categories = new MediatorLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new QuizRepository(application);
        categories.setValue(createDefaultCards());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            LiveData<List<CategoryProgress>> source = repository.getCategoryProgress(user.getUid());
            categories.addSource(source, progress -> {
                List<CategoryCard> cards = createDefaultCards();
                if (progress != null) {
                    for (CategoryProgress item : progress) {
                        for (CategoryCard card : cards) {
                            if (card.getCategoryKey().equals(item.category)) {
                                card.setAttemptedQuestions(item.attemptedQuestions);
                                card.setProgressPercent((int) Math.min(100, Math.round(item.averageScore)));
                            }
                        }
                    }
                }
                categories.setValue(cards);
            });
        }
    }

    public LiveData<List<CategoryCard>> getCategories() {
        return categories;
    }

    private List<CategoryCard> createDefaultCards() {
        List<CategoryCard> cards = new ArrayList<>();
        cards.add(new CategoryCard("General Knowledge", "Current affairs, polity and world facts", "General Knowledge"));
        cards.add(new CategoryCard("Science", "Physics, chemistry and biology boosters", "Science"));
        cards.add(new CategoryCard("CET Aptitude", "Fast reasoning and aptitude drills", "CET Aptitude"));
        cards.add(new CategoryCard("Other Exams", "Banking, SSC and mixed exam practice", "Other Exams"));
        return cards;
    }
}
