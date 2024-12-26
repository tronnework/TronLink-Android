package com.polidea.rxandroidble2.scan;

import android.os.ParcelUuid;
import android.util.SparseArray;
import java.util.List;
import java.util.Map;
public interface ScanRecord {
    int getAdvertiseFlags();

    byte[] getBytes();

    String getDeviceName();

    SparseArray<byte[]> getManufacturerSpecificData();

    byte[] getManufacturerSpecificData(int i);

    Map<ParcelUuid, byte[]> getServiceData();

    byte[] getServiceData(ParcelUuid parcelUuid);

    List<ParcelUuid> getServiceSolicitationUuids();

    List<ParcelUuid> getServiceUuids();

    int getTxPowerLevel();
}
