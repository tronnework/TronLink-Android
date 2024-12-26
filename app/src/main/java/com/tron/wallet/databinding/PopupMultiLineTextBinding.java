package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopupMultiLineTextBinding implements ViewBinding {
    public final ImageView ivArrow;
    private final RelativeLayout rootView;
    public final RecyclerView rvTexts;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopupMultiLineTextBinding(RelativeLayout relativeLayout, ImageView imageView, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.ivArrow = imageView;
        this.rvTexts = recyclerView;
    }

    public static PopupMultiLineTextBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopupMultiLineTextBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popup_multi_line_text, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopupMultiLineTextBinding bind(View view) {
        int i = R.id.iv_arrow;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_arrow);
        if (imageView != null) {
            i = R.id.rv_texts;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_texts);
            if (recyclerView != null) {
                return new PopupMultiLineTextBinding((RelativeLayout) view, imageView, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
