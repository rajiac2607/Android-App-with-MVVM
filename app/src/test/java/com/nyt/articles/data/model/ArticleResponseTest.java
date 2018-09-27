package com.nyt.articles.data.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Raji on 27/09/2018.
 */
@RunWith(JUnit4.class)
public class ArticleResponseTest {

    @Mock
    ArrayList<Article> list;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testPopularArticles() {
        ArticleResponse article = new ArticleResponse();
        article.setPopularArticles(list);
        assertEquals(article.getPopularArticles(), list);

    }
}