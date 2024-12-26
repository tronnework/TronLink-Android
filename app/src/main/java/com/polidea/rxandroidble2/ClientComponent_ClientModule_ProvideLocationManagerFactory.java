package com.polidea.rxandroidble2;

import android.content.Context;
import android.location.LocationManager;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideLocationManagerFactory implements Factory<LocationManager> {
    private final Provider<Context> contextProvider;

    public ClientComponent_ClientModule_ProvideLocationManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override
    public LocationManager get() {
        return (LocationManager) Preconditions.checkNotNull(ClientComponent.ClientModule.provideLocationManager(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideLocationManagerFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideLocationManagerFactory(provider);
    }

    public static LocationManager proxyProvideLocationManager(Context context) {
        return (LocationManager) Preconditions.checkNotNull(ClientComponent.ClientModule.provideLocationManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
