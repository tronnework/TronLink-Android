package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.tabmy.walletmanage.MnemonicIndexView;
import com.tron.wallet.business.walletmanager.createwallet.view.TimelineView;
import com.tronlinkpro.wallet.R;
public final class AcVerifyMnemonicBinding implements ViewBinding {
    public final Button btNext;
    public final MnemonicIndexView indexGrid;
    private final RelativeLayout rootView;
    public final TimelineView timeLineLayout;
    public final LinearLayout verifyContent;
    public final View white;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcVerifyMnemonicBinding(RelativeLayout relativeLayout, Button button, MnemonicIndexView mnemonicIndexView, TimelineView timelineView, LinearLayout linearLayout, View view) {
        this.rootView = relativeLayout;
        this.btNext = button;
        this.indexGrid = mnemonicIndexView;
        this.timeLineLayout = timelineView;
        this.verifyContent = linearLayout;
        this.white = view;
    }

    public static AcVerifyMnemonicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcVerifyMnemonicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_verify_mnemonic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcVerifyMnemonicBinding bind(View view) {
        int i = R.id.bt_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
        if (button != null) {
            i = R.id.index_grid;
            MnemonicIndexView mnemonicIndexView = (MnemonicIndexView) ViewBindings.findChildViewById(view, R.id.index_grid);
            if (mnemonicIndexView != null) {
                i = R.id.time_line_layout;
                TimelineView timelineView = (TimelineView) ViewBindings.findChildViewById(view, R.id.time_line_layout);
                if (timelineView != null) {
                    i = R.id.verify_content;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.verify_content);
                    if (linearLayout != null) {
                        i = R.id.white;
                        View findChildViewById = ViewBindings.findChildViewById(view, R.id.white);
                        if (findChildViewById != null) {
                            return new AcVerifyMnemonicBinding((RelativeLayout) view, button, mnemonicIndexView, timelineView, linearLayout, findChildViewById);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
