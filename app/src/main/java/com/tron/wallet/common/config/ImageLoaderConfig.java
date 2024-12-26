package com.tron.wallet.common.config;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.listener.RequestLoggingListener;
import java.io.File;
import java.util.HashSet;
import okhttp3.OkHttpClient;
public class ImageLoaderConfig {
    private static final String IMAGE_PIPELINE_CACHE_DIR = "image_cache";
    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "image_small_cache";
    private static final int MAX_DISK_SMALL_CACHE_SIZE = 10485760;
    private static final int MAX_DISK_SMALL_ONLOWDISKSPACE_CACHE_SIZE = 5242880;
    private static ImagePipelineConfig sImagePipelineConfig;

    public static ImagePipelineConfig getImagePipelineConfig(Context context) {
        if (sImagePipelineConfig == null) {
            File cacheDir = context.getApplicationContext().getCacheDir();
            DiskCacheConfig build = DiskCacheConfig.newBuilder(context).setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR).setBaseDirectoryPath(cacheDir).build();
            DiskCacheConfig build2 = DiskCacheConfig.newBuilder(context).setBaseDirectoryPath(cacheDir).setBaseDirectoryName(IMAGE_PIPELINE_SMALL_CACHE_DIR).setMaxCacheSize(10485760L).setMaxCacheSizeOnLowDiskSpace(5242880L).build();
            HashSet hashSet = new HashSet();
            hashSet.add(new RequestLoggingListener());
            NoOpMemoryTrimmableRegistry noOpMemoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
            noOpMemoryTrimmableRegistry.registerMemoryTrimmable(new MemoryTrimmable() {
                @Override
                public void trim(MemoryTrimType memoryTrimType) {
                    double suggestedTrimRatio = memoryTrimType.getSuggestedTrimRatio();
                    if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio || MemoryTrimType.OnSystemLowMemoryWhileAppInBackgroundLowSeverity.getSuggestedTrimRatio() == suggestedTrimRatio || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio) {
                        Fresco.getImagePipeline().clearMemoryCaches();
                    }
                }
            });
            new OkHttpClient.Builder().build();
            sImagePipelineConfig = ImagePipelineConfig.newBuilder(context).setBitmapsConfig(Bitmap.Config.RGB_565).setDownsampleEnabled(true).setProgressiveJpegConfig(new ProgressiveJpegConfig() {
                @Override
                public boolean decodeProgressively() {
                    return true;
                }

                @Override
                public int getNextScanNumberToDecode(int i) {
                    return i + 2;
                }

                @Override
                public QualityInfo getQualityInfo(int i) {
                    return ImmutableQualityInfo.of(i, i >= 5, false);
                }
            }).setRequestListeners(hashSet).setMemoryTrimmableRegistry(noOpMemoryTrimmableRegistry).setBitmapMemoryCacheParamsSupplier(new BitmapMemoryCacheParamsSupplier((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))).setMainDiskCacheConfig(build).setSmallImageDiskCacheConfig(build2).setResizeAndRotateEnabledForNetwork(true).build();
        }
        return sImagePipelineConfig;
    }

    public static class BitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams> {
        private final ActivityManager mActivityManager;

        public BitmapMemoryCacheParamsSupplier(ActivityManager activityManager) {
            this.mActivityManager = activityManager;
        }

        @Override
        public MemoryCacheParams get() {
            return new MemoryCacheParams(getMaxCacheSize(), 56, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        private int getMaxCacheSize() {
            int min = Math.min(this.mActivityManager.getMemoryClass() * 1048576, Integer.MAX_VALUE);
            if (min < 33554432) {
                return 4194304;
            }
            if (min < 67108864) {
                return 6291456;
            }
            return min / 4;
        }
    }
}
