package com.tron.wallet.business.tabdapp.browser.grid.draggable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.CustomRecyclerViewUtils;
import com.tron.wallet.business.tabdapp.browser.grid.base.DraggingItemInfo;
public class SwapTargetItemOperator extends BaseDraggableItemDecorator {
    private static final ViewPropertyAnimatorListener RESET_TRANSLATION_LISTENER = new ViewPropertyAnimatorListener() {
        @Override
        public void onAnimationCancel(View view) {
        }

        @Override
        public void onAnimationStart(View view) {
        }

        @Override
        public void onAnimationEnd(View view) {
            ViewCompat.animate(view).setListener(null);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }
    };
    private static final String TAG = "SwapTargetItemOperator";
    private float mCurTranslationPhase;
    private final Rect mDraggingItemDecorationOffsets;
    private DraggingItemInfo mDraggingItemInfo;
    private float mReqTranslationPhase;
    private boolean mStarted;
    private final Rect mSwapTargetDecorationOffsets;
    private RecyclerView.ViewHolder mSwapTargetItem;
    private boolean mSwapTargetItemChanged;
    private final Rect mSwapTargetItemMargins;
    private Interpolator mSwapTargetTranslationInterpolator;
    private int mTranslationX;
    private int mTranslationY;

    public void setSwapTargetTranslationInterpolator(Interpolator interpolator) {
        this.mSwapTargetTranslationInterpolator = interpolator;
    }

    public void update(int i, int i2) {
        this.mTranslationX = i;
        this.mTranslationY = i2;
    }

    @Override
    public void setReturnToDefaultPositionAnimationDuration(int i) {
        super.setReturnToDefaultPositionAnimationDuration(i);
    }

    @Override
    public void setReturnToDefaultPositionAnimationInterpolator(Interpolator interpolator) {
        super.setReturnToDefaultPositionAnimationInterpolator(interpolator);
    }

