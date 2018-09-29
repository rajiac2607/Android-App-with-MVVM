package com.nyt.articles.ui.fragment;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nyt.articles.R;
import com.nyt.articles.data.model.Article;
import com.nyt.articles.databinding.FragmentArticleListBinding;
import com.nyt.articles.ui.MainActivity;
import com.nyt.articles.ui.adapter.ArticleListAdapter;
import com.nyt.articles.ui.fragment.util.FragmentUtil;
import com.nyt.articles.util.Logs;
import com.nyt.articles.viewmodel.ArticlesViewModel;

/**
 * Created by Raji on 26/09/2018.
 */
public class ArticleListFragment extends BaseFragment<ArticlesViewModel,
        FragmentArticleListBinding> implements ArticleListAdapter.OnItemSelected {

    @Override
    protected Class getViewModel() {
        return ArticlesViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_article_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new ArticleListAdapter(ArticleListFragment.this));

        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArticlesViewModel articleListViewModel = ViewModelProviders.of(getActivity()).get(ArticlesViewModel.class);

        articleListViewModel.getArticleList().observe(getActivity(), article -> {

                    if (null != article) {
                        Logs.v("result", "request successful with" + article.size());
                        if(getActivity()!=null)
                        ((MainActivity)getActivity()).hideProgressBar();
                        dataBinding.recyclerView.setVisibility(View.VISIBLE);
                        ((ArticleListAdapter) dataBinding.recyclerView.getAdapter()).setArticles(article);

                    }

                }
        );

    }

    /**
     * show details of selected article.
     * ArticleDetailFragment can be inflated to either a new screen or a
     * second pane in the same activity to depict master-detail flow
      * @param article
     */
    @Override
    public void onItemSelected(Article article) {
        Bundle args = new Bundle();
        args.putParcelable("article", article);
        FragmentUtil.replace(getActivity(), ArticleDetailFragment.newInstance(args), R.id.fragment_container, true, ArticleDetailFragment.class.getSimpleName());

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (null == getActivity())
            return;

        getActivity().getMenuInflater().inflate(R.menu.menu, menu);
        initiateSearchView(menu);

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return id == R.id.action_search || super.onOptionsItemSelected(item);
    }

    private void initiateSearchView(Menu menu) {

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();

        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                if (null != dataBinding.recyclerView.getAdapter())
                    ((ArticleListAdapter) dataBinding.recyclerView.getAdapter()).getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                if (null != dataBinding.recyclerView.getAdapter())
                    ((ArticleListAdapter) dataBinding.recyclerView.getAdapter()).getFilter().filter(query);
                return false;
            }
        });
    }

}
