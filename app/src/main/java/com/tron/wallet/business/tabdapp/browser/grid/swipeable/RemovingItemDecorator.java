package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.CustomRecyclerViewUtils;
import java.lang.ref.WeakReference;
public class RemovingItemDecorator extends RecyclerView.ItemDecoration {
    private static final long ADDITIONAL_REMOVE_DURATION = 50;
    private static final int NOTIFY_REMOVAL_EFFECT_END = 1;
    private static final int NOTIFY_REMOVAL_EFFECT_PHASE_1 = 0;
    private static final String TAG = "RemovingItemDecorator";
    private final boolean mHorizontal;
    private final long mMoveAnimationDuration;
    private Interpolator mMoveAnimationInterpolator;
    private int mPendingNotificationMask;
    private RecyclerView mRecyclerView;
    private final long mRemoveAnimationDuration;
    private long mStartTime;
    private Drawable mSwipeBackgroundDrawable;
    private RecyclerView.ViewHolder mSwipingItem;
    private final Rect mSwipingItemBounds;
    private final long mSwipingItemId;
    private int mTranslationX;
    private int mTranslationY;

    private boolean requiresContinuousAnimationAfterSwipeCompletedSuccessfully(long j) {
        long j2 = this.mRemoveAnimationDuration;
        return j >= j2 && j < j2 + this.mMoveAnimationDuration;
    }

    public void setMoveAnimationInterpolator(Interpolator interpolator) {
        this.mMoveAnimationInterpolator = interpolator;
    }

    public RemovingItemDecorator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, long j, long j2) {
        Rect rect = new Rect();
        this.mSwipingItemBounds = rect;
        boolean z = false;
        this.mPendingNotificationMask = 0;
        this.mRecyclerView = recyclerView;
        this.mSwipingItem = viewHolder;
        this.mSwipingItemId = viewHolder.getItemId();
        this.mHorizontal = (i == 2 || i == 4) ? true : true;
        this.mRemoveAnimationDuration = j + ADDITIONAL_REMOVE_DURATION;
        this.mMoveAnimationDuration = j2;
        this.mTranslationX = (int) (viewHolder.itemView.getTranslationX() + 0.5f);
        this.mTranslationY = (int) (viewHolder.itemView.getTranslationY() + 0.5f);
        CustomRecyclerViewUtils.getViewBounds(this.mSwipingItem.itemView, rect);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        long elapsedTime = getElapsedTime(this.mStartTime);
        fillSwipingItemBackground(canvas, this.mSwipeBackgroundDrawable, determineBackgroundScaleSwipeCompletedSuccessfully(elapsedTime));
        if (this.mSwipingItemId == this.mSwipingItem.getItemId()) {
            this.mTranslationX = (int) (this.mSwipingItem.itemView.getTranslationX() + 0.5f);
            this.mTranslationY = (int) (this.mSwipingItem.itemView.getTranslationY() + 0.5f);
        }
        if (requiresContinuousAnimationAfterSwipeCompletedSuccessfully(elapsedTime)) {
            postInvalidateOnAnimation();
        }
    }

    private float determineBackgroundScaleSwipeCompletedSuccessfully(long j) {
        long j2 = this.mRemoveAnimationDuration;
        if (j < j2) {
            return 1.0f;
        }
        long j3 = this.mMoveAnimationDuration;
        if (j >= j2 + j3 || j3 == 0) {
            return 0.0f;
        }
        float f = 1.0f - (((float) (j - j2)) / ((float) j3));
        Interpolator interpolator = this.mMoveAnimationInterpolator;
        return interpolator != null ? interpolator.getInterpolation(f) : f;
    }

    private void fillSwipingItemBackground(Canvas canvas, Drawable drawable, float f) {
        Rect rect = this.mSwipingItemBounds;
        int i = this.mTranslationX;
        int i2 = this.mTranslationY;
        boolean z = this.mHorizontal;
        float f2 = z ? 1.0f : f;
        if (!z) {
            f = 1.0f;
        }
        int width = (int) ((f2 * rect.width()) + 0.5f);
        int height = (int) ((f * rect.height()) + 0.5f);
        if (height == 0 || width == 0 || drawable == null) {
            return;
        }
        int save = canvas.save();
        canvas.clipRect(rect.left + i, rect.top + i2, rect.left + i + width, rect.top + i2 + height);
        canvas.translate((rect.left + i) - ((rect.width() - width) / 2), (rect.top + i2) - ((rect.height() - height) / 2));
        drawable.setBounds(0, 0, rect.width(), rect.height());
        drawable.draw(canvas);
        canvas.restoreToCount(save);
    }

    private void postInvalidateOnAnimation() {
        ViewCompat.postInvalidateOnAnimation(this.mRecyclerView);
    }

    public void start() {
        ViewCompat.animate(SwipeableViewHolderUtils.getSwipeableContainerView(this.mSwipingItem)).cancel();
        this.mRecyclerView.addItemDecoration(this);
        this.mStartTime = System.currentTimeMillis();
        this.mTranslationY = (int) (this.mSwipingItem.itemView.getTranslationY() + 0.5f);
        this.mSwipeBackgroundDrawable = this.mSwipingItem.itemView.getBackground();
        postInvalidateOnAnimation();
        notifyDelayed(0, this.mRemoveAnimationDuration);
    }

    private void notifyDelayed(int i, long j) {
        int i2 = 1 << i;
        int i3 = this.mPendingNotificationMask;
        if ((i3 & i2) != 0) {
            return;
        }
        this.mPendingNotificationMask = i2 | i3;
        ViewCompat.postOnAnimationDelayed(this.mRecyclerView, new DelayedNotificationRunner(this, i), j);
    }

    void onDelayedNotification(int i) {
        long elapsedTime = getElapsedTime(this.mStartTime);
        this.mPendingNotificationMask = (~(1 << i)) & this.mPendingNotificationMask;
        if (i != 0) {
            if (i != 1) {
                return;
            }
            finish();
            return;
        }
        long j = this.mRemoveAnimationDuration;
        if (elapsedTime < j) {
            notifyDelayed(0, j - elapsedTime);
            return;
        }
        postInvalidateOnAnimation();
        notifyDelayed(1, this.mMoveAnimationDuration);
    }

    private void finish() {
        this.mRecyclerView.removeItemDecoration(this);
        postInvalidateOnAnimation();
        this.mRecyclerView = null;
        this.mSwipingItem = null;
        this.mTranslationY = 0;
        this.mMoveAnimationInterpolator = null;
    }

    protected static long getElapsedTime(long j) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= j) {
            return currentTimeMillis - j;
        }
        return Long.MAX_VALUE;
    }

    public static class DelayedNotificationRunner implements Runnable {
        private final int mCode;
        private WeakReference<RemovingItemDecorator> mRefDecorator;

        public DelayedNotificationRunner(RemovingItemDecorator removingItemDecorator, int i) {
            this.mRefDecorator = new WeakReference<>(removingItemDecorator);
            this.mCode = i;
        }

        @Override
        public void run() {
            RemovingItemDecorator removingItemDecorator = this.mRefDecorator.get();
            this.mRefDecorator.clear();
            this.mRefDecorator = null;
            if (removingItemDecorator != null) {
                removingItemDecorator.onDelayedNotification(this.mCode);
            }
        }
    }
}
