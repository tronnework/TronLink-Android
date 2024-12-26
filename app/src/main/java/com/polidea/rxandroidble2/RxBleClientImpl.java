package com.polidea.rxandroidble2;

import android.bluetooth.BluetoothDevice;
import bleshadow.dagger.Lazy;
import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.ClientComponent;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.exceptions.BleScanException;
import com.polidea.rxandroidble2.internal.RxBleDeviceProvider;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.operations.LegacyScanOperation;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResult;
import com.polidea.rxandroidble2.internal.scan.RxBleInternalScanResultLegacy;
import com.polidea.rxandroidble2.internal.scan.ScanPreconditionsVerifier;
import com.polidea.rxandroidble2.internal.scan.ScanSetup;
import com.polidea.rxandroidble2.internal.scan.ScanSetupBuilder;
import com.polidea.rxandroidble2.internal.serialization.ClientOperationQueue;
import com.polidea.rxandroidble2.internal.util.CheckerLocationPermission;
import com.polidea.rxandroidble2.internal.util.ClientStateObservable;
import com.polidea.rxandroidble2.internal.util.LocationServicesStatus;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
import com.polidea.rxandroidble2.scan.BackgroundScanner;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
public class RxBleClientImpl extends RxBleClient {
    @Deprecated
    public static final String TAG = "RxBleClient";
    private final BackgroundScanner backgroundScanner;
    final Scheduler bluetoothInteractionScheduler;
    private final CheckerLocationPermission checkerLocationPermission;
    private final ClientComponent.ClientComponentFinalizer clientComponentFinalizer;
    final Function<RxBleInternalScanResult, ScanResult> internalToExternalScanResultMapFunction;
    private final Lazy<ClientStateObservable> lazyClientStateObservable;
    private final LocationServicesStatus locationServicesStatus;
    final ClientOperationQueue operationQueue;
    final Map<Set<UUID>, Observable<RxBleScanResult>> queuedScanOperations = new HashMap();
    private final Observable<RxBleAdapterStateObservable.BleAdapterState> rxBleAdapterStateObservable;
    private final RxBleAdapterWrapper rxBleAdapterWrapper;
    private final RxBleDeviceProvider rxBleDeviceProvider;
    final ScanPreconditionsVerifier scanPreconditionVerifier;
    private final ScanRecordParser scanRecordParser;
    final ScanSetupBuilder scanSetupBuilder;

    @Override
    public BackgroundScanner getBackgroundScanner() {
        return this.backgroundScanner;
    }

    @Inject
    public RxBleClientImpl(RxBleAdapterWrapper rxBleAdapterWrapper, ClientOperationQueue clientOperationQueue, Observable<RxBleAdapterStateObservable.BleAdapterState> observable, ScanRecordParser scanRecordParser, LocationServicesStatus locationServicesStatus, Lazy<ClientStateObservable> lazy, RxBleDeviceProvider rxBleDeviceProvider, ScanSetupBuilder scanSetupBuilder, ScanPreconditionsVerifier scanPreconditionsVerifier, Function<RxBleInternalScanResult, ScanResult> function, @Named("bluetooth_interaction") Scheduler scheduler, ClientComponent.ClientComponentFinalizer clientComponentFinalizer, BackgroundScanner backgroundScanner, CheckerLocationPermission checkerLocationPermission) {
        this.operationQueue = clientOperationQueue;
        this.rxBleAdapterWrapper = rxBleAdapterWrapper;
        this.rxBleAdapterStateObservable = observable;
        this.scanRecordParser = scanRecordParser;
        this.locationServicesStatus = locationServicesStatus;
        this.lazyClientStateObservable = lazy;
        this.rxBleDeviceProvider = rxBleDeviceProvider;
        this.scanSetupBuilder = scanSetupBuilder;
        this.scanPreconditionVerifier = scanPreconditionsVerifier;
        this.internalToExternalScanResultMapFunction = function;
        this.bluetoothInteractionScheduler = scheduler;
        this.clientComponentFinalizer = clientComponentFinalizer;
        this.backgroundScanner = backgroundScanner;
        this.checkerLocationPermission = checkerLocationPermission;
    }

    protected void finalize() throws Throwable {
        this.clientComponentFinalizer.onFinalize();
        super.finalize();
    }

    @Override
    public RxBleDevice getBleDevice(String str) {
        guardBluetoothAdapterAvailable();
        return this.rxBleDeviceProvider.getBleDevice(str);
    }

    @Override
    public Set<RxBleDevice> getBondedDevices() {
        guardBluetoothAdapterAvailable();
        HashSet hashSet = new HashSet();
        for (BluetoothDevice bluetoothDevice : this.rxBleAdapterWrapper.getBondedDevices()) {
            hashSet.add(getBleDevice(bluetoothDevice.getAddress()));
        }
        return hashSet;
    }

