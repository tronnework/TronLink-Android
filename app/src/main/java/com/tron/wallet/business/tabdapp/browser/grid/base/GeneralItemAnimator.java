package com.tron.wallet.business.tabdapp.browser.grid.base;

import android.util.Log;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemAddAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemChangeAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemMoveAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemRemoveAnimationManager;
import org.apache.commons.cli.HelpFormatter;
public abstract class GeneralItemAnimator extends BaseItemAnimator {
    private static final String TAG = "ARVGeneralItemAnimator";
    private ItemAddAnimationManager mAddAnimationsManager;
    private ItemChangeAnimationManager mChangeAnimationsManager;
    private boolean mDebug;
    private ItemMoveAnimationManager mMoveAnimationsManager;
    private ItemRemoveAnimationManager mRemoveAnimationManager;

    @Override
    public boolean debugLogEnabled() {
        return this.mDebug;
    }

    protected ItemAddAnimationManager getItemAddAnimationsManager() {
        return this.mAddAnimationsManager;
    }

    protected ItemChangeAnimationManager getItemChangeAnimationsManager() {
        return this.mChangeAnimationsManager;
    }

    protected ItemMoveAnimationManager getItemMoveAnimationsManager() {
        return this.mMoveAnimationsManager;
    }

    protected ItemRemoveAnimationManager getRemoveAnimationManager() {
        return this.mRemoveAnimationManager;
    }

    public boolean isDebug() {
        return this.mDebug;
    }

    protected abstract void onSetup();

    public void setDebug(boolean z) {
        this.mDebug = z;
    }

    public void setItemAddAnimationsManager(ItemAddAnimationManager itemAddAnimationManager) {
        this.mAddAnimationsManager = itemAddAnimationManager;
    }

    public void setItemChangeAnimationsManager(ItemChangeAnimationManager itemChangeAnimationManager) {
        this.mChangeAnimationsManager = itemChangeAnimationManager;
    }

    public void setItemMoveAnimationsManager(ItemMoveAnimationManager itemMoveAnimationManager) {
        this.mMoveAnimationsManager = itemMoveAnimationManager;
    }

    public void setItemRemoveAnimationManager(ItemRemoveAnimationManager itemRemoveAnimationManager) {
        this.mRemoveAnimationManager = itemRemoveAnimationManager;
    }

    public GeneralItemAnimator() {
        setup();
    }

    private void setup() {
        onSetup();
        if (this.mRemoveAnimationManager == null || this.mAddAnimationsManager == null || this.mChangeAnimationsManager == null || this.mMoveAnimationsManager == null) {
            throw new IllegalStateException("setup incomplete");
        }
    }

    @Override
    public void runPendingAnimations() {
        if (hasPendingAnimations()) {
            onSchedulePendingAnimations();
        }
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder viewHolder) {
        if (this.mDebug) {
            Log.d(TAG, "animateRemove(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ")");
        }
        return this.mRemoveAnimationManager.addPendingAnimation(viewHolder);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        if (this.mDebug) {
            Log.d(TAG, "animateAdd(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ")");
        }
        return this.mAddAnimationsManager.addPendingAnimation(viewHolder);
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
        if (this.mDebug) {
            Log.d(TAG, "animateMove(id = " + viewHolder.getItemId() + ", position = " + viewHolder.getLayoutPosition() + ", fromX = " + i + ", fromY = " + i2 + ", toX = " + i3 + ", toY = " + i4 + ")");
        }
        return this.mMoveAnimationsManager.addPendingAnimation(viewHolder, i, i2, i3, i4);
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
        if (viewHolder == viewHolder2) {
            return this.mMoveAnimationsManager.addPendingAnimation(viewHolder, i, i2, i3, i4);
        }
        if (this.mDebug) {
            String str = HelpFormatter.DEFAULT_OPT_PREFIX;
            String l = viewHolder != null ? Long.toString(viewHolder.getItemId()) : HelpFormatter.DEFAULT_OPT_PREFIX;
            String l2 = viewHolder != null ? Long.toString(viewHolder.getLayoutPosition()) : HelpFormatter.DEFAULT_OPT_PREFIX;
            String l3 = viewHolder2 != null ? Long.toString(viewHolder2.getItemId()) : HelpFormatter.DEFAULT_OPT_PREFIX;
            if (viewHolder2 != null) {
                str = Long.toString(viewHolder2.getLayoutPosition());
            }
            Log.d(TAG, "animateChange(old.id = " + l + ", old.position = " + l2 + ", new.id = " + l3 + ", new.position = " + str + ", fromX = " + i + ", fromY = " + i2 + ", toX = " + i3 + ", toY = " + i4 + ")");
        }
        return this.mChangeAnimationsManager.addPendingAnimation(viewHolder, viewHolder2, i, i2, i3, i4);
    }

