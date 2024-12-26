package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ChannelLogger;
import io.grpc.ConnectivityState;
import io.grpc.ConnectivityStateInfo;
import io.grpc.EquivalentAddressGroup;
import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.ServiceConfigUtil;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
public final class AutoConfiguredLoadBalancerFactory {
    private final String defaultPolicy;
    private final LoadBalancerRegistry registry;

    public AutoConfiguredLoadBalancerFactory(String str) {
        this(LoadBalancerRegistry.getDefaultRegistry(), str);
    }

    AutoConfiguredLoadBalancerFactory(LoadBalancerRegistry loadBalancerRegistry, String str) {
        this.registry = (LoadBalancerRegistry) Preconditions.checkNotNull(loadBalancerRegistry, "registry");
        this.defaultPolicy = (String) Preconditions.checkNotNull(str, "defaultPolicy");
    }

    public AutoConfiguredLoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new AutoConfiguredLoadBalancer(helper);
    }

    public static final class NoopLoadBalancer extends LoadBalancer {
        @Override
        public void handleNameResolutionError(Status status) {
        }

        @Override
        @Deprecated
        public void handleResolvedAddressGroups(List<EquivalentAddressGroup> list, Attributes attributes) {
        }

        @Override
        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
        }

        @Override
        public void shutdown() {
        }

        private NoopLoadBalancer() {
        }
    }

    public final class AutoConfiguredLoadBalancer {
        private LoadBalancer delegate;
        private LoadBalancerProvider delegateProvider;
        private final LoadBalancer.Helper helper;

        public LoadBalancer getDelegate() {
            return this.delegate;
        }

        LoadBalancerProvider getDelegateProvider() {
            return this.delegateProvider;
        }

        void setDelegate(LoadBalancer loadBalancer) {
            this.delegate = loadBalancer;
        }

        AutoConfiguredLoadBalancer(LoadBalancer.Helper helper) {
            this.helper = helper;
            LoadBalancerProvider provider = registry.getProvider(defaultPolicy);
            this.delegateProvider = provider;
            if (provider == null) {
                throw new IllegalStateException("Could not find policy '" + defaultPolicy + "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files.");
            }
            this.delegate = provider.newLoadBalancer(helper);
        }

        public void handleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            tryHandleResolvedAddresses(resolvedAddresses);
        }

        public Status tryHandleResolvedAddresses(LoadBalancer.ResolvedAddresses resolvedAddresses) {
            List<EquivalentAddressGroup> addresses = resolvedAddresses.getAddresses();
            Attributes attributes = resolvedAddresses.getAttributes();
            ServiceConfigUtil.PolicySelection policySelection = (ServiceConfigUtil.PolicySelection) resolvedAddresses.getLoadBalancingPolicyConfig();
            if (policySelection == null) {
                try {
                    AutoConfiguredLoadBalancerFactory autoConfiguredLoadBalancerFactory = AutoConfiguredLoadBalancerFactory.this;
                    policySelection = new ServiceConfigUtil.PolicySelection(autoConfiguredLoadBalancerFactory.getProviderOrThrow(autoConfiguredLoadBalancerFactory.defaultPolicy, "using default policy"), null);
                } catch (PolicyException e) {
                    this.helper.updateBalancingState(ConnectivityState.TRANSIENT_FAILURE, new FailingPicker(Status.INTERNAL.withDescription(e.getMessage())));
                    this.delegate.shutdown();
                    this.delegateProvider = null;
                    this.delegate = new NoopLoadBalancer();
                    return Status.OK;
                }
            }
            if (this.delegateProvider == null || !policySelection.provider.getPolicyName().equals(this.delegateProvider.getPolicyName())) {
                this.helper.updateBalancingState(ConnectivityState.CONNECTING, new EmptyPicker());
                this.delegate.shutdown();
                LoadBalancerProvider loadBalancerProvider = policySelection.provider;
                this.delegateProvider = loadBalancerProvider;
                LoadBalancer loadBalancer = this.delegate;
                this.delegate = loadBalancerProvider.newLoadBalancer(this.helper);
                this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.INFO, "Load balancer changed from {0} to {1}", loadBalancer.getClass().getSimpleName(), this.delegate.getClass().getSimpleName());
            }
            Object obj = policySelection.config;
            if (obj != null) {
                this.helper.getChannelLogger().log(ChannelLogger.ChannelLogLevel.DEBUG, "Load-balancing config: {0}", policySelection.config);
            }
            LoadBalancer delegate = getDelegate();
            if (resolvedAddresses.getAddresses().isEmpty() && !delegate.canHandleEmptyAddressListFromNameResolution()) {
                Status status = Status.UNAVAILABLE;
                return status.withDescription("NameResolver returned no usable address. addrs=" + addresses + ", attrs=" + attributes);
            }
            delegate.handleResolvedAddresses(LoadBalancer.ResolvedAddresses.newBuilder().setAddresses(resolvedAddresses.getAddresses()).setAttributes(attributes).setLoadBalancingPolicyConfig(obj).build());
            return Status.OK;
        }

        public void handleNameResolutionError(Status status) {
            getDelegate().handleNameResolutionError(status);
        }

        @Deprecated
        void handleSubchannelState(LoadBalancer.Subchannel subchannel, ConnectivityStateInfo connectivityStateInfo) {
            getDelegate().handleSubchannelState(subchannel, connectivityStateInfo);
        }

        public void requestConnection() {
            getDelegate().requestConnection();
        }

        public void shutdown() {
            this.delegate.shutdown();
            this.delegate = null;
        }
    }

    public LoadBalancerProvider getProviderOrThrow(String str, String str2) throws PolicyException {
        LoadBalancerProvider provider = this.registry.getProvider(str);
        if (provider != null) {
            return provider;
        }
        throw new PolicyException("Trying to load '" + str + "' because " + str2 + ", but it's unavailable");
    }

    @Nullable
    public NameResolver.ConfigOrError parseLoadBalancerPolicy(Map<String, ?> map) {
        List<ServiceConfigUtil.LbConfig> unwrapLoadBalancingConfigList;
        if (map != null) {
            try {
                unwrapLoadBalancingConfigList = ServiceConfigUtil.unwrapLoadBalancingConfigList(ServiceConfigUtil.getLoadBalancingConfigsFromServiceConfig(map));
            } catch (RuntimeException e) {
                return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("can't parse load balancer configuration").withCause(e));
            }
        } else {
            unwrapLoadBalancingConfigList = null;
        }
        if (unwrapLoadBalancingConfigList == null || unwrapLoadBalancingConfigList.isEmpty()) {
            return null;
        }
        return ServiceConfigUtil.selectLbPolicyFromList(unwrapLoadBalancingConfigList, this.registry);
    }

    public static final class PolicyException extends Exception {
        private static final long serialVersionUID = 1;

        private PolicyException(String str) {
            super(str);
        }
    }

    public static final class EmptyPicker extends LoadBalancer.SubchannelPicker {
        private EmptyPicker() {
        }

        @Override
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withNoResult();
        }

        public String toString() {
            return MoreObjects.toStringHelper((Class<?>) EmptyPicker.class).toString();
        }
    }

    public static final class FailingPicker extends LoadBalancer.SubchannelPicker {
        private final Status failure;

        FailingPicker(Status status) {
            this.failure = status;
        }

        @Override
        public LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return LoadBalancer.PickResult.withError(this.failure);
        }
    }
}
