package com.tron.wallet.common.components.frescoimage.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.tron.wallet.R;
import com.tron.wallet.common.components.frescoimage.BigImageViewer;
import com.tron.wallet.common.components.frescoimage.indicator.ProgressIndicator;
import com.tron.wallet.common.components.frescoimage.loader.ImageLoader;
import com.tron.wallet.common.components.frescoimage.metadata.ImageInfoExtractor;
import com.tron.wallet.common.components.frescoimage.utils.DisplayOptimizeListener;
import com.tron.wallet.common.components.frescoimage.utils.ThreadedCallbacks;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
public class BigImageView extends FrameLayout implements ImageLoader.Callback {
    public static final int DEFAULT_IMAGE_SCALE_TYPE = 3;
    public static final ImageView.ScaleType[] IMAGE_SCALE_TYPES = {ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_XY};
    public static final int INIT_SCALE_TYPE_CENTER = 0;
    public static final int INIT_SCALE_TYPE_CENTER_CROP = 1;
    public static final int INIT_SCALE_TYPE_CENTER_INSIDE = 2;
    public static final int INIT_SCALE_TYPE_CUSTOM = 7;
    public static final int INIT_SCALE_TYPE_FIT_CENTER = 3;
    public static final int INIT_SCALE_TYPE_FIT_END = 4;
    public static final int INIT_SCALE_TYPE_FIT_START = 5;
    public static final int INIT_SCALE_TYPE_FIT_XY = 6;
    public static final int INIT_SCALE_TYPE_START = 8;
    private File mCurrentImageFile;
    private DisplayOptimizeListener mDisplayOptimizeListener;
    private View.OnClickListener mFailureImageClickListener;
    private ImageView.ScaleType mFailureImageScaleType;
    private ImageView mFailureImageView;
    private final ImageLoader mImageLoader;
    private ImageSaveCallback mImageSaveCallback;
    private int mInitScaleType;
    private final ImageLoader.Callback mInternalCallback;
    private View mMainView;
    private View.OnClickListener mOnClickListener;
    private View.OnLongClickListener mOnLongClickListener;
    private boolean mOptimizeDisplay;
    private ProgressIndicator mProgressIndicator;
    private View mProgressIndicatorView;
    private SubsamplingScaleImageView mSSIV;
    private boolean mTapToRetry;
    private final List<File> mTempImages;
    private Uri mThumbnail;
    private ImageView.ScaleType mThumbnailScaleType;
    private View mThumbnailView;
    private Uri mUri;
    private ImageLoader.Callback mUserCallback;
    private ImageViewFactory mViewFactory;

    public File getCurrentImageFile() {
        return this.mCurrentImageFile;
    }

    public SubsamplingScaleImageView getSSIV() {
        return this.mSSIV;
    }

    public void setFailureImageInitScaleType(ImageView.ScaleType scaleType) {
        this.mFailureImageScaleType = scaleType;
    }

    public void setImageLoaderCallback(ImageLoader.Callback callback) {
        this.mUserCallback = callback;
    }

    public void setImageSaveCallback(ImageSaveCallback imageSaveCallback) {
        this.mImageSaveCallback = imageSaveCallback;
    }

    public void setImageViewFactory(ImageViewFactory imageViewFactory) {
        if (imageViewFactory == null) {
            return;
        }
        this.mViewFactory = imageViewFactory;
    }

    public void setProgressIndicator(ProgressIndicator progressIndicator) {
        this.mProgressIndicator = progressIndicator;
    }

    public void setTapToRetry(boolean z) {
        this.mTapToRetry = z;
    }

    public void setThumbnailScaleType(ImageView.ScaleType scaleType) {
        this.mThumbnailScaleType = scaleType;
    }

    public BigImageView(Context context) {
        this(context, null);
    }

