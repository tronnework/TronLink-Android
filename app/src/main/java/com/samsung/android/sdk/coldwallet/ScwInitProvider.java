package com.samsung.android.sdk.coldwallet;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
public class ScwInitProvider extends ContentProvider {
    private static final String TAG = "ScwInitProvider";

    @Override
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override
    public boolean onCreate() {
        L.i(TAG, "onCreate");
        Context context = getContext();
        if (context != null && isKeystoreAvailable(context)) {
            String scwAppId = getScwAppId(context);
            L.i(TAG, "scw_app_id=" + scwAppId);
            return ScwService.initializeApp(context, scwAppId) != null;
        }
        return false;
    }

    private String getScwAppId(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                return bundle.getString("scw_app_id");
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            L.e(TAG, "given package name cannot be found.");
            return null;
        }
    }

    private boolean isKeystoreAvailable(Context context) {
        int i = 0;
        try {
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo("com.samsung.android.coldwalletservice", 4).services;
            int length = serviceInfoArr.length;
            boolean z = false;
            while (i < length) {
                try {
                    ServiceInfo serviceInfo = serviceInfoArr[i];
                    if ("com.samsung.android.coldwalletservice.core.CWWalletService".equalsIgnoreCase(serviceInfo.name) && serviceInfo.enabled) {
                        z = true;
                    }
                    i++;
                } catch (PackageManager.NameNotFoundException e) {
                    e = e;
                    i = z ? 1 : 0;
                    e.printStackTrace();
                    L.e(TAG, "Samsung Blockchain Keystore is not available in this device");
                    return i;
                }
            }
            return z;
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
        }
    }
}
