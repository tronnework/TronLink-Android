package io.grpc.util;

import com.google.common.base.MoreObjects;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;
public abstract class ForwardingClientStreamTracer extends ClientStreamTracer {
    protected abstract ClientStreamTracer delegate();

    @Override
    public void outboundHeaders() {
        delegate().outboundHeaders();
    }

    @Override
    public void inboundHeaders() {
        delegate().inboundHeaders();
    }

    @Override
    public void inboundTrailers(Metadata metadata) {
        delegate().inboundTrailers(metadata);
    }

    @Override
    public void streamClosed(Status status) {
        delegate().streamClosed(status);
    }

    @Override
    public void outboundMessage(int i) {
        delegate().outboundMessage(i);
    }

    @Override
    public void inboundMessage(int i) {
        delegate().inboundMessage(i);
    }

    @Override
    public void outboundMessageSent(int i, long j, long j2) {
        delegate().outboundMessageSent(i, j, j2);
    }

    @Override
    public void inboundMessageRead(int i, long j, long j2) {
        delegate().inboundMessageRead(i, j, j2);
    }

    @Override
    public void outboundWireSize(long j) {
        delegate().outboundWireSize(j);
    }

    @Override
    public void outboundUncompressedSize(long j) {
        delegate().outboundUncompressedSize(j);
    }

    @Override
    public void inboundWireSize(long j) {
        delegate().inboundWireSize(j);
    }

    @Override
    public void inboundUncompressedSize(long j) {
        delegate().inboundUncompressedSize(j);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
