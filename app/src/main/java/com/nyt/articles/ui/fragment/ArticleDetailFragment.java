package com.nyt.articles.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.nyt.articles.R;
import com.nyt.articles.data.model.Article;
import com.nyt.articles.databinding.FragmentArticleDetailsBinding;
import com.nyt.articles.viewmodel.ArticlesDetailsViewModel;

/**
 * Created by Raji on 26/09/2018.
 */
public class ArticleDetailFragment extends BaseFragment<ArticlesDetailsViewModel, FragmentArticleDetailsBinding> {


    @Override
    protected Class<ArticlesDetailsViewModel> getViewModel() {
        return ArticlesDetailsViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_article_details;
    }


    public static ArticleDetailFragment newInstance(Bundle args) {
        ArticleDetailFragment articleDetailFragment = new ArticleDetailFragment();
        articleDetailFragment.setArguments(args);
        return articleDetailFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            Article article = getArguments().getParcelable("article");
            viewModel.setArticle(article);
            dataBinding.setArticleDetailViewModel(viewModel);
        }
    }

}