package com.example.productexpo.modules.base;

import android.content.res.Configuration;

/**
 * Created on 9/16/2017.
 */

public interface BasePresenter {
    void showErrorMessage(int errorCode, String message);

    void onConfigChanged(Configuration configuration);
}
