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
public final class AcMakeProposalsBinding implements ViewBinding {
    public final LinearLayout llContent;
    public final RecyclerView rcLayout;
    private final RelativeLayout rootView;
    public final TextView tvConfirm;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcMakeProposalsBinding(RelativeLayout relativeLayout, LinearLayout linearLayout, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.llContent = linearLayout;
        this.rcLayout = recyclerView;
        this.tvConfirm = textView;
        this.tvTitle = textView2;
    }

    public static AcMakeProposalsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcMakeProposalsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_make_proposals, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcMakeProposalsBinding bind(View view) {
        int i = R.id.ll_content;
        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_content);
        if (linearLayout != null) {
            i = R.id.rc_layout;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_layout);
            if (recyclerView != null) {
                i = R.id.tv_confirm;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_confirm);
                if (textView != null) {
                    i = R.id.tv_title;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                    if (textView2 != null) {
                        return new AcMakeProposalsBinding((RelativeLayout) view, linearLayout, recyclerView, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
