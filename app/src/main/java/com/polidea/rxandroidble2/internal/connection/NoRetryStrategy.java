package com.polidea.rxandroidble2.internal.connection;

import com.polidea.rxandroidble2.RxBleConnection;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
public class NoRetryStrategy implements RxBleConnection.WriteOperationRetryStrategy {
    @Override
    public ObservableSource<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> apply(Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> observable) {
        return observable.flatMap(new Function<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure, Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure>>() {
            @Override
            public Observable<RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure> apply(RxBleConnection.WriteOperationRetryStrategy.LongWriteFailure longWriteFailure) {
                return Observable.error(longWriteFailure.getCause());
            }
        });
    }
}
