package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.InternalHelperKK;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.SwipeableItemWrapperAdapter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
public class ItemSlidingAnimator {
    public static final int DIR_DOWN = 3;
    public static final int DIR_LEFT = 0;
    public static final int DIR_RIGHT = 2;
    public static final int DIR_UP = 1;
    private static final String TAG = "ItemSlidingAnimator";
    private final SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> mAdapter;
    private int mImmediatelySetTranslationThreshold;
    private final Interpolator mSlideToDefaultPositionAnimationInterpolator = new AccelerateDecelerateInterpolator();
    private final Interpolator mSlideToSpecifiedPositionAnimationInterpolator = new DecelerateInterpolator();
    private final Interpolator mSlideToOutsideOfWindowAnimationInterpolator = new AccelerateInterpolator(0.8f);
    private final int[] mTmpLocation = new int[2];
    private final Rect mTmpRect = new Rect();
    private final List<RecyclerView.ViewHolder> mActive = new ArrayList();
    private final List<WeakReference<ViewHolderDeferredProcess>> mDeferredProcesses = new ArrayList();

    public int getImmediatelySetTranslationThreshold() {
        return this.mImmediatelySetTranslationThreshold;
    }

    public void setImmediatelySetTranslationThreshold(int i) {
        this.mImmediatelySetTranslationThreshold = i;
    }

    public ItemSlidingAnimator(SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> swipeableItemWrapperAdapter) {
        this.mAdapter = swipeableItemWrapperAdapter;
    }

    public void slideToDefaultPosition(RecyclerView.ViewHolder viewHolder, boolean z, boolean z2, long j) {
        cancelDeferredProcess(viewHolder);
        slideToSpecifiedPositionInternal(viewHolder, 0.0f, false, z, z2, this.mSlideToDefaultPositionAnimationInterpolator, j, null);
    }

    public void slideToOutsideOfWindow(RecyclerView.ViewHolder viewHolder, int i, boolean z, long j) {
        cancelDeferredProcess(viewHolder);
        slideToOutsideOfWindowInternal(viewHolder, i, z, j, null);
    }

    public void slideToSpecifiedPosition(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2, boolean z3, long j) {
        cancelDeferredProcess(viewHolder);
        slideToSpecifiedPositionInternal(viewHolder, f, z, z2, z3, this.mSlideToSpecifiedPositionAnimationInterpolator, j, null);
    }

    public boolean finishSwipeSlideToDefaultPosition(RecyclerView.ViewHolder viewHolder, boolean z, boolean z2, long j, int i, SwipeResultAction swipeResultAction) {
        cancelDeferredProcess(viewHolder);
        return slideToSpecifiedPositionInternal(viewHolder, 0.0f, false, z, z2, this.mSlideToDefaultPositionAnimationInterpolator, j, new SwipeFinishInfo(i, swipeResultAction));
    }

    public boolean finishSwipeSlideToOutsideOfWindow(RecyclerView.ViewHolder viewHolder, int i, boolean z, long j, int i2, SwipeResultAction swipeResultAction) {
        cancelDeferredProcess(viewHolder);
        return slideToOutsideOfWindowInternal(viewHolder, i, z, j, new SwipeFinishInfo(i2, swipeResultAction));
    }

    private void cancelDeferredProcess(RecyclerView.ViewHolder viewHolder) {
        for (int size = this.mDeferredProcesses.size() - 1; size >= 0; size--) {
            ViewHolderDeferredProcess viewHolderDeferredProcess = this.mDeferredProcesses.get(size).get();
            if (viewHolderDeferredProcess != null && viewHolderDeferredProcess.hasTargetViewHolder(viewHolder)) {
                viewHolder.itemView.removeCallbacks(viewHolderDeferredProcess);
                this.mDeferredProcesses.remove(size);
            } else if (viewHolderDeferredProcess == null || viewHolderDeferredProcess.lostReference(viewHolder)) {
                this.mDeferredProcesses.remove(size);
            }
        }
    }