    public BigImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BigImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFailureImageClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTapToRetry) {
                    BigImageView bigImageView = BigImageView.this;
                    bigImageView.showImage(bigImageView.mThumbnail, mUri);
                }
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(view);
                }
            }
        };
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.BigImageView, i, 0);
        this.mInitScaleType = obtainStyledAttributes.getInteger(2, 3);
        if (obtainStyledAttributes.hasValue(0)) {
            this.mFailureImageScaleType = scaleType(obtainStyledAttributes.getInteger(1, 3));
            setFailureImage(obtainStyledAttributes.getDrawable(0));
        }
        if (obtainStyledAttributes.hasValue(5)) {
            this.mThumbnailScaleType = scaleType(obtainStyledAttributes.getInteger(5, 3));
        }
        this.mOptimizeDisplay = obtainStyledAttributes.getBoolean(3, true);
        this.mTapToRetry = obtainStyledAttributes.getBoolean(4, true);
        obtainStyledAttributes.recycle();
        if (isInEditMode()) {
            this.mImageLoader = null;
        } else {
            this.mImageLoader = BigImageViewer.imageLoader();
        }
        this.mInternalCallback = (ImageLoader.Callback) ThreadedCallbacks.create(ImageLoader.Callback.class, this);
        this.mViewFactory = new ImageViewFactory();
        this.mTempImages = new ArrayList();
    }

    public static ImageView.ScaleType scaleType(int i) {
        if (i >= 0) {
            ImageView.ScaleType[] scaleTypeArr = IMAGE_SCALE_TYPES;
            if (i < scaleTypeArr.length) {
                return scaleTypeArr[i];
            }
        }
        return IMAGE_SCALE_TYPES[3];
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        View view = this.mMainView;
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    @Override
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.mOnLongClickListener = onLongClickListener;
        View view = this.mMainView;
        if (view != null) {
            view.setOnLongClickListener(onLongClickListener);
        }
    }

    public void setFailureImage(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (this.mFailureImageView == null) {
            ImageView imageView = new ImageView(getContext());
            this.mFailureImageView = imageView;
            imageView.setVisibility(View.GONE);
            this.mFailureImageView.setOnClickListener(this.mFailureImageClickListener);
            ImageView.ScaleType scaleType = this.mFailureImageScaleType;
            if (scaleType != null) {
                this.mFailureImageView.setScaleType(scaleType);
            }
            addView(this.mFailureImageView);
        }
        this.mFailureImageView.setImageDrawable(drawable);
    }

    public void setInitScaleType(int i) {
        SubsamplingScaleImageView subsamplingScaleImageView = this.mSSIV;
        if (subsamplingScaleImageView == null) {
            return;
        }
        this.mInitScaleType = i;
        if (i == 1) {
            subsamplingScaleImageView.setMinimumScaleType(2);
        } else if (i == 7) {
            subsamplingScaleImageView.setMinimumScaleType(3);
        } else if (i == 8) {
            subsamplingScaleImageView.setMinimumScaleType(4);
        } else {
            subsamplingScaleImageView.setMinimumScaleType(1);
        }
        DisplayOptimizeListener displayOptimizeListener = this.mDisplayOptimizeListener;
        if (displayOptimizeListener != null) {
            displayOptimizeListener.setInitScaleType(i);
        }
    }

    public void setOptimizeDisplay(boolean z) {
        SubsamplingScaleImageView subsamplingScaleImageView = this.mSSIV;
        if (subsamplingScaleImageView == null) {
            return;
        }
        this.mOptimizeDisplay = z;
        if (z) {
            DisplayOptimizeListener displayOptimizeListener = new DisplayOptimizeListener(this.mSSIV);
            this.mDisplayOptimizeListener = displayOptimizeListener;
            this.mSSIV.setOnImageEventListener(displayOptimizeListener);
            return;
        }
        this.mDisplayOptimizeListener = null;
        subsamplingScaleImageView.setOnImageEventListener(null);
    }

    public void saveImageIntoGallery() {
        if (this.mCurrentImageFile == null) {
            ImageSaveCallback imageSaveCallback = this.mImageSaveCallback;
            if (imageSaveCallback != null) {
                imageSaveCallback.onFail(new IllegalStateException("image not downloaded yet"));
                return;
            }
            return;
        }
        try {
            String insertImage = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), this.mCurrentImageFile.getAbsolutePath(), this.mCurrentImageFile.getName(), "");
            if (this.mImageSaveCallback != null) {
                if (!TextUtils.isEmpty(insertImage)) {
                    this.mImageSaveCallback.onSuccess(insertImage);
                } else {
                    this.mImageSaveCallback.onFail(new RuntimeException("saveImageIntoGallery fail"));
                }
            }
        } catch (FileNotFoundException e) {
            ImageSaveCallback imageSaveCallback2 = this.mImageSaveCallback;
            if (imageSaveCallback2 != null) {
                imageSaveCallback2.onFail(e);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mImageLoader.cancel(hashCode());
        int size = this.mTempImages.size();
        for (int i = 0; i < size; i++) {
            this.mTempImages.get(i).delete();
        }
        this.mTempImages.clear();
    }

    public void showImage(Uri uri) {
        showImage(Uri.EMPTY, uri);
    }

    public void showImage(Uri uri, Uri uri2) {
        this.mThumbnail = uri;
        this.mUri = uri2;
        clearThumbnailAndProgressIndicator();
        this.mImageLoader.loadImage(hashCode(), uri2, this.mInternalCallback);
        ImageView imageView = this.mFailureImageView;
        if (imageView != null) {
            imageView.setVisibility(View.GONE);
        }
    }

    public void cancel() {
        this.mImageLoader.cancel(hashCode());
    }

    @Override
    public void onCacheHit(int i, File file) {
        this.mCurrentImageFile = file;
        doShowImage(i, file);
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onCacheHit(i, file);
        }
    }

    @Override
    public void onCacheMiss(int i, File file) {
        this.mCurrentImageFile = file;
        this.mTempImages.add(file);
        doShowImage(i, file);
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onCacheMiss(i, file);
        }
    }

    @Override
    public void onStart() {
        if (this.mThumbnail != Uri.EMPTY) {
            View createThumbnailView = this.mViewFactory.createThumbnailView(getContext(), this.mThumbnail, this.mThumbnailScaleType);
            this.mThumbnailView = createThumbnailView;
            if (createThumbnailView != null) {
                addView(createThumbnailView, -1, -1);
            }
        }
        ProgressIndicator progressIndicator = this.mProgressIndicator;
        if (progressIndicator != null) {
            this.mProgressIndicatorView = progressIndicator.getView(this);
            this.mProgressIndicator.onStart();
            View view = this.mProgressIndicatorView;
            if (view != null) {
                addView(view);
            }
        }
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onStart();
        }
    }

    @Override
    public void onProgress(int i) {
        ProgressIndicator progressIndicator = this.mProgressIndicator;
        if (progressIndicator != null) {
            progressIndicator.onProgress(i);
        }
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onProgress(i);
        }
    }

    @Override
    public void onFinish() {
        doOnFinish();
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onFinish();
        }
    }

    @Override
    public void onSuccess(File file) {
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onSuccess(file);
        }
    }

    @Override
    public void onFail(Exception exc) {
        showFailImage();
        ImageLoader.Callback callback = this.mUserCallback;
        if (callback != null) {
            callback.onFail(exc);
        }
    }

    private void doOnFinish() {
        if (this.mOptimizeDisplay) {
            AnimationSet animationSet = new AnimationSet(true);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(500L);
            alphaAnimation.setFillAfter(true);
            animationSet.addAnimation(alphaAnimation);
            View view = this.mThumbnailView;
            if (view != null) {
                view.setAnimation(animationSet);
            }
            View view2 = this.mProgressIndicatorView;
            if (view2 != null) {
                view2.setAnimation(animationSet);
            }
            ProgressIndicator progressIndicator = this.mProgressIndicator;
            if (progressIndicator != null) {
                progressIndicator.onFinish();
            }
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (mThumbnailView != null) {
                        mThumbnailView.setVisibility(View.GONE);
                    }
                    if (mProgressIndicatorView != null) {
                        mProgressIndicatorView.setVisibility(View.GONE);
                    }
                    if (mThumbnailView == null && mProgressIndicatorView == null) {
                        return;
                    }
                    post(new Runnable() {
                        @Override
                        public void run() {
                            clearThumbnailAndProgressIndicator();
                        }
                    });
                }
            });
            return;
        }
        ProgressIndicator progressIndicator2 = this.mProgressIndicator;
        if (progressIndicator2 != null) {
            progressIndicator2.onFinish();
        }
        clearThumbnailAndProgressIndicator();
    }

    private void doShowImage(int i, File file) {
        View view = this.mMainView;
        if (view != null) {
            removeView(view);
        }
        View createMainView = this.mViewFactory.createMainView(getContext(), i, file, this.mInitScaleType);
        this.mMainView = createMainView;
        if (createMainView == null) {
            onFail(new RuntimeException("Image type not supported: " + ImageInfoExtractor.typeName(i)));
            return;
        }
        addView(createMainView, -1, -1);
        this.mMainView.setOnClickListener(this.mOnClickListener);
        this.mMainView.setOnLongClickListener(this.mOnLongClickListener);
        View view2 = this.mMainView;
        if (view2 instanceof SubsamplingScaleImageView) {
            SubsamplingScaleImageView subsamplingScaleImageView = (SubsamplingScaleImageView) view2;
            this.mSSIV = subsamplingScaleImageView;
            subsamplingScaleImageView.setMinimumTileDpi(160);
            setOptimizeDisplay(this.mOptimizeDisplay);
            setInitScaleType(this.mInitScaleType);
            this.mSSIV.setImage(ImageSource.uri(Uri.fromFile(file)));
        }
        ImageView imageView = this.mFailureImageView;
        if (imageView != null) {
            imageView.setVisibility(View.GONE);
        }
    }

    private void showFailImage() {
        if (this.mFailureImageView == null) {
            return;
        }
        View view = this.mMainView;
        if (view != null) {
            removeView(view);
        }
        this.mFailureImageView.setVisibility(View.VISIBLE);
        clearThumbnailAndProgressIndicator();
    }

    public void clearThumbnailAndProgressIndicator() {
        View view = this.mThumbnailView;
        if (view != null) {
            removeView(view);
            this.mThumbnailView = null;
        }
        View view2 = this.mProgressIndicatorView;
        if (view2 != null) {
            removeView(view2);
            this.mProgressIndicatorView = null;
        }
    }
}
