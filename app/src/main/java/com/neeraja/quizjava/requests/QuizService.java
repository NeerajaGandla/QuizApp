package com.neeraja.quizjava.requests;

import com.neeraja.quizjava.requests.responses.CategoriesResponse;
import com.neeraja.quizjava.requests.responses.QuizResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuizService {
    @GET("api_category.php")
    Call<CategoriesResponse> fetchCategories();

    @GET("api.php")
    Call<QuizResponse> fetchQuestions(@Query("amount") int noOfQuestions, @Query("category")int categoryId
    , @Query("difficulty") String difficulty, @Query("type") String type);
}
