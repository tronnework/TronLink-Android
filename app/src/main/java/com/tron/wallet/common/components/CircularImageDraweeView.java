package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
public class CircularImageDraweeView extends SimpleDraweeView {
    public CircularImageDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
    }

    public CircularImageDraweeView(Context context) {
        super(context);
    }

    public CircularImageDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularImageDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CircularImageDraweeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setCircularImage(String str) {
        loadAsCircle(str);
    }

    private void loadAsCircle(String str) {
        setController(Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setPostprocessor(new CircleProcessor()).build()).build());
    }

    public static class CircleProcessor extends BasePostprocessor {
        @Override
        public void process(Bitmap bitmap, Bitmap bitmap2) {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            canvas.drawCircle(bitmap2.getWidth() >> 1, bitmap2.getHeight() >> 1, Math.min(bitmap2.getWidth(), bitmap2.getHeight()) >> 1, paint);
        }

        @Override
        public CacheKey getPostprocessorCacheKey() {
            return new SimpleCacheKey("CircleImageProcessor");
        }
    }
}
