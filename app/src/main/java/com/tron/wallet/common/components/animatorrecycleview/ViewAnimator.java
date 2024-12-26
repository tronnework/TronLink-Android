package com.tron.wallet.common.components.animatorrecycleview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class ViewAnimator {
    private static final int DEFAULT_ANIMATION_DELAY_MILLIS = 100;
    private static final int DEFAULT_ANIMATION_DURATION_MILLIS = 300;
    private static final int INITIAL_DELAY_MILLIS = 150;
    private static final String SAVEDINSTANCESTATE_FIRSTANIMATEDPOSITION = "savedinstancestate_firstanimatedposition";
    private static final String SAVEDINSTANCESTATE_LASTANIMATEDPOSITION = "savedinstancestate_lastanimatedposition";
    private static final String SAVEDINSTANCESTATE_SHOULDANIMATE = "savedinstancestate_shouldanimate";
    private final RecyclerView mRecyclerView;
    private final SparseArray<Animator> mAnimators = new SparseArray<>();
    private int mInitialDelayMillis = INITIAL_DELAY_MILLIS;
    private int mAnimationDelayMillis = 100;
    private int mAnimationDurationMillis = 300;
    private boolean mShouldAnimate = true;
    private long mAnimationStartMillis = -1;
    private int mFirstAnimatedPosition = -1;
    private int mLastAnimatedPosition = -1;

    public void disableAnimations() {
        this.mShouldAnimate = false;
    }

    public void enableAnimations() {
        this.mShouldAnimate = true;
    }

    public void setAnimationDelayMillis(int i) {
        this.mAnimationDelayMillis = i;
    }

    public void setAnimationDurationMillis(int i) {
        this.mAnimationDurationMillis = i;
    }

    public void setInitialDelayMillis(int i) {
        this.mInitialDelayMillis = i;
    }

    void setLastAnimatedPosition(int i) {
        this.mLastAnimatedPosition = i;
    }

    public ViewAnimator(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public void reset() {
        for (int i = 0; i < this.mAnimators.size(); i++) {
            SparseArray<Animator> sparseArray = this.mAnimators;
            sparseArray.get(sparseArray.keyAt(i)).cancel();
        }
        this.mAnimators.clear();
        this.mFirstAnimatedPosition = -1;
        this.mLastAnimatedPosition = -1;
        this.mAnimationStartMillis = -1L;
        this.mShouldAnimate = true;
    }

    public void setShouldAnimateFromPosition(int i) {
        enableAnimations();
        int i2 = i - 1;
        this.mFirstAnimatedPosition = i2;
        this.mLastAnimatedPosition = i2;
    }

    public void setShouldAnimateNotVisible() {
        enableAnimations();
        this.mFirstAnimatedPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
        this.mLastAnimatedPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

    public void cancelExistingAnimation(View view) {
        int hashCode = view.hashCode();
        Animator animator = this.mAnimators.get(hashCode);
        if (animator != null) {
            animator.end();
            this.mAnimators.remove(hashCode);
        }
    }

    public void animateViewIfNecessary(int i, View view, Animator[] animatorArr) {
        if (!this.mShouldAnimate || i <= this.mLastAnimatedPosition) {
            return;
        }
        if (this.mFirstAnimatedPosition == -1) {
            this.mFirstAnimatedPosition = i;
        }
        animateView(i, view, animatorArr);
        this.mLastAnimatedPosition = i;
    }

    private void animateView(int i, View view, Animator[] animatorArr) {
        if (this.mAnimationStartMillis == -1) {
            this.mAnimationStartMillis = SystemClock.uptimeMillis();
        }
        ViewCompat.setAlpha(view, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorArr);
        animatorSet.setStartDelay(calculateAnimationDelay(i));
        animatorSet.setDuration(this.mAnimationDurationMillis);
        animatorSet.start();
        this.mAnimators.put(view.hashCode(), animatorSet);
    }

    private int calculateAnimationDelay(int i) {
        int max;
        int findLastCompletelyVisibleItemPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
        int findFirstCompletelyVisibleItemPosition = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        int i2 = this.mLastAnimatedPosition;
        if (i2 > findLastCompletelyVisibleItemPosition) {
            findLastCompletelyVisibleItemPosition = i2;
        }
        int i3 = this.mFirstAnimatedPosition;
        if ((findLastCompletelyVisibleItemPosition - findFirstCompletelyVisibleItemPosition) + 1 < (i - 1) - i3) {
            max = this.mAnimationDelayMillis;
            if (this.mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
                max += this.mAnimationDelayMillis * (i % ((GridLayoutManager) this.mRecyclerView.getLayoutManager()).getSpanCount());
                Log.d("GAB", "Delay[" + i + "]=*" + findLastCompletelyVisibleItemPosition + "|" + findFirstCompletelyVisibleItemPosition + "|");
            }
        } else {
            max = Math.max(0, (int) ((-SystemClock.uptimeMillis()) + this.mAnimationStartMillis + this.mInitialDelayMillis + ((i - i3) * this.mAnimationDelayMillis)));
        }
        Log.d("GAB", "Delay[" + i + "]=" + max + "|" + findLastCompletelyVisibleItemPosition + "|" + findFirstCompletelyVisibleItemPosition + "|");
        return max;
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putInt(SAVEDINSTANCESTATE_FIRSTANIMATEDPOSITION, this.mFirstAnimatedPosition);
        bundle.putInt(SAVEDINSTANCESTATE_LASTANIMATEDPOSITION, this.mLastAnimatedPosition);
        bundle.putBoolean(SAVEDINSTANCESTATE_SHOULDANIMATE, this.mShouldAnimate);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mFirstAnimatedPosition = bundle.getInt(SAVEDINSTANCESTATE_FIRSTANIMATEDPOSITION);
            this.mLastAnimatedPosition = bundle.getInt(SAVEDINSTANCESTATE_LASTANIMATEDPOSITION);
            this.mShouldAnimate = bundle.getBoolean(SAVEDINSTANCESTATE_SHOULDANIMATE);
        }
    }
}
