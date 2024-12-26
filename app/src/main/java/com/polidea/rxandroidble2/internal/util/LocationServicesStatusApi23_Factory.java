package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
public final class LocationServicesStatusApi23_Factory implements Factory<LocationServicesStatusApi23> {
    private final Provider<CheckerLocationPermission> checkerLocationPermissionProvider;
    private final Provider<CheckerLocationProvider> checkerLocationProvider;
    private final Provider<Integer> deviceSdkProvider;
    private final Provider<Boolean> isAndroidWearProvider;
    private final Provider<Integer> targetSdkProvider;

    public LocationServicesStatusApi23_Factory(Provider<CheckerLocationProvider> provider, Provider<CheckerLocationPermission> provider2, Provider<Integer> provider3, Provider<Integer> provider4, Provider<Boolean> provider5) {
        this.checkerLocationProvider = provider;
        this.checkerLocationPermissionProvider = provider2;
        this.targetSdkProvider = provider3;
        this.deviceSdkProvider = provider4;
        this.isAndroidWearProvider = provider5;
    }

    @Override
    public LocationServicesStatusApi23 get() {
        return new LocationServicesStatusApi23(this.checkerLocationProvider.get(), this.checkerLocationPermissionProvider.get(), this.targetSdkProvider.get().intValue(), this.deviceSdkProvider.get().intValue(), this.isAndroidWearProvider.get().booleanValue());
    }

    public static LocationServicesStatusApi23_Factory create(Provider<CheckerLocationProvider> provider, Provider<CheckerLocationPermission> provider2, Provider<Integer> provider3, Provider<Integer> provider4, Provider<Boolean> provider5) {
        return new LocationServicesStatusApi23_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static LocationServicesStatusApi23 newLocationServicesStatusApi23(CheckerLocationProvider checkerLocationProvider, CheckerLocationPermission checkerLocationPermission, int i, int i2, boolean z) {
        return new LocationServicesStatusApi23(checkerLocationProvider, checkerLocationPermission, i, i2, z);
    }
}
