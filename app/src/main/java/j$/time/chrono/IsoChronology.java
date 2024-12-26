package j$.time.chrono;

import androidx.exifinterface.media.ExifInterface;
import j$.time.Clock;
import j$.time.DesugarLocalDate$ExternalSyntheticBackport1;
import j$.time.Duration$DurationUnits$ExternalSyntheticBackport1;
import j$.time.Instant;
import j$.time.Instant$ExternalSyntheticBackport0;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.Month;
import j$.time.Year;
import j$.time.ZoneId;
import j$.time.ZonedDateTime;
import j$.time.format.ResolverStyle;
import j$.time.temporal.ChronoField;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
public final class IsoChronology extends AbstractChronology implements Serializable {
    public static final IsoChronology INSTANCE = new IsoChronology();
    private static final long serialVersionUID = -1440403870442975015L;

    private IsoChronology() {
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override
    public LocalDate date(int i, int i2, int i3) {
        return LocalDate.of(i, i2, i3);
    }

    @Override
    public LocalDate date(TemporalAccessor temporalAccessor) {
        return LocalDate.from(temporalAccessor);
    }

    @Override
    public LocalDate dateEpochDay(long j) {
        return LocalDate.ofEpochDay(j);
    }

    @Override
    public LocalDate dateNow() {
        return dateNow(Clock.systemDefaultZone());
    }

    public LocalDate dateNow(Clock clock) {
        Objects.requireNonNull(clock, "clock");
        return date((TemporalAccessor) LocalDate.now(clock));
    }

    @Override
    public LocalDate dateYearDay(int i, int i2) {
        return LocalDate.ofYearDay(i, i2);
    }

    @Override
    public IsoEra eraOf(int i) {
        return IsoEra.of(i);
    }

    @Override
    public List eras() {
        return Duration$DurationUnits$ExternalSyntheticBackport1.m(IsoEra.values());
    }

    @Override
    public String getCalendarType() {
        return "iso8601";
    }

    @Override
    public String getId() {
        return ExifInterface.TAG_RW2_ISO;
    }

    @Override
    public boolean isLeapYear(long j) {
        return (3 & j) == 0 && (j % 100 != 0 || j % 400 == 0);
    }

    @Override
    public LocalDateTime localDateTime(TemporalAccessor temporalAccessor) {
        return LocalDateTime.from(temporalAccessor);
    }

    @Override
    public int prolepticYear(Era era, int i) {
        if (era instanceof IsoEra) {
            return era == IsoEra.CE ? i : 1 - i;
        }
        throw new ClassCastException("Era must be IsoEra");
    }

    @Override
    public ValueRange range(ChronoField chronoField) {
        return chronoField.range();
    }

    @Override
    public LocalDate resolveDate(Map map, ResolverStyle resolverStyle) {
        return (LocalDate) super.resolveDate(map, resolverStyle);
    }

    @Override
    void resolveProlepticMonth(Map map, ResolverStyle resolverStyle) {
        long m;
        ChronoField chronoField = ChronoField.PROLEPTIC_MONTH;
        Long l = (Long) map.remove(chronoField);
        if (l != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                chronoField.checkValidValue(l.longValue());
            }
            addFieldValue(map, ChronoField.MONTH_OF_YEAR, IsoChronology$ExternalSyntheticBackport0.m(l.longValue(), 12) + 1);
            ChronoField chronoField2 = ChronoField.YEAR;
            m = DesugarLocalDate$ExternalSyntheticBackport1.m(l.longValue(), 12);
            addFieldValue(map, chronoField2, m);
        }
    }

    @Override
    public LocalDate resolveYMD(Map map, ResolverStyle resolverStyle) {
        int length;
        ChronoField chronoField = ChronoField.YEAR;
        int checkValidIntValue = chronoField.checkValidIntValue(((Long) map.remove(chronoField)).longValue());
        if (resolverStyle == ResolverStyle.LENIENT) {
            return LocalDate.of(checkValidIntValue, 1, 1).plusMonths(Instant$ExternalSyntheticBackport0.m(((Long) map.remove(ChronoField.MONTH_OF_YEAR)).longValue(), 1L)).plusDays(Instant$ExternalSyntheticBackport0.m(((Long) map.remove(ChronoField.DAY_OF_MONTH)).longValue(), 1L));
        }
        ChronoField chronoField2 = ChronoField.MONTH_OF_YEAR;
        int checkValidIntValue2 = chronoField2.checkValidIntValue(((Long) map.remove(chronoField2)).longValue());
        ChronoField chronoField3 = ChronoField.DAY_OF_MONTH;
        int checkValidIntValue3 = chronoField3.checkValidIntValue(((Long) map.remove(chronoField3)).longValue());
        if (resolverStyle == ResolverStyle.SMART) {
            if (checkValidIntValue2 != 4 && checkValidIntValue2 != 6 && checkValidIntValue2 != 9 && checkValidIntValue2 != 11) {
                length = checkValidIntValue2 == 2 ? Month.FEBRUARY.length(Year.isLeap(checkValidIntValue)) : 30;
            }
            checkValidIntValue3 = Math.min(checkValidIntValue3, length);
        }
        return LocalDate.of(checkValidIntValue, checkValidIntValue2, checkValidIntValue3);
    }

    @Override
    public j$.time.LocalDate resolveYearOfEra(java.util.Map r10, j$.time.format.ResolverStyle r11) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: j$.time.chrono.IsoChronology.resolveYearOfEra(java.util.Map, j$.time.format.ResolverStyle):j$.time.LocalDate");
    }

    @Override
    Object writeReplace() {
        return super.writeReplace();
    }

    @Override
    public ZonedDateTime zonedDateTime(Instant instant, ZoneId zoneId) {
        return ZonedDateTime.ofInstant(instant, zoneId);
    }
}
