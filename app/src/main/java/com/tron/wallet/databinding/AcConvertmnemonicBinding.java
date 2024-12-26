package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcConvertmnemonicBinding implements ViewBinding {
    public final Button btConvert;
    public final TextView copy;
    public final EditText etInnertitle;
    public final ImageView ivCopy;
    public final LinearLayout llBottom;
    private final ScrollView rootView;
    public final TextView tvPrivatekey;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private AcConvertmnemonicBinding(ScrollView scrollView, Button button, TextView textView, EditText editText, ImageView imageView, LinearLayout linearLayout, TextView textView2) {
        this.rootView = scrollView;
        this.btConvert = button;
        this.copy = textView;
        this.etInnertitle = editText;
        this.ivCopy = imageView;
        this.llBottom = linearLayout;
        this.tvPrivatekey = textView2;
    }

    public static AcConvertmnemonicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcConvertmnemonicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_convertmnemonic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcConvertmnemonicBinding bind(View view) {
        int i = R.id.bt_convert;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_convert);
        if (button != null) {
            i = R.id.copy;
            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.copy);
            if (textView != null) {
                i = R.id.et_innertitle;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_innertitle);
                if (editText != null) {
                    i = R.id.iv_copy;
                    ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.iv_copy);
                    if (imageView != null) {
                        i = R.id.ll_bottom;
                        LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_bottom);
                        if (linearLayout != null) {
                            i = R.id.tv_privatekey;
                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_privatekey);
                            if (textView2 != null) {
                                return new AcConvertmnemonicBinding((ScrollView) view, button, textView, editText, imageView, linearLayout, textView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
