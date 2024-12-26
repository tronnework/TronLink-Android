package j$.time;

import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.Chronology;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.time.zone.ZoneOffsetTransition;
import j$.time.zone.ZoneRules;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
public final class ZonedDateTime implements Temporal, ChronoZonedDateTime<LocalDate>, Serializable {
    private static final long serialVersionUID = -6260982410461394882L;
    private final LocalDateTime dateTime;
    private final ZoneOffset offset;
    private final ZoneId zone;

    public static class fun1 {
        static final int[] $SwitchMap$java$time$temporal$ChronoField;

        static {
            int[] iArr = new int[ChronoField.values().length];
            $SwitchMap$java$time$temporal$ChronoField = iArr;
            try {
                iArr[ChronoField.INSTANT_SECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$time$temporal$ChronoField[ChronoField.OFFSET_SECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private ZonedDateTime(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneId zoneId) {
        this.dateTime = localDateTime;
        this.offset = zoneOffset;
        this.zone = zoneId;
    }

    private static ZonedDateTime create(long j, int i, ZoneId zoneId) {
        ZoneOffset offset = zoneId.getRules().getOffset(Instant.ofEpochSecond(j, i));
        return new ZonedDateTime(LocalDateTime.ofEpochSecond(j, i, offset), offset, zoneId);
    }

    public static ZonedDateTime from(TemporalAccessor temporalAccessor) {
        if (temporalAccessor instanceof ZonedDateTime) {
            return (ZonedDateTime) temporalAccessor;
        }
        try {
            ZoneId from = ZoneId.from(temporalAccessor);
            ChronoField chronoField = ChronoField.INSTANT_SECONDS;
            return temporalAccessor.isSupported(chronoField) ? create(temporalAccessor.getLong(chronoField), temporalAccessor.get(ChronoField.NANO_OF_SECOND), from) : of(LocalDate.from(temporalAccessor), LocalTime.from(temporalAccessor), from);
        } catch (DateTimeException e) {
            String name = temporalAccessor.getClass().getName();
            throw new DateTimeException("Unable to obtain ZonedDateTime from TemporalAccessor: " + temporalAccessor + " of type " + name, e);
        }
    }

    public static ZonedDateTime of(LocalDate localDate, LocalTime localTime, ZoneId zoneId) {
        return of(LocalDateTime.of(localDate, localTime), zoneId);
    }

    public static ZonedDateTime of(LocalDateTime localDateTime, ZoneId zoneId) {
        return ofLocal(localDateTime, zoneId, null);
    }

    public static ZonedDateTime ofInstant(Instant instant, ZoneId zoneId) {
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return create(instant.getEpochSecond(), instant.getNano(), zoneId);
    }

    public static ZonedDateTime ofInstant(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneId zoneId) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zoneOffset, "offset");
        Objects.requireNonNull(zoneId, "zone");
        return zoneId.getRules().isValidOffset(localDateTime, zoneOffset) ? new ZonedDateTime(localDateTime, zoneOffset, zoneId) : create(localDateTime.toEpochSecond(zoneOffset), localDateTime.getNano(), zoneId);
    }

    private static ZonedDateTime ofLenient(LocalDateTime localDateTime, ZoneOffset zoneOffset, ZoneId zoneId) {
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zoneOffset, "offset");
        Objects.requireNonNull(zoneId, "zone");
        if (!(zoneId instanceof ZoneOffset) || zoneOffset.equals(zoneId)) {
            return new ZonedDateTime(localDateTime, zoneOffset, zoneId);
        }
        throw new IllegalArgumentException("ZoneId must match ZoneOffset");
    }

    public static ZonedDateTime ofLocal(LocalDateTime localDateTime, ZoneId zoneId, ZoneOffset zoneOffset) {
        Object requireNonNull;
        Objects.requireNonNull(localDateTime, "localDateTime");
        Objects.requireNonNull(zoneId, "zone");
        if (zoneId instanceof ZoneOffset) {
            return new ZonedDateTime(localDateTime, (ZoneOffset) zoneId, zoneId);
        }
        ZoneRules rules = zoneId.getRules();
        List validOffsets = rules.getValidOffsets(localDateTime);
        if (validOffsets.size() != 1) {
            if (validOffsets.size() == 0) {
                ZoneOffsetTransition transition = rules.getTransition(localDateTime);
                localDateTime = localDateTime.plusSeconds(transition.getDuration().getSeconds());
                zoneOffset = transition.getOffsetAfter();
            } else if (zoneOffset == null || !validOffsets.contains(zoneOffset)) {
                requireNonNull = Objects.requireNonNull((ZoneOffset) validOffsets.get(0), "offset");
            }
            return new ZonedDateTime(localDateTime, zoneOffset, zoneId);
        }
        requireNonNull = validOffsets.get(0);
        zoneOffset = (ZoneOffset) requireNonNull;
        return new ZonedDateTime(localDateTime, zoneOffset, zoneId);
    }

    public static ZonedDateTime parse(CharSequence charSequence) {
        return parse(charSequence, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public static ZonedDateTime parse(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        Objects.requireNonNull(dateTimeFormatter, "formatter");
        return (ZonedDateTime) dateTimeFormatter.parse(charSequence, new TemporalQuery() {
            @Override
            public final Object queryFrom(TemporalAccessor temporalAccessor) {
                return ZonedDateTime.from(temporalAccessor);
            }
        });
    }

    public static ZonedDateTime readExternal(ObjectInput objectInput) {
        return ofLenient(LocalDateTime.readExternal(objectInput), ZoneOffset.readExternal(objectInput), (ZoneId) Ser.read(objectInput));
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private ZonedDateTime resolveInstant(LocalDateTime localDateTime) {
        return ofInstant(localDateTime, this.offset, this.zone);
    }

    private ZonedDateTime resolveLocal(LocalDateTime localDateTime) {
        return ofLocal(localDateTime, this.zone, this.offset);
    }

    private ZonedDateTime resolveOffset(ZoneOffset zoneOffset) {
        return (zoneOffset.equals(this.offset) || !this.zone.getRules().isValidOffset(this.dateTime, zoneOffset)) ? this : new ZonedDateTime(this.dateTime, zoneOffset, this.zone);
    }

    private Object writeReplace() {
        return new Ser((byte) 6, this);
    }

    @Override
    public int compareTo(ChronoZonedDateTime chronoZonedDateTime) {
        return ChronoZonedDateTime.-CC.$default$compareTo((ChronoZonedDateTime) this, chronoZonedDateTime);
    }

    @Override
    public int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        return ChronoZonedDateTime.-CC.$default$compareTo((ChronoZonedDateTime) this, (Object) chronoZonedDateTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZonedDateTime) {
            ZonedDateTime zonedDateTime = (ZonedDateTime) obj;
            return this.dateTime.equals(zonedDateTime.dateTime) && this.offset.equals(zonedDateTime.offset) && this.zone.equals(zonedDateTime.zone);
        }
        return false;
    }

    @Override
    public int get(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = fun1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            if (i != 1) {
                return i != 2 ? this.dateTime.get(temporalField) : getOffset().getTotalSeconds();
            }
            throw new UnsupportedTemporalTypeException("Invalid field 'InstantSeconds' for get() method, use getLong() instead");
        }
        return ChronoZonedDateTime.-CC.$default$get(this, temporalField);
    }

    @Override
    public Chronology getChronology() {
        return ChronoZonedDateTime.-CC.$default$getChronology(this);
    }

    @Override
    public long getLong(TemporalField temporalField) {
        if (temporalField instanceof ChronoField) {
            int i = fun1.$SwitchMap$java$time$temporal$ChronoField[((ChronoField) temporalField).ordinal()];
            return i != 1 ? i != 2 ? this.dateTime.getLong(temporalField) : getOffset().getTotalSeconds() : toEpochSecond();
        }
        return temporalField.getFrom(this);
    }

    public int getNano() {
        return this.dateTime.getNano();
    }

    @Override
    public ZoneOffset getOffset() {
        return this.offset;
    }

    @Override
    public ZoneId getZone() {
        return this.zone;
    }

    public int hashCode() {
        return (this.dateTime.hashCode() ^ this.offset.hashCode()) ^ Integer.rotateLeft(this.zone.hashCode(), 3);
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.isSupportedBy(this));
    }

    @Override
    public ZonedDateTime minus(long j, TemporalUnit temporalUnit) {
        return j == Long.MIN_VALUE ? plus(Long.MAX_VALUE, temporalUnit).plus(1L, temporalUnit) : plus(-j, temporalUnit);
    }

    @Override
    public ZonedDateTime plus(long j, TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? temporalUnit.isDateBased() ? resolveLocal(this.dateTime.plus(j, temporalUnit)) : resolveInstant(this.dateTime.plus(j, temporalUnit)) : (ZonedDateTime) temporalUnit.addTo(this, j);
    }

    @Override
    public Object query(TemporalQuery temporalQuery) {
        return temporalQuery == TemporalQueries.localDate() ? toLocalDate() : ChronoZonedDateTime.-CC.$default$query(this, temporalQuery);
    }

    @Override
    public ValueRange range(TemporalField temporalField) {
        return temporalField instanceof ChronoField ? (temporalField == ChronoField.INSTANT_SECONDS || temporalField == ChronoField.OFFSET_SECONDS) ? temporalField.range() : this.dateTime.range(temporalField) : temporalField.rangeRefinedBy(this);
    }

    @Override
    public long toEpochSecond() {
        return ChronoZonedDateTime.-CC.$default$toEpochSecond(this);
    }

    @Override
    public Instant toInstant() {
        return ChronoZonedDateTime.-CC.$default$toInstant(this);
    }

    @Override
    public LocalDate toLocalDate() {
        return this.dateTime.toLocalDate();
    }

    @Override
    public LocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    @Override
    public LocalTime toLocalTime() {
        return this.dateTime.toLocalTime();
    }

    public String toString() {
        String str = this.dateTime.toString() + this.offset.toString();
        ZoneOffset zoneOffset = this.offset;
        ZoneId zoneId = this.zone;
        if (zoneOffset != zoneId) {
            return str + "[" + zoneId.toString() + "]";
        }
        return str;
    }

    @Override
    public ZonedDateTime with(TemporalAdjuster temporalAdjuster) {
        if (temporalAdjuster instanceof LocalDate) {
            return resolveLocal(LocalDateTime.of((LocalDate) temporalAdjuster, this.dateTime.toLocalTime()));
        }
        if (temporalAdjuster instanceof LocalTime) {
            return resolveLocal(LocalDateTime.of(this.dateTime.toLocalDate(), (LocalTime) temporalAdjuster));
        }
        if (temporalAdjuster instanceof LocalDateTime) {
            return resolveLocal((LocalDateTime) temporalAdjuster);
        }
        if (temporalAdjuster instanceof OffsetDateTime) {
            OffsetDateTime offsetDateTime = (OffsetDateTime) temporalAdjuster;
            return ofLocal(offsetDateTime.toLocalDateTime(), this.zone, offsetDateTime.getOffset());
        } else if (!(temporalAdjuster instanceof Instant)) {
            return temporalAdjuster instanceof ZoneOffset ? resolveOffset((ZoneOffset) temporalAdjuster) : (ZonedDateTime) temporalAdjuster.adjustInto(this);
        } else {
            Instant instant = (Instant) temporalAdjuster;
            return create(instant.getEpochSecond(), instant.getNano(), this.zone);
        }
    }

    @Override
    public ZonedDateTime with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = fun1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            return i != 1 ? i != 2 ? resolveLocal(this.dateTime.with(temporalField, j)) : resolveOffset(ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j))) : create(j, getNano(), this.zone);
        }
        return (ZonedDateTime) temporalField.adjustInto(this, j);
    }

    @Override
    public ZonedDateTime withZoneSameLocal(ZoneId zoneId) {
        Objects.requireNonNull(zoneId, "zone");
        return this.zone.equals(zoneId) ? this : ofLocal(this.dateTime, zoneId, this.offset);
    }

    public void writeExternal(DataOutput dataOutput) {
        this.dateTime.writeExternal(dataOutput);
        this.offset.writeExternal(dataOutput);
        this.zone.write(dataOutput);
    }
}
