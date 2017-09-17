package com.example.productexpo.modules.home.product_cart;

import com.example.productexpo.data.Preferences;
import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.webservicemanager.OkHttpResponseDetails;
import com.example.productexpo.webservicemanager.Task;
import com.google.gson.Gson;

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
        //save to shared preference
        Preferences instance = Preferences.getInstance();
        instance.setObjectAsString(Preferences.Key.KEY_PRODUCT_LIST, new Gson().toJson(productResponse));
        presenter.onGetProductsReceived(productResponse);
    }

    @Override
    public void onSyncFailure(Request request, Object result, int webServiceId, String errorMessage) {
        presenter.showErrorMessage(0,errorMessage);
        Preferences instance = Preferences.getInstance();
        String objectAsString = instance.getObjectAsString(Preferences.Key.KEY_PRODUCT_LIST);
        try {
            ProductResponse productResponse = new Gson().fromJson(objectAsString, ProductResponse.class);
            presenter.onGetProductsReceived(productResponse);
        } catch (Exception ex) {
        }
    }

    @Override
    public void getProducts() {
        new Task(this).getProducts();
    }
}
