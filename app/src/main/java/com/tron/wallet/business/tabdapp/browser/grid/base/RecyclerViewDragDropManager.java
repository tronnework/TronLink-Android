package com.tron.wallet.business.tabdapp.browser.grid.base;

import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.DraggableItemViewHolder;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.DraggableItemWrapperAdapter;
import com.tron.wallet.business.tabdapp.browser.grid.baseadapter.WrapperAdapterUtils;
import com.tron.wallet.business.tabdapp.browser.grid.draggable.DraggingItemDecorator;
import com.tron.wallet.business.tabdapp.browser.grid.draggable.SwapTargetItemOperator;
import com.tron.wallet.business.tabdapp.browser.grid.draggable.TopBottomEdgeEffectDecorator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
public class RecyclerViewDragDropManager implements DraggableItemConstants {
    public static final int ITEM_MOVE_MODE_DEFAULT = 0;
    public static final int ITEM_MOVE_MODE_SWAP = 1;
    private static final boolean LOCAL_LOGD = false;
    private static final boolean LOCAL_LOGI = true;
    private static final boolean LOCAL_LOGV = false;
    private static final float SCROLL_AMOUNT_COEFF = 25.0f;
    private static final int SCROLL_DIR_DOWN = 2;
    private static final int SCROLL_DIR_LEFT = 4;
    private static final int SCROLL_DIR_NONE = 0;
    private static final int SCROLL_DIR_RIGHT = 8;
    private static final int SCROLL_DIR_UP = 1;
    private static final float SCROLL_THRESHOLD = 0.3f;
    private static final float SCROLL_TOUCH_SLOP_MULTIPLY = 1.5f;
    private static final String TAG = "ARVDragDropManager";
    private int mActualScrollByXAmount;
    private int mActualScrollByYAmount;
    private boolean mCanDragH;
    private boolean mCanDragV;
    private boolean mCheckCanDrop;
    private Object mComposedAdapterTag;
    private float mDisplayDensity;
    private int mDragMaxTouchX;
    private int mDragMaxTouchY;
    private int mDragMinTouchX;
    private int mDragMinTouchY;
    private int mDragScrollDistanceX;
    private int mDragScrollDistanceY;
    private int mDragStartTouchX;
    private int mDragStartTouchY;
    private ItemDraggableRange mDraggableRange;
    private DraggingItemDecorator mDraggingItemDecorator;
    private DraggingItemInfo mDraggingItemInfo;
    RecyclerView.ViewHolder mDraggingItemViewHolder;
    private TopBottomEdgeEffectDecorator mEdgeEffectDecorator;
    private InternalHandler mHandler;
    private boolean mInScrollByMethod;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private boolean mInitiateOnLongPress;
    private boolean mInitiateOnTouch;
    private OnItemDragEventListener mItemDragEventListener;
    private int mLastTouchX;
    private int mLastTouchY;
    private NestedScrollView mNestedScrollView;
    private int mNestedScrollViewScrollX;
    private int mNestedScrollViewScrollY;
    private int mOrigOverScrollMode;
    private RecyclerView mRecyclerView;
    private ItemDraggableRange mRootDraggableRange;
    private int mScrollTouchSlop;
    private NinePatchDrawable mShadowDrawable;
    private SwapTargetItemOperator mSwapTargetItemOperator;
    private int mTouchSlop;
    private DraggableItemWrapperAdapter mWrapperAdapter;
    public static final Interpolator DEFAULT_SWAP_TARGET_TRANSITION_INTERPOLATOR = new BasicSwapTargetTranslationInterpolator();
    public static final Interpolator DEFAULT_ITEM_SETTLE_BACK_INTO_PLACE_ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private Interpolator mSwapTargetTranslationInterpolator = DEFAULT_SWAP_TARGET_TRANSITION_INTERPOLATOR;
    private long mInitialTouchItemId = -1;
    private boolean mInitiateOnMove = true;
    private final Rect mTmpRect1 = new Rect();
    private int mItemSettleBackIntoPlaceAnimationDuration = 200;
    private Interpolator mItemSettleBackIntoPlaceAnimationInterpolator = DEFAULT_ITEM_SETTLE_BACK_INTO_PLACE_ANIMATION_INTERPOLATOR;
    private int mItemMoveMode = 0;
    private DraggingItemEffectsInfo mDraggingItemEffectsInfo = new DraggingItemEffectsInfo();
    private int mScrollDirMask = 0;
    private float mDragEdgeScrollSpeed = 1.0f;
    private int mCurrentItemMoveMode = 0;
    private SwapTarget mTempSwapTarget = new SwapTarget();
    private FindSwapTargetContext mFindSwapTargetContext = new FindSwapTargetContext();
    private final Runnable mCheckItemSwappingRunnable = new Runnable() {
        @Override
        public void run() {
            if (mDraggingItemViewHolder != null) {
                RecyclerViewDragDropManager recyclerViewDragDropManager = RecyclerViewDragDropManager.this;
                recyclerViewDragDropManager.checkItemSwapping(recyclerViewDragDropManager.getRecyclerView());
            }
        }
    };
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
    private RecyclerView.OnScrollListener mInternalUseOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            onScrollStateChanged(recyclerView, i);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            onScrolled(recyclerView, i, i2);
        }
    };
    private ScrollOnDraggingProcessRunnable mScrollOnDraggingProcess = new ScrollOnDraggingProcessRunnable(this);
    private int mLongPressTimeout = ViewConfiguration.getLongPressTimeout();

    @Retention(RetentionPolicy.SOURCE)
    public @interface ItemMoveMode {
    }

    public interface OnItemDragEventListener {
        void onItemDragFinished(int i, int i2, boolean z);

        void onItemDragMoveDistanceUpdated(int i, int i2);

        void onItemDragPositionChanged(int i, int i2);

        void onItemDragStarted(int i);
    }

    private static boolean supportsEdgeEffect() {
        return true;
    }

    public float getDragEdgeScrollSpeed() {
        return this.mDragEdgeScrollSpeed;
    }

    public RecyclerView.ViewHolder getDraggingItemViewHolder() {
        return this.mDraggingItemViewHolder;
    }

    public int getItemMoveMode() {
        return this.mItemMoveMode;
    }

    public int getItemSettleBackIntoPlaceAnimationDuration() {
        return this.mItemSettleBackIntoPlaceAnimationDuration;
    }

    public Interpolator getItemSettleBackIntoPlaceAnimationInterpolator() {
        return this.mItemSettleBackIntoPlaceAnimationInterpolator;
    }

    public OnItemDragEventListener getOnItemDragEventListener() {
        return this.mItemDragEventListener;
    }

    RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public boolean isCheckCanDropEnabled() {
        return this.mCheckCanDrop;
    }

    public boolean isInitiateOnLongPressEnabled() {
        return this.mInitiateOnLongPress;
    }

    public boolean isInitiateOnMoveEnabled() {
        return this.mInitiateOnMove;
    }

    public boolean isInitiateOnTouchEnabled() {
        return this.mInitiateOnTouch;
    }

    public boolean isReleased() {
        return this.mInternalUseOnItemTouchListener == null;
    }

    public void setCheckCanDropEnabled(boolean z) {
        this.mCheckCanDrop = z;
    }

    public void setDraggingItemShadowDrawable(NinePatchDrawable ninePatchDrawable) {
        this.mShadowDrawable = ninePatchDrawable;
    }

    public void setInitiateOnLongPress(boolean z) {
        this.mInitiateOnLongPress = z;
    }

    public void setInitiateOnMove(boolean z) {
        this.mInitiateOnMove = z;
    }

    public void setInitiateOnTouch(boolean z) {
        this.mInitiateOnTouch = z;
    }

    public void setItemMoveMode(int i) {
        this.mItemMoveMode = i;
    }

    public void setItemSettleBackIntoPlaceAnimationDuration(int i) {
        this.mItemSettleBackIntoPlaceAnimationDuration = i;
    }

    public void setItemSettleBackIntoPlaceAnimationInterpolator(Interpolator interpolator) {
        this.mItemSettleBackIntoPlaceAnimationInterpolator = interpolator;
    }

    public void setLongPressTimeout(int i) {
        this.mLongPressTimeout = i;
    }

    public void setOnItemDragEventListener(OnItemDragEventListener onItemDragEventListener) {
        this.mItemDragEventListener = onItemDragEventListener;
    }

    public Interpolator setSwapTargetTranslationInterpolator() {
        return this.mSwapTargetTranslationInterpolator;
    }

    public void setSwapTargetTranslationInterpolator(Interpolator interpolator) {
        this.mSwapTargetTranslationInterpolator = interpolator;
    }

    public static class SwapTarget {
        public RecyclerView.ViewHolder holder;
        public int position;
        public boolean self;

        public void clear() {
            this.holder = null;
            this.position = -1;
            this.self = false;
        }

        SwapTarget() {
        }
    }

    public static class FindSwapTargetContext {
        public boolean checkCanSwap;
        public RecyclerView.ViewHolder draggingItem;
        public DraggingItemInfo draggingItemInfo;
        public int lastTouchX;
        public int lastTouchY;
        public int layoutType;
        public int overlayItemLeft;
        public int overlayItemLeftNotClipped;
        public int overlayItemTop;
        public int overlayItemTopNotClipped;
        public ItemDraggableRange rootAdapterRange;
        public RecyclerView rv;
        public boolean vertical;
        public ItemDraggableRange wrappedAdapterRange;

        public void clear() {
            this.rv = null;
            this.draggingItemInfo = null;
            this.draggingItem = null;
        }

        FindSwapTargetContext() {
        }

        public void setup(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, DraggingItemInfo draggingItemInfo, int i, int i2, ItemDraggableRange itemDraggableRange, ItemDraggableRange itemDraggableRange2, boolean z) {
            this.rv = recyclerView;
            this.draggingItemInfo = draggingItemInfo;
            this.draggingItem = viewHolder;
            this.lastTouchX = i;
            this.lastTouchY = i2;
            this.wrappedAdapterRange = itemDraggableRange;
            this.rootAdapterRange = itemDraggableRange2;
            this.checkCanSwap = z;
            int layoutType = CustomRecyclerViewUtils.getLayoutType(recyclerView);
            this.layoutType = layoutType;
            this.vertical = CustomRecyclerViewUtils.extractOrientation(layoutType) == 1;
            int i3 = i - draggingItemInfo.grabbedPositionX;
            this.overlayItemLeftNotClipped = i3;
            this.overlayItemLeft = i3;
            int i4 = i2 - draggingItemInfo.grabbedPositionY;
            this.overlayItemTopNotClipped = i4;
            this.overlayItemTop = i4;
            if (this.vertical) {
                int max = Math.max(this.overlayItemLeft, recyclerView.getPaddingLeft());
                this.overlayItemLeft = max;
                this.overlayItemLeft = Math.min(max, Math.max(0, (recyclerView.getWidth() - recyclerView.getPaddingRight()) - this.draggingItemInfo.width));
                return;
            }
            int max2 = Math.max(i4, recyclerView.getPaddingTop());
            this.overlayItemTop = max2;
            this.overlayItemTop = Math.min(max2, Math.max(0, (recyclerView.getHeight() - recyclerView.getPaddingBottom()) - this.draggingItemInfo.height));
        }
    }

    public RecyclerView.Adapter createWrappedAdapter(RecyclerView.Adapter adapter) {
        if (adapter.hasStableIds()) {
            if (this.mWrapperAdapter != null) {
                throw new IllegalStateException("already have a wrapped adapter");
            }
            DraggableItemWrapperAdapter draggableItemWrapperAdapter = new DraggableItemWrapperAdapter(this, adapter);
            this.mWrapperAdapter = draggableItemWrapperAdapter;
            return draggableItemWrapperAdapter;
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
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(this.mInternalUseOnScrollListener);
        this.mRecyclerView.addOnItemTouchListener(this.mInternalUseOnItemTouchListener);
        this.mDisplayDensity = this.mRecyclerView.getResources().getDisplayMetrics().density;
        int scaledTouchSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mTouchSlop = scaledTouchSlop;
        this.mScrollTouchSlop = (int) ((scaledTouchSlop * SCROLL_TOUCH_SLOP_MULTIPLY) + 0.5f);
        this.mHandler = new InternalHandler(this);
        if (supportsEdgeEffect()) {
            int orientation = CustomRecyclerViewUtils.getOrientation(this.mRecyclerView);
            if (orientation == 0 || orientation == 1) {
                this.mEdgeEffectDecorator = new TopBottomEdgeEffectDecorator(this.mRecyclerView);
            }
            TopBottomEdgeEffectDecorator topBottomEdgeEffectDecorator = this.mEdgeEffectDecorator;
            if (topBottomEdgeEffectDecorator != null) {
                topBottomEdgeEffectDecorator.start();
            }
        }
    }

    public void release() {
        RecyclerView.OnScrollListener onScrollListener;
        RecyclerView.OnItemTouchListener onItemTouchListener;
        cancelDrag(true);
        InternalHandler internalHandler = this.mHandler;
        if (internalHandler != null) {
            internalHandler.release();
            this.mHandler = null;
        }
        TopBottomEdgeEffectDecorator topBottomEdgeEffectDecorator = this.mEdgeEffectDecorator;
        if (topBottomEdgeEffectDecorator != null) {
            topBottomEdgeEffectDecorator.finish();
            this.mEdgeEffectDecorator = null;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && (onItemTouchListener = this.mInternalUseOnItemTouchListener) != null) {
            recyclerView.removeOnItemTouchListener(onItemTouchListener);
        }
        this.mInternalUseOnItemTouchListener = null;
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != null && (onScrollListener = this.mInternalUseOnScrollListener) != null) {
            recyclerView2.removeOnScrollListener(onScrollListener);
        }
        this.mInternalUseOnScrollListener = null;
        ScrollOnDraggingProcessRunnable scrollOnDraggingProcessRunnable = this.mScrollOnDraggingProcess;
        if (scrollOnDraggingProcessRunnable != null) {
            scrollOnDraggingProcessRunnable.release();
            this.mScrollOnDraggingProcess = null;
        }
        this.mWrapperAdapter = null;
        this.mRecyclerView = null;
        this.mSwapTargetTranslationInterpolator = null;
    }

    public boolean isDragging() {
        return (this.mDraggingItemInfo == null || this.mHandler.isCancelDragRequested()) ? false : true;
    }

    public void setDragEdgeScrollSpeed(float f) {
        this.mDragEdgeScrollSpeed = Math.min(Math.max(f, 0.0f), 2.0f);
    }

    boolean onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView r4, android.view.MotionEvent r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.onInterceptTouchEvent(androidx.recyclerview.widget.RecyclerView, android.view.MotionEvent):boolean");
    }

    void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (isDragging()) {
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    handleActionMoveWhileDragging(recyclerView, motionEvent);
                    return;
                } else if (actionMasked != 3) {
                    return;
                }
            }
            handleActionUpOrCancel(actionMasked, true);
        }
    }

    void onRequestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            cancelDrag(true);
        }
    }

    void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (this.mInScrollByMethod) {
            this.mActualScrollByXAmount = i;
            this.mActualScrollByYAmount = i2;
        } else if (isDragging()) {
            ViewCompat.postOnAnimationDelayed(this.mRecyclerView, this.mCheckItemSwappingRunnable, 500L);
        }
    }

    void onScrollStateChanged(RecyclerView recyclerView, int i) {
        if (i == 1) {
            cancelDrag(true);
        }
    }

    private boolean handleActionDown(RecyclerView recyclerView, MotionEvent motionEvent) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(recyclerView, motionEvent.getX(), motionEvent.getY());
        if (checkTouchedItemState(recyclerView, findChildViewHolderUnderWithoutTranslation)) {
            int x = (int) (motionEvent.getX() + 0.5f);
            int y = (int) (motionEvent.getY() + 0.5f);
            if (canStartDrag(findChildViewHolderUnderWithoutTranslation, x, y)) {
                int orientation = CustomRecyclerViewUtils.getOrientation(this.mRecyclerView);
                int spanCount = CustomRecyclerViewUtils.getSpanCount(this.mRecyclerView);
                this.mLastTouchX = x;
                this.mInitialTouchX = x;
                this.mLastTouchY = y;
                this.mInitialTouchY = y;
                this.mInitialTouchItemId = findChildViewHolderUnderWithoutTranslation.getItemId();
                boolean z = true;
                this.mCanDragH = orientation == 0 || (orientation == 1 && spanCount > 1);
                if (orientation != 1 && (orientation != 0 || spanCount <= 1)) {
                    z = false;
                }
                this.mCanDragV = z;
                if (this.mInitiateOnTouch) {
                    return checkConditionAndStartDragging(recyclerView, motionEvent, false);
                }
                if (this.mInitiateOnLongPress) {
                    this.mHandler.startLongPressDetection(motionEvent, this.mLongPressTimeout);
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    void handleOnLongPress(MotionEvent motionEvent) {
        if (this.mInitiateOnLongPress) {
            checkConditionAndStartDragging(this.mRecyclerView, motionEvent, false);
        }
    }

    void handleOnCheckItemViewSizeUpdate() {
        RecyclerView.ViewHolder findViewHolderForItemId = this.mRecyclerView.findViewHolderForItemId(this.mDraggingItemInfo.id);
        if (findViewHolderForItemId == null) {
            return;
        }
        int width = findViewHolderForItemId.itemView.getWidth();
        int height = findViewHolderForItemId.itemView.getHeight();
        if (width == this.mDraggingItemInfo.width && height == this.mDraggingItemInfo.height) {
            return;
        }
        DraggingItemInfo createWithNewView = DraggingItemInfo.createWithNewView(this.mDraggingItemInfo, findViewHolderForItemId);
        this.mDraggingItemInfo = createWithNewView;
        this.mDraggingItemDecorator.updateDraggingItemView(createWithNewView, findViewHolderForItemId);
    }

    private void startDragging(RecyclerView recyclerView, MotionEvent motionEvent, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange, AdapterPath adapterPath, int i, Object obj) {
        safeEndAnimation(recyclerView, viewHolder);
        this.mHandler.cancelLongPressDetection();
        this.mDraggingItemInfo = new DraggingItemInfo(recyclerView, viewHolder, this.mLastTouchX, this.mLastTouchY);
        this.mDraggingItemViewHolder = viewHolder;
        this.mDraggableRange = itemDraggableRange;
        this.mRootDraggableRange = convertToRootAdapterRange(adapterPath, itemDraggableRange);
        NestedScrollView findAncestorNestedScrollView = findAncestorNestedScrollView(this.mRecyclerView);
        if (findAncestorNestedScrollView == null || this.mRecyclerView.isNestedScrollingEnabled()) {
            this.mNestedScrollView = null;
        } else {
            this.mNestedScrollView = findAncestorNestedScrollView;
        }
        this.mOrigOverScrollMode = recyclerView.getOverScrollMode();
        recyclerView.setOverScrollMode(2);
        this.mLastTouchX = (int) (motionEvent.getX() + 0.5f);
        this.mLastTouchY = (int) (motionEvent.getY() + 0.5f);
        NestedScrollView nestedScrollView = this.mNestedScrollView;
        this.mNestedScrollViewScrollX = nestedScrollView != null ? nestedScrollView.getScrollX() : 0;
        NestedScrollView nestedScrollView2 = this.mNestedScrollView;
        this.mNestedScrollViewScrollY = nestedScrollView2 != null ? nestedScrollView2.getScrollY() : 0;
        int i2 = this.mLastTouchY;
        this.mDragMaxTouchY = i2;
        this.mDragMinTouchY = i2;
        this.mDragStartTouchY = i2;
        int i3 = this.mLastTouchX;
        this.mDragMaxTouchX = i3;
        this.mDragMinTouchX = i3;
        this.mDragStartTouchX = i3;
        this.mScrollDirMask = 0;
        this.mCurrentItemMoveMode = this.mItemMoveMode;
        this.mComposedAdapterTag = obj;
        this.mRecyclerView.getParent().requestDisallowInterceptTouchEvent(true);
        startScrollOnDraggingProcess();
        this.mWrapperAdapter.startDraggingItem(this.mDraggingItemInfo, viewHolder, this.mDraggableRange, i, this.mCurrentItemMoveMode);
        this.mWrapperAdapter.onBindViewHolder(viewHolder, i);
        DraggingItemDecorator draggingItemDecorator = new DraggingItemDecorator(this.mRecyclerView, viewHolder, this.mRootDraggableRange);
        this.mDraggingItemDecorator = draggingItemDecorator;
        draggingItemDecorator.setShadowDrawable(this.mShadowDrawable);
        this.mDraggingItemDecorator.setupDraggingItemEffects(this.mDraggingItemEffectsInfo);
        this.mDraggingItemDecorator.start(this.mDraggingItemInfo, this.mLastTouchX, this.mLastTouchY);
        int layoutType = CustomRecyclerViewUtils.getLayoutType(this.mRecyclerView);
        if (!this.mCheckCanDrop && CustomRecyclerViewUtils.isLinearLayout(layoutType)) {
            SwapTargetItemOperator swapTargetItemOperator = new SwapTargetItemOperator(this.mRecyclerView, viewHolder, this.mDraggingItemInfo);
            this.mSwapTargetItemOperator = swapTargetItemOperator;
            swapTargetItemOperator.setSwapTargetTranslationInterpolator(this.mSwapTargetTranslationInterpolator);
            this.mSwapTargetItemOperator.start();
            this.mSwapTargetItemOperator.update(this.mDraggingItemDecorator.getDraggingItemTranslationX(), this.mDraggingItemDecorator.getDraggingItemTranslationY());
        }
        TopBottomEdgeEffectDecorator topBottomEdgeEffectDecorator = this.mEdgeEffectDecorator;
        if (topBottomEdgeEffectDecorator != null) {
            topBottomEdgeEffectDecorator.reorderToTop();
        }
        this.mWrapperAdapter.onDragItemStarted();
        OnItemDragEventListener onItemDragEventListener = this.mItemDragEventListener;
        if (onItemDragEventListener != null) {
            onItemDragEventListener.onItemDragStarted(this.mWrapperAdapter.getDraggingItemInitialPosition());
            this.mItemDragEventListener.onItemDragMoveDistanceUpdated(0, 0);
        }
    }

    public void cancelDrag() {
        cancelDrag(false);
    }

    void cancelDrag(boolean z) {
        handleActionUpOrCancel(3, false);
        if (z) {
            finishDragging(false);
        } else if (isDragging()) {
            this.mHandler.requestDeferredCancelDrag();
        }
    }

    private void finishDragging(boolean z) {
        int i;
        int i2;
        if (isDragging()) {
            InternalHandler internalHandler = this.mHandler;
            if (internalHandler != null) {
                internalHandler.removeDeferredCancelDragRequest();
                this.mHandler.removeDraggingItemViewSizeUpdateCheckRequest();
            }
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null && this.mDraggingItemViewHolder != null) {
                recyclerView.setOverScrollMode(this.mOrigOverScrollMode);
            }
            DraggingItemDecorator draggingItemDecorator = this.mDraggingItemDecorator;
            if (draggingItemDecorator != null) {
                draggingItemDecorator.setReturnToDefaultPositionAnimationDuration(this.mItemSettleBackIntoPlaceAnimationDuration);
                this.mDraggingItemDecorator.setReturnToDefaultPositionAnimationInterpolator(this.mItemSettleBackIntoPlaceAnimationInterpolator);
                this.mDraggingItemDecorator.finish(true);
            }
            SwapTargetItemOperator swapTargetItemOperator = this.mSwapTargetItemOperator;
            if (swapTargetItemOperator != null) {
                swapTargetItemOperator.setReturnToDefaultPositionAnimationDuration(this.mItemSettleBackIntoPlaceAnimationDuration);
                this.mDraggingItemDecorator.setReturnToDefaultPositionAnimationInterpolator(this.mItemSettleBackIntoPlaceAnimationInterpolator);
                this.mSwapTargetItemOperator.finish(true);
            }
            TopBottomEdgeEffectDecorator topBottomEdgeEffectDecorator = this.mEdgeEffectDecorator;
            if (topBottomEdgeEffectDecorator != null) {
                topBottomEdgeEffectDecorator.releaseBothGlows();
            }
            stopScrollOnDraggingProcess();
            RecyclerView recyclerView2 = this.mRecyclerView;
            if (recyclerView2 != null && recyclerView2.getParent() != null) {
                this.mRecyclerView.getParent().requestDisallowInterceptTouchEvent(false);
            }
            RecyclerView recyclerView3 = this.mRecyclerView;
            if (recyclerView3 != null) {
                recyclerView3.invalidate();
            }
            this.mDraggableRange = null;
            this.mRootDraggableRange = null;
            this.mDraggingItemDecorator = null;
            this.mSwapTargetItemOperator = null;
            this.mDraggingItemViewHolder = null;
            this.mDraggingItemInfo = null;
            this.mComposedAdapterTag = null;
            this.mNestedScrollView = null;
            this.mLastTouchX = 0;
            this.mLastTouchY = 0;
            this.mNestedScrollViewScrollX = 0;
            this.mNestedScrollViewScrollY = 0;
            this.mDragStartTouchX = 0;
            this.mDragStartTouchY = 0;
            this.mDragMinTouchX = 0;
            this.mDragMinTouchY = 0;
            this.mDragMaxTouchX = 0;
            this.mDragMaxTouchY = 0;
            this.mDragScrollDistanceX = 0;
            this.mDragScrollDistanceY = 0;
            this.mCanDragH = false;
            this.mCanDragV = false;
            DraggableItemWrapperAdapter draggableItemWrapperAdapter = this.mWrapperAdapter;
            if (draggableItemWrapperAdapter != null) {
                i = draggableItemWrapperAdapter.getDraggingItemInitialPosition();
                i2 = this.mWrapperAdapter.getDraggingItemCurrentPosition();
                this.mWrapperAdapter.onDragItemFinished(i, i2, z);
            } else {
                i = -1;
                i2 = -1;
            }
            OnItemDragEventListener onItemDragEventListener = this.mItemDragEventListener;
            if (onItemDragEventListener != null) {
                onItemDragEventListener.onItemDragFinished(i, i2, z);
            }
        }
    }

    private boolean handleActionUpOrCancel(int i, boolean z) {
        boolean z2 = i == 1;
        boolean isDragging = isDragging();
        InternalHandler internalHandler = this.mHandler;
        if (internalHandler != null) {
            internalHandler.cancelLongPressDetection();
        }
        this.mInitialTouchX = 0;
        this.mInitialTouchY = 0;
        this.mLastTouchX = 0;
        this.mLastTouchY = 0;
        this.mDragStartTouchX = 0;
        this.mDragStartTouchY = 0;
        this.mDragMinTouchX = 0;
        this.mDragMinTouchY = 0;
        this.mDragMaxTouchX = 0;
        this.mDragMaxTouchY = 0;
        this.mDragScrollDistanceX = 0;
        this.mDragScrollDistanceY = 0;
        this.mInitialTouchItemId = -1L;
        this.mCanDragH = false;
        this.mCanDragV = false;
        if (z && isDragging()) {
            finishDragging(z2);
        }
        return isDragging;
    }

    private boolean handleActionMoveWhileNotDragging(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mInitiateOnMove) {
            return checkConditionAndStartDragging(recyclerView, motionEvent, true);
        }
        return false;
    }

    private boolean checkConditionAndStartDragging(RecyclerView recyclerView, MotionEvent motionEvent, boolean z) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation;
        if (this.mDraggingItemInfo != null) {
            return false;
        }
        int x = (int) (motionEvent.getX() + 0.5f);
        int y = (int) (motionEvent.getY() + 0.5f);
        this.mLastTouchX = x;
        this.mLastTouchY = y;
        if (this.mInitialTouchItemId == -1) {
            return false;
        }
        if ((!z || ((this.mCanDragH && Math.abs(x - this.mInitialTouchX) > this.mTouchSlop) || (this.mCanDragV && Math.abs(y - this.mInitialTouchY) > this.mTouchSlop))) && (findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(recyclerView, this.mInitialTouchX, this.mInitialTouchY)) != null && canStartDrag(findChildViewHolderUnderWithoutTranslation, x, y)) {
            RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
            AdapterPath adapterPath = new AdapterPath();
            int unwrapPosition = WrapperAdapterUtils.unwrapPosition(adapter, this.mWrapperAdapter, null, findChildViewHolderUnderWithoutTranslation.getAdapterPosition(), adapterPath);
            ItemDraggableRange itemDraggableRange = this.mWrapperAdapter.getItemDraggableRange(findChildViewHolderUnderWithoutTranslation, unwrapPosition);
            if (itemDraggableRange == null) {
                itemDraggableRange = new ItemDraggableRange(0, Math.max(0, this.mWrapperAdapter.getItemCount() - 1));
            }
            ItemDraggableRange itemDraggableRange2 = itemDraggableRange;
            verifyItemDraggableRange(itemDraggableRange2, unwrapPosition);
            startDragging(recyclerView, motionEvent, findChildViewHolderUnderWithoutTranslation, itemDraggableRange2, adapterPath, unwrapPosition, adapterPath.lastSegment().tag);
            return true;
        }
        return false;
    }

    private boolean canStartDrag(RecyclerView.ViewHolder viewHolder, int i, int i2) {
        int adapterPosition = viewHolder.getAdapterPosition();
        int unwrapPosition = WrapperAdapterUtils.unwrapPosition(this.mRecyclerView.getAdapter(), this.mWrapperAdapter, (Object) null, adapterPosition);
        if (unwrapPosition == -1) {
            return false;
        }
        View view = viewHolder.itemView;
        int translationY = (int) (view.getTranslationY() + 0.5f);
        return this.mWrapperAdapter.canStartDrag(viewHolder, unwrapPosition, i - (view.getLeft() + ((int) (view.getTranslationX() + 0.5f))), i2 - (view.getTop() + translationY)) && viewHolder.getAdapterPosition() == adapterPosition;
    }

    private void verifyItemDraggableRange(ItemDraggableRange itemDraggableRange, int i) {
        int max = Math.max(0, this.mWrapperAdapter.getItemCount() - 1);
        if (itemDraggableRange.getStart() > itemDraggableRange.getEnd()) {
            throw new IllegalStateException("Invalid wrappedAdapterRange specified --- start > wrappedAdapterRange (wrappedAdapterRange = " + itemDraggableRange + ")");
        } else if (itemDraggableRange.getStart() < 0) {
            throw new IllegalStateException("Invalid wrappedAdapterRange specified --- start < 0 (wrappedAdapterRange = " + itemDraggableRange + ")");
        } else if (itemDraggableRange.getEnd() > max) {
            throw new IllegalStateException("Invalid wrappedAdapterRange specified --- end >= count (wrappedAdapterRange = " + itemDraggableRange + ")");
        } else if (itemDraggableRange.checkInRange(i)) {
        } else {
            throw new IllegalStateException("Invalid wrappedAdapterRange specified --- does not contain drag target item (wrappedAdapterRange = " + itemDraggableRange + ", position = " + i + ")");
        }
    }

    private void handleActionMoveWhileDragging(RecyclerView recyclerView, MotionEvent motionEvent) {
        this.mLastTouchX = (int) (motionEvent.getX() + 0.5f);
        this.mLastTouchY = (int) (motionEvent.getY() + 0.5f);
        NestedScrollView nestedScrollView = this.mNestedScrollView;
        this.mNestedScrollViewScrollX = nestedScrollView != null ? nestedScrollView.getScrollX() : 0;
        NestedScrollView nestedScrollView2 = this.mNestedScrollView;
        this.mNestedScrollViewScrollY = nestedScrollView2 != null ? nestedScrollView2.getScrollY() : 0;
        this.mDragMinTouchX = Math.min(this.mDragMinTouchX, this.mLastTouchX);
        this.mDragMinTouchY = Math.min(this.mDragMinTouchY, this.mLastTouchY);
        this.mDragMaxTouchX = Math.max(this.mDragMaxTouchX, this.mLastTouchX);
        this.mDragMaxTouchY = Math.max(this.mDragMaxTouchY, this.mLastTouchY);
        updateDragDirectionMask();
        if (this.mDraggingItemDecorator.update(getLastTouchX(), getLastTouchY(), false)) {
            SwapTargetItemOperator swapTargetItemOperator = this.mSwapTargetItemOperator;
            if (swapTargetItemOperator != null) {
                swapTargetItemOperator.update(this.mDraggingItemDecorator.getDraggingItemTranslationX(), this.mDraggingItemDecorator.getDraggingItemTranslationY());
            }
            checkItemSwapping(recyclerView);
            onItemMoveDistanceUpdated();
        }
    }

    private void updateDragDirectionMask() {
        int orientation = CustomRecyclerViewUtils.getOrientation(this.mRecyclerView);
        if (orientation == 0) {
            int lastTouchX = getLastTouchX();
            int i = this.mDragStartTouchX;
            int i2 = this.mDragMinTouchX;
            int i3 = i - i2;
            int i4 = this.mScrollTouchSlop;
            if (i3 > i4 || this.mDragMaxTouchX - lastTouchX > i4) {
                this.mScrollDirMask |= 4;
            }
            if (this.mDragMaxTouchX - i > i4 || lastTouchX - i2 > i4) {
                this.mScrollDirMask |= 8;
            }
        } else if (orientation != 1) {
        } else {
            int lastTouchY = getLastTouchY();
            int i5 = this.mDragStartTouchY;
            int i6 = this.mDragMinTouchY;
            int i7 = i5 - i6;
            int i8 = this.mScrollTouchSlop;
            if (i7 > i8 || this.mDragMaxTouchY - lastTouchY > i8) {
                this.mScrollDirMask = 1 | this.mScrollDirMask;
            }
            if (this.mDragMaxTouchY - i5 > i8 || lastTouchY - i6 > i8) {
                this.mScrollDirMask |= 2;
            }
        }
    }

    private int getLastTouchX() {
        int i = this.mLastTouchX;
        NestedScrollView nestedScrollView = this.mNestedScrollView;
        return nestedScrollView != null ? i + (nestedScrollView.getScrollX() - this.mNestedScrollViewScrollX) : i;
    }

    private int getLastTouchY() {
        int i = this.mLastTouchY;
        NestedScrollView nestedScrollView = this.mNestedScrollView;
        return nestedScrollView != null ? i + (nestedScrollView.getScrollY() - this.mNestedScrollViewScrollY) : i;
    }

    void checkItemSwapping(RecyclerView recyclerView) {
        RecyclerView.ViewHolder viewHolder = this.mDraggingItemViewHolder;
        FindSwapTargetContext findSwapTargetContext = this.mFindSwapTargetContext;
        findSwapTargetContext.setup(recyclerView, viewHolder, this.mDraggingItemInfo, getLastTouchX(), getLastTouchY(), this.mDraggableRange, this.mRootDraggableRange, this.mCheckCanDrop);
        int draggingItemInitialPosition = this.mWrapperAdapter.getDraggingItemInitialPosition();
        int draggingItemCurrentPosition = this.mWrapperAdapter.getDraggingItemCurrentPosition();
        boolean z = false;
        SwapTarget findSwapTargetItem = findSwapTargetItem(this.mTempSwapTarget, findSwapTargetContext, false);
        if (findSwapTargetItem.position != -1) {
            z = !this.mCheckCanDrop;
            if (!z) {
                z = this.mWrapperAdapter.canDropItems(draggingItemInitialPosition, findSwapTargetItem.position);
            }
            if (!z) {
                findSwapTargetItem = findSwapTargetItem(this.mTempSwapTarget, findSwapTargetContext, true);
                if (findSwapTargetItem.position != -1) {
                    z = this.mWrapperAdapter.canDropItems(draggingItemInitialPosition, findSwapTargetItem.position);
                }
            }
        }
        if (z && findSwapTargetItem.holder == null) {
            throw new IllegalStateException("bug check");
        }
        if (z) {
            swapItems(recyclerView, draggingItemCurrentPosition, viewHolder, findSwapTargetItem.holder);
        }
        SwapTargetItemOperator swapTargetItemOperator = this.mSwapTargetItemOperator;
        if (swapTargetItemOperator != null) {
            swapTargetItemOperator.setSwapTargetItem(z ? findSwapTargetItem.holder : null);
        }
        if (z) {
            this.mHandler.scheduleDraggingItemViewSizeUpdateCheck();
        }
        findSwapTargetItem.clear();
        findSwapTargetContext.clear();
    }

    private void onItemMoveDistanceUpdated() {
        if (this.mItemDragEventListener == null) {
            return;
        }
        this.mItemDragEventListener.onItemDragMoveDistanceUpdated(this.mDragScrollDistanceX + this.mDraggingItemDecorator.getDraggingItemMoveOffsetX(), this.mDragScrollDistanceY + this.mDraggingItemDecorator.getDraggingItemMoveOffsetY());
    }

    void handleScrollOnDragging() {
        RecyclerView recyclerView = this.mRecyclerView;
        int orientation = CustomRecyclerViewUtils.getOrientation(recyclerView);
        boolean z = true;
        if (orientation != 0) {
            if (orientation != 1) {
                return;
            }
            z = false;
        }
        if (this.mNestedScrollView != null) {
            handleScrollOnDraggingInternalWithNestedScrollView(recyclerView, z);
        } else {
            handleScrollOnDraggingInternalWithRecyclerView(recyclerView, z);
        }
    }

    private void handleScrollOnDraggingInternalWithNestedScrollView(androidx.recyclerview.widget.RecyclerView r8, boolean r9) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.handleScrollOnDraggingInternalWithNestedScrollView(androidx.recyclerview.widget.RecyclerView, boolean):void");
    }

    private void handleScrollOnDraggingInternalWithRecyclerView(androidx.recyclerview.widget.RecyclerView r18, boolean r19) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.handleScrollOnDraggingInternalWithRecyclerView(androidx.recyclerview.widget.RecyclerView, boolean):void");
    }

    private void updateEdgeEffect(float f) {
        if (f == 0.0f) {
            this.mEdgeEffectDecorator.releaseBothGlows();
        } else if (f < 0.0f) {
            this.mEdgeEffectDecorator.pullFirstEdge(f);
        } else {
            this.mEdgeEffectDecorator.pullSecondEdge(f);
        }
    }

    private static NestedScrollView findAncestorNestedScrollView(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof NestedScrollView) {
                return (NestedScrollView) parent;
            }
        }
        return null;
    }

    private static boolean offsetDescendantRectToAncestorCoords(View view, View view2, Rect rect) {
        ViewParent parent;
        do {
            parent = view.getParent();
            if (!(parent instanceof ViewGroup)) {
                return false;
            }
            ((ViewGroup) parent).offsetDescendantRectToMyCoords(view, rect);
            view = (View) parent;
        } while (parent != view2);
        return true;
    }

    private int scrollByYAndGetScrolledAmount(int i) {
        this.mActualScrollByYAmount = 0;
        this.mInScrollByMethod = true;
        this.mRecyclerView.scrollBy(0, i);
        this.mInScrollByMethod = false;
        return this.mActualScrollByYAmount;
    }

    private int scrollByXAndGetScrolledAmount(int i) {
        this.mActualScrollByXAmount = 0;
        this.mInScrollByMethod = true;
        this.mRecyclerView.scrollBy(i, 0);
        this.mInScrollByMethod = false;
        return this.mActualScrollByXAmount;
    }

    private void startScrollOnDraggingProcess() {
        this.mScrollOnDraggingProcess.start();
    }

    private void stopScrollOnDraggingProcess() {
        ScrollOnDraggingProcessRunnable scrollOnDraggingProcessRunnable = this.mScrollOnDraggingProcess;
        if (scrollOnDraggingProcessRunnable != null) {
            scrollOnDraggingProcessRunnable.stop();
        }
    }

    private void swapItems(RecyclerView recyclerView, int i, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Rect layoutMargins = CustomRecyclerViewUtils.getLayoutMargins(viewHolder2.itemView, this.mTmpRect1);
        int wrappedAdapterPosition = getWrappedAdapterPosition(viewHolder2);
        int abs = Math.abs(i - wrappedAdapterPosition);
        if (i == -1 || wrappedAdapterPosition == -1 || ItemIdComposer.extractWrappedIdPart(this.mWrapperAdapter.getItemId(i)) != ItemIdComposer.extractWrappedIdPart(this.mDraggingItemInfo.id)) {
            return;
        }
        boolean z = true;
        boolean z2 = CustomRecyclerViewUtils.isLinearLayout(CustomRecyclerViewUtils.getLayoutType(recyclerView)) && !this.mCheckCanDrop;
        if (abs == 0) {
            return;
        }
        if (abs == 1 && viewHolder != null && z2) {
            View view = viewHolder.itemView;
            View view2 = viewHolder2.itemView;
            Rect rect = this.mDraggingItemInfo.margins;
            if (this.mCanDragH) {
                int min = Math.min(view.getLeft() - rect.left, view2.getLeft() - layoutMargins.left);
                float max = min + ((Math.max(view.getRight() + rect.right, view2.getRight() + layoutMargins.right) - min) * 0.5f);
                float lastTouchX = (getLastTouchX() - this.mDraggingItemInfo.grabbedPositionX) + (this.mDraggingItemInfo.width * 0.5f);
                if (wrappedAdapterPosition >= i) {
                }
            }
            z = false;
            if (!z && this.mCanDragV) {
                int min2 = Math.min(view.getTop() - rect.top, view2.getTop() - layoutMargins.top);
                float max2 = min2 + ((Math.max(view.getBottom() + rect.bottom, view2.getBottom() + layoutMargins.bottom) - min2) * 0.5f);
                float lastTouchY = (getLastTouchY() - this.mDraggingItemInfo.grabbedPositionY) + (this.mDraggingItemInfo.height * 0.5f);
                if (wrappedAdapterPosition >= i) {
                }
            }
            if (!z) {
                return;
            }
        }
        performSwapItems(recyclerView, viewHolder, viewHolder2, layoutMargins, i, wrappedAdapterPosition);
    }

    private void performSwapItems(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2, Rect rect, int i, int i2) {
        int decoratedMeasuredWidth;
        int i3;
        OnItemDragEventListener onItemDragEventListener = this.mItemDragEventListener;
        if (onItemDragEventListener != null) {
            onItemDragEventListener.onItemDragPositionChanged(i, i2);
        }
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        int layoutType = CustomRecyclerViewUtils.getLayoutType(this.mRecyclerView);
        boolean z = CustomRecyclerViewUtils.extractOrientation(layoutType) == 1;
        int findFirstVisibleItemPosition = CustomRecyclerViewUtils.findFirstVisibleItemPosition(this.mRecyclerView, false);
        View view = viewHolder != null ? viewHolder.itemView : null;
        View view2 = viewHolder2.itemView;
        View findViewByPosition = CustomRecyclerViewUtils.findViewByPosition(layoutManager, findFirstVisibleItemPosition);
        int layoutPosition = viewHolder != null ? viewHolder.getLayoutPosition() : -1;
        int layoutPosition2 = viewHolder2.getLayoutPosition();
        Integer itemViewOrigin = getItemViewOrigin(view, z);
        Integer itemViewOrigin2 = getItemViewOrigin(view2, z);
        Integer itemViewOrigin3 = getItemViewOrigin(findViewByPosition, z);
        this.mWrapperAdapter.moveItem(i, i2, layoutType);
        if (findFirstVisibleItemPosition == layoutPosition && itemViewOrigin3 != null && itemViewOrigin2 != null) {
            scrollBySpecifiedOrientation(recyclerView, -(itemViewOrigin2.intValue() - itemViewOrigin3.intValue()), z);
            safeEndAnimations(recyclerView);
        } else if (findFirstVisibleItemPosition != layoutPosition2 || view == null || itemViewOrigin == null || itemViewOrigin.equals(itemViewOrigin2)) {
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (z) {
                decoratedMeasuredWidth = layoutManager.getDecoratedMeasuredHeight(view) + marginLayoutParams.topMargin;
                i3 = marginLayoutParams.bottomMargin;
            } else {
                decoratedMeasuredWidth = layoutManager.getDecoratedMeasuredWidth(view) + marginLayoutParams.leftMargin;
                i3 = marginLayoutParams.rightMargin;
            }
            scrollBySpecifiedOrientation(recyclerView, -(decoratedMeasuredWidth + i3), z);
            safeEndAnimations(recyclerView);
        }
    }

    private static void scrollBySpecifiedOrientation(RecyclerView recyclerView, int i, boolean z) {
        if (z) {
            recyclerView.scrollBy(0, i);
        } else {
            recyclerView.scrollBy(i, 0);
        }
    }

    private static Integer getItemViewOrigin(View view, boolean z) {
        if (view != null) {
            return Integer.valueOf(z ? view.getTop() : view.getLeft());
        }
        return null;
    }

    private boolean checkTouchedItemState(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            int wrappedAdapterPosition = getWrappedAdapterPosition(viewHolder);
            return wrappedAdapterPosition >= 0 && wrappedAdapterPosition < this.mWrapperAdapter.getItemCount();
        }
        return false;
    }

    private static void safeEndAnimation(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimation(viewHolder);
        }
    }

    private static void safeEndAnimations(RecyclerView recyclerView) {
        RecyclerView.ItemAnimator itemAnimator = recyclerView != null ? recyclerView.getItemAnimator() : null;
        if (itemAnimator != null) {
            itemAnimator.endAnimations();
        }
    }

    private void safeEndAnimationsIfRequired(RecyclerView recyclerView) {
        if (this.mSwapTargetItemOperator != null) {
            safeEndAnimations(recyclerView);
        }
    }

    private com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.SwapTarget findSwapTargetItem(com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.SwapTarget r9, com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.FindSwapTargetContext r10, boolean r11) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager.findSwapTargetItem(com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager$SwapTarget, com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager$FindSwapTargetContext, boolean):com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager$SwapTarget");
    }

    private static RecyclerView.ViewHolder findSwapTargetItemForGridLayoutManager(FindSwapTargetContext findSwapTargetContext, boolean z) {
        if (z) {
            return null;
        }
        RecyclerView.ViewHolder findSwapTargetItemForGridLayoutManagerInternal1 = findSwapTargetItemForGridLayoutManagerInternal1(findSwapTargetContext);
        return findSwapTargetItemForGridLayoutManagerInternal1 == null ? findSwapTargetItemForGridLayoutManagerInternal2(findSwapTargetContext) : findSwapTargetItemForGridLayoutManagerInternal1;
    }

    private static RecyclerView.ViewHolder findSwapTargetItemForStaggeredGridLayoutManager(FindSwapTargetContext findSwapTargetContext, boolean z) {
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation;
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation2;
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation3;
        if (z || findSwapTargetContext.draggingItem == null) {
            return null;
        }
        int i = findSwapTargetContext.overlayItemLeft + 1;
        int i2 = (findSwapTargetContext.overlayItemLeft + (findSwapTargetContext.draggingItemInfo.width / 2)) - 1;
        int i3 = (findSwapTargetContext.overlayItemLeft + findSwapTargetContext.draggingItemInfo.width) - 2;
        int i4 = findSwapTargetContext.overlayItemTop + 1;
        int i5 = (findSwapTargetContext.overlayItemTop + (findSwapTargetContext.draggingItemInfo.height / 2)) - 1;
        int i6 = (findSwapTargetContext.overlayItemTop + findSwapTargetContext.draggingItemInfo.height) - 2;
        if (findSwapTargetContext.vertical) {
            float f = i5;
            findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, i, f);
            findChildViewHolderUnderWithoutTranslation2 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, i3, f);
            findChildViewHolderUnderWithoutTranslation3 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, i2, f);
        } else {
            float f2 = i2;
            findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, f2, i4);
            findChildViewHolderUnderWithoutTranslation2 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, f2, i5);
            findChildViewHolderUnderWithoutTranslation3 = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, f2, i6);
        }
        if (findChildViewHolderUnderWithoutTranslation3 != findSwapTargetContext.draggingItem) {
            if (findChildViewHolderUnderWithoutTranslation3 == findChildViewHolderUnderWithoutTranslation || findChildViewHolderUnderWithoutTranslation3 == findChildViewHolderUnderWithoutTranslation2) {
                return findChildViewHolderUnderWithoutTranslation3;
            }
            return null;
        }
        return null;
    }

    private static RecyclerView.ViewHolder findSwapTargetItemForGridLayoutManagerInternal1(FindSwapTargetContext findSwapTargetContext) {
        return CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, findSwapTargetContext.lastTouchX, findSwapTargetContext.lastTouchY);
    }

    private static RecyclerView.ViewHolder findSwapTargetItemForGridLayoutManagerInternal2(FindSwapTargetContext findSwapTargetContext) {
        float f;
        float f2;
        int spanCount = CustomRecyclerViewUtils.getSpanCount(findSwapTargetContext.rv);
        int height = findSwapTargetContext.rv.getHeight();
        int width = findSwapTargetContext.rv.getWidth();
        int paddingLeft = findSwapTargetContext.vertical ? findSwapTargetContext.rv.getPaddingLeft() : 0;
        int paddingTop = !findSwapTargetContext.vertical ? findSwapTargetContext.rv.getPaddingTop() : 0;
        int paddingRight = ((width - paddingLeft) - (findSwapTargetContext.vertical ? findSwapTargetContext.rv.getPaddingRight() : 0)) / spanCount;
        int paddingBottom = ((height - paddingTop) - (!findSwapTargetContext.vertical ? findSwapTargetContext.rv.getPaddingBottom() : 0)) / spanCount;
        int i = findSwapTargetContext.lastTouchX;
        int i2 = findSwapTargetContext.lastTouchY;
        int start = findSwapTargetContext.rootAdapterRange.getStart();
        int end = findSwapTargetContext.rootAdapterRange.getEnd();
        if (findSwapTargetContext.vertical) {
            f = i - paddingLeft;
            f2 = paddingRight;
        } else {
            f = i2 - paddingTop;
            f2 = paddingBottom;
        }
        for (int min = Math.min(Math.max((int) (f / f2), 0), spanCount - 1); min >= 0; min--) {
            RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, findSwapTargetContext.vertical ? (paddingRight * min) + paddingLeft + (paddingRight / 2) : i, !findSwapTargetContext.vertical ? (paddingBottom * min) + paddingTop + (paddingBottom / 2) : i2);
            if (findChildViewHolderUnderWithoutTranslation != null) {
                int adapterPosition = findChildViewHolderUnderWithoutTranslation.getAdapterPosition();
                if (adapterPosition == -1 || adapterPosition < start || adapterPosition > end) {
                    return null;
                }
                return findChildViewHolderUnderWithoutTranslation;
            }
        }
        return null;
    }

    private static RecyclerView.ViewHolder findSwapTargetItemForLinearLayoutManager(FindSwapTargetContext findSwapTargetContext, boolean z) {
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        if (findSwapTargetContext.draggingItem == null) {
            return null;
        }
        if (!findSwapTargetContext.checkCanSwap && !z) {
            int adapterPosition = findSwapTargetContext.draggingItem.getAdapterPosition();
            int top = findSwapTargetContext.vertical ? findSwapTargetContext.draggingItem.itemView.getTop() : findSwapTargetContext.draggingItem.itemView.getLeft();
            int i = findSwapTargetContext.vertical ? findSwapTargetContext.overlayItemTop : findSwapTargetContext.overlayItemLeft;
            if (i < top) {
                if (adapterPosition <= 0) {
                    return null;
                }
                findViewHolderForAdapterPosition = findSwapTargetContext.rv.findViewHolderForAdapterPosition(adapterPosition - 1);
            } else if (i <= top || adapterPosition >= findSwapTargetContext.rv.getAdapter().getItemCount() - 1) {
                return null;
            } else {
                findViewHolderForAdapterPosition = findSwapTargetContext.rv.findViewHolderForAdapterPosition(adapterPosition + 1);
            }
            return findViewHolderForAdapterPosition;
        }
        float f = findSwapTargetContext.draggingItem.itemView.getResources().getDisplayMetrics().density * 8.0f;
        float min = Math.min(findSwapTargetContext.draggingItemInfo.width * 0.2f, f);
        float min2 = Math.min(findSwapTargetContext.draggingItemInfo.height * 0.2f, f);
        float f2 = findSwapTargetContext.overlayItemLeft + (findSwapTargetContext.draggingItemInfo.width * 0.5f);
        float f3 = findSwapTargetContext.overlayItemTop + (findSwapTargetContext.draggingItemInfo.height * 0.5f);
        RecyclerView.ViewHolder findChildViewHolderUnderWithoutTranslation = CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, f2 - min, f3 - min2);
        if (findChildViewHolderUnderWithoutTranslation == CustomRecyclerViewUtils.findChildViewHolderUnderWithoutTranslation(findSwapTargetContext.rv, f2 + min, f3 + min2)) {
            return findChildViewHolderUnderWithoutTranslation;
        }
        return null;
    }

    public void setDragStartItemAnimationDuration(int i) {
        this.mDraggingItemEffectsInfo.durationMillis = i;
    }

    public int getDragStartItemAnimationDuration() {
        return this.mDraggingItemEffectsInfo.durationMillis;
    }

    public void setDragStartItemScaleAnimationInterpolator(Interpolator interpolator) {
        this.mDraggingItemEffectsInfo.scaleInterpolator = interpolator;
    }

    public Interpolator getDragStartItemScaleAnimationInterpolator() {
        return this.mDraggingItemEffectsInfo.scaleInterpolator;
    }

    public void setDragStartItemRotationAnimationInterpolator(Interpolator interpolator) {
        this.mDraggingItemEffectsInfo.rotationInterpolator = interpolator;
    }

    public Interpolator getDragStartItemRotationAnimationInterpolator() {
        return this.mDraggingItemEffectsInfo.rotationInterpolator;
    }

    public void setDragStartItemAlphaAnimationInterpolator(Interpolator interpolator) {
        this.mDraggingItemEffectsInfo.alphaInterpolator = interpolator;
    }

    public Interpolator getDragStartItemAlphaAnimationInterpolator() {
        return this.mDraggingItemEffectsInfo.alphaInterpolator;
    }

    public void setDraggingItemScale(float f) {
        this.mDraggingItemEffectsInfo.scale = f;
    }

    public float getDraggingItemScale() {
        return this.mDraggingItemEffectsInfo.scale;
    }

    public void setDraggingItemRotation(float f) {
        this.mDraggingItemEffectsInfo.rotation = f;
    }

    public float getDraggingItemRotation() {
        return this.mDraggingItemEffectsInfo.rotation;
    }

    public void setDraggingItemAlpha(float f) {
        this.mDraggingItemEffectsInfo.alpha = f;
    }

    public float getDraggingItemAlpha() {
        return this.mDraggingItemEffectsInfo.alpha;
    }

    public void onItemViewRecycled(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == this.mDraggingItemViewHolder) {
            onDraggingItemViewRecycled();
            return;
        }
        SwapTargetItemOperator swapTargetItemOperator = this.mSwapTargetItemOperator;
        if (swapTargetItemOperator != null) {
            swapTargetItemOperator.onItemViewRecycled(viewHolder);
        }
    }

    public void onNewDraggingItemViewBound(RecyclerView.ViewHolder viewHolder) {
        if (this.mDraggingItemViewHolder != null) {
            onDraggingItemViewRecycled();
        }
        this.mDraggingItemViewHolder = viewHolder;
        this.mDraggingItemDecorator.setDraggingItemViewHolder(viewHolder);
    }

    private void onDraggingItemViewRecycled() {
        Log.i(TAG, "a view holder object which is bound to currently dragging item is recycled");
        this.mDraggingItemViewHolder = null;
        this.mDraggingItemDecorator.invalidateDraggingItem();
    }

    private int getWrappedAdapterPosition(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder == null) {
            return -1;
        }
        return WrapperAdapterUtils.unwrapPosition(this.mRecyclerView.getAdapter(), this.mWrapperAdapter, this.mComposedAdapterTag, viewHolder.getAdapterPosition());
    }

    private ItemDraggableRange convertToRootAdapterRange(AdapterPath adapterPath, ItemDraggableRange itemDraggableRange) {
        RecyclerView.Adapter adapter = this.mRecyclerView.getAdapter();
        return new ItemDraggableRange(WrapperAdapterUtils.wrapPosition(adapterPath, this.mWrapperAdapter, adapter, itemDraggableRange.getStart()), WrapperAdapterUtils.wrapPosition(adapterPath, this.mWrapperAdapter, adapter, itemDraggableRange.getEnd()));
    }

    public static class ScrollOnDraggingProcessRunnable implements Runnable {
        private final WeakReference<RecyclerViewDragDropManager> mHolderRef;
        private boolean mStarted;

        public void stop() {
            if (this.mStarted) {
                this.mStarted = false;
            }
        }

        public ScrollOnDraggingProcessRunnable(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.mHolderRef = new WeakReference<>(recyclerViewDragDropManager);
        }

        public void start() {
            RecyclerViewDragDropManager recyclerViewDragDropManager;
            RecyclerView recyclerView;
            if (this.mStarted || (recyclerViewDragDropManager = this.mHolderRef.get()) == null || (recyclerView = recyclerViewDragDropManager.getRecyclerView()) == null) {
                return;
            }
            ViewCompat.postOnAnimation(recyclerView, this);
            this.mStarted = true;
        }

        public void release() {
            this.mHolderRef.clear();
            this.mStarted = false;
        }

        @Override
        public void run() {
            RecyclerViewDragDropManager recyclerViewDragDropManager = this.mHolderRef.get();
            if (recyclerViewDragDropManager != null && this.mStarted) {
                recyclerViewDragDropManager.handleScrollOnDragging();
                RecyclerView recyclerView = recyclerViewDragDropManager.getRecyclerView();
                if (recyclerView == null || !this.mStarted) {
                    this.mStarted = false;
                } else {
                    ViewCompat.postOnAnimation(recyclerView, this);
                }
            }
        }
    }

    public static class InternalHandler extends Handler {
        private static final int MSG_CHECK_ITEM_VIEW_SIZE_UPDATE = 3;
        private static final int MSG_DEFERRED_CANCEL_DRAG = 2;
        private static final int MSG_LONGPRESS = 1;
        private MotionEvent mDownMotionEvent;
        private RecyclerViewDragDropManager mHolder;

        public InternalHandler(RecyclerViewDragDropManager recyclerViewDragDropManager) {
            this.mHolder = recyclerViewDragDropManager;
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
            } else if (i == 2) {
                this.mHolder.cancelDrag(true);
            } else if (i != 3) {
            } else {
                this.mHolder.handleOnCheckItemViewSizeUpdate();
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

        public void removeDeferredCancelDragRequest() {
            removeMessages(2);
        }

        public void requestDeferredCancelDrag() {
            if (isCancelDragRequested()) {
                return;
            }
            sendEmptyMessage(2);
        }

        public boolean isCancelDragRequested() {
            return hasMessages(2);
        }

        public void scheduleDraggingItemViewSizeUpdateCheck() {
            sendEmptyMessage(3);
        }

        public void removeDraggingItemViewSizeUpdateCheckRequest() {
            removeMessages(3);
        }
    }
}
