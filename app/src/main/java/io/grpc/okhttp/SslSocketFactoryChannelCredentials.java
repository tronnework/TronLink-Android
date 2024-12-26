package io.grpc.okhttp;

import com.google.common.base.Preconditions;
import javax.net.ssl.SSLSocketFactory;
public final class SslSocketFactoryChannelCredentials {
    private SslSocketFactoryChannelCredentials() {
    }

    public static io.grpc.ChannelCredentials create(SSLSocketFactory sSLSocketFactory) {
        return new ChannelCredentials(sSLSocketFactory);
    }

    static final class ChannelCredentials extends io.grpc.ChannelCredentials {
        private final SSLSocketFactory factory;

        public SSLSocketFactory getFactory() {
            return this.factory;
        }

        @Override
        public io.grpc.ChannelCredentials withoutBearerTokens() {
            return this;
        }

        private ChannelCredentials(SSLSocketFactory sSLSocketFactory) {
            this.factory = (SSLSocketFactory) Preconditions.checkNotNull(sSLSocketFactory, "factory");
        }
    }
}
