package com.tron.wallet.common.components.ptr.indicator;

import android.graphics.PointF;
public class PtrIndicator {
    public static final int POS_START = 0;
    private int mFooterHeight;
    private int mHeaderHeight;
    private float mOffsetX;
    private float mOffsetY;
    protected int mOffsetToRefresh = 0;
    protected int mOffsetToLoadMore = 0;
    private PointF mPtLastMove = new PointF();
    private int mCurrentPos = 0;
    private int mLastPos = 0;
    private int mPressedPos = 0;
    private boolean isHeader = true;
    private float mRatioOfHeaderHeightToRefresh = 1.2f;
    private float mResistanceHeader = 1.7f;
    private float mResistanceFooter = 1.7f;
    private boolean mIsUnderTouch = false;
    private int mOffsetToKeepHeaderWhileLoading = -1;
    private int mRefreshCompleteY = 0;

    public float getCurrentPercent() {
        int i = this.mHeaderHeight;
        if (i == 0) {
            return 0.0f;
        }
        return (this.mCurrentPos * 1.0f) / i;
    }

    public int getCurrentPosY() {
        return this.mCurrentPos;
    }

    public int getHeaderHeight() {
        return this.mHeaderHeight;
    }

    public float getLastPercent() {
        int i = this.mHeaderHeight;
        if (i == 0) {
            return 0.0f;
        }
        return (this.mLastPos * 1.0f) / i;
    }

    public int getLastPosY() {
        return this.mLastPos;
    }

    public int getOffsetToKeepHeaderWhileLoading() {
        if (this.isHeader) {
            int i = this.mOffsetToKeepHeaderWhileLoading;
            return i >= 0 ? i : this.mHeaderHeight;
        }
        int i2 = this.mOffsetToKeepHeaderWhileLoading;
        return i2 >= 0 ? i2 : this.mFooterHeight;
    }

    public int getOffsetToLoadMore() {
        return this.mOffsetToLoadMore;
    }

    public int getOffsetToRefresh() {
        return this.mOffsetToRefresh;
    }

    public float getOffsetX() {
        return this.mOffsetX;
    }

    public float getOffsetY() {
        return this.mOffsetY;
    }

    public float getRatioOfHeaderToHeightRefresh() {
        return this.mRatioOfHeaderHeightToRefresh;
    }

    public float getResistanceFooter() {
        return this.mResistanceFooter;
    }

    public float getResistanceHeader() {
        return this.mResistanceHeader;
    }

    public boolean goDownCrossFinishPosition() {
        return this.mCurrentPos >= this.mRefreshCompleteY;
    }

    public boolean hasJustReachedHeaderHeightFromTopToBottom() {
        int i = this.mLastPos;
        int i2 = this.mHeaderHeight;
        return i < i2 && this.mCurrentPos >= i2;
    }

    public boolean hasLeftStartPosition() {
        return this.mCurrentPos > 0;
    }

    public boolean hasMovedAfterPressedDown() {
        return this.mCurrentPos != this.mPressedPos;
    }

    public boolean isAlreadyHere(int i) {
        return this.mCurrentPos == i;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public boolean isInStartPosition() {
        return this.mCurrentPos == 0;
    }

    public boolean isUnderTouch() {
        return this.mIsUnderTouch;
    }

    public void onRelease() {
        this.mIsUnderTouch = false;
    }

    public void onUIRefreshComplete() {
        this.mRefreshCompleteY = this.mCurrentPos;
    }

    protected void onUpdatePos(int i, int i2) {
    }

    public void setIsHeader(boolean z) {
        this.isHeader = z;
    }

    public void setOffset(float f, float f2) {
        this.mOffsetX = f;
        this.mOffsetY = f2;
    }

    public void setOffsetToKeepHeaderWhileLoading(int i) {
        this.mOffsetToKeepHeaderWhileLoading = i;
    }

    public void setOffsetToRefresh(int i) {
        this.mRatioOfHeaderHeightToRefresh = (this.mHeaderHeight * 1.0f) / i;
        this.mOffsetToRefresh = i;
        this.mOffsetToLoadMore = i;
    }

    public void setRatioOfHeaderHeightToRefresh(float f) {
        this.mRatioOfHeaderHeightToRefresh = f;
        this.mOffsetToRefresh = (int) (this.mHeaderHeight * f);
        this.mOffsetToLoadMore = (int) (this.mFooterHeight * f);
    }

    public void setResistanceFooter(float f) {
        this.mResistanceFooter = f;
    }

    public void setResistanceHeader(float f) {
        this.mResistanceHeader = f;
    }

    protected void updateHeight() {
        float f = this.mRatioOfHeaderHeightToRefresh;
        this.mOffsetToRefresh = (int) (this.mHeaderHeight * f);
        this.mOffsetToLoadMore = (int) (f * this.mFooterHeight);
    }

    public boolean willOverTop(int i) {
        return i < 0;
    }

    public void processOnMove(float f, float f2, float f3, float f4) {
        setOffset(f3, f4 / this.mResistanceHeader);
    }

    public void onPressDown(float f, float f2) {
        this.mIsUnderTouch = true;
        this.mPressedPos = this.mCurrentPos;
        this.mPtLastMove.set(f, f2);
    }

    public final void onMove(float f, float f2) {
        processOnMove(f, f2, f - this.mPtLastMove.x, f2 - this.mPtLastMove.y);
        this.mPtLastMove.set(f, f2);
    }

    public final void setCurrentPos(int i) {
        int i2 = this.mCurrentPos;
        this.mLastPos = i2;
        this.mCurrentPos = i;
        onUpdatePos(i, i2);
    }

    public void setHeaderHeight(int i) {
        this.mHeaderHeight = i;
        updateHeight();
    }

    public void setFooterHeight(int i) {
        this.mFooterHeight = i;
        updateHeight();
    }

    public void convertFrom(PtrIndicator ptrIndicator) {
        this.mCurrentPos = ptrIndicator.mCurrentPos;
        this.mLastPos = ptrIndicator.mLastPos;
        this.mHeaderHeight = ptrIndicator.mHeaderHeight;
    }

    public boolean hasJustLeftStartPosition() {
        return this.mLastPos == 0 && hasLeftStartPosition();
    }

    public boolean hasJustBackToStartPosition() {
        return this.mLastPos != 0 && isInStartPosition();
    }

    public boolean isOverOffsetToRefresh() {
        return this.mCurrentPos >= getOffsetToRefresh();
    }

    public boolean crossRefreshLineFromTopToBottom() {
        return this.mLastPos < getOffsetToRefresh() && this.mCurrentPos >= getOffsetToRefresh();
    }

    public boolean isOverOffsetToKeepHeaderWhileLoading() {
        return this.mCurrentPos > getOffsetToKeepHeaderWhileLoading();
    }
}
