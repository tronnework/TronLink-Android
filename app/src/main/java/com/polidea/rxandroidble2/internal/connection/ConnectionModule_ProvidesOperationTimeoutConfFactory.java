package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.Timeout;
import com.polidea.rxandroidble2.internal.operations.TimeoutConfiguration;
import io.reactivex.Scheduler;
public final class ConnectionModule_ProvidesOperationTimeoutConfFactory implements Factory<TimeoutConfiguration> {
    private final Provider<Timeout> operationTimeoutProvider;
    private final Provider<Scheduler> timeoutSchedulerProvider;

    public ConnectionModule_ProvidesOperationTimeoutConfFactory(Provider<Scheduler> provider, Provider<Timeout> provider2) {
        this.timeoutSchedulerProvider = provider;
        this.operationTimeoutProvider = provider2;
    }

    @Override
    public TimeoutConfiguration get() {
        return (TimeoutConfiguration) Preconditions.checkNotNull(ConnectionModule.providesOperationTimeoutConf(this.timeoutSchedulerProvider.get(), this.operationTimeoutProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ConnectionModule_ProvidesOperationTimeoutConfFactory create(Provider<Scheduler> provider, Provider<Timeout> provider2) {
        return new ConnectionModule_ProvidesOperationTimeoutConfFactory(provider, provider2);
    }

    public static TimeoutConfiguration proxyProvidesOperationTimeoutConf(Scheduler scheduler, Timeout timeout) {
        return (TimeoutConfiguration) Preconditions.checkNotNull(ConnectionModule.providesOperationTimeoutConf(scheduler, timeout), "Cannot return null from a non-@Nullable @Provides method");
    }
}
