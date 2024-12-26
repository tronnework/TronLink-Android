package com.tron.wallet.business.tabdapp.browser.grid.draggable;
public class DraggableItemState {
    private int mFlags;

    public int getFlags() {
        return this.mFlags;
    }

    public boolean isActive() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isDragging() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isInRange() {
        return (this.mFlags & 4) != 0;
    }

    public boolean isUpdated() {
        return (this.mFlags & Integer.MIN_VALUE) != 0;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }
}
