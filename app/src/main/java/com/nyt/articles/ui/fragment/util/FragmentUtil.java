package com.nyt.articles.ui.fragment.util;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Raji on 26/09/2018.
 */
public class FragmentUtil {


    /**
     * generic method to perform fragment transactions
     *
     * @param activity     desired hosting activity of the fragment
     * @param fragment     fragment instance
     * @param id           container id
     * @param animate      true/false value to animate or not
     * @param backStackTag tag to be added with fragment transaction
     */
    public static void replace(FragmentActivity activity, Fragment fragment, int id, boolean animate, @Nullable String backStackTag) {

        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if (backStackTag != null)
            fragmentTransaction.addToBackStack(backStackTag);
        if (animate)
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        fragmentTransaction.replace(id, fragment).commit();
    }

    public static void popFragment(FragmentActivity activity) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager.popBackStackImmediate();

    }
}
