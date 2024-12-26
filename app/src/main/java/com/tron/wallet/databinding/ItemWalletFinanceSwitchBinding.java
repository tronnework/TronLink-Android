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
public final class ItemWalletFinanceSwitchBinding implements ViewBinding {
    public final ImageView assetStatus;
    public final RelativeLayout itemLayout;
    public final ImageView ivIcon;
    public final LinearLayout llInfo;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvBalance1;
    public final TextView tvWalletName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemWalletFinanceSwitchBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, ImageView imageView2, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.assetStatus = imageView;
        this.itemLayout = relativeLayout2;
        this.ivIcon = imageView2;
        this.llInfo = linearLayout;
        this.tvAddress = textView;
        this.tvBalance = textView2;
        this.tvBalance1 = textView3;
        this.tvWalletName = textView4;
    }

    public static ItemWalletFinanceSwitchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemWalletFinanceSwitchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_wallet_finance_switch, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemWalletFinanceSwitchBinding bind(View view) {
        int i = R.id.asset_status;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.asset_status);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.iv_icon;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
            if (imageView2 != null) {
                i = R.id.ll_info;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_info);
                if (linearLayout != null) {
                    i = R.id.tv_address;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                    if (textView != null) {
                        i = R.id.tv_balance;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                        if (textView2 != null) {
                            i = R.id.tv_balance1;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance1);
                            if (textView3 != null) {
                                i = R.id.tv_wallet_name;
                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                                if (textView4 != null) {
                                    return new ItemWalletFinanceSwitchBinding(relativeLayout, imageView, relativeLayout, imageView2, linearLayout, textView, textView2, textView3, textView4);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
