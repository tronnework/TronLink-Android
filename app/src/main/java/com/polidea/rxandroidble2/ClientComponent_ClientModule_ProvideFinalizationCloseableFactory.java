package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import bleshadow.javax.inject.Provider;
import com.polidea.rxandroidble2.ClientComponent;
import io.reactivex.Scheduler;
import java.util.concurrent.ExecutorService;
public final class ClientComponent_ClientModule_ProvideFinalizationCloseableFactory implements Factory<ClientComponent.ClientComponentFinalizer> {
    private final Provider<Scheduler> callbacksSchedulerProvider;
    private final Provider<ExecutorService> connectionQueueExecutorServiceProvider;
    private final Provider<ExecutorService> interactionExecutorServiceProvider;

    public ClientComponent_ClientModule_ProvideFinalizationCloseableFactory(Provider<ExecutorService> provider, Provider<Scheduler> provider2, Provider<ExecutorService> provider3) {
        this.interactionExecutorServiceProvider = provider;
        this.callbacksSchedulerProvider = provider2;
        this.connectionQueueExecutorServiceProvider = provider3;
    }

    @Override
    public ClientComponent.ClientComponentFinalizer get() {
        return (ClientComponent.ClientComponentFinalizer) Preconditions.checkNotNull(ClientComponent.ClientModule.provideFinalizationCloseable(this.interactionExecutorServiceProvider.get(), this.callbacksSchedulerProvider.get(), this.connectionQueueExecutorServiceProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static ClientComponent_ClientModule_ProvideFinalizationCloseableFactory create(Provider<ExecutorService> provider, Provider<Scheduler> provider2, Provider<ExecutorService> provider3) {
        return new ClientComponent_ClientModule_ProvideFinalizationCloseableFactory(provider, provider2, provider3);
    }

    public static ClientComponent.ClientComponentFinalizer proxyProvideFinalizationCloseable(ExecutorService executorService, Scheduler scheduler, ExecutorService executorService2) {
        return (ClientComponent.ClientComponentFinalizer) Preconditions.checkNotNull(ClientComponent.ClientModule.provideFinalizationCloseable(executorService, scheduler, executorService2), "Cannot return null from a non-@Nullable @Provides method");
    }
}
