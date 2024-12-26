package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class DappListQuickInnerFragmentBinding implements ViewBinding {
    public final ImageView ivBook;
    public final RelativeLayout noBookDataLayout;
    private final FrameLayout rootView;
    public final RecyclerView rvContent;
    public final TextView tvHowBook;

    @Override
    public FrameLayout getRoot() {
        return this.rootView;
    }

    private DappListQuickInnerFragmentBinding(FrameLayout frameLayout, ImageView imageView, RelativeLayout relativeLayout, RecyclerView recyclerView, TextView textView) {
        this.rootView = frameLayout;
        this.ivBook = imageView;
        this.noBookDataLayout = relativeLayout;
        this.rvContent = recyclerView;
        this.tvHowBook = textView;
    }

    public static DappListQuickInnerFragmentBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DappListQuickInnerFragmentBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dapp_list_quick_inner_fragment, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DappListQuickInnerFragmentBinding bind(View view) {
        int i = R.id.iv_book;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_book);
        if (imageView != null) {
            i = R.id.no_book_data_layout;
            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.no_book_data_layout);
            if (relativeLayout != null) {
                i = R.id.rv_content;
                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_content);
                if (recyclerView != null) {
                    i = R.id.tv_how_book;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_how_book);
                    if (textView != null) {
                        return new DappListQuickInnerFragmentBinding((FrameLayout) view, imageView, relativeLayout, recyclerView, textView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
