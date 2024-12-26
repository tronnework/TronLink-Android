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
public final class PopviewSelectAddressFromAddressbookBinding implements ViewBinding {
    public final Button btnNext;
    public final View dividerLine;
    public final ImageView ivCloseSelect;
    public final ImageView ivEmpty;
    public final ImageView ivPlaceHolder;
    public final RelativeLayout rlEmpty;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvManage;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private PopviewSelectAddressFromAddressbookBinding(RelativeLayout relativeLayout, Button button, View view, ImageView imageView, ImageView imageView2, ImageView imageView3, RelativeLayout relativeLayout2, RecyclerView recyclerView, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btnNext = button;
        this.dividerLine = view;
        this.ivCloseSelect = imageView;
        this.ivEmpty = imageView2;
        this.ivPlaceHolder = imageView3;
        this.rlEmpty = relativeLayout2;
        this.rvList = recyclerView;
        this.tvManage = textView;
        this.tvTitle = textView2;
    }

    public static PopviewSelectAddressFromAddressbookBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static PopviewSelectAddressFromAddressbookBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.popview_select_address_from_addressbook, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static PopviewSelectAddressFromAddressbookBinding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
        if (button != null) {
            i = R.id.divider_line;
            View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider_line);
            if (findChildViewById != null) {
                i = R.id.iv_close_select;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_close_select);
                if (imageView != null) {
                    i = R.id.iv_empty;
                    ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_empty);
                    if (imageView2 != null) {
                        i = R.id.iv_place_holder;
                        ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_place_holder);
                        if (imageView3 != null) {
                            i = R.id.rl_empty;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_empty);
                            if (relativeLayout != null) {
                                i = R.id.rv_list;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                                if (recyclerView != null) {
                                    i = R.id.tv_manage;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_manage);
                                    if (textView != null) {
                                        i = R.id.tv_title;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                        if (textView2 != null) {
                                            return new PopviewSelectAddressFromAddressbookBinding((RelativeLayout) view, button, findChildViewById, imageView, imageView2, imageView3, relativeLayout, recyclerView, textView, textView2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
