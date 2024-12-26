package com.tron.wallet.business.tabdapp.browser.grid.swipeable;
public class SwipeableItemState {
    private int mFlags;

    public int getFlags() {
        return this.mFlags;
    }

    public boolean isActive() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isSwiping() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isUpdated() {
        return (this.mFlags & Integer.MIN_VALUE) != 0;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }
}
