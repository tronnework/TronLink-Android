package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResultLegacy;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import io.reactivex.ObservableEmitter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
public class LegacyScanOperation extends ScanOperation<RxBleInternalScanResultLegacy, BluetoothAdapter.LeScanCallback> {
    final Set<UUID> filterUuids;
    final ScanRecordParser scanRecordParser;

    public LegacyScanOperation(UUID[] uuidArr, RxBleAdapterWrapper rxBleAdapterWrapper, ScanRecordParser scanRecordParser) {
        super(rxBleAdapterWrapper);
        this.scanRecordParser = scanRecordParser;
        if (uuidArr == null || uuidArr.length <= 0) {
            this.filterUuids = null;
            return;
        }
        HashSet hashSet = new HashSet(uuidArr.length);
        this.filterUuids = hashSet;
        Collections.addAll(hashSet, uuidArr);
    }

    @Override
    public BluetoothAdapter.LeScanCallback createScanCallback(final ObservableEmitter<RxBleInternalScanResultLegacy> observableEmitter) {
        return new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                if (filterUuids != null && RxBleLog.isAtLeast(3)) {
                    RxBleLog.d("%s, name=%s, rssi=%d, data=%s", LoggerUtil.commonMacMessage(bluetoothDevice.getAddress()), bluetoothDevice.getName(), Integer.valueOf(i), LoggerUtil.bytesToHex(bArr));
                }
                if (filterUuids == null || scanRecordParser.extractUUIDs(bArr).containsAll(filterUuids)) {
                    observableEmitter.onNext(new RxBleInternalScanResultLegacy(bluetoothDevice, i, bArr));
                }
            }
        };
    }

    @Override
    public boolean startScan(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        if (this.filterUuids == null) {
            RxBleLog.d("No library side filtering —> debug logs of scanned devices disabled", new Object[0]);
        }
        return rxBleAdapterWrapper.startLegacyLeScan(leScanCallback);
    }

    @Override
    public void stopScan(RxBleAdapterWrapper rxBleAdapterWrapper, BluetoothAdapter.LeScanCallback leScanCallback) {
        rxBleAdapterWrapper.stopLegacyLeScan(leScanCallback);
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder("LegacyScanOperation{");
        if (this.filterUuids == null) {
            str = "";
        } else {
            str = "ALL_MUST_MATCH -> uuids=" + LoggerUtil.getUuidSetToLog(this.filterUuids);
        }
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }
}
