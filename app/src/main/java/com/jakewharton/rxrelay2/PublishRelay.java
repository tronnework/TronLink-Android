package com.jakewharton.rxrelay2;

import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizerExternalSyntheticBackportWithForwarding0;
import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
public final class PublishRelay<T> extends Relay<T> {
    static final PublishDisposable[] EMPTY = new PublishDisposable[0];
    final AtomicReference<PublishDisposable<T>[]> subscribers = new AtomicReference<>(EMPTY);

    @CheckReturnValue
    public static <T> PublishRelay<T> create() {
        return new PublishRelay<>();
    }

    PublishRelay() {
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        PublishDisposable<T> publishDisposable = new PublishDisposable<>(observer, this);
        observer.onSubscribe(publishDisposable);
        add(publishDisposable);
        if (publishDisposable.isDisposed()) {
            remove(publishDisposable);
        }
    }

    void add(PublishDisposable<T> publishDisposable) {
        PublishDisposable<T>[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = this.subscribers.get();
            int length = publishDisposableArr.length;
            publishDisposableArr2 = new PublishDisposable[length + 1];
            System.arraycopy(publishDisposableArr, 0, publishDisposableArr2, 0, length);
            publishDisposableArr2[length] = publishDisposable;
        } while (!ByteQuadsCanonicalizerExternalSyntheticBackportWithForwarding0.m(this.subscribers, publishDisposableArr, publishDisposableArr2));
    }

    void remove(PublishDisposable<T> publishDisposable) {
        PublishDisposable<T>[] publishDisposableArr;
        PublishDisposable[] publishDisposableArr2;
        do {
            publishDisposableArr = this.subscribers.get();
            if (publishDisposableArr == EMPTY) {
                return;
            }
            int length = publishDisposableArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (publishDisposableArr[i] == publishDisposable) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                publishDisposableArr2 = EMPTY;
            } else {
                PublishDisposable[] publishDisposableArr3 = new PublishDisposable[length - 1];
                System.arraycopy(publishDisposableArr, 0, publishDisposableArr3, 0, i);
                System.arraycopy(publishDisposableArr, i + 1, publishDisposableArr3, i, (length - i) - 1);
                publishDisposableArr2 = publishDisposableArr3;
            }
        } while (!ByteQuadsCanonicalizerExternalSyntheticBackportWithForwarding0.m(this.subscribers, publishDisposableArr, publishDisposableArr2));
    }

    @Override
    public void accept(T t) {
        if (t == null) {
            throw new NullPointerException("value == null");
        }
        for (PublishDisposable<T> publishDisposable : this.subscribers.get()) {
            publishDisposable.onNext(t);
        }
    }

    @Override
    public boolean hasObservers() {
        return this.subscribers.get().length != 0;
    }

    public static final class PublishDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 3562861878281475070L;
        final Observer<? super T> downstream;
        final PublishRelay<T> parent;

        PublishDisposable(Observer<? super T> observer, PublishRelay<T> publishRelay) {
            this.downstream = observer;
            this.parent = publishRelay;
        }

        public void onNext(T t) {
            if (get()) {
                return;
            }
            this.downstream.onNext(t);
        }

        @Override
        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }

        @Override
        public boolean isDisposed() {
            return get();
        }
    }
}
