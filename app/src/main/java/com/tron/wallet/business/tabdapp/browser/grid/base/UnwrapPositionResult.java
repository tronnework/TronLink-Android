package com.tron.wallet.business.tabdapp.browser.grid.base;

import androidx.recyclerview.widget.RecyclerView;
public class UnwrapPositionResult {
    public RecyclerView.Adapter adapter;
    public int position = -1;
    public Object tag;

    public void clear() {
        this.adapter = null;
        this.tag = null;
        this.position = -1;
    }

    public boolean isValid() {
        return (this.adapter == null || this.position == -1) ? false : true;
    }
}
