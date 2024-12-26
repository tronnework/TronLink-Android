package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGatt;
import bleshadow.dagger.Binds;
import bleshadow.dagger.Module;
import bleshadow.dagger.Provides;
import bleshadow.dagger.multibindings.IntoSet;
import bleshadow.javax.inject.Named;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.internal.operations.OperationsProvider;
import com.polidea.rxandroidble2.internal.operations.OperationsProviderImpl;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueue;
import com.polidea.rxandroidble2.internal.serialization.ConnectionOperationQueueImpl;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
import io.reactivex.Scheduler;
@Module
public abstract class ConnectionModule {
    public static final String OPERATION_TIMEOUT = "operation-timeout";

    @Named("GATT_WRITE_MTU_OVERHEAD")
    @Provides
    public static int gattWriteMtuOverhead() {
        return 3;
    }

    @Named("GATT_MTU_MINIMUM")
    @Provides
    public static int minimumMtu() {
        return 23;
    }

    @Binds
    abstract ConnectionOperationQueue bindConnectionOperationQueue(ConnectionOperationQueueImpl connectionOperationQueueImpl);

    @Binds
    @IntoSet
    abstract ConnectionSubscriptionWatcher bindConnectionQueueSubscriptionWatcher(ConnectionOperationQueueImpl connectionOperationQueueImpl);

    @Binds
    abstract MtuProvider bindCurrentMtuProvider(MtuWatcher mtuWatcher);

    @Binds
    @IntoSet
    abstract ConnectionSubscriptionWatcher bindDisconnectActionSubscriptionWatcher(DisconnectAction disconnectAction);

    @Binds
    abstract DisconnectionRouterInput bindDisconnectionRouterInput(DisconnectionRouter disconnectionRouter);

    @Binds
    abstract DisconnectionRouterOutput bindDisconnectionRouterOutput(DisconnectionRouter disconnectionRouter);

    @Binds
    abstract RxBleConnection.LongWriteOperationBuilder bindLongWriteOperationBuilder(LongWriteOperationBuilderImpl longWriteOperationBuilderImpl);

    @Binds
    @IntoSet
    abstract ConnectionSubscriptionWatcher bindMtuWatcherSubscriptionWatcher(MtuWatcher mtuWatcher);

    @Binds
    abstract OperationsProvider bindOperationsProvider(OperationsProviderImpl operationsProviderImpl);

    @Binds
    abstract RxBleConnection bindRxBleConnection(RxBleConnectionImpl rxBleConnectionImpl);

    @Named("operation-timeout")
    @Provides
    public static TimeoutConfiguration providesOperationTimeoutConf(@Named("timeout") Scheduler scheduler, Timeout timeout) {
        return new TimeoutConfiguration(timeout.timeout, timeout.timeUnit, scheduler);
    }

    @Provides
    public static IllegalOperationHandler provideIllegalOperationHandler(@Named("suppressOperationChecks") boolean z, Provider<LoggingIllegalOperationHandler> provider, Provider<ThrowingIllegalOperationHandler> provider2) {
        if (z) {
            return provider.get();
        }
        return provider2.get();
    }

    @Provides
    public static CharacteristicPropertiesParser provideCharacteristicPropertiesParser() {
        return new CharacteristicPropertiesParser(1, 2, 4, 8, 16, 32, 64);
    }

    @Provides
    public static BluetoothGatt provideBluetoothGatt(BluetoothGattProvider bluetoothGattProvider) {
        return bluetoothGattProvider.getBluetoothGatt();
    }
}
