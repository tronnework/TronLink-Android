package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.NumberInput;
import j$.util.DesugarTimeZone;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
public class StdDateFormat extends DateFormat {
    protected static final DateFormat DATE_FORMAT_ISO8601;
    protected static final DateFormat DATE_FORMAT_ISO8601_Z;
    protected static final DateFormat DATE_FORMAT_PLAIN;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    private static final Locale DEFAULT_LOCALE;
    private static final TimeZone DEFAULT_TIMEZONE;
    public static final StdDateFormat instance;
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    protected static final String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String[] ALL_FORMATS = {DATE_FORMAT_STR_ISO8601, DATE_FORMAT_STR_ISO8601_Z, DATE_FORMAT_STR_RFC1123, DATE_FORMAT_STR_PLAIN};

    public static TimeZone getDefaultTimeZone() {
        return DEFAULT_TIMEZONE;
    }

    protected void _clearFormats() {
        this._formatRFC1123 = null;
        this._formatISO8601 = null;
        this._formatISO8601_z = null;
        this._formatPlain = null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override
    public TimeZone getTimeZone() {
        return this._timezone;
    }

    static {
        TimeZone timeZone = DesugarTimeZone.getTimeZone("UTC");
        DEFAULT_TIMEZONE = timeZone;
        Locale locale = Locale.US;
        DEFAULT_LOCALE = locale;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_STR_RFC1123, locale);
        DATE_FORMAT_RFC1123 = simpleDateFormat;
        simpleDateFormat.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601, locale);
        DATE_FORMAT_ISO8601 = simpleDateFormat2;
        simpleDateFormat2.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(DATE_FORMAT_STR_ISO8601_Z, locale);
        DATE_FORMAT_ISO8601_Z = simpleDateFormat3;
        simpleDateFormat3.setTimeZone(timeZone);
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat(DATE_FORMAT_STR_PLAIN, locale);
        DATE_FORMAT_PLAIN = simpleDateFormat4;
        simpleDateFormat4.setTimeZone(timeZone);
        instance = new StdDateFormat();
    }

    public StdDateFormat() {
        this._locale = DEFAULT_LOCALE;
    }

    @Deprecated
    public StdDateFormat(TimeZone timeZone, Locale locale) {
        this._timezone = timeZone;
        this._locale = locale;
    }

    protected StdDateFormat(TimeZone timeZone, Locale locale, Boolean bool) {
        this._timezone = timeZone;
        this._locale = locale;
        this._lenient = bool;
    }

    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = DEFAULT_TIMEZONE;
        }
        TimeZone timeZone2 = this._timezone;
        return (timeZone == timeZone2 || timeZone.equals(timeZone2)) ? this : new StdDateFormat(timeZone, this._locale, this._lenient);
    }

    public StdDateFormat withLocale(Locale locale) {
        return locale.equals(this._locale) ? this : new StdDateFormat(this._timezone, locale, this._lenient);
    }

    @Override
    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient);
    }

    @Deprecated
    public static DateFormat getISO8601Format(TimeZone timeZone) {
        return getISO8601Format(timeZone, DEFAULT_LOCALE);
    }

    public static DateFormat getISO8601Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, timeZone, locale, null);
    }

    public static DateFormat getRFC1123Format(TimeZone timeZone, Locale locale) {
        return _cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, timeZone, locale, null);
    }

    @Deprecated
    public static DateFormat getRFC1123Format(TimeZone timeZone) {
        return getRFC1123Format(timeZone, DEFAULT_LOCALE);
    }

    @Override
    public void setTimeZone(TimeZone timeZone) {
        if (timeZone.equals(this._timezone)) {
            return;
        }
        _clearFormats();
        this._timezone = timeZone;
    }

    @Override
    public void setLenient(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        if (this._lenient != valueOf) {
            this._lenient = valueOf;
            _clearFormats();
        }
    }

    @Override
    public boolean isLenient() {
        Boolean bool = this._lenient;
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    @Override
    public Date parse(String str) throws ParseException {
        Date parseAsRFC1123;
        String[] strArr;
        String trim = str.trim();
        ParsePosition parsePosition = new ParsePosition(0);
        if (looksLikeISO8601(trim)) {
            parseAsRFC1123 = parseAsISO8601(trim, parsePosition, true);
        } else {
            int length = trim.length();
            while (true) {
                length--;
                if (length < 0) {
                    break;
                }
                char charAt = trim.charAt(length);
                if (charAt < '0' || charAt > '9') {
                    if (length > 0 || charAt != '-') {
                        break;
                    }
                }
            }
            if (length < 0 && (trim.charAt(0) == '-' || NumberInput.inLongRange(trim, false))) {
                parseAsRFC1123 = new Date(Long.parseLong(trim));
            } else {
                parseAsRFC1123 = parseAsRFC1123(trim, parsePosition);
            }
        }
        if (parseAsRFC1123 != null) {
            return parseAsRFC1123;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append(Typography.quote);
            }
            sb.append(str2);
        }
        sb.append(Typography.quote);
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", trim, sb.toString()), parsePosition.getErrorIndex());
    }

    @Override
    public Date parse(String str, ParsePosition parsePosition) {
        if (looksLikeISO8601(str)) {
            try {
                return parseAsISO8601(str, parsePosition, false);
            } catch (ParseException unused) {
                return null;
            }
        }
        int length = str.length();
        while (true) {
            length--;
            if (length < 0) {
                break;
            }
            char charAt = str.charAt(length);
            if (charAt < '0' || charAt > '9') {
                if (length > 0 || charAt != '-') {
                    break;
                }
            }
        }
        if (length < 0 && (str.charAt(0) == '-' || NumberInput.inLongRange(str, false))) {
            return new Date(Long.parseLong(str));
        }
        return parseAsRFC1123(str, parsePosition);
    }

    @Override
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, this._timezone, this._locale, this._lenient);
        }
        return this._formatISO8601.format(date, stringBuffer, fieldPosition);
    }

    public String toString() {
        String str = "DateFormat " + getClass().getName();
        TimeZone timeZone = this._timezone;
        if (timeZone != null) {
            str = str + " (timezone: " + timeZone + ")";
        }
        return str + "(locale: " + this._locale + ")";
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }

    protected boolean looksLikeISO8601(String str) {
        return str.length() >= 5 && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && str.charAt(4) == '-';
    }

    protected Date parseAsISO8601(String str, ParsePosition parsePosition, boolean z) throws ParseException {
        DateFormat dateFormat;
        String str2;
        int length = str.length();
        int i = length - 1;
        char charAt = str.charAt(i);
        if (length > 10 || !Character.isDigit(charAt)) {
            if (charAt == 'Z') {
                DateFormat dateFormat2 = this._formatISO8601_z;
                if (dateFormat2 == null) {
                    dateFormat2 = _cloneFormat(DATE_FORMAT_ISO8601_Z, DATE_FORMAT_STR_ISO8601_Z, this._timezone, this._locale, this._lenient);
                    this._formatISO8601_z = dateFormat2;
                }
                if (str.charAt(length - 4) == ':') {
                    StringBuilder sb = new StringBuilder(str);
                    sb.insert(i, ".000");
                    str = sb.toString();
                }
                dateFormat = dateFormat2;
            } else if (hasTimeZone(str)) {
                int i2 = length - 3;
                char charAt2 = str.charAt(i2);
                if (charAt2 == ':') {
                    StringBuilder sb2 = new StringBuilder(str);
                    sb2.delete(i2, length - 2);
                    str = sb2.toString();
                } else if (charAt2 == '+' || charAt2 == '-') {
                    str = str + "00";
                }
                int length2 = str.length();
                int lastIndexOf = (length2 - str.lastIndexOf(84)) - 6;
                if (lastIndexOf < 12) {
                    int i3 = length2 - 5;
                    StringBuilder sb3 = new StringBuilder(str);
                    switch (lastIndexOf) {
                        case 6:
                            sb3.insert(i3, "00.000");
                        case 5:
                            sb3.insert(i3, ":00.000");
                            break;
                        case 8:
                            sb3.insert(i3, ".000");
                            break;
                        case 9:
                            sb3.insert(i3, "000");
                            break;
                        case 10:
                            sb3.insert(i3, "00");
                            break;
                        case 11:
                            sb3.insert(i3, '0');
                            break;
                    }
                    str = sb3.toString();
                }
                dateFormat = this._formatISO8601;
                str2 = DATE_FORMAT_STR_ISO8601;
                if (dateFormat == null) {
                    dateFormat = _cloneFormat(DATE_FORMAT_ISO8601, DATE_FORMAT_STR_ISO8601, this._timezone, this._locale, this._lenient);
                    this._formatISO8601 = dateFormat;
                }
            } else {
                StringBuilder sb4 = new StringBuilder(str);
                int lastIndexOf2 = (length - str.lastIndexOf(84)) - 1;
                if (lastIndexOf2 < 12) {
                    switch (lastIndexOf2) {
                        case 11:
                            sb4.append('0');
                        case 10:
                            sb4.append('0');
                        case 9:
                            sb4.append('0');
                            break;
                        default:
                            sb4.append(".000");
                            break;
                    }
                }
                sb4.append(Matrix.MATRIX_TYPE_ZERO);
                str = sb4.toString();
                dateFormat = this._formatISO8601_z;
                if (dateFormat == null) {
                    dateFormat = _cloneFormat(DATE_FORMAT_ISO8601_Z, DATE_FORMAT_STR_ISO8601_Z, this._timezone, this._locale, this._lenient);
                    this._formatISO8601_z = dateFormat;
                }
            }
            str2 = DATE_FORMAT_STR_ISO8601_Z;
        } else {
            dateFormat = this._formatPlain;
            str2 = DATE_FORMAT_STR_PLAIN;
            if (dateFormat == null) {
                dateFormat = _cloneFormat(DATE_FORMAT_PLAIN, DATE_FORMAT_STR_PLAIN, this._timezone, this._locale, this._lenient);
                this._formatPlain = dateFormat;
            }
        }
        Date parse = dateFormat.parse(str, parsePosition);
        if (parse != null) {
            return parse;
        }
        throw new ParseException(String.format("Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)", str, str2, this._lenient), parsePosition.getErrorIndex());
    }

    protected Date parseAsRFC1123(String str, ParsePosition parsePosition) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, DATE_FORMAT_STR_RFC1123, this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(str, parsePosition);
    }

    private static final boolean hasTimeZone(String str) {
        char charAt;
        char charAt2;
        int length = str.length();
        if (length >= 6) {
            char charAt3 = str.charAt(length - 6);
            return charAt3 == '+' || charAt3 == '-' || (charAt = str.charAt(length + (-5))) == '+' || charAt == '-' || (charAt2 = str.charAt(length + (-3))) == '+' || charAt2 == '-';
        }
        return false;
    }

    private static final DateFormat _cloneFormat(DateFormat dateFormat, String str, TimeZone timeZone, Locale locale, Boolean bool) {
        DateFormat dateFormat2;
        if (!locale.equals(DEFAULT_LOCALE)) {
            dateFormat2 = new SimpleDateFormat(str, locale);
            if (timeZone == null) {
                timeZone = DEFAULT_TIMEZONE;
            }
            dateFormat2.setTimeZone(timeZone);
        } else {
            dateFormat2 = (DateFormat) dateFormat.clone();
            if (timeZone != null) {
                dateFormat2.setTimeZone(timeZone);
            }
        }
        if (bool != null) {
            dateFormat2.setLenient(bool.booleanValue());
        }
        return dateFormat2;
    }
}
