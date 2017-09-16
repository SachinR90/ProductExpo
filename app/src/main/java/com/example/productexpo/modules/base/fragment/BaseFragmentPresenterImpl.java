package com.example.productexpo.modules.base.fragment;

import android.view.View;

/**
 * Created on 9/17/2017.
 */

public class BaseFragmentPresenterImpl implements BaseFragmentPresenter {

    BaseFragmentView baseFragmentView;

    public BaseFragmentPresenterImpl(BaseFragmentView baseFragmentView) {
        this.baseFragmentView = baseFragmentView;
    }

    @Override
    public void initializeViewAndObject(View v) {
        this.baseFragmentView.bindControls(v);
        this.baseFragmentView.bindValues();
        this.baseFragmentView.bindListeners();
        this.baseFragmentView.bindAdapters();
    }
}
