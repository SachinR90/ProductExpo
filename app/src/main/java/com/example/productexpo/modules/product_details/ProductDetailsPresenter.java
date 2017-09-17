package com.example.productexpo.modules.product_details;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.BasePresenter;

/**
 * Created on 9/17/2017.
 */

public interface ProductDetailsPresenter extends BasePresenter {
    void restoreInstance(Bundle savedInstanceState);

    void saveInstance(Bundle outState);

    void updateViewPager(Product product);

    void handlerViewPager(ViewPager vpProductDetails, View emptyView);

}
