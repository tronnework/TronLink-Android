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
public final class DgDappAuthorizedBinding implements ViewBinding {
    public final ImageView icWallet;
    public final ImageView ivClose;
    public final ImageView ivCopy;
    public final ImageView ivIsBlack;
    public final RelativeLayout rlMiddle;
    private final RelativeLayout rootView;
    public final RelativeLayout tipsLayout;
    public final RelativeLayout top;
    public final TextView tvApprove;
    public final TextView tvBottom;
    public final TextView tvBottom2;
    public final TextView tvHost;
    public final TextView tvReject;
    public final TextView tvTips;
    public final TextView tvTop;
    public final TextView tvTrxNum;
    public final TextView tvWalletAddress;
    public final TextView tvWalletName;
    public final LinearLayout walletAddressLayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgDappAuthorizedBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.icWallet = imageView;
        this.ivClose = imageView2;
        this.ivCopy = imageView3;
        this.ivIsBlack = imageView4;
        this.rlMiddle = relativeLayout2;
        this.tipsLayout = relativeLayout3;
        this.top = relativeLayout4;
        this.tvApprove = textView;
        this.tvBottom = textView2;
        this.tvBottom2 = textView3;
        this.tvHost = textView4;
        this.tvReject = textView5;
        this.tvTips = textView6;
        this.tvTop = textView7;
        this.tvTrxNum = textView8;
        this.tvWalletAddress = textView9;
        this.tvWalletName = textView10;
        this.walletAddressLayout = linearLayout;
    }

    public static DgDappAuthorizedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgDappAuthorizedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_dapp_authorized, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgDappAuthorizedBinding bind(View view) {
        int i = R.id.ic_wallet;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_wallet);
        if (imageView != null) {
            i = R.id.iv_close;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
            if (imageView2 != null) {
                i = R.id.iv_copy;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                if (imageView3 != null) {
                    i = R.id.iv_is_black;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_is_black);
                    if (imageView4 != null) {
                        i = R.id.rl_middle;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_middle);
                        if (relativeLayout != null) {
                            i = R.id.tips_layout;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.tips_layout);
                            if (relativeLayout2 != null) {
                                i = R.id.top;
                                RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                if (relativeLayout3 != null) {
                                    i = R.id.tv_approve;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve);
                                    if (textView != null) {
                                        i = R.id.tv_bottom;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bottom);
                                        if (textView2 != null) {
                                            i = R.id.tv_bottom2;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_bottom2);
                                            if (textView3 != null) {
                                                i = R.id.tv_host;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_host);
                                                if (textView4 != null) {
                                                    i = R.id.tv_reject;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_reject);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_tips;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tips);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_top;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_top);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_trx_num;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_trx_num);
                                                                if (textView8 != null) {
                                                                    i = R.id.tv_wallet_address;
                                                                    TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_address);
                                                                    if (textView9 != null) {
                                                                        i = R.id.tv_wallet_name;
                                                                        TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_wallet_name);
                                                                        if (textView10 != null) {
                                                                            i = R.id.wallet_address_layout;
                                                                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.wallet_address_layout);
                                                                            if (linearLayout != null) {
                                                                                return new DgDappAuthorizedBinding((RelativeLayout) view, imageView, imageView2, imageView3, imageView4, relativeLayout, relativeLayout2, relativeLayout3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, linearLayout);
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
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
