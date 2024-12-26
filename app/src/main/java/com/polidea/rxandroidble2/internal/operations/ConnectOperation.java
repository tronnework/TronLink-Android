package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.connection.BluetoothGattProvider;
import com.polidea.rxandroidble2.internal.connection.ConnectionStateChangeListener;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.BleConnectionCompat;
import com.polidea.rxandroidble2.internal.util.DisposableUtil;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Action;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.concurrent.Callable;
public class ConnectOperation extends QueueOperation<BluetoothGatt> {
    final boolean autoConnect;
    final BluetoothDevice bluetoothDevice;
    final BluetoothGattProvider bluetoothGattProvider;
    final TimeoutConfiguration connectTimeout;
    final BleConnectionCompat connectionCompat;
    final ConnectionStateChangeListener connectionStateChangedAction;
    final RxBleGattCallback rxBleGattCallback;

    @Inject
    public ConnectOperation(BluetoothDevice bluetoothDevice, BleConnectionCompat bleConnectionCompat, RxBleGattCallback rxBleGattCallback, BluetoothGattProvider bluetoothGattProvider, @Named("connect-timeout") TimeoutConfiguration timeoutConfiguration, @Named("autoConnect") boolean z, ConnectionStateChangeListener connectionStateChangeListener) {
        this.bluetoothDevice = bluetoothDevice;
        this.connectionCompat = bleConnectionCompat;
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothGattProvider = bluetoothGattProvider;
        this.connectTimeout = timeoutConfiguration;
        this.autoConnect = z;
        this.connectionStateChangedAction = connectionStateChangeListener;
    }

    @Override
    protected void protectedRun(ObservableEmitter<BluetoothGatt> observableEmitter, final QueueReleaseInterface queueReleaseInterface) {
        observableEmitter.setDisposable((DisposableSingleObserver) getConnectedBluetoothGatt().compose(wrapWithTimeoutWhenNotAutoconnecting()).doFinally(new Action() {
            @Override
            public void run() {
                queueReleaseInterface.release();
            }
        }).subscribeWith(DisposableUtil.disposableSingleObserverFromEmitter(observableEmitter)));
        if (this.autoConnect) {
            queueReleaseInterface.release();
        }
    }

    private SingleTransformer<BluetoothGatt, BluetoothGatt> wrapWithTimeoutWhenNotAutoconnecting() {
        return new SingleTransformer<BluetoothGatt, BluetoothGatt>() {
            @Override
            public SingleSource<BluetoothGatt> apply(Single<BluetoothGatt> single) {
                return autoConnect ? single : single.timeout(connectTimeout.timeout, connectTimeout.timeoutTimeUnit, connectTimeout.timeoutScheduler, prepareConnectionTimeoutError());
            }
        };
    }

    Single<BluetoothGatt> prepareConnectionTimeoutError() {
        return Single.fromCallable(new Callable<BluetoothGatt>() {
            @Override
            public BluetoothGatt call() {
                throw new BleGattCallbackTimeoutException(bluetoothGattProvider.getBluetoothGatt(), BleGattOperationType.CONNECTION_STATE);
            }
        });
    }

    private Single<BluetoothGatt> getConnectedBluetoothGatt() {
        return Single.create(new SingleOnSubscribe<BluetoothGatt>() {
            @Override
            public void subscribe(SingleEmitter<BluetoothGatt> singleEmitter) {
                singleEmitter.setDisposable((DisposableSingleObserver) getBluetoothGattAndChangeStatusToConnected().delaySubscription(rxBleGattCallback.getOnConnectionStateChange().filter(new Predicate<RxBleConnection.RxBleConnectionState>() {
                    @Override
                    public boolean test(RxBleConnection.RxBleConnectionState rxBleConnectionState) {
                        return rxBleConnectionState == RxBleConnection.RxBleConnectionState.CONNECTED;
                    }
                })).mergeWith(rxBleGattCallback.observeDisconnect().firstOrError()).firstOrError().subscribeWith(DisposableUtil.disposableSingleObserverFromEmitter(singleEmitter)));
                connectionStateChangedAction.onConnectionStateChange(RxBleConnection.RxBleConnectionState.CONNECTING);
                bluetoothGattProvider.updateBluetoothGatt(connectionCompat.connectGatt(bluetoothDevice, autoConnect, rxBleGattCallback.getBluetoothGattCallback()));
            }
        });
    }

    Single<BluetoothGatt> getBluetoothGattAndChangeStatusToConnected() {
        return Single.fromCallable(new Callable<BluetoothGatt>() {
            @Override
            public BluetoothGatt call() {
                connectionStateChangedAction.onConnectionStateChange(RxBleConnection.RxBleConnectionState.CONNECTED);
                return bluetoothGattProvider.getBluetoothGatt();
            }
        });
    }

    @Override
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.bluetoothDevice.getAddress(), -1);
    }

    public String toString() {
        return "ConnectOperation{" + LoggerUtil.commonMacMessage(this.bluetoothDevice.getAddress()) + ", autoConnect=" + this.autoConnect + '}';
    }
}
