package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcBip32testBinding implements ViewBinding {
    public final Button bt10000;
    public final Button btCreate;
    public final Button btDefaultCreate;
    public final EditText etAccountIndex;
    public final EditText etChange;
    private final RelativeLayout rootView;
    public final TextView tvAddress;
    public final TextView tvAddress2;

    @Override
    public RelativeLayout getRoot() {
        return this.rootView;
    }

    private AcBip32testBinding(RelativeLayout relativeLayout, Button button, Button button2, Button button3, EditText editText, EditText editText2, TextView textView, TextView textView2) {
        this.rootView = relativeLayout;
        this.bt10000 = button;
        this.btCreate = button2;
        this.btDefaultCreate = button3;
        this.etAccountIndex = editText;
        this.etChange = editText2;
        this.tvAddress = textView;
        this.tvAddress2 = textView2;
    }

    public static AcBip32testBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcBip32testBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_bip32test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcBip32testBinding bind(View view) {
        int i = R.id.bt_10000;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_10000);
        if (button != null) {
            i = R.id.bt_create;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_create);
            if (button2 != null) {
                i = R.id.bt_default_create;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.bt_default_create);
                if (button3 != null) {
                    i = R.id.et_account_index;
                    EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_account_index);
                    if (editText != null) {
                        i = R.id.et_change;
                        EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_change);
                        if (editText2 != null) {
                            i = R.id.tv_address;
                            TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address);
                            if (textView != null) {
                                i = R.id.tv_address2;
                                TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_address2);
                                if (textView2 != null) {
                                    return new AcBip32testBinding((RelativeLayout) view, button, button2, button3, editText, editText2, textView, textView2);
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
