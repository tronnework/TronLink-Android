package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.UUID;
public final class LongWriteOperationBuilderImpl implements RxBleConnection.LongWriteOperationBuilder {
    byte[] bytes;
    PayloadSizeLimitProvider maxBatchSizeProvider;
    final ConnectionOperationQueue operationQueue;
    final OperationsProvider operationsProvider;
    private final RxBleConnection rxBleConnection;
    RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy = new ImmediateSerializedBatchAckStrategy();
    RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy = new NoRetryStrategy();
    private Single<BluetoothGattCharacteristic> writtenCharacteristicObservable;

    @Override
    public RxBleConnection.LongWriteOperationBuilder setBytes(byte[] bArr) {
        this.bytes = bArr;
        return this;
    }

    @Override
    public RxBleConnection.LongWriteOperationBuilder setWriteOperationAckStrategy(RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy) {
        this.writeOperationAckStrategy = writeOperationAckStrategy;
        return this;
    }

    @Override
    public RxBleConnection.LongWriteOperationBuilder setWriteOperationRetryStrategy(RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy) {
        this.writeOperationRetryStrategy = writeOperationRetryStrategy;
        return this;
    }

    @Inject
    public LongWriteOperationBuilderImpl(ConnectionOperationQueue connectionOperationQueue, MtuBasedPayloadSizeLimit mtuBasedPayloadSizeLimit, RxBleConnection rxBleConnection, OperationsProvider operationsProvider) {
        this.operationQueue = connectionOperationQueue;
        this.maxBatchSizeProvider = mtuBasedPayloadSizeLimit;
        this.rxBleConnection = rxBleConnection;
        this.operationsProvider = operationsProvider;
    }

    @Override
    public RxBleConnection.LongWriteOperationBuilder setCharacteristicUuid(final UUID uuid) {
        this.writtenCharacteristicObservable = this.rxBleConnection.discoverServices().flatMap(new Function<RxBleDeviceServices, SingleSource<? extends BluetoothGattCharacteristic>>() {
            @Override
            public SingleSource<? extends BluetoothGattCharacteristic> apply(RxBleDeviceServices rxBleDeviceServices) throws Exception {
                return rxBleDeviceServices.getCharacteristic(uuid);
            }
        });
        return this;
    }

    @Override
    public RxBleConnection.LongWriteOperationBuilder setCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.writtenCharacteristicObservable = Single.just(bluetoothGattCharacteristic);
        return this;
    }

    @Override
    public RxBleConnection.LongWriteOperationBuilder setMaxBatchSize(int i) {
        this.maxBatchSizeProvider = new ConstantPayloadSizeLimit(i);
        return this;
    }

    @Override
    public Observable<byte[]> build() {
        Single<BluetoothGattCharacteristic> single = this.writtenCharacteristicObservable;
        if (single != null) {
            if (this.bytes == null) {
                throw new IllegalArgumentException("setBytes() needs to be called before build()");
            }
            return single.flatMapObservable(new Function<BluetoothGattCharacteristic, Observable<byte[]>>() {
                @Override
                public Observable<byte[]> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                    return operationQueue.queue(operationsProvider.provideLongWriteOperation(bluetoothGattCharacteristic, writeOperationAckStrategy, writeOperationRetryStrategy, maxBatchSizeProvider, bytes));
                }
            });
        }
        throw new IllegalArgumentException("setCharacteristicUuid() or setCharacteristic() needs to be called before build()");
    }
}
