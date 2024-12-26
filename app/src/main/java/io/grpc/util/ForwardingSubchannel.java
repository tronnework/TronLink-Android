package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.Attributes;
import io.grpc.Channel;
import io.grpc.ChannelLogger;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import java.util.List;
public abstract class ForwardingSubchannel extends LoadBalancer.Subchannel {
    protected abstract LoadBalancer.Subchannel delegate();

    @Override
    public void start(LoadBalancer.SubchannelStateListener subchannelStateListener) {
        delegate().start(subchannelStateListener);
    }

    @Override
    public void shutdown() {
        delegate().shutdown();
    }

    @Override
    public void requestConnection() {
        delegate().requestConnection();
    }

    @Override
    public List<EquivalentAddressGroup> getAllAddresses() {
        return delegate().getAllAddresses();
    }

    @Override
    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    @Override
    public Channel asChannel() {
        return delegate().asChannel();
    }

    @Override
    public ChannelLogger getChannelLogger() {
        return delegate().getChannelLogger();
    }

    @Override
    public Object getInternalSubchannel() {
        return delegate().getInternalSubchannel();
    }

    @Override
    public void updateAddresses(List<EquivalentAddressGroup> list) {
        delegate().updateAddresses(list);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
