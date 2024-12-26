package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class NodataviewBinding implements ViewBinding {
    public final RelativeLayout rlNodapp;
    private final RelativeLayout rootView;
    public final TextView tvNoDapp;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NodataviewBinding(RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.rlNodapp = relativeLayout2;
        this.tvNoDapp = textView;
    }

    public static NodataviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NodataviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nodataview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NodataviewBinding bind(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_no_dapp);
        if (textView != null) {
            return new NodataviewBinding(relativeLayout, relativeLayout, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_no_dapp)));
    }
}
