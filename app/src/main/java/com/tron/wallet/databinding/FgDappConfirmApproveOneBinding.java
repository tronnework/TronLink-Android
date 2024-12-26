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
import com.tron.wallet.common.components.CurrencyEditText;
import com.tron.wallet.common.components.ErrorBottomLayout;
import com.tronlinkpro.wallet.R;
public final class FgDappConfirmApproveOneBinding implements ViewBinding {
    public final ImageView approveAmountSelect;
    public final TextView approveAmountTitle;
    public final LinearLayout balanceLayout;
    public final ErrorBottomLayout customApproveAmountLayout;
    public final RelativeLayout defaultApproveAmountLayout;
    public final ImageView defaultTips;
    public final CurrencyEditText etCount;
    public final ImageView ivDelete;
    public final RelativeLayout llAmount;
    public final RelativeLayout rlTrustApproveTitle;
    private final RelativeLayout rootView;
    public final ImageView tips;
    public final ImageView trustApproveSelect;
    public final TextView trustApproveTitle;
    public final TextView tvAmountInfo;
    public final RelativeLayout tvAmountTitle;
    public final TextView tvBalance;
    public final TextView tvDefaultBalance;
    public final TextView tvMax;
    public final TextView tvValue;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgDappConfirmApproveOneBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView, LinearLayout linearLayout, ErrorBottomLayout errorBottomLayout, RelativeLayout relativeLayout2, ImageView imageView2, CurrencyEditText currencyEditText, ImageView imageView3, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, ImageView imageView4, ImageView imageView5, TextView textView2, TextView textView3, RelativeLayout relativeLayout5, TextView textView4, TextView textView5, TextView textView6, TextView textView7) {
        this.rootView = relativeLayout;
        this.approveAmountSelect = imageView;
        this.approveAmountTitle = textView;
        this.balanceLayout = linearLayout;
        this.customApproveAmountLayout = errorBottomLayout;
        this.defaultApproveAmountLayout = relativeLayout2;
        this.defaultTips = imageView2;
        this.etCount = currencyEditText;
        this.ivDelete = imageView3;
        this.llAmount = relativeLayout3;
        this.rlTrustApproveTitle = relativeLayout4;
        this.tips = imageView4;
        this.trustApproveSelect = imageView5;
        this.trustApproveTitle = textView2;
        this.tvAmountInfo = textView3;
        this.tvAmountTitle = relativeLayout5;
        this.tvBalance = textView4;
        this.tvDefaultBalance = textView5;
        this.tvMax = textView6;
        this.tvValue = textView7;
    }

    public static FgDappConfirmApproveOneBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgDappConfirmApproveOneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_dapp_confirm_approve_one, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgDappConfirmApproveOneBinding bind(View view) {
        int i = R.id.approve_amount_select;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.approve_amount_select);
        if (imageView != null) {
            i = R.id.approve_amount_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.approve_amount_title);
            if (textView != null) {
                i = R.id.balance_layout;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.balance_layout);
                if (linearLayout != null) {
                    i = R.id.custom_approve_amount_layout;
                    ErrorBottomLayout errorBottomLayout = (ErrorBottomLayout) ViewBindings.findChildViewById(view, R.id.custom_approve_amount_layout);
                    if (errorBottomLayout != null) {
                        i = R.id.default_approve_amount_layout;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.default_approve_amount_layout);
                        if (relativeLayout != null) {
                            i = R.id.default_tips;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.default_tips);
                            if (imageView2 != null) {
                                i = R.id.et_count;
                                CurrencyEditText currencyEditText = (CurrencyEditText) ViewBindings.findChildViewById(view, R.id.et_count);
                                if (currencyEditText != null) {
                                    i = R.id.iv_delete;
                                    ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                                    if (imageView3 != null) {
                                        i = R.id.ll_amount;
                                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_amount);
                                        if (relativeLayout2 != null) {
                                            i = R.id.rl_trust_approve_title;
                                            RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_trust_approve_title);
                                            if (relativeLayout3 != null) {
                                                i = R.id.tips;
                                                ImageView imageView4 = (ImageView) ViewBindings.findChildViewById(view, R.id.tips);
                                                if (imageView4 != null) {
                                                    i = R.id.trust_approve_select;
                                                    ImageView imageView5 = (ImageView) ViewBindings.findChildViewById(view, R.id.trust_approve_select);
                                                    if (imageView5 != null) {
                                                        i = R.id.trust_approve_title;
                                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.trust_approve_title);
                                                        if (textView2 != null) {
                                                            i = R.id.tv_amount_info;
                                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount_info);
                                                            if (textView3 != null) {
                                                                i = R.id.tv_amount_title;
                                                                RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.tv_amount_title);
                                                                if (relativeLayout4 != null) {
                                                                    i = R.id.tv_balance;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_default_balance;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_default_balance);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_max;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_max);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_value;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_value);
                                                                                if (textView7 != null) {
                                                                                    return new FgDappConfirmApproveOneBinding((RelativeLayout) view, imageView, textView, linearLayout, errorBottomLayout, relativeLayout, imageView2, currencyEditText, imageView3, relativeLayout2, relativeLayout3, imageView4, imageView5, textView2, textView3, relativeLayout4, textView4, textView5, textView6, textView7);
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
