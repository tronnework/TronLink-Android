package com.polidea.rxandroidble2.internal.serialization;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.operations.Operation;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposables;
import io.reactivex.functions.Action;
public class ClientOperationQueueImpl implements ClientOperationQueue {
    final OperationPriorityFifoBlockingQueue queue = new OperationPriorityFifoBlockingQueue();

    @Inject
    public ClientOperationQueueImpl(@Named("bluetooth_interaction") final Scheduler scheduler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        FIFORunnableEntry<?> take = queue.take();
                        Operation<?> operation = take.operation;
                        long currentTimeMillis = System.currentTimeMillis();
                        LoggerUtil.logOperationStarted(operation);
                        LoggerUtil.logOperationRunning(operation);
                        QueueSemaphore queueSemaphore = new QueueSemaphore();
                        take.run(queueSemaphore, scheduler);
                        queueSemaphore.awaitRelease();
                        LoggerUtil.logOperationFinished(operation, currentTimeMillis, System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        RxBleLog.e(e, "Error while processing client operation queue", new Object[0]);
                    }
                }
            }
        }).start();
    }

    @Override
    public <T> Observable<T> queue(final Operation<T> operation) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> observableEmitter) {
                final FIFORunnableEntry fIFORunnableEntry = new FIFORunnableEntry(operation, observableEmitter);
                observableEmitter.setDisposable(Disposables.fromAction(new Action() {
                    @Override
                    public void run() {
                        if (queue.remove(fIFORunnableEntry)) {
                            LoggerUtil.logOperationRemoved(operation);
                        }
                    }
                }));
                LoggerUtil.logOperationQueued(operation);
                queue.add(fIFORunnableEntry);
            }
        });
    }
}
