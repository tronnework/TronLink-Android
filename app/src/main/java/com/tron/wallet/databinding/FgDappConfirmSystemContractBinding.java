package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tronlinkpro.wallet.R;
public final class FgDappConfirmSystemContractBinding implements ViewBinding {
    public final FrameLayout content;
    public final GlobalTitleHeaderView headerView;
    public final LinearLayout root;
    private final RelativeLayout rootView;
    public final XTabLayout tablayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgDappConfirmSystemContractBinding(RelativeLayout relativeLayout, FrameLayout frameLayout, GlobalTitleHeaderView globalTitleHeaderView, LinearLayout linearLayout, XTabLayout xTabLayout) {
        this.rootView = relativeLayout;
        this.content = frameLayout;
        this.headerView = globalTitleHeaderView;
        this.root = linearLayout;
        this.tablayout = xTabLayout;
    }

    public static FgDappConfirmSystemContractBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgDappConfirmSystemContractBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_dapp_confirm_system_contract, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgDappConfirmSystemContractBinding bind(View view) {
        int i = R.id.content;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.content);
        if (frameLayout != null) {
            i = R.id.header_view;
            GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
            if (globalTitleHeaderView != null) {
                i = R.id.root;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.root);
                if (linearLayout != null) {
                    i = R.id.tablayout;
                    XTabLayout xTabLayout = (XTabLayout) ViewBindings.findChildViewById(view, R.id.tablayout);
                    if (xTabLayout != null) {
                        return new FgDappConfirmSystemContractBinding((RelativeLayout) view, frameLayout, globalTitleHeaderView, linearLayout, xTabLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
