package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class LayoutCommonTitleDesLayoutBinding implements ViewBinding {
    public final EditText etTransferAddress;
    public final ImageView ivOneDelete;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private LayoutCommonTitleDesLayoutBinding(LinearLayout linearLayout, EditText editText, ImageView imageView) {
        this.rootView = linearLayout;
        this.etTransferAddress = editText;
        this.ivOneDelete = imageView;
    }

    public static LayoutCommonTitleDesLayoutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static LayoutCommonTitleDesLayoutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.layout_common_title_des_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static LayoutCommonTitleDesLayoutBinding bind(View view) {
        int i = R.id.et_transfer_address;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_transfer_address);
        if (editText != null) {
            i = R.id.iv_one_delete;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_one_delete);
            if (imageView != null) {
                return new LayoutCommonTitleDesLayoutBinding((LinearLayout) view, editText, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
