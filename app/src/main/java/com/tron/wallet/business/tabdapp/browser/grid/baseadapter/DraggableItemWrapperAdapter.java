package com.tron.wallet.business.tabdapp.browser.grid.baseadapter;

import android.util.Log;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.tron.wallet.business.tabdapp.browser.grid.base.CustomRecyclerViewUtils;
import com.tron.wallet.business.tabdapp.browser.grid.base.DraggableItemConstants;
import com.tron.wallet.business.tabdapp.browser.grid.base.DraggingItemInfo;
import com.tron.wallet.business.tabdapp.browser.grid.base.ItemDraggableRange;
import com.tron.wallet.business.tabdapp.browser.grid.base.RecyclerViewDragDropManager;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SimpleWrapperAdapter;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SwipeResultAction;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SwipeResultActionDefault;
import com.tron.wallet.business.tabdapp.browser.grid.swipeable.SwipeableItemAdapter;
import java.util.List;
public class DraggableItemWrapperAdapter<VH extends RecyclerView.ViewHolder> extends SimpleWrapperAdapter<VH> implements SwipeableItemAdapter<VH> {
    private static final boolean DEBUG_BYPASS_MOVE_OPERATION_MODE = false;
    private static final boolean LOCAL_LOGD = false;
    private static final boolean LOCAL_LOGI = true;
    private static final boolean LOCAL_LOGV = false;
    private static final int STATE_FLAG_INITIAL_VALUE = -1;
    private static final String TAG = "ARVDraggableWrapper";
    private boolean mCallingDragStarted;
    private RecyclerViewDragDropManager mDragDropManager;
    private DraggableItemAdapter mDraggableItemAdapter;
    private ItemDraggableRange mDraggableRange;
    private int mDraggingItemCurrentPosition;
    private DraggingItemInfo mDraggingItemInfo;
    private int mDraggingItemInitialPosition;
    private RecyclerView.ViewHolder mDraggingItemViewHolder;
    private int mItemMoveMode;

    private interface Constants extends DraggableItemConstants {
    }

    public int getDraggingItemCurrentPosition() {
        return this.mDraggingItemCurrentPosition;
    }

    public int getDraggingItemInitialPosition() {
        return this.mDraggingItemInitialPosition;
    }

    protected boolean isDragging() {
        return this.mDraggingItemInfo != null;
    }

    public DraggableItemWrapperAdapter(RecyclerViewDragDropManager recyclerViewDragDropManager, RecyclerView.Adapter<VH> adapter) {
        super(adapter);
        this.mDraggingItemInitialPosition = -1;
        this.mDraggingItemCurrentPosition = -1;
        if (recyclerViewDragDropManager == null) {
            throw new IllegalArgumentException("manager cannot be null");
        }
        this.mDragDropManager = recyclerViewDragDropManager;
    }

