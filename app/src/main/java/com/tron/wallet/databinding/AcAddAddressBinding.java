package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcAddAddressBinding implements ViewBinding {
    public final Button btNext;
    public final EditText etAddress;
    public final EditText etAddressName;
    public final EditText etDescription;
    public final ImageView ivAddress;
    public final ImageView ivName;
    public final RelativeLayout rlContent;
    public final RelativeLayout rlDelete;
    public final RelativeLayout rlErrorAddress;
    public final RelativeLayout rlErrorName;
    public final RelativeLayout rlScan;
    private final RelativeLayout rootView;
    public final LinearLayout top;
    public final TextView tvAddressError;
    public final TextView tvNameError;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcAddAddressBinding(RelativeLayout relativeLayout, Button button, EditText editText, EditText editText2, EditText editText3, ImageView imageView, ImageView imageView2, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, RelativeLayout relativeLayout6, LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.btNext = button;
        this.etAddress = editText;
        this.etAddressName = editText2;
        this.etDescription = editText3;
        this.ivAddress = imageView;
        this.ivName = imageView2;
        this.rlContent = relativeLayout2;
        this.rlDelete = relativeLayout3;
        this.rlErrorAddress = relativeLayout4;
        this.rlErrorName = relativeLayout5;
        this.rlScan = relativeLayout6;
        this.top = linearLayout;
        this.tvAddressError = textView;
        this.tvNameError = textView2;
    }

    public static AcAddAddressBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcAddAddressBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_add_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcAddAddressBinding bind(View view) {
        int i = R.id.bt_next;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_next);
        if (button != null) {
            i = R.id.et_address;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_address);
            if (editText != null) {
                i = R.id.et_address_name;
                EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_address_name);
                if (editText2 != null) {
                    i = R.id.et_description;
                    EditText editText3 = (EditText) ViewBindings.findChildViewById(view, R.id.et_description);
                    if (editText3 != null) {
                        i = R.id.iv_address;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_address);
                        if (imageView != null) {
                            i = R.id.iv_name;
                            ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_name);
                            if (imageView2 != null) {
                                i = R.id.rl_content;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_content);
                                if (relativeLayout != null) {
                                    i = R.id.rl_delete;
                                    RelativeLayout relativeLayout2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_delete);
                                    if (relativeLayout2 != null) {
                                        i = R.id.rl_error_address;
                                        RelativeLayout relativeLayout3 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_error_address);
                                        if (relativeLayout3 != null) {
                                            i = R.id.rl_error_name;
                                            RelativeLayout relativeLayout4 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_error_name);
                                            if (relativeLayout4 != null) {
                                                i = R.id.rl_scan;
                                                RelativeLayout relativeLayout5 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl_scan);
                                                if (relativeLayout5 != null) {
                                                    i = R.id.top;
                                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.top);
                                                    if (linearLayout != null) {
                                                        i = R.id.tv_address_error;
                                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address_error);
                                                        if (textView != null) {
                                                            i = R.id.tv_name_error;
                                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_name_error);
                                                            if (textView2 != null) {
                                                                return new AcAddAddressBinding((RelativeLayout) view, button, editText, editText2, editText3, imageView, imageView2, relativeLayout, relativeLayout2, relativeLayout3, relativeLayout4, relativeLayout5, linearLayout, textView, textView2);
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
