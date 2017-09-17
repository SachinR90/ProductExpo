package com.example.productexpo.modules.base.activity;

import android.support.v4.app.FragmentManager;

import com.example.productexpo.modules.base.BaseView;

/**
 * Created on 9/16/2017.
 */

public interface BaseActivityView extends BaseView {

    /**
     * initialize the UI components
     */
    void initializeUIComponent();

    /**
     * this is used to get the FragmentManager object.
     */
    FragmentManager getManagerForFragment();
}
