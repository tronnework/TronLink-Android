package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import java.util.concurrent.TimeUnit;
abstract class ForwardingManagedChannel extends ManagedChannel {
    private final ManagedChannel delegate;

    public ForwardingManagedChannel(ManagedChannel managedChannel) {
        this.delegate = managedChannel;
    }

    @Override
    public ManagedChannel shutdown() {
        return this.delegate.shutdown();
    }

    @Override
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override
    public ManagedChannel shutdownNow() {
        return this.delegate.shutdownNow();
    }

    @Override
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    @Override
    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        return this.delegate.newCall(methodDescriptor, callOptions);
    }

    @Override
    public String authority() {
        return this.delegate.authority();
    }

    @Override
    public ConnectivityState getState(boolean z) {
        return this.delegate.getState(z);
    }

    @Override
    public void notifyWhenStateChanged(ConnectivityState connectivityState, Runnable runnable) {
        this.delegate.notifyWhenStateChanged(connectivityState, runnable);
    }

    @Override
    public void resetConnectBackoff() {
        this.delegate.resetConnectBackoff();
    }

    @Override
    public void enterIdle() {
        this.delegate.enterIdle();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", this.delegate).toString();
    }
}
