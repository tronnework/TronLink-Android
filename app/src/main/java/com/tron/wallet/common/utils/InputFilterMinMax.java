package com.tron.wallet.common.utils;

import android.text.InputFilter;
import android.text.Spanned;
public class InputFilterMinMax implements InputFilter {
    private double max;
    private double min;

    private boolean isInRange(double d, double d2, double d3) {
        if (d2 > d) {
            if (d3 >= d && d3 <= d2) {
                return true;
            }
        } else if (d3 >= d2 && d3 <= d) {
            return true;
        }
        return false;
    }

    public InputFilterMinMax(double d, double d2) {
        this.min = d;
        this.max = d2;
    }

    public InputFilterMinMax(String str, String str2) {
        this.min = Double.parseDouble(str);
        this.max = Double.parseDouble(str2);
    }

    @Override
    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        try {
            String str = spanned.toString().substring(0, i3) + spanned.toString().substring(i4, spanned.toString().length());
            if (isInRange(this.min, this.max, Double.parseDouble(str.substring(0, i3) + charSequence.toString() + str.substring(i3, str.length())))) {
                return null;
            }
            return "";
        } catch (NumberFormatException e) {
            SentryUtil.captureException(e);
            return "";
        }
    }
}
