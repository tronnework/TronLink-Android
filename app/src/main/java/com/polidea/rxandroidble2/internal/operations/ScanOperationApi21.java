package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.scan.AndroidScanObjectsConverter;
import com.polidea.rxandroidble2.internal.scan.EmulatedScanFilterMatcher;
import com.polidea.rxandroidble2.internal.scan.InternalScanResultCreator;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.ObservableEmitter;
import java.util.Arrays;
import java.util.List;
public class ScanOperationApi21 extends ScanOperation<RxBleInternalScanResult, ScanCallback> {
    private final AndroidScanObjectsConverter androidScanObjectsConverter;
    final EmulatedScanFilterMatcher emulatedScanFilterMatcher;
    final InternalScanResultCreator internalScanResultCreator;
    private ObservableEmitter<RxBleInternalScanResult> scanEmitter;
    private final ScanFilter[] scanFilters;
    private final ScanSettings scanSettings;

    public ScanOperationApi21(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, AndroidScanObjectsConverter androidScanObjectsConverter, ScanSettings scanSettings, EmulatedScanFilterMatcher emulatedScanFilterMatcher, ScanFilter[] scanFilterArr) {
        super(rxBleAdapterWrapper);
        this.internalScanResultCreator = internalScanResultCreator;
        this.scanSettings = scanSettings;
        this.emulatedScanFilterMatcher = emulatedScanFilterMatcher;
        this.scanFilters = scanFilterArr;
        this.androidScanObjectsConverter = androidScanObjectsConverter;
        this.scanEmitter = null;
    }

    @Override
    public ScanCallback createScanCallback(ObservableEmitter<RxBleInternalScanResult> observableEmitter) {
        this.scanEmitter = observableEmitter;
        return new ScanCallback() {
            @Override
            public void onScanResult(int i, ScanResult scanResult) {
                ObservableEmitter observableEmitter2;
                if (!emulatedScanFilterMatcher.isEmpty() && RxBleLog.isAtLeast(3) && RxBleLog.getShouldLogScannedPeripherals()) {
                    ScanRecord scanRecord = scanResult.getScanRecord();
                    Object[] objArr = new Object[4];
                    objArr[0] = LoggerUtil.commonMacMessage(scanResult.getDevice().getAddress());
                    objArr[1] = scanResult.getDevice().getName();
                    objArr[2] = Integer.valueOf(scanResult.getRssi());
                    objArr[3] = LoggerUtil.bytesToHex(scanRecord != null ? scanRecord.getBytes() : null);
                    RxBleLog.d("%s, name=%s, rssi=%d, data=%s", objArr);
                }
                RxBleInternalScanResult create = internalScanResultCreator.create(i, scanResult);
                if (!emulatedScanFilterMatcher.matches(create) || (observableEmitter2 = scanEmitter) == null) {
                    return;
                }
                observableEmitter2.onNext(create);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> list) {
                ObservableEmitter observableEmitter2;
                for (ScanResult scanResult : list) {
                    RxBleInternalScanResult create = internalScanResultCreator.create(scanResult);
                    if (emulatedScanFilterMatcher.matches(create) && (observableEmitter2 = scanEmitter) != null) {
                        observableEmitter2.onNext(create);
                    }
                }
            }

            @Override
            public void onScanFailed(int i) {
                ObservableEmitter observableEmitter2 = scanEmitter;
                if (observableEmitter2 != null) {
                    observableEmitter2.tryOnError(new BleScanException(ScanOperationApi21.errorCodeToBleErrorCode(i)));
                }
            }
        };
    }

    @Override
    public boolean startScan(RxBleAdapterWrapper rxBleAdapterWrapper, ScanCallback scanCallback) {
        if (this.emulatedScanFilterMatcher.isEmpty()) {
            RxBleLog.d("No library side filtering —> debug logs of scanned devices disabled", new Object[0]);
        }
        rxBleAdapterWrapper.startLeScan(this.androidScanObjectsConverter.toNativeFilters(this.scanFilters), this.androidScanObjectsConverter.toNativeSettings(this.scanSettings), scanCallback);
        return true;
    }

    @Override
    public void stopScan(RxBleAdapterWrapper rxBleAdapterWrapper, ScanCallback scanCallback) {
        rxBleAdapterWrapper.stopLeScan(scanCallback);
        ObservableEmitter<RxBleInternalScanResult> observableEmitter = this.scanEmitter;
        if (observableEmitter != null) {
            observableEmitter.onComplete();
            this.scanEmitter = null;
        }
    }

    static int errorCodeToBleErrorCode(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            RxBleLog.w("Encountered unknown scanning error code: %d -> check android.bluetooth.le.ScanCallback", new Object[0]);
                            return Integer.MAX_VALUE;
                        }
                        return 9;
                    }
                    return 8;
                }
                return 7;
            }
            return 6;
        }
        return 5;
    }

    public String toString() {
        String str;
        ScanFilter[] scanFilterArr = this.scanFilters;
        boolean z = scanFilterArr == null || scanFilterArr.length == 0;
        boolean isEmpty = this.emulatedScanFilterMatcher.isEmpty();
        StringBuilder sb = new StringBuilder("ScanOperationApi21{");
        String str2 = "";
        if (z) {
            str = "";
        } else {
            str = "ANY_MUST_MATCH -> nativeFilters=" + Arrays.toString(this.scanFilters);
        }
        sb.append(str);
        sb.append((z || isEmpty) ? "" : " and then ");
        if (!isEmpty) {
            str2 = "ANY_MUST_MATCH -> " + this.emulatedScanFilterMatcher;
        }
        sb.append(str2);
        sb.append('}');
        return sb.toString();
    }
}
