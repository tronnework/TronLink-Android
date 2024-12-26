package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemNoteNotedetailBinding implements ViewBinding {
    public final TextView commitmentValue;
    public final TextView denominationValue;
    public final ImageView ivCopy;
    private final LinearLayout rootView;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemNoteNotedetailBinding(LinearLayout linearLayout, TextView textView, TextView textView2, ImageView imageView) {
        this.rootView = linearLayout;
        this.commitmentValue = textView;
        this.denominationValue = textView2;
        this.ivCopy = imageView;
    }

    public static ItemNoteNotedetailBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNoteNotedetailBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_note_notedetail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNoteNotedetailBinding bind(View view) {
        int i = R.id.commitment_value;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.commitment_value);
        if (textView != null) {
            i = R.id.denomination_value;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.denomination_value);
            if (textView2 != null) {
                i = R.id.iv_copy;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                if (imageView != null) {
                    return new ItemNoteNotedetailBinding((LinearLayout) view, textView, textView2, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
