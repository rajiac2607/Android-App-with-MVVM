package com.nyt.articles.di;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nyt.articles.data.network.APIConfig;
import com.nyt.articles.data.network.RequestInterceptor;
import com.nyt.articles.data.repository.ArticleRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raji on 26/09/2018.
 */
@Module
public class AppModule {
    @Singleton
    @NonNull
    @Provides
    public Gson providesGson() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }

    @Singleton
    @NonNull
    @Provides
    public GsonConverterFactory providesGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Singleton
    @NonNull
    @Provides
    public OkHttpClient providesOkHttpClient() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(interceptor);

        okHttpClientBuilder.addInterceptor(new RequestInterceptor());

        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        return okHttpClient;
    }


    @Singleton
    @NonNull
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    ArticleRepository providesArticleRepository() {
        return new ArticleRepository();
    }
}
