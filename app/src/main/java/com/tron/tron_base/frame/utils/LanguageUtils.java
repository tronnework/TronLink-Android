package com.tron.tron_base.frame.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import com.tron.tron_base.R;
import com.tron.wallet.TronApplicationExternalSyntheticApiModelOutline0;
import java.util.Locale;
public class LanguageUtils {
    private static String F_TRONKEY = "f_TronKey";
    private static int ID_IsFirst = R.string.is_first;
    private static int ID_LanguageKey = R.string.language_key;
    public static String LAN_EN = "1";
    public static String LAN_JAN = "3";
    public static String LAN_ZH = "2";

    public enum Language {
        LAN_EN,
        LAN_ZH,
        LAN_JAN,
        LAN_ARABIC,
        LAN_KOREAN,
        LAN_PERSIAN,
        LAN_RUSSIAN,
        LAN_SPAIN
    }

    public static Language useLanguage(Context context) {
        String str = (String) SpUtils.getParam(F_TRONKEY, context, context.getString(ID_LanguageKey), "");
        return LAN_ZH.equals(str) ? Language.LAN_ZH : LAN_JAN.equals(str) ? Language.LAN_JAN : Language.LAN_EN;
    }

    public static boolean languageZH(Context context) {
        return Language.LAN_ZH == useLanguage(context);
    }

    public static boolean languageEN(Context context) {
        return Language.LAN_EN == useLanguage(context);
    }

    public static boolean isFirst(Context context) {
        return ((Boolean) SpUtils.getParam(F_TRONKEY, context, context.getString(ID_IsFirst), true)).booleanValue();
    }

    public static void setLanguage(Context context, String str) {
        SpUtils.setParam(F_TRONKEY, context, context.getString(ID_LanguageKey), str);
    }

    public static Context setLocal(Context context) {
        return updateResources(context, getSetLanguageLocale(context));
    }

    public static Locale getSetLanguageLocale() {
        return getSetLanguageLocale(AppContextUtil.getContext());
    }

    public static Locale getSetLanguageLocale(Context context) {
        Locale locale;
        LocaleList localeList;
        if (Build.VERSION.SDK_INT >= 24) {
            localeList = LocaleList.getDefault();
            locale = localeList.get(0);
        } else {
            locale = Locale.getDefault();
        }
        if (isFirst(context)) {
            if ("zh".equals(locale.getLanguage())) {
                setLanguage(context, "2");
            } else if ("ja".equals(locale.getLanguage())) {
                setLanguage(context, "3");
            } else {
                setLanguage(context, "1");
            }
        }
        Language useLanguage = useLanguage(context);
        if (Language.LAN_ZH == useLanguage) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (Language.LAN_JAN == useLanguage) {
            return Locale.JAPANESE;
        }
        return Locale.ENGLISH;
    }

    public static void setApplicationLanguage(Context context) {
        Resources resources = context.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale setLanguageLocale = getSetLanguageLocale();
        configuration.locale = setLanguageLocale;
        if (Build.VERSION.SDK_INT >= 24) {
            TronApplicationExternalSyntheticApiModelOutline0.m();
            LocaleList m = TronApplicationExternalSyntheticApiModelOutline0.m(new Locale[]{setLanguageLocale});
            LocaleList.setDefault(m);
            configuration.setLocales(m);
            context.getApplicationContext().createConfigurationContext(configuration);
            Locale.setDefault(setLanguageLocale);
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    public static void onConfigurationChanged(Context context) {
        setLocal(context);
        setApplicationLanguage(context);
    }

    private static Context updateResources(Context context, Locale locale) {
        Locale.setDefault(locale);
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    static class fun1 {
        static final int[] $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language;

        static {
            int[] iArr = new int[Language.values().length];
            $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language = iArr;
            try {
                iArr[Language.LAN_ZH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[Language.LAN_JAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[Language.LAN_KOREAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[Language.LAN_ARABIC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[Language.LAN_PERSIAN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[Language.LAN_RUSSIAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[Language.LAN_SPAIN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public static String getTronScanLang() {
        switch (fun1.$SwitchMap$com$tron$tron_base$frame$utils$LanguageUtils$Language[useLanguage(AppContextUtil.getContext()).ordinal()]) {
            case 1:
                return "lang=zh";
            case 2:
                return "lang=ja";
            case 3:
                return "lang=kr";
            case 4:
                return "lang=ar";
            case 5:
                return "lang=fa";
            case 6:
                return "lang=ru";
            case 7:
                return "lang=es";
            default:
                return "lang=en";
        }
    }
}
