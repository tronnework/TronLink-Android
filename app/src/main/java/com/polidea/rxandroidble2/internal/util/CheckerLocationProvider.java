package com.polidea.rxandroidble2.internal.util;

import android.content.ContentResolver;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
public class CheckerLocationProvider {
    private final ContentResolver contentResolver;
    private final LocationManager locationManager;

    @Inject
    public CheckerLocationProvider(ContentResolver contentResolver, LocationManager locationManager) {
        this.contentResolver = contentResolver;
        this.locationManager = locationManager;
    }

    public boolean isLocationProviderEnabled() {
        boolean isLocationEnabled;
        if (Build.VERSION.SDK_INT >= 28) {
            isLocationEnabled = this.locationManager.isLocationEnabled();
            return isLocationEnabled;
        }
        try {
            return Settings.Secure.getInt(this.contentResolver, "location_mode") != 0;
        } catch (Settings.SettingNotFoundException e) {
            RxBleLog.w(e, "Could not use LOCATION_MODE check. Falling back to a legacy/heuristic function.", new Object[0]);
            return this.locationManager.isProviderEnabled("network") || this.locationManager.isProviderEnabled("gps");
        }
    }
}
