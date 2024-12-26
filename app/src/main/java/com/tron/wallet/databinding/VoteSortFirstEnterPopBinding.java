package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class VoteSortFirstEnterPopBinding implements ViewBinding {
    public final ImageView ivClose;
    private final RelativeLayout rootView;
    public final TextView tvSortPop;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private VoteSortFirstEnterPopBinding(RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
        this.rootView = relativeLayout;
        this.ivClose = imageView;
        this.tvSortPop = textView;
    }

    public static VoteSortFirstEnterPopBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static VoteSortFirstEnterPopBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.vote_sort_first_enter_pop, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static VoteSortFirstEnterPopBinding bind(View view) {
        int i = R.id.iv_close;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close);
        if (imageView != null) {
            i = R.id.tv_sort_pop;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sort_pop);
            if (textView != null) {
                return new VoteSortFirstEnterPopBinding((RelativeLayout) view, imageView, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
