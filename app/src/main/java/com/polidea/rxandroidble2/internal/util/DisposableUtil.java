package com.polidea.rxandroidble2.internal.util;

import io.reactivex.ObservableEmitter;
import io.reactivex.SingleEmitter;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
public class DisposableUtil {
    private DisposableUtil() {
    }

    public static <T> DisposableSingleObserver<T> disposableSingleObserverFromEmitter(final SingleEmitter<T> singleEmitter) {
        return new DisposableSingleObserver<T>() {
            @Override
            public void onSuccess(T t) {
                SingleEmitter.this.onSuccess(t);
            }

            @Override
            public void onError(Throwable th) {
                SingleEmitter.this.tryOnError(th);
            }
        };
    }

    public static <T> DisposableObserver<T> disposableObserverFromEmitter(final ObservableEmitter<T> observableEmitter) {
        return new DisposableObserver<T>() {
            @Override
            public void onNext(T t) {
                ObservableEmitter.this.onNext(t);
            }

            @Override
            public void onError(Throwable th) {
                ObservableEmitter.this.tryOnError(th);
            }

            @Override
            public void onComplete() {
                ObservableEmitter.this.onComplete();
            }
        };
    }

    public static <T> DisposableSingleObserver<T> disposableSingleObserverFromEmitter(final ObservableEmitter<T> observableEmitter) {
        return new DisposableSingleObserver<T>() {
            @Override
            public void onSuccess(T t) {
                ObservableEmitter.this.onNext(t);
                ObservableEmitter.this.onComplete();
            }

            @Override
            public void onError(Throwable th) {
                ObservableEmitter.this.tryOnError(th);
            }
        };
    }
}
