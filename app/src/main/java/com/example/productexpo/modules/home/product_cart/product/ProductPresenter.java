package com.example.productexpo.modules.home.product_cart.product;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public interface ProductPresenter extends BasePresenter {
    String KEY_PRODUCT_LIST = "KEY_PRODUCT_LIST";

    /**
     * Method to set reference to recyclerView
     *  @param recyclerView to be handled by presenter
     * @param emptyView    to be set to EmptyRecyclerView
     * @param mSwipeRefreshLayout
     */
    void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView, SwipeRefreshLayout mSwipeRefreshLayout);

    /**
     * request focus on empty
     */
    void requestFocusOnEmptyView();

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
     * add the product to cart list
     *
     * @param product to be added to list
     */
    void addProductToCartList(Product product);
}
