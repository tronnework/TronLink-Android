package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemIndicator1Binding implements ViewBinding {
    public final View indicator;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemIndicator1Binding(RelativeLayout relativeLayout, View view) {
        this.rootView = relativeLayout;
        this.indicator = view;
    }

    public static ItemIndicator1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemIndicator1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_indicator1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemIndicator1Binding bind(View view) {
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.indicator);
        if (findChildViewById != null) {
            return new ItemIndicator1Binding((RelativeLayout) view, findChildViewById);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.indicator)));
    }
}
