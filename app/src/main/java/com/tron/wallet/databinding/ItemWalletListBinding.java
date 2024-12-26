package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.MultiSignPopupTextView;
import com.tronlinkpro.wallet.R;
public final class ItemWalletListBinding implements ViewBinding {
    public final ImageView assetStatus;
    public final MultiSignPopupTextView flagMultiSign;
    public final RelativeLayout itemLayout;
    public final ImageView ivCopy;
    public final ImageView ivIcon;
    public final LinearLayout llInfo;
    public final ConstraintLayout rlAddress;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvWalletName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemWalletListBinding(RelativeLayout relativeLayout, ImageView imageView, MultiSignPopupTextView multiSignPopupTextView, RelativeLayout relativeLayout2, ImageView imageView2, ImageView imageView3, LinearLayout linearLayout, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.assetStatus = imageView;
        this.flagMultiSign = multiSignPopupTextView;
        this.itemLayout = relativeLayout2;
        this.ivCopy = imageView2;
        this.ivIcon = imageView3;
        this.llInfo = linearLayout;
        this.rlAddress = constraintLayout;
        this.tvAddress = textView;
        this.tvBalance = textView2;
        this.tvWalletName = textView3;
    }

    public static ItemWalletListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemWalletListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_wallet_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemWalletListBinding bind(View view) {
        int i = R.id.asset_status;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.asset_status);
        if (imageView != null) {
            i = R.id.flag_multi_sign;
            MultiSignPopupTextView multiSignPopupTextView = (MultiSignPopupTextView) ViewBindings.findChildViewById(view, R.id.flag_multi_sign);
            if (multiSignPopupTextView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.iv_copy;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                if (imageView2 != null) {
                    i = R.id.iv_icon;
                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_icon);
                    if (imageView3 != null) {
                        i = R.id.ll_info;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_info);
                        if (linearLayout != null) {
                            i = R.id.rl_address;
                            ConstraintLayout constraintLayout = (ConstraintLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                            if (constraintLayout != null) {
                                i = R.id.tv_address;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                if (textView != null) {
                                    i = R.id.tv_balance;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                    if (textView2 != null) {
                                        i = R.id.tv_wallet_name;
                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                                        if (textView3 != null) {
                                            return new ItemWalletListBinding(relativeLayout, imageView, multiSignPopupTextView, relativeLayout, imageView2, imageView3, linearLayout, constraintLayout, textView, textView2, textView3);
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
