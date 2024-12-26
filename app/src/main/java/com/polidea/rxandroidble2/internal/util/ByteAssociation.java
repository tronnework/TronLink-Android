package com.polidea.rxandroidble2.internal.util;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import java.util.Arrays;
import java.util.UUID;
public class ByteAssociation<T> {
    public final T first;
    public final byte[] second;

    public ByteAssociation(T t, byte[] bArr) {
        this.first = t;
        this.second = bArr;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ByteAssociation) {
            ByteAssociation byteAssociation = (ByteAssociation) obj;
            return Arrays.equals(byteAssociation.second, this.second) && byteAssociation.first.equals(this.first);
        }
        return false;
    }

    public int hashCode() {
        return this.first.hashCode() ^ Arrays.hashCode(this.second);
    }

    public String toString() {
        String simpleName;
        T t = this.first;
        if (t instanceof BluetoothGattCharacteristic) {
            simpleName = "BluetoothGattCharacteristic(" + ((BluetoothGattCharacteristic) this.first).getUuid().toString() + ")";
        } else if (t instanceof BluetoothGattDescriptor) {
            simpleName = "BluetoothGattDescriptor(" + ((BluetoothGattDescriptor) this.first).getUuid().toString() + ")";
        } else if (t instanceof UUID) {
            simpleName = "UUID(" + this.first.toString() + ")";
        } else {
            simpleName = t.getClass().getSimpleName();
        }
        return getClass().getSimpleName() + "[first=" + simpleName + ", second=" + Arrays.toString(this.second) + "]";
    }

    public static <T> ByteAssociation<T> create(T t, byte[] bArr) {
        return new ByteAssociation<>(t, bArr);
    }
}
