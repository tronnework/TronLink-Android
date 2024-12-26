package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.Closeable;
import java.util.ArrayDeque;
import java.util.Queue;
final class MigratingThreadDeframer implements ThreadOptimizedDeframer {
    private final ApplicationThreadDeframerListener appListener;
    private final MessageDeframer deframer;
    private boolean deframerOnTransportThread;
    private boolean messageProducerEnqueued;
    private final MigratingDeframerListener migratingListener;
    private final ApplicationThreadDeframerListener.TransportExecutor transportExecutor;
    private final MessageDeframer.Listener transportListener;
    private final DeframeMessageProducer messageProducer = new DeframeMessageProducer();
    private final Object lock = new Object();
    private final Queue<Op> opQueue = new ArrayDeque();

    public interface Op {
        void run(boolean z);
    }

    static ApplicationThreadDeframerListener Fun$500(MigratingThreadDeframer migratingThreadDeframer) {
        return migratingThreadDeframer.appListener;
    }

    public MigratingThreadDeframer(MessageDeframer.Listener listener, ApplicationThreadDeframerListener.TransportExecutor transportExecutor, MessageDeframer messageDeframer) {
        SquelchLateMessagesAvailableDeframerListener squelchLateMessagesAvailableDeframerListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER));
        this.transportListener = squelchLateMessagesAvailableDeframerListener;
        this.transportExecutor = (ApplicationThreadDeframerListener.TransportExecutor) Preconditions.checkNotNull(transportExecutor, "transportExecutor");
        ApplicationThreadDeframerListener applicationThreadDeframerListener = new ApplicationThreadDeframerListener(squelchLateMessagesAvailableDeframerListener, transportExecutor);
        this.appListener = applicationThreadDeframerListener;
        MigratingDeframerListener migratingDeframerListener = new MigratingDeframerListener(applicationThreadDeframerListener);
        this.migratingListener = migratingDeframerListener;
        messageDeframer.setListener(migratingDeframerListener);
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

    private boolean runWhereAppropriate(Op op) {
        return runWhereAppropriate(op, true);
    }

    private boolean runWhereAppropriate(Op op, boolean z) {
        boolean z2;
        boolean z3;
        synchronized (this.lock) {
            z2 = this.deframerOnTransportThread;
            z3 = this.messageProducerEnqueued;
            if (!z2) {
                this.opQueue.offer(op);
                this.messageProducerEnqueued = true;
            }
        }
        if (z2) {
            op.run(true);
            return true;
        } else if (z3) {
            return false;
        } else {
            if (z) {
                PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                try {
                    this.transportListener.messagesAvailable(this.messageProducer);
                    return false;
                } finally {
                    PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                }
            }
            final Link linkOut = PerfMark.linkOut();
            this.transportExecutor.runOnTransportThread(new Runnable() {
                @Override
                public void run() {
                    PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                    PerfMark.linkIn(linkOut);
                    try {
                        transportListener.messagesAvailable(messageProducer);
                    } finally {
                        PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                    }
                }
            });
            return false;
        }
    }

    @Override
    public void request(final int i) {
        runWhereAppropriate(new Op() {
            @Override
            public void run(boolean z) {
                if (z) {
                    final Link linkOut = PerfMark.linkOut();
                    transportExecutor.runOnTransportThread(new Runnable() {
                        @Override
                        public void run() {
                            PerfMark.startTask("MigratingThreadDeframer.request");
                            PerfMark.linkIn(linkOut);
                            try {
                                requestFromTransportThread(i);
                            } finally {
                                PerfMark.stopTask("MigratingThreadDeframer.request");
                            }
                        }
                    });
                    return;
                }
                PerfMark.startTask("MigratingThreadDeframer.request");
                try {
                    deframer.request(i);
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }, false);
    }

    public void requestFromTransportThread(final int i) {
        runWhereAppropriate(new Op() {
            @Override
            public void run(boolean z) {
                if (z) {
                    try {
                        deframer.request(i);
                    } catch (Throwable th) {
                        appListener.deframeFailed(th);
                        deframer.close();
                    }
                    if (deframer.hasPendingDeliveries()) {
                        return;
                    }
                    synchronized (lock) {
                        PerfMark.event("MigratingThreadDeframer.deframerOnApplicationThread");
                        migratingListener.setDelegate(appListener);
                        deframerOnTransportThread = false;
                    }
                    return;
                }
                request(i);
            }
        });
    }

    class 1DeframeOp implements Op, Closeable {
        final ReadableBuffer val$data;

        1DeframeOp(ReadableBuffer readableBuffer) {
            this.val$data = readableBuffer;
        }

        @Override
        public void run(boolean z) {
            PerfMark.startTask("MigratingThreadDeframer.deframe");
            try {
                if (z) {
                    deframer.deframe(this.val$data);
                } else {
                    deframer.deframe(this.val$data);
                }
            } finally {
                PerfMark.stopTask("MigratingThreadDeframer.deframe");
            }
        }

        @Override
        public void close() {
            this.val$data.close();
        }
    }

    @Override
    public void deframe(ReadableBuffer readableBuffer) {
        runWhereAppropriate(new 1DeframeOp(readableBuffer));
    }

    @Override
    public void closeWhenComplete() {
        runWhereAppropriate(new Op() {
            @Override
            public void run(boolean z) {
                deframer.closeWhenComplete();
            }
        });
    }

    @Override
    public void close() {
        if (runWhereAppropriate(new Op() {
            @Override
            public void run(boolean z) {
                deframer.close();
            }
        })) {
            return;
        }
        this.deframer.stopDelivery();
    }

    public class DeframeMessageProducer implements StreamListener.MessageProducer, Closeable {
        DeframeMessageProducer() {
        }

        @Override
        public java.io.InputStream next() {
            


return null;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.internal.MigratingThreadDeframer.DeframeMessageProducer.next():java.io.InputStream");
        }

        @Override
        public void close() {
            Op op;
            while (true) {
                synchronized (lock) {
                    do {
                        op = (Op) opQueue.poll();
                        if (op == null) {
                            break;
                        }
                    } while (!(op instanceof Closeable));
                    if (op == null) {
                        messageProducerEnqueued = false;
                        return;
                    }
                }
                GrpcUtil.closeQuietly((Closeable) op);
            }
        }
    }

    static class MigratingDeframerListener extends ForwardingDeframerListener {
        private MessageDeframer.Listener delegate;

        @Override
        protected MessageDeframer.Listener delegate() {
            return this.delegate;
        }

        public MigratingDeframerListener(MessageDeframer.Listener listener) {
            setDelegate(listener);
        }

        public void setDelegate(MessageDeframer.Listener listener) {
            this.delegate = (MessageDeframer.Listener) Preconditions.checkNotNull(listener, "delegate");
        }
    }
}
