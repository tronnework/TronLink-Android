package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import com.tron.tron_base.frame.base.BaseHolder;
public class NoMoreFooterHolder extends BaseHolder {
    private Context mContext;

    public void bindHolder() {
    }

    public NoMoreFooterHolder(View view, Context context) {
        super(view);
        this.mContext = context;
    }

    public void bindHolder(boolean z) {
        this.itemView.setVisibility(z ? View.VISIBLE : View.GONE);
    }
}
