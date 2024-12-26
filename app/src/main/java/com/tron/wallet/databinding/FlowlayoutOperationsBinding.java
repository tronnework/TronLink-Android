package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class FlowlayoutOperationsBinding implements ViewBinding {
    private final TextView rootView;
    public final TextView tvOperation;

    @Override
    public TextView getRoot() {
        return this.rootView;
    }

    private FlowlayoutOperationsBinding(TextView textView, TextView textView2) {
        this.rootView = textView;
        this.tvOperation = textView2;
    }

    public static FlowlayoutOperationsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FlowlayoutOperationsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.flowlayout_operations, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FlowlayoutOperationsBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        TextView textView = (TextView) view;
        return new FlowlayoutOperationsBinding(textView, textView);
    }
}
