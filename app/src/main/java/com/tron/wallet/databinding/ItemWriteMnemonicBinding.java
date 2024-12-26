package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.tronlinkpro.wallet.R;
public final class ItemWriteMnemonicBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView tvItem;

    @Override
    public LinearLayout getRoot() {
        return this.rootView;
    }

    private ItemWriteMnemonicBinding(LinearLayout linearLayout, TextView textView) {
        this.rootView = linearLayout;
        this.tvItem = textView;
    }

    public static ItemWriteMnemonicBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemWriteMnemonicBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_write_mnemonic, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemWriteMnemonicBinding bind(View view) {
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.tv_item);
        if (textView != null) {
            return new ItemWriteMnemonicBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.tv_item)));
    }
}
