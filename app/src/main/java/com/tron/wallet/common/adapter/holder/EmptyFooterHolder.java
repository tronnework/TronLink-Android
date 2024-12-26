package com.tron.wallet.common.adapter.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.tron.tron_base.frame.base.BaseHolder;
public class EmptyFooterHolder extends BaseHolder {
    public EmptyFooterHolder(Context context) {
        super(new View(context));
        this.itemView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    }

    public void onBind(View view, View view2) {
        this.itemView.getLayoutParams().height = (int) (view.getMeasuredHeight() * 1.8f);
        this.itemView.setLayoutParams(this.itemView.getLayoutParams());
    }
}
