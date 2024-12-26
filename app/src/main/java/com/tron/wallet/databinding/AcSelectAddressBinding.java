package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcSelectAddressBinding implements ViewBinding {
    public final ItemChooseAddressBinding addressItemShieldContainer;
    public final Button btnNext;
    public final CheckBox chbNonhd;
    public final ItemErrorviewBinding errorLayout;
    public final ImageView ivLoading;
    public final RelativeLayout rlChbNonhd;
    private final RelativeLayout rootView;
    public final RecyclerView rvList;
    public final TextView tvChangeAddress;
    public final RelativeLayout viewContent;
    public final LedgerNoDeviceBinding viewLoadFail;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcSelectAddressBinding(RelativeLayout relativeLayout, ItemChooseAddressBinding itemChooseAddressBinding, Button button, CheckBox checkBox, ItemErrorviewBinding itemErrorviewBinding, ImageView imageView, RelativeLayout relativeLayout2, RecyclerView recyclerView, TextView textView, RelativeLayout relativeLayout3, LedgerNoDeviceBinding ledgerNoDeviceBinding) {
        this.rootView = relativeLayout;
        this.addressItemShieldContainer = itemChooseAddressBinding;
        this.btnNext = button;
        this.chbNonhd = checkBox;
        this.errorLayout = itemErrorviewBinding;
        this.ivLoading = imageView;
        this.rlChbNonhd = relativeLayout2;
        this.rvList = recyclerView;
        this.tvChangeAddress = textView;
        this.viewContent = relativeLayout3;
        this.viewLoadFail = ledgerNoDeviceBinding;
    }

    public static AcSelectAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSelectAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_select_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSelectAddressBinding bind(View view) {
        int i = R.id.address_item_shield_container;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.address_item_shield_container);
        if (findChildViewById != null) {
            ItemChooseAddressBinding bind = ItemChooseAddressBinding.bind(findChildViewById);
            i = R.id.btn_next;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
            if (button != null) {
                i = R.id.chb_nonhd;
                CheckBox checkBox = (CheckBox) ViewBindings.findChildViewById(view, R.id.chb_nonhd);
                if (checkBox != null) {
                    i = R.id.error_layout;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.error_layout);
                    if (findChildViewById2 != null) {
                        ItemErrorviewBinding bind2 = ItemErrorviewBinding.bind(findChildViewById2);
                        i = R.id.iv_loading;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
                        if (imageView != null) {
                            i = R.id.rl_chb_nonhd;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_chb_nonhd);
                            if (relativeLayout != null) {
                                i = R.id.rv_list;
                                RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.rv_list);
                                if (recyclerView != null) {
                                    i = R.id.tv_change_address;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_change_address);
                                    if (textView != null) {
                                        RelativeLayout relativeLayout2 = (RelativeLayout) view;
                                        i = R.id.view_load_fail;
                                        View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.view_load_fail);
                                        if (findChildViewById3 != null) {
                                            return new AcSelectAddressBinding(relativeLayout2, bind, button, checkBox, bind2, imageView, relativeLayout, recyclerView, textView, relativeLayout2, LedgerNoDeviceBinding.bind(findChildViewById3));
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
