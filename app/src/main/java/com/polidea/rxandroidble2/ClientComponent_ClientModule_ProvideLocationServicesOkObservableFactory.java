package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.internal.util.LocationServicesOkObservableApi23Factory;
import io.reactivex.Observable;
public final class ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory implements Factory<Observable<Boolean>> {
    private final Provider<Integer> deviceSdkProvider;
    private final Provider<LocationServicesOkObservableApi23Factory> locationServicesOkObservableApi23FactoryProvider;

    public ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory(Provider<Integer> provider, Provider<LocationServicesOkObservableApi23Factory> provider2) {
        this.deviceSdkProvider = provider;
        this.locationServicesOkObservableApi23FactoryProvider = provider2;
    }

    @Override
    public Observable<Boolean> get() {
        return (Observable) Preconditions.checkNotNull(ClientComponent.ClientModule.provideLocationServicesOkObservable(this.deviceSdkProvider.get().intValue(), this.locationServicesOkObservableApi23FactoryProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory create(Provider<Integer> provider, Provider<LocationServicesOkObservableApi23Factory> provider2) {
        return new ClientComponent_ClientModule_ProvideLocationServicesOkObservableFactory(provider, provider2);
    }

    public static Observable<Boolean> proxyProvideLocationServicesOkObservable(int i, LocationServicesOkObservableApi23Factory locationServicesOkObservableApi23Factory) {
        return (Observable) Preconditions.checkNotNull(ClientComponent.ClientModule.provideLocationServicesOkObservable(i, locationServicesOkObservableApi23Factory), "Cannot return null from a non-@Nullable @Provides method");
    }
}
