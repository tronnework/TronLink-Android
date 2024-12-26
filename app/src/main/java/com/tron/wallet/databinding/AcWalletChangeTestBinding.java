package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcWalletChangeTestBinding implements ViewBinding {
    public final Button bt1;
    public final Button bt2;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcWalletChangeTestBinding(LinearLayout linearLayout, Button button, Button button2) {
        this.rootView = linearLayout;
        this.bt1 = button;
        this.bt2 = button2;
    }

    public static AcWalletChangeTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcWalletChangeTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_wallet_change_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcWalletChangeTestBinding bind(View view) {
        int i = R.id.bt_1;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_1);
        if (button != null) {
            i = R.id.bt_2;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_2);
            if (button2 != null) {
                return new AcWalletChangeTestBinding((LinearLayout) view, button, button2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
