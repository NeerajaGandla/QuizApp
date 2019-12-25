package com.neeraja.quizjava.requests;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.neeraja.quizjava.model.Category;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizFactory {
    private final static String BASE_URL = "https://opentdb.com/";
    private MutableLiveData<List<Category>> mCategories;

    public static QuizService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(QuizService.class);
    }
}
