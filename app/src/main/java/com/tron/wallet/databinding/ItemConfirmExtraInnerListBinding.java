package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemConfirmExtraInnerListBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final TextView tvLeft;
    public final TextView tvRight;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemConfirmExtraInnerListBinding(RelativeLayout relativeLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.tvLeft = textView;
        this.tvRight = textView2;
    }

    public static ItemConfirmExtraInnerListBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemConfirmExtraInnerListBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_confirm_extra_inner_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemConfirmExtraInnerListBinding bind(View view) {
        int i = R.id.tv_left;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_left);
        if (textView != null) {
            i = R.id.tv_right;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_right);
            if (textView2 != null) {
                return new ItemConfirmExtraInnerListBinding((RelativeLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
