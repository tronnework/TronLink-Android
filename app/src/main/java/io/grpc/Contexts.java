package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ForwardingServerCallListener;
import io.grpc.ServerCall;
import io.grpc.Status;
import java.util.concurrent.TimeoutException;
public final class Contexts {
    private Contexts() {
    }

    public static <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(Context context, ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        Context attach = context.attach();
        try {
            return new ContextualizedServerCallListener(serverCallHandler.startCall(serverCall, metadata), context);
        } finally {
            context.detach(attach);
        }
    }

    private static class ContextualizedServerCallListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
        private final Context context;

        public ContextualizedServerCallListener(ServerCall.Listener<ReqT> listener, Context context) {
            super(listener);
            this.context = context;
        }

        @Override
        public void onMessage(ReqT reqt) {
            Context attach = this.context.attach();
            try {
                super.onMessage(reqt);
            } finally {
                this.context.detach(attach);
            }
        }

        @Override
        public void onHalfClose() {
            Context attach = this.context.attach();
            try {
                super.onHalfClose();
            } finally {
                this.context.detach(attach);
            }
        }

        @Override
        public void onCancel() {
            Context attach = this.context.attach();
            try {
                super.onCancel();
            } finally {
                this.context.detach(attach);
            }
        }

        @Override
        public void onComplete() {
            Context attach = this.context.attach();
            try {
                super.onComplete();
            } finally {
                this.context.detach(attach);
            }
        }

        @Override
        public void onReady() {
            Context attach = this.context.attach();
            try {
                super.onReady();
            } finally {
                this.context.detach(attach);
            }
        }
    }

    public static Status statusFromCancelled(Context context) {
        Preconditions.checkNotNull(context, "context must not be null");
        if (context.isCancelled()) {
            Throwable cancellationCause = context.cancellationCause();
            if (cancellationCause == null) {
                return Status.CANCELLED.withDescription("io.grpc.Context was cancelled without error");
            }
            if (cancellationCause instanceof TimeoutException) {
                return Status.DEADLINE_EXCEEDED.withDescription(cancellationCause.getMessage()).withCause(cancellationCause);
            }
            Status fromThrowable = Status.fromThrowable(cancellationCause);
            if (Status.Code.UNKNOWN.equals(fromThrowable.getCode()) && fromThrowable.getCause() == cancellationCause) {
                return Status.CANCELLED.withDescription("Context cancelled").withCause(cancellationCause);
            }
            return fromThrowable.withCause(cancellationCause);
        }
        return null;
    }
}
