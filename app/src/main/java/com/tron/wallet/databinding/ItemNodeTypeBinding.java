package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemNodeTypeBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvNodeType;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemNodeTypeBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvNodeType = textView;
    }

    public static ItemNodeTypeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemNodeTypeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_node_type, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemNodeTypeBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_node_type);
        if (textView != null) {
            return new ItemNodeTypeBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_node_type)));
    }
}
