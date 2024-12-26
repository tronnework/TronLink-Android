package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemApproveRiskTokenBinding implements ViewBinding {
    public final Button btnCancelAuthorization;
    public final View divider;
    public final ImageView icAccountAddressCopy;
    public final ImageView ivTokenAddressCopy;
    public final RelativeLayout rlNameContent;
    private final ConstraintLayout rootView;
    public final TextView tokenContractTag;
    public final TextView tvAccountAddress;
    public final TextView tvAccountAddressLabel;
    public final TextView tvAccountRiskAmount;
    public final TextView tvAccountRiskAmountLabel;
    public final TextView tvAuthorizationQuantity;
    public final TextView tvAuthorizationQuantityLabel;
    public final TextView tvAuthorizationTime;
    public final TextView tvAuthorizationTimeLabel;
    public final TextView tvProjectName;
    public final TextView tvTokenAddress;
    public final TextView tvViewHolder;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemApproveRiskTokenBinding(ConstraintLayout constraintLayout, Button button, View view, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        this.rootView = constraintLayout;
        this.btnCancelAuthorization = button;
        this.divider = view;
        this.icAccountAddressCopy = imageView;
        this.ivTokenAddressCopy = imageView2;
        this.rlNameContent = relativeLayout;
        this.tokenContractTag = textView;
        this.tvAccountAddress = textView2;
        this.tvAccountAddressLabel = textView3;
        this.tvAccountRiskAmount = textView4;
        this.tvAccountRiskAmountLabel = textView5;
        this.tvAuthorizationQuantity = textView6;
        this.tvAuthorizationQuantityLabel = textView7;
        this.tvAuthorizationTime = textView8;
        this.tvAuthorizationTimeLabel = textView9;
        this.tvProjectName = textView10;
        this.tvTokenAddress = textView11;
        this.tvViewHolder = textView12;
    }

    public static ItemApproveRiskTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemApproveRiskTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_approve_risk_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemApproveRiskTokenBinding bind(View view) {
        int i = R.id.btn_cancel_authorization;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_cancel_authorization);
        if (button != null) {
            i = R.id.divider;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
            if (findChildViewById != null) {
                i = R.id.ic_account_address_copy;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ic_account_address_copy);
                if (imageView != null) {
                    i = R.id.iv_token_address_copy;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_token_address_copy);
                    if (imageView2 != null) {
                        i = R.id.rl_name_content;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_name_content);
                        if (relativeLayout != null) {
                            i = R.id.token_contract_tag;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.token_contract_tag);
                            if (textView != null) {
                                i = R.id.tv_account_address;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_address);
                                if (textView2 != null) {
                                    i = R.id.tv_account_address_label;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_address_label);
                                    if (textView3 != null) {
                                        i = R.id.tv_account_risk_amount;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_risk_amount);
                                        if (textView4 != null) {
                                            i = R.id.tv_account_risk_amount_label;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_account_risk_amount_label);
                                            if (textView5 != null) {
                                                i = R.id.tv_authorization_quantity;
                                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_quantity);
                                                if (textView6 != null) {
                                                    i = R.id.tv_authorization_quantity_label;
                                                    TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_quantity_label);
                                                    if (textView7 != null) {
                                                        i = R.id.tv_authorization_time;
                                                        TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_time);
                                                        if (textView8 != null) {
                                                            i = R.id.tv_authorization_time_label;
                                                            TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_time_label);
                                                            if (textView9 != null) {
                                                                i = R.id.tv_project_name;
                                                                TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_project_name);
                                                                if (textView10 != null) {
                                                                    i = R.id.tv_token_address;
                                                                    TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_address);
                                                                    if (textView11 != null) {
                                                                        i = R.id.tv_view_holder;
                                                                        TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_view_holder);
                                                                        if (textView12 != null) {
                                                                            return new ItemApproveRiskTokenBinding((ConstraintLayout) view, button, findChildViewById, imageView, imageView2, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12);
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
