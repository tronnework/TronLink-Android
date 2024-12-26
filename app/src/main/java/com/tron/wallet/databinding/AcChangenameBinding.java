package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcChangenameBinding implements ViewBinding {
    public final Button btNext;
    public final EditText etName;
    public final ImageView ivName;
    public final RelativeLayout rlErrorName;
    private final RelativeLayout rootView;
    public final TextView tvNameError;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcChangenameBinding(RelativeLayout relativeLayout, Button button, EditText editText, ImageView imageView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.btNext = button;
        this.etName = editText;
        this.ivName = imageView;
        this.rlErrorName = relativeLayout2;
        this.tvNameError = textView;
    }

    public static AcChangenameBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcChangenameBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_changename, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcChangenameBinding bind(View view) {
        int i = R.id.bt_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
        if (button != null) {
            i = R.id.et_name;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_name);
            if (editText != null) {
                i = R.id.iv_name;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_name);
                if (imageView != null) {
                    i = R.id.rl_error_name;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_error_name);
                    if (relativeLayout != null) {
                        i = R.id.tv_name_error;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                        if (textView != null) {
                            return new AcChangenameBinding((RelativeLayout) view, button, editText, imageView, relativeLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
