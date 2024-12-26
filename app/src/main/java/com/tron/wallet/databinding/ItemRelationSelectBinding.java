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
public final class ItemRelationSelectBinding implements ViewBinding {
    public final ImageView assetStatus;
    public final RelativeLayout icItemBack;
    public final RelativeLayout itemLayout;
    public final ImageView ivCopy;
    public final LinearLayout llInfo;
    public final RelativeLayout llWalletIcon;
    public final TextView path;
    public final LinearLayout rlAddress;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvIndex;
    public final TextView tvWalletName;
    public final RelativeLayout walletIcon;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemRelationSelectBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, ImageView imageView2, LinearLayout linearLayout, RelativeLayout relativeLayout4, TextView textView, LinearLayout linearLayout2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, RelativeLayout relativeLayout5) {
        this.rootView = relativeLayout;
        this.assetStatus = imageView;
        this.icItemBack = relativeLayout2;
        this.itemLayout = relativeLayout3;
        this.ivCopy = imageView2;
        this.llInfo = linearLayout;
        this.llWalletIcon = relativeLayout4;
        this.path = textView;
        this.rlAddress = linearLayout2;
        this.tvAddress = textView2;
        this.tvBalance = textView3;
        this.tvIndex = textView4;
        this.tvWalletName = textView5;
        this.walletIcon = relativeLayout5;
    }

    public static ItemRelationSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRelationSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_relation_select, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRelationSelectBinding bind(View view) {
        int i = R.id.asset_status;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.asset_status);
        if (imageView != null) {
            i = R.id.ic_item_back;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ic_item_back);
            if (relativeLayout != null) {
                RelativeLayout relativeLayout2 = (RelativeLayout) view;
                i = R.id.iv_copy;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                if (imageView2 != null) {
                    i = R.id.ll_info;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_info);
                    if (linearLayout != null) {
                        i = R.id.ll_wallet_icon;
                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_wallet_icon);
                        if (relativeLayout3 != null) {
                            i = R.id.path;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.path);
                            if (textView != null) {
                                i = R.id.rl_address;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                                if (linearLayout2 != null) {
                                    i = R.id.tv_address;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                    if (textView2 != null) {
                                        i = R.id.tv_balance;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                        if (textView3 != null) {
                                            i = R.id.tv_index;
                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_index);
                                            if (textView4 != null) {
                                                i = R.id.tv_wallet_name;
                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                                                if (textView5 != null) {
                                                    i = R.id.wallet_icon;
                                                    RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.wallet_icon);
                                                    if (relativeLayout4 != null) {
                                                        return new ItemRelationSelectBinding(relativeLayout2, imageView, relativeLayout, relativeLayout2, imageView2, linearLayout, relativeLayout3, textView, linearLayout2, textView2, textView3, textView4, textView5, relativeLayout4);
                                                    }
                                                }
                                            }
                                        }
                                    }
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
