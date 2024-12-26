package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.Factory;
public final class LocationServicesStatusApi18_Factory implements Factory<LocationServicesStatusApi18> {
    private static final LocationServicesStatusApi18_Factory INSTANCE = new LocationServicesStatusApi18_Factory();

    public static LocationServicesStatusApi18_Factory create() {
        return INSTANCE;
    }

    @Override
    public LocationServicesStatusApi18 get() {
        return new LocationServicesStatusApi18();
    }

    public static LocationServicesStatusApi18 newLocationServicesStatusApi18() {
        return new LocationServicesStatusApi18();
    }
}
