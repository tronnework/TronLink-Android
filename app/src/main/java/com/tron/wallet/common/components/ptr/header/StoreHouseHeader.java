package com.tron.wallet.common.components.ptr.header;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import com.tron.wallet.common.components.ptr.PtrFrameLayout;
import com.tron.wallet.common.components.ptr.PtrUIHandler;
import com.tron.wallet.common.components.ptr.indicator.PtrIndicator;
import com.tron.wallet.common.components.ptr.util.PtrLocalDisplay;
import com.tron.wallet.common.utils.BigDecimalUtils;
import java.util.ArrayList;
public class StoreHouseHeader extends View implements PtrUIHandler {
    private AniController mAniController;
    private float mBarDarkAlpha;
    private int mDrawZoneHeight;
    private int mDrawZoneWidth;
    private int mDropHeight;
    private float mFromAlpha;
    private int mHorizontalRandomness;
    private float mInternalAnimationFactor;
    private boolean mIsInLoading;
    public ArrayList<StoreHouseBarItem> mItemList;
    private int mLineWidth;
    private int mLoadingAniDuration;
    private int mLoadingAniItemDuration;
    private int mLoadingAniSegDuration;
    private int mOffsetX;
    private int mOffsetY;
    private float mProgress;
    private float mScale;
    private int mTextColor;
    private float mToAlpha;
    private Transformation mTransformation;

    private void setProgress(float f) {
        this.mProgress = f;
    }

    public int getLoadingAniDuration() {
        return this.mLoadingAniDuration;
    }

    public float getScale() {
        return this.mScale;
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
    }

    public StoreHouseHeader setDropHeight(int i) {
        this.mDropHeight = i;
        return this;
    }

    public void setLoadingAniDuration(int i) {
        this.mLoadingAniDuration = i;
        this.mLoadingAniSegDuration = i;
    }

    public void setScale(float f) {
        this.mScale = f;
    }

    public StoreHouseHeader(Context context) {
        super(context);
        this.mItemList = new ArrayList<>();
        this.mLineWidth = -1;
        this.mScale = 1.0f;
        this.mDropHeight = -1;
        this.mInternalAnimationFactor = 0.7f;
        this.mHorizontalRandomness = -1;
        this.mProgress = 0.0f;
        this.mDrawZoneWidth = 0;
        this.mDrawZoneHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBarDarkAlpha = 0.4f;
        this.mFromAlpha = 1.0f;
        this.mToAlpha = 0.4f;
        this.mLoadingAniDuration = 1000;
        this.mLoadingAniSegDuration = 1000;
        this.mLoadingAniItemDuration = 400;
        this.mTransformation = new Transformation();
        this.mIsInLoading = false;
        this.mAniController = new AniController();
        this.mTextColor = -1;
        initView();
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItemList = new ArrayList<>();
        this.mLineWidth = -1;
        this.mScale = 1.0f;
        this.mDropHeight = -1;
        this.mInternalAnimationFactor = 0.7f;
        this.mHorizontalRandomness = -1;
        this.mProgress = 0.0f;
        this.mDrawZoneWidth = 0;
        this.mDrawZoneHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBarDarkAlpha = 0.4f;
        this.mFromAlpha = 1.0f;
        this.mToAlpha = 0.4f;
        this.mLoadingAniDuration = 1000;
        this.mLoadingAniSegDuration = 1000;
        this.mLoadingAniItemDuration = 400;
        this.mTransformation = new Transformation();
        this.mIsInLoading = false;
        this.mAniController = new AniController();
        this.mTextColor = -1;
        initView();
    }

