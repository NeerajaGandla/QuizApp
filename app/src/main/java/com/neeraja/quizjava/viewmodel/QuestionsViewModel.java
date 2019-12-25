package com.neeraja.quizjava.viewmodel;

import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.repository.QuestionsRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class QuestionsViewModel extends ViewModel {
    private QuestionsRepository mRepository;

    public QuestionsViewModel() {
        mRepository = QuestionsRepository.getInstance();
    }

    public LiveData<List<Question>> getQuestions() {
        return mRepository.getQuestions();
    }

    public void questionsApi(String noOfQuestions, int categoryId, String difficulty) {
        mRepository.questionsApi(noOfQuestions, categoryId, difficulty);
    }
}
