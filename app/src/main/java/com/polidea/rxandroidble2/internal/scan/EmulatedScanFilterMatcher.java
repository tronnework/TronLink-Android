package com.polidea.rxandroidble2.internal.scan;

import java.util.Arrays;
public class EmulatedScanFilterMatcher {
    private final boolean isEmpty;
    private final ScanFilterInterface[] scanFilters;

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public EmulatedScanFilterMatcher(ScanFilterInterface... scanFilterInterfaceArr) {
        boolean z;
        this.scanFilters = scanFilterInterfaceArr;
        if (scanFilterInterfaceArr != null && scanFilterInterfaceArr.length != 0) {
            z = false;
            for (ScanFilterInterface scanFilterInterface : scanFilterInterfaceArr) {
                if (!scanFilterInterface.isAllFieldsEmpty()) {
                    break;
                }
            }
        }
        z = true;
        this.isEmpty = z;
    }

    public boolean matches(RxBleInternalScanResult rxBleInternalScanResult) {
        ScanFilterInterface[] scanFilterInterfaceArr = this.scanFilters;
        if (scanFilterInterfaceArr == null || scanFilterInterfaceArr.length == 0) {
            return true;
        }
        for (ScanFilterInterface scanFilterInterface : scanFilterInterfaceArr) {
            if (scanFilterInterface.matches(rxBleInternalScanResult)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "emulatedFilters=" + Arrays.toString(this.scanFilters);
    }
}
