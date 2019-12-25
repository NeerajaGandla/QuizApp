package com.neeraja.quizjava.util;

import android.util.Log;

import com.neeraja.quizjava.model.Category;
import com.neeraja.quizjava.model.Question;

import java.util.List;

public class Testing {
    public static void printCategories(List<Category> categories, String tag) {
        for (Category category : categories) {
            Log.d(tag, "onChanged: " + category.getName());
        }
    }

    public static void printQuestions(List<Question> questions, String tag) {
        for (Question question : questions) {
            Log.d(tag, "onChanged: " + question.getQuestion());
        }
    }

}
