package com.tron.wallet.business.tabdapp.browser.grid.draggable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.tron.wallet.business.tabdapp.browser.grid.base.CustomRecyclerViewUtils;
import com.tron.wallet.business.tabdapp.browser.grid.base.DraggingItemEffectsInfo;
import com.tron.wallet.business.tabdapp.browser.grid.base.DraggingItemInfo;
import com.tron.wallet.business.tabdapp.browser.grid.base.ItemDraggableRange;
public class DraggingItemDecorator extends BaseDraggableItemDecorator {
    private static final String TAG = "DraggingItemDecorator";
    private Interpolator mAlphaInterpolator;
    private Bitmap mDraggingItemImage;
    private DraggingItemInfo mDraggingItemInfo;
    private float mInitialDraggingItemScaleX;
    private float mInitialDraggingItemScaleY;
    private boolean mIsScrolling;
    private float mLastDraggingItemAlpha;
    private float mLastDraggingItemRotation;
    private float mLastDraggingItemScaleX;
    private float mLastDraggingItemScaleY;
    private int mLayoutOrientation;
    private int mLayoutType;
    private Paint mPaint;
    private ItemDraggableRange mRange;
    private Interpolator mRotationInterpolator;
    private Interpolator mScaleInterpolator;
    private NinePatchDrawable mShadowDrawable;
    private final Rect mShadowPadding;
    private long mStartAnimationDurationMillis;
    private long mStartMillis;
    private boolean mStarted;
    private float mTargetDraggingItemAlpha;
    private float mTargetDraggingItemRotation;
    private float mTargetDraggingItemScale;
    private int mTouchPositionX;
    private int mTouchPositionY;
    private int mTranslationBottomLimit;
    private int mTranslationLeftLimit;
    private int mTranslationRightLimit;
    private int mTranslationTopLimit;
    private int mTranslationX;
    private int mTranslationY;

    public int getDraggingItemTranslationX() {
        return this.mTranslationX;
    }

    public int getDraggingItemTranslationY() {
        return this.mTranslationY;
    }

    public int getTranslatedItemPositionLeft() {
        return this.mTranslationX;
    }

    public int getTranslatedItemPositionTop() {
        return this.mTranslationY;
    }

    public boolean isReachedToBottomLimit() {
        return this.mTranslationY == this.mTranslationBottomLimit;
    }

    public boolean isReachedToLeftLimit() {
        return this.mTranslationX == this.mTranslationLeftLimit;
    }

    public boolean isReachedToRightLimit() {
        return this.mTranslationX == this.mTranslationRightLimit;
    }

    public boolean isReachedToTopLimit() {
        return this.mTranslationY == this.mTranslationTopLimit;
    }

    public void setIsScrolling(boolean z) {
        if (this.mIsScrolling == z) {
            return;
        }
        this.mIsScrolling = z;
    }

    @Override
    public void setReturnToDefaultPositionAnimationDuration(int i) {
        super.setReturnToDefaultPositionAnimationDuration(i);
    }

    @Override
    public void setReturnToDefaultPositionAnimationInterpolator(Interpolator interpolator) {
        super.setReturnToDefaultPositionAnimationInterpolator(interpolator);
    }

    public DraggingItemDecorator(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, ItemDraggableRange itemDraggableRange) {
        super(recyclerView, viewHolder);
        this.mShadowPadding = new Rect();
        this.mStartAnimationDurationMillis = 0L;
        this.mTargetDraggingItemScale = 1.0f;
        this.mTargetDraggingItemRotation = 0.0f;
        this.mTargetDraggingItemAlpha = 1.0f;
        this.mScaleInterpolator = null;
        this.mRotationInterpolator = null;
        this.mAlphaInterpolator = null;
        this.mRange = itemDraggableRange;
        this.mPaint = new Paint();
    }

    private static int clip(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private static View findRangeFirstItem(RecyclerView recyclerView, ItemDraggableRange itemDraggableRange, int i, int i2) {
        int layoutPosition;
        if (i == -1 || i2 == -1) {
            return null;
        }
        int childCount = recyclerView.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = recyclerView.getChildAt(i3);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            if (childViewHolder != null && (layoutPosition = childViewHolder.getLayoutPosition()) >= i && layoutPosition <= i2 && itemDraggableRange.checkInRange(layoutPosition)) {
                return childAt;
            }
        }
        return null;
    }

