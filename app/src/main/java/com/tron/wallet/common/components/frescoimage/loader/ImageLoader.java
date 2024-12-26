package com.tron.wallet.common.components.frescoimage.loader;

import android.net.Uri;
import java.io.File;
public interface ImageLoader {

    public interface Callback {
        void onCacheHit(int i, File file);

        void onCacheMiss(int i, File file);

        void onFail(Exception exc);

        void onFinish();

        void onProgress(int i);

        void onStart();

        void onSuccess(File file);
    }

    void cancel(int i);

    void loadImage(int i, Uri uri, Callback callback);

    void prefetch(Uri uri);
}
