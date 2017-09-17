package com.example.productexpo.modules.home.product_cart.product;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.entities.Product;
import com.example.productexpo.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/17/2017.
 */

public class ProductPresenterImpl implements ProductPresenter {
    ProductView productView;
    EmptyRecyclerView recyclerView;

    public ProductPresenterImpl(ProductView productView) {
        this.productView = productView;
    }

    @Override
    public void handleEmptyRecyclerView(EmptyRecyclerView recyclerView, View emptyView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setEmptyView(emptyView);

        //set empty adapter
        this.recyclerView.setAdapter(new RVAdapterProductList(productView.getViewContext(), new ArrayList<Product>()));

        int count;
        if (!UIUtils.isTablet(productView.getViewContext()) && UIUtils.getDeviceOrientation(productView.getViewContext()) == Configuration.ORIENTATION_LANDSCAPE) {
            count = 3;
        } else {
            count = 2;
        }
        this.recyclerView.setLayoutManager(new GridLayoutManager(productView.getViewContext(), count, GridLayoutManager.VERTICAL, false));
    }

    @Override
    public void requestFocusOnEmptyView() {
        recyclerView.getEmptyView().requestFocus();
    }

    @Override
    public void updateProductList(List<Product> productList) {
        if (this.recyclerView != null) {
            RVAdapterProductList adapter = (RVAdapterProductList) this.recyclerView.getAdapter();
            adapter.replaceList(productList);
        }
    }

    @Override
    public void restoreInstance(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            List<Product>  productList = savedInstanceState.getParcelableArrayList(KEY_PRODUC_LIST);
            if (productList == null) {
                productList = new ArrayList<>(0);
            }
            updateProductList(productList);
        }
    }

    @Override
    public void saveInstance(Bundle outState) {
        outState.putParcelableArrayList(KEY_PRODUC_LIST,getProductList());
    }

    @Override
    public ArrayList<Product> getProductList() {
        RVAdapterProductList adapter = (RVAdapterProductList) this.recyclerView.getAdapter();
        return adapter.getData();
    }

    @Override
    public void showErrorMessage(int errorCode, String message) {

    }

    @Override
    public void onConfigChanged(Configuration configuration) {
        int count;
        if (!UIUtils.isTablet(productView.getViewContext()) && UIUtils.getDeviceOrientation(productView.getViewContext()) == Configuration.ORIENTATION_LANDSCAPE) {
            count = 3;
        } else {
            count = 2;
        }
        this.recyclerView.setLayoutManager(new GridLayoutManager(productView.getViewContext(), count, GridLayoutManager.VERTICAL, false));
    }
}
