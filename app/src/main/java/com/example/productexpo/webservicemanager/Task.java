package com.example.productexpo.webservicemanager;

import com.example.productexpo.entities.ProductResponse;
import com.example.productexpo.webservicemanager.interfaces.SyncListener;
import com.example.productexpo.webservicemanager.interfaces.TaskId;
import com.example.productexpo.webservicemanager.interfaces.WebUrls;

import okhttp3.Request;

/**
 * Sole responsibility of this class is to call the web services
 * Created on 9/16/2017.
 */
public class Task {
    private WebServiceManager webServiceManager;

    /**
     * Construct new Task by injecting a listener for the web service
     *
     * @param listener - listen to the response from the web service
     */
    public Task(SyncListener listener,Class clazz) {
        webServiceManager = new WebServiceManager(listener);
    }

    ////////////////////////////////////////ADD POST/GET Method Below This line for readability//////////////////////////////////////////////////////

    /**
     * Get all product from the server.
     * URL:<br><p>
     * <a href="https://mobiletest-hackathon.herokuapp.com/getdata/">
     * https://mobiletest-hackathon.herokuapp.com/getdata/
     * </a>
     * </p>
     */
    public synchronized void getProducts() {
        Request request = new Request.Builder()
                .url(WebUrls.WS_GET_PRODUCTS)
                .tag(TaskId.WS_GET_PRODUCT)
                .build();

        webServiceManager.callWebservice(request, ProductResponse.class);
    }
}
