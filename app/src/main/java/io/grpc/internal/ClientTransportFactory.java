package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.HttpConnectProxiedSocketAddress;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
public interface ClientTransportFactory extends Closeable {
    @Override
    void close();

    ScheduledExecutorService getScheduledExecutorService();

    ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger);

    @CheckReturnValue
    @Nullable
    SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials);

    public static final class ClientTransportOptions {
        private ChannelLogger channelLogger;
        @Nullable
        private HttpConnectProxiedSocketAddress connectProxiedSocketAddr;
        @Nullable
        private String userAgent;
        private String authority = "unknown-authority";
        private Attributes eagAttributes = Attributes.EMPTY;

        public String getAuthority() {
            return this.authority;
        }

        public ChannelLogger getChannelLogger() {
            return this.channelLogger;
        }

        public Attributes getEagAttributes() {
            return this.eagAttributes;
        }

        @Nullable
        public HttpConnectProxiedSocketAddress getHttpConnectProxiedSocketAddress() {
            return this.connectProxiedSocketAddr;
        }

        @Nullable
        public String getUserAgent() {
            return this.userAgent;
        }

        public ClientTransportOptions setChannelLogger(ChannelLogger channelLogger) {
            this.channelLogger = channelLogger;
            return this;
        }

        public ClientTransportOptions setHttpConnectProxiedSocketAddress(@Nullable HttpConnectProxiedSocketAddress httpConnectProxiedSocketAddress) {
            this.connectProxiedSocketAddr = httpConnectProxiedSocketAddress;
            return this;
        }

        public ClientTransportOptions setUserAgent(@Nullable String str) {
            this.userAgent = str;
            return this;
        }

        public ClientTransportOptions setAuthority(String str) {
            this.authority = (String) Preconditions.checkNotNull(str, "authority");
            return this;
        }

        public ClientTransportOptions setEagAttributes(Attributes attributes) {
            Preconditions.checkNotNull(attributes, "eagAttributes");
            this.eagAttributes = attributes;
            return this;
        }

        public int hashCode() {
            return Objects.hashCode(this.authority, this.eagAttributes, this.userAgent, this.connectProxiedSocketAddr);
        }

        public boolean equals(Object obj) {
            if (obj instanceof ClientTransportOptions) {
                ClientTransportOptions clientTransportOptions = (ClientTransportOptions) obj;
                return this.authority.equals(clientTransportOptions.authority) && this.eagAttributes.equals(clientTransportOptions.eagAttributes) && Objects.equal(this.userAgent, clientTransportOptions.userAgent) && Objects.equal(this.connectProxiedSocketAddr, clientTransportOptions.connectProxiedSocketAddr);
            }
            return false;
        }
    }

    public static final class SwapChannelCredentialsResult {
        @Nullable
        final CallCredentials callCredentials;
        final ClientTransportFactory transportFactory;

        public SwapChannelCredentialsResult(ClientTransportFactory clientTransportFactory, @Nullable CallCredentials callCredentials) {
            this.transportFactory = (ClientTransportFactory) Preconditions.checkNotNull(clientTransportFactory, "transportFactory");
            this.callCredentials = callCredentials;
        }
    }
}