    public StoreHouseHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mItemList = new ArrayList<>();
        this.mLineWidth = -1;
        this.mScale = 1.0f;
        this.mDropHeight = -1;
        this.mInternalAnimationFactor = 0.7f;
        this.mHorizontalRandomness = -1;
        this.mProgress = 0.0f;
        this.mDrawZoneWidth = 0;
        this.mDrawZoneHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBarDarkAlpha = 0.4f;
        this.mFromAlpha = 1.0f;
        this.mToAlpha = 0.4f;
        this.mLoadingAniDuration = 1000;
        this.mLoadingAniSegDuration = 1000;
        this.mLoadingAniItemDuration = 400;
        this.mTransformation = new Transformation();
        this.mIsInLoading = false;
        this.mAniController = new AniController();
        this.mTextColor = -1;
        initView();
    }

    private void initView() {
        PtrLocalDisplay.init(getContext());
        this.mLineWidth = PtrLocalDisplay.dp2px(1.0f);
        this.mDropHeight = PtrLocalDisplay.dp2px(40.0f);
        this.mHorizontalRandomness = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2;
    }

    public StoreHouseHeader setLineWidth(int i) {
        this.mLineWidth = i;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            this.mItemList.get(i2).setLineWidth(i);
        }
        return this;
    }

    public StoreHouseHeader setTextColor(int i) {
        this.mTextColor = i;
        for (int i2 = 0; i2 < this.mItemList.size(); i2++) {
            this.mItemList.get(i2).setColor(i);
        }
        return this;
    }

    @Override
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(getTopOffset() + this.mDrawZoneHeight + getBottomOffset(), MeasureSpec.AT_MOST));
        this.mOffsetX = (getMeasuredWidth() - this.mDrawZoneWidth) / 2;
        this.mOffsetY = getTopOffset();
        this.mDropHeight = getTopOffset();
    }

    private int getTopOffset() {
        return getPaddingTop() + PtrLocalDisplay.dp2px(10.0f);
    }

    private int getBottomOffset() {
        return getPaddingBottom() + PtrLocalDisplay.dp2px(10.0f);
    }

    public void initWithString(String str) {
        initWithString(str, 25);
    }

    public void initWithString(String str, int i) {
        initWithPointList(StoreHousePath.getPath(str, i * 0.01f, 14));
    }

    public void initWithStringArray(int i) {
        String[] stringArray = getResources().getStringArray(i);
        ArrayList<float[]> arrayList = new ArrayList<>();
        for (String str : stringArray) {
            String[] split = str.split(",");
            float[] fArr = new float[4];
            for (int i2 = 0; i2 < 4; i2++) {
                fArr[i2] = Float.parseFloat(split[i2]);
            }
            arrayList.add(fArr);
        }
        initWithPointList(arrayList);
    }

    public void initWithPointList(ArrayList<float[]> arrayList) {
        boolean z = this.mItemList.size() > 0;
        this.mItemList.clear();
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i = 0; i < arrayList.size(); i++) {
            float[] fArr = arrayList.get(i);
            PointF pointF = new PointF(PtrLocalDisplay.dp2px(fArr[0]) * this.mScale, PtrLocalDisplay.dp2px(fArr[1]) * this.mScale);
            PointF pointF2 = new PointF(PtrLocalDisplay.dp2px(fArr[2]) * this.mScale, PtrLocalDisplay.dp2px(fArr[3]) * this.mScale);
            f = Math.max(Math.max(f, pointF.x), pointF2.x);
            f2 = Math.max(Math.max(f2, pointF.y), pointF2.y);
            StoreHouseBarItem storeHouseBarItem = new StoreHouseBarItem(i, pointF, pointF2, this.mTextColor, this.mLineWidth);
            storeHouseBarItem.resetPosition(this.mHorizontalRandomness);
            this.mItemList.add(storeHouseBarItem);
        }
        this.mDrawZoneWidth = (int) Math.ceil(f);
        this.mDrawZoneHeight = (int) Math.ceil(f2);
        if (z) {
            requestLayout();
        }
    }

    private void beginLoading() {
        this.mIsInLoading = true;
        this.mAniController.start();
        invalidate();
    }

    private void loadFinish() {
        this.mIsInLoading = false;
        this.mAniController.stop();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.mProgress;
        int save = canvas.save();
        int size = this.mItemList.size();
        for (int i = 0; i < size; i++) {
            canvas.save();
            StoreHouseBarItem storeHouseBarItem = this.mItemList.get(i);
            float f2 = this.mOffsetX + storeHouseBarItem.midPoint.x;
            float f3 = this.mOffsetY + storeHouseBarItem.midPoint.y;
            if (this.mIsInLoading) {
                storeHouseBarItem.getTransformation(getDrawingTime(), this.mTransformation);
                canvas.translate(f2, f3);
            } else if (BigDecimalUtils.equalsZero((Number) Float.valueOf(f))) {
                storeHouseBarItem.resetPosition(this.mHorizontalRandomness);
            } else {
                float f4 = this.mInternalAnimationFactor;
                float f5 = ((1.0f - f4) * i) / size;
                float f6 = (1.0f - f4) - f5;
                if (f == 1.0f || f >= 1.0f - f6) {
                    canvas.translate(f2, f3);
                    storeHouseBarItem.setAlpha(this.mBarDarkAlpha);
                } else {
                    float min = f <= f5 ? 0.0f : Math.min(1.0f, (f - f5) / f4);
                    float f7 = 1.0f - min;
                    float f8 = f2 + (storeHouseBarItem.translationX * f7);
                    float f9 = f3 + ((-this.mDropHeight) * f7);
                    Matrix matrix = new Matrix();
                    matrix.postRotate(360.0f * min);
                    matrix.postScale(min, min);
                    matrix.postTranslate(f8, f9);
                    storeHouseBarItem.setAlpha(this.mBarDarkAlpha * min);
                    canvas.concat(matrix);
                }
            }
            storeHouseBarItem.draw(canvas);
            canvas.restore();
        }
        if (this.mIsInLoading) {
            invalidate();
        }
        canvas.restoreToCount(save);
    }

    @Override
    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        loadFinish();
        for (int i = 0; i < this.mItemList.size(); i++) {
            this.mItemList.get(i).resetPosition(this.mHorizontalRandomness);
        }
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        beginLoading();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout, boolean z) {
        loadFinish();
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        setProgress(Math.min(1.0f, ptrIndicator.getCurrentPercent()));
        invalidate();
    }

    public class AniController implements Runnable {
        private int mCountPerSeg;
        private int mInterval;
        private boolean mRunning;
        private int mSegCount;
        private int mTick;

        private AniController() {
            this.mTick = 0;
            this.mCountPerSeg = 0;
            this.mSegCount = 0;
            this.mInterval = 0;
            this.mRunning = true;
        }

        public void start() {
            this.mRunning = true;
            this.mTick = 0;
            this.mInterval = mLoadingAniDuration / mItemList.size();
            this.mCountPerSeg = mLoadingAniSegDuration / this.mInterval;
            this.mSegCount = (mItemList.size() / this.mCountPerSeg) + 1;
            run();
        }

        @Override
        public void run() {
            int i = this.mTick % this.mCountPerSeg;
            for (int i2 = 0; i2 < this.mSegCount; i2++) {
                int i3 = (this.mCountPerSeg * i2) + i;
                if (i3 <= this.mTick) {
                    StoreHouseBarItem storeHouseBarItem = mItemList.get(i3 % mItemList.size());
                    storeHouseBarItem.setFillAfter(false);
                    storeHouseBarItem.setFillEnabled(true);
                    storeHouseBarItem.setFillBefore(false);
                    storeHouseBarItem.setDuration(mLoadingAniItemDuration);
                    storeHouseBarItem.start(mFromAlpha, mToAlpha);
                }
            }
            this.mTick++;
            if (this.mRunning) {
                postDelayed(this, this.mInterval);
            }
        }

        public void stop() {
            this.mRunning = false;
            removeCallbacks(this);
        }
    }
}
