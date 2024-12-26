package com.polidea.rxandroidble2.internal.scan;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.ObservableUtil;
import com.polidea.rxandroidble2.scan.ScanCallbackType;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import java.util.concurrent.TimeUnit;
public class ScanSettingsEmulator {
    final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateFirstMatch;
    final Scheduler scheduler;
    final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateMatchLost = new ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult>() {
        @Override
        public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.debounce(10L, TimeUnit.SECONDS, scheduler).map(ScanSettingsEmulator.toMatchLost());
        }
    };
    private final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateFirstMatchAndMatchLost = new ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult>() {
        @Override
        public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.publish(new Function<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() {
                @Override
                public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable2) {
                    return Observable.merge(observable2.compose(emulateFirstMatch), observable2.compose(emulateMatchLost));
                }
            });
        }
    };

    @Inject
    public ScanSettingsEmulator(@Named("computation") Scheduler scheduler) {
        this.scheduler = scheduler;
        this.emulateFirstMatch = new fun1(scheduler);
    }

    public class fun1 implements ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> {
        final Observable<Long> timerObservable;
        final Scheduler val$scheduler;
        final Function<RxBleInternalScanResult, RxBleInternalScanResult> toFirstMatchFunc = ScanSettingsEmulator.toFirstMatch();
        final Function<RxBleInternalScanResult, Observable<?>> emitAfterTimerFunc = new Function<RxBleInternalScanResult, Observable<?>>() {
            @Override
            public Observable<?> apply(RxBleInternalScanResult rxBleInternalScanResult) {
                return fun1.this.timerObservable;
            }
        };
        final Function<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>> takeFirstFromEachWindowFunc = new Function<Observable<RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() {
            @Override
            public Observable<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
                return observable.take(1L);
            }
        };

        fun1(Scheduler scheduler) {
            this.val$scheduler = scheduler;
            this.timerObservable = Observable.timer(10L, TimeUnit.SECONDS, scheduler);
        }

        @Override
        public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
            return observable.publish(new Function<Observable<RxBleInternalScanResult>, ObservableSource<RxBleInternalScanResult>>() {
                @Override
                public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable2) {
                    return observable2.window(observable2.switchMap(fun1.this.emitAfterTimerFunc)).flatMap(fun1.this.takeFirstFromEachWindowFunc).map(fun1.this.toFirstMatchFunc);
                }
            });
        }
    }

    public ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateScanMode(int i) {
        if (i == -1) {
            RxBleLog.w("Cannot emulate opportunistic scan mode since it is OS dependent - fallthrough to low power", new Object[0]);
        } else if (i != 0) {
            if (i == 1) {
                return scanModeBalancedTransformer();
            }
            return ObservableUtil.identityTransformer();
        }
        return scanModeLowPowerTransformer();
    }

    private ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> scanModeBalancedTransformer() {
        return repeatedWindowTransformer(2500);
    }

    private ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> scanModeLowPowerTransformer() {
        return repeatedWindowTransformer(500);
    }

    private ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> repeatedWindowTransformer(final int i) {
        final long max = Math.max(TimeUnit.SECONDS.toMillis(5L) - i, 0L);
        return new ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult>() {
            @Override
            public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
                return observable.take(i, TimeUnit.MILLISECONDS, scheduler).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Object> observable2) {
                        return observable2.delay(max, TimeUnit.MILLISECONDS, scheduler);
                    }
                });
            }
        };
    }

    public ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> emulateCallbackType(int i) {
        if (i != 2) {
            if (i != 4) {
                if (i == 6) {
                    return splitByAddressAndForEach(this.emulateFirstMatchAndMatchLost);
                }
                return ObservableUtil.identityTransformer();
            }
            return splitByAddressAndForEach(this.emulateMatchLost);
        }
        return splitByAddressAndForEach(this.emulateFirstMatch);
    }

    private static ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> splitByAddressAndForEach(final ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult> observableTransformer) {
        return new ObservableTransformer<RxBleInternalScanResult, RxBleInternalScanResult>() {
            @Override
            public ObservableSource<RxBleInternalScanResult> apply(Observable<RxBleInternalScanResult> observable) {
                return observable.groupBy(new Function<RxBleInternalScanResult, String>() {
                    @Override
                    public String apply(RxBleInternalScanResult rxBleInternalScanResult) {
                        return rxBleInternalScanResult.getBluetoothDevice().getAddress();
                    }
                }).flatMap(new Function<GroupedObservable<String, RxBleInternalScanResult>, Observable<RxBleInternalScanResult>>() {
                    @Override
                    public Observable<RxBleInternalScanResult> apply(GroupedObservable<String, RxBleInternalScanResult> groupedObservable) {
                        return groupedObservable.compose(ObservableTransformer.this);
                    }
                });
            }
        };
    }

    static Function<RxBleInternalScanResult, RxBleInternalScanResult> toFirstMatch() {
        return new Function<RxBleInternalScanResult, RxBleInternalScanResult>() {
            @Override
            public RxBleInternalScanResult apply(RxBleInternalScanResult rxBleInternalScanResult) {
                return new RxBleInternalScanResult(rxBleInternalScanResult.getBluetoothDevice(), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanRecord(), ScanCallbackType.CALLBACK_TYPE_FIRST_MATCH);
            }
        };
    }

    static Function<RxBleInternalScanResult, RxBleInternalScanResult> toMatchLost() {
        return new Function<RxBleInternalScanResult, RxBleInternalScanResult>() {
            @Override
            public RxBleInternalScanResult apply(RxBleInternalScanResult rxBleInternalScanResult) {
                return new RxBleInternalScanResult(rxBleInternalScanResult.getBluetoothDevice(), rxBleInternalScanResult.getRssi(), rxBleInternalScanResult.getTimestampNanos(), rxBleInternalScanResult.getScanRecord(), ScanCallbackType.CALLBACK_TYPE_MATCH_LOST);
            }
        };
    }
}
