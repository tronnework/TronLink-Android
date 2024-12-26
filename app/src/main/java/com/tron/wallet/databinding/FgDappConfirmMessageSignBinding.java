package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.confirm.fg.component.GlobalTitleHeaderView;
import com.tronlinkpro.wallet.R;
public final class FgDappConfirmMessageSignBinding implements ViewBinding {
    public final GlobalTitleHeaderView headerView;
    public final TextView messageContent;
    private final RelativeLayout rootView;
    public final LinearLayout rootview;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FgDappConfirmMessageSignBinding(RelativeLayout relativeLayout, GlobalTitleHeaderView globalTitleHeaderView, TextView textView, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.headerView = globalTitleHeaderView;
        this.messageContent = textView;
        this.rootview = linearLayout;
    }

    public static FgDappConfirmMessageSignBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FgDappConfirmMessageSignBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fg_dapp_confirm_message_sign, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FgDappConfirmMessageSignBinding bind(View view) {
        int i = R.id.header_view;
        GlobalTitleHeaderView globalTitleHeaderView = (GlobalTitleHeaderView) ViewBindings.findChildViewById(view, R.id.header_view);
        if (globalTitleHeaderView != null) {
            i = R.id.message_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.message_content);
            if (textView != null) {
                i = R.id.rootview;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.rootview);
                if (linearLayout != null) {
                    return new FgDappConfirmMessageSignBinding((RelativeLayout) view, globalTitleHeaderView, textView, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
