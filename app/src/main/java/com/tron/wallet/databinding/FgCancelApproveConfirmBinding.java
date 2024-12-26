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
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class FgCancelApproveConfirmBinding implements ViewBinding {
    public final TextView authorizationLabel;
    public final GlobalConfirmButton confirmCancelApprove;
    public final RelativeLayout headerTitleView;
    public final GlobalTitleHeaderView headerView;
    public final ImageView ivBack;
    public final ImageView ivCloseRight;
    public final SimpleDraweeView ivCoinIcon;
    public final LinearLayout liApproveCap;
    public final LinearLayout liApproveObj;
    public final RelativeLayout liToken;
    public final GlobalFeeResourceView resourceView;
    public final RelativeLayout rlContent;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final TextView tokenContractTag;
    public final TextView tokenTag;
    public final TextView tvApproveCap;
    public final TextView tvApproveCapLabel;
    public final TextView tvAuthorizationTarget;
    public final TextView tvTokenAddress;
    public final TextView tvTokenName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgCancelApproveConfirmBinding(RelativeLayout relativeLayout, TextView textView, GlobalConfirmButton globalConfirmButton, RelativeLayout relativeLayout2, GlobalTitleHeaderView globalTitleHeaderView, ImageView imageView, ImageView imageView2, SimpleDraweeView simpleDraweeView, LinearLayout linearLayout, LinearLayout linearLayout2, RelativeLayout relativeLayout3, GlobalFeeResourceView globalFeeResourceView, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.authorizationLabel = textView;
        this.confirmCancelApprove = globalConfirmButton;
        this.headerTitleView = relativeLayout2;
        this.headerView = globalTitleHeaderView;
        this.ivBack = imageView;
        this.ivCloseRight = imageView2;
        this.ivCoinIcon = simpleDraweeView;
        this.liApproveCap = linearLayout;
        this.liApproveObj = linearLayout2;
        this.liToken = relativeLayout3;
        this.resourceView = globalFeeResourceView;
        this.rlContent = relativeLayout4;
        this.root = relativeLayout5;
        this.tokenContractTag = textView2;
        this.tokenTag = textView3;
        this.tvApproveCap = textView4;
        this.tvApproveCapLabel = textView5;
        this.tvAuthorizationTarget = textView6;
        this.tvTokenAddress = textView7;
        this.tvTokenName = textView8;
    }

    public static FgCancelApproveConfirmBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgCancelApproveConfirmBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_cancel_approve_confirm, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgCancelApproveConfirmBinding bind(View view) {
        int i = R.id.authorizationLabel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.authorizationLabel);
        if (textView != null) {
            i = R.id.confirm_cancel_approve;
            GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.confirm_cancel_approve);
            if (globalConfirmButton != null) {
                i = R.id.header_title_view;
                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.header_title_view);
                if (relativeLayout != null) {
                    i = R.id.header_view;
                    GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
                    if (globalTitleHeaderView != null) {
                        i = R.id.iv_back;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_back);
                        if (imageView != null) {
                            i = R.id.iv_close_right;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_right);
                            if (imageView2 != null) {
                                i = R.id.iv_coin_icon;
                                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) ViewBindings.findChildViewById(view, R.id.iv_coin_icon);
                                if (simpleDraweeView != null) {
                                    i = R.id.li_approve_cap;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_approve_cap);
                                    if (linearLayout != null) {
                                        i = R.id.li_approve_obj;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_approve_obj);
                                        if (linearLayout2 != null) {
                                            i = R.id.li_token;
                                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.li_token);
                                            if (relativeLayout2 != null) {
                                                i = R.id.resource_view;
                                                GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                                                if (globalFeeResourceView != null) {
                                                    i = R.id.rl_content;
                                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                                                    if (relativeLayout3 != null) {
                                                        i = R.id.root;
                                                        RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.root);
                                                        if (relativeLayout4 != null) {
                                                            i = R.id.token_contract_tag;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.token_contract_tag);
                                                            if (textView2 != null) {
                                                                i = R.id.token_tag;
                                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.token_tag);
                                                                if (textView3 != null) {
                                                                    i = R.id.tv_approve_cap;
                                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_cap);
                                                                    if (textView4 != null) {
                                                                        i = R.id.tv_approve_cap_label;
                                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_approve_cap_label);
                                                                        if (textView5 != null) {
                                                                            i = R.id.tv_authorization_target;
                                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_authorization_target);
                                                                            if (textView6 != null) {
                                                                                i = R.id.tv_token_address;
                                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_address);
                                                                                if (textView7 != null) {
                                                                                    i = R.id.tv_token_name;
                                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_name);
                                                                                    if (textView8 != null) {
                                                                                        return new FgCancelApproveConfirmBinding((RelativeLayout) view, textView, globalConfirmButton, relativeLayout, globalTitleHeaderView, imageView, imageView2, simpleDraweeView, linearLayout, linearLayout2, relativeLayout2, globalFeeResourceView, relativeLayout3, relativeLayout4, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
