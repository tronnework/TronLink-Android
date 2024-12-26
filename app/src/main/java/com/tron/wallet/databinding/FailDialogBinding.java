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
public final class FailDialogBinding implements ViewBinding {
    public final LinearLayout llDouble;
    private final RelativeLayout rootView;
    public final Button tvCancle;
    public final TextView tvContent;
    public final Button tvOk;
    public final TextView tvOk2;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FailDialogBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, Button button, TextView textView, Button button2, TextView textView2) {
        this.rootView = relativeLayout;
        this.llDouble = linearLayout;
        this.tvCancle = button;
        this.tvContent = textView;
        this.tvOk = button2;
        this.tvOk2 = textView2;
    }

    public static FailDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FailDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fail_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FailDialogBinding bind(View view) {
        int i = R.id.ll_double;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_double);
        if (linearLayout != null) {
            i = R.id.tv_cancle;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.tv_cancle);
            if (button != null) {
                i = R.id.tv_content;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
                if (textView != null) {
                    i = R.id.tv_ok;
                    Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.tv_ok);
                    if (button2 != null) {
                        i = R.id.tv_ok2;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok2);
                        if (textView2 != null) {
                            return new FailDialogBinding((RelativeLayout) view, linearLayout, button, textView, button2, textView2);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
