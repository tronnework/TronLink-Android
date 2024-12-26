package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class DgAddaddressbookBinding implements ViewBinding {
    public final ErrorEdiTextLayout eetAddressName;
    public final EditText etAddressName;
    public final TextView innerTitle;
    public final ImageView ivDelete;
    public final LinearLayout liEt;
    private final RelativeLayout rootView;
    public final TextView title;
    public final RelativeLayout top;
    public final TextView tvCancle;
    public final TextView tvNote;
    public final TextView tvOk;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private DgAddaddressbookBinding(RelativeLayout relativeLayout, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, TextView textView, ImageView imageView, LinearLayout linearLayout, TextView textView2, RelativeLayout relativeLayout2, TextView textView3, TextView textView4, TextView textView5) {
        this.rootView = relativeLayout;
        this.eetAddressName = errorEdiTextLayout;
        this.etAddressName = editText;
        this.innerTitle = textView;
        this.ivDelete = imageView;
        this.liEt = linearLayout;
        this.title = textView2;
        this.top = relativeLayout2;
        this.tvCancle = textView3;
        this.tvNote = textView4;
        this.tvOk = textView5;
    }

    public static DgAddaddressbookBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static DgAddaddressbookBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dg_addaddressbook, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DgAddaddressbookBinding bind(View view) {
        int i = R.id.eet_address_name;
        ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_address_name);
        if (errorEdiTextLayout != null) {
            i = R.id.et_address_name;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_address_name);
            if (editText != null) {
                i = R.id.inner_title;
                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.inner_title);
                if (textView != null) {
                    i = R.id.iv_delete;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_delete);
                    if (imageView != null) {
                        i = R.id.li_et;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.li_et);
                        if (linearLayout != null) {
                            i = R.id.title;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.title);
                            if (textView2 != null) {
                                i = R.id.top;
                                RelativeLayout relativeLayout = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.top);
                                if (relativeLayout != null) {
                                    i = R.id.tv_cancle;
                                    TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_cancle);
                                    if (textView3 != null) {
                                        i = R.id.tv_note;
                                        TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_note);
                                        if (textView4 != null) {
                                            i = R.id.tv_ok;
                                            TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_ok);
                                            if (textView5 != null) {
                                                return new DgAddaddressbookBinding((RelativeLayout) view, errorEdiTextLayout, editText, textView, imageView, linearLayout, textView2, relativeLayout, textView3, textView4, textView5);
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
