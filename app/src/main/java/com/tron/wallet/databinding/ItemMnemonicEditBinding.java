package com.tron.wallet.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.viewbinding.ViewBinding;
import com.tronlinkpro.wallet.R;
public final class ItemMnemonicEditBinding implements ViewBinding {
    private final EditText rootView;

    @Override
    public EditText getRoot() {
        return this.rootView;
    }

    private ItemMnemonicEditBinding(EditText editText) {
        this.rootView = editText;
    }

    public static ItemMnemonicEditBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemMnemonicEditBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_mnemonic_edit, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemMnemonicEditBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        return new ItemMnemonicEditBinding((EditText) view);
    }
}
