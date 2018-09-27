package com.nyt.articles.data.network;

import android.arch.lifecycle.MutableLiveData;

import com.nyt.articles.data.model.Article;

import java.util.List;

/**
 * Created by Raji on 26/09/2018.
 */
public interface ResponseListener {
    void onResponse(NTError error, MutableLiveData<List<Article>> mutableLiveData);
}
