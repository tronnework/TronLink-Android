package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemSecurity2Binding implements ViewBinding {
    private final ConstraintLayout rootView;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private ItemSecurity2Binding(ConstraintLayout constraintLayout) {
        this.rootView = constraintLayout;
    }

    public static ItemSecurity2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSecurity2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_security_2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSecurity2Binding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemSecurity2Binding((ConstraintLayout) view);
    }
}
