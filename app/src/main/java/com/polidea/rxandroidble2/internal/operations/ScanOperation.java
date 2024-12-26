package com.polidea.rxandroidble2.internal.operations;

import android.os.DeadObjectException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.Cancellable;
public abstract class ScanOperation<SCAN_RESULT_TYPE, SCAN_CALLBACK_TYPE> extends QueueOperation<SCAN_RESULT_TYPE> {
    final RxBleAdapterWrapper rxBleAdapterWrapper;

    abstract SCAN_CALLBACK_TYPE createScanCallback(ObservableEmitter<SCAN_RESULT_TYPE> observableEmitter);

    abstract boolean startScan(RxBleAdapterWrapper rxBleAdapterWrapper, SCAN_CALLBACK_TYPE scan_callback_type);

    abstract void stopScan(RxBleAdapterWrapper rxBleAdapterWrapper, SCAN_CALLBACK_TYPE scan_callback_type);

    public ScanOperation(RxBleAdapterWrapper rxBleAdapterWrapper) {
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
    }

    @Override
    protected final void protectedRun(ObservableEmitter<SCAN_RESULT_TYPE> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        final SCAN_CALLBACK_TYPE createScanCallback = createScanCallback(observableEmitter);
        try {
            observableEmitter.setCancellable(new Cancellable() {
                @Override
                public void cancel() {
                    RxBleLog.i("Scan operation is requested to stop.", new Object[0]);
                    ScanOperation scanOperation = ScanOperation.this;
                    scanOperation.stopScan(scanOperation.rxBleAdapterWrapper, createScanCallback);
                }
            });
            RxBleLog.i("Scan operation is requested to start.", new Object[0]);
            if (!startScan(this.rxBleAdapterWrapper, createScanCallback)) {
                observableEmitter.tryOnError(new BleScanException(0));
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleScanException(1, deadObjectException);
    }
}
