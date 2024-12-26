package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgNoscreenshotsBinding implements ViewBinding {
    public final Button btKnow;
    public final LinearLayout content;
    private final LinearLayout rootView;
    public final TextView tvWarning1;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private DgNoscreenshotsBinding(LinearLayout linearLayout, Button button, LinearLayout linearLayout2, TextView textView) {
        this.rootView = linearLayout;
        this.btKnow = button;
        this.content = linearLayout2;
        this.tvWarning1 = textView;
    }

    public static DgNoscreenshotsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgNoscreenshotsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_noscreenshots, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgNoscreenshotsBinding bind(View view) {
        int i = R.id.bt_know;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_know);
        if (button != null) {
            LinearLayout linearLayout = (LinearLayout) view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_warning1);
            if (textView != null) {
                return new DgNoscreenshotsBinding(linearLayout, button, linearLayout, textView);
            }
            i = R.id.tv_warning1;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
