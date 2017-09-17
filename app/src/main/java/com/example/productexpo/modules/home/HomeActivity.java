package com.example.productexpo.modules.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.productexpo.R;
import com.example.productexpo.modules.base.activity.BaseActivity;

/**
 * Created on 9/17/2017.
 */

public class HomeActivity extends BaseActivity implements HomeView {
    private Toolbar toolbar;
    HomePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenterImpl(this);
        setContentView(R.layout.activity_home);

    }

    @Override
    public void initializeUIComponent() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        presenter.showProductCartFragment();
    }
}
