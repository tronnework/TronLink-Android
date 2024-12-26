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
public final class PopHardwareAddressFailBinding implements ViewBinding {
    public final ImageView ivCloseForAddressError;
    public final RelativeLayout rlDeviceNotMatched;
    private final RelativeLayout rootView;
    public final TextView tvDeviceNotMatched;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopHardwareAddressFailBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.ivCloseForAddressError = imageView;
        this.rlDeviceNotMatched = relativeLayout2;
        this.tvDeviceNotMatched = textView;
    }

    public static PopHardwareAddressFailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopHardwareAddressFailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_hardware_address_fail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopHardwareAddressFailBinding bind(View view) {
        int i = R.id.iv_close_for_address_error;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_for_address_error);
        if (imageView != null) {
            RelativeLayout relativeLayout = (RelativeLayout) view;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_device_not_matched);
            if (textView != null) {
                return new PopHardwareAddressFailBinding(relativeLayout, imageView, relativeLayout, textView);
            }
            i = R.id.tv_device_not_matched;
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
