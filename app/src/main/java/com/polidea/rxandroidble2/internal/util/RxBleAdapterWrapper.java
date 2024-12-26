package com.polidea.rxandroidble2.internal.util;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import java.util.List;
import java.util.Set;
public class RxBleAdapterWrapper {
    private static BleException nullBluetoothAdapter = new BleException("bluetoothAdapter is null");
    private final BluetoothAdapter bluetoothAdapter;

    public boolean hasBluetoothAdapter() {
        return this.bluetoothAdapter != null;
    }

    @Inject
    public RxBleAdapterWrapper(BluetoothAdapter bluetoothAdapter) {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public BluetoothDevice getRemoteDevice(String str) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        return bluetoothAdapter.getRemoteDevice(str);
    }

    public boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled();
    }

    public boolean startLegacyLeScan(BluetoothAdapter.LeScanCallback leScanCallback) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        return bluetoothAdapter.startLeScan(leScanCallback);
    }

    public void stopLegacyLeScan(BluetoothAdapter.LeScanCallback leScanCallback) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        bluetoothAdapter.stopLeScan(leScanCallback);
    }

    public void startLeScan(List<ScanFilter> list, ScanSettings scanSettings, ScanCallback scanCallback) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        bluetoothAdapter.getBluetoothLeScanner().startScan(list, scanSettings, scanCallback);
    }

    public int startLeScan(List<ScanFilter> list, ScanSettings scanSettings, PendingIntent pendingIntent) {
        int startScan;
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        startScan = bluetoothAdapter.getBluetoothLeScanner().startScan(list, scanSettings, pendingIntent);
        return startScan;
    }

    public void stopLeScan(PendingIntent pendingIntent) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        bluetoothAdapter.getBluetoothLeScanner().stopScan(pendingIntent);
    }

    public void stopLeScan(ScanCallback scanCallback) {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        if (!bluetoothAdapter.isEnabled()) {
            RxBleLog.v("BluetoothAdapter is disabled, calling BluetoothLeScanner.stopScan(ScanCallback) may cause IllegalStateException", new Object[0]);
            return;
        }
        BluetoothLeScanner bluetoothLeScanner = this.bluetoothAdapter.getBluetoothLeScanner();
        if (bluetoothLeScanner == null) {
            RxBleLog.w("Cannot call BluetoothLeScanner.stopScan(ScanCallback) on 'null' reference; BluetoothAdapter.isEnabled() == %b", Boolean.valueOf(this.bluetoothAdapter.isEnabled()));
        } else {
            bluetoothLeScanner.stopScan(scanCallback);
        }
    }

    public Set<BluetoothDevice> getBondedDevices() {
        BluetoothAdapter bluetoothAdapter = this.bluetoothAdapter;
        if (bluetoothAdapter == null) {
            throw nullBluetoothAdapter;
        }
        return bluetoothAdapter.getBondedDevices();
    }
}
