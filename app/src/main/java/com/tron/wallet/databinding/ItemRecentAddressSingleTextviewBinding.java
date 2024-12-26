package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemRecentAddressSingleTextviewBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tv;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemRecentAddressSingleTextviewBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tv = textView;
    }

    public static ItemRecentAddressSingleTextviewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemRecentAddressSingleTextviewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_recent_address_single_textview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemRecentAddressSingleTextviewBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv);
        if (textView != null) {
            return new ItemRecentAddressSingleTextviewBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv)));
    }
}
