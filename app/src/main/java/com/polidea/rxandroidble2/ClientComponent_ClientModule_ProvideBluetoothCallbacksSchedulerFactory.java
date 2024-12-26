package com.polidea.rxandroidble2;

import bleshadow.dagger.internal.Factory;
import bleshadow.dagger.internal.Preconditions;
import com.polidea.rxandroidble2.ClientComponent;
import io.reactivex.Scheduler;
public final class ClientComponent_ClientModule_ProvideBluetoothCallbacksSchedulerFactory implements Factory<Scheduler> {
    private static final ClientComponent_ClientModule_ProvideBluetoothCallbacksSchedulerFactory INSTANCE = new ClientComponent_ClientModule_ProvideBluetoothCallbacksSchedulerFactory();

    public static ClientComponent_ClientModule_ProvideBluetoothCallbacksSchedulerFactory create() {
        return INSTANCE;
    }

    @Override
    public Scheduler get() {
        return (Scheduler) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothCallbacksScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Scheduler proxyProvideBluetoothCallbacksScheduler() {
        return (Scheduler) Preconditions.checkNotNull(ClientComponent.ClientModule.provideBluetoothCallbacksScheduler(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
