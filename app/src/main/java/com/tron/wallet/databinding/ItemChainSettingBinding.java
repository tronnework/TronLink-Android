package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemChainSettingBinding implements ViewBinding {
    public final ImageView back3;
    public final ImageView ivSelect;
    public final LinearLayout llSelect;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tvNode;
    public final TextView tvNodeName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemChainSettingBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.back3 = imageView;
        this.ivSelect = imageView2;
        this.llSelect = linearLayout;
        this.root = relativeLayout2;
        this.tvNode = textView;
        this.tvNodeName = textView2;
    }

    public static ItemChainSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemChainSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_chain_setting, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemChainSettingBinding bind(View view) {
        int i = R.id.back3;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.back3);
        if (imageView != null) {
            i = R.id.iv_select;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_select);
            if (imageView2 != null) {
                i = R.id.ll_select;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_select);
                if (linearLayout != null) {
                    RelativeLayout relativeLayout = (RelativeLayout) view;
                    i = R.id.tv_node;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node);
                    if (textView != null) {
                        i = R.id.tv_node_name;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_name);
                        if (textView2 != null) {
                            return new ItemChainSettingBinding(relativeLayout, imageView, imageView2, linearLayout, relativeLayout, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
