package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ActivityChooseAddressBinding implements ViewBinding {
    public final ItemChooseAddressBinding addressItemContainer;
    public final Button btnNext;
    public final ItemErrorviewBinding errorLayout;
    public final EditText etName;
    public final ImageView ivLoading;
    private final RelativeLayout rootView;
    public final TextView tvChangeAddress;
    public final LedgerNoDeviceBinding viewLoadFail;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ActivityChooseAddressBinding(RelativeLayout relativeLayout, ItemChooseAddressBinding itemChooseAddressBinding, Button button, ItemErrorviewBinding itemErrorviewBinding, EditText editText, ImageView imageView, TextView textView, LedgerNoDeviceBinding ledgerNoDeviceBinding) {
        this.rootView = relativeLayout;
        this.addressItemContainer = itemChooseAddressBinding;
        this.btnNext = button;
        this.errorLayout = itemErrorviewBinding;
        this.etName = editText;
        this.ivLoading = imageView;
        this.tvChangeAddress = textView;
        this.viewLoadFail = ledgerNoDeviceBinding;
    }

    public static ActivityChooseAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityChooseAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_choose_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityChooseAddressBinding bind(View view) {
        int i = R.id.address_item_container;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.address_item_container);
        if (findChildViewById != null) {
            ItemChooseAddressBinding bind = ItemChooseAddressBinding.bind(findChildViewById);
            i = R.id.btn_next;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
            if (button != null) {
                i = R.id.error_layout;
                View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.error_layout);
                if (findChildViewById2 != null) {
                    ItemErrorviewBinding bind2 = ItemErrorviewBinding.bind(findChildViewById2);
                    i = R.id.et_name;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_name);
                    if (editText != null) {
                        i = R.id.iv_loading;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_loading);
                        if (imageView != null) {
                            i = R.id.tv_change_address;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_change_address);
                            if (textView != null) {
                                i = R.id.view_load_fail;
                                View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.view_load_fail);
                                if (findChildViewById3 != null) {
                                    return new ActivityChooseAddressBinding((RelativeLayout) view, bind, button, bind2, editText, imageView, textView, LedgerNoDeviceBinding.bind(findChildViewById3));
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