    private void scheduleViewHolderDeferredSlideProcess(RecyclerView.ViewHolder viewHolder, ViewHolderDeferredProcess viewHolderDeferredProcess) {
        this.mDeferredProcesses.add(new WeakReference<>(viewHolderDeferredProcess));
        viewHolder.itemView.post(viewHolderDeferredProcess);
    }

    private boolean slideToSpecifiedPositionInternal(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2, boolean z3, Interpolator interpolator, long j, SwipeFinishInfo swipeFinishInfo) {
        float f2 = f;
        View swipeableContainerView = com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder);
        long j2 = (!z3 ? z3 : ViewCompat.isAttachedToWindow(swipeableContainerView) && swipeableContainerView.getVisibility() == 0) ? 0L : j;
        if (f2 != 0.0f) {
            int width = swipeableContainerView.getWidth();
            int height = swipeableContainerView.getHeight();
            if (z2 && (!z || width != 0)) {
                if (z) {
                    f2 *= width;
                }
                return animateSlideInternalCompat(viewHolder, true, (int) (f2 + 0.5f), 0, j2, interpolator, swipeFinishInfo);
            } else if (!z2 && (!z || height != 0)) {
                if (z) {
                    f2 *= height;
                }
                return animateSlideInternalCompat(viewHolder, false, 0, (int) (f2 + 0.5f), j2, interpolator, swipeFinishInfo);
            } else if (swipeFinishInfo != null) {
                throw new IllegalStateException("Unexpected state in slideToSpecifiedPositionInternal (swipeFinish == null)");
            } else {
                scheduleViewHolderDeferredSlideProcess(viewHolder, new DeferredSlideProcess(viewHolder, f, z2));
                return false;
            }
        }
        return animateSlideInternalCompat(viewHolder, z2, 0, 0, j2, interpolator, swipeFinishInfo);
    }

    private boolean slideToOutsideOfWindowInternal(RecyclerView.ViewHolder viewHolder, int i, boolean z, long j, SwipeFinishInfo swipeFinishInfo) {
        boolean z2;
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View swipeableContainerView = com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder);
            ViewGroup viewGroup = (ViewGroup) swipeableContainerView.getParent();
            if (viewGroup == null) {
                return false;
            }
            int left = swipeableContainerView.getLeft();
            int right = swipeableContainerView.getRight();
            int top = swipeableContainerView.getTop();
            int i2 = right - left;
            int bottom = swipeableContainerView.getBottom() - top;
            viewGroup.getWindowVisibleDisplayFrame(this.mTmpRect);
            int width = this.mTmpRect.width();
            int height = this.mTmpRect.height();
            if (i2 == 0 || bottom == 0) {
                if (i != 0) {
                    if (i == 1) {
                        height = -height;
                    } else if (i != 2) {
                        if (i != 3) {
                            width = 0;
                        }
                    }
                    width = 0;
                    z2 = false;
                } else {
                    width = -width;
                }
                height = 0;
                z2 = false;
            } else {
                viewGroup.getLocationInWindow(this.mTmpLocation);
                int[] iArr = this.mTmpLocation;
                int i3 = iArr[0];
                int i4 = iArr[1];
                if (i == 0) {
                    width = -(i3 + i2);
                    height = 0;
                } else if (i != 1) {
                    if (i == 2) {
                        width -= i3 - left;
                        z2 = z;
                    } else if (i != 3) {
                        z2 = z;
                        width = 0;
                    } else {
                        height -= i4 - top;
                        z2 = z;
                        width = 0;
                    }
                    height = 0;
                } else {
                    height = -(i4 + bottom);
                    width = 0;
                }
                z2 = z;
            }
            return animateSlideInternalCompat(viewHolder, i == 0 || i == 2, width, height, (!z2 ? z2 : ViewCompat.isAttachedToWindow(swipeableContainerView) && swipeableContainerView.getVisibility() == 0) ? 0L : j, this.mSlideToOutsideOfWindowAnimationInterpolator, swipeFinishInfo);
        }
        return false;
    }

    private boolean animateSlideInternalCompat(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2, long j, Interpolator interpolator, SwipeFinishInfo swipeFinishInfo) {
        return animateSlideInternal(viewHolder, z, i, i2, j, interpolator, swipeFinishInfo);
    }

    static void slideInternalCompat(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2) {
        slideInternal(viewHolder, z, i, i2);
    }

    private static void slideInternal(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View swipeableContainerView = com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder);
            ViewCompat.animate(swipeableContainerView).cancel();
            swipeableContainerView.setTranslationX(i);
            swipeableContainerView.setTranslationY(i2);
        }
    }

    private boolean animateSlideInternal(RecyclerView.ViewHolder viewHolder, boolean z, int i, int i2, long j, Interpolator interpolator, SwipeFinishInfo swipeFinishInfo) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            View swipeableContainerView = com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder);
            int translationX = (int) (swipeableContainerView.getTranslationX() + 0.5f);
            int translationY = (int) (swipeableContainerView.getTranslationY() + 0.5f);
            endAnimation(viewHolder);
            int translationX2 = (int) (swipeableContainerView.getTranslationX() + 0.5f);
            int translationY2 = (int) (swipeableContainerView.getTranslationY() + 0.5f);
            if (j == 0 || ((translationX2 == i && translationY2 == i2) || Math.max(Math.abs(i - translationX), Math.abs(i2 - translationY)) <= this.mImmediatelySetTranslationThreshold)) {
                swipeableContainerView.setTranslationX(i);
                swipeableContainerView.setTranslationY(i2);
                return false;
            }
            swipeableContainerView.setTranslationX(translationX);
            swipeableContainerView.setTranslationY(translationY);
            new SlidingAnimatorListenerObject(this.mAdapter, this.mActive, viewHolder, i, i2, j, z, interpolator, swipeFinishInfo).start();
            return true;
        }
        return false;
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof SwipeableItemViewHolder) {
            cancelDeferredProcess(viewHolder);
            ViewCompat.animate(com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder)).cancel();
            if (this.mActive.remove(viewHolder)) {
                throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [slide]");
            }
        }
    }

    public void endAnimations() {
        for (int size = this.mActive.size() - 1; size >= 0; size--) {
            endAnimation(this.mActive.get(size));
        }
    }

    public boolean isRunning(RecyclerView.ViewHolder viewHolder) {
        return this.mActive.contains(viewHolder);
    }

    public boolean isRunning() {
        return !this.mActive.isEmpty();
    }

    public int getSwipeContainerViewTranslationX(RecyclerView.ViewHolder viewHolder) {
        return (int) (com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder).getTranslationX() + 0.5f);
    }

    public int getSwipeContainerViewTranslationY(RecyclerView.ViewHolder viewHolder) {
        return (int) (com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder).getTranslationY() + 0.5f);
    }

    public static abstract class ViewHolderDeferredProcess implements Runnable {
        final WeakReference<RecyclerView.ViewHolder> mRefHolder;

        protected abstract void onProcess(RecyclerView.ViewHolder viewHolder);

        public ViewHolderDeferredProcess(RecyclerView.ViewHolder viewHolder) {
            this.mRefHolder = new WeakReference<>(viewHolder);
        }

        @Override
        public void run() {
            RecyclerView.ViewHolder viewHolder = this.mRefHolder.get();
            if (viewHolder != null) {
                onProcess(viewHolder);
            }
        }

        public boolean lostReference(RecyclerView.ViewHolder viewHolder) {
            return this.mRefHolder.get() == null;
        }

        public boolean hasTargetViewHolder(RecyclerView.ViewHolder viewHolder) {
            return this.mRefHolder.get() == viewHolder;
        }
    }

    public static final class DeferredSlideProcess extends ViewHolderDeferredProcess {
        final boolean mHorizontal;
        final float mPosition;

        public DeferredSlideProcess(RecyclerView.ViewHolder viewHolder, float f, boolean z) {
            super(viewHolder);
            this.mPosition = f;
            this.mHorizontal = z;
        }

        @Override
        protected void onProcess(RecyclerView.ViewHolder viewHolder) {
            View swipeableContainerView = com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder);
            if (this.mHorizontal) {
                ItemSlidingAnimator.slideInternalCompat(viewHolder, true, (int) ((swipeableContainerView.getWidth() * this.mPosition) + 0.5f), 0);
            } else {
                ItemSlidingAnimator.slideInternalCompat(viewHolder, false, 0, (int) ((swipeableContainerView.getHeight() * this.mPosition) + 0.5f));
            }
        }
    }

    public static class SlidingAnimatorListenerObject implements ViewPropertyAnimatorListener, ViewPropertyAnimatorUpdateListener {
        private List<RecyclerView.ViewHolder> mActive;
        private SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> mAdapter;
        private ViewPropertyAnimatorCompat mAnimator;
        private final long mDuration;
        private final boolean mHorizontal;
        private final Interpolator mInterpolator;
        private float mInvSize;
        private final SwipeFinishInfo mSwipeFinish;
        private final int mToX;
        private final int mToY;
        private RecyclerView.ViewHolder mViewHolder;

        @Override
        public void onAnimationCancel(View view) {
        }

        @Override
        public void onAnimationStart(View view) {
        }

        SlidingAnimatorListenerObject(SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> swipeableItemWrapperAdapter, List<RecyclerView.ViewHolder> list, RecyclerView.ViewHolder viewHolder, int i, int i2, long j, boolean z, Interpolator interpolator, SwipeFinishInfo swipeFinishInfo) {
            this.mAdapter = swipeableItemWrapperAdapter;
            this.mActive = list;
            this.mViewHolder = viewHolder;
            this.mToX = i;
            this.mToY = i2;
            this.mHorizontal = z;
            this.mSwipeFinish = swipeFinishInfo;
            this.mDuration = j;
            this.mInterpolator = interpolator;
        }

        void start() {
            View swipeableContainerView = com.tron.wallet.business.tabdapp.browser.grid.base.SwipeableViewHolderUtils.getSwipeableContainerView(this.mViewHolder);
            this.mInvSize = 1.0f / Math.max(1.0f, this.mHorizontal ? swipeableContainerView.getWidth() : swipeableContainerView.getHeight());
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(swipeableContainerView);
            this.mAnimator = animate;
            animate.setDuration(this.mDuration);
            this.mAnimator.translationX(this.mToX);
            this.mAnimator.translationY(this.mToY);
            Interpolator interpolator = this.mInterpolator;
            if (interpolator != null) {
                this.mAnimator.setInterpolator(interpolator);
            }
            this.mAnimator.setListener(this);
            this.mAnimator.setUpdateListener(this);
            this.mActive.add(this.mViewHolder);
            this.mAnimator.start();
        }

        @Override
        public void onAnimationUpdate(View view) {
            float translationX = this.mHorizontal ? view.getTranslationX() : view.getTranslationY();
            SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> swipeableItemWrapperAdapter = this.mAdapter;
            RecyclerView.ViewHolder viewHolder = this.mViewHolder;
            swipeableItemWrapperAdapter.onUpdateSlideAmount(viewHolder, viewHolder.getLayoutPosition(), translationX * this.mInvSize, true, this.mHorizontal, false);
        }

        @Override
        public void onAnimationEnd(View view) {
            this.mAnimator.setListener(null);
            InternalHelperKK.clearViewPropertyAnimatorUpdateListener(view);
            view.setTranslationX(this.mToX);
            view.setTranslationY(this.mToY);
            this.mActive.remove(this.mViewHolder);
            ViewParent parent = this.mViewHolder.itemView.getParent();
            if (parent != null) {
                ViewCompat.postInvalidateOnAnimation((View) parent);
            }
            SwipeFinishInfo swipeFinishInfo = this.mSwipeFinish;
            if (swipeFinishInfo != null) {
                swipeFinishInfo.resultAction.slideAnimationEnd();
            }
            this.mActive = null;
            this.mAnimator = null;
            this.mViewHolder = null;
            this.mAdapter = null;
        }
    }

    public static class SwipeFinishInfo {
        final int itemPosition;
        SwipeResultAction resultAction;

        public void clear() {
            this.resultAction = null;
        }

        public SwipeFinishInfo(int i, SwipeResultAction swipeResultAction) {
            this.itemPosition = i;
            this.resultAction = swipeResultAction;
        }
    }
}
