package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.BindsInstance;
import bleshadow.dagger.Subcomponent;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.internal.operations.ConnectOperation;
import java.util.Set;
@ConnectionScope
@Subcomponent(modules = {ConnectionModule.class})
public interface ConnectionComponent {

    public interface Builder {
        @BindsInstance
        Builder autoConnect(@Named("autoConnect") boolean z);

        ConnectionComponent build();

        @BindsInstance
        Builder operationTimeout(Timeout timeout);

        @BindsInstance
        Builder suppressOperationChecks(@Named("suppressOperationChecks") boolean z);
    }

    @ConnectionScope
    ConnectOperation connectOperation();

    @ConnectionScope
    Set<ConnectionSubscriptionWatcher> connectionSubscriptionWatchers();

    @ConnectionScope
    RxBleGattCallback gattCallback();

    @ConnectionScope
    RxBleConnection rxBleConnection();

    public static class NamedBooleans {
        public static final String AUTO_CONNECT = "autoConnect";
        public static final String SUPPRESS_OPERATION_CHECKS = "suppressOperationChecks";

        private NamedBooleans() {
        }
    }

    public static class NamedInts {
        static final String GATT_MTU_MINIMUM = "GATT_MTU_MINIMUM";
        static final String GATT_WRITE_MTU_OVERHEAD = "GATT_WRITE_MTU_OVERHEAD";

        private NamedInts() {
        }
    }
}
