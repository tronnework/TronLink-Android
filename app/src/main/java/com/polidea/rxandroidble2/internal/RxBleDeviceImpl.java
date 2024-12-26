package com.polidea.rxandroidble2.internal;

import android.bluetooth.BluetoothDevice;
import bleshadow.javax.inject.Inject;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.ConnectionSetup;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.exceptions.BleAlreadyConnectedException;
import com.polidea.rxandroidble2.internal.connection.Connector;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Action;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
@DeviceScope
public class RxBleDeviceImpl implements RxBleDevice {
    final BluetoothDevice bluetoothDevice;
    private final BehaviorRelay<RxBleConnection.RxBleConnectionState> connectionStateRelay;
    final Connector connector;
    final AtomicBoolean isConnected = new AtomicBoolean(false);

    @Override
    public BluetoothDevice getBluetoothDevice() {
        return this.bluetoothDevice;
    }

    @Inject
    public RxBleDeviceImpl(BluetoothDevice bluetoothDevice, Connector connector, BehaviorRelay<RxBleConnection.RxBleConnectionState> behaviorRelay) {
        this.bluetoothDevice = bluetoothDevice;
        this.connector = connector;
        this.connectionStateRelay = behaviorRelay;
    }

    @Override
    public Observable<RxBleConnection.RxBleConnectionState> observeConnectionStateChanges() {
        return this.connectionStateRelay.distinctUntilChanged().skip(1L);
    }

    @Override
    public RxBleConnection.RxBleConnectionState getConnectionState() {
        return this.connectionStateRelay.getValue();
    }

    @Override
    public Observable<RxBleConnection> establishConnection(boolean z) {
        return establishConnection(new ConnectionSetup.Builder().setAutoConnect(z).setSuppressIllegalOperationCheck(true).build());
    }

    @Override
    public Observable<RxBleConnection> establishConnection(boolean z, Timeout timeout) {
        return establishConnection(new ConnectionSetup.Builder().setAutoConnect(z).setOperationTimeout(timeout).setSuppressIllegalOperationCheck(true).build());
    }

    public Observable<RxBleConnection> establishConnection(final ConnectionSetup connectionSetup) {
        return Observable.defer(new Callable<ObservableSource<RxBleConnection>>() {
            @Override
            public ObservableSource<RxBleConnection> call() {
                if (isConnected.compareAndSet(false, true)) {
                    return connector.prepareConnection(connectionSetup).doFinally(new Action() {
                        @Override
                        public void run() {
                            isConnected.set(false);
                        }
                    });
                }
                return Observable.error(new BleAlreadyConnectedException(bluetoothDevice.getAddress()));
            }
        });
    }

    @Override
    public String getName() {
        return this.bluetoothDevice.getName();
    }

    @Override
    public String getMacAddress() {
        return this.bluetoothDevice.getAddress();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RxBleDeviceImpl) {
            return this.bluetoothDevice.equals(((RxBleDeviceImpl) obj).bluetoothDevice);
        }
        return false;
    }

    public int hashCode() {
        return this.bluetoothDevice.hashCode();
    }

    public String toString() {
        return "RxBleDeviceImpl{" + LoggerUtil.commonMacMessage(this.bluetoothDevice.getAddress()) + ", name=" + this.bluetoothDevice.getName() + '}';
    }
}
