package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleGattException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import io.reactivex.Observable;
import io.reactivex.disposables.SerialDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
@ConnectionScope
class MtuWatcher implements ConnectionSubscriptionWatcher, MtuProvider, Consumer<Integer> {
    private Integer currentMtu;
    private final Observable<Integer> mtuObservable;
    private final SerialDisposable serialSubscription = new SerialDisposable();

    @Override
    public void accept(Integer num) {
        this.currentMtu = num;
    }

    @Inject
    public MtuWatcher(RxBleGattCallback rxBleGattCallback, @Named("GATT_MTU_MINIMUM") int i) {
        this.mtuObservable = rxBleGattCallback.getOnMtuChanged().retry(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable th) {
                return (th instanceof BleGattException) && ((BleGattException) th).getBleGattOperationType() == BleGattOperationType.ON_MTU_CHANGED;
            }
        });
        this.currentMtu = Integer.valueOf(i);
    }

    @Override
    public int getMtu() {
        return this.currentMtu.intValue();
    }

    @Override
    public void onConnectionSubscribed() {
        this.serialSubscription.set(this.mtuObservable.subscribe(this, Functions.emptyConsumer()));
    }

    @Override
    public void onConnectionUnsubscribed() {
        this.serialSubscription.dispose();
    }
}
