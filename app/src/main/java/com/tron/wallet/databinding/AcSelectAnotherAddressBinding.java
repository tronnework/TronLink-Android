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
public final class AcSelectAnotherAddressBinding implements ViewBinding {
    public final Button btnNext;
    public final View dividerLine;
    public final ImageView ivCloseSelect;
    public final ImageView ivPlaceHolder;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvSelectSetPath;
    public final TextView tvTitle;
    public final RelativeLayout viewContent;
    public final LedgerNoDeviceBinding viewLoadFail;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectAnotherAddressBinding(RelativeLayout relativeLayout, Button button, View view, ImageView imageView, ImageView imageView2, RecyclerView recyclerView, TextView textView, TextView textView2, RelativeLayout relativeLayout2, LedgerNoDeviceBinding ledgerNoDeviceBinding) {
        this.rootView = relativeLayout;
        this.btnNext = button;
        this.dividerLine = view;
        this.ivCloseSelect = imageView;
        this.ivPlaceHolder = imageView2;
        this.rvList = recyclerView;
        this.tvSelectSetPath = textView;
        this.tvTitle = textView2;
        this.viewContent = relativeLayout2;
        this.viewLoadFail = ledgerNoDeviceBinding;
    }

    public static AcSelectAnotherAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectAnotherAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_another_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectAnotherAddressBinding bind(View view) {
        int i = R.id.btn_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
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
                            i = R.id.tv_select_set_path;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_select_set_path);
                            if (textView != null) {
                                i = R.id.tv_title;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                if (textView2 != null) {
                                    RelativeLayout relativeLayout = (RelativeLayout) view;
                                    i = R.id.view_load_fail;
                                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.view_load_fail);
                                    if (findChildViewById2 != null) {
                                        return new AcSelectAnotherAddressBinding(relativeLayout, button, findChildViewById, imageView, imageView2, recyclerView, textView, textView2, relativeLayout, LedgerNoDeviceBinding.bind(findChildViewById2));
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
