package com.neeraja.quizjava.repository;

import android.content.Context;
import android.util.Log;

import com.neeraja.quizjava.AppExecutors;
import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.persistence.CategoryDao;
import com.neeraja.quizjava.persistence.QuizDatabase;
import com.neeraja.quizjava.requests.QuizFactory;
import com.neeraja.quizjava.requests.responses.ApiResponse;
import com.neeraja.quizjava.requests.responses.CategoriesResponse;
import com.neeraja.quizjava.util.Constants;
import com.neeraja.quizjava.util.LiveDataCallAdapter;
import com.neeraja.quizjava.util.NetworkBoundResource;
import com.neeraja.quizjava.util.Resource;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class CategoryRepository {
    private static final String TAG = "CategoryRepository";

    private static CategoryRepository instance;
    private CategoryDao categoryDao;

    public static CategoryRepository getInstance(Context context) {
        if (instance == null)
            instance = new CategoryRepository(context);
        return instance;
    }

    private CategoryRepository(Context context) {
        categoryDao = QuizDatabase.getInstance(context).categoryDao();
    }

    public LiveData<Resource<List<Category>>> getCategoiesApi() {
        return new NetworkBoundResource<List<Category>, CategoriesResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull CategoriesResponse item) {
                if (item.getCategories() != null && item.getCategories().size() != 0) {
                    Category[] categories = new Category[item.getCategories().size()];

                    for (Category category : item.getCategories()) {
                        category.setTimestamp((int) (System.currentTimeMillis() / 1000));
                    }

                    int index = 0;
                    for (long rowid : categoryDao.insertCategories((Category[]) (item.getCategories().toArray(categories)))) {
                        if (rowid == -1) {
                            Log.d(TAG, "saveCallResult: CONFLICT... This recipe is already in the cache");
                        } else {
                            Log.d(TAG, "saveCallResult: inserted");
                        }
                        index++;
                    }
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Category> data) {
                if (data != null && data.size() != 0) {
                    Category category = data.get(0);
                    Log.d(TAG, "shouldFetch: category: " + data.toString());
                    int currentTime = (int) (System.currentTimeMillis() / 1000);
                    Log.d(TAG, "shouldFetch: current time: " + currentTime);
                    int lastRefresh = category.getTimestamp();
                    Log.d(TAG, "shouldFetch: last refresh: " + lastRefresh);
                    Log.d(TAG, "shouldFetch: it's been " + ((currentTime - lastRefresh) / 60 / 60 / 24) +
                            " days since this recipe was refreshed. 30 days must elapse before refreshing. ");
                    if ((currentTime - category.getTimestamp()) >= Constants.CATEGORY_REFRESH_TIME) {
                        Log.d(TAG, "shouldFetch: SHOULD REFRESH RECIPE?! " + true);
                        return true;
                    }
                }
                Log.d(TAG, "shouldFetch: SHOULD REFRESH RECIPE?! " + false);
                return false;
            }

            @NonNull
            @Override
            protected LiveData<List<Category>> loadFromDb() {
                Log.d(TAG, "loadFromDb: ");
                return categoryDao.getCategories();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<CategoriesResponse>> createCall() {
                return QuizFactory.create().fetchCategories();
            }
        }.getAsLiveData();
    }
}
