package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class MarketDialogBinding implements ViewBinding {
    public final CheckBox ck;
    public final LinearLayout llShadowCancel;
    public final LinearLayout llShadowOk;
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvOk;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private MarketDialogBinding(RelativeLayout relativeLayout, CheckBox checkBox, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.ck = checkBox;
        this.llShadowCancel = linearLayout;
        this.llShadowOk = linearLayout2;
        this.tvCancle = textView;
        this.tvOk = textView2;
    }

    public static MarketDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static MarketDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.market_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static MarketDialogBinding bind(View view) {
        int i = R.id.ck;
        CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.ck);
        if (checkBox != null) {
            i = R.id.ll_shadow_cancel;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_shadow_cancel);
            if (linearLayout != null) {
                i = R.id.ll_shadow_ok;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_shadow_ok);
                if (linearLayout2 != null) {
                    i = R.id.tv_cancle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                    if (textView != null) {
                        i = R.id.tv_ok;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok);
                        if (textView2 != null) {
                            return new MarketDialogBinding((RelativeLayout) view, checkBox, linearLayout, linearLayout2, textView, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
