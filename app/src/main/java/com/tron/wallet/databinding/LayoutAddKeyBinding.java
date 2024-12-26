package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.TrimEditText;
import com.tronlinkpro.wallet.R;
public final class LayoutAddKeyBinding implements ViewBinding {
    public final TrimEditText etKeyAddress;
    public final EditText etWeight;
    public final ImageView ivDelKeys;
    public final ImageView ivKey;
    public final RelativeLayout rlAddress;
    private final LinearLayout rootView;
    public final TextView tvAddkeyTip;
    public final View viewBorder;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutAddKeyBinding(LinearLayout linearLayout, TrimEditText trimEditText, EditText editText, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout, TextView textView, View view) {
        this.rootView = linearLayout;
        this.etKeyAddress = trimEditText;
        this.etWeight = editText;
        this.ivDelKeys = imageView;
        this.ivKey = imageView2;
        this.rlAddress = relativeLayout;
        this.tvAddkeyTip = textView;
        this.viewBorder = view;
    }

    public static LayoutAddKeyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutAddKeyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_add_key, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutAddKeyBinding bind(View view) {
        int i = R.id.et_key_address;
        TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_key_address);
        if (trimEditText != null) {
            i = R.id.et_weight;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_weight);
            if (editText != null) {
                i = R.id.iv_del_keys;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_del_keys);
                if (imageView != null) {
                    i = R.id.iv_key;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_key);
                    if (imageView2 != null) {
                        i = R.id.rl_address;
                        RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address);
                        if (relativeLayout != null) {
                            i = R.id.tv_addkey_tip;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_addkey_tip);
                            if (textView != null) {
                                i = R.id.view_border;
                                View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_border);
                                if (findChildViewById != null) {
                                    return new LayoutAddKeyBinding((LinearLayout) view, trimEditText, editText, imageView, imageView2, relativeLayout, textView, findChildViewById);
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
