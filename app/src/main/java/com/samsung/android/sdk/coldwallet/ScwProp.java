package com.samsung.android.sdk.coldwallet;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
class ScwProp {
    private static final String AUTHORITY = "com.samsung.android.coldwalletservice.properties";
    private static final Uri CONTENT_URI = Uri.parse("content://com.samsung.android.coldwalletservice.properties");
    static final String SERVICE_ACTION_NAME = "com.samsung.android.sdk.coldwallet.ICWWallet";
    static final String SERVICE_CLASS_NAME = "com.samsung.android.coldwalletservice.core.CWWalletService";
    static final String SERVICE_PKG_NAME = "com.samsung.android.coldwalletservice";

    ScwProp() {
    }

    static class Methods {
        static final String GET_VALUE = "method_get_value";

        Methods() {
        }
    }

    public static Integer getKeystoreApiLevel(Context context) {
        try {
            return intFromBundle(context.getContentResolver().call(CONTENT_URI, "method_get_value", Params.EXTRAS_KEY_API_LEVEL, (Bundle) null));
        } catch (Exception unused) {
            return null;
        }
    }

    public static int[] getSupportedCoins(Context context) {
        return intArrayFromBundle(context.getContentResolver().call(CONTENT_URI, "method_get_value", Params.EXTRAS_KEY_RESULT_SUPPORTED_COINS, (Bundle) null));
    }

    public static String getSeedHash(Context context) {
        return stringFromBundle(context.getContentResolver().call(CONTENT_URI, "method_get_value", Params.EXTRAS_KEY_RESULT_WALLET_KEY, (Bundle) null));
    }

    public static Boolean isRootSeedBackedUp(Context context) {
        return booleanFromBundle(context.getContentResolver().call(CONTENT_URI, "method_get_value", Params.EXTRAS_KEY_RESULT_BACKUP_ROOT_SEED_KEY, (Bundle) null));
    }

    private static Integer intFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return Integer.valueOf(bundle.getInt("value"));
    }

    private static int[] intArrayFromBundle(Bundle bundle) {
        int[] intArray;
        if (bundle == null || (intArray = bundle.getIntArray("value")) == null) {
            return null;
        }
        return intArray;
    }

    private static String stringFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return bundle.getString("value");
    }

    private static Boolean booleanFromBundle(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        return Boolean.valueOf(bundle.getBoolean("value"));
    }

    static Bundle bundleForValue(String str) {
        Bundle bundle = new Bundle(1);
        bundle.putString("value", str);
        return bundle;
    }

    static Bundle bundleForValue(int i) {
        Bundle bundle = new Bundle(1);
        bundle.putInt("value", i);
        return bundle;
    }

    static Bundle bundleForValue(int[] iArr) {
        Bundle bundle = new Bundle(1);
        bundle.putIntArray("value", iArr);
        return bundle;
    }

    static Bundle bundleForValue(boolean z) {
        Bundle bundle = new Bundle(1);
        bundle.putBoolean("value", z);
        return bundle;
    }

    static Bundle bundleForNull() {
        Bundle bundle = new Bundle(1);
        bundle.putString("value", null);
        return bundle;
    }
}
