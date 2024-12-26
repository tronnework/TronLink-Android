package com.tron.wallet.business.tabdapp.browser.grid.impl;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemAnimationInfo;
import java.util.ArrayList;
import java.util.List;
public abstract class BaseItemAnimationManager<T extends ItemAnimationInfo> {
    private static TimeInterpolator sDefaultInterpolator;
    protected final BaseItemAnimator mItemAnimator;
    protected final List<T> mPending = new ArrayList();
    protected final List<RecyclerView.ViewHolder> mActive = new ArrayList();
    protected final List<List<T>> mDeferredReadySets = new ArrayList();

    public abstract void dispatchFinished(T t, RecyclerView.ViewHolder viewHolder);

    public abstract void dispatchStarting(T t, RecyclerView.ViewHolder viewHolder);

    protected abstract boolean endNotStartedAnimation(T t, RecyclerView.ViewHolder viewHolder);

    public abstract long getDuration();

    protected abstract void onAnimationCancel(T t, RecyclerView.ViewHolder viewHolder);

    public abstract void onAnimationEndedBeforeStarted(T t, RecyclerView.ViewHolder viewHolder);

    protected abstract void onAnimationEndedSuccessfully(T t, RecyclerView.ViewHolder viewHolder);

    protected abstract void onCreateAnimation(T t);

    public abstract void setDuration(long j);

    public BaseItemAnimationManager(BaseItemAnimator baseItemAnimator) {
        this.mItemAnimator = baseItemAnimator;
    }

    public final boolean debugLogEnabled() {
        return this.mItemAnimator.debugLogEnabled();
    }

    public boolean hasPending() {
        return !this.mPending.isEmpty();
    }

    public boolean isRunning() {
        return (this.mPending.isEmpty() && this.mActive.isEmpty() && this.mDeferredReadySets.isEmpty()) ? false : true;
    }

    public boolean removeFromActive(RecyclerView.ViewHolder viewHolder) {
        return this.mActive.remove(viewHolder);
    }

    public void cancelAllStartedAnimations() {
        List<RecyclerView.ViewHolder> list = this.mActive;
        for (int size = list.size() - 1; size >= 0; size--) {
            ViewCompat.animate(list.get(size).itemView).cancel();
        }
    }

    public void runPendingAnimations(boolean z, long j) {
        final ArrayList<ItemAnimationInfo> arrayList = new ArrayList(this.mPending);
        this.mPending.clear();
        if (z) {
            this.mDeferredReadySets.add(arrayList);
            ViewCompat.postOnAnimationDelayed(((ItemAnimationInfo) arrayList.get(0)).getAvailableViewHolder().itemView, new Runnable() {
                @Override
                public void run() {
                    for (ItemAnimationInfo itemAnimationInfo : arrayList) {
                        createAnimation(itemAnimationInfo);
                    }
                    arrayList.clear();
                    mDeferredReadySets.remove(arrayList);
                }
            }, j);
            return;
        }
        for (ItemAnimationInfo itemAnimationInfo : arrayList) {
            createAnimation(itemAnimationInfo);
        }
        arrayList.clear();
    }

    public void endPendingAnimations(RecyclerView.ViewHolder viewHolder) {
        List<T> list = this.mPending;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (endNotStartedAnimation(list.get(size), viewHolder) && viewHolder != null) {
                list.remove(size);
            }
        }
        if (viewHolder == null) {
            list.clear();
        }
    }

    public void endAllPendingAnimations() {
        endPendingAnimations(null);
    }

    public void endDeferredReadyAnimations(RecyclerView.ViewHolder viewHolder) {
        for (int size = this.mDeferredReadySets.size() - 1; size >= 0; size--) {
            List<T> list = this.mDeferredReadySets.get(size);
            for (int size2 = list.size() - 1; size2 >= 0; size2--) {
                if (endNotStartedAnimation(list.get(size2), viewHolder) && viewHolder != null) {
                    list.remove(size2);
                }
            }
            if (viewHolder == null) {
                list.clear();
            }
            if (list.isEmpty()) {
                this.mDeferredReadySets.remove(list);
            }
        }
    }

    public void endAllDeferredReadyAnimations() {
        endDeferredReadyAnimations(null);
    }

    void createAnimation(T t) {
        onCreateAnimation(t);
    }

    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        this.mItemAnimator.endAnimation(viewHolder);
    }

    public void resetAnimation(RecyclerView.ViewHolder viewHolder) {
        if (sDefaultInterpolator == null) {
            sDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        viewHolder.itemView.animate().setInterpolator(sDefaultInterpolator);
        endAnimation(viewHolder);
    }

    protected void dispatchFinishedWhenDone() {
        this.mItemAnimator.dispatchFinishedWhenDone();
    }

    public void enqueuePendingAnimationInfo(T t) {
        this.mPending.add(t);
    }

    public void startActiveItemAnimation(T t, RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        viewPropertyAnimatorCompat.setListener(new BaseAnimatorListener(this, t, viewHolder, viewPropertyAnimatorCompat));
        addActiveAnimationTarget(viewHolder);
        viewPropertyAnimatorCompat.start();
    }

    private void addActiveAnimationTarget(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            throw new IllegalStateException("item is null");
        }
        this.mActive.add(viewHolder);
    }

    protected static class BaseAnimatorListener implements ViewPropertyAnimatorListener {
        private ItemAnimationInfo mAnimationInfo;
        private ViewPropertyAnimatorCompat mAnimator;
        private RecyclerView.ViewHolder mHolder;
        private BaseItemAnimationManager mManager;

        public BaseAnimatorListener(BaseItemAnimationManager baseItemAnimationManager, ItemAnimationInfo itemAnimationInfo, RecyclerView.ViewHolder viewHolder, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.mManager = baseItemAnimationManager;
            this.mAnimationInfo = itemAnimationInfo;
            this.mHolder = viewHolder;
            this.mAnimator = viewPropertyAnimatorCompat;
        }

        @Override
        public void onAnimationStart(View view) {
            this.mManager.dispatchStarting(this.mAnimationInfo, this.mHolder);
        }

        @Override
        public void onAnimationEnd(View view) {
            BaseItemAnimationManager baseItemAnimationManager = this.mManager;
            ItemAnimationInfo itemAnimationInfo = this.mAnimationInfo;
            RecyclerView.ViewHolder viewHolder = this.mHolder;
            this.mAnimator.setListener(null);
            this.mManager = null;
            this.mAnimationInfo = null;
            this.mHolder = null;
            this.mAnimator = null;
            baseItemAnimationManager.onAnimationEndedSuccessfully(itemAnimationInfo, viewHolder);
            baseItemAnimationManager.dispatchFinished(itemAnimationInfo, viewHolder);
            itemAnimationInfo.clear(viewHolder);
            baseItemAnimationManager.mActive.remove(viewHolder);
            baseItemAnimationManager.dispatchFinishedWhenDone();
        }

        @Override
        public void onAnimationCancel(View view) {
            this.mManager.onAnimationCancel(this.mAnimationInfo, this.mHolder);
        }
    }
}