    private static View findRangeLastItem(RecyclerView recyclerView, ItemDraggableRange itemDraggableRange, int i, int i2) {
        int layoutPosition;
        if (i == -1 || i2 == -1) {
            return null;
        }
        for (int childCount = recyclerView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = recyclerView.getChildAt(childCount);
            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(childAt);
            if (childViewHolder != null && (layoutPosition = childViewHolder.getLayoutPosition()) >= i && layoutPosition <= i2 && itemDraggableRange.checkInRange(layoutPosition)) {
                return childAt;
            }
        }
        return null;
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        if (this.mDraggingItemImage == null) {
            return;
        }
        int min = (int) Math.min(System.currentTimeMillis() - this.mStartMillis, this.mStartAnimationDurationMillis);
        long j = this.mStartAnimationDurationMillis;
        float f = j > 0 ? min / ((float) j) : 1.0f;
        float interpolation = getInterpolation(this.mScaleInterpolator, f);
        float f2 = this.mTargetDraggingItemScale;
        float f3 = this.mInitialDraggingItemScaleX;
        float f4 = ((f2 - f3) * interpolation) + f3;
        float f5 = this.mInitialDraggingItemScaleY;
        float f6 = (interpolation * (f2 - f5)) + f5;
        float interpolation2 = (getInterpolation(this.mAlphaInterpolator, f) * (this.mTargetDraggingItemAlpha - 1.0f)) + 1.0f;
        float interpolation3 = getInterpolation(this.mRotationInterpolator, f) * this.mTargetDraggingItemRotation;
        if (f4 > 0.0f && f6 > 0.0f && interpolation2 > 0.0f) {
            this.mPaint.setAlpha((int) (255.0f * interpolation2));
            int save = canvas.save();
            canvas.translate(this.mTranslationX + this.mDraggingItemInfo.grabbedPositionX, this.mTranslationY + this.mDraggingItemInfo.grabbedPositionY);
            canvas.scale(f4, f6);
            canvas.rotate(interpolation3);
            canvas.translate(-(this.mShadowPadding.left + this.mDraggingItemInfo.grabbedPositionX), -(this.mShadowPadding.top + this.mDraggingItemInfo.grabbedPositionY));
            canvas.drawBitmap(this.mDraggingItemImage, 0.0f, 0.0f, this.mPaint);
            canvas.restoreToCount(save);
        }
        if (f < 1.0f) {
            ViewCompat.postInvalidateOnAnimation(this.mRecyclerView);
        }
        this.mLastDraggingItemScaleX = f4;
        this.mLastDraggingItemScaleY = f6;
        this.mLastDraggingItemRotation = interpolation3;
        this.mLastDraggingItemAlpha = interpolation2;
    }

    public void setupDraggingItemEffects(DraggingItemEffectsInfo draggingItemEffectsInfo) {
        this.mStartAnimationDurationMillis = draggingItemEffectsInfo.durationMillis;
        this.mTargetDraggingItemScale = draggingItemEffectsInfo.scale;
        this.mScaleInterpolator = draggingItemEffectsInfo.scaleInterpolator;
        this.mTargetDraggingItemRotation = draggingItemEffectsInfo.rotation;
        this.mRotationInterpolator = draggingItemEffectsInfo.rotationInterpolator;
        this.mTargetDraggingItemAlpha = draggingItemEffectsInfo.alpha;
        this.mAlphaInterpolator = draggingItemEffectsInfo.alphaInterpolator;
    }

    public void start(DraggingItemInfo draggingItemInfo, int i, int i2) {
        if (this.mStarted) {
            return;
        }
        View view = this.mDraggingItemViewHolder.itemView;
        this.mDraggingItemInfo = draggingItemInfo;
        this.mDraggingItemImage = createDraggingItemImage(view, this.mShadowDrawable);
        this.mTranslationLeftLimit = this.mRecyclerView.getPaddingLeft();
        this.mTranslationTopLimit = this.mRecyclerView.getPaddingTop();
        this.mLayoutOrientation = CustomRecyclerViewUtils.getOrientation(this.mRecyclerView);
        this.mLayoutType = CustomRecyclerViewUtils.getLayoutType(this.mRecyclerView);
        this.mInitialDraggingItemScaleX = view.getScaleX();
        this.mInitialDraggingItemScaleY = view.getScaleY();
        this.mLastDraggingItemScaleX = 1.0f;
        this.mLastDraggingItemScaleY = 1.0f;
        this.mLastDraggingItemRotation = 0.0f;
        this.mLastDraggingItemAlpha = 1.0f;
        view.setVisibility(View.INVISIBLE);
        update(i, i2, true);
        this.mRecyclerView.addItemDecoration(this);
        this.mStartMillis = System.currentTimeMillis();
        this.mStarted = true;
    }

