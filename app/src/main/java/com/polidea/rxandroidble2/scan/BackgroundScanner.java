package com.polidea.rxandroidble2.scan;

import android.app.PendingIntent;
import android.content.Intent;
import java.util.List;
public interface BackgroundScanner {
    List<ScanResult> onScanResultReceived(Intent intent);

    void scanBleDeviceInBackground(PendingIntent pendingIntent, ScanSettings scanSettings, ScanFilter... scanFilterArr);

    void stopBackgroundBleScan(PendingIntent pendingIntent);
}
