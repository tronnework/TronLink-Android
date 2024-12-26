package com.polidea.rxandroidble2.internal.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import bleshadow.javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Cancellable;
import io.reactivex.schedulers.Schedulers;
public class LocationServicesOkObservableApi23Factory {
    final Context context;
    final LocationServicesStatus locationServicesStatus;

    @Inject
    public LocationServicesOkObservableApi23Factory(Context context, LocationServicesStatus locationServicesStatus) {
        this.context = context;
        this.locationServicesStatus = locationServicesStatus;
    }

    public Observable<Boolean> get() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> observableEmitter) {
                boolean isLocationProviderOk = locationServicesStatus.isLocationProviderOk();
                final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        observableEmitter.onNext(Boolean.valueOf(locationServicesStatus.isLocationProviderOk()));
                    }
                };
                observableEmitter.onNext(Boolean.valueOf(isLocationProviderOk));
                context.registerReceiver(broadcastReceiver, new IntentFilter("android.location.MODE_CHANGED"));
                observableEmitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() {
                        context.unregisterReceiver(broadcastReceiver);
                    }
                });
            }
        }).distinctUntilChanged().subscribeOn(Schedulers.trampoline()).unsubscribeOn(Schedulers.trampoline());
    }
}
