package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DialogPairColdWalletWarningBinding implements ViewBinding {
    public final Button btnConfirm;
    public final LinearLayout llEnterPair;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private DialogPairColdWalletWarningBinding(LinearLayout linearLayout, Button button, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.btnConfirm = button;
        this.llEnterPair = linearLayout2;
    }

    public static DialogPairColdWalletWarningBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogPairColdWalletWarningBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_pair_cold_wallet_warning, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogPairColdWalletWarningBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.ll_enter_pair;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_enter_pair);
            if (linearLayout != null) {
                return new DialogPairColdWalletWarningBinding((LinearLayout) view, button, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
