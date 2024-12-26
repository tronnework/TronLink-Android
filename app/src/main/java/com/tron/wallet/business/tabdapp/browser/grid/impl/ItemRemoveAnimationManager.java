package com.tron.wallet.business.tabdapp.browser.grid.impl;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
public abstract class ItemRemoveAnimationManager extends BaseItemAnimationManager<RemoveAnimationInfo> {
    private static final String TAG = "ARVItemRemoveAnimMgr";

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder);

    public ItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    @Override
    public long getDuration() {
        return this.mItemAnimator.getRemoveDuration();
    }

    @Override
    public void setDuration(long j) {
        this.mItemAnimator.setRemoveDuration(j);
    }

    @Override
    public void dispatchStarting(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchRemoveStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchRemoveStarting(viewHolder);
    }

    @Override
    public void dispatchFinished(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchRemoveFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchRemoveFinished(viewHolder);
    }

    @Override
    public boolean endNotStartedAnimation(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (removeAnimationInfo.holder != null) {
            if (viewHolder == null || removeAnimationInfo.holder == viewHolder) {
                onAnimationEndedBeforeStarted(removeAnimationInfo, removeAnimationInfo.holder);
                dispatchFinished(removeAnimationInfo, removeAnimationInfo.holder);
                removeAnimationInfo.clear(removeAnimationInfo.holder);
                return true;
            }
            return false;
        }
        return false;
    }
}
