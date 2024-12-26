package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.SwitchButton;
import com.tronlinkpro.wallet.R;
public final class AcMessagenotificationBinding implements ViewBinding {
    public final ImageView back3;
    public final RelativeLayout notificationSystem;
    private final LinearLayout rootView;
    public final SwitchButton switchButton;
    public final TextView tvHint;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcMessagenotificationBinding(LinearLayout linearLayout, ImageView imageView, RelativeLayout relativeLayout, SwitchButton switchButton, TextView textView) {
        this.rootView = linearLayout;
        this.back3 = imageView;
        this.notificationSystem = relativeLayout;
        this.switchButton = switchButton;
        this.tvHint = textView;
    }

    public static AcMessagenotificationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMessagenotificationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_messagenotification, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMessagenotificationBinding bind(View view) {
        int i = R.id.back3;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.back3);
        if (imageView != null) {
            i = R.id.notification_system;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.notification_system);
            if (relativeLayout != null) {
                i = R.id.switch_button;
                SwitchButton switchButton = (SwitchButton) ViewBindings.findChildViewById(view, R.id.switch_button);
                if (switchButton != null) {
                    i = R.id.tv_hint;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_hint);
                    if (textView != null) {
                        return new AcMessagenotificationBinding((LinearLayout) view, imageView, relativeLayout, switchButton, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
