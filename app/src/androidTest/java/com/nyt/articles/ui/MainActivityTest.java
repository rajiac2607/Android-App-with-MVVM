package com.nyt.articles.ui;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;
import android.view.View;

import com.nyt.articles.R;
import com.nyt.articles.data.model.Article;
import com.nyt.articles.ui.fragment.ArticleListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private static final int ITEM_BELOW_THE_FOLD = 5;
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        // load list fragment in the activity before beginning to test
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ArticleListFragment()).commit();
    }

    @Test
    public void onRecyclerViewClick() {
        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(click());

    }

    @Test
    public void onQuerySearch() {
        onView(withId(R.id.action_search)).perform(click()).check(matches(isDisplayed()));
    }

    @Test
    public void onQueryEntered() {

        //When
        onView(allOf(withId(R.id.action_search), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))).perform(
                click());
        onView(withId(R.id.search_src_text)).perform(typeText("kavan"), pressKey(KeyEvent.KEYCODE_ENTER));

        //Then
        onView(withText("kavan")).check(matches((isDisplayed())));
    }


}
