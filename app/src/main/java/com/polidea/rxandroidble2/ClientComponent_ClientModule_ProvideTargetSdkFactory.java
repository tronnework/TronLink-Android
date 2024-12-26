package com.polidea.rxandroidble2;

import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideTargetSdkFactory implements Factory<Integer> {
    private final Provider<Context> contextProvider;

    public ClientComponent_ClientModule_ProvideTargetSdkFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override
    public Integer get() {
        return Integer.valueOf(ClientComponent.ClientModule.provideTargetSdk(this.contextProvider.get()));
    }

    public static ClientComponent_ClientModule_ProvideTargetSdkFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideTargetSdkFactory(provider);
    }

    public static int proxyProvideTargetSdk(Context context) {
        return ClientComponent.ClientModule.provideTargetSdk(context);
    }
}
