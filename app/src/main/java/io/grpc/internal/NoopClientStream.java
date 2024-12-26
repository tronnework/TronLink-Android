package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;
import java.io.InputStream;
import javax.annotation.Nonnull;
public class NoopClientStream implements ClientStream {
    public static final NoopClientStream INSTANCE = new NoopClientStream();

    @Override
    public void cancel(Status status) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void halfClose() {
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void optimizeForDirectExecutor() {
    }

    @Override
    public void request(int i) {
    }

    @Override
    public void setAuthority(String str) {
    }

    @Override
    public void setCompressor(Compressor compressor) {
    }

    @Override
    public void setDeadline(@Nonnull Deadline deadline) {
    }

    @Override
    public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
    }

    @Override
    public void setFullStreamDecompression(boolean z) {
    }

    @Override
    public void setMaxInboundMessageSize(int i) {
    }

    @Override
    public void setMaxOutboundMessageSize(int i) {
    }

    @Override
    public void setMessageCompression(boolean z) {
    }

    @Override
    public void start(ClientStreamListener clientStreamListener) {
    }

    @Override
    public void writeMessage(InputStream inputStream) {
    }

    @Override
    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    @Override
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.append("noop");
    }
}
