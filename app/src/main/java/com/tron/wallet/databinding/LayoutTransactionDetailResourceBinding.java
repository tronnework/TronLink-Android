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
public final class LayoutTransactionDetailResourceBinding implements ViewBinding {
    public final ImageView ivArrowRight;
    public final ImageView ivFeeLoading;
    public final LinearLayout llOtherError;
    public final LinearLayout llTop;
    public final LayoutTransactionFeeResourceBinding resourceInfoView;
    public final RelativeLayout rlAmount;
    public final RelativeLayout rlFee;
    private final RelativeLayout rootView;
    public final TextView tvAmount;
    public final TextView tvAmountTitle;
    public final TextView tvErrorText;
    public final TextView tvFee;
    public final TextView tvFeeTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutTransactionDetailResourceBinding(RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2, LinearLayout linearLayout, LinearLayout linearLayout2, LayoutTransactionFeeResourceBinding layoutTransactionFeeResourceBinding, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.ivArrowRight = imageView;
        this.ivFeeLoading = imageView2;
        this.llOtherError = linearLayout;
        this.llTop = linearLayout2;
        this.resourceInfoView = layoutTransactionFeeResourceBinding;
        this.rlAmount = relativeLayout2;
        this.rlFee = relativeLayout3;
        this.tvAmount = textView;
        this.tvAmountTitle = textView2;
        this.tvErrorText = textView3;
        this.tvFee = textView4;
        this.tvFeeTitle = textView5;
    }

    public static LayoutTransactionDetailResourceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutTransactionDetailResourceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_transaction_detail_resource, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutTransactionDetailResourceBinding bind(View view) {
        int i = R.id.iv_arrow_right;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
        if (imageView != null) {
            i = R.id.iv_fee_loading;
            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_fee_loading);
            if (imageView2 != null) {
                i = R.id.ll_other_error;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_other_error);
                if (linearLayout != null) {
                    i = R.id.ll_top;
                    LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_top);
                    if (linearLayout2 != null) {
                        i = R.id.resource_info_view;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.resource_info_view);
                        if (findChildViewById != null) {
                            LayoutTransactionFeeResourceBinding bind = LayoutTransactionFeeResourceBinding.bind(findChildViewById);
                            i = R.id.rl_amount;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_amount);
                            if (relativeLayout != null) {
                                i = R.id.rl_fee;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_fee);
                                if (relativeLayout2 != null) {
                                    i = R.id.tv_amount;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                                    if (textView != null) {
                                        i = R.id.tv_amount_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_title);
                                        if (textView2 != null) {
                                            i = R.id.tv_error_text;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_text);
                                            if (textView3 != null) {
                                                i = R.id.tv_fee;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee);
                                                if (textView4 != null) {
                                                    i = R.id.tv_fee_title;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_fee_title);
                                                    if (textView5 != null) {
                                                        return new LayoutTransactionDetailResourceBinding((RelativeLayout) view, imageView, imageView2, linearLayout, linearLayout2, bind, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5);
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
