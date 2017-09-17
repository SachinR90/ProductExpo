package com.example.productexpo.modules.base.fragment;

import android.support.v4.app.FragmentManager;
import android.view.View;

import com.example.productexpo.modules.base.BaseView;

/**
 * Created on 9/17/2017.
 */

public interface BaseFragmentView extends BaseView {
    /**
     * Get resID.
     *
     * @return layout resource id for the fragment container
     */
    int getResId();

    /**
     * This is used to check if current activity is null or finishing
     *
     * @return true if finishing/null else false;
     */
    boolean isActivityFinishing();

    /**
     * Method to initialize UI components
     *
     * @param v root layout from which child views will be initialized
     */
    void initializeUIComponents(View v);


    /**
     * get Child Fragment Manager for fragment
     *
     * @return FragmentManager object returns Child FragmentManager
     */
    FragmentManager getChildManagerForFragment();
}
