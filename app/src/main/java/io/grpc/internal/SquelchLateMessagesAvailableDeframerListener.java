package io.grpc.internal;

import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
final class SquelchLateMessagesAvailableDeframerListener extends ForwardingDeframerListener {
    private boolean closed;
    private final MessageDeframer.Listener delegate;

    @Override
    protected MessageDeframer.Listener delegate() {
        return this.delegate;
    }

    public SquelchLateMessagesAvailableDeframerListener(MessageDeframer.Listener listener) {
        this.delegate = listener;
    }

    @Override
    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        if (this.closed) {
            if (messageProducer instanceof Closeable) {
                GrpcUtil.closeQuietly((Closeable) messageProducer);
                return;
            }
            return;
        }
        super.messagesAvailable(messageProducer);
    }

    @Override
    public void deframerClosed(boolean z) {
        this.closed = true;
        super.deframerClosed(z);
    }

    @Override
    public void deframeFailed(Throwable th) {
        this.closed = true;
        super.deframeFailed(th);
    }
}
