package io.grpc;

import com.google.common.base.Preconditions;
public final class CompositeChannelCredentials extends ChannelCredentials {
    private final CallCredentials callCredentials;
    private final ChannelCredentials channelCredentials;

    public CallCredentials getCallCredentials() {
        return this.callCredentials;
    }

    public ChannelCredentials getChannelCredentials() {
        return this.channelCredentials;
    }

    public static ChannelCredentials create(ChannelCredentials channelCredentials, CallCredentials callCredentials) {
        return new CompositeChannelCredentials(channelCredentials, callCredentials);
    }

    private CompositeChannelCredentials(ChannelCredentials channelCredentials, CallCredentials callCredentials) {
        this.channelCredentials = (ChannelCredentials) Preconditions.checkNotNull(channelCredentials, "channelCreds");
        this.callCredentials = (CallCredentials) Preconditions.checkNotNull(callCredentials, "callCreds");
    }

    @Override
    public ChannelCredentials withoutBearerTokens() {
        return this.channelCredentials.withoutBearerTokens();
    }
}
