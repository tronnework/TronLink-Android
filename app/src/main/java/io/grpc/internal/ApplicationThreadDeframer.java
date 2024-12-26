package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;
public class ApplicationThreadDeframer implements Deframer {
    private final ApplicationThreadDeframerListener appListener;
    private final MessageDeframer deframer;
    private final MessageDeframer.Listener storedListener;

    interface TransportExecutor extends ApplicationThreadDeframerListener.TransportExecutor {
    }

    MessageDeframer.Listener getAppListener() {
        return this.appListener;
    }

    public ApplicationThreadDeframer(MessageDeframer.Listener listener, TransportExecutor transportExecutor, MessageDeframer messageDeframer) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.storedListener = squelchLateMessagesAvailableDeframerListener;
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor);
        this.appListener = applicationThreadDeframerListener;
        messageDeframer.setListener(applicationThreadDeframerListener);
        this.deframer = messageDeframer;
    }

    @Override
    public void setMaxInboundMessageSize(int i) {
        this.deframer.setMaxInboundMessageSize(i);
    }

    @Override
    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    @Override
    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
    }

    @Override
    public void request(final int i) {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            @Override
            public void run() {
                if (deframer.isClosed()) {
                    return;
                }
                try {
                    deframer.request(i);
                } catch (Throwable th) {
                    appListener.deframeFailed(th);
                    deframer.close();
                }
            }
        }));
    }

    @Override
    public void deframe(final ReadableBuffer readableBuffer) {
        this.storedListener.messagesAvailable(new CloseableInitializingMessageProducer(new Runnable() {
            @Override
            public void run() {
                try {
                    deframer.deframe(readableBuffer);
                } catch (Throwable th) {
                    appListener.deframeFailed(th);
                    deframer.close();
                }
            }
        }, new Closeable() {
            @Override
            public void close() {
                readableBuffer.close();
            }
        }));
    }

    @Override
    public void closeWhenComplete() {
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            @Override
            public void run() {
                deframer.closeWhenComplete();
            }
        }));
    }

    @Override
    public void close() {
        this.deframer.stopDelivery();
        this.storedListener.messagesAvailable(new InitializingMessageProducer(new Runnable() {
            @Override
            public void run() {
                deframer.close();
            }
        }));
    }

    private class InitializingMessageProducer implements StreamListener.MessageProducer {
        private boolean initialized;
        private final Runnable runnable;

        private InitializingMessageProducer(Runnable runnable) {
            this.initialized = false;
            this.runnable = runnable;
        }

        private void initialize() {
            if (this.initialized) {
                return;
            }
            this.runnable.run();
            this.initialized = true;
        }

        @Override
        @Nullable
        public InputStream next() {
            initialize();
            return appListener.messageReadQueuePoll();
        }
    }

    private class CloseableInitializingMessageProducer extends InitializingMessageProducer implements Closeable {
        private final Closeable closeable;

        public CloseableInitializingMessageProducer(Runnable runnable, Closeable closeable) {
            super(runnable);
            this.closeable = closeable;
        }

        @Override
        public void close() throws IOException {
            this.closeable.close();
        }
    }
}
