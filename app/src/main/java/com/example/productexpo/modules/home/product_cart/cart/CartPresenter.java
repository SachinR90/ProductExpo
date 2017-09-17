package com.example.productexpo.modules.home.product_cart.cart;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public interface CartPresenter extends BasePresenter {
    String KEY_CART_LIST = "KEY_CART_LIST";

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
     *
     * @param tvTotalPrice price text view
     */
    void handlePriceText(AppCompatTextView tvTotalPrice);

    /**
     * Update Recyclerview when product list received
     *
     * @param productList
     */
    void updateProductList(List<Product> productList);

    /**
     * Restore states from specified bundle
     *
     * @param savedInstanceState bundle instance from which the state will be restored
     */
    void restoreInstance(Bundle savedInstanceState);

    /**
     * Save state to bundle
     *
     * @param outState in which the State will be saved
     */
    void saveInstance(Bundle outState);


    /**
     * get list form the recyclerview
     */
    ArrayList<Product> getProductList();


    /**
     * remove the product to cart list
     *
     * @param product to be removed
     */
    void removeProductFromCartList(int position, Product product);

    /**
     * Get cart list from shared preference
     */
    void refreshCartList();

}
