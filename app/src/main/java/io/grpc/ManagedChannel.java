package io.grpc;

import java.util.concurrent.TimeUnit;
public abstract class ManagedChannel extends Channel {
    public abstract boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException;

    public void enterIdle() {
    }

    public abstract boolean isShutdown();

    public abstract boolean isTerminated();

    public void resetConnectBackoff() {
    }

    public abstract ManagedChannel shutdown();

    public abstract ManagedChannel shutdownNow();

    public ConnectivityState getState(boolean z) {
        


return null;

//throw new UnsupportedOperationException(
Not implemented");
    }

    public void notifyWhenStateChanged(ConnectivityState connectivityState, Runnable runnable) {
        


return;

//throw new UnsupportedOperationException(
Not implemented");
    }
}
