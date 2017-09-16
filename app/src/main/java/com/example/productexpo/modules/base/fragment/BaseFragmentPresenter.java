package com.example.productexpo.modules.base.fragment;

import android.view.View;

/**
 * Created on 9/17/2017.
 */

public interface BaseFragmentPresenter {
    /**
     * <b>Note: </b> Override this method to use your own logic and sequence of binding
     *
     * @param v view to find other views
     */
    void initializeViewAndObject(View v);

}
