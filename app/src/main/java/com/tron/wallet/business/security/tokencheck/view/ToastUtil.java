package com.tron.wallet.business.security.tokencheck.view;

import android.content.Context;
import android.widget.Toast;
public class ToastUtil {
    private static Toast sToast;

    public static void showToast(Context context, String str) {
        if (sToast == null) {
            initToast(context, str);
        }
        sToast.setText(str);
        sToast.show();
    }

    private static void initToast(Context context, String str) {
        Toast makeText = Toast.makeText(context.getApplicationContext(), str, 0);
        sToast = makeText;
        makeText.setText(str);
    }
}
