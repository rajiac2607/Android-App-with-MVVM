package com.nyt.articles.data.network;

import com.nyt.articles.data.model.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Raji on 26/09/2018.
 */
public interface WebService {

    @GET("svc/mostpopular/v2/mostviewed/{section}/{index}.json")
    Call<ArticleResponse> loadArticles(@Path("section") String section,@Path("index") int index);

}
