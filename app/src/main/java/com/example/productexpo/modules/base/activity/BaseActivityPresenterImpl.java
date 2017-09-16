package com.example.productexpo.modules.base.activity;

/**
 * Created on 9/16/2017.
 */

public class BaseActivityPresenterImpl implements BaseActivityPresenter {
    private BaseActivityView baseActivityView;

    public BaseActivityPresenterImpl(BaseActivityView baseActivityView) {
        this.baseActivityView = baseActivityView;
    }

    @Override
    public void initializeViewAndObject() {
        baseActivityView.bindControls();
        baseActivityView.bindValues();
        baseActivityView.bindListeners();
        baseActivityView.bindAdapters();
    }
}
