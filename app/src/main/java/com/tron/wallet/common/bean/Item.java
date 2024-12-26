package com.tron.wallet.common.bean;
public class Item {
    public boolean isSelected;
    public int res;
    public String title;

    public Item(int i, String str, boolean z) {
        this.res = i;
        this.title = str;
        this.isSelected = z;
    }

    public Item() {
    }
}
