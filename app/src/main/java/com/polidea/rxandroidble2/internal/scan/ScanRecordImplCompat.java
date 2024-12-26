package com.polidea.rxandroidble2.internal.scan;

import android.os.ParcelUuid;
import android.util.SparseArray;
import com.polidea.rxandroidble2.scan.ScanRecord;
import java.util.List;
import java.util.Map;
public class ScanRecordImplCompat implements ScanRecord {
    private final int advertiseFlags;
    private final byte[] bytes;
    private final String deviceName;
    private final SparseArray<byte[]> manufacturerSpecificData;
    private final Map<ParcelUuid, byte[]> serviceData;
    private final List<ParcelUuid> serviceSolicitationUuids;
    private final List<ParcelUuid> serviceUuids;
    private final int txPowerLevel;

    @Override
    public int getAdvertiseFlags() {
        return this.advertiseFlags;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public String getDeviceName() {
        return this.deviceName;
    }

    @Override
    public SparseArray<byte[]> getManufacturerSpecificData() {
        return this.manufacturerSpecificData;
    }

    @Override
    public Map<ParcelUuid, byte[]> getServiceData() {
        return this.serviceData;
    }

    @Override
    public List<ParcelUuid> getServiceSolicitationUuids() {
        return this.serviceSolicitationUuids;
    }

    @Override
    public List<ParcelUuid> getServiceUuids() {
        return this.serviceUuids;
    }

    @Override
    public int getTxPowerLevel() {
        return this.txPowerLevel;
    }

    public ScanRecordImplCompat(List<ParcelUuid> list, List<ParcelUuid> list2, SparseArray<byte[]> sparseArray, Map<ParcelUuid, byte[]> map, int i, int i2, String str, byte[] bArr) {
        this.serviceUuids = list;
        this.serviceSolicitationUuids = list2;
        this.manufacturerSpecificData = sparseArray;
        this.serviceData = map;
        this.deviceName = str;
        this.advertiseFlags = i;
        this.txPowerLevel = i2;
        this.bytes = bArr;
    }

    @Override
    public byte[] getManufacturerSpecificData(int i) {
        return this.manufacturerSpecificData.get(i);
    }

    @Override
    public byte[] getServiceData(ParcelUuid parcelUuid) {
        if (parcelUuid == null) {
            return null;
        }
        return this.serviceData.get(parcelUuid);
    }
}
