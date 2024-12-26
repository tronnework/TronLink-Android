package com.tron.wallet.common.adapter.holder;

import android.view.View;
import android.widget.LinearLayout;
import com.tron.tron_base.frame.base.BaseHolder;
import com.tronlinkpro.wallet.R;
public class TitleViewHolder extends BaseHolder {
    LinearLayout llTitle;

    public TitleViewHolder(View view) {
        super(view);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_title);
        this.llTitle = linearLayout;
        linearLayout.setVisibility(View.VISIBLE);
    }
}
