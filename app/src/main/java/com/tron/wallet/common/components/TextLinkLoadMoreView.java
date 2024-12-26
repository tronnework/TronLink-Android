package com.tron.wallet.common.components;

import android.widget.TextView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tronlinkpro.wallet.R;
public class TextLinkLoadMoreView extends CustomLoadMoreView {
    private TextView linkTextView;

    @Override
    public int getLayoutId() {
        return R.layout.no_more_text_link;
    }

    public TextView getLinkTextView() {
        return this.linkTextView;
    }

    @Override
    public int getLoadEndViewId() {
        return R.id.rl_no_more_text;
    }

    @Override
    public void convert(BaseViewHolder baseViewHolder) {
        TextView textView = (TextView) baseViewHolder.itemView.findViewById(R.id.tv_extra);
        this.linkTextView = textView;
        if (textView != null) {
            textView.getPaint().setUnderlineText(true);
        }
        super.convert(baseViewHolder);
    }
}
