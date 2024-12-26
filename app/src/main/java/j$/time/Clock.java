package j$.time;

import java.io.ObjectInputStream;
import java.io.Serializable;
import okhttp3.internal.ws.RealWebSocket;
public abstract class Clock {

    public static final class SystemClock extends Clock implements Serializable {
        private static final long OFFSET_SEED = (System.currentTimeMillis() / 1000) - RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        static final SystemClock UTC = new SystemClock(ZoneOffset.UTC);
        private static final long serialVersionUID = 6740630888130243051L;
        private final ZoneId zone;

        SystemClock(ZoneId zoneId) {
            this.zone = zoneId;
        }

        private void readObject(ObjectInputStream objectInputStream) {
            objectInputStream.defaultReadObject();
        }

        public boolean equals(Object obj) {
            if (obj instanceof SystemClock) {
                return this.zone.equals(((SystemClock) obj).zone);
            }
            return false;
        }

        @Override
        public ZoneId getZone() {
            return this.zone;
        }

        public int hashCode() {
            return this.zone.hashCode() + 1;
        }

        @Override
        public Instant instant() {
            return Instant.ofEpochMilli(millis());
        }

        @Override
        public long millis() {
            return System.currentTimeMillis();
        }

        public String toString() {
            ZoneId zoneId = this.zone;
            return "SystemClock[" + zoneId + "]";
        }
    }

    protected Clock() {
    }

    public static Clock systemDefaultZone() {
        return new SystemClock(ZoneId.systemDefault());
    }

    public static Clock systemUTC() {
        return SystemClock.UTC;
    }

    public abstract ZoneId getZone();

    public abstract Instant instant();

    public abstract long millis();
}
