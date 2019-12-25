package com.neeraja.quizjava.requests.responses;

import com.google.gson.annotations.SerializedName;
import com.neeraja.quizjava.model.Question;

import java.util.List;

public class QuizResponse {
    @SerializedName("results")
    private List<Question> questionsList;

    public List<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList = questionsList;
    }
}
