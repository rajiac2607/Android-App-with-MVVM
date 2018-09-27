package com.nyt.articles.di;

import android.app.Application;

import com.nyt.articles.data.repository.ArticleRepository;
import com.nyt.articles.viewmodel.ArticlesViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Raji on 26/09/2018.
 */
@Singleton
@Component(modules = {
        AppModule.class
})
public interface AppComponent {

    void inject(ArticlesViewModel articlesViewModel);
    void inject(ArticleRepository baseRepository);


    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }

}