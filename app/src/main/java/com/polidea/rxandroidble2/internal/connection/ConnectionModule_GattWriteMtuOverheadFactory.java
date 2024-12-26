package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.Factory;
public final class ConnectionModule_GattWriteMtuOverheadFactory implements Factory<Integer> {
    private static final ConnectionModule_GattWriteMtuOverheadFactory INSTANCE = new ConnectionModule_GattWriteMtuOverheadFactory();

    public static ConnectionModule_GattWriteMtuOverheadFactory create() {
        return INSTANCE;
    }

    @Override
    public Integer get() {
        return Integer.valueOf(ConnectionModule.gattWriteMtuOverhead());
    }

    public static int proxyGattWriteMtuOverhead() {
        return ConnectionModule.gattWriteMtuOverhead();
    }
}
