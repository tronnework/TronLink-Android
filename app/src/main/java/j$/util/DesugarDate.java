package j$.util;

import j$.time.Instant;
import java.util.Date;
public abstract class DesugarDate {
    public static Instant toInstant(Date date) {
        return Instant.ofEpochMilli(date.getTime());
    }
}
