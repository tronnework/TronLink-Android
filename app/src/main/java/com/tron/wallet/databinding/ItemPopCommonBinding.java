package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemPopCommonBinding implements ViewBinding {
    public final ImageView icon;
    public final View line;
    private final RelativeLayout rootView;
    public final ImageView selected;
    public final TextView title;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemPopCommonBinding(RelativeLayout relativeLayout, ImageView imageView, View view, ImageView imageView2, TextView textView) {
        this.rootView = relativeLayout;
        this.icon = imageView;
        this.line = view;
        this.selected = imageView2;
        this.title = textView;
    }

    public static ItemPopCommonBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemPopCommonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_pop_common, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemPopCommonBinding bind(View view) {
        int i = R.id.icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.icon);
        if (imageView != null) {
            i = R.id.line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.line);
            if (findChildViewById != null) {
                i = R.id.selected;
                ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.selected);
                if (imageView2 != null) {
                    i = R.id.title;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                    if (textView != null) {
                        return new ItemPopCommonBinding((RelativeLayout) view, imageView, findChildViewById, imageView2, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
