package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopHardwareConnectFailBinding implements ViewBinding {
    public final ImageView ivCloseForConnectError;
    public final RelativeLayout rlConnectFail;
    private final RelativeLayout rootView;
    public final TextView tvConnectFail;
    public final TextView tvConnectFailContent;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopHardwareConnectFailBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ivCloseForConnectError = imageView;
        this.rlConnectFail = relativeLayout2;
        this.tvConnectFail = textView;
        this.tvConnectFailContent = textView2;
    }

    public static PopHardwareConnectFailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopHardwareConnectFailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_hardware_connect_fail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopHardwareConnectFailBinding bind(View view) {
        int i = R.id.iv_close_for_connect_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_for_connect_error);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            i = R.id.tv_connect_fail;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_connect_fail);
            if (textView != null) {
                i = R.id.tv_connect_fail_content;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_connect_fail_content);
                if (textView2 != null) {
                    return new PopHardwareConnectFailBinding(relativeLayout, imageView, relativeLayout, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
