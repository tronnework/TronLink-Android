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
public final class AcSelectTimeTestBinding implements ViewBinding {
    public final Button btn1;
    private final LinearLayout rootView;
    public final TextView tvContent;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcSelectTimeTestBinding(LinearLayout linearLayout, Button button, TextView textView) {
        this.rootView = linearLayout;
        this.btn1 = button;
        this.tvContent = textView;
    }

    public static AcSelectTimeTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectTimeTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_time_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectTimeTestBinding bind(View view) {
        int i = R.id.btn_1;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_1);
        if (button != null) {
            i = R.id.tv_content;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
            if (textView != null) {
                return new AcSelectTimeTestBinding((LinearLayout) view, button, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
