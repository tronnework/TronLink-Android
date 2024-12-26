package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemSecurity3Binding implements ViewBinding {
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurity3Binding(RelativeLayout relativeLayout) {
        this.rootView = relativeLayout;
    }

    public static ItemSecurity3Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurity3Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_3, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurity3Binding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemSecurity3Binding((RelativeLayout) view);
    }
}
