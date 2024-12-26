package com.tron.wallet.business.tabdapp.browser.grid.draggable;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
abstract class BaseDraggableItemDecorator extends RecyclerView.ItemDecoration {
    private static final int RETURN_TO_DEFAULT_POS_ANIMATE_THRESHOLD_DP = 2;
    private static final int RETURN_TO_DEFAULT_POS_ANIMATE_THRESHOLD_MSEC = 20;
    protected RecyclerView.ViewHolder mDraggingItemViewHolder;
    protected final RecyclerView mRecyclerView;
    private final int mReturnToDefaultPositionAnimateThreshold;
    private int mReturnToDefaultPositionDuration = 200;
    private Interpolator mReturnToDefaultPositionInterpolator;

    public void setReturnToDefaultPositionAnimationDuration(int i) {
        this.mReturnToDefaultPositionDuration = i;
    }

    public void setReturnToDefaultPositionAnimationInterpolator(Interpolator interpolator) {
        this.mReturnToDefaultPositionInterpolator = interpolator;
    }

    public BaseDraggableItemDecorator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        this.mRecyclerView = recyclerView;
        this.mDraggingItemViewHolder = viewHolder;
        this.mReturnToDefaultPositionAnimateThreshold = (int) ((recyclerView.getResources().getDisplayMetrics().density * 2.0f) + 0.5f);
    }

    public void moveToDefaultPosition(View view, float f, float f2, float f3, float f4, boolean z) {
        final float translationZ = ViewCompat.getTranslationZ(view);
        int determineMoveToDefaultPositionAnimationDurationFactor = (int) (this.mReturnToDefaultPositionDuration * determineMoveToDefaultPositionAnimationDurationFactor(view, f, f2, f3, f4));
        if (z && determineMoveToDefaultPositionAnimationDurationFactor > 20) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
            view.setScaleX(f);
            view.setScaleY(f2);
            view.setRotation(f3);
            view.setAlpha(f4);
            ViewCompat.setTranslationZ(view, translationZ + 1.0f);
            animate.cancel();
            animate.setDuration(determineMoveToDefaultPositionAnimationDurationFactor);
            animate.setInterpolator(this.mReturnToDefaultPositionInterpolator);
            animate.translationX(0.0f);
            animate.translationY(0.0f);
            animate.translationZ(translationZ);
            animate.alpha(1.0f);
            animate.rotation(0.0f);
            animate.scaleX(1.0f);
            animate.scaleY(1.0f);
            animate.setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationCancel(View view2) {
                }

                @Override
                public void onAnimationStart(View view2) {
                }

                @Override
                public void onAnimationEnd(View view2) {
                    ViewCompat.animate(view2).setListener(null);
                    BaseDraggableItemDecorator.resetDraggingItemViewEffects(view2, translationZ);
                    if (view2.getParent() instanceof RecyclerView) {
                        ViewCompat.postInvalidateOnAnimation((RecyclerView) view2.getParent());
                    }
                }
            });
            animate.start();
            return;
        }
        resetDraggingItemViewEffects(view, translationZ);
    }

    protected float determineMoveToDefaultPositionAnimationDurationFactor(View view, float f, float f2, float f3, float f4) {
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        float abs = width > 0 ? Math.abs(translationX / width) : 0.0f;
        float abs2 = height > 0 ? Math.abs(translationY / height) : 0.0f;
        float abs3 = Math.abs(Math.max(f, f2) - 1.0f);
        return Math.min(Math.max(Math.max(Math.max(Math.max(Math.max(0.0f, abs), abs2), abs3), Math.abs(f3 * 0.033333335f)), Math.abs(f4 - 1.0f)), 1.0f);
    }

    protected static void resetDraggingItemViewEffects(View view, float f) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        ViewCompat.setTranslationZ(view, f);
        view.setAlpha(1.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }

    public static void setItemTranslation(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
        viewHolder.itemView.setTranslationX(f);
        viewHolder.itemView.setTranslationY(f2);
    }
}
