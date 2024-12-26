package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.DeadObjectException;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattCannotStartException;
import com.polidea.rxandroidble2.exceptions.BleGattCharacteristicException;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.QueueOperation;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.connection.PayloadSizeLimitProvider;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import com.polidea.rxandroidble2.internal.util.ByteAssociation;
import com.polidea.rxandroidble2.internal.util.DisposableUtil;
import com.polidea.rxandroidble2.internal.util.QueueReleasingEmitterWrapper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import java.nio.ByteBuffer;
import java.util.UUID;
public class CharacteristicLongWriteOperation extends QueueOperation<byte[]> {
    private final PayloadSizeLimitProvider batchSizeProvider;
    private final BluetoothGatt bluetoothGatt;
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final Scheduler bluetoothInteractionScheduler;
    final byte[] bytesToWrite;
    private final RxBleGattCallback rxBleGattCallback;
    private byte[] tempBatchArray;
    private final TimeoutConfiguration timeoutConfiguration;
    private final RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy;
    private final RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy;

    public interface IntSupplier {
        int get();
    }

    public CharacteristicLongWriteOperation(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, @Named("bluetooth_interaction") Scheduler scheduler, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic, PayloadSizeLimitProvider payloadSizeLimitProvider, RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, byte[] bArr) {
        this.bluetoothGatt = bluetoothGatt;
        this.rxBleGattCallback = rxBleGattCallback;
        this.bluetoothInteractionScheduler = scheduler;
        this.timeoutConfiguration = timeoutConfiguration;
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.batchSizeProvider = payloadSizeLimitProvider;
        this.writeOperationAckStrategy = writeOperationAckStrategy;
        this.writeOperationRetryStrategy = writeOperationRetryStrategy;
        this.bytesToWrite = bArr;
    }

    @Override
    protected void protectedRun(ObservableEmitter<byte[]> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        final int payloadSizeLimit = this.batchSizeProvider.getPayloadSizeLimit();
        if (payloadSizeLimit <= 0) {
            throw new IllegalArgumentException("batchSizeProvider value must be greater than zero (now: " + payloadSizeLimit + ")");
        }
        Observable error = Observable.error(new BleGattCallbackTimeoutException(this.bluetoothGatt, BleGattOperationType.CHARACTERISTIC_LONG_WRITE));
        final ByteBuffer wrap = ByteBuffer.wrap(this.bytesToWrite);
        final QueueReleasingEmitterWrapper queueReleasingEmitterWrapper = new QueueReleasingEmitterWrapper(observableEmitter, queueReleaseInterface);
        IntSupplier intSupplier = new IntSupplier() {
            @Override
            public int get() {
                return ((int) Math.ceil(wrap.position() / payloadSizeLimit)) - 1;
            }
        };
        writeBatchAndObserve(payloadSizeLimit, wrap, intSupplier).subscribeOn(this.bluetoothInteractionScheduler).filter(writeResponseForMatchingCharacteristic(this.bluetoothGattCharacteristic)).take(1L).timeout(this.timeoutConfiguration.timeout, this.timeoutConfiguration.timeoutTimeUnit, this.timeoutConfiguration.timeoutScheduler, error).repeatWhen(bufferIsNotEmptyAndOperationHasBeenAcknowledgedAndNotUnsubscribed(this.writeOperationAckStrategy, wrap, queueReleasingEmitterWrapper)).retryWhen(errorIsRetryableAndAccordingTo(this.writeOperationRetryStrategy, wrap, payloadSizeLimit, intSupplier)).subscribe(new Observer<ByteAssociation<UUID>>() {
            @Override
            public void onNext(ByteAssociation<UUID> byteAssociation) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onError(Throwable th) {
                queueReleasingEmitterWrapper.onError(th);
            }

            @Override
            public void onComplete() {
                queueReleasingEmitterWrapper.onNext(bytesToWrite);
                queueReleasingEmitterWrapper.onComplete();
            }
        });
    }

    @Override
    protected BleException provideException(DeadObjectException deadObjectException) {
        return new BleDisconnectedException(deadObjectException, this.bluetoothGatt.getDevice().getAddress(), -1);
    }

    private Observable<ByteAssociation<UUID>> writeBatchAndObserve(final int i, final ByteBuffer byteBuffer, final IntSupplier intSupplier) {
        final Observable<ByteAssociation<UUID>> onCharacteristicWrite = this.rxBleGattCallback.getOnCharacteristicWrite();
        return Observable.create(new ObservableOnSubscribe<ByteAssociation<UUID>>() {
            @Override
            public void subscribe(ObservableEmitter<ByteAssociation<UUID>> observableEmitter) {
                observableEmitter.setDisposable((DisposableObserver) onCharacteristicWrite.subscribeWith(DisposableUtil.disposableObserverFromEmitter(observableEmitter)));
                try {
                    writeData(getNextBatch(byteBuffer, i), intSupplier);
                } catch (Throwable th) {
                    observableEmitter.onError(th);
                }
            }
        });
    }

