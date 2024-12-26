package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DgUpdateBinding implements ViewBinding {
    public final EditText etPassword;
    public final TextView innerTitle;
    private final RelativeLayout rootView;
    public final TextView title;
    public final RelativeLayout top;
    public final TextView tvCancle;
    public final TextView tvOk;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgUpdateBinding(RelativeLayout relativeLayout, EditText editText, TextView textView, TextView textView2, RelativeLayout relativeLayout2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.etPassword = editText;
        this.innerTitle = textView;
        this.title = textView2;
        this.top = relativeLayout2;
        this.tvCancle = textView3;
        this.tvOk = textView4;
    }

    public static DgUpdateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_update, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgUpdateBinding bind(View view) {
        int i = R.id.et_password;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_password);
        if (editText != null) {
            i = R.id.inner_title;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.inner_title);
            if (textView != null) {
                i = R.id.title;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                if (textView2 != null) {
                    i = R.id.top;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                    if (relativeLayout != null) {
                        i = R.id.tv_cancle;
                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                        if (textView3 != null) {
                            i = R.id.tv_ok;
                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok);
                            if (textView4 != null) {
                                return new DgUpdateBinding((RelativeLayout) view, editText, textView, textView2, relativeLayout, textView3, textView4);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
