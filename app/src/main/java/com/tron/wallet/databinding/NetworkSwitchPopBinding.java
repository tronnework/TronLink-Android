package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class NetworkSwitchPopBinding implements ViewBinding {
    public final Button btnConfirm;
    public final Button btnKnow;
    public final LinearLayout llCold;
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvContent;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private NetworkSwitchPopBinding(RelativeLayout relativeLayout, Button button, Button button2, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.btnConfirm = button;
        this.btnKnow = button2;
        this.llCold = linearLayout;
        this.tvCancle = textView;
        this.tvContent = textView2;
        this.tvTitle = textView3;
    }

    public static NetworkSwitchPopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NetworkSwitchPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.network_switch_pop, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NetworkSwitchPopBinding bind(View view) {
        int i = R.id.btn_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_confirm);
        if (button != null) {
            i = R.id.btn_know;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.btn_know);
            if (button2 != null) {
                i = R.id.ll_cold;
                LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_cold);
                if (linearLayout != null) {
                    i = R.id.tv_cancle;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                    if (textView != null) {
                        i = R.id.tv_content;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                        if (textView2 != null) {
                            i = R.id.tv_title;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView3 != null) {
                                return new NetworkSwitchPopBinding((RelativeLayout) view, button, button2, linearLayout, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
