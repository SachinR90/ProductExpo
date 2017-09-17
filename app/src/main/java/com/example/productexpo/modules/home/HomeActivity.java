package com.example.productexpo.modules.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.productexpo.R;
import com.example.productexpo.modules.base.activity.BaseActivity;

/**
 * Created on 9/17/2017.
 */

public class HomeActivity extends BaseActivity implements HomeView {
    HomePresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (savedInstanceState == null) {
            presenter = new HomePresenterImpl(this);
            presenter.showProductCartFragment();
        } else {

        }
    }

    @Override
    public void initializeUIComponent() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }
}
