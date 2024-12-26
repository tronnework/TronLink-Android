package com.polidea.rxandroidble2.helpers;

import android.content.Context;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.DaggerClientComponent;
import io.reactivex.Observable;
import io.reactivex.Observer;
public class LocationServicesOkObservable extends Observable<Boolean> {
    private final Observable<Boolean> locationServicesOkObsImpl;

    public static LocationServicesOkObservable createInstance(Context context) {
        return DaggerClientComponent.builder().applicationContext(context.getApplicationContext()).build().locationServicesOkObservable();
    }

    @Inject
    public LocationServicesOkObservable(@Named("location-ok-boolean-observable") Observable<Boolean> observable) {
        this.locationServicesOkObsImpl = observable;
    }

    @Override
    protected void subscribeActual(Observer<? super Boolean> observer) {
        this.locationServicesOkObsImpl.subscribe(observer);
    }
}
