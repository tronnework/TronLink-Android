package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutPendingLoadingBinding implements ViewBinding {
    public final Button btnDoneLoading;
    public final Button btnTransactionInfo;
    public final ImageView loadingIcon;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private LayoutPendingLoadingBinding(RelativeLayout relativeLayout, Button button, Button button2, ImageView imageView) {
        this.rootView = relativeLayout;
        this.btnDoneLoading = button;
        this.btnTransactionInfo = button2;
        this.loadingIcon = imageView;
    }

    public static LayoutPendingLoadingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutPendingLoadingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_pending_loading, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutPendingLoadingBinding bind(View view) {
        int i = R.id.btn_done_loading;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_done_loading);
        if (button != null) {
            i = R.id.btn_transaction_info;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_transaction_info);
            if (button2 != null) {
                i = R.id.loading_icon;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.loading_icon);
                if (imageView != null) {
                    return new LayoutPendingLoadingBinding((RelativeLayout) view, button, button2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
