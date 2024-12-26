package com.polidea.rxandroidble2.internal.connection;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
public final class ConnectionModule_ProvideIllegalOperationHandlerFactory implements Factory<IllegalOperationHandler> {
    private final Provider<LoggingIllegalOperationHandler> loggingIllegalOperationHandlerProvider;
    private final Provider<Boolean> suppressOperationCheckProvider;
    private final Provider<ThrowingIllegalOperationHandler> throwingIllegalOperationHandlerProvider;

    public ConnectionModule_ProvideIllegalOperationHandlerFactory(Provider<Boolean> provider, Provider<LoggingIllegalOperationHandler> provider2, Provider<ThrowingIllegalOperationHandler> provider3) {
        this.suppressOperationCheckProvider = provider;
        this.loggingIllegalOperationHandlerProvider = provider2;
        this.throwingIllegalOperationHandlerProvider = provider3;
    }

    @Override
    public IllegalOperationHandler get() {
        return (IllegalOperationHandler) Preconditions.checkNotNull(ConnectionModule.provideIllegalOperationHandler(this.suppressOperationCheckProvider.get().booleanValue(), this.loggingIllegalOperationHandlerProvider, this.throwingIllegalOperationHandlerProvider), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ConnectionModule_ProvideIllegalOperationHandlerFactory create(Provider<Boolean> provider, Provider<LoggingIllegalOperationHandler> provider2, Provider<ThrowingIllegalOperationHandler> provider3) {
        return new ConnectionModule_ProvideIllegalOperationHandlerFactory(provider, provider2, provider3);
    }

    public static IllegalOperationHandler proxyProvideIllegalOperationHandler(boolean z, Provider<LoggingIllegalOperationHandler> provider, Provider<ThrowingIllegalOperationHandler> provider2) {
        return (IllegalOperationHandler) Preconditions.checkNotNull(ConnectionModule.provideIllegalOperationHandler(z, provider, provider2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
