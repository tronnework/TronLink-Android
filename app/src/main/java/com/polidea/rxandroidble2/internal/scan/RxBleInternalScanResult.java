package com.polidea.rxandroidble2.internal.scan;

import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble2.internal.ScanResultInterface;
import com.polidea.rxandroidble2.scan.ScanCallbackType;
import com.polidea.rxandroidble2.scan.ScanRecord;
public class RxBleInternalScanResult implements ScanResultInterface {
    private final BluetoothDevice bluetoothDevice;
    private final int rssi;
    private final ScanCallbackType scanCallbackType;
    private final ScanRecord scanRecord;
    private final long timestampNanos;

    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    @Override
    public int getRssi() {
        return this.rssi;
    }

    @Override
    public ScanCallbackType getScanCallbackType() {
        return this.scanCallbackType;
    }

    @Override
    public ScanRecord getScanRecord() {
        return this.scanRecord;
    }

    @Override
    public long getTimestampNanos() {
        return this.timestampNanos;
    }

    public RxBleInternalScanResult(BluetoothDevice bluetoothDevice, int i, long j, ScanRecord scanRecord, ScanCallbackType scanCallbackType) {
        this.bluetoothDevice = bluetoothDevice;
        this.rssi = i;
        this.timestampNanos = j;
        this.scanRecord = scanRecord;
        this.scanCallbackType = scanCallbackType;
    }

    @Override
    public String getAddress() {
        return this.bluetoothDevice.getAddress();
    }

    @Override
    public String getDeviceName() {
        BluetoothDevice bluetoothDevice = getBluetoothDevice();
        if (bluetoothDevice == null) {
            return null;
        }
        return bluetoothDevice.getName();
    }
}