    byte[] getNextBatch(ByteBuffer byteBuffer, int i) {
        int min = Math.min(byteBuffer.remaining(), i);
        byte[] bArr = this.tempBatchArray;
        if (bArr == null || bArr.length != min) {
            this.tempBatchArray = new byte[min];
        }
        byteBuffer.get(this.tempBatchArray);
        return this.tempBatchArray;
    }

    void writeData(byte[] bArr, IntSupplier intSupplier) {
        if (RxBleLog.isAtLeast(3)) {
            RxBleLog.d("Writing batch #%04d: %s", Integer.valueOf(intSupplier.get()), LoggerUtil.bytesToHex(bArr));
        }
        this.bluetoothGattCharacteristic.setValue(bArr);
        if (!this.bluetoothGatt.writeCharacteristic(this.bluetoothGattCharacteristic)) {
            throw new BleGattCannotStartException(this.bluetoothGatt, BleGattOperationType.CHARACTERISTIC_LONG_WRITE);
        }
    }

    private static Predicate<ByteAssociation<UUID>> writeResponseForMatchingCharacteristic(final BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return new Predicate<ByteAssociation<UUID>>() {
            @Override
            public boolean test(ByteAssociation<UUID> byteAssociation) {
                return byteAssociation.first.equals(bluetoothGattCharacteristic.getUuid());
            }
        };
    }

    static Function<Observable<?>, ObservableSource<?>> bufferIsNotEmptyAndOperationHasBeenAcknowledgedAndNotUnsubscribed(final RxBleConnection.WriteOperationAckStrategy writeOperationAckStrategy, final ByteBuffer byteBuffer, final QueueReleasingEmitterWrapper<byte[]> queueReleasingEmitterWrapper) {
        return new Function<Observable<?>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<?> observable) {
                return observable.takeWhile(notUnsubscribed(QueueReleasingEmitterWrapper.this)).map(bufferIsNotEmpty(byteBuffer)).compose(writeOperationAckStrategy).takeWhile(new Predicate<Boolean>() {
                    @Override
                    public boolean test(Boolean bool) {
                        return bool.booleanValue();
                    }
                });
            }

            private Function<Object, Boolean> bufferIsNotEmpty(final ByteBuffer byteBuffer2) {
                return new Function<Object, Boolean>() {
                    @Override
                    public Boolean apply(Object obj) {
                        return Boolean.valueOf(byteBuffer2.hasRemaining());
                    }
                };
            }

            private Predicate<Object> notUnsubscribed(final QueueReleasingEmitterWrapper<byte[]> queueReleasingEmitterWrapper2) {
                return new Predicate<Object>() {
                    @Override
                    public boolean test(Object obj) {
                        return !queueReleasingEmitterWrapper2.isWrappedEmitterUnsubscribed();
                    }
                };
            }
        };
    }

    private static Function<Observable<Throwable>, ObservableSource<?>> errorIsRetryableAndAccordingTo(final RxBleConnection.WriteOperationRetryStrategy writeOperationRetryStrategy, final ByteBuffer byteBuffer, final int i, final IntSupplier intSupplier) {
        return new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Throwable> observable) {
                return observable.flatMap(toLongWriteFailureOrError()).doOnNext(repositionByteBufferForRetry()).compose(RxBleConnection.WriteOperationRetryStrategy.this);
            }

            private Function<Throwable, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>> toLongWriteFailureOrError() {
                return new Function<Throwable, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>>() {
                    @Override
                    public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> apply(Throwable th) {
                        if (!(th instanceof BleGattCharacteristicException) && !(th instanceof BleGattCannotStartException)) {
                            return Observable.error(th);
                        }
                        return Observable.just(new RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure(intSupplier.get(), (BleGattException) th));
                    }
                };
            }

            private Consumer<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> repositionByteBufferForRetry() {
                return new Consumer<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>() {
                    @Override
                    public void accept(RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure longWriteFailure) {
                        byteBuffer.position(longWriteFailure.getBatchIndex() * i);
                    }
                };
            }
        };
    }

    public String toString() {
        return "CharacteristicLongWriteOperation{" + LoggerUtil.commonMacMessage(this.bluetoothGatt) + ", characteristic=" + LoggerUtil.wrap(this.bluetoothGattCharacteristic, false) + ", maxBatchSize=" + this.batchSizeProvider.getPayloadSizeLimit() + '}';
    }
}
