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
public final class ItemSelectWalletGroupBinding implements ViewBinding {
    public final ImageView ivTips;
    public final LinearLayout rlGroupContent;
    private final RelativeLayout rootView;
    public final TextView tvHd;
    public final TextView tvTotalBalance;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSelectWalletGroupBinding(RelativeLayout relativeLayout, ImageView imageView, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivTips = imageView;
        this.rlGroupContent = linearLayout;
        this.tvHd = textView;
        this.tvTotalBalance = textView2;
    }

    public static ItemSelectWalletGroupBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSelectWalletGroupBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_select_wallet_group, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSelectWalletGroupBinding bind(View view) {
        int i = R.id.iv_tips;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
        if (imageView != null) {
            i = R.id.rl_group_content;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_group_content);
            if (linearLayout != null) {
                i = R.id.tv_hd;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hd);
                if (textView != null) {
                    i = R.id.tv_total_balance;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_balance);
                    if (textView2 != null) {
                        return new ItemSelectWalletGroupBinding((RelativeLayout) view, imageView, linearLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