    public void updateDraggingItemView(DraggingItemInfo draggingItemInfo, RecyclerView.ViewHolder viewHolder) {
        if (this.mStarted) {
            if (this.mDraggingItemViewHolder != viewHolder) {
                invalidateDraggingItem();
                this.mDraggingItemViewHolder = viewHolder;
            }
            this.mDraggingItemImage = createDraggingItemImage(viewHolder.itemView, this.mShadowDrawable);
            this.mDraggingItemInfo = draggingItemInfo;
            refresh(true);
        }
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
        updateDraggingItemPosition(this.mTranslationX, this.mTranslationY);
        if (this.mDraggingItemViewHolder != null) {
            moveToDefaultPosition(this.mDraggingItemViewHolder.itemView, this.mLastDraggingItemScaleX, this.mLastDraggingItemScaleY, this.mLastDraggingItemRotation, this.mLastDraggingItemAlpha, z);
        }
        if (this.mDraggingItemViewHolder != null) {
            this.mDraggingItemViewHolder.itemView.setVisibility(View.VISIBLE);
        }
        this.mDraggingItemViewHolder = null;
        Bitmap bitmap = this.mDraggingItemImage;
        if (bitmap != null) {
            bitmap.recycle();
            this.mDraggingItemImage = null;
        }
        this.mRange = null;
        this.mTranslationX = 0;
        this.mTranslationY = 0;
        this.mTranslationLeftLimit = 0;
        this.mTranslationRightLimit = 0;
        this.mTranslationTopLimit = 0;
        this.mTranslationBottomLimit = 0;
        this.mTouchPositionX = 0;
        this.mTouchPositionY = 0;
        this.mStarted = false;
    }

    public boolean update(int i, int i2, boolean z) {
        this.mTouchPositionX = i;
        this.mTouchPositionY = i2;
        return refresh(z);
    }

    public boolean refresh(boolean z) {
        int i = this.mTranslationX;
        int i2 = this.mTranslationY;
        updateTranslationOffset();
        int i3 = this.mTranslationX;
        boolean z2 = (i == i3 && i2 == this.mTranslationY) ? false : true;
        if (z2 || z) {
            updateDraggingItemPosition(i3, this.mTranslationY);
            ViewCompat.postInvalidateOnAnimation(this.mRecyclerView);
        }
        return z2;
    }

    public void setShadowDrawable(NinePatchDrawable ninePatchDrawable) {
        this.mShadowDrawable = ninePatchDrawable;
        if (ninePatchDrawable != null) {
            ninePatchDrawable.getPadding(this.mShadowPadding);
        }
    }

    public int getDraggingItemMoveOffsetY() {
        return this.mTranslationY - this.mDraggingItemInfo.initialItemTop;
    }

    public int getDraggingItemMoveOffsetX() {
        return this.mTranslationX - this.mDraggingItemInfo.initialItemLeft;
    }

