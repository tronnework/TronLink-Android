package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ConnectionParameters;
import com.polidea.rxandroidble2.NotificationSetupMode;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleCustomOperation;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.Priority;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.ByteAssociation;
import com.polidea.rxandroidble2.internal.util.QueueReleasingEmitterWrapper;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@ConnectionScope
public class RxBleConnectionImpl implements RxBleConnection {
    final BluetoothGatt bluetoothGatt;
    final Scheduler callbackScheduler;
    private final DescriptorWriter descriptorWriter;
    final RxBleGattCallback gattCallback;
    private final IllegalOperationChecker illegalOperationChecker;
    private final Provider<RxBleConnection.LongWriteOperationBuilder> longWriteOperationBuilderProvider;
    private final MtuProvider mtuProvider;
    private final NotificationAndIndicationManager notificationIndicationManager;
    private final ConnectionOperationQueue operationQueue;
    private final OperationsProvider operationsProvider;
    private final ServiceDiscoveryManager serviceDiscoveryManager;

    @Inject
    public RxBleConnectionImpl(ConnectionOperationQueue connectionOperationQueue, RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, ServiceDiscoveryManager serviceDiscoveryManager, NotificationAndIndicationManager notificationAndIndicationManager, MtuProvider mtuProvider, DescriptorWriter descriptorWriter, OperationsProvider operationsProvider, Provider<RxBleConnection.LongWriteOperationBuilder> provider, @Named("bluetooth_interaction") Scheduler scheduler, IllegalOperationChecker illegalOperationChecker) {
        this.operationQueue = connectionOperationQueue;
        this.gattCallback = rxBleGattCallback;
        this.bluetoothGatt = bluetoothGatt;
        this.serviceDiscoveryManager = serviceDiscoveryManager;
        this.notificationIndicationManager = notificationAndIndicationManager;
        this.mtuProvider = mtuProvider;
        this.descriptorWriter = descriptorWriter;
        this.operationsProvider = operationsProvider;
        this.longWriteOperationBuilderProvider = provider;
        this.callbackScheduler = scheduler;
        this.illegalOperationChecker = illegalOperationChecker;
    }

    @Override
    public RxBleConnection.LongWriteOperationBuilder createNewLongWriteBuilder() {
        return this.longWriteOperationBuilderProvider.get();
    }

    @Override
    public Completable requestConnectionPriority(int i, long j, TimeUnit timeUnit) {
        if (i == 2 || i == 0 || i == 1) {
            if (j <= 0) {
                return Completable.error(new IllegalArgumentException("Delay must be bigger than 0"));
            }
            return this.operationQueue.queue(this.operationsProvider.provideConnectionPriorityChangeOperation(i, j, timeUnit)).ignoreElements();
        }
        return Completable.error(new IllegalArgumentException("Connection priority must have valid value from BluetoothGatt (received " + i + ")"));
    }

    @Override
    public Single<Integer> requestMtu(int i) {
        return this.operationQueue.queue(this.operationsProvider.provideMtuChangeOperation(i)).firstOrError();
    }

    @Override
    public int getMtu() {
        return this.mtuProvider.getMtu();
    }

    @Override
    public Single<RxBleDeviceServices> discoverServices() {
        return this.serviceDiscoveryManager.getDiscoverServicesSingle(20L, TimeUnit.SECONDS);
    }

    @Override
    public Single<RxBleDeviceServices> discoverServices(long j, TimeUnit timeUnit) {
        return this.serviceDiscoveryManager.getDiscoverServicesSingle(j, timeUnit);
    }

    @Override
    @Deprecated
    public Single<BluetoothGattCharacteristic> getCharacteristic(final UUID uuid) {
        return discoverServices().flatMap(new Function<RxBleDeviceServices, Single<? extends BluetoothGattCharacteristic>>() {
            @Override
            public Single<? extends BluetoothGattCharacteristic> apply(RxBleDeviceServices rxBleDeviceServices) {
                return rxBleDeviceServices.getCharacteristic(uuid);
            }
        });
    }

