package com.tron.wallet.common.components.frescoimage.view;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.File;
public class ImageViewFactory {
    protected View createAnimatedImageView(Context context, int i, File file, int i2) {
        return null;
    }

    public View createThumbnailView(Context context, Uri uri, ImageView.ScaleType scaleType) {
        return null;
    }

    public final View createMainView(Context context, int i, File file, int i2) {
        if (i == 1 || i == 2) {
            return createAnimatedImageView(context, i, file, i2);
        }
        return createStillImageView(context);
    }

    protected SubsamplingScaleImageView createStillImageView(Context context) {
        return new SubsamplingScaleImageView(context);
    }
}
