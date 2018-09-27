package com.nyt.articles.viewmodel;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.nyt.articles.data.model.Article;
import com.nyt.articles.data.network.ResponseListener;
import com.nyt.articles.data.repository.ArticleRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Raji on 27/09/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticlesViewModelTest {
    ArticlesViewModel viewModel;
    @Mock
    ArticleRepository repository;
    @Mock
    ResponseListener listener;
    @Mock
    Application context;
    @Mock
    private MutableLiveData<List<Article>> arrayList;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);// required for the "@Mock" annotations

        viewModel = Mockito.spy(new ArticlesViewModel(context));

    }

    @Test
    public void getArticleList() {
        viewModel.setArticleList(arrayList);
        assertEquals(viewModel.getArticleList(), arrayList);

    }

    @Test
    public void setArticleList() {
        viewModel.setArticleList(arrayList);
        assertEquals(viewModel.getArticleList().getValue(), arrayList.getValue());
    }

    @Test
    public void loadArticles() {
        repository.loadArticles(context,"all-sections",7,listener);
        Mockito.verify(repository, Mockito.times(1)).loadArticles(context, "all-sections", 7, listener);

    }
    @Test
    public void loadArticles_Never() {
        Mockito.verify(repository, Mockito.never()).loadArticles(context, "all-sections", 7, listener);

    }
}