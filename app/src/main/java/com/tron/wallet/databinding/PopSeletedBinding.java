package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopSeletedBinding implements ViewBinding {
    public final LinearLayout llContent;
    public final RecyclerView rcList;
    private final RelativeLayout rootView;
    public final TextView tvCancel;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopSeletedBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.llContent = linearLayout;
        this.rcList = recyclerView;
        this.tvCancel = textView;
    }

    public static PopSeletedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopSeletedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_seleted, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopSeletedBinding bind(View view) {
        int i = R.id.ll_content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
        if (linearLayout != null) {
            i = R.id.rc_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
            if (recyclerView != null) {
                i = R.id.tv_cancel;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                if (textView != null) {
                    return new PopSeletedBinding((RelativeLayout) view, linearLayout, recyclerView, textView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
