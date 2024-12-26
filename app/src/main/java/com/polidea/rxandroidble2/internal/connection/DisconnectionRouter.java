package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.exceptions.BleDisconnectedException;
import com.polidea.rxandroidble2.exceptions.BleException;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
@ConnectionScope
public class DisconnectionRouter implements DisconnectionRouterInput, DisconnectionRouterOutput {
    private final BehaviorRelay<BleException> bleExceptionBehaviorRelay;
    private final Observable<Object> firstDisconnectionExceptionObs;
    private final Observable<BleException> firstDisconnectionValueObs;

    @Override
    public <T> Observable<T> asErrorOnlyObservable() {
        return (Observable<T>) this.firstDisconnectionExceptionObs;
    }

    @Override
    public Observable<BleException> asValueOnlyObservable() {
        return this.firstDisconnectionValueObs;
    }

    @Inject
    public DisconnectionRouter(@Named("mac-address") final String str, RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable) {
        BehaviorRelay<BleException> create = BehaviorRelay.create();
        this.bleExceptionBehaviorRelay = create;
        final Disposable subscribe = awaitAdapterNotUsable(rxBleAdapterWrapper, observable).map(new Function<Boolean, BleException>() {
            @Override
            public BleException apply(Boolean bool) {
                return BleDisconnectedException.adapterDisabled(str);
            }
        }).doOnNext(new Consumer<BleException>() {
            @Override
            public void accept(BleException bleException) {
                RxBleLog.v("An exception received, indicating that the adapter has became unusable.", new Object[0]);
            }
        }).subscribe(create, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable th) {
                RxBleLog.e(th, "Failed to monitor adapter state.", new Object[0]);
            }
        });
        Observable<BleException> autoConnect = create.firstElement().toObservable().doOnTerminate(new Action() {
            @Override
            public void run() {
                subscribe.dispose();
            }
        }).replay().autoConnect(0);
        this.firstDisconnectionValueObs = autoConnect;
        this.firstDisconnectionExceptionObs = autoConnect.flatMap(new Function<BleException, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(BleException bleException) {
                return Observable.error(bleException);
            }
        });
    }

    private static Observable<Boolean> awaitAdapterNotUsable(RxBleAdapterWrapper rxBleAdapterWrapper, Observable<RxBleAdapterStateObservable.BleAdapterState> observable) {
        return observable.map(new Function<RxBleAdapterStateObservable.BleAdapterState, Boolean>() {
            @Override
            public Boolean apply(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return Boolean.valueOf(bleAdapterState.isUsable());
            }
        }).startWith((Observable<R>) Boolean.valueOf(rxBleAdapterWrapper.isBluetoothEnabled())).filter(new Predicate<Boolean>() {
            @Override
            public boolean test(Boolean bool) {
                return !bool.booleanValue();
            }
        });
    }

    @Override
    public void onDisconnectedException(BleDisconnectedException bleDisconnectedException) {
        this.bleExceptionBehaviorRelay.accept(bleDisconnectedException);
    }

    @Override
    public void onGattConnectionStateException(BleGattException bleGattException) {
        this.bleExceptionBehaviorRelay.accept(bleGattException);
    }
}
