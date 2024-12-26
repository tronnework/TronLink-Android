package j$.time.chrono;

import j$.time.Clock$OffsetClock$ExternalSyntheticBackport0;
import j$.time.Duration$ExternalSyntheticBackport0;
import j$.time.LocalTime;
import j$.time.chrono.ChronoLocalDate;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import java.io.Serializable;
import org.apache.commons.cli.HelpFormatter;
public abstract class ChronoLocalDateImpl implements ChronoLocalDate, Temporal, TemporalAdjuster, Serializable {
    private static final long serialVersionUID = 6282433883239719096L;

    public static class fun1 {
        static final int[] $SwitchMap$java$time$temporal$ChronoUnit;

        static {
            int[] iArr = new int[ChronoUnit.values().length];
            $SwitchMap$java$time$temporal$ChronoUnit = iArr;
            try {
                iArr[ChronoUnit.DAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.WEEKS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MONTHS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.YEARS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.DECADES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.CENTURIES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.MILLENNIA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoUnit[ChronoUnit.ERAS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static ChronoLocalDate ensureValid(Chronology chronology, Temporal temporal) {
        ChronoLocalDate chronoLocalDate = (ChronoLocalDate) temporal;
        if (chronology.equals(chronoLocalDate.getChronology())) {
            return chronoLocalDate;
        }
        String id = chronology.getId();
        String id2 = chronoLocalDate.getChronology().getId();
        throw new ClassCastException("Chronology mismatch, expected: " + id + ", actual: " + id2);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal with;
        with = temporal.with(ChronoField.EPOCH_DAY, toEpochDay());
        return with;
    }

    @Override
    public ChronoLocalDateTime atTime(LocalTime localTime) {
        ChronoLocalDateTime of;
        of = ChronoLocalDateTimeImpl.of(this, localTime);
        return of;
    }

    @Override
    public int compareTo(ChronoLocalDate chronoLocalDate) {
        return Long.compare(toEpochDay(), chronoLocalDate.toEpochDay());
    }

    @Override
    public int compareTo(Object obj) {
        int compareTo;
        compareTo = compareTo((ChronoLocalDate) obj);
        return compareTo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoLocalDate) && compareTo((ChronoLocalDate) obj) == 0;
    }

    @Override
    public int get(TemporalField temporalField) {
        return TemporalAccessor.-CC.$default$get(this, temporalField);
    }

    @Override
    public Era getEra() {
        Era eraOf;
        eraOf = getChronology().eraOf(get(ChronoField.ERA));
        return eraOf;
    }

    @Override
    public int hashCode() {
        long epochDay = toEpochDay();
        return getChronology().hashCode() ^ ((int) (epochDay ^ (epochDay >>> 32)));
    }

    @Override
    public boolean isLeapYear() {
        boolean isLeapYear;
        isLeapYear = getChronology().isLeapYear(getLong(ChronoField.YEAR));
        return isLeapYear;
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        return ChronoLocalDate.-CC.$default$isSupported(this, temporalField);
    }

    @Override
    public int lengthOfYear() {
        return ChronoLocalDate.-CC.$default$lengthOfYear(this);
    }

    @Override
    public ChronoLocalDate minus(long j, TemporalUnit temporalUnit) {
        ChronoLocalDate ensureValid;
        ensureValid = ensureValid(getChronology(), Temporal.-CC.$default$minus(this, j, temporalUnit));
        return ensureValid;
    }

    @Override
    public ChronoLocalDate plus(long j, TemporalUnit temporalUnit) {
        long m;
        long m2;
        long m3;
        long m4;
        if (temporalUnit instanceof ChronoUnit) {
            switch (fun1.$SwitchMap$java$time$temporal$ChronoUnit[((ChronoUnit) temporalUnit).ordinal()]) {
                case 1:
                    return plusDays(j);
                case 2:
                    m = Duration$ExternalSyntheticBackport0.m(j, 7);
                    return plusDays(m);
                case 3:
                    return plusMonths(j);
                case 4:
                    return plusYears(j);
                case 5:
                    m2 = Duration$ExternalSyntheticBackport0.m(j, 10);
                    return plusYears(m2);
                case 6:
                    m3 = Duration$ExternalSyntheticBackport0.m(j, 100);
                    return plusYears(m3);
                case 7:
                    m4 = Duration$ExternalSyntheticBackport0.m(j, 1000);
                    return plusYears(m4);
                case 8:
                    ChronoField chronoField = ChronoField.ERA;
                    return with((TemporalField) chronoField, Clock$OffsetClock$ExternalSyntheticBackport0.m(getLong(chronoField), j));
                default:
                    throw new UnsupportedTemporalTypeException("Unsupported unit: " + temporalUnit);
            }
        }
        return ChronoLocalDate.-CC.$default$plus(this, j, temporalUnit);
    }

    @Override
    public ChronoLocalDate plus(TemporalAmount temporalAmount) {
        return ChronoLocalDate.-CC.$default$plus(this, temporalAmount);
    }

    abstract ChronoLocalDate plusDays(long j);

    abstract ChronoLocalDate plusMonths(long j);

    abstract ChronoLocalDate plusYears(long j);

    @Override
    public Object query(TemporalQuery temporalQuery) {
        return ChronoLocalDate.-CC.$default$query(this, temporalQuery);
    }

    @Override
    public ValueRange range(TemporalField temporalField) {
        return TemporalAccessor.-CC.$default$range(this, temporalField);
    }

    @Override
    public long toEpochDay() {
        long j;
        j = getLong(ChronoField.EPOCH_DAY);
        return j;
    }

    @Override
    public String toString() {
        long j = getLong(ChronoField.YEAR_OF_ERA);
        long j2 = getLong(ChronoField.MONTH_OF_YEAR);
        long j3 = getLong(ChronoField.DAY_OF_MONTH);
        StringBuilder sb = new StringBuilder(30);
        sb.append(getChronology().toString());
        sb.append(" ");
        sb.append(getEra());
        sb.append(" ");
        sb.append(j);
        String str = HelpFormatter.DEFAULT_OPT_PREFIX;
        sb.append(j2 < 10 ? "-0" : HelpFormatter.DEFAULT_OPT_PREFIX);
        sb.append(j2);
        if (j3 < 10) {
            str = "-0";
        }
        sb.append(str);
        sb.append(j3);
        return sb.toString();
    }

    @Override
    public ChronoLocalDate with(TemporalAdjuster temporalAdjuster) {
        return ChronoLocalDate.-CC.$default$with(this, temporalAdjuster);
    }

    @Override
    public ChronoLocalDate with(TemporalField temporalField, long j) {
        return ChronoLocalDate.-CC.$default$with(this, temporalField, j);
    }
}
