package com.smartquiz.profile;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smartquiz.database.QuizRepository;
import com.smartquiz.database.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserDirectoryViewModel extends AndroidViewModel {
    private final MutableLiveData<List<UserEntity>> users = new MutableLiveData<>(new ArrayList<>());

    public UserDirectoryViewModel(@NonNull Application application) {
        super(application);
        new QuizRepository(application).fetchAllUsers(users::postValue);
    }

    public LiveData<List<UserEntity>> getUsers() {
        return users;
    }
}