    @Override
    public Observable<Observable<byte[]>> setupNotification(UUID uuid) {
        return setupNotification(uuid, NotificationSetupMode.DEFAULT);
    }

    @Override
    public Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setupNotification(bluetoothGattCharacteristic, NotificationSetupMode.DEFAULT);
    }

    @Override
    public Observable<Observable<byte[]>> setupNotification(UUID uuid, final NotificationSetupMode notificationSetupMode) {
        return getCharacteristic(uuid).flatMapObservable(new Function<BluetoothGattCharacteristic, ObservableSource<? extends Observable<byte[]>>>() {
            @Override
            public Observable<? extends Observable<byte[]>> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return setupNotification(bluetoothGattCharacteristic, notificationSetupMode);
            }
        });
    }

    @Override
    public Observable<Observable<byte[]>> setupNotification(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 16).andThen(this.notificationIndicationManager.setupServerInitiatedCharacteristicRead(bluetoothGattCharacteristic, notificationSetupMode, false));
    }

    @Override
    public Observable<Observable<byte[]>> setupIndication(UUID uuid) {
        return setupIndication(uuid, NotificationSetupMode.DEFAULT);
    }

    @Override
    public Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return setupIndication(bluetoothGattCharacteristic, NotificationSetupMode.DEFAULT);
    }

    @Override
    public Observable<Observable<byte[]>> setupIndication(UUID uuid, final NotificationSetupMode notificationSetupMode) {
        return getCharacteristic(uuid).flatMapObservable(new Function<BluetoothGattCharacteristic, ObservableSource<? extends Observable<byte[]>>>() {
            @Override
            public Observable<? extends Observable<byte[]>> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return setupIndication(bluetoothGattCharacteristic, notificationSetupMode);
            }
        });
    }

    @Override
    public Observable<Observable<byte[]>> setupIndication(BluetoothGattCharacteristic bluetoothGattCharacteristic, NotificationSetupMode notificationSetupMode) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 32).andThen(this.notificationIndicationManager.setupServerInitiatedCharacteristicRead(bluetoothGattCharacteristic, notificationSetupMode, true));
    }

    @Override
    public Single<byte[]> readCharacteristic(UUID uuid) {
        return getCharacteristic(uuid).flatMap(new Function<BluetoothGattCharacteristic, SingleSource<? extends byte[]>>() {
            @Override
            public SingleSource<? extends byte[]> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return readCharacteristic(bluetoothGattCharacteristic);
            }
        });
    }

    @Override
    public Single<byte[]> readCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 2).andThen(this.operationQueue.queue(this.operationsProvider.provideReadCharacteristic(bluetoothGattCharacteristic))).firstOrError();
    }

    @Override
    public Single<byte[]> writeCharacteristic(UUID uuid, final byte[] bArr) {
        return getCharacteristic(uuid).flatMap(new Function<BluetoothGattCharacteristic, SingleSource<? extends byte[]>>() {
            @Override
            public SingleSource<? extends byte[]> apply(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
                return writeCharacteristic(bluetoothGattCharacteristic, bArr);
            }
        });
    }

    @Override
    public Single<byte[]> writeCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        return this.illegalOperationChecker.checkAnyPropertyMatches(bluetoothGattCharacteristic, 76).andThen(this.operationQueue.queue(this.operationsProvider.provideWriteCharacteristic(bluetoothGattCharacteristic, bArr))).firstOrError();
    }

    @Override
    public Single<byte[]> readDescriptor(final UUID uuid, final UUID uuid2, final UUID uuid3) {
        return discoverServices().flatMap(new Function<RxBleDeviceServices, SingleSource<BluetoothGattDescriptor>>() {
            @Override
            public SingleSource<BluetoothGattDescriptor> apply(RxBleDeviceServices rxBleDeviceServices) {
                return rxBleDeviceServices.getDescriptor(uuid, uuid2, uuid3);
            }
        }).flatMap(new Function<BluetoothGattDescriptor, SingleSource<byte[]>>() {
            @Override
            public SingleSource<byte[]> apply(BluetoothGattDescriptor bluetoothGattDescriptor) {
                return readDescriptor(bluetoothGattDescriptor);
            }
        });
    }

    @Override
    public Single<byte[]> readDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor) {
        return this.operationQueue.queue(this.operationsProvider.provideReadDescriptor(bluetoothGattDescriptor)).firstOrError().map(new Function<ByteAssociation<BluetoothGattDescriptor>, byte[]>() {
            @Override
            public byte[] apply(ByteAssociation<BluetoothGattDescriptor> byteAssociation) {
                return byteAssociation.second;
            }
        });
    }

    @Override
    public Completable writeDescriptor(final UUID uuid, final UUID uuid2, final UUID uuid3, final byte[] bArr) {
        return discoverServices().flatMap(new Function<RxBleDeviceServices, SingleSource<BluetoothGattDescriptor>>() {
            @Override
            public SingleSource<BluetoothGattDescriptor> apply(RxBleDeviceServices rxBleDeviceServices) {
                return rxBleDeviceServices.getDescriptor(uuid, uuid2, uuid3);
            }
        }).flatMapCompletable(new Function<BluetoothGattDescriptor, CompletableSource>() {
            @Override
            public CompletableSource apply(BluetoothGattDescriptor bluetoothGattDescriptor) {
                return writeDescriptor(bluetoothGattDescriptor, bArr);
            }
        });
    }

    @Override
    public Completable writeDescriptor(BluetoothGattDescriptor bluetoothGattDescriptor, byte[] bArr) {
        return this.descriptorWriter.writeDescriptor(bluetoothGattDescriptor, bArr);
    }

    @Override
    public Single<Integer> readRssi() {
        return this.operationQueue.queue(this.operationsProvider.provideRssiReadOperation()).firstOrError();
    }

    @Override
    public Observable<ConnectionParameters> observeConnectionParametersUpdates() {
        return this.gattCallback.getConnectionParametersUpdates();
    }

    @Override
    public <T> Observable<T> queue(RxBleCustomOperation<T> rxBleCustomOperation) {
        return queue(rxBleCustomOperation, Priority.NORMAL);
    }

    @Override
    public <T> Observable<T> queue(final RxBleCustomOperation<T> rxBleCustomOperation, final Priority priority) {
        return this.operationQueue.queue(new QueueOperation<T>() {
            @Override
            public Priority definedPriority() {
                return priority;
            }

            @Override
            protected void protectedRun(ObservableEmitter<T> observableEmitter, QueueReleaseInterface queueReleaseInterface) throws Throwable {
                try {
                    Observable asObservable = rxBleCustomOperation.asObservable(bluetoothGatt, gattCallback, callbackScheduler);
                    if (asObservable == null) {
                        queueReleaseInterface.release();
                        throw new IllegalArgumentException("The custom operation asObservable method must return a non-null observable");
                    }
                    asObservable.doOnTerminate(clearNativeCallbackReferenceAction()).subscribe(new QueueReleasingEmitterWrapper(observableEmitter, queueReleaseInterface));
                } catch (Throwable th) {
                    queueReleaseInterface.release();
                    throw th;
                }
            }

            private Action clearNativeCallbackReferenceAction() {
                return new Action() {
                    @Override
                    public void run() {
                        gattCallback.setNativeCallback(null);
                        gattCallback.setHiddenNativeCallback(null);
                    }
                };
            }

            @Override
            protected BleException provideException(DeadObjectException deadObjectException) {
                return new BleDisconnectedException(deadObjectException, bluetoothGatt.getDevice().getAddress(), -1);
            }
        });
    }
}
