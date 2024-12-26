package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcSetCustomPathBinding implements ViewBinding {
    public final ItemChooseAddressBinding addressView;
    public final Button btnNext;
    public final ImageView ivInfo;
    public final SetMnemonicPathViewBinding li1;
    public final ConstraintLayout root;
    private final ConstraintLayout rootView;
    public final TextView tvSetPathTips;

    @Override
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    private AcSetCustomPathBinding(ConstraintLayout constraintLayout, ItemChooseAddressBinding itemChooseAddressBinding, Button button, ImageView imageView, SetMnemonicPathViewBinding setMnemonicPathViewBinding, ConstraintLayout constraintLayout2, TextView textView) {
        this.rootView = constraintLayout;
        this.addressView = itemChooseAddressBinding;
        this.btnNext = button;
        this.ivInfo = imageView;
        this.li1 = setMnemonicPathViewBinding;
        this.root = constraintLayout2;
        this.tvSetPathTips = textView;
    }

    public static AcSetCustomPathBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcSetCustomPathBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_set_custom_path, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcSetCustomPathBinding bind(View view) {
        int i = R.id.address_view;
        View findChildViewById = ViewBindings.findChildViewById(view, R.id.address_view);
        if (findChildViewById != null) {
            ItemChooseAddressBinding bind = ItemChooseAddressBinding.bind(findChildViewById);
            i = R.id.btn_next;
            Button button = (Button) ViewBindings.findChildViewById(view, R.id.btn_next);
            if (button != null) {
                i = R.id.iv_info;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_info);
                if (imageView != null) {
                    i = R.id.li1;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.li1);
                    if (findChildViewById2 != null) {
                        SetMnemonicPathViewBinding bind2 = SetMnemonicPathViewBinding.bind(findChildViewById2);
                        ConstraintLayout constraintLayout = (ConstraintLayout) view;
                        i = R.id.tv_set_path_tips;
                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_set_path_tips);
                        if (textView != null) {
                            return new AcSetCustomPathBinding(constraintLayout, bind, button, imageView, bind2, constraintLayout, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
