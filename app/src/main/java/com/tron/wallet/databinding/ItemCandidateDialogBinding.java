package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemCandidateDialogBinding implements ViewBinding {
    public final EditText etInput;
    public final ImageView ivDeleteVR;
    private final RelativeLayout rootView;
    public final RelativeLayout superBg;
    public final TextView tvPersonName;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemCandidateDialogBinding(RelativeLayout relativeLayout, EditText editText, ImageView imageView, RelativeLayout relativeLayout2, TextView textView) {
        this.rootView = relativeLayout;
        this.etInput = editText;
        this.ivDeleteVR = imageView;
        this.superBg = relativeLayout2;
        this.tvPersonName = textView;
    }

    public static ItemCandidateDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemCandidateDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_candidate_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemCandidateDialogBinding bind(View view) {
        int i = R.id.et_input;
        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_input);
        if (editText != null) {
            i = R.id.iv_delete_VR;
            ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete_VR);
            if (imageView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                i = R.id.tv_person_name;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_person_name);
                if (textView != null) {
                    return new ItemCandidateDialogBinding(relativeLayout, editText, imageView, relativeLayout, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
