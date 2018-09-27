package com.nyt.articles.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nyt.articles.R;
import com.nyt.articles.ui.fragment.ArticleListFragment;
import com.nyt.articles.ui.fragment.util.FragmentUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);

        FragmentUtil.replace(this, new ArticleListFragment(), R.id.fragment_container, true, null);

    }
}
