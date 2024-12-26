package com.tron.wallet.business.tabdapp.browser.grid.impl;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
public abstract class ItemChangeAnimationManager extends BaseItemAnimationManager<ChangeAnimationInfo> {
    private static final String TAG = "ARVItemChangeAnimMgr";

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4);

    protected abstract void onCreateChangeAnimationForNewItem(ChangeAnimationInfo changeAnimationInfo);

    protected abstract void onCreateChangeAnimationForOldItem(ChangeAnimationInfo changeAnimationInfo);

    public ItemChangeAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    @Override
    public void dispatchStarting(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchChangeStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchChangeStarting(viewHolder, viewHolder == changeAnimationInfo.oldHolder);
    }

    @Override
    public void dispatchFinished(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchChangeFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchChangeFinished(viewHolder, viewHolder == changeAnimationInfo.oldHolder);
    }

    @Override
    public long getDuration() {
        return this.mItemAnimator.getChangeDuration();
    }

    @Override
    public void setDuration(long j) {
        this.mItemAnimator.setChangeDuration(j);
    }

    @Override
    public void onCreateAnimation(ChangeAnimationInfo changeAnimationInfo) {
        if (changeAnimationInfo.oldHolder != null) {
            onCreateChangeAnimationForOldItem(changeAnimationInfo);
        }
        if (changeAnimationInfo.newHolder != null) {
            onCreateChangeAnimationForNewItem(changeAnimationInfo);
        }
    }

    @Override
    public boolean endNotStartedAnimation(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (changeAnimationInfo.oldHolder != null && (viewHolder == null || changeAnimationInfo.oldHolder == viewHolder)) {
            onAnimationEndedBeforeStarted(changeAnimationInfo, changeAnimationInfo.oldHolder);
            dispatchFinished(changeAnimationInfo, changeAnimationInfo.oldHolder);
            changeAnimationInfo.clear(changeAnimationInfo.oldHolder);
        }
        if (changeAnimationInfo.newHolder != null && (viewHolder == null || changeAnimationInfo.newHolder == viewHolder)) {
            onAnimationEndedBeforeStarted(changeAnimationInfo, changeAnimationInfo.newHolder);
            dispatchFinished(changeAnimationInfo, changeAnimationInfo.newHolder);
            changeAnimationInfo.clear(changeAnimationInfo.newHolder);
        }
        return changeAnimationInfo.oldHolder == null && changeAnimationInfo.newHolder == null;
    }
}
