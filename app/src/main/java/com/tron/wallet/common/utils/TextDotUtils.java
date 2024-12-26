package com.tron.wallet.common.utils;

import android.graphics.Color;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import org.apache.commons.lang3.StringUtils;
public class TextDotUtils {
    public static CharSequence getTextDisplayWithMidDot(TextView textView, String str, String str2, boolean z) {
        String textDisplayWithMidDotWrap = getTextDisplayWithMidDotWrap(textView, str, str2, z);
        if (textDisplayWithMidDotWrap != null && textDisplayWithMidDotWrap.contains("(") && textDisplayWithMidDotWrap.contains(")")) {
            SpannableString spannableString = new SpannableString(textDisplayWithMidDotWrap);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), textDisplayWithMidDotWrap.indexOf("("), textDisplayWithMidDotWrap.length(), 17);
            textView.setText(spannableString);
            if (z) {
                textView.setEnabled(false);
            }
            return textDisplayWithMidDotWrap;
        }
        textView.setText(textDisplayWithMidDotWrap);
        if (z) {
            textView.setEnabled(false);
        }
        return textDisplayWithMidDotWrap;
    }

    public static String getTextDisplayWithMidDotWrap(TextView textView, String str, String str2, boolean z) {
        if (textView == null) {
            return "";
        }
        try {
            TextPaint paint = textView.getPaint();
            if (StringTronUtil.isNullOrEmpty(str)) {
                if (textView.getWidth() < ((int) paint.measureText(str2))) {
                    return str2.substring(0, (str2.length() / 2) - 4) + "..." + str2.substring((str2.length() / 2) + 4);
                }
                return str2;
            }
            (str + " (" + str2 + ")").length();
            int measureText = (int) paint.measureText(str + " (" + str2 + ")");
            int width = textView.getWidth();
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" ()");
            paint.measureText(sb.toString());
            paint.measureText(str2);
            if (width == 0) {
                width = UIUtils.getScreenWidth(textView.getContext()) - UIUtils.dip2px(135.0f);
            }
            if (width - 100 < measureText) {
                for (int i = 0; i < str2.length() / 2; i++) {
                    int length = ((str2.length() / 2) - 2) - i;
                    if (length >= str2.length()) {
                        length = str2.length() / 2;
                    }
                    String str3 = str + " (" + str2.substring(0, length) + "..." + str2.substring(str2.length() - length) + ")";
                    if (((int) paint.measureText(str3)) <= width) {
                        return str3;
                    }
                }
                return str + " (" + str2 + ")";
            }
            return str + " (" + str2 + ")";
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureMessage(e.getMessage() + "Address:" + str2);
            return str2;
        }
    }

    public static void setTextChangedListener_Dot(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String str;
                if (i3 != i2) {
                    String str2 = "";
                    String replace = charSequence.toString().replace(",", "");
                    int indexOf = replace.indexOf(ThreadPoolManager.DOT);
                    if (indexOf > 0) {
                        String[] split = replace.split("\\.");
                        String str3 = split[0];
                        str = split.length > 1 ? split[1] : "";
                        replace = str3;
                    } else {
                        str = "";
                    }
                    int length = replace.length() / 3;
                    if (replace.length() >= 3) {
                        int length2 = replace.length() % 3;
                        if (length2 == 0) {
                            length = (replace.length() / 3) - 1;
                            length2 = 3;
                        }
                        for (int i4 = 0; i4 < length; i4++) {
                            str2 = str2 + replace.substring(0, length2) + "," + replace.substring(length2, 3);
                            replace = replace.substring(3, replace.length());
                        }
                        String str4 = str2 + replace;
                        if (indexOf > 0) {
                            editText.setText(str4 + ThreadPoolManager.DOT + str);
                        } else {
                            editText.setText(str4);
                        }
                    }
                }
                EditText editText2 = editText;
                editText2.setSelection(editText2.getText().length());
            }
        });
    }

    public static void setText_Dot(TextView textView, String str) {
        setText_Dot(textView, str, null);
    }

    public static void setText_Dot(TextView textView, String str, String str2) {
        String str3;
        String replace = str.toString().replace(",", "");
        int indexOf = replace.indexOf(ThreadPoolManager.DOT);
        if (indexOf > 0) {
            String[] split = replace.split("\\.");
            String str4 = split[0];
            str3 = split.length > 1 ? split[1] : "";
            replace = str4;
        } else {
            str3 = "";
        }
        int length = replace.length() / 3;
        if (replace.length() >= 3) {
            int length2 = replace.length() % 3;
            if (length2 == 0) {
                length = (replace.length() / 3) - 1;
                length2 = 3;
            }
            String str5 = "";
            for (int i = 0; i < length; i++) {
                str5 = str5 + replace.substring(0, length2) + "," + replace.substring(length2, 3);
                replace = replace.substring(3, replace.length());
            }
            str = str5 + replace;
            if (indexOf > 0) {
                str = str + ThreadPoolManager.DOT + str3;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str2 != null ? " " + str2 : "");
        textView.setText(sb.toString());
    }

    public static String getText_EditText_Dot(EditText editText) {
        return editText.getText().toString().trim().replace(",", "");
    }

    public static String getText_TextView_Dot(TextView textView) {
        return textView.getText().toString().trim().replace(",", "");
    }

    public static String getDotText(String str) {
        String str2;
        String str3 = "";
        String replace = str.toString().replace(",", "");
        int indexOf = replace.indexOf(ThreadPoolManager.DOT);
        if (indexOf > 0) {
            String[] split = replace.split("\\.");
            String str4 = split[0];
            str2 = split.length > 1 ? split[1] : "";
            replace = str4;
        } else {
            str2 = "";
        }
        int length = replace.length() / 3;
        if (replace.length() >= 3) {
            int length2 = replace.length() % 3;
            if (length2 == 0) {
                length = (replace.length() / 3) - 1;
                length2 = 3;
            }
            for (int i = 0; i < length; i++) {
                str3 = str3 + replace.substring(0, length2) + "," + replace.substring(length2, 3);
                replace = replace.substring(3, replace.length());
            }
            String str5 = str3 + replace;
            if (indexOf > 0) {
                return str5 + ThreadPoolManager.DOT + str2;
            }
            return str5;
        }
        return str;
    }

    public static String[] autoSplitText(TextView textView, String str) {
        return autoSplitText(textView, str, false);
    }

    public static String[] autoSplitText(TextView textView, String str, boolean z) {
        if (textView == null || textView.getText() == null || str == null) {
            return new String[]{"", ""};
        }
        try {
            String charSequence = textView.getText().toString();
            int lastIndexOf = z ? charSequence.lastIndexOf(str) : charSequence.indexOf(str);
            int length = str.length() + lastIndexOf;
            TextPaint paint = textView.getPaint();
            float width = (textView.getWidth() - textView.getPaddingLeft()) - textView.getPaddingRight();
            String[] split = charSequence.replaceAll(StringUtils.CR, "").split(StringUtils.LF);
            StringBuilder sb = new StringBuilder();
            for (String str2 : split) {
                if (paint.measureText(str2) <= width) {
                    sb.append(str2);
                } else {
                    int i = 0;
                    float f = 0.0f;
                    while (i != str2.length()) {
                        char charAt = str2.charAt(i);
                        f += paint.measureText(String.valueOf(charAt));
                        if (f <= width) {
                            sb.append(charAt);
                        } else {
                            sb.append(StringUtils.LF);
                            if (i < lastIndexOf) {
                                lastIndexOf++;
                            } else {
                                if (i <= length) {
                                }
                                i--;
                                f = 0.0f;
                            }
                            length++;
                            i--;
                            f = 0.0f;
                        }
                        i++;
                    }
                }
                sb.append(StringUtils.LF);
            }
            if (!charSequence.endsWith(StringUtils.LF)) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return new String[]{sb.toString(), sb.substring(lastIndexOf, length)};
        } catch (Exception e) {
            LogUtils.e(e);
            return new String[]{textView.getText().toString(), str};
        }
    }
}
