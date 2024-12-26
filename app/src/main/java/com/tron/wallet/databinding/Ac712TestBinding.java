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
public final class Ac712TestBinding implements ViewBinding {
    public final Button btCompare;
    private final LinearLayout rootView;
    public final TextView tvResult;
    public final TextView tvResult2;
    public final TextView tvResult3;
    public final TextView tvResult4;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private Ac712TestBinding(LinearLayout linearLayout, Button button, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.btCompare = button;
        this.tvResult = textView;
        this.tvResult2 = textView2;
        this.tvResult3 = textView3;
        this.tvResult4 = textView4;
    }

    public static Ac712TestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static Ac712TestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_712_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static Ac712TestBinding bind(View view) {
        int i = R.id.bt_compare;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_compare);
        if (button != null) {
            i = R.id.tv_result;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result);
            if (textView != null) {
                i = R.id.tv_result2;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result2);
                if (textView2 != null) {
                    i = R.id.tv_result3;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result3);
                    if (textView3 != null) {
                        i = R.id.tv_result4;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_result4);
                        if (textView4 != null) {
                            return new Ac712TestBinding((LinearLayout) view, button, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
