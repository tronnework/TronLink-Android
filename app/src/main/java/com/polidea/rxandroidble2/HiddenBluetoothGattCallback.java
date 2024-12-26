package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothGatt;
public interface HiddenBluetoothGattCallback {
    void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4);
}
