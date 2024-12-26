package com.polidea.rxandroidble2.internal.util;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
public class LocationServicesStatusApi23 implements LocationServicesStatus {
    private final CheckerLocationPermission checkerLocationPermission;
    private final CheckerLocationProvider checkerLocationProvider;
    private final int deviceSdk;
    private final boolean isAndroidWear;
    private final int targetSdk;

    private boolean isLocationProviderEnabledRequired() {
        return !this.isAndroidWear && (this.deviceSdk >= 29 || this.targetSdk >= 23);
    }

    @Inject
    public LocationServicesStatusApi23(CheckerLocationProvider checkerLocationProvider, CheckerLocationPermission checkerLocationPermission, @Named("target-sdk") int i, @Named("device-sdk") int i2, @Named("android-wear") boolean z) {
        this.checkerLocationProvider = checkerLocationProvider;
        this.checkerLocationPermission = checkerLocationPermission;
        this.targetSdk = i;
        this.deviceSdk = i2;
        this.isAndroidWear = z;
    }

    @Override
    public boolean isLocationPermissionOk() {
        return this.checkerLocationPermission.isScanRuntimePermissionGranted();
    }

    @Override
    public boolean isLocationProviderOk() {
        return !isLocationProviderEnabledRequired() || this.checkerLocationProvider.isLocationProviderEnabled();
    }
}
