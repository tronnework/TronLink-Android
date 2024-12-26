package com.tron.wallet.business.tabdapp.browser.grid.base;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.SwipeableItemWrapperAdapter;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.WrapperAdapterUtils;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.ItemSlidingAnimator;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SwipeableItemConstants;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SwipeableItemViewHolder;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SwipingItemOperator;
public class RecyclerViewSwipeManager implements SwipeableItemConstants {
    private static final boolean LOCAL_LOGD = false;
    private static final boolean LOCAL_LOGV = false;
    private static final int MIN_DISTANCE_TOUCH_SLOP_MUL = 5;
    private static final int SLIDE_ITEM_IMMEDIATELY_SET_TRANSLATION_THRESHOLD_DP = 8;
    private static final String TAG = "ARVSwipeManager";
    private InternalHandler mHandler;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private ItemSlidingAnimator mItemSlideAnimator;
    private OnItemSwipeEventListener mItemSwipeEventListener;
    private int mLastTouchX;
    private int mLastTouchY;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private RecyclerView mRecyclerView;
    private boolean mSwipeHorizontal;
    private int mSwipeThresholdDistance;
    private RecyclerView.ViewHolder mSwipingItem;
    private SwipingItemOperator mSwipingItemOperator;
    private int mSwipingItemReactionType;
    private int mTouchSlop;
    private int mTouchedItemOffsetX;
    private int mTouchedItemOffsetY;
    private SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> mWrapperAdapter;
    private long mReturnToDefaultPositionAnimationDuration = 300;
    private long mMoveToSpecifiedPositionAnimationDuration = 200;
    private long mMoveToOutsideWindowAnimationDuration = 200;
    private long mCheckingTouchSlop = -1;
    private int mSwipingItemPosition = -1;
    private long mSwipingItemId = -1;
    private final Rect mSwipingItemMargins = new Rect();
    private RecyclerView.OnItemTouchListener mInternalUseOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            return onInterceptTouchEvent(recyclerView, motionEvent);
        }

        @Override
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            onTouchEvent(recyclerView, motionEvent);
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            onRequestDisallowInterceptTouchEvent(z);
        }
    };
    private VelocityTracker mVelocityTracker = VelocityTracker.obtain();
    private int mLongPressTimeout = ViewConfiguration.getLongPressTimeout();

    public interface OnItemSwipeEventListener {
        void onItemSwipeFinished(int i, int i2, int i3);

        void onItemSwipeStarted(int i);
    }

    private static int determineBackgroundType(float f, boolean z) {
        return z ? f < 0.0f ? 1 : 3 : f < 0.0f ? 2 : 4;
    }

    private static boolean isSpecialSwipeAmountValue(float f) {
        return f == -65536.0f || f == 65536.0f || f == -65537.0f || f == 65537.0f;
    }

    private static int resultCodeToSlideDirection(int i) {
        if (i != 3) {
            if (i != 4) {
                return i != 5 ? 0 : 3;
            }
            return 2;
        }
        return 1;
    }

    public long getMoveToOutsideWindowAnimationDuration() {
        return this.mMoveToOutsideWindowAnimationDuration;
    }

    public long getMoveToSpecifiedPositionAnimationDuration() {
        return this.mMoveToSpecifiedPositionAnimationDuration;
    }

    public OnItemSwipeEventListener getOnItemSwipeEventListener() {
        return this.mItemSwipeEventListener;
    }

    public long getReturnToDefaultPositionAnimationDuration() {
        return this.mReturnToDefaultPositionAnimationDuration;
    }

    public int getSwipeThresholdDistance() {
        return this.mSwipeThresholdDistance;
    }

    public int getSwipingItemPosition() {
        return this.mSwipingItemPosition;
    }

    public boolean isReleased() {
        return this.mInternalUseOnItemTouchListener == null;
    }

    public void setLongPressTimeout(int i) {
        this.mLongPressTimeout = i;
    }

    public void setMoveToOutsideWindowAnimationDuration(long j) {
        this.mMoveToOutsideWindowAnimationDuration = j;
    }

    public void setMoveToSpecifiedPositionAnimationDuration(long j) {
        this.mMoveToSpecifiedPositionAnimationDuration = j;
    }

    public void setOnItemSwipeEventListener(OnItemSwipeEventListener onItemSwipeEventListener) {
        this.mItemSwipeEventListener = onItemSwipeEventListener;
    }

    public void setReturnToDefaultPositionAnimationDuration(long j) {
        this.mReturnToDefaultPositionAnimationDuration = j;
    }

    public boolean swipeHorizontal() {
        return this.mSwipeHorizontal;
    }

    public RecyclerView.Adapter createWrappedAdapter(RecyclerView.Adapter adapter) {
        if (adapter.hasStableIds()) {
            if (this.mWrapperAdapter != null) {
                throw new IllegalStateException("already have a wrapped adapter");
            }
            SwipeableItemWrapperAdapter<RecyclerView.ViewHolder> swipeableItemWrapperAdapter = new SwipeableItemWrapperAdapter<>(this, adapter);
            this.mWrapperAdapter = swipeableItemWrapperAdapter;
            return swipeableItemWrapperAdapter;
        }
        throw new IllegalArgumentException("The passed adapter does not support stable IDs");
    }

    public void attachRecyclerView(RecyclerView recyclerView) {
        if (isReleased()) {
            throw new IllegalStateException("Accessing released object");
        }
        if (this.mRecyclerView != null) {
            throw new IllegalStateException("RecyclerView instance has already been set");
        }
        int orientation = CustomRecyclerViewUtils.getOrientation(recyclerView);
        if (orientation == -1) {
            throw new IllegalStateException("failed to determine layout orientation");
        }
        this.mRecyclerView = recyclerView;
        recyclerView.addOnItemTouchListener(this.mInternalUseOnItemTouchListener);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(recyclerView.getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mSwipeThresholdDistance = this.mTouchSlop * 5;
        ItemSlidingAnimator itemSlidingAnimator = new ItemSlidingAnimator(this.mWrapperAdapter);
        this.mItemSlideAnimator = itemSlidingAnimator;
        itemSlidingAnimator.setImmediatelySetTranslationThreshold((int) ((recyclerView.getResources().getDisplayMetrics().density * 8.0f) + 0.5f));
        this.mSwipeHorizontal = orientation == 1;
        this.mHandler = new InternalHandler(this);
    }

    public void release() {
        RecyclerView.OnItemTouchListener onItemTouchListener;
        cancelSwipe(true);
        InternalHandler internalHandler = this.mHandler;
        if (internalHandler != null) {
            internalHandler.release();
            this.mHandler = null;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && (onItemTouchListener = this.mInternalUseOnItemTouchListener) != null) {
            recyclerView.removeOnItemTouchListener(onItemTouchListener);
        }
        this.mInternalUseOnItemTouchListener = null;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        ItemSlidingAnimator itemSlidingAnimator = this.mItemSlideAnimator;
        if (itemSlidingAnimator != null) {
            itemSlidingAnimator.endAnimations();
            this.mItemSlideAnimator = null;
        }
        this.mWrapperAdapter = null;
        this.mRecyclerView = null;
    }

    public boolean isSwiping() {
        return (this.mSwipingItem == null || this.mHandler.isCancelSwipeRequested()) ? false : true;
    }

    public void setSwipeThresholdDistance(int i) {
        this.mSwipeThresholdDistance = Math.max(i, this.mTouchSlop);
    }

    boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            if (isSwiping()) {
                return false;
            }
            handleActionDown(recyclerView, motionEvent);
            return false;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (!isSwiping()) {
                    return handleActionMoveWhileNotSwiping(recyclerView, motionEvent);
                }
                handleActionMoveWhileSwiping(motionEvent);
                return true;
            } else if (actionMasked != 3) {
                return false;
            }
        }
        return handleActionUpOrCancel(motionEvent, true);
    }

    void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (isSwiping()) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    handleActionMoveWhileSwiping(motionEvent);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            handleActionUpOrCancel(motionEvent, true);
        }
    }

    void onRequestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            cancelSwipe(true);
        }
    }

    private boolean handleActionDown(RecyclerView recyclerView, MotionEvent motionEvent) {
        int wrappedItemPosition;
        RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
        if ((findChildViewHolderUnderWithTranslation instanceof SwipeableItemViewHolder) && (wrappedItemPosition = getWrappedItemPosition(findChildViewHolderUnderWithTranslation)) >= 0 && wrappedItemPosition < this.mWrapperAdapter.getItemCount()) {
            if (ItemIdComposer.extractWrappedIdPart(findChildViewHolderUnderWithTranslation.getItemId()) != ItemIdComposer.extractWrappedIdPart(this.mWrapperAdapter.getItemId(wrappedItemPosition))) {
                return false;
            }
            int x = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            View view = findChildViewHolderUnderWithTranslation.itemView;
            int translationY = (int) (view.getTranslationY() + 0.5f);
            int left = view.getLeft();
            int swipeReactionType = this.mWrapperAdapter.getSwipeReactionType(findChildViewHolderUnderWithTranslation, wrappedItemPosition, x - (left + ((int) (view.getTranslationX() + 0.5f))), y - (view.getTop() + translationY));
            if (swipeReactionType == 0) {
                return false;
            }
            this.mInitialTouchX = x;
            this.mInitialTouchY = y;
            this.mCheckingTouchSlop = findChildViewHolderUnderWithTranslation.getItemId();
            this.mSwipingItemReactionType = swipeReactionType;
            if ((16777216 & swipeReactionType) != 0) {
                this.mHandler.startLongPressDetection(motionEvent, this.mLongPressTimeout);
                return true;
            }
            return true;
        }
        return false;
    }

    private boolean handleActionUpOrCancel(MotionEvent motionEvent, boolean z) {
        int i;
        if (motionEvent != null) {
            i = motionEvent.getActionMasked();
            this.mLastTouchX = (int) (motionEvent.getX() + 0.5f);
            this.mLastTouchY = (int) (motionEvent.getY() + 0.5f);
        } else {
            i = 3;
        }
        if (!isSwiping()) {
            handleActionUpOrCancelWhileNotSwiping();
            return false;
        } else if (z) {
            handleActionUpOrCancelWhileSwiping(i);
            return true;
        } else {
            return true;
        }
    }

    private void handleActionUpOrCancelWhileNotSwiping() {
        InternalHandler internalHandler = this.mHandler;
        if (internalHandler != null) {
            internalHandler.cancelLongPressDetection();
        }
        this.mCheckingTouchSlop = -1L;
        this.mSwipingItemReactionType = 0;
    }

    private void handleActionUpOrCancelWhileSwiping(int r13) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewSwipeManager.handleActionUpOrCancelWhileSwiping(int):void");
    }

    private boolean handleActionMoveWhileNotSwiping(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mCheckingTouchSlop == -1) {
            return false;
        }
        int x = ((int) (motionEvent.getX() + 0.5f)) - this.mInitialTouchX;
        int y = ((int) (motionEvent.getY() + 0.5f)) - this.mInitialTouchY;
        if (this.mSwipeHorizontal) {
            y = x;
            x = y;
        }
        if (Math.abs(x) > this.mTouchSlop) {
            this.mCheckingTouchSlop = -1L;
            return false;
        } else if (Math.abs(y) <= this.mTouchSlop) {
            return false;
        } else {
            if (!this.mSwipeHorizontal ? y >= 0 ? (this.mSwipingItemReactionType & 2097152) == 0 : (this.mSwipingItemReactionType & 512) == 0 : y >= 0 ? (this.mSwipingItemReactionType & 32768) == 0 : (this.mSwipingItemReactionType & 8) == 0) {
                this.mCheckingTouchSlop = -1L;
                return false;
            }
            RecyclerView.ViewHolder findChildViewHolderUnderWithTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
            if (findChildViewHolderUnderWithTranslation == null || findChildViewHolderUnderWithTranslation.getItemId() != this.mCheckingTouchSlop) {
                this.mCheckingTouchSlop = -1L;
                return false;
            }
            return checkConditionAndStartSwiping(motionEvent, findChildViewHolderUnderWithTranslation);
        }
    }

    private void handleActionMoveWhileSwiping(MotionEvent motionEvent) {
        this.mLastTouchX = (int) (motionEvent.getX() + 0.5f);
        this.mLastTouchY = (int) (motionEvent.getY() + 0.5f);
        this.mVelocityTracker.addMovement(motionEvent);
        int i = this.mLastTouchX - this.mTouchedItemOffsetX;
        int i2 = this.mLastTouchY - this.mTouchedItemOffsetY;
        this.mSwipingItemOperator.update(getSwipingItemPosition(), i, i2);
    }

    private boolean checkConditionAndStartSwiping(MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder) {
        int wrappedItemPosition = getWrappedItemPosition(viewHolder);
        if (wrappedItemPosition == -1) {
            return false;
        }
        startSwiping(motionEvent, viewHolder, wrappedItemPosition);
        return true;
    }

    private void startSwiping(MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, int i) {
        this.mHandler.cancelLongPressDetection();
        this.mSwipingItem = viewHolder;
        this.mSwipingItemPosition = i;
        this.mSwipingItemId = this.mWrapperAdapter.getItemId(i);
        this.mLastTouchX = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        this.mLastTouchY = y;
        this.mTouchedItemOffsetX = this.mLastTouchX;
        this.mTouchedItemOffsetY = y;
        this.mCheckingTouchSlop = -1L;
        CustomRecyclerViewUtils.getLayoutMargins(viewHolder.itemView, this.mSwipingItemMargins);
        SwipingItemOperator swipingItemOperator = new SwipingItemOperator(this, this.mSwipingItem, this.mSwipingItemReactionType, this.mSwipeHorizontal);
        this.mSwipingItemOperator = swipingItemOperator;
        swipingItemOperator.start();
        this.mVelocityTracker.clear();
        this.mVelocityTracker.addMovement(motionEvent);
        this.mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
        OnItemSwipeEventListener onItemSwipeEventListener = this.mItemSwipeEventListener;
        if (onItemSwipeEventListener != null) {
            onItemSwipeEventListener.onItemSwipeStarted(i);
        }
        this.mWrapperAdapter.onSwipeItemStarted(this, viewHolder, i, this.mSwipingItemId);
    }

    private void finishSwiping(int r18) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewSwipeManager.finishSwiping(int):void");
    }

    private static void verifyAfterReaction(int i, int i2) {
        if ((i2 != 2 && i2 != 1) || i == 2 || i == 3 || i == 4 || i == 5) {
            return;
        }
        throw new IllegalStateException("Unexpected after reaction has been requested: result = " + i + ", afterReaction = " + i2);
    }

    static int getItemPosition(RecyclerView.Adapter adapter, long j, int i) {
        if (adapter == null) {
            return -1;
        }
        int itemCount = adapter.getItemCount();
        if (i < 0 || i >= itemCount || adapter.getItemId(i) != j) {
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (adapter.getItemId(i2) == j) {
                    return i2;
                }
            }
            return -1;
        }
        return i;
    }

    public void cancelSwipe() {
        cancelSwipe(false);
    }

    public boolean performFakeSwipe(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = 0;
        if ((viewHolder instanceof SwipeableItemViewHolder) && !isSwiping()) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return false;
                            }
                        }
                    }
                    if (this.mSwipeHorizontal) {
                        return false;
                    }
                }
                if (!this.mSwipeHorizontal) {
                    return false;
                }
            }
            int wrappedItemPosition = getWrappedItemPosition(viewHolder);
            if (wrappedItemPosition == -1) {
                return false;
            }
            MotionEvent obtain = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0);
            startSwiping(obtain, viewHolder, wrappedItemPosition);
            obtain.recycle();
            if (i == 2 || i == 3) {
                i2 = -1;
            } else if (i == 4 || i == 5) {
                i2 = 1;
            }
            applySlideItem(viewHolder, wrappedItemPosition, 0.0f, i2, false, this.mSwipeHorizontal, false, true);
            finishSwiping(i);
            return true;
        }
        return false;
    }

    void cancelSwipe(boolean z) {
        handleActionUpOrCancel(null, false);
        if (z) {
            finishSwiping(1);
        } else if (isSwiping()) {
            this.mHandler.requestDeferredCancelSwipe();
        }
    }

    public boolean isAnimationRunning(RecyclerView.ViewHolder viewHolder) {
        ItemSlidingAnimator itemSlidingAnimator = this.mItemSlideAnimator;
        return itemSlidingAnimator != null && itemSlidingAnimator.isRunning(viewHolder);
    }

    private void slideItem(RecyclerView.ViewHolder viewHolder, float f, boolean z, boolean z2, boolean z3) {
        if (f == -65536.0f) {
            this.mItemSlideAnimator.slideToOutsideOfWindow(viewHolder, 0, z3, this.mMoveToOutsideWindowAnimationDuration);
        } else if (f == -65537.0f) {
            this.mItemSlideAnimator.slideToOutsideOfWindow(viewHolder, 1, z3, this.mMoveToOutsideWindowAnimationDuration);
        } else if (f == 65536.0f) {
            this.mItemSlideAnimator.slideToOutsideOfWindow(viewHolder, 2, z3, this.mMoveToOutsideWindowAnimationDuration);
        } else if (f == 65537.0f) {
            this.mItemSlideAnimator.slideToOutsideOfWindow(viewHolder, 3, z3, this.mMoveToOutsideWindowAnimationDuration);
        } else if (f == 0.0f) {
            this.mItemSlideAnimator.slideToDefaultPosition(viewHolder, z2, z3, this.mReturnToDefaultPositionAnimationDuration);
        } else {
            this.mItemSlideAnimator.slideToSpecifiedPosition(viewHolder, f, z, z2, z3, this.mMoveToSpecifiedPositionAnimationDuration);
        }
    }

    private int getWrappedItemPosition(RecyclerView.ViewHolder viewHolder) {
        return WrapperAdapterUtils.unwrapPosition(this.mRecyclerView.getAdapter(), this.mWrapperAdapter, CustomRecyclerViewUtils.getSynchronizedPosition(viewHolder));
    }

    public void applySlideItem(androidx.recyclerview.widget.RecyclerView.ViewHolder r14, int r15, float r16, float r17, boolean r18, boolean r19, boolean r20, boolean r21) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewSwipeManager.applySlideItem(androidx.recyclerview.widget.RecyclerView$ViewHolder, int, float, float, boolean, boolean, boolean, boolean):void");
    }

    public void cancelPendingAnimations(RecyclerView.ViewHolder viewHolder) {
        ItemSlidingAnimator itemSlidingAnimator = this.mItemSlideAnimator;
        if (itemSlidingAnimator != null) {
            itemSlidingAnimator.endAnimation(viewHolder);
        }
    }

    public int getSwipeContainerViewTranslationX(RecyclerView.ViewHolder viewHolder) {
        return this.mItemSlideAnimator.getSwipeContainerViewTranslationX(viewHolder);
    }

    public int getSwipeContainerViewTranslationY(RecyclerView.ViewHolder viewHolder) {
        return this.mItemSlideAnimator.getSwipeContainerViewTranslationY(viewHolder);
    }

    void handleOnLongPress(MotionEvent motionEvent) {
        RecyclerView.ViewHolder findViewHolderForItemId = this.mRecyclerView.findViewHolderForItemId(this.mCheckingTouchSlop);
        if (findViewHolderForItemId != null) {
            checkConditionAndStartSwiping(motionEvent, findViewHolderForItemId);
        }
    }

    public int syncSwipingItemPosition() {
        return syncSwipingItemPosition(this.mSwipingItemPosition);
    }

    public int syncSwipingItemPosition(int i) {
        int itemPosition = getItemPosition(this.mWrapperAdapter, this.mSwipingItemId, i);
        this.mSwipingItemPosition = itemPosition;
        return itemPosition;
    }

    public static float adaptAmount(SwipeableItemViewHolder swipeableItemViewHolder, boolean z, float f, boolean z2, boolean z3) {
        if (z2 ^ z3) {
            if (f == 0.0f || isSpecialSwipeAmountValue(f)) {
                return f;
            }
            View swipeableContainerView = SwipeableViewHolderUtils.getSwipeableContainerView(swipeableItemViewHolder);
            float width = z ? swipeableContainerView.getWidth() : swipeableContainerView.getHeight();
            if (z3) {
                width = width != 0.0f ? 1.0f / width : 0.0f;
            }
            return f * width;
        }
        return f;
    }

    public static class InternalHandler extends Handler {
        private static final int MSG_DEFERRED_CANCEL_SWIPE = 2;
        private static final int MSG_LONGPRESS = 1;
        private MotionEvent mDownMotionEvent;
        private RecyclerViewSwipeManager mHolder;

        public InternalHandler(RecyclerViewSwipeManager recyclerViewSwipeManager) {
            this.mHolder = recyclerViewSwipeManager;
        }

        public void release() {
            removeCallbacksAndMessages(null);
            this.mHolder = null;
        }

        @Override
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                this.mHolder.handleOnLongPress(this.mDownMotionEvent);
            } else if (i != 2) {
            } else {
                this.mHolder.cancelSwipe(true);
            }
        }

        public void startLongPressDetection(MotionEvent motionEvent, int i) {
            cancelLongPressDetection();
            this.mDownMotionEvent = MotionEvent.obtain(motionEvent);
            sendEmptyMessageAtTime(1, motionEvent.getDownTime() + i);
        }

        public void cancelLongPressDetection() {
            removeMessages(1);
            MotionEvent motionEvent = this.mDownMotionEvent;
            if (motionEvent != null) {
                motionEvent.recycle();
                this.mDownMotionEvent = null;
            }
        }

        public void removeDeferredCancelSwipeRequest() {
            removeMessages(2);
        }

        public void requestDeferredCancelSwipe() {
            if (isCancelSwipeRequested()) {
                return;
            }
            sendEmptyMessage(2);
        }

        public boolean isCancelSwipeRequested() {
            return hasMessages(2);
        }
    }
}
