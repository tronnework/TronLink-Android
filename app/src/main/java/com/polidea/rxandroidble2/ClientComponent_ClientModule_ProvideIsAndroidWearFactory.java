package com.polidea.rxandroidble2;

import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideIsAndroidWearFactory implements Factory<Boolean> {
    private final Provider<Context> contextProvider;
    private final Provider<Integer> deviceSdkProvider;

    public ClientComponent_ClientModule_ProvideIsAndroidWearFactory(Provider<Context> provider, Provider<Integer> provider2) {
        this.contextProvider = provider;
        this.deviceSdkProvider = provider2;
    }

    @Override
    public Boolean get() {
        return Boolean.valueOf(ClientComponent.ClientModule.provideIsAndroidWear(this.contextProvider.get(), this.deviceSdkProvider.get().intValue()));
    }

    public static ClientComponent_ClientModule_ProvideIsAndroidWearFactory create(Provider<Context> provider, Provider<Integer> provider2) {
        return new ClientComponent_ClientModule_ProvideIsAndroidWearFactory(provider, provider2);
    }

    public static boolean proxyProvideIsAndroidWear(Context context, int i) {
        return ClientComponent.ClientModule.provideIsAndroidWear(context, i);
    }
}
