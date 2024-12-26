package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcDappdemoBinding implements ViewBinding {
    public final ImageView bt;
    public final EditText etUrl;
    public final RecyclerView rcContent;
    public final RelativeLayout rlInput;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcDappdemoBinding(LinearLayout linearLayout, ImageView imageView, EditText editText, RecyclerView recyclerView, RelativeLayout relativeLayout) {
        this.rootView = linearLayout;
        this.bt = imageView;
        this.etUrl = editText;
        this.rcContent = recyclerView;
        this.rlInput = relativeLayout;
    }

    public static AcDappdemoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcDappdemoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_dappdemo, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcDappdemoBinding bind(View view) {
        int i = R.id.bt;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.bt);
        if (imageView != null) {
            i = R.id.et_url;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_url);
            if (editText != null) {
                i = R.id.rc_content;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_content);
                if (recyclerView != null) {
                    i = R.id.rl_input;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_input);
                    if (relativeLayout != null) {
                        return new AcDappdemoBinding((LinearLayout) view, imageView, editText, recyclerView, relativeLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
