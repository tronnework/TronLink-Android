package com.tron.wallet.common.components.frescoimage;

import android.net.Uri;
import com.tron.wallet.common.components.frescoimage.loader.ImageLoader;
public final class BigImageViewer {
    private static volatile BigImageViewer sInstance;
    private final ImageLoader mImageLoader;

    private BigImageViewer(ImageLoader imageLoader) {
        this.mImageLoader = imageLoader;
    }

    public static void initialize(ImageLoader imageLoader) {
        sInstance = new BigImageViewer(imageLoader);
    }

    public static ImageLoader imageLoader() {
        if (sInstance == null) {
            throw new IllegalStateException("You must initialize BigImageViewer before use it!");
        }
        return sInstance.mImageLoader;
    }

    public static void prefetch(Uri... uriArr) {
        if (uriArr == null) {
            return;
        }
        ImageLoader imageLoader = imageLoader();
        for (Uri uri : uriArr) {
            imageLoader.prefetch(uri);
        }
    }
}
