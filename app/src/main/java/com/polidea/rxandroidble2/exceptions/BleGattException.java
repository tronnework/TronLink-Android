package com.polidea.rxandroidble2.exceptions;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.utils.GattStatusParser;
public class BleGattException extends BleException {
    public static final int UNKNOWN_STATUS = -1;
    private final BleGattOperationType bleGattOperationType;
    private final BluetoothGatt gatt;
    private final int status;

    public BleGattOperationType getBleGattOperationType() {
        return this.bleGattOperationType;
    }

    public int getStatus() {
        return this.status;
    }

    @Deprecated
    public BleGattException(int i, BleGattOperationType bleGattOperationType) {
        super(createMessage(null, i, bleGattOperationType));
        this.gatt = null;
        this.status = i;
        this.bleGattOperationType = bleGattOperationType;
    }

    public BleGattException(BluetoothGatt bluetoothGatt, int i, BleGattOperationType bleGattOperationType) {
        super(createMessage(bluetoothGatt, i, bleGattOperationType));
        this.gatt = bluetoothGatt;
        this.status = i;
        this.bleGattOperationType = bleGattOperationType;
    }

    public BleGattException(BluetoothGatt bluetoothGatt, BleGattOperationType bleGattOperationType) {
        this(bluetoothGatt, -1, bleGattOperationType);
    }

    public String getMacAddress() {
        return getMacAddress(this.gatt);
    }

    private static String getMacAddress(BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null || bluetoothGatt.getDevice() == null) {
            return null;
        }
        return bluetoothGatt.getDevice().getAddress();
    }

    private static String createMessage(BluetoothGatt bluetoothGatt, int i, BleGattOperationType bleGattOperationType) {
        if (i == -1) {
            return String.format("GATT exception from MAC address %s, with type %s", getMacAddress(bluetoothGatt), bleGattOperationType);
        }
        return String.format("GATT exception from %s, status %d (%s), type %s. (Look up status 0x%02x here %s)", LoggerUtil.commonMacMessage(bluetoothGatt), Integer.valueOf(i), GattStatusParser.getGattCallbackStatusDescription(i), bleGattOperationType, Integer.valueOf(i), "https://android.googlesource.com/platform/external/bluetooth/bluedroid/+/android-5.1.0_r1/stack/include/gatt_api.h");
    }
}
