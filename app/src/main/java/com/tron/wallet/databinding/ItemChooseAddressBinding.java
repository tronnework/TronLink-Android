package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemChooseAddressBinding implements ViewBinding {
    public final TextView ivIdx;
    public final AppCompatCheckBox ivSelectTag;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvBalance;
    public final TextView tvCustom;
    public final TextView tvImported;
    public final TextView tvPath;
    public final View viewHolder;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemChooseAddressBinding(RelativeLayout relativeLayout, TextView textView, AppCompatCheckBox appCompatCheckBox, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, View view) {
        this.rootView = relativeLayout;
        this.ivIdx = textView;
        this.ivSelectTag = appCompatCheckBox;
        this.tvAddress = textView2;
        this.tvBalance = textView3;
        this.tvCustom = textView4;
        this.tvImported = textView5;
        this.tvPath = textView6;
        this.viewHolder = view;
    }

    public static ItemChooseAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemChooseAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_choose_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemChooseAddressBinding bind(View view) {
        int i = R.id.iv_idx;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.iv_idx);
        if (textView != null) {
            i = R.id.iv_select_tag;
            AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ViewBindings.findChildViewById(view, R.id.iv_select_tag);
            if (appCompatCheckBox != null) {
                i = R.id.tv_address;
                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                if (textView2 != null) {
                    i = R.id.tv_balance;
                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_balance);
                    if (textView3 != null) {
                        i = R.id.tv_custom;
                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_custom);
                        if (textView4 != null) {
                            i = R.id.tv_imported;
                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_imported);
                            if (textView5 != null) {
                                i = R.id.tv_path;
                                TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_path);
                                if (textView6 != null) {
                                    i = R.id.view_holder;
                                    View findChildViewById = ViewBindings.findChildViewById(view, R.id.view_holder);
                                    if (findChildViewById != null) {
                                        return new ItemChooseAddressBinding((RelativeLayout) view, textView, appCompatCheckBox, textView2, textView3, textView4, textView5, textView6, findChildViewById);
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
