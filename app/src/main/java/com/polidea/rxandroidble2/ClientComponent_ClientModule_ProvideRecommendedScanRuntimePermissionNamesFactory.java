package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory implements Factory<String[]> {
    private final Provider<Integer> deviceSdkProvider;
    private final Provider<Integer> targetSdkProvider;

    public ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory(Provider<Integer> provider, Provider<Integer> provider2) {
        this.deviceSdkProvider = provider;
        this.targetSdkProvider = provider2;
    }

    @Override
    public String[] get() {
        return (String[]) Preconditions.checkNotNull(ClientComponent.ClientModule.provideRecommendedScanRuntimePermissionNames(this.deviceSdkProvider.get().intValue(), this.targetSdkProvider.get().intValue()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory create(Provider<Integer> provider, Provider<Integer> provider2) {
        return new ClientComponent_ClientModule_ProvideRecommendedScanRuntimePermissionNamesFactory(provider, provider2);
    }

    public static String[] proxyProvideRecommendedScanRuntimePermissionNames(int i, int i2) {
        return (String[]) Preconditions.checkNotNull(ClientComponent.ClientModule.provideRecommendedScanRuntimePermissionNames(i, i2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
