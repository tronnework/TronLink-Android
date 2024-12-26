package com.tron.wallet.ledger.bleclient;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
public class RxSchedulers2 {
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

    public static <T> SingleTransformer<T, T> io_main_single() {
        return new SingleTransformer() {
            @Override
            public final SingleSource apply(Single single) {
                SingleSource observeOn;
                observeOn = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                return observeOn;
            }
        };
    }
}
