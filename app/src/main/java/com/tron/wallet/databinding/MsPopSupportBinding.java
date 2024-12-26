package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class MsPopSupportBinding implements ViewBinding {
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private MsPopSupportBinding(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    public static MsPopSupportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MsPopSupportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ms_pop_support, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static MsPopSupportBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new MsPopSupportBinding((RelativeLayout) view);
    }
}
