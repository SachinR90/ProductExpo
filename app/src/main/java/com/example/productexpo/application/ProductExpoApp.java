package com.example.productexpo.application;

import android.app.Application;

import com.example.productexpo.BuildConfig;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

/**
 * Created on 9/16/2017.
 */

public class ProductExpoApp extends Application {
    public static final long MAX_REQUEST_TIMEOUT_MS = 1000 * 60;//60seconds

    /**
     * Application Instance
     */
    private static ProductExpoApp mInstance;

    /**
     * single instance of okhttp client
     */
    private static OkHttpClient CLIENT;

    public static OkHttpClient getOkHttpClient() {
        if (CLIENT == null) {
            // create an instance of OkHttpClient builder
            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder.readTimeout(MAX_REQUEST_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .writeTimeout(MAX_REQUEST_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .connectTimeout(MAX_REQUEST_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true);
            if (BuildConfig.IS_IN_DEBUG) {
                LoggingInterceptor.Builder builder = new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.IS_IN_DEBUG)
                        .setLevel(Level.BASIC)
                        .log(Platform.INFO)
                        .request("Request")
                        .response("Response");
                okHttpBuilder.addInterceptor(builder.build());
            }
            //create client
            CLIENT = okHttpBuilder.build();
        }
        return CLIENT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //create instance of okhttp to use
        getOkHttpClient();
    }
    public static synchronized ProductExpoApp getAppContext() {
        return mInstance;
    }
}
