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
public final class ItemNoHistoryBinding implements ViewBinding {
    public final ImageView noDataIcon;
    public final TextView noDataText;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemNoHistoryBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
        this.rootView = relativeLayout;
        this.noDataIcon = imageView;
        this.noDataText = textView;
    }

    public static ItemNoHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNoHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_no_history, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNoHistoryBinding bind(View view) {
        int i = R.id.no_data_icon;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.no_data_icon);
        if (imageView != null) {
            i = R.id.no_data_text;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.no_data_text);
            if (textView != null) {
                return new ItemNoHistoryBinding((RelativeLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
