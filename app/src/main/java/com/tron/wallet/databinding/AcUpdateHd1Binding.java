package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcUpdateHd1Binding implements ViewBinding {
    public final TextView btCancel;
    public final Button btNext;
    public final LinearLayout loadingView;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcUpdateHd1Binding(RelativeLayout relativeLayout, TextView textView, Button button, LinearLayout linearLayout) {
        this.rootView = relativeLayout;
        this.btCancel = textView;
        this.btNext = button;
        this.loadingView = linearLayout;
    }

    public static AcUpdateHd1Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUpdateHd1Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_update_hd_1, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUpdateHd1Binding bind(View view) {
        int i = R.id.bt_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bt_cancel);
        if (textView != null) {
            i = R.id.bt_next;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
            if (button != null) {
                i = R.id.loading_view;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.loading_view);
                if (linearLayout != null) {
                    return new AcUpdateHd1Binding((RelativeLayout) view, textView, button, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
