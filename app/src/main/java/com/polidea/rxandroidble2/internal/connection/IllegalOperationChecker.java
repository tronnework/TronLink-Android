package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.BleIllegalOperationException;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
public class IllegalOperationChecker {
    final IllegalOperationHandler resultHandler;

    @Inject
    public IllegalOperationChecker(IllegalOperationHandler illegalOperationHandler) {
        this.resultHandler = illegalOperationHandler;
    }

    public Completable checkAnyPropertyMatches(final BluetoothGattCharacteristic bluetoothGattCharacteristic, final int i) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() {
                BleIllegalOperationException handleMismatchData;
                if ((bluetoothGattCharacteristic.getProperties() & i) == 0 && (handleMismatchData = resultHandler.handleMismatchData(bluetoothGattCharacteristic, i)) != null) {
                    throw handleMismatchData;
                }
            }
        });
    }
}
