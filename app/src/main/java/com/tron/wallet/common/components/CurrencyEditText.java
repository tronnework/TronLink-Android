package com.tron.wallet.common.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.R;
import java.util.ArrayList;
import java.util.Iterator;
public class CurrencyEditText extends AppCompatEditText {
    private int DOTValue;
    private int INTValue;
    private ArrayList<TextWatcher> mListeners;
    private OnPasteCallback mOnPasteCallback;

    public interface OnPasteCallback {
        void onPaste();
    }

    public void setDOTValue(int i) {
        this.DOTValue = i;
    }

    public void setINTValue(int i) {
        this.INTValue = i;
    }

    public void setOnPasteCallback(OnPasteCallback onPasteCallback) {
        this.mOnPasteCallback = onPasteCallback;
    }

    public CurrencyEditText(Context context) {
        super(context);
        this.INTValue = 23;
        this.DOTValue = 18;
        this.mListeners = null;
    }

    public CurrencyEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.INTValue = 23;
        this.DOTValue = 18;
        this.mListeners = null;
        init(context, attributeSet);
    }

    public CurrencyEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.INTValue = 23;
        this.DOTValue = 18;
        this.mListeners = null;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CurrencyEditText);
        if (obtainStyledAttributes != null) {
            this.INTValue = obtainStyledAttributes.getInteger(1, 23);
            this.DOTValue = obtainStyledAttributes.getInteger(0, 18);
            obtainStyledAttributes.recycle();
        }
    }

    @Override
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (spanned.length() == 1 && spanned.toString().equals("0") && charSequence.equals("0")) {
                    return "";
                }
                if (spanned.length() == 1 && spanned.toString().equals("0") && charSequence.toString().matches("[1-9]")) {
                    setText(charSequence);
                    return "";
                } else if (spanned.toString().equals("0") && charSequence.equals("0")) {
                    return "";
                } else {
                    if (spanned.length() == 0 && charSequence.equals(ThreadPoolManager.DOT)) {
                        return "0.";
                    }
                    String obj = spanned.toString();
                    int indexOf = obj.indexOf(ThreadPoolManager.DOT);
                    String[] split = obj.split("\\.");
                    if (split.length > 1) {
                        String str = split[1];
                        String str2 = split[0];
                        if (i3 <= indexOf) {
                            if (str2.length() >= INTValue) {
                                return "";
                            }
                        } else if (str.length() >= DOTValue) {
                            return "";
                        }
                    } else if (obj.length() < INTValue || charSequence.toString().equals(ThreadPoolManager.DOT)) {
                        return null;
                    } else {
                        if (obj.length() == INTValue && obj.endsWith(ThreadPoolManager.DOT)) {
                            return null;
                        }
                        if (indexOf == -1 || i3 <= indexOf) {
                            return "";
                        }
                        return null;
                    }
                    return null;
                }
            }
        }});
    }

    @Override
    public void addTextChangedListener(TextWatcher textWatcher) {
        if (this.mListeners == null) {
            this.mListeners = new ArrayList<>();
        }
        this.mListeners.add(textWatcher);
        super.addTextChangedListener(textWatcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher textWatcher) {
        int indexOf;
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null && (indexOf = arrayList.indexOf(textWatcher)) >= 0) {
            this.mListeners.remove(indexOf);
        }
        super.removeTextChangedListener(textWatcher);
    }

    public void clearTextChangedListeners() {
        ArrayList<TextWatcher> arrayList = this.mListeners;
        if (arrayList != null) {
            Iterator<TextWatcher> it = arrayList.iterator();
            while (it.hasNext()) {
                super.removeTextChangedListener(it.next());
            }
            this.mListeners.clear();
            this.mListeners = null;
        }
    }

    @Override
    public boolean onTextContextMenuItem(int i) {
        OnPasteCallback onPasteCallback;
        if (i == 16908322 && (onPasteCallback = this.mOnPasteCallback) != null) {
            onPasteCallback.onPaste();
        }
        return super.onTextContextMenuItem(i);
    }
}
