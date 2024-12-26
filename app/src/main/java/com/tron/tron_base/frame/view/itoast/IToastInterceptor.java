package com.tron.tron_base.frame.view.itoast;

import android.widget.Toast;
public interface IToastInterceptor {
    boolean intercept(Toast toast, CharSequence charSequence);
}
