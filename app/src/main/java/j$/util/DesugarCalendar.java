package j$.util;

import j$.time.Instant;
import java.util.Calendar;
public final class DesugarCalendar {
    public static final Instant toInstant(Calendar calendar) {
        return Instant.ofEpochMilli(calendar.getTimeInMillis());
    }
}
