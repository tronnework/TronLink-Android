package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalFeeResourceView;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class FgConfirmProposalBinding implements ViewBinding {
    public final GlobalConfirmButton buttonConfirm;
    public final GlobalTitleHeaderView headerView;
    public final GlobalFeeResourceView resourceView;
    public final RelativeLayout rlType;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final RecyclerView rvExtras;
    public final TextView tvContentTitle;
    public final TextView tvType;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgConfirmProposalBinding(RelativeLayout relativeLayout, GlobalConfirmButton globalConfirmButton, GlobalTitleHeaderView globalTitleHeaderView, GlobalFeeResourceView globalFeeResourceView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.buttonConfirm = globalConfirmButton;
        this.headerView = globalTitleHeaderView;
        this.resourceView = globalFeeResourceView;
        this.rlType = relativeLayout2;
        this.root = relativeLayout3;
        this.rvExtras = recyclerView;
        this.tvContentTitle = textView;
        this.tvType = textView2;
    }

    public static FgConfirmProposalBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgConfirmProposalBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_confirm_proposal, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgConfirmProposalBinding bind(View view) {
        int i = R.id.button_confirm;
        GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.button_confirm);
        if (globalConfirmButton != null) {
            i = R.id.header_view;
            GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
            if (globalTitleHeaderView != null) {
                i = R.id.resource_view;
                GlobalFeeResourceView globalFeeResourceView = (GlobalFeeResourceView) ViewBindings.findChildViewById(view, R.id.resource_view);
                if (globalFeeResourceView != null) {
                    i = R.id.rl_type;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_type);
                    if (relativeLayout != null) {
                        i = R.id.root;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.root);
                        if (relativeLayout2 != null) {
                            i = R.id.rv_extras;
                            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_extras);
                            if (recyclerView != null) {
                                i = R.id.tv_content_title;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content_title);
                                if (textView != null) {
                                    i = R.id.tv_type;
                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_type);
                                    if (textView2 != null) {
                                        return new FgConfirmProposalBinding((RelativeLayout) view, globalConfirmButton, globalTitleHeaderView, globalFeeResourceView, relativeLayout, relativeLayout2, recyclerView, textView, textView2);
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
