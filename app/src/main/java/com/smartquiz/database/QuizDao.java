package com.smartquiz.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuizDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity userEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuestions(List<QuestionEntity> questions);

    @Insert
    void insertResult(QuizResultEntity result);

    @Query("SELECT * FROM questions WHERE category = :category LIMIT :limit")
    List<QuestionEntity> getQuestionsForCategory(String category, int limit);

    @Query("SELECT COUNT(*) FROM questions")
    int questionCount();

    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    int getUserCountByEmail(String email);

    @Query("SELECT * FROM users WHERE uid = :uid LIMIT 1")
    LiveData<UserEntity> getUserById(String uid);

    @Query("SELECT * FROM quiz_results WHERE userId = :userId ORDER BY createdAt DESC")
    LiveData<List<QuizResultEntity>> getResults(String userId);

    @Query("SELECT COUNT(*) as totalQuizzes, AVG(score) as averageScore FROM quiz_results WHERE userId = :userId")
    LiveData<ProfileStats> getProfileStats(String userId);

    @Query("SELECT category, SUM(totalQuestions) as attemptedQuestions, AVG(score) as averageScore FROM quiz_results WHERE userId = :userId GROUP BY category")
    LiveData<List<CategoryProgress>> getCategoryProgress(String userId);
}
