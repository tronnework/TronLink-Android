package com.tron.tron_base.frame.view.itoast;

import android.widget.Toast;
public class ToastInterceptor implements IToastInterceptor {
    @Override
    public boolean intercept(Toast toast, CharSequence charSequence) {
        return charSequence == null || "".equals(charSequence.toString());
    }
}
