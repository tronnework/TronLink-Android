package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorView;
import com.tron.wallet.common.components.TrimEditText;
import com.tronlinkpro.wallet.R;
public final class ItemInputAddressBinding implements ViewBinding {
    public final ErrorView errorView;
    public final TrimEditText etInputAddress;
    public final TextView flagDid;
    public final ImageView ivDelete;
    public final ImageView ivScan;
    public final ImageView ivTip1;
    public final RelativeLayout rlAddress;
    public final RelativeLayout rlButtons;
    public final RelativeLayout rlInput;
    private final RelativeLayout rootView;
    public final TextView tvInputMask;
    public final TextView tvPaste;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private ItemInputAddressBinding(RelativeLayout relativeLayout, ErrorView errorView, TrimEditText trimEditText, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = relativeLayout;
        this.errorView = errorView;
        this.etInputAddress = trimEditText;
        this.flagDid = textView;
        this.ivDelete = imageView;
        this.ivScan = imageView2;
        this.ivTip1 = imageView3;
        this.rlAddress = relativeLayout2;
        this.rlButtons = relativeLayout3;
        this.rlInput = relativeLayout4;
        this.tvInputMask = textView2;
        this.tvPaste = textView3;
        this.tvTitle = textView4;
    }

    public static ItemInputAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemInputAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_input_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemInputAddressBinding bind(View view) {
        int i = R.id.error_view;
        ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.error_view);
        if (errorView != null) {
            i = R.id.et_input_address;
            TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_input_address);
            if (trimEditText != null) {
                i = R.id.flag_did;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.flag_did);
                if (textView != null) {
                    i = R.id.iv_delete;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                    if (imageView != null) {
                        i = R.id.iv_scan;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_scan);
                        if (imageView2 != null) {
                            i = R.id.iv_tip1;
                            ImageView imageView3 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_tip1);
                            if (imageView3 != null) {
                                RelativeLayout relativeLayout = (RelativeLayout) view;
                                i = R.id.rl_buttons;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_buttons);
                                if (relativeLayout2 != null) {
                                    i = R.id.rl_input;
                                    RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_input);
                                    if (relativeLayout3 != null) {
                                        i = R.id.tv_input_mask;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_input_mask);
                                        if (textView2 != null) {
                                            i = R.id.tv_paste;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_paste);
                                            if (textView3 != null) {
                                                i = R.id.tv_title;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                if (textView4 != null) {
                                                    return new ItemInputAddressBinding(relativeLayout, errorView, trimEditText, textView, imageView, imageView2, imageView3, relativeLayout, relativeLayout2, relativeLayout3, textView2, textView3, textView4);
                                                }
                                            }
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
