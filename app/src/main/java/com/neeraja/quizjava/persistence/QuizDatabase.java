package com.neeraja.quizjava.persistence;

import android.content.Context;

import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.model.Quiz;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Category.class, Quiz.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class QuizDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "quiz_db";

    private static QuizDatabase instance;

    public static QuizDatabase getInstance(Context context) {
        if (instance == null) {
        instance = Room.databaseBuilder(
                context.getApplicationContext(),
                QuizDatabase.class,
                DATABASE_NAME
        ).build();
        }
        return instance;
    }

    public abstract CategoryDao categoryDao();

    public abstract QuizDao quizDao();
}
