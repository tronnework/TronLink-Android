package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
public interface RxBleDevice {
    Observable<RxBleConnection> establishConnection(boolean z);

    Observable<RxBleConnection> establishConnection(boolean z, Timeout timeout);

    BluetoothDevice getBluetoothDevice();

    RxBleConnection.RxBleConnectionState getConnectionState();

    String getMacAddress();

    String getName();

    Observable<RxBleConnection.RxBleConnectionState> observeConnectionStateChanges();
}
