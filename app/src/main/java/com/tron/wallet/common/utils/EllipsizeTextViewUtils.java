package com.tron.wallet.common.utils;

import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
public class EllipsizeTextViewUtils {
    public static void ellipseAndSet(final TextView textView, final String str, final int i) {
        textView.post(new Runnable() {
            @Override
            public final void run() {
                EllipsizeTextViewUtils.lambda$ellipseAndSet$0(str, textView, i);
            }
        });
    }

    public static void lambda$ellipseAndSet$0(String str, TextView textView, int i) {
        int measuredWidth = textView.getMeasuredWidth();
        int i2 = i - 1;
        String substring = str.substring(0, i2);
        String substring2 = str.substring(i2, str.length());
        if (!substring2.contains("...")) {
            substring2 = substring2.replace(substring2.substring(7, substring2.length() - 7), "...");
            str = substring + substring2;
        }
        float f = measuredWidth;
        if (textView.getPaint().measureText(str) > f) {
            for (int length = substring.length() - 3; length >= 0; length--) {
                substring = substring.substring(0, length) + "...";
                str = substring + substring2;
                if (textView.getPaint().measureText(str) <= f) {
                    break;
                }
            }
        }
        textView.setText(str);
    }

    public static String ellipseNameOnly(TextView textView, String str, String str2) {
        try {
            int indexOf = str.indexOf(str2);
            if (indexOf < 0) {
                return str;
            }
            String substring = str.substring(0, indexOf - 1);
            float measuredWidth = (int) (textView.getMeasuredWidth() - textView.getTextSize());
            if (textView.getPaint().measureText(substring) <= measuredWidth) {
                return str;
            }
            String str3 = "";
            for (int length = substring.length() - 3; length >= 0; length--) {
                str3 = substring.substring(0, length) + "...";
                if (textView.getPaint().measureText(str3) <= measuredWidth) {
                    break;
                }
            }
            return str3 + str2 + str.substring(indexOf + 1);
        } catch (Exception e) {
            LogUtils.e(e);
            return str;
        }
    }
}
