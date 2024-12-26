package com.polidea.rxandroidble2.internal.serialization;

import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.operations.Operation;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicLong;
class FIFORunnableEntry<T> implements Comparable<FIFORunnableEntry> {
    private static final AtomicLong SEQUENCE = new AtomicLong(0);
    final Operation<T> operation;
    final ObservableEmitter<T> operationResultObserver;
    private final long seqNum = SEQUENCE.getAndIncrement();

    public FIFORunnableEntry(Operation<T> operation, ObservableEmitter<T> observableEmitter) {
        this.operation = operation;
        this.operationResultObserver = observableEmitter;
    }

    @Override
    public int compareTo(FIFORunnableEntry fIFORunnableEntry) {
        int compareTo = this.operation.compareTo(fIFORunnableEntry.operation);
        return (compareTo != 0 || fIFORunnableEntry.operation == this.operation) ? compareTo : this.seqNum < fIFORunnableEntry.seqNum ? -1 : 1;
    }

    public void run(final QueueSemaphore queueSemaphore, final Scheduler scheduler) {
        if (this.operationResultObserver.isDisposed()) {
            LoggerUtil.logOperationSkippedBecauseDisposedWhenAboutToRun(this.operation);
            queueSemaphore.release();
            return;
        }
        scheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                operation.run(queueSemaphore).unsubscribeOn(scheduler).subscribe((Observer<T>) new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        operationResultObserver.setDisposable(disposable);
                    }

                    @Override
                    public void onNext(T t) {
                        operationResultObserver.onNext(t);
                    }

                    @Override
                    public void onError(Throwable th) {
                        operationResultObserver.tryOnError(th);
                    }

                    @Override
                    public void onComplete() {
                        operationResultObserver.onComplete();
                    }
                });
            }
        });
    }
}
