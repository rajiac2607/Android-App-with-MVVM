package com.nyt.articles.data.repository;

import com.nyt.articles.data.network.APIConfig;
import com.nyt.articles.data.network.RequestInterceptor;
import com.nyt.articles.data.network.WebService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by Raji on 27/09/2018.
 */
@RunWith(JUnit4.class)
public class ArticleRepositoryTest {

    private WebService webService;

    @Before
    public void setUp() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(APIConfig.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(APIConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(APIConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        webService = new Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(WebService.class);
    }


    @Test
    public void loadArticles() {
        try {
            Response response = webService.loadArticles("all-sections",7).execute();
            assertEquals(response.code(), 200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}