package com.neeraja.quizjava.requests;

import android.util.Log;

import com.neeraja.quizjava.AppExecutors;
import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.repository.QuestionsRepository;
import com.neeraja.quizjava.requests.responses.QuizResponse;
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

public class QuestionsApiClient {
    private static final String TAG = "QuestionsApiClient";
    private static QuestionsApiClient instance;
    private MutableLiveData<List<Question>> mQuestions;
    private RetrieveQuestionsRunnable mRetrieveQuestionsRunnable;

    public static QuestionsApiClient getInstance() {
        if (instance == null)
            instance = new QuestionsApiClient();
        return instance;
    }

    private QuestionsApiClient() {
        mQuestions = new MutableLiveData<>();
    }

    public LiveData<List<Question>> getQuestions() {
        return mQuestions;
    }

    public void questionsApi(String noOfQuestions, int categoryId, String difficulty) {
        Log.d(TAG, "questionsApi: ");
        if (mRetrieveQuestionsRunnable != null) {
            mRetrieveQuestionsRunnable = null;
        }
        mRetrieveQuestionsRunnable = new RetrieveQuestionsRunnable(noOfQuestions, categoryId, difficulty);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveQuestionsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveQuestionsRunnable implements Runnable {
        boolean cancelRequest;
        String noOfQuestions, difficulty;
        int categoryId;

        RetrieveQuestionsRunnable(String noOfQuestions, int categoryId, String difficulty) {
            cancelRequest = false;
            this.noOfQuestions = noOfQuestions;
            this.difficulty = difficulty;
            this.categoryId = categoryId;
        }

        @Override
        public void run() {
            try {
                Response response = getQuestions().execute();
                Log.d(TAG, "run: " + response.raw().request().url());
                if (cancelRequest)
                    return;
                if (response.code() == 200) {
                    List<Question> questions = new ArrayList<>(((QuizResponse) response.body()).getQuestionsList());
                    mQuestions.postValue(questions);
                } else {
                    String error = response.errorBody().toString();
                    Log.e(TAG, "run: " + error);
                    mQuestions.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private Call<QuizResponse> getQuestions() {
            Call<QuizResponse> responseCall = QuizFactory.create().fetchQuestions(Integer.parseInt(noOfQuestions), categoryId, difficulty, "multiple");
            Log.d(TAG, "getQuestions: " + responseCall.toString());
            return responseCall;
        }

        private void cancelRequest() {
            Log.e(TAG, "cancelRequest: Canceling the fetch Request");
            cancelRequest = true;
        }
    }
}
