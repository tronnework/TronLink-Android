package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
public final class DeviceModule_ProvideBluetoothDeviceFactory implements Factory<BluetoothDevice> {
    private final Provider<RxBleAdapterWrapper> adapterWrapperProvider;
    private final Provider<String> macAddressProvider;

    public DeviceModule_ProvideBluetoothDeviceFactory(Provider<String> provider, Provider<RxBleAdapterWrapper> provider2) {
        this.macAddressProvider = provider;
        this.adapterWrapperProvider = provider2;
    }

    @Override
    public BluetoothDevice get() {
        return (BluetoothDevice) Preconditions.checkNotNull(DeviceModule.provideBluetoothDevice(this.macAddressProvider.get(), this.adapterWrapperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static DeviceModule_ProvideBluetoothDeviceFactory create(Provider<String> provider, Provider<RxBleAdapterWrapper> provider2) {
        return new DeviceModule_ProvideBluetoothDeviceFactory(provider, provider2);
    }

    public static BluetoothDevice proxyProvideBluetoothDevice(String str, RxBleAdapterWrapper rxBleAdapterWrapper) {
        return (BluetoothDevice) Preconditions.checkNotNull(DeviceModule.provideBluetoothDevice(str, rxBleAdapterWrapper), "Cannot return null from a non-@Nullable @Provides method");
    }
}
