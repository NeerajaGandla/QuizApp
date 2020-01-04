package com.neeraja.quizjava.repository;

import android.content.Context;
import android.util.Log;

import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.model.Quiz;
import com.neeraja.quizjava.persistence.QuizDao;
import com.neeraja.quizjava.persistence.QuizDatabase;
import com.neeraja.quizjava.requests.QuizFactory;
import com.neeraja.quizjava.requests.responses.ApiResponse;
import com.neeraja.quizjava.requests.responses.QuizResponse;
import com.neeraja.quizjava.util.Resource;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsRepository {
    private static final String TAG = "QuestionsRepository";
    private static QuestionsRepository instance;
    private QuizDao quizDao;

    public static QuestionsRepository getInstance(Context context) {
        if (instance == null)
            instance = new QuestionsRepository(context);
        return instance;
    }

    private QuestionsRepository(Context context) {
        quizDao = QuizDatabase.getInstance(context).quizDao();
    }

    public MutableLiveData<List<Question>> getQuestionsApi(int noOfQuestions, int categoryId, String difficulty) {
        MutableLiveData<List<Question>> newsData = new MutableLiveData<>();
        Call<QuizResponse> responseCall = QuizFactory.create().fetchQuestions(noOfQuestions, categoryId, difficulty, "multiple");
        responseCall.enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call,
                                   Response<QuizResponse> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + response.raw().request().url());
                    Log.d(TAG, "onResponse: " + response.body().getQuestionsList().toString());
                    newsData.setValue(response.body().getQuestionsList());
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
