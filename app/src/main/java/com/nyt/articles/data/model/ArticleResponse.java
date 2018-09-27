package com.nyt.articles.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raji on 26/09/2018.
 */
public class ArticleResponse {

    @SerializedName("results")
    private List<Article> popularArticles;

    /**
     * @return list of articles fetched from api
     */
    public List<Article> getPopularArticles() {
        return popularArticles;
    }

    public void setPopularArticles(List<Article> popularArticles) {
        this.popularArticles = popularArticles;

    }
}
