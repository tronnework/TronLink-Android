package com.tron.wallet.business.tabdapp.browser.grid.adapter;
public abstract class AbstractDataProvider {

    public static abstract class Data {
        public abstract long getId();

        public abstract String getText();

        public abstract int getViewType();

        public abstract boolean isPinned();

        public abstract boolean isSectionHeader();

        public abstract void setPinned(boolean z);
    }

    public abstract int getCount();

    public abstract Data getItem(int i);

    public abstract void moveItem(int i, int i2);

    public abstract void removeItem(int i);

    public abstract void swapItem(int i, int i2);

    public abstract int undoLastRemoval();
}
