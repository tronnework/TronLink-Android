package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.animator.DraggableItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.base.RefactoredDefaultItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemRemoveAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.RemoveAnimationInfo;
public class SwipeDismissItemAnimator extends DraggableItemAnimator {
    public static final Interpolator MOVE_INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @Override
    protected void onSetup() {
        setItemAddAnimationsManager(new RefactoredDefaultItemAnimator.DefaultItemAddAnimationManager(this));
        setItemRemoveAnimationManager(new SwipeDismissItemRemoveAnimationManager(this));
        setItemChangeAnimationsManager(new RefactoredDefaultItemAnimator.DefaultItemChangeAnimationManager(this));
        setItemMoveAnimationsManager(new RefactoredDefaultItemAnimator.DefaultItemMoveAnimationManager(this));
        setRemoveDuration(150L);
        setMoveDuration(150L);
    }

    protected static class SwipeDismissItemRemoveAnimationManager extends ItemRemoveAnimationManager {
        protected static final Interpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();

        @Override
        public void onAnimationCancel(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public SwipeDismissItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override
        public void onCreateAnimation(RemoveAnimationInfo removeAnimationInfo) {
            ViewPropertyAnimatorCompat animate;
            if (isSwipeDismissed(removeAnimationInfo.holder)) {
                animate = ViewCompat.animate(removeAnimationInfo.holder.itemView);
                animate.setDuration(getDuration());
            } else {
                animate = ViewCompat.animate(removeAnimationInfo.holder.itemView);
                animate.setDuration(getDuration());
                animate.setInterpolator(DEFAULT_INTERPOLATOR);
                animate.alpha(0.0f);
            }
            startActiveItemAnimation(removeAnimationInfo, removeAnimationInfo.holder, animate);
        }

        protected static boolean isSwipeDismissed(RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof SwipeableItemViewHolder) {
                SwipeableItemViewHolder swipeableItemViewHolder = (SwipeableItemViewHolder) viewHolder;
                int swipeResult = swipeableItemViewHolder.getSwipeResult();
                return (swipeResult == 2 || swipeResult == 3 || swipeResult == 4 || swipeResult == 5) && swipeableItemViewHolder.getAfterSwipeReaction() == 1;
            }
            return false;
        }

        protected static boolean isSwipeDismissed(RemoveAnimationInfo removeAnimationInfo) {
            return removeAnimationInfo instanceof SwipeDismissRemoveAnimationInfo;
        }

        @Override
        public void onAnimationEndedSuccessfully(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (isSwipeDismissed(removeAnimationInfo)) {
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                return;
            }
            view.setAlpha(1.0f);
        }

        @Override
        public void onAnimationEndedBeforeStarted(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            if (isSwipeDismissed(removeAnimationInfo)) {
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                return;
            }
            view.setAlpha(1.0f);
        }

        @Override
        public boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder) {
            if (isSwipeDismissed(viewHolder)) {
                View view = viewHolder.itemView;
                endAnimation(viewHolder);
                view.setTranslationX((int) (view.getTranslationX() + 0.5f));
                view.setTranslationY((int) (view.getTranslationY() + 0.5f));
                enqueuePendingAnimationInfo(new SwipeDismissRemoveAnimationInfo(viewHolder));
                return true;
            }
            endAnimation(viewHolder);
            enqueuePendingAnimationInfo(new RemoveAnimationInfo(viewHolder));
            return true;
        }
    }

    public static class SwipeDismissRemoveAnimationInfo extends RemoveAnimationInfo {
        public SwipeDismissRemoveAnimationInfo(RecyclerView.ViewHolder viewHolder) {
            super(viewHolder);
        }
    }
}
