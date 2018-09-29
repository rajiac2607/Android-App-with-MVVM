package com.nyt.articles.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.nyt.articles.R;
import com.nyt.articles.ui.fragment.ArticleDetailFragment;
import com.nyt.articles.ui.fragment.ArticleListFragment;
import com.nyt.articles.ui.fragment.util.FragmentUtil;
import com.nyt.articles.viewmodel.ArticlesViewModel;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
        actionBar.setDisplayShowTitleEnabled(true);
        FragmentUtil.replace(this, new ArticleListFragment(), R.id.fragment_container, true, null);

        ArticlesViewModel articleListViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(

                menuItem -> {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    FragmentUtil.popFragment(this);
                    showProgressBar();
                    switch (menuItem.getItemId()) {
                        case R.id.today:
                            articleListViewModel.loadArticles(1);
                            break;
                        case R.id.weekly:
                            articleListViewModel.loadArticles(7);
                            break;
                        case R.id.monthly:
                            articleListViewModel.loadArticles(30);
                            break;
                    }

                    return true;
                });
        articleListViewModel.loadArticles(1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return true;
    }

    private void showProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        findViewById(R.id.progressBar).setVisibility(View.GONE);

    }
}
