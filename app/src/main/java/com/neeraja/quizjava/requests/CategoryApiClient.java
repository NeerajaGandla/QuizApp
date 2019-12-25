package com.neeraja.quizjava.requests;

import android.util.Log;

import com.neeraja.quizjava.AppExecutors;
import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.repository.CategoryRepository;
import com.neeraja.quizjava.requests.responses.CategoriesResponse;
import com.neeraja.quizjava.util.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

public class CategoryApiClient {
    private static final String TAG = "CategoryApiClient";

    private static CategoryApiClient instance;
    private MutableLiveData<List<Category>> mCategories;
    private RetrieveCategoriesRunnable mRetrieveCategoriesRunnable;

    public static CategoryApiClient getInstance() {
        if (instance == null)
            instance = new CategoryApiClient();
        return instance;
    }

    private CategoryApiClient() {
        mCategories = new MutableLiveData<>();
    }

    public LiveData<List<Category>> getCategories() {
        return mCategories;
    }

    public void categoriesApi() {
        Log.d(TAG, "categoriesApi: ");
        if (mRetrieveCategoriesRunnable != null) {
            mRetrieveCategoriesRunnable = null;
        }
        mRetrieveCategoriesRunnable = new RetrieveCategoriesRunnable();
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveCategoriesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //let the user know it's timed out
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveCategoriesRunnable implements Runnable {
        boolean cancelRequest;

        public RetrieveCategoriesRunnable() {
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getCategories().execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Category> list = new ArrayList<>(((CategoriesResponse)response.body()).getCategories());
                    mCategories.postValue(list);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mCategories.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mCategories.postValue(null);
            }
        }

        private Call<CategoriesResponse> getCategories() {
            return QuizFactory.create().fetchCategories();
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: canceling the fetch request" );
            cancelRequest = true;
        }
    }
}