    @Override
    public Observable<ScanResult> scanBleDevices(final ScanSettings scanSettings, final ScanFilter... scanFilterArr) {
        return Observable.defer(new Callable<ObservableSource<? extends ScanResult>>() {
            @Override
            public ObservableSource<? extends ScanResult> call() {
                scanPreconditionVerifier.verify(scanSettings.shouldCheckLocationProviderState());
                ScanSetup build = scanSetupBuilder.build(scanSettings, scanFilterArr);
                return operationQueue.queue(build.scanOperation).unsubscribeOn(bluetoothInteractionScheduler).compose(build.scanOperationBehaviourEmulatorTransformer).map(internalToExternalScanResultMapFunction).doOnNext(new Consumer<ScanResult>() {
                    @Override
                    public void accept(ScanResult scanResult) {
                        if (RxBleLog.getShouldLogScannedPeripherals()) {
                            RxBleLog.i("%s", scanResult);
                        }
                    }
                }).mergeWith(bluetoothAdapterOffExceptionObservable());
            }
        });
    }

    @Override
    @Deprecated
    public Observable<RxBleScanResult> scanBleDevices(final UUID... uuidArr) {
        return Observable.defer(new Callable<ObservableSource<? extends RxBleScanResult>>() {
            @Override
            public ObservableSource<? extends RxBleScanResult> call() {
                scanPreconditionVerifier.verify(true);
                return initializeScan(uuidArr);
            }
        });
    }

    private Set<UUID> toDistinctSet(UUID[] uuidArr) {
        if (uuidArr == null) {
            uuidArr = new UUID[0];
        }
        return new HashSet(Arrays.asList(uuidArr));
    }

    Observable<RxBleScanResult> initializeScan(UUID[] uuidArr) {
        Observable<RxBleScanResult> observable;
        Set<UUID> distinctSet = toDistinctSet(uuidArr);
        synchronized (this.queuedScanOperations) {
            observable = this.queuedScanOperations.get(distinctSet);
            if (observable == null) {
                observable = createScanOperationApi18(uuidArr);
                this.queuedScanOperations.put(distinctSet, observable);
            }
        }
        return observable;
    }

    <T> Observable<T> bluetoothAdapterOffExceptionObservable() {
        return this.rxBleAdapterStateObservable.filter(new Predicate<RxBleAdapterStateObservable.BleAdapterState>() {
            @Override
            public boolean test(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return bleAdapterState != RxBleAdapterStateObservable.BleAdapterState.STATE_ON;
            }
        }).firstElement().flatMap(new Function<RxBleAdapterStateObservable.BleAdapterState, MaybeSource<T>>() {
            @Override
            public MaybeSource<T> apply(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return Maybe.error(new BleScanException(1));
            }
        }).toObservable();
    }

    RxBleScanResult convertToPublicScanResult(RxBleInternalScanResultLegacy rxBleInternalScanResultLegacy) {
        return new RxBleScanResult(getBleDevice(rxBleInternalScanResultLegacy.getBluetoothDevice().getAddress()), rxBleInternalScanResultLegacy.getRssi(), rxBleInternalScanResultLegacy.getScanRecord());
    }

    private Observable<RxBleScanResult> createScanOperationApi18(UUID[] uuidArr) {
        final Set<UUID> distinctSet = toDistinctSet(uuidArr);
        return this.operationQueue.queue(new LegacyScanOperation(uuidArr, this.rxBleAdapterWrapper, this.scanRecordParser)).doFinally(new Action() {
            @Override
            public void run() {
                synchronized (queuedScanOperations) {
                    queuedScanOperations.remove(distinctSet);
                }
            }
        }).mergeWith(bluetoothAdapterOffExceptionObservable()).map(new Function<RxBleInternalScanResultLegacy, RxBleScanResult>() {
            @Override
            public RxBleScanResult apply(RxBleInternalScanResultLegacy rxBleInternalScanResultLegacy) {
                return convertToPublicScanResult(rxBleInternalScanResultLegacy);
            }
        }).doOnNext(new Consumer<RxBleScanResult>() {
            @Override
            public void accept(RxBleScanResult rxBleScanResult) {
                RxBleLog.i("%s", rxBleScanResult);
            }
        }).share();
    }

    private void guardBluetoothAdapterAvailable() {
        if (!this.rxBleAdapterWrapper.hasBluetoothAdapter()) {
            


return;

//throw new UnsupportedOperationException(
RxAndroidBle library needs a BluetoothAdapter to be available in the system to work. If this is a test on an emulator then you can use 'https://github.com/Polidea/RxAndroidBle/tree/master/mockrxandroidble'");
        }
    }

    @Override
    public Observable<RxBleClient.State> observeStateChanges() {
        return this.lazyClientStateObservable.get();
    }

    @Override
    public RxBleClient.State getState() {
        if (!this.rxBleAdapterWrapper.hasBluetoothAdapter()) {
            return RxBleClient.State.BLUETOOTH_NOT_AVAILABLE;
        }
        if (!this.locationServicesStatus.isLocationPermissionOk()) {
            return RxBleClient.State.LOCATION_PERMISSION_NOT_GRANTED;
        }
        if (!this.rxBleAdapterWrapper.isBluetoothEnabled()) {
            return RxBleClient.State.BLUETOOTH_NOT_ENABLED;
        }
        if (!this.locationServicesStatus.isLocationProviderOk()) {
            return RxBleClient.State.LOCATION_SERVICES_NOT_ENABLED;
        }
        return RxBleClient.State.READY;
    }

    @Override
    public boolean isScanRuntimePermissionGranted() {
        return this.checkerLocationPermission.isScanRuntimePermissionGranted();
    }

    @Override
    public String[] getRecommendedScanRuntimePermissions() {
        return this.checkerLocationPermission.getRecommendedScanRuntimePermissions();
    }
}
