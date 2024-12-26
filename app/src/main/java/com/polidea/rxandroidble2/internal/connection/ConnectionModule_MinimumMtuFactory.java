package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.Factory;
public final class ConnectionModule_MinimumMtuFactory implements Factory<Integer> {
    private static final ConnectionModule_MinimumMtuFactory INSTANCE = new ConnectionModule_MinimumMtuFactory();

    public static ConnectionModule_MinimumMtuFactory create() {
        return INSTANCE;
    }

    @Override
    public Integer get() {
        return Integer.valueOf(ConnectionModule.minimumMtu());
    }

    public static int proxyMinimumMtu() {
        return ConnectionModule.minimumMtu();
    }
}
