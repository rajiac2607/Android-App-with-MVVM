package com.nyt.articles.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.nyt.articles.data.model.Article;

/**
 * Created by Raji on 26/09/2018.
 */
public class ArticlesDetailsViewModel extends AndroidViewModel {

    private Article article;


    public ArticlesDetailsViewModel(@NonNull Application application) {
        super(application);
    }


    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}

