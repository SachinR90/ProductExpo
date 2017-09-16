package com.example.productexpo.modules.base.fragment;

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
     * initialize all your views and objects in this method
     *
     * @param v rootLayout
     */
    void bindControls(View v);

    /**
     * set values to your views and objects in this method
     */
    void bindValues();

    /**
     * set listeners to your views and objects in this method
     */
    void bindListeners();

    /**
     * set adapter to the required views.
     */
    void bindAdapters();

    /**
     * This is used to check if current activity is null or finishing
     *
     * @return true if finishing/null else false;
     */
    boolean isActivityFinishing();
}
