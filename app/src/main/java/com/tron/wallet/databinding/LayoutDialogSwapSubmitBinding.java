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
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tronlinkpro.wallet.R;
public final class LayoutDialogSwapSubmitBinding implements ViewBinding {
    public final RelativeLayout headerView;
    public final SimpleDraweeView imageTokenFrom;
    public final SimpleDraweeView imageTokenTo;
    public final ImageView ivBack;
    public final ImageView ivClose;
    public final ImageView ivHelpFee;
    public final ImageView ivHelpMin;
    public final ImageView ivHelpPrice;
    public final LinearLayout liContent;
    public final GlobalFeeResourceView resourceView;
    private final RelativeLayout rootView;
    public final GlobalConfirmButton send;
    public final ImageView swapDivider;
    public final TextView textTokenFrom;
    public final TextView textTokenTo;
    public final TextView tvFee;
    public final TextView tvFeeRight;
    public final TextView tvMin;
    public final TextView tvMinReceivedRight;
    public final TextView tvMinTips;
    public final TextView tvPriceImpact;
    public final TextView tvPriceImpactRight;
    public final TextView tvRate;
    public final TextView tvRateRight;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutDialogSwapSubmitBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, SimpleDraweeView simpleDraweeView, SimpleDraweeView simpleDraweeView2, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, GlobalFeeResourceView globalFeeResourceView, GlobalConfirmButton globalConfirmButton, ImageView imageView6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11) {
        this.rootView = relativeLayout;
        this.headerView = relativeLayout2;
        this.imageTokenFrom = simpleDraweeView;
        this.imageTokenTo = simpleDraweeView2;
        this.ivBack = imageView;
        this.ivClose = imageView2;
        this.ivHelpFee = imageView3;
        this.ivHelpMin = imageView4;
        this.ivHelpPrice = imageView5;
        this.liContent = linearLayout;
        this.resourceView = globalFeeResourceView;
        this.send = globalConfirmButton;
        this.swapDivider = imageView6;
        this.textTokenFrom = textView;
        this.textTokenTo = textView2;
        this.tvFee = textView3;
        this.tvFeeRight = textView4;
        this.tvMin = textView5;
        this.tvMinReceivedRight = textView6;
        this.tvMinTips = textView7;
        this.tvPriceImpact = textView8;
        this.tvPriceImpactRight = textView9;
        this.tvRate = textView10;
        this.tvRateRight = textView11;
    }

    public static LayoutDialogSwapSubmitBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutDialogSwapSubmitBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_dialog_swap_submit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutDialogSwapSubmitBinding bind(View view) {
        int i = R.id.header_view;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_view);
        if (relativeLayout != null) {
            i = R.id.image_token_from;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image_token_from);
            if (simpleDraweeView != null) {
                i = R.id.image_token_to;
                SimpleDraweeView simpleDraweeView2 = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.image_token_to);
                if (simpleDraweeView2 != null) {
                    i = R.id.iv_back;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                    if (imageView != null) {
                        i = R.id.iv_close;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
                        if (imageView2 != null) {
                            i = R.id.iv_help_fee;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_fee);
                            if (imageView3 != null) {
                                i = R.id.iv_help_min;
                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_min);
                                if (imageView4 != null) {
                                    i = R.id.iv_help_price;
                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_help_price);
                                    if (imageView5 != null) {
                                        i = R.id.li_content;
                                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_content);
                                        if (linearLayout != null) {
                                            i = R.id.resource_view;
                                            GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                                            if (globalFeeResourceView != null) {
                                                i = R.id.send;
                                                GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.send);
                                                if (globalConfirmButton != null) {
                                                    i = R.id.swap_divider;
                                                    ImageView imageView6 = (ImageView) ViewBindings.findChildViewById(view, R.id.swap_divider);
                                                    if (imageView6 != null) {
                                                        i = R.id.text_token_from;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.text_token_from);
                                                        if (textView != null) {
                                                            i = R.id.text_token_to;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.text_token_to);
                                                            if (textView2 != null) {
                                                                i = R.id.tv_fee;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_fee_right;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_right);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_min;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_min);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_min_received_right;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_min_received_right);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_min_tips;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_min_tips);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_price_impact;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price_impact);
                                                                                    if (textView8 != null) {
                                                                                        i = R.id.tv_price_impact_right;
                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_price_impact_right);
                                                                                        if (textView9 != null) {
                                                                                            i = R.id.tv_rate;
                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_rate);
                                                                                            if (textView10 != null) {
                                                                                                i = R.id.tv_rate_right;
                                                                                                TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_rate_right);
                                                                                                if (textView11 != null) {
                                                                                                    return new LayoutDialogSwapSubmitBinding((RelativeLayout) view, relativeLayout, simpleDraweeView, simpleDraweeView2, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout, globalFeeResourceView, globalConfirmButton, imageView6, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
