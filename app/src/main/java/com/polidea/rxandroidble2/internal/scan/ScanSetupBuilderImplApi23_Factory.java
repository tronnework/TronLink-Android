package com.polidea.rxandroidble2.internal.scan;

import bleshadow.dagger.internal.Factory;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.internal.util.RxBleAdapterWrapper;
public final class ScanSetupBuilderImplApi23_Factory implements Factory<ScanSetupBuilderImplApi23> {
    private final Provider<AndroidScanObjectsConverter> androidScanObjectsConverterProvider;
    private final Provider<InternalScanResultCreator> internalScanResultCreatorProvider;
    private final Provider<RxBleAdapterWrapper> rxBleAdapterWrapperProvider;
    private final Provider<ScanSettingsEmulator> scanSettingsEmulatorProvider;

    public ScanSetupBuilderImplApi23_Factory(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3, Provider<AndroidScanObjectsConverter> provider4) {
        this.rxBleAdapterWrapperProvider = provider;
        this.internalScanResultCreatorProvider = provider2;
        this.scanSettingsEmulatorProvider = provider3;
        this.androidScanObjectsConverterProvider = provider4;
    }

    @Override
    public ScanSetupBuilderImplApi23 get() {
        return new ScanSetupBuilderImplApi23(this.rxBleAdapterWrapperProvider.get(), this.internalScanResultCreatorProvider.get(), this.scanSettingsEmulatorProvider.get(), this.androidScanObjectsConverterProvider.get());
    }

    public static ScanSetupBuilderImplApi23_Factory create(Provider<RxBleAdapterWrapper> provider, Provider<InternalScanResultCreator> provider2, Provider<ScanSettingsEmulator> provider3, Provider<AndroidScanObjectsConverter> provider4) {
        return new ScanSetupBuilderImplApi23_Factory(provider, provider2, provider3, provider4);
    }

    public static ScanSetupBuilderImplApi23 newScanSetupBuilderImplApi23(RxBleAdapterWrapper rxBleAdapterWrapper, InternalScanResultCreator internalScanResultCreator, ScanSettingsEmulator scanSettingsEmulator, AndroidScanObjectsConverter androidScanObjectsConverter) {
        return new ScanSetupBuilderImplApi23(rxBleAdapterWrapper, internalScanResultCreator, scanSettingsEmulator, androidScanObjectsConverter);
    }
}
