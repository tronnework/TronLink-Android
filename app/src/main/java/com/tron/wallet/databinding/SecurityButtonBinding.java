package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class SecurityButtonBinding implements ViewBinding {
    public final RelativeLayout btCheck;
    private final LinearLayout rootView;
    public final TextView tvText;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private SecurityButtonBinding(LinearLayout linearLayout, RelativeLayout relativeLayout, TextView textView) {
        this.rootView = linearLayout;
        this.btCheck = relativeLayout;
        this.tvText = textView;
    }

    public static SecurityButtonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SecurityButtonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.security_button, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SecurityButtonBinding bind(View view) {
        int i = R.id.bt_check;
        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.bt_check);
        if (relativeLayout != null) {
            i = R.id.tv_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_text);
            if (textView != null) {
                return new SecurityButtonBinding((LinearLayout) view, relativeLayout, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
