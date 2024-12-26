package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolver;
import io.grpc.NameResolverRegistry;
import io.grpc.SynchronizationContext;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
public abstract class ForwardingLoadBalancerHelper extends LoadBalancer.Helper {
    protected abstract LoadBalancer.Helper delegate();

    @Override
    public LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        return delegate().createSubchannel(createSubchannelArgs);
    }

    @Override
    public ManagedChannel createOobChannel(EquivalentAddressGroup equivalentAddressGroup, String str) {
        return delegate().createOobChannel(equivalentAddressGroup, str);
    }

    @Override
    public ManagedChannel createOobChannel(List<EquivalentAddressGroup> list, String str) {
        return delegate().createOobChannel(list, str);
    }

    @Override
    public void updateOobChannelAddresses(ManagedChannel managedChannel, EquivalentAddressGroup equivalentAddressGroup) {
        delegate().updateOobChannelAddresses(managedChannel, equivalentAddressGroup);
    }

    @Override
    public void updateOobChannelAddresses(ManagedChannel managedChannel, List<EquivalentAddressGroup> list) {
        delegate().updateOobChannelAddresses(managedChannel, list);
    }

    @Override
    @Deprecated
    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str) {
        return delegate().createResolvingOobChannelBuilder(str);
    }

    @Override
    public ManagedChannelBuilder<?> createResolvingOobChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return delegate().createResolvingOobChannelBuilder(str, channelCredentials);
    }

    @Override
    public ManagedChannel createResolvingOobChannel(String str) {
        return delegate().createResolvingOobChannel(str);
    }

    @Override
    public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        delegate().updateBalancingState(connectivityState, subchannelPicker);
    }

    @Override
    public void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    @Override
    public void ignoreRefreshNameResolutionCheck() {
        delegate().ignoreRefreshNameResolutionCheck();
    }

    @Override
    public String getAuthority() {
        return delegate().getAuthority();
    }

    @Override
    public ChannelCredentials getChannelCredentials() {
        return delegate().getChannelCredentials();
    }

    @Override
    public ChannelCredentials getUnsafeChannelCredentials() {
        return delegate().getUnsafeChannelCredentials();
    }

    @Override
    public SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    @Override
    public ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    @Override
    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    @Override
    public NameResolver.Args getNameResolverArgs() {
        return delegate().getNameResolverArgs();
    }

    @Override
    public NameResolverRegistry getNameResolverRegistry() {
        return delegate().getNameResolverRegistry();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
