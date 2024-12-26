package j$.util;

import j$.time.ZoneId;
import java.util.TimeZone;
public final class TimeZoneRetargetClass {
    public static ZoneId toZoneId(TimeZone timeZone) {
        return DesugarTimeZone.toZoneId(timeZone);
    }
}
