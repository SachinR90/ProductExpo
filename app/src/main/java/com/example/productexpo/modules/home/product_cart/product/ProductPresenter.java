package com.example.productexpo.modules.home.product_cart.product;

import android.os.Bundle;
import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.entities.Product;
import com.example.productexpo.modules.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public interface ProductPresenter extends BasePresenter{
    String KEY_PRODUC_LIST = "KEY_PRODUCT_LIST";
    /**
     * Method to set reference to recyclerView
     *
     * @param recyclerView to be handled by presenter
     * @param emptyView    to be set to EmptyRecyclerView
     */
    void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView);

    /**
     * request focus on empty
     */
    void requestFocusOnEmptyView();


    void updateProductList(List<Product> productList);


    void restoreInstance(Bundle savedInstanceState);

    void saveInstance(Bundle outState);

    ArrayList<Product> getProductList();
}
