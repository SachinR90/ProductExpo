package com.example.productexpo.modules.home;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.productexpo.R;
import com.example.productexpo.modules.home.product_cart.ProductCartFragment;

/**
 * Created on 9/17/2017.
 */

public class HomePresenterImpl implements HomePresenter {
    HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void showErrorMessage(int errorCode, String message) {

    }

    @Override
    public void onConfigChanged(Configuration configuration) {

    }

    @Override
    public void showProductCartFragment() {
        FragmentManager managerForFragment = homeView.getManagerForFragment();
        //load first fragment
        FragmentTransaction transaction = managerForFragment.beginTransaction();
        transaction.replace(R.id.fl_fragment_container, ProductCartFragment.newInstance(), ProductCartFragment.class.getSimpleName());
        transaction.commit();
    }
}
