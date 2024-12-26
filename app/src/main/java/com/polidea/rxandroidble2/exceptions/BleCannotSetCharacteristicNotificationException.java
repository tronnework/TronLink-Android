package com.polidea.rxandroidble2.exceptions;

import android.bluetooth.BluetoothGattCharacteristic;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
public class BleCannotSetCharacteristicNotificationException extends BleException {
    public static final int CANNOT_FIND_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR = 2;
    public static final int CANNOT_SET_LOCAL_NOTIFICATION = 1;
    public static final int CANNOT_WRITE_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR = 3;
    @Deprecated
    public static final int UNKNOWN = -1;
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final int reason;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Reason {
    }

    private static String reasonDescription(int i) {
        return i != 1 ? i != 2 ? i != 3 ? "Unknown error" : "Cannot write client characteristic config descriptor" : "Cannot find client characteristic config descriptor" : "Cannot set local notification";
    }

    public BluetoothGattCharacteristic getBluetoothGattCharacteristic() {
        return this.bluetoothGattCharacteristic;
    }

    public int getReason() {
        return this.reason;
    }

    @Deprecated
    public BleCannotSetCharacteristicNotificationException(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super(createMessage(bluetoothGattCharacteristic, -1));
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.reason = -1;
    }

    public BleCannotSetCharacteristicNotificationException(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, Throwable th) {
        super(createMessage(bluetoothGattCharacteristic, i), th);
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.reason = i;
    }

    private static String createMessage(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return reasonDescription(i) + " (code " + i + ") with characteristic UUID " + bluetoothGattCharacteristic.getUuid();
    }
}
