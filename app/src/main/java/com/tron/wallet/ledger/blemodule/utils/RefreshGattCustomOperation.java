package com.tron.wallet.ledger.blemodule.utils;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble2.RxBleCustomOperation;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
public class RefreshGattCustomOperation implements RxBleCustomOperation<Boolean> {
    @Override
    public Observable<Boolean> asObservable(final BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) throws Throwable {
        return Observable.ambArray(Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public java.lang.Boolean call() throws java.lang.Exception {
                


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.ledger.blemodule.utils.RefreshGattCustomOperation.1.call():java.lang.Boolean");
            }
        }).subscribeOn(scheduler).delay(1L, TimeUnit.SECONDS, scheduler), rxBleGattCallback.observeDisconnect());
    }
}
