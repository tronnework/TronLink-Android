package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
public final class ConnectionModule_ProvideBluetoothGattFactory implements Factory<BluetoothGatt> {
    private final Provider<BluetoothGattProvider> bluetoothGattProvider;

    public ConnectionModule_ProvideBluetoothGattFactory(Provider<BluetoothGattProvider> provider) {
        this.bluetoothGattProvider = provider;
    }

    @Override
    public BluetoothGatt get() {
        return (BluetoothGatt) Preconditions.checkNotNull(ConnectionModule.provideBluetoothGatt(this.bluetoothGattProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ConnectionModule_ProvideBluetoothGattFactory create(Provider<BluetoothGattProvider> provider) {
        return new ConnectionModule_ProvideBluetoothGattFactory(provider);
    }

    public static BluetoothGatt proxyProvideBluetoothGatt(BluetoothGattProvider bluetoothGattProvider) {
        return (BluetoothGatt) Preconditions.checkNotNull(ConnectionModule.provideBluetoothGatt(bluetoothGattProvider), "Cannot return null from a non-@Nullable @Provides method");
    }
}