    private void updateTranslationOffset() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView.getChildCount() > 0) {
            this.mTranslationLeftLimit = 0;
            this.mTranslationRightLimit = recyclerView.getWidth() - this.mDraggingItemInfo.width;
            this.mTranslationTopLimit = 0;
            this.mTranslationBottomLimit = recyclerView.getHeight() - this.mDraggingItemInfo.height;
            int i = this.mLayoutOrientation;
            if (i == 0) {
                this.mTranslationTopLimit += recyclerView.getPaddingTop();
                this.mTranslationBottomLimit -= recyclerView.getPaddingBottom();
                this.mTranslationLeftLimit = -this.mDraggingItemInfo.width;
                this.mTranslationRightLimit = recyclerView.getWidth();
            } else if (i == 1) {
                this.mTranslationTopLimit = -this.mDraggingItemInfo.height;
                this.mTranslationBottomLimit = recyclerView.getHeight();
                this.mTranslationLeftLimit += recyclerView.getPaddingLeft();
                this.mTranslationRightLimit -= recyclerView.getPaddingRight();
            }
            this.mTranslationRightLimit = Math.max(this.mTranslationLeftLimit, this.mTranslationRightLimit);
            this.mTranslationBottomLimit = Math.max(this.mTranslationTopLimit, this.mTranslationBottomLimit);
            if (!this.mIsScrolling) {
                int findFirstVisibleItemPosition = CustomRecyclerViewUtils.findFirstVisibleItemPosition(recyclerView, true);
                int findLastVisibleItemPosition = CustomRecyclerViewUtils.findLastVisibleItemPosition(recyclerView, true);
                View findRangeFirstItem = findRangeFirstItem(recyclerView, this.mRange, findFirstVisibleItemPosition, findLastVisibleItemPosition);
                View findRangeLastItem = findRangeLastItem(recyclerView, this.mRange, findFirstVisibleItemPosition, findLastVisibleItemPosition);
                int i2 = this.mLayoutOrientation;
                if (i2 == 0) {
                    if (findRangeFirstItem != null) {
                        this.mTranslationLeftLimit = Math.min(this.mTranslationLeftLimit, findRangeFirstItem.getLeft());
                    }
                    if (findRangeLastItem != null) {
                        this.mTranslationRightLimit = Math.min(this.mTranslationRightLimit, Math.max(0, findRangeLastItem.getRight() - this.mDraggingItemInfo.width));
                    }
                } else if (i2 == 1) {
                    if (findRangeFirstItem != null) {
                        this.mTranslationTopLimit = Math.min(this.mTranslationBottomLimit, findRangeFirstItem.getTop());
                    }
                    if (findRangeLastItem != null) {
                        this.mTranslationBottomLimit = Math.min(this.mTranslationBottomLimit, Math.max(0, findRangeLastItem.getBottom() - this.mDraggingItemInfo.height));
                    }
                }
            }
        } else {
            int paddingLeft = recyclerView.getPaddingLeft();
            this.mTranslationLeftLimit = paddingLeft;
            this.mTranslationRightLimit = paddingLeft;
            int paddingTop = recyclerView.getPaddingTop();
            this.mTranslationTopLimit = paddingTop;
            this.mTranslationBottomLimit = paddingTop;
        }
        this.mTranslationX = this.mTouchPositionX - this.mDraggingItemInfo.grabbedPositionX;
        this.mTranslationY = this.mTouchPositionY - this.mDraggingItemInfo.grabbedPositionY;
        if (CustomRecyclerViewUtils.isLinearLayout(this.mLayoutType)) {
            this.mTranslationX = clip(this.mTranslationX, this.mTranslationLeftLimit, this.mTranslationRightLimit);
            this.mTranslationY = clip(this.mTranslationY, this.mTranslationTopLimit, this.mTranslationBottomLimit);
        }
    }

    private static int toSpanAlignedPosition(int i, int i2) {
        if (i == -1) {
            return -1;
        }
        return (i / i2) * i2;
    }

    private Bitmap createDraggingItemImage(View view, NinePatchDrawable ninePatchDrawable) {
        int top = view.getTop();
        int left = view.getLeft();
        int width = view.getWidth();
        int height = view.getHeight();
        int i = this.mShadowPadding.left + width + this.mShadowPadding.right;
        int i2 = this.mShadowPadding.top + height + this.mShadowPadding.bottom;
        view.measure(View.MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));
        view.layout(left, top, width + left, height + top);
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        if (ninePatchDrawable != null) {
            ninePatchDrawable.setBounds(0, 0, i, i2);
            ninePatchDrawable.draw(canvas);
        }
        int save = canvas.save();
        canvas.clipRect(this.mShadowPadding.left, this.mShadowPadding.top, i - this.mShadowPadding.right, i2 - this.mShadowPadding.bottom);
        canvas.translate(this.mShadowPadding.left, this.mShadowPadding.top);
        view.draw(canvas);
        canvas.restoreToCount(save);
        return createBitmap;
    }

    private void updateDraggingItemPosition(float f, int i) {
        if (this.mDraggingItemViewHolder != null) {
            setItemTranslation(this.mRecyclerView, this.mDraggingItemViewHolder, f - this.mDraggingItemViewHolder.itemView.getLeft(), i - this.mDraggingItemViewHolder.itemView.getTop());
        }
    }

    public int getTranslatedItemPositionBottom() {
        return this.mTranslationY + this.mDraggingItemInfo.height;
    }

    public int getTranslatedItemPositionRight() {
        return this.mTranslationX + this.mDraggingItemInfo.width;
    }

    public void invalidateDraggingItem() {
        if (this.mDraggingItemViewHolder != null) {
            this.mDraggingItemViewHolder.itemView.setTranslationX(0.0f);
            this.mDraggingItemViewHolder.itemView.setTranslationY(0.0f);
            this.mDraggingItemViewHolder.itemView.setVisibility(View.VISIBLE);
        }
        this.mDraggingItemViewHolder = null;
    }

    public void setDraggingItemViewHolder(RecyclerView.ViewHolder viewHolder) {
        if (this.mDraggingItemViewHolder != null) {
            throw new IllegalStateException("A new view holder is attempt to be assigned before invalidating the older one");
        }
        this.mDraggingItemViewHolder = viewHolder;
        viewHolder.itemView.setVisibility(View.INVISIBLE);
    }

    private static float getInterpolation(Interpolator interpolator, float f) {
        return interpolator != null ? interpolator.getInterpolation(f) : f;
    }
}
