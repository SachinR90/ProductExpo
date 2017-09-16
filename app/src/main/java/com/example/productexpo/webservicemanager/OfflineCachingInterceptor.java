package com.example.productexpo.webservicemanager;

import com.example.productexpo.application.ProductExpoApp;
import com.example.productexpo.utils.AppUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OfflineCachingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        //Checking if the device is online
        if (!(AppUtils.checkIsInternetConnected(ProductExpoApp.getAppContext()))) {
            // 1 day stale
            int maxStale = 24 * 60 * 60;//in seconds
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return chain.proceed(request);
    }
}