    public SwapTargetItemOperator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo) {
        super(recyclerView, viewHolder);
        this.mSwapTargetDecorationOffsets = new Rect();
        this.mSwapTargetItemMargins = new Rect();
        Rect rect = new Rect();
        this.mDraggingItemDecorationOffsets = rect;
        this.mDraggingItemInfo = draggingItemInfo;
        CustomRecyclerViewUtils.getDecorationOffsets(this.mRecyclerView.getLayoutManager(), this.mDraggingItemViewHolder.itemView, rect);
    }

    private static float calculateCurrentTranslationPhase(float f, float f2) {
        float f3 = (f * 0.7f) + (0.3f * f2);
        return Math.abs(f3 - f2) < 0.01f ? f2 : f3;
    }

    public void setSwapTargetItem(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = this.mSwapTargetItem;
        if (viewHolder2 == viewHolder) {
            return;
        }
        if (viewHolder2 != null) {
            ViewPropertyAnimatorCompat animate = ViewCompat.animate(viewHolder2.itemView);
            animate.cancel();
            animate.setDuration(10L).translationX(0.0f).translationY(0.0f).setListener(RESET_TRANSLATION_LISTENER).start();
        }
        this.mSwapTargetItem = viewHolder;
        if (viewHolder != null) {
            ViewCompat.animate(viewHolder.itemView).cancel();
        }
        this.mSwapTargetItemChanged = true;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        RecyclerView.ViewHolder viewHolder = this.mDraggingItemViewHolder;
        RecyclerView.ViewHolder viewHolder2 = this.mSwapTargetItem;
        if (viewHolder == null || viewHolder2 == null || viewHolder.getItemId() != this.mDraggingItemInfo.id) {
            return;
        }
        float calculateTranslationPhase = calculateTranslationPhase(viewHolder, viewHolder2);
        this.mReqTranslationPhase = calculateTranslationPhase;
        if (this.mSwapTargetItemChanged) {
            this.mSwapTargetItemChanged = false;
            this.mCurTranslationPhase = calculateTranslationPhase;
        } else {
            this.mCurTranslationPhase = calculateCurrentTranslationPhase(this.mCurTranslationPhase, calculateTranslationPhase);
        }
        updateSwapTargetTranslation(viewHolder, viewHolder2, this.mCurTranslationPhase);
    }

    private float calculateTranslationPhase(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        View view = viewHolder2.itemView;
        int layoutPosition = viewHolder.getLayoutPosition();
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        CustomRecyclerViewUtils.getDecorationOffsets(this.mRecyclerView.getLayoutManager(), view, this.mSwapTargetDecorationOffsets);
        CustomRecyclerViewUtils.getLayoutMargins(view, this.mSwapTargetItemMargins);
        Rect rect = this.mSwapTargetItemMargins;
        Rect rect2 = this.mSwapTargetDecorationOffsets;
        int height = view.getHeight() + rect.top + rect.bottom + rect2.top + rect2.bottom;
        int width = view.getWidth() + rect.left + rect.right + rect2.left + rect2.right;
        float left = width != 0 ? (viewHolder.itemView.getLeft() - this.mTranslationX) / width : 0.0f;
        float top = height != 0 ? (viewHolder.itemView.getTop() - this.mTranslationY) / height : 0.0f;
        int orientation = CustomRecyclerViewUtils.getOrientation(this.mRecyclerView);
        if (orientation == 1) {
            left = layoutPosition > layoutPosition2 ? top : top + 1.0f;
        } else if (orientation != 0) {
            left = 0.0f;
        } else if (layoutPosition <= layoutPosition2) {
            left += 1.0f;
        }
        return Math.min(Math.max(left, 0.0f), 1.0f);
    }

    private void updateSwapTargetTranslation(RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, float f) {
        View view = viewHolder2.itemView;
        int layoutPosition = viewHolder.getLayoutPosition();
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        Rect rect = this.mDraggingItemInfo.margins;
        Rect rect2 = this.mDraggingItemDecorationOffsets;
        int i = this.mDraggingItemInfo.height + rect.top + rect.bottom + rect2.top + rect2.bottom;
        int i2 = this.mDraggingItemInfo.width + rect.left + rect.right + rect2.left + rect2.right;
        Interpolator interpolator = this.mSwapTargetTranslationInterpolator;
        if (interpolator != null) {
            f = interpolator.getInterpolation(f);
        }
        int orientation = CustomRecyclerViewUtils.getOrientation(this.mRecyclerView);
        if (orientation == 0) {
            if (layoutPosition > layoutPosition2) {
                view.setTranslationX(f * i2);
            } else {
                view.setTranslationX((f - 1.0f) * i2);
            }
        } else if (orientation != 1) {
        } else {
            if (layoutPosition > layoutPosition2) {
                view.setTranslationY(f * i);
            } else {
                view.setTranslationY((f - 1.0f) * i);
            }
        }
    }

    public void start() {
        if (this.mStarted) {
            return;
        }
        this.mRecyclerView.addItemDecoration(this, 0);
        this.mStarted = true;
    }

    public void finish(boolean z) {
        if (this.mStarted) {
            this.mRecyclerView.removeItemDecoration(this);
        }
        RecyclerView.ItemAnimator itemAnimator = this.mRecyclerView.getItemAnimator();
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
        this.mRecyclerView.stopScroll();
        if (this.mSwapTargetItem != null) {
            updateSwapTargetTranslation(this.mDraggingItemViewHolder, this.mSwapTargetItem, this.mCurTranslationPhase);
            moveToDefaultPosition(this.mSwapTargetItem.itemView, 1.0f, 1.0f, 0.0f, 1.0f, z);
            this.mSwapTargetItem = null;
        }
        this.mDraggingItemViewHolder = null;
        this.mTranslationX = 0;
        this.mTranslationY = 0;
        this.mCurTranslationPhase = 0.0f;
        this.mReqTranslationPhase = 0.0f;
        this.mStarted = false;
        this.mDraggingItemInfo = null;
    }

    public void onItemViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == this.mSwapTargetItem) {
            setSwapTargetItem(null);
        }
    }
}
