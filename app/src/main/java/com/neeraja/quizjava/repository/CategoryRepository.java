package com.neeraja.quizjava.repository;

import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.requests.CategoryApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CategoryRepository {
    private static CategoryRepository instance;
    private CategoryApiClient mCategoryApiClient;

    public static CategoryRepository getInstance() {
        if (instance == null)
            instance = new CategoryRepository();
        return instance;
    }

    private CategoryRepository() {
        mCategoryApiClient = CategoryApiClient.getInstance();
    }

    public LiveData<List<Category>> getCategories() {
        return mCategoryApiClient.getCategories();
    }

    public void categoriesApi() {
        mCategoryApiClient.categoriesApi();
    }
}
