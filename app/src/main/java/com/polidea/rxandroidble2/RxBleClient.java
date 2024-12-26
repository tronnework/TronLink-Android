package com.polidea.rxandroidble2;

import android.content.Context;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.scan.BackgroundScanner;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;
import io.reactivex.Observable;
import java.util.Set;
import java.util.UUID;
public abstract class RxBleClient {

    public enum State {
        BLUETOOTH_NOT_AVAILABLE,
        LOCATION_PERMISSION_NOT_GRANTED,
        BLUETOOTH_NOT_ENABLED,
        LOCATION_SERVICES_NOT_ENABLED,
        READY
    }

    public abstract BackgroundScanner getBackgroundScanner();

    public abstract RxBleDevice getBleDevice(String str);

    public abstract Set<RxBleDevice> getBondedDevices();

    public abstract String[] getRecommendedScanRuntimePermissions();

    public abstract State getState();

    public abstract boolean isScanRuntimePermissionGranted();

    public abstract Observable<State> observeStateChanges();

    public abstract Observable<ScanResult> scanBleDevices(ScanSettings scanSettings, ScanFilter... scanFilterArr);

    @Deprecated
    public abstract Observable<RxBleScanResult> scanBleDevices(UUID... uuidArr);

    public static RxBleClient create(Context context) {
        return DaggerClientComponent.builder().applicationContext(context.getApplicationContext()).build().rxBleClient();
    }

    @Deprecated
    public static void setLogLevel(int i) {
        RxBleLog.setLogLevel(i);
    }

    public static void updateLogOptions(LogOptions logOptions) {
        RxBleLog.updateLogOptions(logOptions);
    }
}
