package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothManager;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import io.reactivex.Emitter;
import io.reactivex.ObservableEmitter;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
public class DisconnectOperation extends QueueOperation<Void> {
    private final BluetoothGattProvider bluetoothGattProvider;
    private final Scheduler bluetoothInteractionScheduler;
    private final BluetoothManager bluetoothManager;
    private final ConnectionStateChangeListener connectionStateChangeListener;
    private final String macAddress;
    private final RxBleGattCallback rxBleGattCallback;
    private final TimeoutConfiguration timeoutConfiguration;

    @Inject
    public DisconnectOperation(RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, @Named("mac-address") String str, BluetoothManager bluetoothManager, @Named("bluetooth_interaction") Scheduler scheduler, @Named("disconnect-timeout") TimeoutConfiguration timeoutConfiguration, ConnectionStateChangeListener connectionStateChangeListener) {
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothGattProvider = bluetoothGattProvider;
        this.macAddress = str;
        this.bluetoothManager = bluetoothManager;
        this.bluetoothInteractionScheduler = scheduler;
        this.timeoutConfiguration = timeoutConfiguration;
        this.connectionStateChangeListener = connectionStateChangeListener;
    }

    @Override
    protected void protectedRun(final ObservableEmitter<Void> observableEmitter, final QueueReleaseInterface queueReleaseInterface) {
        this.connectionStateChangeListener.onConnectionStateChange(RxBleConnection.RxBleConnectionState.DISCONNECTING);
        BluetoothGatt bluetoothGatt = this.bluetoothGattProvider.getBluetoothGatt();
        if (bluetoothGatt == null) {
            RxBleLog.w("Disconnect operation has been executed but GATT instance was null - considering disconnected.", new Object[0]);
            considerGattDisconnected(observableEmitter, queueReleaseInterface);
            return;
        }
        disconnectIfRequired(bluetoothGatt).observeOn(this.bluetoothInteractionScheduler).subscribe(new SingleObserver<BluetoothGatt>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onSuccess(BluetoothGatt bluetoothGatt2) {
                bluetoothGatt2.close();
                considerGattDisconnected(observableEmitter, queueReleaseInterface);
            }

            @Override
            public void onError(Throwable th) {
                RxBleLog.w(th, "Disconnect operation has been executed but finished with an error - considering disconnected.", new Object[0]);
                considerGattDisconnected(observableEmitter, queueReleaseInterface);
            }
        });
    }

    private Single<BluetoothGatt> disconnectIfRequired(BluetoothGatt bluetoothGatt) {
        if (isDisconnected(bluetoothGatt)) {
            return Single.just(bluetoothGatt);
        }
        return disconnect(bluetoothGatt);
    }

    void considerGattDisconnected(Emitter<Void> emitter, QueueReleaseInterface queueReleaseInterface) {
        this.connectionStateChangeListener.onConnectionStateChange(RxBleConnection.RxBleConnectionState.DISCONNECTED);
        queueReleaseInterface.release();
        emitter.onComplete();
    }

    private boolean isDisconnected(BluetoothGatt bluetoothGatt) {
        return this.bluetoothManager.getConnectionState(bluetoothGatt.getDevice(), 7) == 0;
    }

    private Single<BluetoothGatt> disconnect(BluetoothGatt bluetoothGatt) {
        return new DisconnectGattObservable(bluetoothGatt, this.rxBleGattCallback, this.bluetoothInteractionScheduler).timeout(this.timeoutConfiguration.timeout, this.timeoutConfiguration.timeoutTimeUnit, this.timeoutConfiguration.timeoutScheduler, Single.just(bluetoothGatt));
    }

    public static class DisconnectGattObservable extends Single<BluetoothGatt> {
        final BluetoothGatt bluetoothGatt;
        private final Scheduler disconnectScheduler;
        private final RxBleGattCallback rxBleGattCallback;

        DisconnectGattObservable(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) {
            this.bluetoothGatt = bluetoothGatt;
            this.rxBleGattCallback = rxBleGattCallback;
            this.disconnectScheduler = scheduler;
        }

        @Override
        protected void subscribeActual(SingleObserver<? super BluetoothGatt> singleObserver) {
            this.rxBleGattCallback.getOnConnectionStateChange().filter(new Predicate<RxBleConnection.RxBleConnectionState>() {
                @Override
                public boolean test(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                    return rxBleConnectionState == RxBleConnection.RxBleConnectionState.DISCONNECTED;
                }
            }).firstOrError().map(new Function<RxBleConnection.RxBleConnectionState, BluetoothGatt>() {
                @Override
                public BluetoothGatt apply(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                    return DisconnectGattObservable.this.bluetoothGatt;
                }
            }).subscribe(singleObserver);
            this.disconnectScheduler.createWorker().schedule(new Runnable() {
                @Override
                public void run() {
                    DisconnectGattObservable.this.bluetoothGatt.disconnect();
                }
            });
        }
    }

    @Override
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.macAddress, -1);
    }

    public String toString() {
        return "DisconnectOperation{" + LoggerUtil.commonMacMessage(this.macAddress) + '}';
    }
}
