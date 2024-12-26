package com.polidea.rxandroidble2.internal.util;

import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
public final class CheckerLocationPermission_Factory implements Factory<CheckerLocationPermission> {
    private final Provider<Context> contextProvider;
    private final Provider<String[]> scanPermissionsProvider;

    public CheckerLocationPermission_Factory(Provider<Context> provider, Provider<String[]> provider2) {
        this.contextProvider = provider;
        this.scanPermissionsProvider = provider2;
    }

    @Override
    public CheckerLocationPermission get() {
        return new CheckerLocationPermission(this.contextProvider.get(), this.scanPermissionsProvider.get());
    }

    public static CheckerLocationPermission_Factory create(Provider<Context> provider, Provider<String[]> provider2) {
        return new CheckerLocationPermission_Factory(provider, provider2);
    }

    public static CheckerLocationPermission newCheckerLocationPermission(Context context, String[] strArr) {
        return new CheckerLocationPermission(context, strArr);
    }
}
