package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutMnemonicIndiciesBinding implements ViewBinding {
    public final TextView indicesTvNumber;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutMnemonicIndiciesBinding(RelativeLayout relativeLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.indicesTvNumber = textView;
    }

    public static LayoutMnemonicIndiciesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutMnemonicIndiciesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_mnemonic_indicies, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutMnemonicIndiciesBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.indices_tv_number);
        if (textView != null) {
            return new LayoutMnemonicIndiciesBinding((RelativeLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.indices_tv_number)));
    }
}
