package com.polidea.rxandroidble2.internal;

import android.os.DeadObjectException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.internal.operations.Operation;
import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
public abstract class QueueOperation<T> implements Operation<T> {
    protected abstract void protectedRun(ObservableEmitter<T> observableEmitter, QueueReleaseInterface queueReleaseInterface) throws Throwable;

    protected abstract BleException provideException(DeadObjectException deadObjectException);

    @Override
    public int compareTo(Operation<?> operation) {
        return compareTo((Operation) operation);
    }

    @Override
    public final Observable<T> run(final QueueReleaseInterface queueReleaseInterface) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> observableEmitter) {
                try {
                    protectedRun(observableEmitter, queueReleaseInterface);
                } catch (DeadObjectException e) {
                    observableEmitter.tryOnError(provideException(e));
                    RxBleLog.e(e, "QueueOperation terminated with a DeadObjectException", new Object[0]);
                } catch (Throwable th) {
                    observableEmitter.tryOnError(th);
                    RxBleLog.e(th, "QueueOperation terminated with an unexpected exception", new Object[0]);
                }
            }
        });
    }

    @Override
    public Priority definedPriority() {
        return Priority.NORMAL;
    }

    public int compareTo(Operation operation) {
        return operation.definedPriority().priority - definedPriority().priority;
    }
}
