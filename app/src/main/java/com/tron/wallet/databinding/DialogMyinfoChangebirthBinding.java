package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import com.tronlinkpro.wallet.R;
public final class DialogMyinfoChangebirthBinding implements ViewBinding {
    public final TextView btnMyinfoCancel;
    public final TextView btnMyinfoSure;
    public final LinearLayout lyMyinfoChangeaddress;
    public final LinearLayout lyMyinfoChangeaddressChild;
    private final LinearLayout rootView;
    public final WheelView wvBirthDay;
    public final WheelView wvBirthMonth;
    public final WheelView wvBirthYear;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private DialogMyinfoChangebirthBinding(LinearLayout linearLayout, TextView textView, TextView textView2, LinearLayout linearLayout2, LinearLayout linearLayout3, WheelView wheelView, WheelView wheelView2, WheelView wheelView3) {
        this.rootView = linearLayout;
        this.btnMyinfoCancel = textView;
        this.btnMyinfoSure = textView2;
        this.lyMyinfoChangeaddress = linearLayout2;
        this.lyMyinfoChangeaddressChild = linearLayout3;
        this.wvBirthDay = wheelView;
        this.wvBirthMonth = wheelView2;
        this.wvBirthYear = wheelView3;
    }

    public static DialogMyinfoChangebirthBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogMyinfoChangebirthBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_myinfo_changebirth, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogMyinfoChangebirthBinding bind(View view) {
        int i = R.id.btn_myinfo_cancel;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.btn_myinfo_cancel);
        if (textView != null) {
            i = R.id.btn_myinfo_sure;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_myinfo_sure);
            if (textView2 != null) {
                LinearLayout linearLayout = (LinearLayout) view;
                i = R.id.ly_myinfo_changeaddress_child;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ly_myinfo_changeaddress_child);
                if (linearLayout2 != null) {
                    i = R.id.wv_birth_day;
                    WheelView wheelView = (WheelView) ViewBindings.findChildViewById(view, R.id.wv_birth_day);
                    if (wheelView != null) {
                        i = R.id.wv_birth_month;
                        WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(view, R.id.wv_birth_month);
                        if (wheelView2 != null) {
                            i = R.id.wv_birth_year;
                            WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(view, R.id.wv_birth_year);
                            if (wheelView3 != null) {
                                return new DialogMyinfoChangebirthBinding(linearLayout, textView, textView2, linearLayout, linearLayout2, wheelView, wheelView2, wheelView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
