package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class SwapInstantSwapBinding implements ViewBinding {
    public final Button btnSwapInstant;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapInstantSwapBinding(RelativeLayout relativeLayout, Button button) {
        this.rootView = relativeLayout;
        this.btnSwapInstant = button;
    }

    public static SwapInstantSwapBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapInstantSwapBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_instant_swap, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapInstantSwapBinding bind(View view) {
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_swap_instant);
        if (button != null) {
            return new SwapInstantSwapBinding((RelativeLayout) view, button);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.btn_swap_instant)));
    }
}
