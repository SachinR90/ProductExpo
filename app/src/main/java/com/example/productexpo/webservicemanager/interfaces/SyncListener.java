package com.example.productexpo.webservicemanager.interfaces;

import com.example.productexpo.webservicemanager.OkHttpResponseDetails;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Listener to check when the webservice is completed successfully or not
 * Created by SachinR on 25/Jan/2017.
 */
public interface SyncListener extends ProgressListener{
    /**
     * Called when the HTTP response was successfully returned by the remote server. The callback may
     * proceed to read the response body with {@link Response#body}. The response is still live until
     * its response body is {@linkplain ResponseBody closed}. The recipient of the callback may
     * consume the response body on another thread.
     * <p>
     * <p>Note that transport-layer success (receiving a HTTP response code, headers and body) does
     * not necessarily indicate application-layer success: {@code response} may still indicate an
     * unhappy HTTP response code like 404 or 500.
     *
     * @param originalRequest - original request made
     * @param responseResult  - response from web
     * @param webServiceId    - id of webservice called.
     */
    void  onSyncSuccess(Request originalRequest, OkHttpResponseDetails responseResult, int webServiceId);

    /**
     * Called when the request could not be executed due to cancellation, a connectivity problem or
     * timeout. Because networks can fail during an exchange, it is possible that the remote server
     * accepted the request before the failure.
     *
     * @param request      - original request made
     * @param result       - expected result
     * @param webServiceId - Id of webservice called
     * @param errorMessage - Message of error
     */
    void onSyncFailure(Request request, Object result, int webServiceId, String errorMessage);

}
