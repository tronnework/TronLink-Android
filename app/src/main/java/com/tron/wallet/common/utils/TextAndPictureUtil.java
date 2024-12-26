package com.tron.wallet.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
public class TextAndPictureUtil {
    private TextAndPictureUtil mTextAndPictureUtil;

    private TextAndPictureUtil() {
    }

    public TextAndPictureUtil getInstance() {
        if (this.mTextAndPictureUtil == null) {
            synchronized (TextAndPictureUtil.class) {
                if (this.mTextAndPictureUtil == null) {
                    this.mTextAndPictureUtil = new TextAndPictureUtil();
                }
            }
        }
        return this.mTextAndPictureUtil;
    }

    public static SpannableString getText(Context context, String str, int i) {
        SpannableString spannableString = new SpannableString("  " + str);
        Drawable drawable = context.getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        spannableString.setSpan(new VerticalImageSpan(drawable), 0, 1, 33);
        return spannableString;
    }
}
