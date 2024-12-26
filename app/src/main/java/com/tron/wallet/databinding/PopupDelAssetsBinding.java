package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupDelAssetsBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tv1;
    public final TextView tv2;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PopupDelAssetsBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.tv1 = textView;
        this.tv2 = textView2;
    }

    public static PopupDelAssetsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupDelAssetsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_del_assets, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupDelAssetsBinding bind(View view) {
        int i = R.id.tv_1;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_1);
        if (textView != null) {
            i = R.id.tv_2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_2);
            if (textView2 != null) {
                return new PopupDelAssetsBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
