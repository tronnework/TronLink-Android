package com.tron.wallet.business.tabdapp.browser.grid.impl;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
public abstract class ItemMoveAnimationManager extends BaseItemAnimationManager<MoveAnimationInfo> {
    public static final String TAG = "ARVItemMoveAnimMgr";

    public abstract boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4);

    public ItemMoveAnimationManager(BaseItemAnimator baseItemAnimator) {
        super(baseItemAnimator);
    }

    @Override
    public long getDuration() {
        return this.mItemAnimator.getMoveDuration();
    }

    @Override
    public void setDuration(long j) {
        this.mItemAnimator.setMoveDuration(j);
    }

    @Override
    public void dispatchStarting(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchMoveStarting(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchMoveStarting(viewHolder);
    }

    @Override
    public void dispatchFinished(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (debugLogEnabled()) {
            Log.d(TAG, "dispatchMoveFinished(" + viewHolder + ")");
        }
        this.mItemAnimator.dispatchMoveFinished(viewHolder);
    }

    @Override
    public boolean endNotStartedAnimation(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        if (moveAnimationInfo.holder != null) {
            if (viewHolder == null || moveAnimationInfo.holder == viewHolder) {
                onAnimationEndedBeforeStarted(moveAnimationInfo, moveAnimationInfo.holder);
                dispatchFinished(moveAnimationInfo, moveAnimationInfo.holder);
                moveAnimationInfo.clear(moveAnimationInfo.holder);
                return true;
            }
            return false;
        }
        return false;
    }
}
