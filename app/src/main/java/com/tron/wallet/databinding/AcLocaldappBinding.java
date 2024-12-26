package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalConfirmButton;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tron.wallet.common.components.xtablayout.XTabLayout;
import com.tronlinkpro.wallet.R;
public final class AcLocaldappBinding implements ViewBinding {
    public final GlobalConfirmButton buttonConfirm;
    public final FrameLayout content;
    public final GlobalTitleHeaderView headerView;
    public final LinearLayout llContent;
    public final RelativeLayout root;
    private final RelativeLayout rootView;
    public final XTabLayout tablayout;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcLocaldappBinding(RelativeLayout relativeLayout, GlobalConfirmButton globalConfirmButton, FrameLayout frameLayout, GlobalTitleHeaderView globalTitleHeaderView, LinearLayout linearLayout, RelativeLayout relativeLayout2, XTabLayout xTabLayout) {
        this.rootView = relativeLayout;
        this.buttonConfirm = globalConfirmButton;
        this.content = frameLayout;
        this.headerView = globalTitleHeaderView;
        this.llContent = linearLayout;
        this.root = relativeLayout2;
        this.tablayout = xTabLayout;
    }

    public static AcLocaldappBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcLocaldappBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_localdapp, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcLocaldappBinding bind(View view) {
        int i = R.id.button_confirm;
        GlobalConfirmButton globalConfirmButton = (GlobalConfirmButton) ViewBindings.findChildViewById(view, R.id.button_confirm);
        if (globalConfirmButton != null) {
            i = R.id.content;
            FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.content);
            if (frameLayout != null) {
                i = R.id.header_view;
                GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
                if (globalTitleHeaderView != null) {
                    i = R.id.ll_content;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
                    if (linearLayout != null) {
                        RelativeLayout relativeLayout = (RelativeLayout) view;
                        i = R.id.tablayout;
                        XTabLayout xTabLayout = (XTabLayout) ViewBindings.findChildViewById(view, R.id.tablayout);
                        if (xTabLayout != null) {
                            return new AcLocaldappBinding(relativeLayout, globalConfirmButton, frameLayout, globalTitleHeaderView, linearLayout, relativeLayout, xTabLayout);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
