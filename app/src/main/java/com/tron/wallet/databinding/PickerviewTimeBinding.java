package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import com.tronlinkpro.wallet.R;
public final class PickerviewTimeBinding implements ViewBinding {
    public final WheelView day;
    public final WheelView hour;
    public final WheelView min;
    public final WheelView month;
    private final LinearLayout rootView;
    public final WheelView second;
    public final LinearLayout timepicker;
    public final WheelView year;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private PickerviewTimeBinding(LinearLayout linearLayout, WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, WheelView wheelView5, LinearLayout linearLayout2, WheelView wheelView6) {
        this.rootView = linearLayout;
        this.day = wheelView;
        this.hour = wheelView2;
        this.min = wheelView3;
        this.month = wheelView4;
        this.second = wheelView5;
        this.timepicker = linearLayout2;
        this.year = wheelView6;
    }

    public static PickerviewTimeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PickerviewTimeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pickerview_time, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PickerviewTimeBinding bind(View view) {
        int i = R.id.day;
        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(view, R.id.day);
        if (wheelView != null) {
            i = R.id.hour;
            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(view, R.id.hour);
            if (wheelView2 != null) {
                i = R.id.min;
                WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(view, R.id.min);
                if (wheelView3 != null) {
                    i = R.id.month;
                    WheelView wheelView4 = (WheelView) ViewBindings.findChildViewById(view, R.id.month);
                    if (wheelView4 != null) {
                        i = R.id.second;
                        WheelView wheelView5 = (WheelView) ViewBindings.findChildViewById(view, R.id.second);
                        if (wheelView5 != null) {
                            i = R.id.timepicker;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.timepicker);
                            if (linearLayout != null) {
                                i = R.id.year;
                                WheelView wheelView6 = (WheelView) ViewBindings.findChildViewById(view, R.id.year);
                                if (wheelView6 != null) {
                                    return new PickerviewTimeBinding((LinearLayout) view, wheelView, wheelView2, wheelView3, wheelView4, wheelView5, linearLayout, wheelView6);
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
