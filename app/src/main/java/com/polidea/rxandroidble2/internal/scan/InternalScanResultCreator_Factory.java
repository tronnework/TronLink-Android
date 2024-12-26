package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.ScanRecordParser;
public final class InternalScanResultCreator_Factory implements Factory<InternalScanResultCreator> {
    private final Provider<ScanRecordParser> scanRecordParserProvider;

    public InternalScanResultCreator_Factory(Provider<ScanRecordParser> provider) {
        this.scanRecordParserProvider = provider;
    }

    @Override
    public InternalScanResultCreator get() {
        return new InternalScanResultCreator(this.scanRecordParserProvider.get());
    }

    public static InternalScanResultCreator_Factory create(Provider<ScanRecordParser> provider) {
        return new InternalScanResultCreator_Factory(provider);
    }
}
