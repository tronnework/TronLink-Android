package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tron.wallet.common.components.TrimEditText;
import com.tronlinkpro.wallet.R;
public final class AcChangeAddressBinding implements ViewBinding {
    public final Button confirm;
    public final ErrorEdiTextLayout eetAddress;
    public final TrimEditText etAddress;
    public final RelativeLayout rlAddressBook;
    public final RelativeLayout rlClear;
    private final LinearLayout rootView;
    public final TextView tvAddressTitle;
    public final TextView tvDescription;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcChangeAddressBinding(LinearLayout linearLayout, Button button, ErrorEdiTextLayout errorEdiTextLayout, TrimEditText trimEditText, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.confirm = button;
        this.eetAddress = errorEdiTextLayout;
        this.etAddress = trimEditText;
        this.rlAddressBook = relativeLayout;
        this.rlClear = relativeLayout2;
        this.tvAddressTitle = textView;
        this.tvDescription = textView2;
    }

    public static AcChangeAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcChangeAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_change_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcChangeAddressBinding bind(View view) {
        int i = R.id.confirm;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.confirm);
        if (button != null) {
            i = R.id.eet_address;
            ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_address);
            if (errorEdiTextLayout != null) {
                i = R.id.et_address;
                TrimEditText trimEditText = (TrimEditText) ViewBindings.findChildViewById(view, R.id.et_address);
                if (trimEditText != null) {
                    i = R.id.rl_address_book;
                    RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_address_book);
                    if (relativeLayout != null) {
                        i = R.id.rl_clear;
                        RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_clear);
                        if (relativeLayout2 != null) {
                            i = R.id.tv_address_title;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_title);
                            if (textView != null) {
                                i = R.id.tv_description;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_description);
                                if (textView2 != null) {
                                    return new AcChangeAddressBinding((LinearLayout) view, button, errorEdiTextLayout, trimEditText, relativeLayout, relativeLayout2, textView, textView2);
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
