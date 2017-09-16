package com.example.productexpo.webservicemanager.interfaces;

/**
 * To notify when parsing started or completed
 * Created by SachinR on 26/Jan/2017.
 */

interface ParseListener {
    void onParseComplete(boolean isSuccessful, String resultMessage, Object result, int webServiceId);
}