    protected void cancelAnimations(RecyclerView.ViewHolder viewHolder) {
        ViewCompat.animate(viewHolder.itemView).cancel();
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder viewHolder) {
        cancelAnimations(viewHolder);
        this.mMoveAnimationsManager.endPendingAnimations(viewHolder);
        this.mChangeAnimationsManager.endPendingAnimations(viewHolder);
        this.mRemoveAnimationManager.endPendingAnimations(viewHolder);
        this.mAddAnimationsManager.endPendingAnimations(viewHolder);
        this.mMoveAnimationsManager.endDeferredReadyAnimations(viewHolder);
        this.mChangeAnimationsManager.endDeferredReadyAnimations(viewHolder);
        this.mRemoveAnimationManager.endDeferredReadyAnimations(viewHolder);
        this.mAddAnimationsManager.endDeferredReadyAnimations(viewHolder);
        if (this.mRemoveAnimationManager.removeFromActive(viewHolder) && this.mDebug) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [remove]");
        }
        if (this.mAddAnimationsManager.removeFromActive(viewHolder) && this.mDebug) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [add]");
        }
        if (this.mChangeAnimationsManager.removeFromActive(viewHolder) && this.mDebug) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [change]");
        }
        if (this.mMoveAnimationsManager.removeFromActive(viewHolder) && this.mDebug) {
            throw new IllegalStateException("after animation is cancelled, item should not be in the active animation list [move]");
        }
        dispatchFinishedWhenDone();
    }

    @Override
    public boolean isRunning() {
        return this.mRemoveAnimationManager.isRunning() || this.mAddAnimationsManager.isRunning() || this.mChangeAnimationsManager.isRunning() || this.mMoveAnimationsManager.isRunning();
    }

    @Override
    public void endAnimations() {
        this.mMoveAnimationsManager.endAllPendingAnimations();
        this.mRemoveAnimationManager.endAllPendingAnimations();
        this.mAddAnimationsManager.endAllPendingAnimations();
        this.mChangeAnimationsManager.endAllPendingAnimations();
        if (isRunning()) {
            this.mMoveAnimationsManager.endAllDeferredReadyAnimations();
            this.mAddAnimationsManager.endAllDeferredReadyAnimations();
            this.mChangeAnimationsManager.endAllDeferredReadyAnimations();
            this.mRemoveAnimationManager.cancelAllStartedAnimations();
            this.mMoveAnimationsManager.cancelAllStartedAnimations();
            this.mAddAnimationsManager.cancelAllStartedAnimations();
            this.mChangeAnimationsManager.cancelAllStartedAnimations();
            dispatchAnimationsFinished();
        }
    }

    @Override
    public boolean dispatchFinishedWhenDone() {
        if (this.mDebug && !isRunning()) {
            Log.d(TAG, "dispatchFinishedWhenDone()");
        }
        return super.dispatchFinishedWhenDone();
    }

    protected boolean hasPendingAnimations() {
        return this.mRemoveAnimationManager.hasPending() || this.mMoveAnimationsManager.hasPending() || this.mChangeAnimationsManager.hasPending() || this.mAddAnimationsManager.hasPending();
    }

    protected void onSchedulePendingAnimations() {
        schedulePendingAnimationsByDefaultRule();
    }

    public void schedulePendingAnimationsByDefaultRule() {
        boolean hasPending = this.mRemoveAnimationManager.hasPending();
        boolean hasPending2 = this.mMoveAnimationsManager.hasPending();
        boolean hasPending3 = this.mChangeAnimationsManager.hasPending();
        boolean hasPending4 = this.mAddAnimationsManager.hasPending();
        long removeDuration = hasPending ? getRemoveDuration() : 0L;
        long moveDuration = hasPending2 ? getMoveDuration() : 0L;
        long changeDuration = hasPending3 ? getChangeDuration() : 0L;
        boolean z = false;
        if (hasPending) {
            this.mRemoveAnimationManager.runPendingAnimations(false, 0L);
        }
        if (hasPending2) {
            this.mMoveAnimationsManager.runPendingAnimations(hasPending, removeDuration);
        }
        if (hasPending3) {
            this.mChangeAnimationsManager.runPendingAnimations(hasPending, removeDuration);
        }
        if (hasPending4) {
            z = (hasPending || hasPending2 || hasPending3) ? true : true;
            this.mAddAnimationsManager.runPendingAnimations(z, z ? removeDuration + Math.max(moveDuration, changeDuration) : 0L);
        }
    }
}
