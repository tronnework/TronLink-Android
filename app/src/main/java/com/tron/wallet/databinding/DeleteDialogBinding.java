package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DeleteDialogBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvCancle;
    public final TextView tvContent;
    public final TextView tvOk;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DeleteDialogBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.tvCancle = textView;
        this.tvContent = textView2;
        this.tvOk = textView3;
        this.tvTitle = textView4;
    }

    public static DeleteDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DeleteDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.delete_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DeleteDialogBinding bind(View view) {
        int i = R.id.tv_cancle;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
        if (textView != null) {
            i = R.id.tv_content;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_content);
            if (textView2 != null) {
                i = R.id.tv_ok;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok);
                if (textView3 != null) {
                    i = R.id.tv_title;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView4 != null) {
                        return new DeleteDialogBinding((RelativeLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
