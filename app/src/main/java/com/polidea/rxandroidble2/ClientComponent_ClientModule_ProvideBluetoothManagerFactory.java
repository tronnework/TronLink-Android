package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothManager;
import android.content.Context;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
public final class ClientComponent_ClientModule_ProvideBluetoothManagerFactory implements Factory<BluetoothManager> {
    private final Provider<Context> contextProvider;

    public ClientComponent_ClientModule_ProvideBluetoothManagerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    @Override
    public BluetoothManager get() {
        return (BluetoothManager) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothManager(this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideBluetoothManagerFactory create(Provider<Context> provider) {
        return new ClientComponent_ClientModule_ProvideBluetoothManagerFactory(provider);
    }

    public static BluetoothManager proxyProvideBluetoothManager(Context context) {
        return (BluetoothManager) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothManager(context), "Cannot return null from a non-@Nullable @Provides method");
    }
}
