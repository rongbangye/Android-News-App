package com.begoat.newsapp.Home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.begoat.newsapp.R;
import com.begoat.newsapp.retrofit.NewsRequestApi;
import com.begoat.newsapp.retrofit.Response.Article;
import com.begoat.newsapp.retrofit.Response.News;
import com.begoat.newsapp.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private static final String API_KEY = "d212f5e485a7415d81c5a9526878d183";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NewsAdapter newsAdapter;
    private List<Article> articles = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        loadJson();

        return view;
    }

    private void loadJson() {
        final NewsRequestApi newsRequestApi = RetrofitClient.getRetrofit().create(NewsRequestApi.class);
        Call<News> call = newsRequestApi.getNews("TechCrunch", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful()) {
                    articles = response.body().getArticles();
                    newsAdapter = new NewsAdapter(articles);
                    recyclerView.setAdapter(newsAdapter);
                    newsAdapter.notifyDataSetChanged();
                } else {
                    Log.d("FAIL", "TEST");
                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
            }
        });
    }
}
