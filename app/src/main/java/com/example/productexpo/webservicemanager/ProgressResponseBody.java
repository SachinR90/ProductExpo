package com.example.productexpo.webservicemanager;

import android.os.Handler;
import android.os.Looper;

import com.example.productexpo.webservicemanager.interfaces.ProgressListener;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * https://github.com/square/okhttp/blob/master/samples/guide/src/main/java/okhttp3/recipes/Progress.java
 */
class ProgressResponseBody extends ResponseBody {
    /**
     * as this may be a context, An activity which can be garbage collected
     */
    private final WeakReference<ProgressListener> progressListener;
    private final ResponseBody                    responseBody;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.responseBody = responseBody;
        this.progressListener = new WeakReference<>(progressListener);
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                final long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        long contentLength = responseBody.contentLength();
                        ProgressListener listener = progressListener.get();
                        if (listener != null) {
                            listener.onSyncProgressUpdate(totalBytesRead, contentLength, ((100 * bytesRead) / contentLength), bytesRead == -1);
                        }
                    }
                });
                return bytesRead;
            }
        };
    }
}
