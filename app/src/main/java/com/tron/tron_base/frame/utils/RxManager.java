package com.tron.tron_base.frame.utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
public class RxManager {
    private RxBus mRxBus = RxBus.getInstance();
    private Map<String, Observable<?>> mObservables = new HashMap();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DisposableMap disposableMap = new DisposableMap();

    public void on(String str, Consumer<Object> consumer) {
        Observable<?> register = this.mRxBus.register(str);
        this.mObservables.put(str, register);
        this.compositeDisposable.add(register.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable th) {
                LogUtils.e(th);
            }
        }));
    }

    public void onIO(String str, Consumer<Object> consumer) {
        Observable<?> register = this.mRxBus.register(str);
        this.mObservables.put(str, register);
        this.compositeDisposable.add(register.observeOn(Schedulers.io()).subscribe(consumer, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable th) throws Exception {
                LogUtils.e(th);
                LogUtils.e("RxManager", th);
            }
        }));
    }

    public void onSticky(String str, Consumer<Object> consumer) {
        Observable<?> register = this.mRxBus.register(str);
        Disposable registerSticky = this.mRxBus.registerSticky(str, consumer, (Subject) register);
        if (register != null) {
            this.mObservables.put(str, register);
        }
        if (registerSticky != null) {
            this.compositeDisposable.add(registerSticky);
        }
    }

    public void add(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }

    public void remove(Disposable disposable) {
        this.compositeDisposable.remove(disposable);
    }

    public void add(String str, Disposable disposable) {
        this.disposableMap.replaceDisposable(str, disposable);
    }

    public void removeDisposableByKey(String str) {
        this.disposableMap.removeDisposable(str);
    }

    public void clear() {
        this.compositeDisposable.dispose();
        this.disposableMap.removeAllDisposables();
        for (Map.Entry<String, Observable<?>> entry : this.mObservables.entrySet()) {
            this.mRxBus.unregister(entry.getKey(), entry.getValue());
        }
    }

    public void post(Object obj, Object obj2) {
        this.mRxBus.post(obj, obj2);
    }

    public void postSticky(Object obj, Object obj2) {
        this.mRxBus.postSticky(obj, obj2);
    }

    public void cancle() {
        this.compositeDisposable.clear();
        this.disposableMap.removeAllDisposables();
    }

    public class DisposableMap {
        private final Map<String, Disposable> Disposables;

        private DisposableMap() {
            this.Disposables = new HashMap();
        }

        public synchronized void replaceDisposable(String str, Disposable disposable) {
            Disposable put = this.Disposables.put(str, disposable);
            if (put != null && !put.isDisposed()) {
                put.dispose();
            }
        }

        public synchronized boolean removeDisposable(String str) {
            Disposable remove = this.Disposables.remove(str);
            if (remove == null) {
                return false;
            }
            if (!remove.isDisposed()) {
                remove.dispose();
            }
            return true;
        }

        public synchronized void removeAllDisposables() {
            Iterator<Map.Entry<String, Disposable>> it = this.Disposables.entrySet().iterator();
            while (it.hasNext()) {
                Disposable value = it.next().getValue();
                it.remove();
                if (!value.isDisposed()) {
                    value.dispose();
                }
            }
        }
    }
}
