package com.nyt.articles.data.model;

import com.nyt.articles.data.model.Article;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

/**
 * Created by Raji on 26/09/2018.
 */
@RunWith(JUnit4.class)
public class ArticleTest {
    @Test
    public void testId(){
        Article article = new Article();
        article.setId(1000);
        assertEquals(article.getId(), 1000);
    }

    @Test
    public void testTitle(){
        Article article = new Article();
        article.setTitle("test");
        assertEquals(article.getTitle(), "test");
    }

    @Test
    public void testByline(){
        Article article = new Article();
        article.setByline("test");
        assertEquals(article.getByline(), "test");
    }

    @Test
    public void testPublishDate(){
        Article article = new Article();
        article.setPublished_date("test");
        assertEquals(article.getPublished_date(), "test");
    }

    @Test
    public void testUrl(){
        Article article = new Article();
        article.setUrl("test");
        assertEquals(article.getUrl(), "test");
    }
    @Test
    public void testArticleAbstract(){
        Article article=new Article();
        article.setArticleAbstract("test");
        assertEquals(article.getArticleAbstract(),"test");
    }
}
