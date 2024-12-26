package com.polidea.rxandroidble2.internal.scan;

import android.os.Build;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import com.polidea.rxandroidble2.scan.ScanRecord;
import java.util.List;
import java.util.Map;
public class ScanRecordImplNativeWrapper implements ScanRecord {
    private final android.bluetooth.le.ScanRecord nativeScanRecord;
    private final ScanRecordParser scanRecordParser;

    public ScanRecordImplNativeWrapper(android.bluetooth.le.ScanRecord scanRecord, ScanRecordParser scanRecordParser) {
        this.nativeScanRecord = scanRecord;
        this.scanRecordParser = scanRecordParser;
    }

    @Override
    public int getAdvertiseFlags() {
        return this.nativeScanRecord.getAdvertiseFlags();
    }

    @Override
    public List<ParcelUuid> getServiceUuids() {
        return this.nativeScanRecord.getServiceUuids();
    }

    @Override
    public List<ParcelUuid> getServiceSolicitationUuids() {
        List<ParcelUuid> serviceSolicitationUuids;
        if (Build.VERSION.SDK_INT >= 29) {
            serviceSolicitationUuids = this.nativeScanRecord.getServiceSolicitationUuids();
            return serviceSolicitationUuids;
        }
        return this.scanRecordParser.parseFromBytes(this.nativeScanRecord.getBytes()).getServiceSolicitationUuids();
    }

    @Override
    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.nativeScanRecord.getManufacturerSpecificData();
    }

    @Override
    public byte[] getManufacturerSpecificData(int i) {
        return this.nativeScanRecord.getManufacturerSpecificData(i);
    }

    @Override
    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.nativeScanRecord.getServiceData();
    }

    @Override
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        return this.nativeScanRecord.getServiceData(parcelUuid);
    }

    @Override
    public int getTxPowerLevel() {
        return this.nativeScanRecord.getTxPowerLevel();
    }

    @Override
    public String getDeviceName() {
        return this.nativeScanRecord.getDeviceName();
    }

    @Override
    public byte[] getBytes() {
        return this.nativeScanRecord.getBytes();
    }
}
