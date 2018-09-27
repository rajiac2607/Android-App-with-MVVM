package com.nyt.articles.data.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.nyt.articles.data.model.Article;
import com.nyt.articles.data.model.ArticleResponse;
import com.nyt.articles.data.network.NTError;
import com.nyt.articles.data.network.ResponseListener;
import com.nyt.articles.data.network.WebService;
import com.nyt.articles.di.DaggerAppComponent;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Raji on 26/09/2018.
 */

public class ArticleRepository {
    @Inject
    Retrofit retrofit;

    /**
     * @param application
     * @param section          :section to be retrieved from API,for now hardcoded in APIConfig
     * @param index            : index to be retrieved from API,for now hardcoded in APIConfig
     * @param responseListener : where network response will be sent
     */

    public void loadArticles(Application application, String section, int index, ResponseListener responseListener) {

        initializeDagger(application);
        WebService webService = retrofit.create(WebService.class);

        MutableLiveData<List<Article>> listLiveData = new MutableLiveData<>();

        Call<ArticleResponse> call = webService.loadArticles(section, index);
        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {

                if (response.isSuccessful()) {
                    listLiveData.setValue(response.body().getPopularArticles());
                    responseListener.onResponse(null, listLiveData);
                } else {

                    NTError error;
                    switch (response.code()) {
                        case 404:
                            error = new NTError("resource_not_found");
                            break;
                        case 500:
                            error = new NTError("server_error");
                            break;
                        default:
                            error = new NTError("unknownError");
                            break;
                    }
                    responseListener.onResponse(error, listLiveData);

                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                call.cancel();

                if (t instanceof IOException) {  //  IOExceptions are network errors
                    responseListener.onResponse(new NTError("networkError"), listLiveData);

                } else
                    responseListener.onResponse(new NTError("unknownError"), listLiveData);

            }
        });
    }

    private void initializeDagger(Application application) {
        DaggerAppComponent.builder().application(application).build().inject(this);
    }

}
