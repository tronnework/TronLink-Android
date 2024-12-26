package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tron.wallet.common.components.ErrorEdiTextLayout;
import com.tronlinkpro.wallet.R;
public final class AcNftTestBinding implements ViewBinding {
    public final Button btParse;
    public final Button btSearch;
    public final Button btSend;
    public final ErrorEdiTextLayout eetContent;
    public final EditText etAddress;
    public final EditText etSearch;
    public final EditText etTokenid;
    private final LinearLayout rootView;
    public final TextView tvSearch;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private AcNftTestBinding(LinearLayout linearLayout, Button button, Button button2, Button button3, ErrorEdiTextLayout errorEdiTextLayout, EditText editText, EditText editText2, EditText editText3, TextView textView) {
        this.rootView = linearLayout;
        this.btParse = button;
        this.btSearch = button2;
        this.btSend = button3;
        this.eetContent = errorEdiTextLayout;
        this.etAddress = editText;
        this.etSearch = editText2;
        this.etTokenid = editText3;
        this.tvSearch = textView;
    }

    public static AcNftTestBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNftTestBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_nft_test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNftTestBinding bind(View view) {
        int i = R.id.bt_parse;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.bt_parse);
        if (button != null) {
            i = R.id.bt_search;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.bt_search);
            if (button2 != null) {
                i = R.id.bt_send;
                Button button3 = (Button) ViewBindings.findChildViewById(view, R.id.bt_send);
                if (button3 != null) {
                    i = R.id.eet_content;
                    ErrorEdiTextLayout errorEdiTextLayout = (ErrorEdiTextLayout) ViewBindings.findChildViewById(view, R.id.eet_content);
                    if (errorEdiTextLayout != null) {
                        i = R.id.et_address;
                        EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_address);
                        if (editText != null) {
                            i = R.id.et_search;
                            EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_search);
                            if (editText2 != null) {
                                i = R.id.et_tokenid;
                                EditText editText3 = (EditText) ViewBindings.findChildViewById(view, R.id.et_tokenid);
                                if (editText3 != null) {
                                    i = R.id.tv_search;
                                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_search);
                                    if (textView != null) {
                                        return new AcNftTestBinding((LinearLayout) view, button, button2, button3, errorEdiTextLayout, editText, editText2, editText3, textView);
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
