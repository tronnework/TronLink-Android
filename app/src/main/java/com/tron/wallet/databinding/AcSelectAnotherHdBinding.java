package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcSelectAnotherHdBinding implements ViewBinding {
    public final Button btConfirm;
    public final View dividerLine;
    public final ImageView ivCloseSelect;
    public final ImageView ivPlaceHolder;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectAnotherHdBinding(RelativeLayout relativeLayout, Button button, View view, ImageView imageView, ImageView imageView2, RecyclerView recyclerView, TextView textView) {
        this.rootView = relativeLayout;
        this.btConfirm = button;
        this.dividerLine = view;
        this.ivCloseSelect = imageView;
        this.ivPlaceHolder = imageView2;
        this.rvList = recyclerView;
        this.tvTitle = textView;
    }

    public static AcSelectAnotherHdBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectAnotherHdBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_another_hd, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectAnotherHdBinding bind(View view) {
        int i = R.id.bt_confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_confirm);
        if (button != null) {
            i = R.id.divider_line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider_line);
            if (findChildViewById != null) {
                i = R.id.iv_close_select;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_select);
                if (imageView != null) {
                    i = R.id.iv_place_holder;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
                    if (imageView2 != null) {
                        i = R.id.rv_list;
                        RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                        if (recyclerView != null) {
                            i = R.id.tv_title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                            if (textView != null) {
                                return new AcSelectAnotherHdBinding((RelativeLayout) view, button, findChildViewById, imageView, imageView2, recyclerView, textView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
