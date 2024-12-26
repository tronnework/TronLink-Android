package com.polidea.rxandroidble2.internal.scan;

import com.polidea.rxandroidble2.internal.scan.ExternalScanSettingsExtension;
public interface ExternalScanSettingsExtension<R extends ExternalScanSettingsExtension<R>> {

    public interface Builder<T extends Builder<T>> {
        T setShouldCheckLocationServicesState(boolean z);
    }

    R copyWithCallbackType(int i);

    boolean shouldCheckLocationProviderState();
}
