package com.tron.wallet.common.components.bannerview.manager;

import com.tron.wallet.common.components.bannerview.utils.BannerUtils;
public class BannerOptions {
    public static final int DEFAULT_REVEAL_WIDTH = -1000;
    private int indicatorGravity;
    private int interval;
    private boolean isCanLoop;
    private IndicatorMargin mIndicatorMargin;
    private int roundRadius;
    private int scrollDuration;
    private int offScreenPageLimit = -1;
    private boolean isAutoPlay = false;
    private int pageStyle = 0;
    private float pageScale = 0.85f;
    private int mIndicatorVisibility = 0;
    private boolean userInputEnabled = true;
    private int orientation = 0;
    private int pageMargin = BannerUtils.dp2px(20.0f);
    private int rightRevealWidth = -1000;
    private int leftRevealWidth = -1000;

    public int getIndicatorGravity() {
        return this.indicatorGravity;
    }

    public IndicatorMargin getIndicatorMargin() {
        return this.mIndicatorMargin;
    }

    public int getIndicatorVisibility() {
        return this.mIndicatorVisibility;
    }

    public int getInterval() {
        return this.interval;
    }

    public int getLeftRevealWidth() {
        return this.leftRevealWidth;
    }

    public int getOffScreenPageLimit() {
        return this.offScreenPageLimit;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public int getPageMargin() {
        return this.pageMargin;
    }

    public float getPageScale() {
        return this.pageScale;
    }

    public int getPageStyle() {
        return this.pageStyle;
    }

    public int getRightRevealWidth() {
        return this.rightRevealWidth;
    }

    public int getRoundRectRadius() {
        return this.roundRadius;
    }

    public int getScrollDuration() {
        return this.scrollDuration;
    }

    public boolean isAutoPlay() {
        return this.isAutoPlay;
    }

    public boolean isCanLoop() {
        return this.isCanLoop;
    }

    public boolean isUserInputEnabled() {
        return this.userInputEnabled;
    }

    public void setAutoPlay(boolean z) {
        this.isAutoPlay = z;
    }

    public void setCanLoop(boolean z) {
        this.isCanLoop = z;
    }

    public void setIndicatorGravity(int i) {
        this.indicatorGravity = i;
    }

    public void setIndicatorVisibility(int i) {
        this.mIndicatorVisibility = i;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public void setLeftRevealWidth(int i) {
        this.leftRevealWidth = i;
    }

    public void setOffScreenPageLimit(int i) {
        this.offScreenPageLimit = i;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public void setPageMargin(int i) {
        this.pageMargin = i;
    }

    public void setPageScale(float f) {
        this.pageScale = f;
    }

    public void setPageStyle(int i) {
        this.pageStyle = i;
    }

    public void setRightRevealWidth(int i) {
        this.rightRevealWidth = i;
    }

    public void setRoundRectRadius(int i) {
        this.roundRadius = i;
    }

    public void setScrollDuration(int i) {
        this.scrollDuration = i;
    }

    public void setUserInputEnabled(boolean z) {
        this.userInputEnabled = z;
    }

    public void setIndicatorMargin(int i, int i2, int i3, int i4) {
        this.mIndicatorMargin = new IndicatorMargin(i, i2, i3, i4);
    }

    public static class IndicatorMargin {
        private int bottom;
        private int left;
        private int right;
        private int top;

        public int getBottom() {
            return this.bottom;
        }

        public int getLeft() {
            return this.left;
        }

        public int getRight() {
            return this.right;
        }

        public int getTop() {
            return this.top;
        }

        public IndicatorMargin(int i, int i2, int i3, int i4) {
            this.left = i;
            this.right = i3;
            this.top = i2;
            this.bottom = i4;
        }
    }
}
