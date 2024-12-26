package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizerExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
@ConnectionScope
public class BluetoothGattProvider {
    private final AtomicReference<BluetoothGatt> reference = new AtomicReference<>();

    public BluetoothGatt getBluetoothGatt() {
        return this.reference.get();
    }

    public void updateBluetoothGatt(BluetoothGatt bluetoothGatt) {
        ByteQuadsCanonicalizerExternalSyntheticBackportWithForwarding0.m(this.reference, null, bluetoothGatt);
    }
}
