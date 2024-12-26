package com.tron.wallet.common.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
public class SpannableUtils {
    public static SpannableString getTextStyleSpannable(String str, String str2, int i) {
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        SpannableString spannableString = new SpannableString(str);
        if (indexOf >= 0 && indexOf < str.length() && indexOf < length) {
            spannableString.setSpan(new StyleSpan(i), indexOf, length, 17);
        }
        return spannableString;
    }

    public static SpannableString getAddressSpannable(String str, int i) {
        if (StringTronUtil.isEmpty(str)) {
            return new SpannableString("");
        }
        int indexOf = str.indexOf("(");
        if (indexOf < 0) {
            return new SpannableString(str);
        }
        int length = str.length();
        SpannableString spannableString = new SpannableString(str);
        if (indexOf >= 0 && indexOf < str.length() && indexOf < length) {
            spannableString.setSpan(new ForegroundColorSpan(i), indexOf, length, 17);
            spannableString.setSpan(new StyleSpan(1), 0, indexOf, 17);
        }
        return spannableString;
    }

    public static SpannableString getTextColorSpannable(String str, String str2, int i) {
        return getTextColorSpannable(str, str2, i, false);
    }

    public static SpannableString getTextColorSpannable(String str, String str2, int i, boolean z) {
        int indexOf = str.indexOf(str2);
        int length = str2.length() + indexOf;
        SpannableString spannableString = new SpannableString(str);
        if (indexOf >= 0 && indexOf < str.length() && indexOf < length) {
            spannableString.setSpan(new ForegroundColorSpan(i), indexOf, length, 17);
            if (z) {
                spannableString.setSpan(new StyleSpan(1), indexOf, length, 17);
            }
        }
        return spannableString;
    }

    public static SpannableString getTextColorSpannable(String str, int i, int i2, int i3) {
        int i4;
        if (i < 0 || i >= str.length() || i2 <= 0 || (i4 = i2 + i) > str.length()) {
            return new SpannableString(str);
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(i3), i, i4, 17);
        return spannableString;
    }

    public static void autoBreakLineSpannable(final TextView textView, final String str, final String str2, final int i) {
        if (textView == null) {
            return;
        }
        textView.post(new Runnable() {
            @Override
            public final void run() {
                SpannableUtils.lambda$autoBreakLineSpannable$0(str, str2, textView, i);
            }
        });
    }

    public static void lambda$autoBreakLineSpannable$0(String str, String str2, TextView textView, int i) {
        try {
            int indexOf = str.indexOf(str2);
            if (indexOf < 0) {
                textView.setText(str);
                return;
            }
            int measuredWidth = textView.getMeasuredWidth();
            int length = str.length();
            while (length > 0) {
                if (textView.getPaint().measureText(str.subSequence(0, length).toString()) <= measuredWidth) {
                    break;
                }
                length--;
            }
            if (length <= 1) {
                textView.setText(str);
                return;
            }
            String substring = str.substring(0, length);
            String substring2 = str.substring(length);
            if (length <= indexOf) {
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(substring2);
                int i2 = indexOf + 1;
                spannableStringBuilder.setSpan(new ForegroundColorSpan(i), i2, str2.length() + i2, 17);
                textView.setText(substring + StringUtils.LF);
                textView.append(spannableStringBuilder);
            }
            if (length <= str2.length() + indexOf) {
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(substring);
                spannableStringBuilder2.setSpan(new ForegroundColorSpan(i), indexOf, length, 17);
                spannableStringBuilder2.insert(length, (CharSequence) StringUtils.LF);
                textView.setText(spannableStringBuilder2);
                SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(substring2);
                spannableStringBuilder3.setSpan(new ForegroundColorSpan(i), 0, (indexOf + str2.length()) - length, 17);
                textView.append(spannableStringBuilder3);
                return;
            }
            SpannableStringBuilder spannableStringBuilder4 = new SpannableStringBuilder(substring);
            spannableStringBuilder4.setSpan(new ForegroundColorSpan(i), indexOf, str2.length() + indexOf, 17);
            textView.setText(spannableStringBuilder4);
            textView.append(StringUtils.LF + substring2);
        } catch (Throwable th) {
            LogUtils.e(th);
            textView.setText(str);
        }
    }

    public static SpannableString getImageSpannable(Drawable drawable, int i, int i2, int i3, int i4, CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence);
        for (int i5 = i; i5 < i2 * 2; i5++) {
            sb.insert(0, " ");
        }
        ImageSpan imageSpan = new ImageSpan(drawable, 2);
        drawable.setBounds(0, 0, i3, i4);
        SpannableString spannableString = new SpannableString(sb);
        spannableString.setSpan(imageSpan, i, i2, 17);
        return spannableString;
    }

    public static SpannableString getCompatImageSpan(Drawable drawable, int i, int i2, int i3, int i4, CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence);
        for (int i5 = i; i5 < i2 * 2; i5++) {
            sb.insert(0, " ");
        }
        CenterVerticalImageSpan centerVerticalImageSpan = new CenterVerticalImageSpan(drawable);
        drawable.setBounds(0, 0, i3, i4);
        SpannableString spannableString = new SpannableString(sb);
        spannableString.setSpan(centerVerticalImageSpan, i, i2, 17);
        return spannableString;
    }

    private static class CenterVerticalImageSpan extends ImageSpan {
        CenterVerticalImageSpan(Drawable drawable) {
            super(drawable);
        }

        @Override
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            Drawable drawable = getDrawable();
            canvas.save();
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float f2 = i4;
            canvas.translate(f, (int) (((((fontMetrics.ascent + f2) + f2) + fontMetrics.descent) / 2.0f) - ((drawable.getBounds().bottom + drawable.getBounds().top) / 2)));
            drawable.draw(canvas);
            canvas.restore();
        }

        @Override
        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            Rect bounds = getDrawable().getBounds();
            if (fontMetricsInt != null) {
                fontMetricsInt.ascent = -bounds.bottom;
                fontMetricsInt.descent = 0;
                fontMetricsInt.top = fontMetricsInt.ascent;
                fontMetricsInt.bottom = 0;
            }
            return bounds.right;
        }
    }
}
