package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class EmptyFooterBinding implements ViewBinding {
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private EmptyFooterBinding(LinearLayout linearLayout) {
        this.rootView = linearLayout;
    }

    public static EmptyFooterBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static EmptyFooterBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.empty_footer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static EmptyFooterBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new EmptyFooterBinding((LinearLayout) view);
    }
}
