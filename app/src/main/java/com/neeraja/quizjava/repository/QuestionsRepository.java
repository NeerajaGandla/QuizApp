package com.neeraja.quizjava.repository;

import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.requests.QuestionsApiClient;

import java.util.List;

import androidx.lifecycle.LiveData;

public class QuestionsRepository {
    private static QuestionsRepository instance;
    private QuestionsApiClient mQuestionsApiClient;

    public static QuestionsRepository getInstance() {
        if (instance == null)
            instance = new QuestionsRepository();
        return instance;
    }

    private QuestionsRepository() {
        mQuestionsApiClient = QuestionsApiClient.getInstance();
    }

    public LiveData<List<Question>> getQuestions() {
        return mQuestionsApiClient.getQuestions();
    }

    public void questionsApi(String noOfQuestions, int categoryId, String difficulty) {
        mQuestionsApiClient.questionsApi(noOfQuestions, categoryId, difficulty);
    }
}
