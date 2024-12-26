package com.tron.wallet.common.components.frescoimage.utils;

import android.graphics.PointF;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
public class DisplayOptimizeListener implements SubsamplingScaleImageView.OnImageEventListener {
    private static final int LONG_IMAGE_SIZE_RATIO = 2;
    private final SubsamplingScaleImageView mImageView;
    private int mInitScaleType;

    @Override
    public void onImageLoadError(Exception exc) {
    }

    @Override
    public void onImageLoaded() {
    }

    @Override
    public void onPreviewLoadError(Exception exc) {
    }

    @Override
    public void onPreviewReleased() {
    }

    @Override
    public void onTileLoadError(Exception exc) {
    }

    public void setInitScaleType(int i) {
        this.mInitScaleType = i;
    }

    public DisplayOptimizeListener(SubsamplingScaleImageView subsamplingScaleImageView) {
        this.mImageView = subsamplingScaleImageView;
    }

    @Override
    public void onReady() {
        float f;
        float f2;
        float f3;
        int sWidth = this.mImageView.getSWidth();
        int sHeight = this.mImageView.getSHeight();
        int width = this.mImageView.getWidth();
        int height = this.mImageView.getHeight();
        boolean z = sWidth == 0 || sHeight == 0 || width == 0 || height == 0;
        if (z) {
            f = 0.5f;
        } else {
            if (sWidth <= sHeight) {
                f2 = width;
                f3 = sWidth;
            } else {
                f2 = height;
                f3 = sHeight;
            }
            f = f2 / f3;
        }
        if (!z && sHeight / sWidth > 2.0f) {
            this.mImageView.animateScaleAndCenter(f, new PointF(sWidth / 2, 0.0f)).withEasing(1).start();
        }
        if (Math.abs(f - 0.1d) < 0.20000000298023224d) {
            f += 0.2f;
        }
        if (this.mInitScaleType == 7) {
            float f4 = width / sWidth;
            float f5 = height / sHeight;
            float max = Math.max(f4, f5);
            if (max > 1.0f) {
                this.mImageView.setMinScale(1.0f);
                this.mImageView.setMaxScale(Math.max(this.mImageView.getMaxScale(), 1.2f * max));
            } else {
                this.mImageView.setMinScale(Math.min(f4, f5));
            }
            this.mImageView.setScaleAndCenter(max, new PointF(sWidth / 2, sHeight / 2));
        }
        this.mImageView.setDoubleTapZoomScale(f);
    }
}
