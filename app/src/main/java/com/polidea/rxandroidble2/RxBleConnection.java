package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.internal.Priority;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
public interface RxBleConnection {
    public static final int GATT_MTU_MAXIMUM = 517;
    public static final int GATT_MTU_MINIMUM = 23;
    public static final int GATT_READ_MTU_OVERHEAD = 1;
    public static final int GATT_WRITE_MTU_OVERHEAD = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConnectionPriority {
    }

    @Deprecated
    public interface Connector {
        Single<RxBleConnection> prepareConnection(boolean z);
    }

    public interface LongWriteOperationBuilder {
        Observable<byte[]> build();

        LongWriteOperationBuilder setBytes(byte[] bArr);

        LongWriteOperationBuilder setCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

        LongWriteOperationBuilder setCharacteristicUuid(UUID uuid);

        LongWriteOperationBuilder setMaxBatchSize(int i);

        LongWriteOperationBuilder setWriteOperationAckStrategy(WriteOperationAckStrategy writeOperationAckStrategy);

        LongWriteOperationBuilder setWriteOperationRetryStrategy(WriteOperationRetryStrategy writeOperationRetryStrategy);
    }

    public interface WriteOperationAckStrategy extends ObservableTransformer<Boolean, Boolean> {
    }

    LongWriteOperationBuilder createNewLongWriteBuilder();

    Single<RxBleDeviceServices> discoverServices();

    Single<RxBleDeviceServices> discoverServices(long j, TimeUnit timeUnit);

    @Deprecated
    Single<BluetoothGattCharacteristic> getCharacteristic(UUID uuid);

    int getMtu();

    Observable<ConnectionParameters> observeConnectionParametersUpdates();

    <T> Observable<T> queue(RxBleCustomOperation<T> rxBleCustomOperation);

    <T> Observable<T> queue(RxBleCustomOperation<T> rxBleCustomOperation, Priority priority);

    Single<byte[]> readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Single<byte[]> readCharacteristic(UUID uuid);

    Single<byte[]> readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor);

    Single<byte[]> readDescriptor(UUID uuid, UUID uuid2, UUID uuid3);

    Single<Integer> readRssi();

    Completable requestConnectionPriority(int i, long j, TimeUnit timeUnit);

    Single<Integer> requestMtu(int i);

    Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupIndication(UUID uuid);

    Observable<Observable<byte[]>> setupIndication(UUID uuid, NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode);

    Observable<Observable<byte[]>> setupNotification(UUID uuid);

    Observable<Observable<byte[]>> setupNotification(UUID uuid, NotificationSetupMode notificationSetupMode);

    Single<byte[]> writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr);

    Single<byte[]> writeCharacteristic(UUID uuid, byte[] bArr);

    Completable writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr);

    Completable writeDescriptor(UUID uuid, UUID uuid2, UUID uuid3, byte[] bArr);

    public enum RxBleConnectionState {
        CONNECTING("CONNECTING"),
        CONNECTED("CONNECTED"),
        DISCONNECTED("DISCONNECTED"),
        DISCONNECTING("DISCONNECTING");
        
        private final String description;

        RxBleConnectionState(String str) {
            this.description = str;
        }

        @Override
        public String toString() {
            return "RxBleConnectionState{" + this.description + '}';
        }
    }

    public interface WriteOperationRetryStrategy extends ObservableTransformer<LongWriteFailure, LongWriteFailure> {

        public static class LongWriteFailure {
            final int batchIndex;
            final BleGattException cause;

            public int getBatchIndex() {
                return this.batchIndex;
            }

            public BleGattException getCause() {
                return this.cause;
            }

            public LongWriteFailure(int i, BleGattException bleGattException) {
                this.batchIndex = i;
                this.cause = bleGattException;
            }
        }
    }
}
