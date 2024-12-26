package com.tron.wallet.business.tabdapp.browser.grid.impl;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
public abstract class ItemAddAnimationManager extends BaseItemAnimationManager<AddAnimationInfo> {
    private static final String TAG = "ARVItemAddAnimMgr";

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder);

    public ItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    @Override
    public long getDuration() {
        return this.mItemAnimator.getAddDuration();
    }

    @Override
    public void setDuration(long j) {
        this.mItemAnimator.setAddDuration(j);
    }

    @Override
    public void dispatchStarting(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchAddStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchAddStarting(viewHolder);
    }

    @Override
    public void dispatchFinished(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchAddFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchAddFinished(viewHolder);
    }

    @Override
    public boolean endNotStartedAnimation(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (addAnimationInfo.holder != null) {
            if (viewHolder == null || addAnimationInfo.holder == viewHolder) {
                onAnimationEndedBeforeStarted(addAnimationInfo, addAnimationInfo.holder);
                dispatchFinished(addAnimationInfo, addAnimationInfo.holder);
                addAnimationInfo.clear(addAnimationInfo.holder);
                return true;
            }
            return false;
        }
        return false;
    }
}
