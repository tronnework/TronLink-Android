package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.TokenLogoDraweeView;
import com.tronlinkpro.wallet.R;
public final class ItemApproveRiskBinding implements ViewBinding {
    public final View divider;
    public final TokenLogoDraweeView iconToken;
    public final ImageView ivArrow;
    public final ImageView ivContractCopy;
    public final ImageView ivUnrecorderTips;
    public final View placeHolderView;
    public final RecyclerView recyclerView;
    public final RelativeLayout rlHeader;
    private final ConstraintLayout rootView;
    public final TextView tvAmount;
    public final TextView tvBalanceTitle;
    public final TextView tvContract;
    public final TextView tvContractLabel;
    public final TextView tvContractTag;
    public final TextView tvRiskAmount;
    public final TextView tvTokenName;
    public final TextView tvUnknowContract;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemApproveRiskBinding(ConstraintLayout constraintLayout, View view, TokenLogoDraweeView tokenLogoDraweeView, ImageView imageView, ImageView imageView2, ImageView imageView3, View view2, RecyclerView recyclerView, RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = constraintLayout;
        this.divider = view;
        this.iconToken = tokenLogoDraweeView;
        this.ivArrow = imageView;
        this.ivContractCopy = imageView2;
        this.ivUnrecorderTips = imageView3;
        this.placeHolderView = view2;
        this.recyclerView = recyclerView;
        this.rlHeader = relativeLayout;
        this.tvAmount = textView;
        this.tvBalanceTitle = textView2;
        this.tvContract = textView3;
        this.tvContractLabel = textView4;
        this.tvContractTag = textView5;
        this.tvRiskAmount = textView6;
        this.tvTokenName = textView7;
        this.tvUnknowContract = textView8;
    }

    public static ItemApproveRiskBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemApproveRiskBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_approve_risk, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemApproveRiskBinding bind(View view) {
        int i = R.id.divider;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
        if (findChildViewById != null) {
            i = R.id.icon_token;
            TokenLogoDraweeView tokenLogoDraweeView = (TokenLogoDraweeView) ViewBindings.findChildViewById(view, R.id.icon_token);
            if (tokenLogoDraweeView != null) {
                i = R.id.iv_arrow;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
                if (imageView != null) {
                    i = R.id.iv_contract_copy;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_contract_copy);
                    if (imageView2 != null) {
                        i = R.id.iv_unrecorder_tips;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_unrecorder_tips);
                        if (imageView3 != null) {
                            i = R.id.place_holder_view;
                            View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.place_holder_view);
                            if (findChildViewById2 != null) {
                                i = R.id.recycler_view;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.recycler_view);
                                if (recyclerView != null) {
                                    i = R.id.rl_header;
                                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_header);
                                    if (relativeLayout != null) {
                                        i = R.id.tv_amount;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_amount);
                                        if (textView != null) {
                                            i = R.id.tv_balance_title;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance_title);
                                            if (textView2 != null) {
                                                i = R.id.tv_contract;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract);
                                                if (textView3 != null) {
                                                    i = R.id.tv_contract_label;
                                                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_label);
                                                    if (textView4 != null) {
                                                        i = R.id.tv_contract_tag;
                                                        TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_contract_tag);
                                                        if (textView5 != null) {
                                                            i = R.id.tv_risk_amount;
                                                            TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_risk_amount);
                                                            if (textView6 != null) {
                                                                i = R.id.tv_token_name;
                                                                TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_name);
                                                                if (textView7 != null) {
                                                                    i = R.id.tv_unknow_contract;
                                                                    TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_unknow_contract);
                                                                    if (textView8 != null) {
                                                                        return new ItemApproveRiskBinding((ConstraintLayout) view, findChildViewById, tokenLogoDraweeView, imageView, imageView2, imageView3, findChildViewById2, recyclerView, relativeLayout, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
