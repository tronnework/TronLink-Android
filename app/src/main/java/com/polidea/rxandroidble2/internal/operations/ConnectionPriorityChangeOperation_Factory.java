package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
public final class ConnectionPriorityChangeOperation_Factory implements Factory<ConnectionPriorityChangeOperation> {
    private final Provider<BluetoothGatt> bluetoothGattProvider;
    private final Provider<Integer> connectionPriorityProvider;
    private final Provider<RxBleGattCallback> rxBleGattCallbackProvider;
    private final Provider<TimeoutConfiguration> successTimeoutConfigurationAndTimeoutConfigurationProvider;

    public ConnectionPriorityChangeOperation_Factory(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4) {
        this.rxBleGattCallbackProvider = provider;
        this.bluetoothGattProvider = provider2;
        this.successTimeoutConfigurationAndTimeoutConfigurationProvider = provider3;
        this.connectionPriorityProvider = provider4;
    }

    @Override
    public ConnectionPriorityChangeOperation get() {
        return new ConnectionPriorityChangeOperation(this.rxBleGattCallbackProvider.get(), this.bluetoothGattProvider.get(), this.successTimeoutConfigurationAndTimeoutConfigurationProvider.get(), this.connectionPriorityProvider.get().intValue(), this.successTimeoutConfigurationAndTimeoutConfigurationProvider.get());
    }

    public static ConnectionPriorityChangeOperation_Factory create(Provider<RxBleGattCallback> provider, Provider<BluetoothGatt> provider2, Provider<TimeoutConfiguration> provider3, Provider<Integer> provider4) {
        return new ConnectionPriorityChangeOperation_Factory(provider, provider2, provider3, provider4);
    }

    public static ConnectionPriorityChangeOperation newConnectionPriorityChangeOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, TimeoutConfiguration timeoutConfiguration, int i, TimeoutConfiguration timeoutConfiguration2) {
        return new ConnectionPriorityChangeOperation(rxBleGattCallback, bluetoothGatt, timeoutConfiguration, i, timeoutConfiguration2);
    }
}
