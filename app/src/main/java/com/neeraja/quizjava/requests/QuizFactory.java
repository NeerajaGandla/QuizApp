package com.neeraja.quizjava.requests;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.neeraja.quizjava.util.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.neeraja.quizjava.util.Constants.CONNECTION_TIMEOUT;
import static com.neeraja.quizjava.util.Constants.READ_TIMEOUT;
import static com.neeraja.quizjava.util.Constants.WRITE_TIMEOUT;

public class QuizFactory {

    private final static String BASE_URL = "https://opentdb.com/";

    private static OkHttpClient client = new OkHttpClient.Builder()
            // establish connection to server
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            // time between each byte sent to the server
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            // time between each byte read from the server
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build();

    public static QuizService create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(QuizService.class);
    }
}
