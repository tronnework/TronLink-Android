package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import com.tronlinkpro.wallet.R;
public final class PickerviewOptionsBinding implements ViewBinding {
    public final WheelView options1;
    public final WheelView options2;
    public final WheelView options3;
    public final LinearLayout optionspicker;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PickerviewOptionsBinding(LinearLayout linearLayout, WheelView wheelView, WheelView wheelView2, WheelView wheelView3, LinearLayout linearLayout2) {
        this.rootView = linearLayout;
        this.options1 = wheelView;
        this.options2 = wheelView2;
        this.options3 = wheelView3;
        this.optionspicker = linearLayout2;
    }

    public static PickerviewOptionsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PickerviewOptionsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pickerview_options, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PickerviewOptionsBinding bind(View view) {
        int i = R.id.options1;
        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(view, R.id.options1);
        if (wheelView != null) {
            i = R.id.options2;
            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(view, R.id.options2);
            if (wheelView2 != null) {
                i = R.id.options3;
                WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(view, R.id.options3);
                if (wheelView3 != null) {
                    i = R.id.optionspicker;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.optionspicker);
                    if (linearLayout != null) {
                        return new PickerviewOptionsBinding((LinearLayout) view, wheelView, wheelView2, wheelView3, linearLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
