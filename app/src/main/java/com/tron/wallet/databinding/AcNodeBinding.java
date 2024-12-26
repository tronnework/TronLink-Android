package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class AcNodeBinding implements ViewBinding {
    public final Button connetFullnormal;
    public final Button connetSolnormal;
    public final EditText etChainid;
    public final EditText etFullip;
    public final EditText etFullport;
    public final EditText etSolip;
    public final EditText etSolport;
    public final LinearLayout llChainid;
    public final LinearLayout root;
    private final ScrollView rootView;

    @Override
    public ScrollView getRoot() {
        return this.rootView;
    }

    private AcNodeBinding(ScrollView scrollView, Button button, Button button2, EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, LinearLayout linearLayout, LinearLayout linearLayout2) {
        this.rootView = scrollView;
        this.connetFullnormal = button;
        this.connetSolnormal = button2;
        this.etChainid = editText;
        this.etFullip = editText2;
        this.etFullport = editText3;
        this.etSolip = editText4;
        this.etSolport = editText5;
        this.llChainid = linearLayout;
        this.root = linearLayout2;
    }

    public static AcNodeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AcNodeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.ac_node, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AcNodeBinding bind(View view) {
        int i = R.id.connet_fullnormal;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.connet_fullnormal);
        if (button != null) {
            i = R.id.connet_solnormal;
            Button button2 = (Button) ViewBindings.findChildViewById(view, R.id.connet_solnormal);
            if (button2 != null) {
                i = R.id.et_chainid;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.et_chainid);
                if (editText != null) {
                    i = R.id.et_fullip;
                    EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.et_fullip);
                    if (editText2 != null) {
                        i = R.id.et_fullport;
                        EditText editText3 = (EditText) ViewBindings.findChildViewById(view, R.id.et_fullport);
                        if (editText3 != null) {
                            i = R.id.et_solip;
                            EditText editText4 = (EditText) ViewBindings.findChildViewById(view, R.id.et_solip);
                            if (editText4 != null) {
                                i = R.id.et_solport;
                                EditText editText5 = (EditText) ViewBindings.findChildViewById(view, R.id.et_solport);
                                if (editText5 != null) {
                                    i = R.id.ll_chainid;
                                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll_chainid);
                                    if (linearLayout != null) {
                                        i = R.id.root;
                                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.root);
                                        if (linearLayout2 != null) {
                                            return new AcNodeBinding((ScrollView) view, button, button2, editText, editText2, editText3, editText4, editText5, linearLayout, linearLayout2);
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
