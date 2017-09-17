package com.example.productexpo.modules.home.product_cart;

import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.webservicemanager.OkHttpResponseDetails;
import com.example.productexpo.webservicemanager.Task;

import okhttp3.Request;

/**
 * Created on 9/17/2017.
 */

public class ProductCartInteractorImpl implements ProductCartInteractor {
    ProductCartPresenter presenter;

    public ProductCartInteractorImpl(ProductCartPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSyncProgressUpdate(long bytesRead, long contentLength, long percentDone, boolean done) {

    }

    @Override
    public void onSyncSuccess(Request originalRequest, OkHttpResponseDetails responseResult, int webServiceId) {
        ProductResponse productResponse = (ProductResponse) responseResult.parsedObject;
        presenter.onGetProductsReceived(productResponse);
    }

    @Override
    public void onSyncFailure(Request request, Object result, int webServiceId, String errorMessage) {
        presenter.showErrorMessage(0,errorMessage);
    }

    @Override
    public void getProducts() {
        new Task(this).getProducts();
    }
}
