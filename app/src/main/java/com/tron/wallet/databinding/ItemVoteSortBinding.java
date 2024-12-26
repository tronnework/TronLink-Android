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
public final class ItemVoteSortBinding implements ViewBinding {
    public final ImageView iv;
    public final RelativeLayout rlOptions;
    private final RelativeLayout rootView;
    public final TextView tv;
    public final TextView tvSortByUser;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemVoteSortBinding(RelativeLayout relativeLayout, ImageView imageView, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.iv = imageView;
        this.rlOptions = relativeLayout2;
        this.tv = textView;
        this.tvSortByUser = textView2;
    }

    public static ItemVoteSortBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVoteSortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_vote_sort, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVoteSortBinding bind(View view) {
        int i = R.id.iv;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv);
        if (imageView != null) {
            i = R.id.rl_options;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_options);
            if (relativeLayout != null) {
                i = R.id.tv;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv);
                if (textView != null) {
                    i = R.id.tv_sort_by_user;
                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_sort_by_user);
                    if (textView2 != null) {
                        return new ItemVoteSortBinding((RelativeLayout) view, imageView, relativeLayout, textView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
