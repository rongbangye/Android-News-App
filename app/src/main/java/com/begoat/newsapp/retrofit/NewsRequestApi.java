package com.begoat.newsapp.retrofit;

import com.begoat.newsapp.retrofit.Response.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsRequestApi {
    @GET("top-headlines")
    Call<News> getNews(@Query("sources") String sources, @Query("apiKey") String apiKey);
}
