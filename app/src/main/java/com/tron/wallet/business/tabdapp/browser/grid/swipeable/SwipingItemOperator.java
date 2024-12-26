package com.tron.wallet.business.tabdapp.browser.grid.swipeable;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewSwipeManager;
public class SwipingItemOperator {
    private static final int MIN_GRABBING_AREA_SIZE = 48;
    private static final int REACTION_CAN_NOT_SWIPE = 0;
    private static final int REACTION_CAN_NOT_SWIPE_WITH_RUBBER_BAND_EFFECT = 1;
    private static final int REACTION_CAN_SWIPE = 2;
    private static final String TAG = "SwipingItemOperator";
    private int mDownSwipeReactionType;
    private int mInitialTranslateAmountX;
    private int mInitialTranslateAmountY;
    private float mInvSwipingItemHeight;
    private float mInvSwipingItemWidth;
    private int mLeftSwipeReactionType;
    private float mPrevTranslateAmount;
    private int mRightSwipeReactionType;
    private int mSwipeDistanceX;
    private int mSwipeDistanceY;
    private final boolean mSwipeHorizontal;
    private RecyclerViewSwipeManager mSwipeManager;
    private RecyclerView.ViewHolder mSwipingItem;
    private View mSwipingItemContainerView;
    private final int mSwipingItemHeight;
    private int mSwipingItemWidth;
    private int mUpSwipeReactionType;
    private static final float RUBBER_BAND_LIMIT = 0.15f;
    private static final Interpolator RUBBER_BAND_INTERPOLATOR = new RubberBandInterpolator(RUBBER_BAND_LIMIT);

    private static float calcInv(int i) {
        if (i != 0) {
            return 1.0f / i;
        }
        return 0.0f;
    }

    public void finish() {
        this.mSwipeManager = null;
        this.mSwipingItem = null;
        this.mSwipeDistanceX = 0;
        this.mSwipeDistanceY = 0;
        this.mSwipingItemWidth = 0;
        this.mInvSwipingItemWidth = 0.0f;
        this.mInvSwipingItemHeight = 0.0f;
        this.mLeftSwipeReactionType = 0;
        this.mUpSwipeReactionType = 0;
        this.mRightSwipeReactionType = 0;
        this.mDownSwipeReactionType = 0;
        this.mPrevTranslateAmount = 0.0f;
        this.mInitialTranslateAmountX = 0;
        this.mInitialTranslateAmountY = 0;
        this.mSwipingItemContainerView = null;
    }

    public SwipingItemOperator(RecyclerViewSwipeManager recyclerViewSwipeManager, RecyclerView.ViewHolder viewHolder, int i, boolean z) {
        this.mSwipeManager = recyclerViewSwipeManager;
        this.mSwipingItem = viewHolder;
        this.mLeftSwipeReactionType = SwipeReactionUtils.extractLeftReaction(i);
        this.mUpSwipeReactionType = SwipeReactionUtils.extractUpReaction(i);
        this.mRightSwipeReactionType = SwipeReactionUtils.extractRightReaction(i);
        this.mDownSwipeReactionType = SwipeReactionUtils.extractDownReaction(i);
        this.mSwipeHorizontal = z;
        View swipeableContainerView = SwipeableViewHolderUtils.getSwipeableContainerView(viewHolder);
        this.mSwipingItemContainerView = swipeableContainerView;
        this.mSwipingItemWidth = swipeableContainerView.getWidth();
        int height = this.mSwipingItemContainerView.getHeight();
        this.mSwipingItemHeight = height;
        this.mInvSwipingItemWidth = calcInv(this.mSwipingItemWidth);
        this.mInvSwipingItemHeight = calcInv(height);
    }

    public void start() {
        int i = (int) (this.mSwipingItem.itemView.getResources().getDisplayMetrics().density * 48.0f);
        int max = Math.max(0, this.mSwipingItemWidth - i);
        int max2 = Math.max(0, this.mSwipingItemHeight - i);
        this.mInitialTranslateAmountX = clip(this.mSwipeManager.getSwipeContainerViewTranslationX(this.mSwipingItem), -max, max);
        this.mInitialTranslateAmountY = clip(this.mSwipeManager.getSwipeContainerViewTranslationY(this.mSwipingItem), -max2, max2);
    }

    public void update(int i, int i2, int i3) {
        float signum;
        if (this.mSwipeDistanceX == i2 && this.mSwipeDistanceY == i3) {
            return;
        }
        this.mSwipeDistanceX = i2;
        this.mSwipeDistanceY = i3;
        boolean z = this.mSwipeHorizontal;
        int i4 = z ? i2 + this.mInitialTranslateAmountX : this.mInitialTranslateAmountY + i3;
        int i5 = z ? this.mSwipingItemWidth : this.mSwipingItemHeight;
        float f = z ? this.mInvSwipingItemWidth : this.mInvSwipingItemHeight;
        int i6 = z ? i4 > 0 ? this.mRightSwipeReactionType : this.mLeftSwipeReactionType : i4 > 0 ? this.mDownSwipeReactionType : this.mUpSwipeReactionType;
        if (i6 == 1) {
            signum = Math.signum(i4) * RUBBER_BAND_INTERPOLATOR.getInterpolation(Math.min(Math.abs(i4), i5) * f);
        } else {
            signum = i6 != 2 ? 0.0f : Math.min(Math.max(i4 * f, -1.0f), 1.0f);
        }
        this.mSwipeManager.applySlideItem(this.mSwipingItem, i, this.mPrevTranslateAmount, signum, true, this.mSwipeHorizontal, false, true);
        this.mPrevTranslateAmount = signum;
    }

    private static int clip(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }
}
