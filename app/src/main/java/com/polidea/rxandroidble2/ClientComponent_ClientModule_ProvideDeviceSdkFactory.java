package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.Factory;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideDeviceSdkFactory implements Factory<Integer> {
    private static final ClientComponent_ClientModule_ProvideDeviceSdkFactory INSTANCE = new ClientComponent_ClientModule_ProvideDeviceSdkFactory();

    public static ClientComponent_ClientModule_ProvideDeviceSdkFactory create() {
        return INSTANCE;
    }

    @Override
    public Integer get() {
        return Integer.valueOf(ClientComponent.ClientModule.provideDeviceSdk());
    }

    public static int proxyProvideDeviceSdk() {
        return ClientComponent.ClientModule.provideDeviceSdk();
    }
}
