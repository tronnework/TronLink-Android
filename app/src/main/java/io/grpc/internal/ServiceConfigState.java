package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.NameResolver;
import javax.annotation.Nullable;
final class ServiceConfigState {
    static final boolean $assertionsDisabled = false;
    @Nullable
    private NameResolver.ConfigOrError currentServiceConfigOrError;
    @Nullable
    private final NameResolver.ConfigOrError defaultServiceConfig;
    private final boolean lookUpServiceConfig;
    private boolean updated;

    boolean expectUpdates() {
        return this.lookUpServiceConfig;
    }

    ServiceConfigState(@Nullable ManagedChannelServiceConfig managedChannelServiceConfig, boolean z) {
        if (managedChannelServiceConfig == null) {
            this.defaultServiceConfig = null;
        } else {
            this.defaultServiceConfig = NameResolver.ConfigOrError.fromConfig(managedChannelServiceConfig);
        }
        this.lookUpServiceConfig = z;
        if (z) {
            return;
        }
        this.currentServiceConfigOrError = this.defaultServiceConfig;
    }

    boolean shouldWaitOnServiceConfig() {
        return !this.updated && expectUpdates();
    }

    @Nullable
    NameResolver.ConfigOrError getCurrent() {
        Preconditions.checkState(!shouldWaitOnServiceConfig(), "still waiting on service config");
        return this.currentServiceConfigOrError;
    }

    void update(@Nullable NameResolver.ConfigOrError configOrError) {
        Preconditions.checkState(expectUpdates(), "unexpected service config update");
        boolean z = !this.updated;
        this.updated = true;
        if (z) {
            if (configOrError == null) {
                this.currentServiceConfigOrError = this.defaultServiceConfig;
            } else if (configOrError.getError() == null) {
                this.currentServiceConfigOrError = configOrError;
            } else {
                NameResolver.ConfigOrError configOrError2 = this.defaultServiceConfig;
                if (configOrError2 != null) {
                    this.currentServiceConfigOrError = configOrError2;
                } else {
                    this.currentServiceConfigOrError = configOrError;
                }
            }
        } else if (configOrError == null) {
            NameResolver.ConfigOrError configOrError3 = this.defaultServiceConfig;
            if (configOrError3 != null) {
                this.currentServiceConfigOrError = configOrError3;
            } else {
                this.currentServiceConfigOrError = null;
            }
        } else if (configOrError.getError() == null) {
            this.currentServiceConfigOrError = configOrError;
        } else {
            NameResolver.ConfigOrError configOrError4 = this.currentServiceConfigOrError;
            if (configOrError4 == null || configOrError4.getError() == null) {
                return;
            }
            this.currentServiceConfigOrError = configOrError;
        }
    }
}
