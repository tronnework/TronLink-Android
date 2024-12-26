package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;
import com.polidea.rxandroidble2.ConnectionParameters;
import com.polidea.rxandroidble2.HiddenBluetoothGattCallback;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleGattCharacteristicException;
import com.polidea.rxandroidble2.exceptions.BleGattDescriptorException;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.ByteAssociation;
import com.polidea.rxandroidble2.internal.util.CharacteristicChangedEvent;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@ConnectionScope
public class RxBleGattCallback {
    final BluetoothGattProvider bluetoothGattProvider;
    private final Scheduler callbackScheduler;
    final DisconnectionRouter disconnectionRouter;
    final NativeCallbackDispatcher nativeCallbackDispatcher;
    final PublishRelay<RxBleConnection.RxBleConnectionState> connectionStatePublishRelay = PublishRelay.create();
    final Output<RxBleDeviceServices> servicesDiscoveredOutput = new Output<>();
    final Output<ByteAssociation<UUID>> readCharacteristicOutput = new Output<>();
    final Output<ByteAssociation<UUID>> writeCharacteristicOutput = new Output<>();
    final Relay<CharacteristicChangedEvent> changedCharacteristicSerializedPublishRelay = PublishRelay.create().toSerialized();
    final Output<ByteAssociation<BluetoothGattDescriptor>> readDescriptorOutput = new Output<>();
    final Output<ByteAssociation<BluetoothGattDescriptor>> writeDescriptorOutput = new Output<>();
    final Output<Integer> readRssiOutput = new Output<>();
    final Output<Integer> changedMtuOutput = new Output<>();
    final Output<ConnectionParameters> updatedConnectionOutput = new Output<>();
    private final Function<BleGattException, Observable<?>> errorMapper = new Function<BleGattException, Observable<?>>() {
        @Override
        public Observable<?> apply(BleGattException bleGattException) {
            return Observable.error(bleGattException);
        }
    };
    private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        private boolean isDisconnectedOrDisconnecting(int i) {
            return i == 0 || i == 3;
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            LoggerUtil.logCallback("onConnectionStateChange", bluetoothGatt, i, i2);
            nativeCallbackDispatcher.notifyNativeConnectionStateCallback(bluetoothGatt, i, i2);
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            bluetoothGattProvider.updateBluetoothGatt(bluetoothGatt);
            if (isDisconnectedOrDisconnecting(i2)) {
                disconnectionRouter.onDisconnectedException(new BleDisconnectedException(bluetoothGatt.getDevice().getAddress(), i));
            } else if (i != 0) {
                disconnectionRouter.onGattConnectionStateException(new BleGattException(bluetoothGatt, i, BleGattOperationType.CONNECTION_STATE));
            }
            connectionStatePublishRelay.accept(RxBleGattCallback.mapConnectionStateToRxBleConnectionStatus(i2));
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            LoggerUtil.logCallback("onServicesDiscovered", bluetoothGatt, i);
            nativeCallbackDispatcher.notifyNativeServicesDiscoveredCallback(bluetoothGatt, i);
            super.onServicesDiscovered(bluetoothGatt, i);
            if (!servicesDiscoveredOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(servicesDiscoveredOutput, bluetoothGatt, i, BleGattOperationType.SERVICE_DISCOVERY)) {
                return;
            }
            servicesDiscoveredOutput.valueRelay.accept(new RxBleDeviceServices(bluetoothGatt.getServices()));
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LoggerUtil.logCallback("onCharacteristicRead", bluetoothGatt, i, bluetoothGattCharacteristic, true);
            nativeCallbackDispatcher.notifyNativeReadCallback(bluetoothGatt, bluetoothGattCharacteristic, i);
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (!readCharacteristicOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(readCharacteristicOutput, bluetoothGatt, bluetoothGattCharacteristic, i, BleGattOperationType.CHARACTERISTIC_READ)) {
                return;
            }
            readCharacteristicOutput.valueRelay.accept(new ByteAssociation<>(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue()));
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LoggerUtil.logCallback("onCharacteristicWrite", bluetoothGatt, i, bluetoothGattCharacteristic, false);
            nativeCallbackDispatcher.notifyNativeWriteCallback(bluetoothGatt, bluetoothGattCharacteristic, i);
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            if (!writeCharacteristicOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(writeCharacteristicOutput, bluetoothGatt, bluetoothGattCharacteristic, i, BleGattOperationType.CHARACTERISTIC_WRITE)) {
                return;
            }
            writeCharacteristicOutput.valueRelay.accept(new ByteAssociation<>(bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getValue()));
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            LoggerUtil.logCallback("onCharacteristicChanged", bluetoothGatt, bluetoothGattCharacteristic, true);
            nativeCallbackDispatcher.notifyNativeChangedCallback(bluetoothGatt, bluetoothGattCharacteristic);
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            if (changedCharacteristicSerializedPublishRelay.hasObservers()) {
                changedCharacteristicSerializedPublishRelay.accept(new CharacteristicChangedEvent(bluetoothGattCharacteristic.getUuid(), Integer.valueOf(bluetoothGattCharacteristic.getInstanceId()), bluetoothGattCharacteristic.getValue()));
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LoggerUtil.logCallback("onDescriptorRead", bluetoothGatt, i, bluetoothGattDescriptor, true);
            nativeCallbackDispatcher.notifyNativeDescriptorReadCallback(bluetoothGatt, bluetoothGattDescriptor, i);
            super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
            if (!readDescriptorOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(readDescriptorOutput, bluetoothGatt, bluetoothGattDescriptor, i, BleGattOperationType.DESCRIPTOR_READ)) {
                return;
            }
            readDescriptorOutput.valueRelay.accept(new ByteAssociation<>(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue()));
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LoggerUtil.logCallback("onDescriptorWrite", bluetoothGatt, i, bluetoothGattDescriptor, false);
            nativeCallbackDispatcher.notifyNativeDescriptorWriteCallback(bluetoothGatt, bluetoothGattDescriptor, i);
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (!writeDescriptorOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(writeDescriptorOutput, bluetoothGatt, bluetoothGattDescriptor, i, BleGattOperationType.DESCRIPTOR_WRITE)) {
                return;
            }
            writeDescriptorOutput.valueRelay.accept(new ByteAssociation<>(bluetoothGattDescriptor, bluetoothGattDescriptor.getValue()));
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
            LoggerUtil.logCallback("onReliableWriteCompleted", bluetoothGatt, i);
            nativeCallbackDispatcher.notifyNativeReliableWriteCallback(bluetoothGatt, i);
            super.onReliableWriteCompleted(bluetoothGatt, i);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            LoggerUtil.logCallback("onReadRemoteRssi", bluetoothGatt, i2, i);
            nativeCallbackDispatcher.notifyNativeReadRssiCallback(bluetoothGatt, i, i2);
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (!readRssiOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(readRssiOutput, bluetoothGatt, i2, BleGattOperationType.READ_RSSI)) {
                return;
            }
            readRssiOutput.valueRelay.accept(Integer.valueOf(i));
        }

        @Override
        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            LoggerUtil.logCallback("onMtuChanged", bluetoothGatt, i2, i);
            nativeCallbackDispatcher.notifyNativeMtuChangedCallback(bluetoothGatt, i, i2);
            super.onMtuChanged(bluetoothGatt, i, i2);
            if (!changedMtuOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(changedMtuOutput, bluetoothGatt, i2, BleGattOperationType.ON_MTU_CHANGED)) {
                return;
            }
            changedMtuOutput.valueRelay.accept(Integer.valueOf(i));
        }

