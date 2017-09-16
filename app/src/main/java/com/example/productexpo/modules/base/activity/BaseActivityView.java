package com.example.productexpo.modules.base.activity;

import com.example.productexpo.modules.base.BaseView;

/**
 * Created on 9/16/2017.
 */

public interface BaseActivityView extends BaseView {
    void bindControls();

    void bindValues();

    void bindListeners();

    void bindAdapters();
}
