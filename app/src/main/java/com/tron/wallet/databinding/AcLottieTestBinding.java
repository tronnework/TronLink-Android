package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcLottieTestBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final Button start;
    public final TextView tvUnderLine;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcLottieTestBinding(RelativeLayout relativeLayout, Button button, TextView textView) {
        this.rootView = relativeLayout;
        this.start = button;
        this.tvUnderLine = textView;
    }

    public static AcLottieTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcLottieTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_lottie_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcLottieTestBinding bind(View view) {
        int i = R.id.start;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.start);
        if (button != null) {
            i = R.id.tv_under_line;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_under_line);
            if (textView != null) {
                return new AcLottieTestBinding((RelativeLayout) view, button, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
