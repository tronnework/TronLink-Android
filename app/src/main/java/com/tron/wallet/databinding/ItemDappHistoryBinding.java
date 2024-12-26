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
import com.facebook.drawee.view.SimpleDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemDappHistoryBinding implements ViewBinding {
    public final View ivDivider;
    public final ImageView rbSelect;
    public final RelativeLayout rlBg;
    private final LinearLayout rootView;
    public final SimpleDraweeView simpleDraweeView;
    public final TextView tvContent;
    public final TextView tvName;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemDappHistoryBinding(LinearLayout linearLayout, View view, ImageView imageView, RelativeLayout relativeLayout, SimpleDraweeView simpleDraweeView, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.ivDivider = view;
        this.rbSelect = imageView;
        this.rlBg = relativeLayout;
        this.simpleDraweeView = simpleDraweeView;
        this.tvContent = textView;
        this.tvName = textView2;
    }

    public static ItemDappHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDappHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_dapp_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDappHistoryBinding bind(View view) {
        int i = R.id.iv_divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.iv_divider);
        if (findChildViewById != null) {
            i = R.id.rb_select;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.rb_select);
            if (imageView != null) {
                i = R.id.rl_bg;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
                if (relativeLayout != null) {
                    i = R.id.simple_drawee_view;
                    SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.simple_drawee_view);
                    if (simpleDraweeView != null) {
                        i = R.id.tv_content;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                        if (textView != null) {
                            i = R.id.tv_name;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name);
                            if (textView2 != null) {
                                return new ItemDappHistoryBinding((LinearLayout) view, findChildViewById, imageView, relativeLayout, simpleDraweeView, textView, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
