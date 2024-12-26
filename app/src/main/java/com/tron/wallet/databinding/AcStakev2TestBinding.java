package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcStakev2TestBinding implements ViewBinding {
    public final Button bt1;
    public final Button bt2;
    public final Button bt3;
    public final Button bt4;
    public final Button bt5;
    public final Button bt6;
    public final Button bt7;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcStakev2TestBinding(LinearLayout linearLayout, Button button, Button button2, Button button3, Button button4, Button button5, Button button6, Button button7) {
        this.rootView = linearLayout;
        this.bt1 = button;
        this.bt2 = button2;
        this.bt3 = button3;
        this.bt4 = button4;
        this.bt5 = button5;
        this.bt6 = button6;
        this.bt7 = button7;
    }

    public static AcStakev2TestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcStakev2TestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_stakev2_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcStakev2TestBinding bind(View view) {
        int i = R.id.bt_1;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_1);
        if (button != null) {
            i = R.id.bt_2;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_2);
            if (button2 != null) {
                i = R.id.bt_3;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.bt_3);
                if (button3 != null) {
                    i = R.id.bt_4;
                    Button button4 = (Button) ViewBindings.findChildViewById(view, R.id.bt_4);
                    if (button4 != null) {
                        i = R.id.bt_5;
                        Button button5 = (Button) ViewBindings.findChildViewById(view, R.id.bt_5);
                        if (button5 != null) {
                            i = R.id.bt_6;
                            Button button6 = (Button) ViewBindings.findChildViewById(view, R.id.bt_6);
                            if (button6 != null) {
                                i = R.id.bt_7;
                                Button button7 = (Button) ViewBindings.findChildViewById(view, R.id.bt_7);
                                if (button7 != null) {
                                    return new AcStakev2TestBinding((LinearLayout) view, button, button2, button3, button4, button5, button6, button7);
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
