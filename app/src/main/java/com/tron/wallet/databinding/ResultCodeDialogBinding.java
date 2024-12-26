package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ResultCodeDialogBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvContent;
    public final TextView tvErrorCode;
    public final TextView tvOk2;
    public final TextView tvSubtitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ResultCodeDialogBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.tvContent = textView;
        this.tvErrorCode = textView2;
        this.tvOk2 = textView3;
        this.tvSubtitle = textView4;
    }

    public static ResultCodeDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ResultCodeDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.result_code_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ResultCodeDialogBinding bind(View view) {
        int i = R.id.tv_content;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
        if (textView != null) {
            i = R.id.tv_error_code;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_error_code);
            if (textView2 != null) {
                i = R.id.tv_ok2;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok2);
                if (textView3 != null) {
                    i = R.id.tv_subtitle;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_subtitle);
                    if (textView4 != null) {
                        return new ResultCodeDialogBinding((RelativeLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
