package com.example.productexpo.webservicemanager.interfaces;

/**
 * This is used to listen to progress of {@link okhttp3.Request} in OkHttpCall
 * Created by SachinR on 25/Jan/2017.
 */
public interface ProgressListener {

    /**
     * Update the progress back to UI
     *
     * @param bytesRead     - total bytes read by the call
     * @param contentLength - total length of the response
     * @param percentDone   - percentage done
     * @param done          - check if the reading is done or not
     */
    void onSyncProgressUpdate(long bytesRead, long contentLength, long percentDone, boolean done);
}