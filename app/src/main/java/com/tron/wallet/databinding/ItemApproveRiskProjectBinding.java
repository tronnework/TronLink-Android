package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemApproveRiskProjectBinding implements ViewBinding {
    public final Button btnCancelAuthorization;
    public final View divider;
    public final ImageView icAccountAddressCopy;
    public final ImageView ivProjectNameTips;
    private final ConstraintLayout rootView;
    public final TextView tvAccountAddress;
    public final TextView tvAccountAddressLabel;
    public final TextView tvAccountRiskAmount;
    public final TextView tvAccountRiskAmountLabel;
    public final TextView tvAuthorizationQuantity;
    public final TextView tvAuthorizationQuantityLabel;
    public final TextView tvAuthorizationTime;
    public final TextView tvAuthorizationTimeLabel;
    public final TokenLogoDraweeView tvProjectIcon;
    public final TextView tvProjectName;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemApproveRiskProjectBinding(ConstraintLayout constraintLayout, Button button, View view, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TokenLogoDraweeView tokenLogoDraweeView, TextView textView9) {
        this.rootView = constraintLayout;
        this.btnCancelAuthorization = button;
        this.divider = view;
        this.icAccountAddressCopy = imageView;
        this.ivProjectNameTips = imageView2;
        this.tvAccountAddress = textView;
        this.tvAccountAddressLabel = textView2;
        this.tvAccountRiskAmount = textView3;
        this.tvAccountRiskAmountLabel = textView4;
        this.tvAuthorizationQuantity = textView5;
        this.tvAuthorizationQuantityLabel = textView6;
        this.tvAuthorizationTime = textView7;
        this.tvAuthorizationTimeLabel = textView8;
        this.tvProjectIcon = tokenLogoDraweeView;
        this.tvProjectName = textView9;
    }

    public static ItemApproveRiskProjectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemApproveRiskProjectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_approve_risk_project, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemApproveRiskProjectBinding bind(View view) {
        int i = R.id.btn_cancel_authorization;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel_authorization);
        if (button != null) {
            i = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i = R.id.ic_account_address_copy;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_account_address_copy);
                if (imageView != null) {
                    i = R.id.iv_project_name_tips;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_project_name_tips);
                    if (imageView2 != null) {
                        i = R.id.tv_account_address;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_address);
                        if (textView != null) {
                            i = R.id.tv_account_address_label;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_address_label);
                            if (textView2 != null) {
                                i = R.id.tv_account_risk_amount;
                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_risk_amount);
                                if (textView3 != null) {
                                    i = R.id.tv_account_risk_amount_label;
                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_risk_amount_label);
                                    if (textView4 != null) {
                                        i = R.id.tv_authorization_quantity;
                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_quantity);
                                        if (textView5 != null) {
                                            i = R.id.tv_authorization_quantity_label;
                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_quantity_label);
                                            if (textView6 != null) {
                                                i = R.id.tv_authorization_time;
                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_time);
                                                if (textView7 != null) {
                                                    i = R.id.tv_authorization_time_label;
                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_time_label);
                                                    if (textView8 != null) {
                                                        i = R.id.tv_project_icon;
                                                        TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.tv_project_icon);
                                                        if (tokenLogoDraweeView != null) {
                                                            i = R.id.tv_project_name;
                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_project_name);
                                                            if (textView9 != null) {
                                                                return new ItemApproveRiskProjectBinding((ConstraintLayout) view, button, findChildViewById, imageView, imageView2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, tokenLogoDraweeView, textView9);
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
