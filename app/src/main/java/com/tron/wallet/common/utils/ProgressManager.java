package com.tron.wallet.common.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tron.wallet.common.utils.progressmanager.ProgressListener;
import com.tron.wallet.common.utils.progressmanager.ProgressRequestBody;
import com.tron.wallet.common.utils.progressmanager.ProgressResponseBody;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public final class ProgressManager {
    private static final int DEFAULT_REFRESH_TIME = 150;
    private static final boolean DEPENDENCY_OKHTTP;
    private static final String IDENTIFICATION_HEADER = "JessYan";
    private static final String IDENTIFICATION_NUMBER = "?JessYan=";
    private static final String LOCATION_HEADER = "Location";
    private static final String OKHTTP_PACKAGE_NAME = "okhttp3.OkHttpClient";
    private static volatile ProgressManager mProgressManager;
    private final Map<String, List<ProgressListener>> mRequestListeners = new WeakHashMap();
    private final Map<String, List<ProgressListener>> mResponseListeners = new WeakHashMap();
    private int mRefreshTime = DEFAULT_REFRESH_TIME;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            ProgressManager progressManager = ProgressManager.this;
            return progressManager.wrapResponseBody(chain.proceed(progressManager.wrapRequestBody(chain.request())));
        }
    };

    static {
        boolean z;
        try {
            Class.forName(OKHTTP_PACKAGE_NAME);
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        DEPENDENCY_OKHTTP = z;
    }

    private ProgressManager() {
    }

    public static final ProgressManager getInstance() {
        if (mProgressManager == null) {
            if (!DEPENDENCY_OKHTTP) {
                throw new IllegalStateException("Must be dependency Okhttp");
            }
            synchronized (ProgressManager.class) {
                if (mProgressManager == null) {
                    mProgressManager = new ProgressManager();
                }
            }
        }
        return mProgressManager;
    }

    public void setRefreshTime(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("refreshTime must be >= 0");
        }
        this.mRefreshTime = i;
    }

    public void addRequestListener(String str, ProgressListener progressListener) {
        List<ProgressListener> list;
        checkNotNull(str, "url cannot be null");
        checkNotNull(progressListener, "listener cannot be null");
        synchronized (ProgressManager.class) {
            list = this.mRequestListeners.get(str);
            if (list == null) {
                list = new LinkedList<>();
                this.mRequestListeners.put(str, list);
            }
        }
        list.add(progressListener);
    }

    public void addResponseListener(String str, ProgressListener progressListener) {
        List<ProgressListener> list;
        checkNotNull(str, "url cannot be null");
        checkNotNull(progressListener, "listener cannot be null");
        synchronized (ProgressManager.class) {
            list = this.mResponseListeners.get(str);
            if (list == null) {
                list = new LinkedList<>();
                this.mResponseListeners.put(str, list);
            }
        }
        list.add(progressListener);
    }

    public void notifyOnErorr(String str, Exception exc) {
        checkNotNull(str, "url cannot be null");
        forEachListenersOnError(this.mRequestListeners, str, exc);
        forEachListenersOnError(this.mResponseListeners, str, exc);
    }

    public OkHttpClient.Builder with(OkHttpClient.Builder builder) {
        checkNotNull(builder, "builder cannot be null");
        return builder.addNetworkInterceptor(this.mInterceptor);
    }

    public Request wrapRequestBody(Request request) {
        if (request == null) {
            return request;
        }
        String httpUrl = request.url().toString();
        Request pruneIdentification = pruneIdentification(httpUrl, request);
        if (pruneIdentification.body() != null && this.mRequestListeners.containsKey(httpUrl)) {
            return pruneIdentification.newBuilder().method(pruneIdentification.method(), new ProgressRequestBody(this.mHandler, pruneIdentification.body(), this.mRequestListeners.get(httpUrl), this.mRefreshTime)).build();
        }
        return pruneIdentification;
    }

    private Request pruneIdentification(String str, Request request) {
        return !str.contains(IDENTIFICATION_NUMBER) ? request : request.newBuilder().url(str.substring(0, str.indexOf(IDENTIFICATION_NUMBER))).header(IDENTIFICATION_HEADER, str).build();
    }

    public Response wrapResponseBody(Response response) {
        if (response == null) {
            return response;
        }
        String httpUrl = response.request().url().toString();
        if (!TextUtils.isEmpty(response.request().header(IDENTIFICATION_HEADER))) {
            httpUrl = response.request().header(IDENTIFICATION_HEADER);
        }
        if (response.isRedirect()) {
            resolveRedirect(this.mRequestListeners, response, httpUrl);
            return modifyLocation(response, resolveRedirect(this.mResponseListeners, response, httpUrl));
        } else if (response.body() != null && this.mResponseListeners.containsKey(httpUrl)) {
            return response.newBuilder().body(new ProgressResponseBody(this.mHandler, response.body(), this.mResponseListeners.get(httpUrl), this.mRefreshTime)).build();
        } else {
            return response;
        }
    }

    private Response modifyLocation(Response response, String str) {
        return (TextUtils.isEmpty(str) || !str.contains(IDENTIFICATION_NUMBER)) ? response : response.newBuilder().header("Location", str).build();
    }

    public String addDiffResponseListenerOnSameUrl(String str, ProgressListener progressListener) {
        return addDiffResponseListenerOnSameUrl(str, String.valueOf(SystemClock.elapsedRealtime() + progressListener.hashCode()), progressListener);
    }

    public String addDiffResponseListenerOnSameUrl(String str, String str2, ProgressListener progressListener) {
        String str3 = str + IDENTIFICATION_NUMBER + str2;
        addResponseListener(str3, progressListener);
        return str3;
    }

    public String addDiffRequestListenerOnSameUrl(String str, ProgressListener progressListener) {
        return addDiffRequestListenerOnSameUrl(str, String.valueOf(SystemClock.elapsedRealtime() + progressListener.hashCode()), progressListener);
    }

    public String addDiffRequestListenerOnSameUrl(String str, String str2, ProgressListener progressListener) {
        String str3 = str + IDENTIFICATION_NUMBER + str2;
        addRequestListener(str3, progressListener);
        return str3;
    }

    private String resolveRedirect(Map<String, List<ProgressListener>> map, Response response, String str) {
        List<ProgressListener> list = map.get(str);
        if (list == null || list.size() <= 0) {
            return null;
        }
        String header = response.header("Location");
        if (TextUtils.isEmpty(header)) {
            return header;
        }
        if (str.contains(IDENTIFICATION_NUMBER) && !header.contains(IDENTIFICATION_NUMBER)) {
            header = header + str.substring(str.indexOf(IDENTIFICATION_NUMBER), str.length());
        }
        if (!map.containsKey(header)) {
            map.put(header, list);
            return header;
        }
        List<ProgressListener> list2 = map.get(header);
        for (ProgressListener progressListener : list) {
            if (!list2.contains(progressListener)) {
                list2.add(progressListener);
            }
        }
        return header;
    }

    private void forEachListenersOnError(Map<String, List<ProgressListener>> map, String str, Exception exc) {
        if (map.containsKey(str)) {
            List<ProgressListener> list = map.get(str);
            for (ProgressListener progressListener : (ProgressListener[]) list.toArray(new ProgressListener[list.size()])) {
                progressListener.onError(-1L, exc);
            }
        }
    }

    static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
