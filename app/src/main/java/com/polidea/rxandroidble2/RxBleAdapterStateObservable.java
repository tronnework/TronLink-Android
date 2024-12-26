package com.polidea.rxandroidble2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
public class RxBleAdapterStateObservable extends Observable<BleAdapterState> {
    private final Observable<BleAdapterState> bleAdapterStateObservable;

    public static class BleAdapterState {
        private final boolean isUsable;
        private final String stateName;
        public static final BleAdapterState STATE_ON = new BleAdapterState(true, "STATE_ON");
        public static final BleAdapterState STATE_OFF = new BleAdapterState(false, "STATE_OFF");
        public static final BleAdapterState STATE_TURNING_ON = new BleAdapterState(false, "STATE_TURNING_ON");
        public static final BleAdapterState STATE_TURNING_OFF = new BleAdapterState(false, "STATE_TURNING_OFF");

        public boolean isUsable() {
            return this.isUsable;
        }

        public String toString() {
            return this.stateName;
        }

        private BleAdapterState(boolean z, String str) {
            this.isUsable = z;
            this.stateName = str;
        }
    }

    @Inject
    public RxBleAdapterStateObservable(final Context context) {
        this.bleAdapterStateObservable = Observable.create(new ObservableOnSubscribe<BleAdapterState>() {
            @Override
            public void subscribe(final ObservableEmitter<BleAdapterState> observableEmitter) {
                final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context2, Intent intent) {
                        BleAdapterState mapToBleAdapterState = RxBleAdapterStateObservable.mapToBleAdapterState(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1));
                        RxBleLog.i("Adapter state changed: %s", mapToBleAdapterState);
                        observableEmitter.onNext(mapToBleAdapterState);
                    }
                };
                context.registerReceiver(broadcastReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                observableEmitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() {
                        context.unregisterReceiver(broadcastReceiver);
                    }
                });
            }
        }).subscribeOn(Schedulers.trampoline()).unsubscribeOn(Schedulers.trampoline()).share();
    }

    @Override
    protected void subscribeActual(Observer<? super BleAdapterState> observer) {
        this.bleAdapterStateObservable.subscribe(observer);
    }

    static BleAdapterState mapToBleAdapterState(int i) {
        switch (i) {
            case 11:
                return BleAdapterState.STATE_TURNING_ON;
            case 12:
                return BleAdapterState.STATE_ON;
            case 13:
                return BleAdapterState.STATE_TURNING_OFF;
            default:
                return BleAdapterState.STATE_OFF;
        }
    }
}
