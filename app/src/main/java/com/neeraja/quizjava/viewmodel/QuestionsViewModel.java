package com.neeraja.quizjava.viewmodel;

import android.app.Application;
import android.util.Log;

import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.model.Question;
import com.neeraja.quizjava.repository.QuestionsRepository;
import com.neeraja.quizjava.util.Constants;
import com.neeraja.quizjava.util.Resource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class QuestionsViewModel extends AndroidViewModel implements BaseViewModel {
    private static final String TAG = "QuestionsViewModel";
    public static final String QUERY_EXHAUSTED = "No more results.";

    private QuestionsRepository mRepository;
    private MutableLiveData<List<Question>> questions;
    private int categoryId;
    private SavedStateHandle savedStateHandle;

    public QuestionsViewModel(@NonNull Application application, SavedStateHandle savedStateHandle) {
        super(application);
        this.savedStateHandle = savedStateHandle;
        mRepository = QuestionsRepository.getInstance(application);
    }

    public LiveData<List<Question>> getQuestions(int noOfQuestions, int categoryId, String difficulty) {
        this.categoryId = categoryId;
        if (questions == null) {
            questions = mRepository.getQuestionsApi(noOfQuestions, categoryId, difficulty);
        }
        return questions;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }

    @NotNull
    @Override
    public LiveData<Integer> getPosition() {
        return savedStateHandle.getLiveData(Constants.POSITION);
    }

    @Override
    public void savePosition(int position) {
        savedStateHandle.set(Constants.POSITION, position);
    }

    @NotNull
    @Override
    public LiveData<Long> getTime() {
        return savedStateHandle.getLiveData(Constants.TIME_LEFT);
    }

    @Override
    public void saveTime(long time) {
        savedStateHandle.set(Constants.TIME_LEFT, time);
    }
}
