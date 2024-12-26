package com.tron.wallet.common.components;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.animation.backend.AnimationBackend;
import com.facebook.fresco.animation.backend.AnimationBackendDelegate;
import com.facebook.fresco.animation.drawable.AnimatedDrawable2;
import com.facebook.fresco.animation.drawable.AnimationListener;
import com.facebook.imagepipeline.image.ImageInfo;
import java.util.function.Consumer;
public class SimpleDraweeViewGif extends SimpleDraweeView {
    private DraweeController controller;

    public SimpleDraweeViewGif(Context context) {
        super(context);
    }

    public SimpleDraweeViewGif(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SimpleDraweeViewGif(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setGif(int i, final int i2) {
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder().setAutoPlayAnimations(true).setUri(getUriFromDrawableRes(i)).setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
                if (animatable instanceof AnimatedDrawable2) {
                    AnimatedDrawable2 animatedDrawable2 = (AnimatedDrawable2) animatable;
                    animatedDrawable2.setAnimationBackend(new LoopCountModifyingBackend(animatedDrawable2.getAnimationBackend(), i2));
                }
            }
        }).build();
        this.controller = build;
        setController(build);
    }

    public void setGif(int i, int i2, long j, Consumer<SimpleDraweeViewGif> consumer) {
        AbstractDraweeController build = Fresco.newDraweeControllerBuilder().setUri(getUriFromDrawableRes(i)).setControllerListener(new fun2(i2, consumer, j)).build();
        this.controller = build;
        setController(build);
    }

    public class fun2 extends BaseControllerListener<ImageInfo> {
        final long val$delayMills;
        final int val$loopCount;
        final Consumer val$onStop;

        fun2(int i, Consumer consumer, long j) {
            this.val$loopCount = i;
            this.val$onStop = consumer;
            this.val$delayMills = j;
        }

        @Override
        public void onFinalImageSet(String str, ImageInfo imageInfo, Animatable animatable) {
            if (animatable instanceof AnimatedDrawable2) {
                final AnimatedDrawable2 animatedDrawable2 = (AnimatedDrawable2) animatable;
                animatedDrawable2.setAnimationBackend(new LoopCountModifyingBackend(animatedDrawable2.getAnimationBackend(), this.val$loopCount));
                animatedDrawable2.setAnimationListener(new AnimationListener() {
                    @Override
                    public void onAnimationFrame(Drawable drawable, int i) {
                    }

                    @Override
                    public void onAnimationLoaded() {
                    }

                    @Override
                    public void onAnimationRepeat(Drawable drawable) {
                    }

                    @Override
                    public void onAnimationReset(Drawable drawable) {
                    }

                    @Override
                    public void onAnimationStart(Drawable drawable) {
                    }

                    @Override
                    public void onAnimationStop(Drawable drawable) {
                        if (fun2.this.val$onStop != null) {
                            fun2.this.val$onStop.accept(SimpleDraweeViewGif.this);
                        }
                    }
                });
                postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        AnimatedDrawable2.this.start();
                    }
                }, this.val$delayMills);
            }
        }
    }

    private Uri getUriFromDrawableRes(int i) {
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(i)).build();
    }

    public class LoopCountModifyingBackend extends AnimationBackendDelegate {
        private int mLoopCount;

        @Override
        public int getLoopCount() {
            return this.mLoopCount;
        }

        public LoopCountModifyingBackend(AnimationBackend animationBackend, int i) {
            super(animationBackend);
            this.mLoopCount = i <= 0 ? 0 : i;
        }
    }
}