    @Override
    public void onRelease() {
        super.onRelease();
        this.mDraggingItemViewHolder = null;
        this.mDraggableItemAdapter = null;
        this.mDragDropManager = null;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        VH vh = (VH) super.onCreateViewHolder(viewGroup, i);
        if (vh instanceof DraggableItemViewHolder) {
            ((DraggableItemViewHolder) vh).setDragStateFlags(-1);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(VH vh, int i, List<Object> list) {
        if (isDragging()) {
            long j = this.mDraggingItemInfo.id;
            long itemId = vh.getItemId();
            int convertToOriginalPosition = convertToOriginalPosition(i, this.mDraggingItemInitialPosition, this.mDraggingItemCurrentPosition, this.mItemMoveMode);
            int i2 = (itemId > j ? 1 : (itemId == j ? 0 : -1));
            if (i2 == 0 && vh != this.mDraggingItemViewHolder) {
                Log.i(TAG, "a new view holder object for the currently dragging item is assigned");
                this.mDraggingItemViewHolder = vh;
                this.mDragDropManager.onNewDraggingItemViewBound(vh);
            }
            int i3 = i2 == 0 ? 3 : 1;
            if (this.mDraggableRange.checkInRange(i)) {
                i3 |= 4;
            }
            safeUpdateFlags(vh, i3);
            super.onBindViewHolder(vh, convertToOriginalPosition, list);
            return;
        }
        safeUpdateFlags(vh, 0);
        super.onBindViewHolder(vh, i, list);
    }

    @Override
    public long getItemId(int i) {
        if (isDragging()) {
            return super.getItemId(convertToOriginalPosition(i, this.mDraggingItemInitialPosition, this.mDraggingItemCurrentPosition, this.mItemMoveMode));
        }
        return super.getItemId(i);
    }

    @Override
    public int getItemViewType(int i) {
        if (isDragging()) {
            return super.getItemViewType(convertToOriginalPosition(i, this.mDraggingItemInitialPosition, this.mDraggingItemCurrentPosition, this.mItemMoveMode));
        }
        return super.getItemViewType(i);
    }

    protected static int convertToOriginalPosition(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i3 < 0) {
            return i;
        }
        if (i4 == 0) {
            return i2 != i3 ? (i >= i2 || i >= i3) ? (i <= i2 || i <= i3) ? i3 < i2 ? i == i3 ? i2 : i - 1 : i == i3 ? i2 : i + 1 : i : i : i;
        } else if (i4 == 1) {
            return i == i3 ? i2 : i == i2 ? i3 : i;
        } else {
            throw new IllegalStateException("unexpected state");
        }
    }

    @Override
    public void onHandleWrappedAdapterChanged() {
        if (shouldCancelDragOnDataUpdated()) {
            cancelDrag();
        } else {
            super.onHandleWrappedAdapterChanged();
        }
    }

    @Override
    public void onHandleWrappedAdapterItemRangeChanged(int i, int i2) {
        if (shouldCancelDragOnDataUpdated()) {
            cancelDrag();
        } else {
            super.onHandleWrappedAdapterItemRangeChanged(i, i2);
        }
    }

    @Override
    public void onHandleWrappedAdapterItemRangeInserted(int i, int i2) {
        if (shouldCancelDragOnDataUpdated()) {
            cancelDrag();
        } else {
            super.onHandleWrappedAdapterItemRangeInserted(i, i2);
        }
    }

    @Override
    public void onHandleWrappedAdapterItemRangeRemoved(int i, int i2) {
        if (shouldCancelDragOnDataUpdated()) {
            cancelDrag();
        } else {
            super.onHandleWrappedAdapterItemRangeRemoved(i, i2);
        }
    }

    @Override
    public void onHandleWrappedAdapterRangeMoved(int i, int i2, int i3) {
        if (shouldCancelDragOnDataUpdated()) {
            cancelDrag();
        } else {
            super.onHandleWrappedAdapterRangeMoved(i, i2, i3);
        }
    }

    private boolean shouldCancelDragOnDataUpdated() {
        return isDragging() && !this.mCallingDragStarted;
    }

    private void cancelDrag() {
        RecyclerViewDragDropManager recyclerViewDragDropManager = this.mDragDropManager;
        if (recyclerViewDragDropManager != null) {
            recyclerViewDragDropManager.cancelDrag();
        }
    }

    public void startDraggingItem(DraggingItemInfo draggingItemInfo, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange, int i, int i2) {
        if (viewHolder.getItemId() == -1) {
            throw new IllegalStateException("dragging target must provides valid ID");
        }
        DraggableItemAdapter draggableItemAdapter = (DraggableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(this, DraggableItemAdapter.class, i);
        this.mDraggableItemAdapter = draggableItemAdapter;
        if (draggableItemAdapter == null) {
            throw new IllegalStateException("DraggableItemAdapter not found!");
        }
        this.mDraggingItemCurrentPosition = i;
        this.mDraggingItemInitialPosition = i;
        this.mDraggingItemInfo = draggingItemInfo;
        this.mDraggingItemViewHolder = viewHolder;
        this.mDraggableRange = itemDraggableRange;
        this.mItemMoveMode = i2;
    }

    public void onDragItemStarted() {
        this.mCallingDragStarted = true;
        this.mDraggableItemAdapter.onItemDragStarted(getDraggingItemInitialPosition());
        this.mCallingDragStarted = false;
    }

    public void onDragItemFinished(int i, int i2, boolean z) {
        DraggableItemAdapter draggableItemAdapter = this.mDraggableItemAdapter;
        this.mDraggingItemInitialPosition = -1;
        this.mDraggingItemCurrentPosition = -1;
        this.mDraggableRange = null;
        this.mDraggingItemInfo = null;
        this.mDraggingItemViewHolder = null;
        this.mDraggableItemAdapter = null;
        if (z && i2 != i) {
            draggableItemAdapter.onMoveItem(i, i2);
        }
        draggableItemAdapter.onItemDragFinished(i, i2, z);
    }

    @Override
    public void onViewRecycled(VH vh, int i) {
        if (isDragging()) {
            this.mDragDropManager.onItemViewRecycled(vh);
            this.mDraggingItemViewHolder = this.mDragDropManager.getDraggingItemViewHolder();
        }
        super.onViewRecycled(vh, i);
    }

    public boolean canStartDrag(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3) {
        DraggableItemAdapter draggableItemAdapter = (DraggableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(this, DraggableItemAdapter.class, i);
        if (draggableItemAdapter == null) {
            return false;
        }
        return draggableItemAdapter.onCheckCanStartDrag(viewHolder, i, i2, i3);
    }

    public boolean canDropItems(int i, int i2) {
        return this.mDraggableItemAdapter.onCheckCanDrop(i, i2);
    }

    public ItemDraggableRange getItemDraggableRange(RecyclerView.ViewHolder viewHolder, int i) {
        DraggableItemAdapter draggableItemAdapter = (DraggableItemAdapter) WrapperAdapterUtils.findWrappedAdapter(this, DraggableItemAdapter.class, i);
        if (draggableItemAdapter == null) {
            return null;
        }
        return draggableItemAdapter.onGetItemDraggableRange(viewHolder, i);
    }

    public void moveItem(int i, int i2, int i3) {
        int convertToOriginalPosition = convertToOriginalPosition(i, this.mDraggingItemInitialPosition, this.mDraggingItemCurrentPosition, this.mItemMoveMode);
        if (convertToOriginalPosition != this.mDraggingItemInitialPosition) {
            throw new IllegalStateException("onMoveItem() - may be a bug or has duplicate IDs  --- mDraggingItemInitialPosition = " + this.mDraggingItemInitialPosition + ", mDraggingItemCurrentPosition = " + this.mDraggingItemCurrentPosition + ", origFromPosition = " + convertToOriginalPosition + ", fromPosition = " + i + ", toPosition = " + i2);
        }
        this.mDraggingItemCurrentPosition = i2;
        if (this.mItemMoveMode == 0 && CustomRecyclerViewUtils.isLinearLayout(i3)) {
            notifyItemMoved(i, i2);
        } else {
            notifyDataSetChanged();
        }
    }

    private static void safeUpdateFlags(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DraggableItemViewHolder) {
            DraggableItemViewHolder draggableItemViewHolder = (DraggableItemViewHolder) viewHolder;
            int dragStateFlags = draggableItemViewHolder.getDragStateFlags();
            if (dragStateFlags == -1 || ((dragStateFlags ^ i) & Integer.MAX_VALUE) != 0) {
                i |= Integer.MIN_VALUE;
            }
            draggableItemViewHolder.setDragStateFlags(i);
        }
    }

    private int getOriginalPosition(int i) {
        return isDragging() ? convertToOriginalPosition(i, this.mDraggingItemInitialPosition, this.mDraggingItemCurrentPosition, this.mItemMoveMode) : i;
    }

    @Override
    public int onGetSwipeReactionType(VH vh, int i, int i2, int i3) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (wrappedAdapter instanceof SwipeableItemAdapter) {
            return ((SwipeableItemAdapter) wrappedAdapter).onGetSwipeReactionType(vh, getOriginalPosition(i), i2, i3);
        }
        return 0;
    }

    @Override
    public void onSwipeItemStarted(VH vh, int i) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (wrappedAdapter instanceof SwipeableItemAdapter) {
            ((SwipeableItemAdapter) wrappedAdapter).onSwipeItemStarted(vh, getOriginalPosition(i));
        }
    }

    @Override
    public void onSetSwipeBackground(VH vh, int i, int i2) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (wrappedAdapter instanceof SwipeableItemAdapter) {
            ((SwipeableItemAdapter) wrappedAdapter).onSetSwipeBackground(vh, getOriginalPosition(i), i2);
        }
    }

    @Override
    public SwipeResultAction onSwipeItem(VH vh, int i, int i2) {
        RecyclerView.Adapter<VH> wrappedAdapter = getWrappedAdapter();
        if (!(wrappedAdapter instanceof SwipeableItemAdapter)) {
            return new SwipeResultActionDefault();
        }
        return ((SwipeableItemAdapter) wrappedAdapter).onSwipeItem(vh, getOriginalPosition(i), i2);
    }
}
