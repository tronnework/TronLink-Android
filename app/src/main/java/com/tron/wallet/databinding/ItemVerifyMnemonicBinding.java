package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemVerifyMnemonicBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvItem1;
    public final TextView tvItem2;
    public final TextView tvItem3;
    public final TextView tvNumber;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemVerifyMnemonicBinding(LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = linearLayout;
        this.tvItem1 = textView;
        this.tvItem2 = textView2;
        this.tvItem3 = textView3;
        this.tvNumber = textView4;
    }

    public static ItemVerifyMnemonicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemVerifyMnemonicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_verify_mnemonic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemVerifyMnemonicBinding bind(View view) {
        int i = R.id.tv_item1;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_item1);
        if (textView != null) {
            i = R.id.tv_item2;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_item2);
            if (textView2 != null) {
                i = R.id.tv_item3;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_item3);
                if (textView3 != null) {
                    i = R.id.tv_number;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.tv_number);
                    if (textView4 != null) {
                        return new ItemVerifyMnemonicBinding((LinearLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
