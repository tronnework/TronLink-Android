package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemSecurity1Binding implements ViewBinding {
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurity1Binding(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    public static ItemSecurity1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurity1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurity1Binding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemSecurity1Binding((RelativeLayout) view);
    }
}
