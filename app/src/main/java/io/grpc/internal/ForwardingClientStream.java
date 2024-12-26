package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;
import java.io.InputStream;
abstract class ForwardingClientStream implements ClientStream {
    protected abstract ClientStream delegate();

    @Override
    public void request(int i) {
        delegate().request(i);
    }

    @Override
    public void writeMessage(InputStream inputStream) {
        delegate().writeMessage(inputStream);
    }

    @Override
    public void flush() {
        delegate().flush();
    }

    @Override
    public boolean isReady() {
        return delegate().isReady();
    }

    @Override
    public void optimizeForDirectExecutor() {
        delegate().optimizeForDirectExecutor();
    }

    @Override
    public void setCompressor(Compressor compressor) {
        delegate().setCompressor(compressor);
    }

    @Override
    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    @Override
    public void cancel(Status status) {
        delegate().cancel(status);
    }

    @Override
    public void halfClose() {
        delegate().halfClose();
    }

    @Override
    public void setAuthority(String str) {
        delegate().setAuthority(str);
    }

    @Override
    public void setFullStreamDecompression(boolean z) {
        delegate().setFullStreamDecompression(z);
    }

    @Override
    public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        delegate().setDecompressorRegistry(decompressorRegistry);
    }

    @Override
    public void start(ClientStreamListener clientStreamListener) {
        delegate().start(clientStreamListener);
    }

    @Override
    public void setMaxInboundMessageSize(int i) {
        delegate().setMaxInboundMessageSize(i);
    }

    @Override
    public void setMaxOutboundMessageSize(int i) {
        delegate().setMaxOutboundMessageSize(i);
    }

    @Override
    public void setDeadline(Deadline deadline) {
        delegate().setDeadline(deadline);
    }

    @Override
    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }

    @Override
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        delegate().appendTimeoutInsight(insightBuilder);
    }
}
