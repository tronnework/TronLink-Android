package com.tron.wallet.business.walletmanager.input;

import android.text.Editable;
public interface TextChangedListener {

    public final class -CC {
        public static void $default$beforeTextChanged(TextChangedListener _this, CharSequence charSequence, int i, int i2, int i3) {
        }

        public static void $default$onTextChanged(TextChangedListener _this, CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    void afterTextChanged(Editable editable);

    void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3);

    void onTextChanged(CharSequence charSequence, int i, int i2, int i3);
}
