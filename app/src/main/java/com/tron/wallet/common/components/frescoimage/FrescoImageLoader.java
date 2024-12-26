package com.tron.wallet.common.components.frescoimage;

import android.content.Context;
import android.net.Uri;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.DefaultExecutorSupplier;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.request.ImageRequest;
import com.tron.wallet.common.components.frescoimage.loader.ImageLoader;
import com.tron.wallet.common.components.frescoimage.metadata.ImageInfoExtractor;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
public final class FrescoImageLoader implements ImageLoader {
    private final Context mAppContext;
    private final ConcurrentHashMap<Integer, DataSource> mRequestSourceMap = new ConcurrentHashMap<>();
    private final DefaultExecutorSupplier mExecutorSupplier = new DefaultExecutorSupplier(Runtime.getRuntime().availableProcessors());

    private FrescoImageLoader(Context context) {
        this.mAppContext = context;
    }

    public static FrescoImageLoader with(Context context) {
        return with(context, null, null);
    }

    public static FrescoImageLoader with(Context context, ImagePipelineConfig imagePipelineConfig) {
        return with(context, imagePipelineConfig, null);
    }

    public static FrescoImageLoader with(Context context, ImagePipelineConfig imagePipelineConfig, DraweeConfig draweeConfig) {
        Fresco.initialize(context, imagePipelineConfig, draweeConfig);
        return new FrescoImageLoader(context);
    }

    @Override
    public void loadImage(int i, Uri uri, final ImageLoader.Callback callback) {
        ImageRequest fromUri = ImageRequest.fromUri(uri);
        final File cacheFile = getCacheFile(fromUri);
        if (cacheFile.exists()) {
            this.mExecutorSupplier.forLocalStorageRead().execute(new Runnable() {
                @Override
                public void run() {
                    callback.onCacheHit(ImageInfoExtractor.getImageType(cacheFile), cacheFile);
                    callback.onSuccess(cacheFile);
                }
            });
            return;
        }
        callback.onStart();
        callback.onProgress(0);
        DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage = Fresco.getImagePipeline().fetchEncodedImage(fromUri, true);
        fetchEncodedImage.subscribe(new ImageDownloadSubscriber(this.mAppContext) {
            @Override
            protected void onProgress(int i2) {
                callback.onProgress(i2);
            }

            @Override
            protected void onSuccess(File file) {
                callback.onFinish();
                callback.onCacheMiss(ImageInfoExtractor.getImageType(file), file);
                callback.onSuccess(file);
            }

            @Override
            protected void onFail(Throwable th) {
                th.printStackTrace();
                callback.onFail((Exception) th);
            }
        }, this.mExecutorSupplier.forBackgroundTasks());
        closeSource(i);
        saveSource(i, fetchEncodedImage);
    }

    @Override
    public void prefetch(Uri uri) {
        Fresco.getImagePipeline().prefetchToDiskCache(ImageRequest.fromUri(uri), false);
    }

    @Override
    public void cancel(int i) {
        closeSource(i);
    }

    private void saveSource(int i, DataSource dataSource) {
        this.mRequestSourceMap.put(Integer.valueOf(i), dataSource);
    }

    private void closeSource(int i) {
        DataSource remove = this.mRequestSourceMap.remove(Integer.valueOf(i));
        if (remove != null) {
            remove.close();
        }
    }

    private File getCacheFile(ImageRequest imageRequest) {
        FileCache mainFileCache = ImagePipelineFactory.getInstance().getMainFileCache();
        CacheKey encodedCacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(imageRequest, false);
        File sourceFile = imageRequest.getSourceFile();
        return (!mainFileCache.hasKey(encodedCacheKey) || mainFileCache.getResource(encodedCacheKey) == null) ? sourceFile : ((FileBinaryResource) mainFileCache.getResource(encodedCacheKey)).getFile();
    }
}
