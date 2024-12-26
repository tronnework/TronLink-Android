package com.tron.wallet.business.tabdapp.browser.grid.base;

import androidx.recyclerview.widget.RecyclerView;
public class AdapterPathSegment {
    public final RecyclerView.Adapter adapter;
    public final Object tag;

    public AdapterPathSegment(RecyclerView.Adapter adapter, Object obj) {
        this.adapter = adapter;
        this.tag = obj;
    }
}
