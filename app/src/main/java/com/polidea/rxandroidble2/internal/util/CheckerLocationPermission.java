package com.polidea.rxandroidble2.internal.util;

import android.content.Context;
import android.os.Process;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.ClientScope;
@ClientScope
public class CheckerLocationPermission {
    private final Context context;
    private final String[] scanPermissions;

    public String[] getRecommendedScanRuntimePermissions() {
        return this.scanPermissions;
    }

    @Inject
    public CheckerLocationPermission(Context context, @Named("scan-permissions") String[] strArr) {
        this.context = context;
        this.scanPermissions = strArr;
    }

    public boolean isScanRuntimePermissionGranted() {
        for (String str : this.scanPermissions) {
            if (isPermissionGranted(str)) {
                return true;
            }
        }
        return this.scanPermissions.length == 0;
    }

    private boolean isPermissionGranted(String str) {
        if (str != null) {
            return this.context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
        }
        throw new IllegalArgumentException("permission is null");
    }
}
