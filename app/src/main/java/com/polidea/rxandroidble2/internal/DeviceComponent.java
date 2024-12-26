package com.polidea.rxandroidble2.internal;

import bleshadow.dagger.BindsInstance;
import bleshadow.dagger.Subcomponent;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleDevice;
@DeviceScope
@Subcomponent(modules = {DeviceModule.class})
public interface DeviceComponent {

    public interface Builder {
        DeviceComponent build();

        @BindsInstance
        Builder macAddress(@Named("mac-address") String str);
    }

    @DeviceScope
    RxBleDevice provideDevice();
}
