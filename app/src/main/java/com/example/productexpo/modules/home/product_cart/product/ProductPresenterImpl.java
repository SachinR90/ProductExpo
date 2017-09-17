package com.example.productexpo.modules.home.product_cart.product;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.productexpo.customviews.EmptyRecyclerView;
import com.example.productexpo.webservicemanager.OkHttpResponseDetails;

import okhttp3.Request;

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

        //todo set to 3 on landscape
        this.recyclerView.setLayoutManager(new GridLayoutManager(productView.getViewContext(), 2));


    }

    @Override
    public void onSyncProgressUpdate(long bytesRead, long contentLength, long percentDone, boolean done) {

    }

    @Override
    public void onSyncSuccess(Request originalRequest, OkHttpResponseDetails responseResult, int webServiceId) {

    }

    @Override
    public void onSyncFailure(Request request, Object result, int webServiceId, String errorMessage) {

    }
}
