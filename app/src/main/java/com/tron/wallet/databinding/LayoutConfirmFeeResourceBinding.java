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
import com.tron.wallet.common.components.DashUnderLineTextView;
import com.tron.wallet.common.components.FeeResourceDetailView;
import com.tronlinkpro.wallet.R;
public final class LayoutConfirmFeeResourceBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final ImageView ivFeeLoading;
    public final ImageView ivTips;
    public final ImageView ivTipsFee;
    public final ImageView ivTipsResource;
    public final LinearLayout llOtherError;
    public final LinearLayout llResourceAll;
    public final LinearLayout llTop;
    public final FeeResourceDetailView resourceInfoView;
    public final RelativeLayout rlFee;
    public final RelativeLayout rlResourceConsume;
    public final RelativeLayout rlResourceConsumeContent;
    public final RelativeLayout rlResourceConsumeTitle;
    public final RelativeLayout rlTotalConsumed;
    private final RelativeLayout rootView;
    public final TextView tvConsumeResourceBandwidth;
    public final DashUnderLineTextView tvConsumeResourceEnergy;
    public final DashUnderLineTextView tvConsumeResourceEnergyUnder;
    public final TextView tvConsumeResourceMid;
    public final TextView tvContactEnergyNotEnoughTip;
    public final TextView tvErrorText;
    public final TextView tvFee;
    public final TextView tvFeePrice;
    public final TextView tvFeeTitle;
    public final TextView tvResourceConsumeLeft;
    public final TextView tvResourceConsumeTitle;
    public final TextView tvTotalConsumed;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutConfirmFeeResourceBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, FeeResourceDetailView feeResourceDetailView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, TextView textView, DashUnderLineTextView dashUnderLineTextView, DashUnderLineTextView dashUnderLineTextView2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivFeeLoading = imageView2;
        this.ivTips = imageView3;
        this.ivTipsFee = imageView4;
        this.ivTipsResource = imageView5;
        this.llOtherError = linearLayout;
        this.llResourceAll = linearLayout2;
        this.llTop = linearLayout3;
        this.resourceInfoView = feeResourceDetailView;
        this.rlFee = relativeLayout2;
        this.rlResourceConsume = relativeLayout3;
        this.rlResourceConsumeContent = relativeLayout4;
        this.rlResourceConsumeTitle = relativeLayout5;
        this.rlTotalConsumed = relativeLayout6;
        this.tvConsumeResourceBandwidth = textView;
        this.tvConsumeResourceEnergy = dashUnderLineTextView;
        this.tvConsumeResourceEnergyUnder = dashUnderLineTextView2;
        this.tvConsumeResourceMid = textView2;
        this.tvContactEnergyNotEnoughTip = textView3;
        this.tvErrorText = textView4;
        this.tvFee = textView5;
        this.tvFeePrice = textView6;
        this.tvFeeTitle = textView7;
        this.tvResourceConsumeLeft = textView8;
        this.tvResourceConsumeTitle = textView9;
        this.tvTotalConsumed = textView10;
    }

    public static LayoutConfirmFeeResourceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutConfirmFeeResourceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_confirm_fee_resource, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutConfirmFeeResourceBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_fee_loading;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_fee_loading);
            if (imageView2 != null) {
                i = R.id.iv_tips;
                ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips);
                if (imageView3 != null) {
                    i = R.id.iv_tips_fee;
                    ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips_fee);
                    if (imageView4 != null) {
                        i = R.id.iv_tips_resource;
                        ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tips_resource);
                        if (imageView5 != null) {
                            i = R.id.ll_other_error;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_other_error);
                            if (linearLayout != null) {
                                i = R.id.ll_resource_all;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_resource_all);
                                if (linearLayout2 != null) {
                                    i = R.id.ll_top;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top);
                                    if (linearLayout3 != null) {
                                        i = R.id.resource_info_view;
                                        FeeResourceDetailView feeResourceDetailView = (FeeResourceDetailView) ViewBindings.findChildViewById(view, R.id.resource_info_view);
                                        if (feeResourceDetailView != null) {
                                            i = R.id.rl_fee;
                                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
                                            if (relativeLayout != null) {
                                                i = R.id.rl_resource_consume;
                                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_consume);
                                                if (relativeLayout2 != null) {
                                                    i = R.id.rl_resource_consume_content;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_consume_content);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.rl_resource_consume_title;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_resource_consume_title);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.rl_total_consumed;
                                                            RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_total_consumed);
                                                            if (relativeLayout5 != null) {
                                                                i = R.id.tv_consume_resource_bandwidth;
                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_bandwidth);
                                                                if (textView != null) {
                                                                    i = R.id.tv_consume_resource_energy;
                                                                    DashUnderLineTextView dashUnderLineTextView = (DashUnderLineTextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_energy);
                                                                    if (dashUnderLineTextView != null) {
                                                                        i = R.id.tv_consume_resource_energy_under;
                                                                        DashUnderLineTextView dashUnderLineTextView2 = (DashUnderLineTextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_energy_under);
                                                                        if (dashUnderLineTextView2 != null) {
                                                                            i = R.id.tv_consume_resource_mid;
                                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_consume_resource_mid);
                                                                            if (textView2 != null) {
                                                                                i = R.id.tv_contact_energy_not_enough_tip;
                                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contact_energy_not_enough_tip);
                                                                                if (textView3 != null) {
                                                                                    i = R.id.tv_error_text;
                                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_text);
                                                                                    if (textView4 != null) {
                                                                                        i = R.id.tv_fee;
                                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                                                        if (textView5 != null) {
                                                                                            i = R.id.tv_fee_price;
                                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_price);
                                                                                            if (textView6 != null) {
                                                                                                i = R.id.tv_fee_title;
                                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_title);
                                                                                                if (textView7 != null) {
                                                                                                    i = R.id.tv_resource_consume_left;
                                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_consume_left);
                                                                                                    if (textView8 != null) {
                                                                                                        i = R.id.tv_resource_consume_title;
                                                                                                        TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_resource_consume_title);
                                                                                                        if (textView9 != null) {
                                                                                                            i = R.id.tv_total_consumed;
                                                                                                            TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_total_consumed);
                                                                                                            if (textView10 != null) {
                                                                                                                return new LayoutConfirmFeeResourceBinding((RelativeLayout) view, imageView, imageView2, imageView3, imageView4, imageView5, linearLayout, linearLayout2, linearLayout3, feeResourceDetailView, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, textView, dashUnderLineTextView, dashUnderLineTextView2, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10);
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
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
