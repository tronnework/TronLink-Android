package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemSecurityHeader1Binding implements ViewBinding {
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurityHeader1Binding(LinearLayout linearLayout) {
        this.rootView = linearLayout;
    }

    public static ItemSecurityHeader1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurityHeader1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_header_1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurityHeader1Binding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemSecurityHeader1Binding((LinearLayout) view);
    }
}
