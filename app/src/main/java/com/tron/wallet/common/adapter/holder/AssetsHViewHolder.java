package com.tron.wallet.common.adapter.holder;

import android.view.View;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tron.wallet.common.components.HomeHeader;
public class AssetsHViewHolder extends BaseHolder {
    protected HomeHeader header;

    public HomeHeader getHeader() {
        return this.header;
    }

    public AssetsHViewHolder(View view) {
        super(view);
        if (!(view instanceof HomeHeader)) {
            throw new IllegalArgumentException("must instanceof HomeHeader");
        }
        this.header = (HomeHeader) view;
    }

    public void bindHolder(boolean z) {
        this.header.setVisibility(z ? View.VISIBLE : View.GONE);
    }
}
