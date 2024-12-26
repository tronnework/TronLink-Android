package com.tron.wallet.common.utils;

import android.content.Context;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import com.tronlinkpro.wallet.R;
public class CustomFontUtils {
    public static Typeface getTypeface(Context context, int i) {
        Typeface font;
        try {
            if (i == 1) {
                font = ResourcesCompat.getFont(context, R.font.lato_bold_4);
            } else {
                font = ResourcesCompat.getFont(context, R.font.lato_regular_15);
            }
            return font;
        } catch (Throwable unused) {
            return Typeface.defaultFromStyle(i);
        }
    }
}
