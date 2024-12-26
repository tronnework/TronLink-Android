package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.flowlayout.TagFlowLayout;
import com.tronlinkpro.wallet.R;
public final class ItemDappHotHistoryBinding implements ViewBinding {
    public final TagFlowLayout flHistory;
    public final TagFlowLayout flHot;
    public final ImageView ivDelete;
    public final LinearLayout llHot;
    public final RelativeLayout rlHistory;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemDappHotHistoryBinding(LinearLayout linearLayout, TagFlowLayout tagFlowLayout, TagFlowLayout tagFlowLayout2, ImageView imageView, LinearLayout linearLayout2, RelativeLayout relativeLayout) {
        this.rootView = linearLayout;
        this.flHistory = tagFlowLayout;
        this.flHot = tagFlowLayout2;
        this.ivDelete = imageView;
        this.llHot = linearLayout2;
        this.rlHistory = relativeLayout;
    }

    public static ItemDappHotHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappHotHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_hot_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappHotHistoryBinding bind(View view) {
        int i = R.id.fl_history;
        TagFlowLayout tagFlowLayout = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_history);
        if (tagFlowLayout != null) {
            i = R.id.fl_hot;
            TagFlowLayout tagFlowLayout2 = (TagFlowLayout) ViewBindings.findChildViewById(view, R.id.fl_hot);
            if (tagFlowLayout2 != null) {
                i = R.id.iv_delete;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                if (imageView != null) {
                    i = R.id.ll_hot;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_hot);
                    if (linearLayout != null) {
                        i = R.id.rl_history;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_history);
                        if (relativeLayout != null) {
                            return new ItemDappHotHistoryBinding((LinearLayout) view, tagFlowLayout, tagFlowLayout2, imageView, linearLayout, relativeLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
