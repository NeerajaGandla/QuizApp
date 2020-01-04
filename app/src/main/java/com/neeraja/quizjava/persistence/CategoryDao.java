package com.neeraja.quizjava.persistence;

import com.neeraja.quizjava.model.Category;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface CategoryDao {
    @Insert(onConflict = IGNORE)
    long[] insertCategories(Category...categories);

    @Query("SELECT * FROM categories")
    LiveData<List<Category>> getCategories();
}
