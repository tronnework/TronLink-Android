package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class SwapTitleRecordBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView swapMore;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapTitleRecordBinding(RelativeLayout relativeLayout, TextView textView) {
        this.rootView = relativeLayout;
        this.swapMore = textView;
    }

    public static SwapTitleRecordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapTitleRecordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_title_record, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapTitleRecordBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.swap_more);
        if (textView != null) {
            return new SwapTitleRecordBinding((RelativeLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.swap_more)));
    }
}
