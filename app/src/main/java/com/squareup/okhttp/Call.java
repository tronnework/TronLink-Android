package com.squareup.okhttp;

import androidx.core.app.NotificationCompat;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.internal.Internal;
import com.squareup.okhttp.internal.NamedRunnable;
import com.squareup.okhttp.internal.http.HttpEngine;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Call {
    volatile boolean canceled;
    private final OkHttpClient client;
    HttpEngine engine;
    private boolean executed;
    Request originalRequest;

    public boolean isCanceled() {
        return this.canceled;
    }

    public Call(OkHttpClient okHttpClient, Request request) {
        this.client = okHttpClient.copyWithDefaults();
        this.originalRequest = request;
    }

    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        try {
            this.client.getDispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain(false);
            if (responseWithInterceptorChain != null) {
                return responseWithInterceptorChain;
            }
            throw new IOException("Canceled");
        } finally {
            this.client.getDispatcher().finished(this);
        }
    }

    public Object tag() {
        return this.originalRequest.tag();
    }

    public void enqueue(Callback callback) {
        enqueue(callback, false);
    }

    public void enqueue(Callback callback, boolean z) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.client.getDispatcher().enqueue(new AsyncCall(callback, z));
    }

    public void cancel() {
        this.canceled = true;
        HttpEngine httpEngine = this.engine;
        if (httpEngine != null) {
            httpEngine.cancel();
        }
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public final class AsyncCall extends NamedRunnable {
        private final boolean forWebSocket;
        private final Callback responseCallback;

        public Call get() {
            return Call.this;
        }

        private AsyncCall(Callback callback, boolean z) {
            super("OkHttp %s", originalRequest.urlString());
            this.responseCallback = callback;
            this.forWebSocket = z;
        }

        public String host() {
            return originalRequest.httpUrl().host();
        }

        Request request() {
            return originalRequest;
        }

        public Object tag() {
            return originalRequest.tag();
        }

        public void cancel() {
            cancel();
        }

        @Override
        protected void execute() {
            IOException e;
            boolean z;
            Response responseWithInterceptorChain;
            try {
                try {
                    responseWithInterceptorChain = getResponseWithInterceptorChain(this.forWebSocket);
                    z = true;
                } catch (IOException e2) {
                    e = e2;
                    z = false;
                }
                try {
                    if (canceled) {
                        this.responseCallback.onFailure(originalRequest, new IOException("Canceled"));
                    } else {
                        this.responseCallback.onResponse(responseWithInterceptorChain);
                    }
                } catch (IOException e3) {
                    e = e3;
                    if (z) {
                        Logger logger = Internal.logger;
                        Level level = Level.INFO;
                        logger.log(level, "Callback failure for " + toLoggableString(), (Throwable) e);
                    } else {
                        this.responseCallback.onFailure(engine == null ? originalRequest : engine.getRequest(), e);
                    }
                }
            } finally {
                client.getDispatcher().finished(this);
            }
        }
    }

    public String toLoggableString() {
        String str = this.canceled ? "canceled call" : NotificationCompat.CATEGORY_CALL;
        HttpUrl resolve = this.originalRequest.httpUrl().resolve("/...");
        return str + " to " + resolve;
    }

    public Response getResponseWithInterceptorChain(boolean z) throws IOException {
        return new ApplicationInterceptorChain(0, this.originalRequest, z).proceed(this.originalRequest);
    }

    public class ApplicationInterceptorChain implements Interceptor.Chain {
        private final boolean forWebSocket;
        private final int index;
        private final Request request;

        @Override
        public Connection connection() {
            return null;
        }

        @Override
        public Request request() {
            return this.request;
        }

        ApplicationInterceptorChain(int i, Request request, boolean z) {
            this.index = i;
            this.request = request;
            this.forWebSocket = z;
        }

        @Override
        public Response proceed(Request request) throws IOException {
            if (this.index < client.interceptors().size()) {
                ApplicationInterceptorChain applicationInterceptorChain = new ApplicationInterceptorChain(this.index + 1, request, this.forWebSocket);
                Interceptor interceptor = client.interceptors().get(this.index);
                Response intercept = interceptor.intercept(applicationInterceptorChain);
                if (intercept != null) {
                    return intercept;
                }
                throw new NullPointerException("application interceptor " + interceptor + " returned null");
            }
            return getResponse(request, this.forWebSocket);
        }
    }

    com.squareup.okhttp.Response getResponse(com.squareup.okhttp.Request r13, boolean r14) throws java.io.IOException {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.squareup.okhttp.Call.getResponse(com.squareup.okhttp.Request, boolean):com.squareup.okhttp.Response");
    }
}
