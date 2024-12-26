package com.tron.wallet.business.tabswap.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.vote.util.BaseTextWatcher;
public class AmountTextWatcher extends BaseTextWatcher {
    private IOnAmountChangedCallback callback;
    private final int direction;
    private int fraction = 0;
    private final EditText host;

    public interface IOnAmountChangedCallback {
        void onAmountChanged(int i, String str);
    }

    public void setCallback(IOnAmountChangedCallback iOnAmountChangedCallback) {
        this.callback = iOnAmountChangedCallback;
    }

    public void setFraction(int i) {
        this.fraction = i;
    }

    public AmountTextWatcher(int i, EditText editText) {
        this.direction = i;
        this.host = editText;
    }

    public String getFractionString(String str) {
        int indexOf = str.indexOf(ThreadPoolManager.DOT);
        return indexOf < 0 ? str : str.substring(0, indexOf + this.fraction + 1);
    }

    public boolean validFraction(String str) {
        int indexOf;
        return TextUtils.isEmpty(str) || this.fraction == 0 || (indexOf = str.indexOf(ThreadPoolManager.DOT)) < 0 || (str.length() - 1) - indexOf <= this.fraction;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        if (!validFraction(obj)) {
            obj = getFractionString(obj);
        }
        this.host.removeTextChangedListener(this);
        this.host.setTextKeepState(obj);
        this.host.addTextChangedListener(this);
        if (!TextUtils.isEmpty(obj) && obj.endsWith(ThreadPoolManager.DOT)) {
            obj = obj + "0";
        }
        IOnAmountChangedCallback iOnAmountChangedCallback = this.callback;
        if (iOnAmountChangedCallback != null) {
            iOnAmountChangedCallback.onAmountChanged(this.direction, obj);
        }
    }
}
