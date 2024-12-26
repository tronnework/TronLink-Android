package com.polidea.rxandroidble2;

import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
public class RxBleScanResult {
    private final RxBleDevice bleDevice;
    private final int rssi;
    private final byte[] scanRecord;

    public RxBleDevice getBleDevice() {
        return this.bleDevice;
    }

    public int getRssi() {
        return this.rssi;
    }

    public byte[] getScanRecord() {
        return this.scanRecord;
    }

    public RxBleScanResult(RxBleDevice rxBleDevice, int i, byte[] bArr) {
        this.bleDevice = rxBleDevice;
        this.rssi = i;
        this.scanRecord = bArr;
    }

    public String toString() {
        return "RxBleScanResult{bleDevice=" + this.bleDevice + ", rssi=" + this.rssi + ", scanRecord=" + LoggerUtil.bytesToHex(this.scanRecord) + '}';
    }
}
