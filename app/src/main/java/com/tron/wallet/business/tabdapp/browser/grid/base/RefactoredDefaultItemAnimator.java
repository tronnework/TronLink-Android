package com.tron.wallet.business.tabdapp.browser.grid.base;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.animator.BaseItemAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.impl.AddAnimationInfo;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ChangeAnimationInfo;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemAddAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemChangeAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemMoveAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.ItemRemoveAnimationManager;
import com.tron.wallet.business.tabdapp.browser.grid.impl.MoveAnimationInfo;
import com.tron.wallet.business.tabdapp.browser.grid.impl.RemoveAnimationInfo;
import java.util.List;
public class RefactoredDefaultItemAnimator extends GeneralItemAnimator {
    @Override
    public void onSetup() {
        setItemAddAnimationsManager(new DefaultItemAddAnimationManager(this));
        setItemRemoveAnimationManager(new DefaultItemRemoveAnimationManager(this));
        setItemChangeAnimationsManager(new DefaultItemChangeAnimationManager(this));
        setItemMoveAnimationsManager(new DefaultItemMoveAnimationManager(this));
    }

    @Override
    protected void onSchedulePendingAnimations() {
        schedulePendingAnimationsByDefaultRule();
    }

    @Override
    public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder, List<Object> list) {
        return !list.isEmpty() || super.canReuseUpdatedViewHolder(viewHolder, list);
    }

    protected static class DefaultItemAddAnimationManager extends ItemAddAnimationManager {
        @Override
        public void onAnimationEndedSuccessfully(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemAddAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override
        public void onCreateAnimation(AddAnimationInfo addAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(addAnimationInfo.holder.itemView);
            animate.alpha(1.0f);
            animate.setDuration(getDuration());
            startActiveItemAnimation(addAnimationInfo, addAnimationInfo.holder, animate);
        }

        @Override
        public void onAnimationEndedBeforeStarted(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override
        public void onAnimationCancel(AddAnimationInfo addAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override
        public boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder) {
            resetAnimation(viewHolder);
            viewHolder.itemView.setAlpha(0.0f);
            enqueuePendingAnimationInfo(new AddAnimationInfo(viewHolder));
            return true;
        }
    }

    protected static class DefaultItemRemoveAnimationManager extends ItemRemoveAnimationManager {
        @Override
        public void onAnimationCancel(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemRemoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override
        public void onCreateAnimation(RemoveAnimationInfo removeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(removeAnimationInfo.holder.itemView);
            animate.setDuration(getDuration());
            animate.alpha(0.0f);
            startActiveItemAnimation(removeAnimationInfo, removeAnimationInfo.holder, animate);
        }

        @Override
        public void onAnimationEndedSuccessfully(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override
        public void onAnimationEndedBeforeStarted(RemoveAnimationInfo removeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            viewHolder.itemView.setAlpha(1.0f);
        }

        @Override
        public boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder) {
            resetAnimation(viewHolder);
            enqueuePendingAnimationInfo(new RemoveAnimationInfo(viewHolder));
            return true;
        }
    }

    protected static class DefaultItemChangeAnimationManager extends ItemChangeAnimationManager {
        @Override
        public void onAnimationCancel(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemChangeAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override
        protected void onCreateChangeAnimationForOldItem(ChangeAnimationInfo changeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.oldHolder.itemView);
            animate.setDuration(getDuration());
            animate.translationX(changeAnimationInfo.toX - changeAnimationInfo.fromX);
            animate.translationY(changeAnimationInfo.toY - changeAnimationInfo.fromY);
            animate.alpha(0.0f);
            startActiveItemAnimation(changeAnimationInfo, changeAnimationInfo.oldHolder, animate);
        }

        @Override
        protected void onCreateChangeAnimationForNewItem(ChangeAnimationInfo changeAnimationInfo) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(changeAnimationInfo.newHolder.itemView);
            animate.translationX(0.0f);
            animate.translationY(0.0f);
            animate.setDuration(getDuration());
            animate.alpha(1.0f);
            startActiveItemAnimation(changeAnimationInfo, changeAnimationInfo.newHolder, animate);
        }

        @Override
        public void onAnimationEndedSuccessfully(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        @Override
        public void onAnimationEndedBeforeStarted(ChangeAnimationInfo changeAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            view.setAlpha(1.0f);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        @Override
        public boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, int i, int i2, int i3, int i4) {
            float translationX = viewHolder.itemView.getTranslationX();
            float translationY = viewHolder.itemView.getTranslationY();
            float alpha = viewHolder.itemView.getAlpha();
            resetAnimation(viewHolder);
            int i5 = (int) ((i3 - i) - translationX);
            int i6 = (int) ((i4 - i2) - translationY);
            viewHolder.itemView.setTranslationX(translationX);
            viewHolder.itemView.setTranslationY(translationY);
            viewHolder.itemView.setAlpha(alpha);
            if (viewHolder2 != null) {
                resetAnimation(viewHolder2);
                viewHolder2.itemView.setTranslationX(-i5);
                viewHolder2.itemView.setTranslationY(-i6);
                viewHolder2.itemView.setAlpha(0.0f);
            }
            enqueuePendingAnimationInfo(new ChangeAnimationInfo(viewHolder, viewHolder2, i, i2, i3, i4));
            return true;
        }
    }

    protected static class DefaultItemMoveAnimationManager extends ItemMoveAnimationManager {
        @Override
        public void onAnimationEndedSuccessfully(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
        }

        public DefaultItemMoveAnimationManager(BaseItemAnimator baseItemAnimator) {
            super(baseItemAnimator);
        }

        @Override
        public void onCreateAnimation(MoveAnimationInfo moveAnimationInfo) {
            View view = moveAnimationInfo.holder.itemView;
            int i = moveAnimationInfo.toX - moveAnimationInfo.fromX;
            int i2 = moveAnimationInfo.toY - moveAnimationInfo.fromY;
            if (i != 0) {
                ViewCompat.animate(view).translationX(0.0f);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(0.0f);
            }
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(view);
            animate.setDuration(getDuration());
            startActiveItemAnimation(moveAnimationInfo, moveAnimationInfo.holder, animate);
        }

        @Override
        public void onAnimationEndedBeforeStarted(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
        }

        @Override
        public void onAnimationCancel(MoveAnimationInfo moveAnimationInfo, RecyclerView.ViewHolder viewHolder) {
            View view = viewHolder.itemView;
            int i = moveAnimationInfo.toX - moveAnimationInfo.fromX;
            int i2 = moveAnimationInfo.toY - moveAnimationInfo.fromY;
            if (i != 0) {
                ViewCompat.animate(view).translationX(0.0f);
            }
            if (i2 != 0) {
                ViewCompat.animate(view).translationY(0.0f);
            }
            if (i != 0) {
                view.setTranslationX(0.0f);
            }
            if (i2 != 0) {
                view.setTranslationY(0.0f);
            }
        }

        @Override
        public boolean addPendingAnimation(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            View view = viewHolder.itemView;
            int translationX = (int) (i + viewHolder.itemView.getTranslationX());
            int translationY = (int) (i2 + viewHolder.itemView.getTranslationY());
            resetAnimation(viewHolder);
            int i5 = i3 - translationX;
            int i6 = i4 - translationY;
            MoveAnimationInfo moveAnimationInfo = new MoveAnimationInfo(viewHolder, translationX, translationY, i3, i4);
            if (i5 == 0 && i6 == 0) {
                dispatchFinished(moveAnimationInfo, moveAnimationInfo.holder);
                moveAnimationInfo.clear(moveAnimationInfo.holder);
                return false;
            }
            if (i5 != 0) {
                view.setTranslationX(-i5);
            }
            if (i6 != 0) {
                view.setTranslationY(-i6);
            }
            enqueuePendingAnimationInfo(moveAnimationInfo);
            return true;
        }
    }
}
