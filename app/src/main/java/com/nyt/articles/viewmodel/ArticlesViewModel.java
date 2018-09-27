package com.nyt.articles.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.nyt.articles.data.model.Article;
import com.nyt.articles.data.network.APIConfig;
import com.nyt.articles.data.repository.ArticleRepository;
import com.nyt.articles.di.DaggerAppComponent;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Raji on 26/09/2018.
 */
public class ArticlesViewModel extends AndroidViewModel {

    public ArticlesViewModel(@NonNull Application application) {
        super(application);
        initializeDagger();
        loadArticles();
    }

    @Inject
    ArticleRepository articleRepository;

    private MutableLiveData<List<Article>> articleList = new MutableLiveData<>();

    public MutableLiveData<List<Article>> getArticleList() {
        return articleList;
    }

    public void setArticleList(MutableLiveData<List<Article>> articleList) {
        this.articleList = articleList;

    }


    private void initializeDagger() {
        DaggerAppComponent.builder().application(getApplication()).build().inject(this);
    }


    /**
     * call repository method to fetch data from API
     */
    public void loadArticles() {

        articleRepository.loadArticles(getApplication(), APIConfig.NYT_API_SECTION, APIConfig.NYT_API_INDEX, (error, list) -> {
            articleList.setValue(list.getValue());

            if (error != null)
                Toast.makeText(getApplication(), getStringResourceByName(error.getErrorMessage()), Toast.LENGTH_SHORT).show();
        });

    }

    /**
     * @param aString name of the string resource
     * @return value of the desired string
     */
    private String getStringResourceByName(String aString) {
        String packageName = getApplication().getPackageName();
        int resId = getApplication().getResources().getIdentifier(aString, "string", packageName);
        return getApplication().getString(resId);
    }

}
