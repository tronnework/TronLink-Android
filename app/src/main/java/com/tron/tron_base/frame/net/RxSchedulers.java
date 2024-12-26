package com.tron.tron_base.frame.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
public class RxSchedulers {
    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer() {
            @Override
            public final ObservableSource apply(Observable observable) {
                ObservableSource observeOn;
                observeOn = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                return observeOn;
            }
        };
    }

    public static <T> ObservableTransformer<T, T> io_io() {
        return new ObservableTransformer() {
            @Override
            public final ObservableSource apply(Observable observable) {
                ObservableSource observeOn;
                observeOn = observable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
                return observeOn;
            }
        };
    }
}
