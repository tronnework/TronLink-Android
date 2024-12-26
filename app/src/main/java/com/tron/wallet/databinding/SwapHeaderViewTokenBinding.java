package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class SwapHeaderViewTokenBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvDivider;
    public final TextView tvTokenFrom;
    public final TextView tvTokenTo;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private SwapHeaderViewTokenBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = relativeLayout;
        this.tvDivider = textView;
        this.tvTokenFrom = textView2;
        this.tvTokenTo = textView3;
    }

    public static SwapHeaderViewTokenBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SwapHeaderViewTokenBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.swap_header_view_token, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SwapHeaderViewTokenBinding bind(View view) {
        int i = R.id.tv_divider;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_divider);
        if (textView != null) {
            i = R.id.tv_token_from;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_from);
            if (textView2 != null) {
                i = R.id.tv_token_to;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_token_to);
                if (textView3 != null) {
                    return new SwapHeaderViewTokenBinding((RelativeLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
