package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class FgConfirmStakeVoteBinding implements ViewBinding {
    public final GlobalConfirmButton buttonConfirm;
    public final GlobalTitleHeaderView headerView;
    public final ImageView ivArrowRight;
    public final RelativeLayout llArrow;
    public final GlobalFeeResourceView resourceView;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvExtras;
    public final RecyclerView rvInfo;
    public final TextView tvTip;
    public final TextView tvVoteSr;
    public final TextView voteSrsTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmStakeVoteBinding(RelativeLayout relativeLayout, GlobalConfirmButton globalConfirmButton, GlobalTitleHeaderView globalTitleHeaderView, ImageView imageView, RelativeLayout relativeLayout2, GlobalFeeResourceView globalFeeResourceView, RelativeLayout relativeLayout3, RecyclerView recyclerView, RecyclerView recyclerView2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.buttonConfirm = globalConfirmButton;
        this.headerView = globalTitleHeaderView;
        this.ivArrowRight = imageView;
        this.llArrow = relativeLayout2;
        this.resourceView = globalFeeResourceView;
        this.root = relativeLayout3;
        this.rvExtras = recyclerView;
        this.rvInfo = recyclerView2;
        this.tvTip = textView;
        this.tvVoteSr = textView2;
        this.voteSrsTitle = textView3;
    }

    public static FgConfirmStakeVoteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmStakeVoteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_stake_vote, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmStakeVoteBinding bind(View view) {
        int i = R.id.button_confirm;
        GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.button_confirm);
        if (globalConfirmButton != null) {
            i = R.id.header_view;
            GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
            if (globalTitleHeaderView != null) {
                i = R.id.iv_arrow_right;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow_right);
                if (imageView != null) {
                    i = R.id.ll_arrow;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ll_arrow);
                    if (relativeLayout != null) {
                        i = R.id.resource_view;
                        GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                        if (globalFeeResourceView != null) {
                            i = R.id.root;
                            RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.root);
                            if (relativeLayout2 != null) {
                                i = R.id.rv_extras;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_extras);
                                if (recyclerView != null) {
                                    i = R.id.rv_info;
                                    RecyclerView recyclerView2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_info);
                                    if (recyclerView2 != null) {
                                        i = R.id.tv_tip;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_tip);
                                        if (textView != null) {
                                            i = R.id.tv_vote_sr;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_vote_sr);
                                            if (textView2 != null) {
                                                i = R.id.vote_srs_title;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.vote_srs_title);
                                                if (textView3 != null) {
                                                    return new FgConfirmStakeVoteBinding((RelativeLayout) view, globalConfirmButton, globalTitleHeaderView, imageView, relativeLayout, globalFeeResourceView, relativeLayout2, recyclerView, recyclerView2, textView, textView2, textView3);
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
