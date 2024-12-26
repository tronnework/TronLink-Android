package io.grpc.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.firebase.messaging.Constants;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.LoadBalancer;
import io.grpc.Status;
import javax.annotation.Nullable;
public final class GracefulSwitchLoadBalancer extends ForwardingLoadBalancer {
    static final LoadBalancer.SubchannelPicker BUFFER_PICKER = new LoadBalancer.SubchannelPicker() {
        public String toString() {
            return "BUFFER_PICKER";
        }

        @Override
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }
    };
    @Nullable
    private LoadBalancer.Factory currentBalancerFactory;
    private LoadBalancer currentLb;
    private boolean currentLbIsReady;
    private final LoadBalancer defaultBalancer;
    private final LoadBalancer.Helper helper;
    @Nullable
    private LoadBalancer.Factory pendingBalancerFactory;
    private LoadBalancer pendingLb;
    private LoadBalancer.SubchannelPicker pendingPicker;
    private ConnectivityState pendingState;

    @Override
    protected LoadBalancer delegate() {
        LoadBalancer loadBalancer = this.pendingLb;
        return loadBalancer == this.defaultBalancer ? this.currentLb : loadBalancer;
    }

    public GracefulSwitchLoadBalancer(LoadBalancer.Helper helper) {
        LoadBalancer loadBalancer = new LoadBalancer() {
            @Override
            public void shutdown() {
            }

            @Override
            public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
                throw new IllegalStateException("GracefulSwitchLoadBalancer must switch to a load balancing policy before handling ResolvedAddresses");
            }

            @Override
            public void handleNameResolutionError(final Status status) {
                helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new LoadBalancer.SubchannelPicker() {
                    @Override
                    public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
                        return LoadBalancer.PickResult.withError(status);
                    }

                    public String toString() {
                        return MoreObjects.toStringHelper((Class<?>) 1ErrorPicker.class).add(Constants.IPC_BUNDLE_KEY_SEND_ERROR, status).toString();
                    }
                });
            }
        };
        this.defaultBalancer = loadBalancer;
        this.currentLb = loadBalancer;
        this.pendingLb = loadBalancer;
        this.helper = (LoadBalancer.Helper) Preconditions.checkNotNull(helper, "helper");
    }

    public void switchTo(LoadBalancer.Factory factory) {
        Preconditions.checkNotNull(factory, "newBalancerFactory");
        if (factory.equals(this.pendingBalancerFactory)) {
            return;
        }
        this.pendingLb.shutdown();
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
        this.pendingState = ConnectivityState.CONNECTING;
        this.pendingPicker = BUFFER_PICKER;
        if (factory.equals(this.currentBalancerFactory)) {
            return;
        }
        1PendingHelper r0 = new 1PendingHelper();
        r0.lb = factory.newLoadBalancer(r0);
        this.pendingLb = r0.lb;
        this.pendingBalancerFactory = factory;
        if (this.currentLbIsReady) {
            return;
        }
        swap();
    }

    class 1PendingHelper extends ForwardingLoadBalancerHelper {
        LoadBalancer lb;

        1PendingHelper() {
        }

        @Override
        protected LoadBalancer.Helper delegate() {
            return helper;
        }

        @Override
        public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
            if (this.lb == pendingLb) {
                Preconditions.checkState(currentLbIsReady, "there's pending lb while current lb has been out of READY");
                pendingState = connectivityState;
                pendingPicker = subchannelPicker;
                if (connectivityState == ConnectivityState.READY) {
                    swap();
                }
            } else if (this.lb == currentLb) {
                currentLbIsReady = connectivityState == ConnectivityState.READY;
                if (currentLbIsReady || pendingLb == defaultBalancer) {
                    helper.updateBalancingState(connectivityState, subchannelPicker);
                } else {
                    swap();
                }
            }
        }
    }

    public void swap() {
        this.helper.updateBalancingState(this.pendingState, this.pendingPicker);
        this.currentLb.shutdown();
        this.currentLb = this.pendingLb;
        this.currentBalancerFactory = this.pendingBalancerFactory;
        this.pendingLb = this.defaultBalancer;
        this.pendingBalancerFactory = null;
    }

    @Override
    @Deprecated
    public void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
        


return;

//throw new UnsupportedOperationException(
handleSubchannelState() is not supported by " + getClass().getName());
    }

    @Override
    public void shutdown() {
        this.pendingLb.shutdown();
        this.currentLb.shutdown();
    }
}
