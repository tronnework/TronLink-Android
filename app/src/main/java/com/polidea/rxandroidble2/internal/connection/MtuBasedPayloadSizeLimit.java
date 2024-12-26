package com.polidea.rxandroidble2.internal.connection;

import bleshadow.javax.inject.Inject;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
@ConnectionScope
class MtuBasedPayloadSizeLimit implements PayloadSizeLimitProvider {
    private final int gattWriteMtuOverhead;
    private final RxBleConnection rxBleConnection;

    @Inject
    public MtuBasedPayloadSizeLimit(RxBleConnection rxBleConnection, @Named("GATT_WRITE_MTU_OVERHEAD") int i) {
        this.rxBleConnection = rxBleConnection;
        this.gattWriteMtuOverhead = i;
    }

    @Override
    public int getPayloadSizeLimit() {
        return this.rxBleConnection.getMtu() - this.gattWriteMtuOverhead;
    }
}
