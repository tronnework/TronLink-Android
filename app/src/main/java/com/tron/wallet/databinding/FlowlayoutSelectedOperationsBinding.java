package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class FlowlayoutSelectedOperationsBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvOperation;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private FlowlayoutSelectedOperationsBinding(RelativeLayout relativeLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.tvOperation = textView;
    }

    public static FlowlayoutSelectedOperationsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FlowlayoutSelectedOperationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.flowlayout_selected_operations, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FlowlayoutSelectedOperationsBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_operation);
        if (textView != null) {
            return new FlowlayoutSelectedOperationsBinding((RelativeLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_operation)));
    }
}
