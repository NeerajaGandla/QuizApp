package com.neeraja.quizjava.persistence;

import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.model.Quiz;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface QuizDao {

    @Insert(onConflict = IGNORE)
    long[] insertQuiz(Quiz... questions);

    @Query("SELECT * FROM quiz WHERE quizId = :quizId")
    LiveData<List<Quiz>> getQuestions(int quizId);

    @Query("SELECT * FROM quiz GROUP BY quizId")
    LiveData<List<Quiz>> getQuizzes();
}
