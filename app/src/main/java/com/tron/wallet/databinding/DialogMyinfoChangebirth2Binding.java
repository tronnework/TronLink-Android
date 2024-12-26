package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.pickerview.pickerview.wheelview.view.WheelView;
import com.tronlinkpro.wallet.R;
public final class DialogMyinfoChangebirth2Binding implements ViewBinding {
    public final TextView bgtext;
    public final TextView btnMyinfoSure;
    public final RelativeLayout lyMyinfoChangeaddress;
    public final RelativeLayout lyMyinfoChangeaddressChild;
    private final RelativeLayout rootView;
    public final LinearLayout wheelview;
    public final WheelView wvBirthDay;
    public final WheelView wvBirthMonth;
    public final WheelView wvBirthYear;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DialogMyinfoChangebirth2Binding(RelativeLayout relativeLayout, TextView textView, TextView textView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, LinearLayout linearLayout, WheelView wheelView, WheelView wheelView2, WheelView wheelView3) {
        this.rootView = relativeLayout;
        this.bgtext = textView;
        this.btnMyinfoSure = textView2;
        this.lyMyinfoChangeaddress = relativeLayout2;
        this.lyMyinfoChangeaddressChild = relativeLayout3;
        this.wheelview = linearLayout;
        this.wvBirthDay = wheelView;
        this.wvBirthMonth = wheelView2;
        this.wvBirthYear = wheelView3;
    }

    public static DialogMyinfoChangebirth2Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DialogMyinfoChangebirth2Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dialog_myinfo_changebirth2, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DialogMyinfoChangebirth2Binding bind(View view) {
        int i = R.id.bgtext;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.bgtext);
        if (textView != null) {
            i = R.id.btn_myinfo_sure;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.btn_myinfo_sure);
            if (textView2 != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.ly_myinfo_changeaddress_child;
                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.ly_myinfo_changeaddress_child);
                if (relativeLayout2 != null) {
                    i = R.id.wheelview;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.wheelview);
                    if (linearLayout != null) {
                        i = R.id.wv_birth_day;
                        WheelView wheelView = (WheelView) ViewBindings.findChildViewById(view, R.id.wv_birth_day);
                        if (wheelView != null) {
                            i = R.id.wv_birth_month;
                            WheelView wheelView2 = (WheelView) ViewBindings.findChildViewById(view, R.id.wv_birth_month);
                            if (wheelView2 != null) {
                                i = R.id.wv_birth_year;
                                WheelView wheelView3 = (WheelView) ViewBindings.findChildViewById(view, R.id.wv_birth_year);
                                if (wheelView3 != null) {
                                    return new DialogMyinfoChangebirth2Binding(relativeLayout, textView, textView2, relativeLayout, relativeLayout2, linearLayout, wheelView, wheelView2, wheelView3);
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
