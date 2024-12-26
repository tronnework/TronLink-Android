package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.business.walletmanager.createwallet.view.TimelineView;
import com.tronlinkpro.wallet.R;
public final class AcBackupMnemonicBinding implements ViewBinding {
    public final FrameLayout frame;
    private final LinearLayout rootView;
    public final TimelineView timeLineLayout;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcBackupMnemonicBinding(LinearLayout linearLayout, FrameLayout frameLayout, TimelineView timelineView) {
        this.rootView = linearLayout;
        this.frame = frameLayout;
        this.timeLineLayout = timelineView;
    }

    public static AcBackupMnemonicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBackupMnemonicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_backup_mnemonic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBackupMnemonicBinding bind(View view) {
        int i = R.id.frame;
        FrameLayout frameLayout = (FrameLayout) ViewBindings.findChildViewById(view, R.id.frame);
        if (frameLayout != null) {
            i = R.id.time_line_layout;
            TimelineView timelineView = (TimelineView) ViewBindings.findChildViewById(view, R.id.time_line_layout);
            if (timelineView != null) {
                return new AcBackupMnemonicBinding((LinearLayout) view, frameLayout, timelineView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
