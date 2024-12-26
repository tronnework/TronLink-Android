package io.grpc.internal;

import com.google.common.base.Objects;
import io.grpc.Attributes;
import io.grpc.MethodDescriptor;
import io.grpc.ServerStreamTracer;
import javax.annotation.Nullable;
final class ServerCallInfoImpl<ReqT, RespT> extends ServerStreamTracer.ServerCallInfo<ReqT, RespT> {
    private final Attributes attributes;
    private final String authority;
    private final MethodDescriptor<ReqT, RespT> methodDescriptor;

    @Override
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override
    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.methodDescriptor;
    }

    public ServerCallInfoImpl(MethodDescriptor<ReqT, RespT> methodDescriptor, Attributes attributes, @Nullable String str) {
        this.methodDescriptor = methodDescriptor;
        this.attributes = attributes;
        this.authority = str;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ServerCallInfoImpl) {
            ServerCallInfoImpl serverCallInfoImpl = (ServerCallInfoImpl) obj;
            return Objects.equal(this.methodDescriptor, serverCallInfoImpl.methodDescriptor) && Objects.equal(this.attributes, serverCallInfoImpl.attributes) && Objects.equal(this.authority, serverCallInfoImpl.authority);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.methodDescriptor, this.attributes, this.authority);
    }
}
