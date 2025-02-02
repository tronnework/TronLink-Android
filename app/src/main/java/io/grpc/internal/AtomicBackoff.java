package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
public final class AtomicBackoff {
    private static final Logger log = Logger.getLogger(AtomicBackoff.class.getName());
    private final String name;
    private final AtomicLong value;

    public AtomicBackoff(String str, long j) {
        AtomicLong atomicLong = new AtomicLong();
        this.value = atomicLong;
        Preconditions.checkArgument(j > 0, "value must be positive");
        this.name = str;
        atomicLong.set(j);
    }

    public State getState() {
        return new State(this.value.get());
    }

    public final class State {
        static final boolean $assertionsDisabled = false;
        private final long savedValue;

        public long get() {
            return this.savedValue;
        }

        private State(long j) {
            this.savedValue = j;
        }

        public void backoff() {
            long j = this.savedValue;
            long max = Math.max(2 * j, j);
            if (value.compareAndSet(this.savedValue, max)) {
                AtomicBackoff.log.log(Level.WARNING, "Increased {0} to {1}", new Object[]{name, Long.valueOf(max)});
            }
        }
    }
}
