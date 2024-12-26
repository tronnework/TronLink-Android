package io.grpc.internal;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.tron.wallet.business.pull.PullConstants;
import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.StreamListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.CheckReturnValue;
class DelayedStream implements ClientStream {
    static final boolean $assertionsDisabled = false;
    private DelayedStreamListener delayedListener;
    private Status error;
    private ClientStreamListener listener;
    private volatile boolean passThrough;
    private List<Runnable> pendingCalls = new ArrayList();
    private List<Runnable> preStartPendingCalls = new ArrayList();
    private ClientStream realStream;
    private long startTimeNanos;
    private long streamSetTimeNanos;

    ClientStream getRealStream() {
        return this.realStream;
    }

    @Override
    public void setMaxInboundMessageSize(final int i) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setMaxInboundMessageSize(i);
            }
        });
    }

    @Override
    public void setMaxOutboundMessageSize(final int i) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setMaxOutboundMessageSize(i);
            }
        });
    }

    @Override
    public void setDeadline(final Deadline deadline) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setDeadline(deadline);
            }
        });
    }

    @Override
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        synchronized (this) {
            if (this.listener == null) {
                return;
            }
            if (this.realStream != null) {
                insightBuilder.appendKeyValue("buffered_nanos", Long.valueOf(this.streamSetTimeNanos - this.startTimeNanos));
                this.realStream.appendTimeoutInsight(insightBuilder);
            } else {
                insightBuilder.appendKeyValue("buffered_nanos", Long.valueOf(System.nanoTime() - this.startTimeNanos));
                insightBuilder.append("waiting_for_connection");
            }
        }
    }

    @CheckReturnValue
    public final Runnable setStream(ClientStream clientStream) {
        synchronized (this) {
            if (this.realStream != null) {
                return null;
            }
            setRealStream((ClientStream) Preconditions.checkNotNull(clientStream, "stream"));
            ClientStreamListener clientStreamListener = this.listener;
            if (clientStreamListener == null) {
                this.pendingCalls = null;
                this.passThrough = true;
            }
            if (clientStreamListener == null) {
                return null;
            }
            internalStart(clientStreamListener);
            return new Runnable() {
                @Override
                public void run() {
                    drainPendingCalls();
                }
            };
        }
    }

    public void drainPendingCalls() {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: io.grpc.internal.DelayedStream.drainPendingCalls():void");
    }

    private void delayOrExecute(Runnable runnable) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        synchronized (this) {
            if (!this.passThrough) {
                this.pendingCalls.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    @Override
    public void setAuthority(final String str) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(str, "authority");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setAuthority(str);
            }
        });
    }

    @Override
    public void start(ClientStreamListener clientStreamListener) {
        Status status;
        boolean z;
        Preconditions.checkNotNull(clientStreamListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkState(this.listener == null, "already started");
        synchronized (this) {
            status = this.error;
            z = this.passThrough;
            if (!z) {
                DelayedStreamListener delayedStreamListener = new DelayedStreamListener(clientStreamListener);
                this.delayedListener = delayedStreamListener;
                clientStreamListener = delayedStreamListener;
            }
            this.listener = clientStreamListener;
            this.startTimeNanos = System.nanoTime();
        }
        if (status != null) {
            clientStreamListener.closed(status, new Metadata());
        } else if (z) {
            internalStart(clientStreamListener);
        }
    }

    private void internalStart(ClientStreamListener clientStreamListener) {
        for (Runnable runnable : this.preStartPendingCalls) {
            runnable.run();
        }
        this.preStartPendingCalls = null;
        this.realStream.start(clientStreamListener);
    }

    @Override
    public Attributes getAttributes() {
        ClientStream clientStream;
        synchronized (this) {
            clientStream = this.realStream;
        }
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    @Override
    public void writeMessage(final InputStream inputStream) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        Preconditions.checkNotNull(inputStream, PullConstants.RESULT_MESSAGE);
        if (this.passThrough) {
            this.realStream.writeMessage(inputStream);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realStream.writeMessage(inputStream);
                }
            });
        }
    }

    @Override
    public void flush() {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.flush();
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realStream.flush();
                }
            });
        }
    }

    @Override
    public void cancel(final Status status) {
        boolean z = true;
        Preconditions.checkState(this.listener != null, "May only be called after start");
        Preconditions.checkNotNull(status, "reason");
        synchronized (this) {
            if (this.realStream == null) {
                setRealStream(NoopClientStream.INSTANCE);
                this.error = status;
                z = false;
            }
        }
        if (z) {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realStream.cancel(status);
                }
            });
            return;
        }
        drainPendingCalls();
        this.listener.closed(status, new Metadata());
    }

    private void setRealStream(ClientStream clientStream) {
        ClientStream clientStream2 = this.realStream;
        Preconditions.checkState(clientStream2 == null, "realStream already set to %s", clientStream2);
        this.realStream = clientStream;
        this.streamSetTimeNanos = System.nanoTime();
    }

    @Override
    public void halfClose() {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        delayOrExecute(new Runnable() {
            @Override
            public void run() {
                realStream.halfClose();
            }
        });
    }

    @Override
    public void request(final int i) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.request(i);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realStream.request(i);
                }
            });
        }
    }

    @Override
    public void optimizeForDirectExecutor() {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.optimizeForDirectExecutor();
            }
        });
    }

    @Override
    public void setCompressor(final Compressor compressor) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(compressor, "compressor");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setCompressor(compressor);
            }
        });
    }

    @Override
    public void setFullStreamDecompression(final boolean z) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setFullStreamDecompression(z);
            }
        });
    }

    @Override
    public void setDecompressorRegistry(final DecompressorRegistry decompressorRegistry) {
        Preconditions.checkState(this.listener == null, "May only be called before start");
        Preconditions.checkNotNull(decompressorRegistry, "decompressorRegistry");
        this.preStartPendingCalls.add(new Runnable() {
            @Override
            public void run() {
                realStream.setDecompressorRegistry(decompressorRegistry);
            }
        });
    }

    @Override
    public boolean isReady() {
        if (this.passThrough) {
            return this.realStream.isReady();
        }
        return false;
    }

    @Override
    public void setMessageCompression(final boolean z) {
        Preconditions.checkState(this.listener != null, "May only be called after start");
        if (this.passThrough) {
            this.realStream.setMessageCompression(z);
        } else {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    realStream.setMessageCompression(z);
                }
            });
        }
    }

    public static class DelayedStreamListener implements ClientStreamListener {
        static final boolean $assertionsDisabled = false;
        private volatile boolean passThrough;
        private List<Runnable> pendingCallbacks = new ArrayList();
        private final ClientStreamListener realListener;

        public DelayedStreamListener(ClientStreamListener clientStreamListener) {
            this.realListener = clientStreamListener;
        }

        private void delayOrExecute(Runnable runnable) {
            synchronized (this) {
                if (!this.passThrough) {
                    this.pendingCallbacks.add(runnable);
                } else {
                    runnable.run();
                }
            }
        }

        @Override
        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            if (this.passThrough) {
                this.realListener.messagesAvailable(messageProducer);
            } else {
                delayOrExecute(new Runnable() {
                    @Override
                    public void run() {
                        DelayedStreamListener.this.realListener.messagesAvailable(messageProducer);
                    }
                });
            }
        }

        @Override
        public void onReady() {
            if (this.passThrough) {
                this.realListener.onReady();
            } else {
                delayOrExecute(new Runnable() {
                    @Override
                    public void run() {
                        DelayedStreamListener.this.realListener.onReady();
                    }
                });
            }
        }

        @Override
        public void headersRead(final Metadata metadata) {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    DelayedStreamListener.this.realListener.headersRead(metadata);
                }
            });
        }

        @Override
        public void closed(final Status status, final Metadata metadata) {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    DelayedStreamListener.this.realListener.closed(status, metadata);
                }
            });
        }

        @Override
        public void closed(final Status status, final ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            delayOrExecute(new Runnable() {
                @Override
                public void run() {
                    DelayedStreamListener.this.realListener.closed(status, rpcProgress, metadata);
                }
            });
        }

        public void drainPendingCallbacks() {
            List<Runnable> list;
            List arrayList = new ArrayList();
            while (true) {
                synchronized (this) {
                    if (this.pendingCallbacks.isEmpty()) {
                        this.pendingCallbacks = null;
                        this.passThrough = true;
                        return;
                    }
                    list = this.pendingCallbacks;
                    this.pendingCallbacks = arrayList;
                }
                for (Runnable runnable : list) {
                    runnable.run();
                }
                list.clear();
                arrayList = list;
            }
        }
    }
}
