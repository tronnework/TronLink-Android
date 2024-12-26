package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class PopSelectLoginaccountBinding implements ViewBinding {
    public final ImageView ivClose;
    public final RecyclerView rcList;
    public final RelativeLayout rlRoot;
    private final RelativeLayout rootView;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopSelectLoginaccountBinding(RelativeLayout relativeLayout, ImageView imageView, RecyclerView recyclerView, RelativeLayout relativeLayout2) {
        this.rootView = relativeLayout;
        this.ivClose = imageView;
        this.rcList = recyclerView;
        this.rlRoot = relativeLayout2;
    }

    public static PopSelectLoginaccountBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopSelectLoginaccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.pop_select_loginaccount, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopSelectLoginaccountBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
        if (imageView != null) {
            i = R.id.rc_list;
            RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rc_list);
            if (recyclerView != null) {
                RelativeLayout relativeLayout = (RelativeLayout) view;
                return new PopSelectLoginaccountBinding(relativeLayout, imageView, recyclerView, relativeLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
