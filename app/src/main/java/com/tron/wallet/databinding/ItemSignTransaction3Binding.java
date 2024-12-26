package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemSignTransaction3Binding implements ViewBinding {
    public final RelativeLayout rlBg;
    private final RelativeLayout rootView;
    public final TextView tvNumber;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemSignTransaction3Binding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.rlBg = relativeLayout2;
        this.tvNumber = textView;
    }

    public static ItemSignTransaction3Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemSignTransaction3Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_sign_transaction_3, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemSignTransaction3Binding bind(View view) {
        int i = R.id.rl_bg;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_bg);
        if (relativeLayout != null) {
            i = R.id.tv_number;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_number);
            if (textView != null) {
                return new ItemSignTransaction3Binding((RelativeLayout) view, relativeLayout, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
