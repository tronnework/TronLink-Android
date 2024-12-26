package com.polidea.rxandroidble2.internal.scan;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.BackgroundScanner;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;
import java.util.ArrayList;
import java.util.List;
public class BackgroundScannerImpl implements BackgroundScanner {
    private static final int NO_ERROR = 0;
    private final InternalScanResultCreator internalScanResultCreator;
    private final InternalToExternalScanResultConverter internalToExternalScanResultConverter;
    private final RxBleAdapterWrapper rxBleAdapterWrapper;
    private final AndroidScanObjectsConverter scanObjectsConverter;

    @Inject
    public BackgroundScannerImpl(RxBleAdapterWrapper rxBleAdapterWrapper, AndroidScanObjectsConverter androidScanObjectsConverter, InternalScanResultCreator internalScanResultCreator, InternalToExternalScanResultConverter internalToExternalScanResultConverter) {
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
        this.scanObjectsConverter = androidScanObjectsConverter;
        this.internalScanResultCreator = internalScanResultCreator;
        this.internalToExternalScanResultConverter = internalToExternalScanResultConverter;
    }

    @Override
    public void scanBleDeviceInBackground(PendingIntent pendingIntent, ScanSettings scanSettings, ScanFilter... scanFilterArr) {
        if (Build.VERSION.SDK_INT < 26) {
            RxBleLog.w("PendingIntent based scanning is available for Android O and higher only.", new Object[0]);
        } else if (!this.rxBleAdapterWrapper.isBluetoothEnabled()) {
            RxBleLog.w("PendingIntent based scanning is available only when Bluetooth is ON.", new Object[0]);
            throw new BleScanException(1);
        } else {
            RxBleLog.i("Requesting pending intent based scan.", new Object[0]);
            int startLeScan = this.rxBleAdapterWrapper.startLeScan(this.scanObjectsConverter.toNativeFilters(scanFilterArr), this.scanObjectsConverter.toNativeSettings(scanSettings), pendingIntent);
            if (startLeScan == 0) {
                return;
            }
            BleScanException bleScanException = new BleScanException(startLeScan);
            RxBleLog.w(bleScanException, "Failed to start scan", new Object[0]);
            throw bleScanException;
        }
    }

    @Override
    public void stopBackgroundBleScan(PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT < 26) {
            RxBleLog.w("PendingIntent based scanning is available for Android O and higher only.", new Object[0]);
        } else if (!this.rxBleAdapterWrapper.isBluetoothEnabled()) {
            RxBleLog.w("PendingIntent based scanning is available only when Bluetooth is ON.", new Object[0]);
        } else {
            RxBleLog.i("Stopping pending intent based scan.", new Object[0]);
            this.rxBleAdapterWrapper.stopLeScan(pendingIntent);
        }
    }

    @Override
    public List<ScanResult> onScanResultReceived(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.le.extra.CALLBACK_TYPE", -1);
        int intExtra2 = intent.getIntExtra("android.bluetooth.le.extra.ERROR_CODE", 0);
        List<android.bluetooth.le.ScanResult> extractScanResults = extractScanResults(intent);
        ArrayList arrayList = new ArrayList();
        if (intExtra2 == 0) {
            for (android.bluetooth.le.ScanResult scanResult : extractScanResults) {
                arrayList.add(convertScanResultToRxAndroidBLEModel(intExtra, scanResult));
            }
            return arrayList;
        }
        throw new BleScanException(intExtra2);
    }

    private static List<android.bluetooth.le.ScanResult> extractScanResults(Intent intent) {
        return (List) intent.getSerializableExtra("android.bluetooth.le.extra.LIST_SCAN_RESULT");
    }

    private ScanResult convertScanResultToRxAndroidBLEModel(int i, android.bluetooth.le.ScanResult scanResult) {
        return this.internalToExternalScanResultConverter.apply(this.internalScanResultCreator.create(i, scanResult));
    }
}
