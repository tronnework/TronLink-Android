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
public final class TabItemBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final ImageView tabIcon;
    public final TextView tabText;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private TabItemBinding(LinearLayout linearLayout, ImageView imageView, TextView textView) {
        this.rootView = linearLayout;
        this.tabIcon = imageView;
        this.tabText = textView;
    }

    public static TabItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static TabItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.tab_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static TabItemBinding bind(View view) {
        int i = R.id.tab_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.tab_icon);
        if (imageView != null) {
            i = R.id.tab_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tab_text);
            if (textView != null) {
                return new TabItemBinding((LinearLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
