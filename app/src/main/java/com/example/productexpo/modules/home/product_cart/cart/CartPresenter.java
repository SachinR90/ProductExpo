package com.example.productexpo.modules.home.product_cart.cart;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.modules.base.BasePresenter;

/**
 * Created on 9/17/2017.
 */

public interface CartPresenter extends BasePresenter {
    /**
     * Handles the EmptyRecyclerView and EmptyView
     *
     * @param emptyRecyclerView Recyclerview reference
     * @param emptyView         view reference
     */
    void handleEmptyRecyclerView(EmptyRecyclerView emptyRecyclerView, View emptyView);

    /**
     * request focus on empty
     */
    void requestFocusOnEmptyView();

    /**
     * Handles the price textView
     * @param tvTotalPrice price text view
     */
    void handlePriceText(AppCompatTextView tvTotalPrice);
}
