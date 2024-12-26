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
public final class AcUninstallhelperBinding implements ViewBinding {
    public final Button bt1;
    public final Button bt2;
    public final Button bt3;
    private final LinearLayout rootView;
    public final TextView tvContent;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcUninstallhelperBinding(LinearLayout linearLayout, Button button, Button button2, Button button3, TextView textView) {
        this.rootView = linearLayout;
        this.bt1 = button;
        this.bt2 = button2;
        this.bt3 = button3;
        this.tvContent = textView;
    }

    public static AcUninstallhelperBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcUninstallhelperBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_uninstallhelper, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcUninstallhelperBinding bind(View view) {
        int i = R.id.bt_1;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_1);
        if (button != null) {
            i = R.id.bt_2;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_2);
            if (button2 != null) {
                i = R.id.bt_3;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.bt_3);
                if (button3 != null) {
                    i = R.id.tv_content;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                    if (textView != null) {
                        return new AcUninstallhelperBinding((LinearLayout) view, button, button2, button3, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
