package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.exceptions.BleGattCallbackTimeoutException;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.SingleResponseOperation;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtilBluetoothServices;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
public class ServiceDiscoveryOperation extends SingleResponseOperation<RxBleDeviceServices> {
    final LoggerUtilBluetoothServices bleServicesLogger;
    final BluetoothGatt bluetoothGatt;

    public ServiceDiscoveryOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, LoggerUtilBluetoothServices loggerUtilBluetoothServices, TimeoutConfiguration timeoutConfiguration) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.SERVICE_DISCOVERY, timeoutConfiguration);
        this.bluetoothGatt = bluetoothGatt;
        this.bleServicesLogger = loggerUtilBluetoothServices;
    }

    @Override
    protected Single<RxBleDeviceServices> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnServicesDiscovered().firstOrError().doOnSuccess(new Consumer<RxBleDeviceServices>() {
            @Override
            public void accept(RxBleDeviceServices rxBleDeviceServices) {
                bleServicesLogger.log(rxBleDeviceServices, bluetoothGatt.getDevice());
            }
        });
    }

    @Override
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        return bluetoothGatt.discoverServices();
    }

    public class fun2 implements Callable<SingleSource<? extends RxBleDeviceServices>> {
        final BluetoothGatt val$bluetoothGatt;
        final Scheduler val$timeoutScheduler;

        fun2(BluetoothGatt bluetoothGatt, Scheduler scheduler) {
            this.val$bluetoothGatt = bluetoothGatt;
            this.val$timeoutScheduler = scheduler;
        }

        @Override
        public SingleSource<? extends RxBleDeviceServices> call() {
            if (this.val$bluetoothGatt.getServices().size() == 0) {
                return Single.error(new BleGattCallbackTimeoutException(this.val$bluetoothGatt, BleGattOperationType.SERVICE_DISCOVERY));
            }
            return Single.timer(5L, TimeUnit.SECONDS, this.val$timeoutScheduler).flatMap(new Function<Long, Single<RxBleDeviceServices>>() {
                @Override
                public Single<RxBleDeviceServices> apply(Long l) {
                    return Single.fromCallable(new Callable<RxBleDeviceServices>() {
                        @Override
                        public RxBleDeviceServices call() {
                            return new RxBleDeviceServices(fun2.this.val$bluetoothGatt.getServices());
                        }
                    });
                }
            });
        }
    }

    @Override
    protected Single<RxBleDeviceServices> timeoutFallbackProcedure(BluetoothGatt bluetoothGatt, RxBleGattCallback rxBleGattCallback, Scheduler scheduler) {
        return Single.defer(new fun2(bluetoothGatt, scheduler));
    }

    @Override
    public String toString() {
        return "ServiceDiscoveryOperation{" + super.toString() + '}';
    }
}
