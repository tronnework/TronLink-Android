package com.tron.wallet.business.vote.util;

import android.text.Editable;
import android.text.TextWatcher;
public class BaseTextWatcher implements TextWatcher {
    private AfterTextChangedCallback afterTextChangedCallback;

    public interface AfterTextChangedCallback {
        void afterTextChanged(Editable editable, TextWatcher textWatcher);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public BaseTextWatcher() {
    }

    public BaseTextWatcher(AfterTextChangedCallback afterTextChangedCallback) {
        this.afterTextChangedCallback = afterTextChangedCallback;
    }

    @Override
    public void afterTextChanged(Editable editable) {
        AfterTextChangedCallback afterTextChangedCallback = this.afterTextChangedCallback;
        if (afterTextChangedCallback != null) {
            afterTextChangedCallback.afterTextChanged(editable, this);
        }
    }
}
