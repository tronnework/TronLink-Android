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
public final class SwapTokenInfoBinding implements ViewBinding {
    public final ImageView ivEnterSwapRouter;
    public final ImageView ivHelpFee;
    public final ImageView ivHelpMinReceived;
    public final ImageView ivHelpPriceImpact;
    public final ImageView ivJustSwapLogo;
    public final ImageView ivLoading;
    public final LinearLayout llRoot;
    public final LinearLayout llSwapRouterContent;
    public final LinearLayout rlContainer;
    private final RelativeLayout rootView;
    public final TextView tvFee;
    public final TextView tvFeeNumber;
    public final TextView tvMinReceived;
    public final TextView tvMinReceivedNumber;
    public final TextView tvPriceImpact;
    public final TextView tvPriceImpactNumber;
    public final TextView tvRate;
    public final TextView tvRateNumber;
    public final TextView tvSwapRouterTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapTokenInfoBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9) {
        this.rootView = relativeLayout;
        this.ivEnterSwapRouter = imageView;
        this.ivHelpFee = imageView2;
        this.ivHelpMinReceived = imageView3;
        this.ivHelpPriceImpact = imageView4;
        this.ivJustSwapLogo = imageView5;
        this.ivLoading = imageView6;
        this.llRoot = linearLayout;
        this.llSwapRouterContent = linearLayout2;
        this.rlContainer = linearLayout3;
        this.tvFee = textView;
        this.tvFeeNumber = textView2;
        this.tvMinReceived = textView3;
        this.tvMinReceivedNumber = textView4;
        this.tvPriceImpact = textView5;
        this.tvPriceImpactNumber = textView6;
        this.tvRate = textView7;
        this.tvRateNumber = textView8;
        this.tvSwapRouterTitle = textView9;
    }

    public static SwapTokenInfoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapTokenInfoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_token_info, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapTokenInfoBinding bind(View view) {
        int i = R.id.iv_enter_swap_router;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_enter_swap_router);
        if (imageView != null) {
            i = R.id.iv_help_fee;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_fee);
            if (imageView2 != null) {
                i = R.id.iv_help_min_received;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_min_received);
                if (imageView3 != null) {
                    i = R.id.iv_help_price_impact;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_price_impact);
                    if (imageView4 != null) {
                        i = R.id.iv_just_swap_logo;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_just_swap_logo);
                        if (imageView5 != null) {
                            i = R.id.iv_loading;
                            ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
                            if (imageView6 != null) {
                                i = R.id.ll_root;
                                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_root);
                                if (linearLayout != null) {
                                    i = R.id.ll_swap_router_content;
                                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_swap_router_content);
                                    if (linearLayout2 != null) {
                                        i = R.id.rl_container;
                                        LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rl_container);
                                        if (linearLayout3 != null) {
                                            i = R.id.tv_fee;
                                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                            if (textView != null) {
                                                i = R.id.tv_fee_number;
                                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_number);
                                                if (textView2 != null) {
                                                    i = R.id.tv_min_received;
                                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_min_received);
                                                    if (textView3 != null) {
                                                        i = R.id.tv_min_received_number;
                                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_min_received_number);
                                                        if (textView4 != null) {
                                                            i = R.id.tv_price_impact;
                                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price_impact);
                                                            if (textView5 != null) {
                                                                i = R.id.tv_price_impact_number;
                                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price_impact_number);
                                                                if (textView6 != null) {
                                                                    i = R.id.tv_rate;
                                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_rate);
                                                                    if (textView7 != null) {
                                                                        i = R.id.tv_rate_number;
                                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_rate_number);
                                                                        if (textView8 != null) {
                                                                            i = R.id.tv_swap_router_title;
                                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_swap_router_title);
                                                                            if (textView9 != null) {
                                                                                return new SwapTokenInfoBinding((RelativeLayout) view, imageView, imageView2, imageView3, imageView4, imageView5, imageView6, linearLayout, linearLayout2, linearLayout3, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9);
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
