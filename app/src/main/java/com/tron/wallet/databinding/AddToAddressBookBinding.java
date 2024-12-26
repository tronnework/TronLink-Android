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
public final class AddToAddressBookBinding implements ViewBinding {
    public final ErrorView errorView;
    public final TrimEditText etInputName;
    public final TrimEditText etInputNote;
    public final ImageView ivClearName;
    public final ImageView ivClearNote;
    public final RelativeLayout rlName;
    public final RelativeLayout rlNote;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvAddressName;
    public final TextView tvAddressNote;
    public final TextView tvAddressNoteOptional;
    public final TextView tvAddressTitle;
    public final TextView tvCancel;
    public final TextView tvOk;
    public final TextView tvTitle;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AddToAddressBookBinding(RelativeLayout relativeLayout, ErrorView errorView, TrimEditText trimEditText, TrimEditText trimEditText2, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8) {
        this.rootView = relativeLayout;
        this.errorView = errorView;
        this.etInputName = trimEditText;
        this.etInputNote = trimEditText2;
        this.ivClearName = imageView;
        this.ivClearNote = imageView2;
        this.rlName = relativeLayout2;
        this.rlNote = relativeLayout3;
        this.tvAddress = textView;
        this.tvAddressName = textView2;
        this.tvAddressNote = textView3;
        this.tvAddressNoteOptional = textView4;
        this.tvAddressTitle = textView5;
        this.tvCancel = textView6;
        this.tvOk = textView7;
        this.tvTitle = textView8;
    }

    public static AddToAddressBookBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AddToAddressBookBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.add_to_address_book, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AddToAddressBookBinding bind(View view) {
        int i = R.id.error_view;
        ErrorView errorView = (ErrorView) ViewBindings.findChildViewById(view, R.id.error_view);
        if (errorView != null) {
            i = R.id.et_input_name;
            TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_input_name);
            if (trimEditText != null) {
                i = R.id.et_input_note;
                TrimEditText trimEditText2 = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_input_note);
                if (trimEditText2 != null) {
                    i = R.id.iv_clear_name;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear_name);
                    if (imageView != null) {
                        i = R.id.iv_clear_note;
                        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_clear_note);
                        if (imageView2 != null) {
                            i = R.id.rl_name;
                            RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_name);
                            if (relativeLayout != null) {
                                i = R.id.rl_note;
                                RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_note);
                                if (relativeLayout2 != null) {
                                    i = R.id.tv_address;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                                    if (textView != null) {
                                        i = R.id.tv_address_name;
                                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_name);
                                        if (textView2 != null) {
                                            i = R.id.tv_address_note;
                                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_note);
                                            if (textView3 != null) {
                                                i = R.id.tv_address_note_optional;
                                                TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_note_optional);
                                                if (textView4 != null) {
                                                    i = R.id.tv_address_title;
                                                    TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_title);
                                                    if (textView5 != null) {
                                                        i = R.id.tv_cancel;
                                                        TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancel);
                                                        if (textView6 != null) {
                                                            i = R.id.tv_ok;
                                                            TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok);
                                                            if (textView7 != null) {
                                                                i = R.id.tv_title;
                                                                TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_title);
                                                                if (textView8 != null) {
                                                                    return new AddToAddressBookBinding((RelativeLayout) view, errorView, trimEditText, trimEditText2, imageView, imageView2, relativeLayout, relativeLayout2, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8);
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
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
