package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattService;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
@ConnectionScope
public class ServiceDiscoveryManager {
    final BluetoothGatt bluetoothGatt;
    private Single<RxBleDeviceServices> deviceServicesObservable;
    final OperationsProvider operationProvider;
    final ConnectionOperationQueue operationQueue;
    final Subject<TimeoutConfiguration> timeoutBehaviorSubject = BehaviorSubject.create().toSerialized();
    boolean hasCachedResults = false;

    @Inject
    public ServiceDiscoveryManager(ConnectionOperationQueue connectionOperationQueue, BluetoothGatt bluetoothGatt, OperationsProvider operationsProvider) {
        this.operationQueue = connectionOperationQueue;
        this.bluetoothGatt = bluetoothGatt;
        this.operationProvider = operationsProvider;
        reset();
    }

    public Single<RxBleDeviceServices> getDiscoverServicesSingle(final long j, final TimeUnit timeUnit) {
        return this.hasCachedResults ? this.deviceServicesObservable : this.deviceServicesObservable.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) {
                timeoutBehaviorSubject.onNext(new TimeoutConfiguration(j, timeUnit, Schedulers.computation()));
            }
        });
    }

    void reset() {
        this.hasCachedResults = false;
        this.deviceServicesObservable = getListOfServicesFromGatt().map(wrapIntoRxBleDeviceServices()).switchIfEmpty(getTimeoutConfiguration().flatMap(scheduleActualDiscoveryWithTimeout())).doOnSuccess(Functions.actionConsumer(new Action() {
            @Override
            public void run() {
                hasCachedResults = true;
            }
        })).doOnError(Functions.actionConsumer(new Action() {
            @Override
            public void run() {
                reset();
            }
        })).cache();
    }

    private static Function<List<BluetoothGattService>, RxBleDeviceServices> wrapIntoRxBleDeviceServices() {
        return new Function<List<BluetoothGattService>, RxBleDeviceServices>() {
            @Override
            public RxBleDeviceServices apply(List<BluetoothGattService> list) {
                return new RxBleDeviceServices(list);
            }
        };
    }

    private Maybe<List<BluetoothGattService>> getListOfServicesFromGatt() {
        return Single.fromCallable(new Callable<List<BluetoothGattService>>() {
            @Override
            public List<BluetoothGattService> call() {
                return bluetoothGatt.getServices();
            }
        }).filter(new Predicate<List<BluetoothGattService>>() {
            @Override
            public boolean test(List<BluetoothGattService> list) {
                return list.size() > 0;
            }
        });
    }

    private Single<TimeoutConfiguration> getTimeoutConfiguration() {
        return this.timeoutBehaviorSubject.firstOrError();
    }

    private Function<TimeoutConfiguration, Single<RxBleDeviceServices>> scheduleActualDiscoveryWithTimeout() {
        return new Function<TimeoutConfiguration, Single<RxBleDeviceServices>>() {
            @Override
            public Single<RxBleDeviceServices> apply(TimeoutConfiguration timeoutConfiguration) {
                return operationQueue.queue(operationProvider.provideServiceDiscoveryOperation(timeoutConfiguration.timeout, timeoutConfiguration.timeoutTimeUnit)).firstOrError();
            }
        };
    }
}