        public void onConnectionUpdated(BluetoothGatt bluetoothGatt, int i, int i2, int i3, int i4) {
            LoggerUtil.logConnectionUpdateCallback("onConnectionUpdated", bluetoothGatt, i4, i, i2, i3);
            nativeCallbackDispatcher.notifyNativeParamsUpdateCallback(bluetoothGatt, i, i2, i3, i4);
            if (!updatedConnectionOutput.hasObservers() || RxBleGattCallback.propagateErrorIfOccurred(updatedConnectionOutput, bluetoothGatt, i4, BleGattOperationType.CONNECTION_PRIORITY_CHANGE)) {
                return;
            }
            updatedConnectionOutput.valueRelay.accept(new ConnectionParametersImpl(i, i2, i3));
        }
    };

    private static boolean isException(int i) {
        return i != 0;
    }

    public BluetoothGattCallback getBluetoothGattCallback() {
        return this.bluetoothGattCallback;
    }

    @Inject
    public RxBleGattCallback(@Named("bluetooth_callbacks") Scheduler scheduler, BluetoothGattProvider bluetoothGattProvider, DisconnectionRouter disconnectionRouter, NativeCallbackDispatcher nativeCallbackDispatcher) {
        this.callbackScheduler = scheduler;
        this.bluetoothGattProvider = bluetoothGattProvider;
        this.disconnectionRouter = disconnectionRouter;
        this.nativeCallbackDispatcher = nativeCallbackDispatcher;
    }

    static RxBleConnection.RxBleConnectionState mapConnectionStateToRxBleConnectionStatus(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return RxBleConnection.RxBleConnectionState.DISCONNECTING;
                }
                return RxBleConnection.RxBleConnectionState.DISCONNECTED;
            }
            return RxBleConnection.RxBleConnectionState.CONNECTED;
        }
        return RxBleConnection.RxBleConnectionState.CONNECTING;
    }

    static boolean propagateErrorIfOccurred(Output<?> output, BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i, BleGattOperationType bleGattOperationType) {
        return isException(i) && propagateStatusError(output, new BleGattCharacteristicException(bluetoothGatt, bluetoothGattCharacteristic, i, bleGattOperationType));
    }

    static boolean propagateErrorIfOccurred(Output<?> output, BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i, BleGattOperationType bleGattOperationType) {
        return isException(i) && propagateStatusError(output, new BleGattDescriptorException(bluetoothGatt, bluetoothGattDescriptor, i, bleGattOperationType));
    }

    static boolean propagateErrorIfOccurred(Output<?> output, BluetoothGatt bluetoothGatt, int i, BleGattOperationType bleGattOperationType) {
        return isException(i) && propagateStatusError(output, new BleGattException(bluetoothGatt, i, bleGattOperationType));
    }

    private static boolean propagateStatusError(Output<?> output, BleGattException bleGattException) {
        output.errorRelay.accept(bleGattException);
        return true;
    }

    private <T> Observable<T> withDisconnectionHandling(Output<T> output) {
        return Observable.merge(this.disconnectionRouter.asErrorOnlyObservable(), output.valueRelay, output.errorRelay.flatMap(this.errorMapper));
    }

    public <T> Observable<T> observeDisconnect() {
        return this.disconnectionRouter.asErrorOnlyObservable();
    }

    public Observable<RxBleConnection.RxBleConnectionState> getOnConnectionStateChange() {
        return this.connectionStatePublishRelay.delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<RxBleDeviceServices> getOnServicesDiscovered() {
        return withDisconnectionHandling(this.servicesDiscoveredOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<Integer> getOnMtuChanged() {
        return withDisconnectionHandling(this.changedMtuOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<ByteAssociation<UUID>> getOnCharacteristicRead() {
        return withDisconnectionHandling(this.readCharacteristicOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<ByteAssociation<UUID>> getOnCharacteristicWrite() {
        return withDisconnectionHandling(this.writeCharacteristicOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<CharacteristicChangedEvent> getOnCharacteristicChanged() {
        return Observable.merge(this.disconnectionRouter.asErrorOnlyObservable(), this.changedCharacteristicSerializedPublishRelay).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<ByteAssociation<BluetoothGattDescriptor>> getOnDescriptorRead() {
        return withDisconnectionHandling(this.readDescriptorOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<ByteAssociation<BluetoothGattDescriptor>> getOnDescriptorWrite() {
        return withDisconnectionHandling(this.writeDescriptorOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<Integer> getOnRssiRead() {
        return withDisconnectionHandling(this.readRssiOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public Observable<ConnectionParameters> getConnectionParametersUpdates() {
        return withDisconnectionHandling(this.updatedConnectionOutput).delay(0L, TimeUnit.SECONDS, this.callbackScheduler);
    }

    public void setNativeCallback(BluetoothGattCallback bluetoothGattCallback) {
        this.nativeCallbackDispatcher.setNativeCallback(bluetoothGattCallback);
    }

    public void setHiddenNativeCallback(HiddenBluetoothGattCallback hiddenBluetoothGattCallback) {
        this.nativeCallbackDispatcher.setNativeCallbackHidden(hiddenBluetoothGattCallback);
    }

    public static class Output<T> {
        final PublishRelay<T> valueRelay = PublishRelay.create();
        final PublishRelay<BleGattException> errorRelay = PublishRelay.create();

        Output() {
        }

        boolean hasObservers() {
            return this.valueRelay.hasObservers() || this.errorRelay.hasObservers();
        }
    }
}
