package com.begoat.newsapp.Home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.begoat.newsapp.R;
import com.begoat.newsapp.retrofit.Response.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<Article> articleList;

     NewsAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View layoutView = inflater.inflate(R.layout.news_card, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holders, int position) {
        final ViewHolder holder = holders;
        Article article = articleList.get(position);

        TextView titleTextView = holder.titleTextView;
        titleTextView.setText(article.getTitle());
        TextView description = holder.descriptionTextView;
        description.setText(article.getDescription());
        Picasso.get().load(article.getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, descriptionTextView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.news_title);
            descriptionTextView = (TextView) itemView.findViewById(R.id.news_description);
            imageView = (ImageView) itemView.findViewById(R.id.news_image);
        }
    }

}
