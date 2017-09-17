package com.example.productexpo.modules.home.product_cart.product;

import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.modules.base.BasePresenter;

/**
 * Created on 9/17/2017.
 */

public interface ProductPresenter extends BasePresenter{
    /**
     * Method to set reference to recyclerView
     *
     * @param recyclerView to be handled by presenter
     * @param emptyView    to be set to EmptyRecyclerView
     */
    void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView);
}
