package com.polidea.rxandroidble2.internal.util;

import bleshadow.dagger.internal.Factory;
public final class ScanRecordParser_Factory implements Factory<ScanRecordParser> {
    private static final ScanRecordParser_Factory INSTANCE = new ScanRecordParser_Factory();

    public static ScanRecordParser_Factory create() {
        return INSTANCE;
    }

    @Override
    public ScanRecordParser get() {
        return new ScanRecordParser();
    }
}
