package j$.time.chrono;

import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.temporal.ChronoField;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAdjuster;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.TemporalUnit;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
public final class ChronoZonedDateTimeImpl implements ChronoZonedDateTime, Serializable {
    private static final long serialVersionUID = -5261813987200935591L;
    private final transient ChronoLocalDateTimeImpl dateTime;
    private final transient ZoneOffset offset;
    private final transient ZoneId zone;

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

    private ChronoZonedDateTimeImpl(ChronoLocalDateTimeImpl chronoLocalDateTimeImpl, ZoneOffset zoneOffset, ZoneId zoneId) {
        this.dateTime = (ChronoLocalDateTimeImpl) Objects.requireNonNull(chronoLocalDateTimeImpl, "dateTime");
        this.offset = (ZoneOffset) Objects.requireNonNull(zoneOffset, "offset");
        this.zone = (ZoneId) Objects.requireNonNull(zoneId, "zone");
    }

    private ChronoZonedDateTimeImpl create(Instant instant, ZoneId zoneId) {
        return ofInstant(getChronology(), instant, zoneId);
    }

    public static ChronoZonedDateTimeImpl ensureValid(Chronology chronology, Temporal temporal) {
        ChronoZonedDateTimeImpl chronoZonedDateTimeImpl = (ChronoZonedDateTimeImpl) temporal;
        if (chronology.equals(chronoZonedDateTimeImpl.getChronology())) {
            return chronoZonedDateTimeImpl;
        }
        String id = chronology.getId();
        String id2 = chronoZonedDateTimeImpl.getChronology().getId();
        throw new ClassCastException("Chronology mismatch, required: " + id + ", actual: " + id2);
    }

    public static j$.time.chrono.ChronoZonedDateTime ofBest(j$.time.chrono.ChronoLocalDateTimeImpl r6, j$.time.ZoneId r7, j$.time.ZoneOffset r8) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: j$.time.chrono.ChronoZonedDateTimeImpl.ofBest(j$.time.chrono.ChronoLocalDateTimeImpl, j$.time.ZoneId, j$.time.ZoneOffset):j$.time.chrono.ChronoZonedDateTime");
    }

    public static ChronoZonedDateTimeImpl ofInstant(Chronology chronology, Instant instant, ZoneId zoneId) {
        ZoneOffset offset = zoneId.getRules().getOffset(instant);
        Objects.requireNonNull(offset, "offset");
        return new ChronoZonedDateTimeImpl((ChronoLocalDateTimeImpl) chronology.localDateTime(LocalDateTime.ofEpochSecond(instant.getEpochSecond(), instant.getNano(), offset)), offset, zoneId);
    }

    public static ChronoZonedDateTime readExternal(ObjectInput objectInput) {
        return ((ChronoLocalDateTime) objectInput.readObject()).atZone((ZoneOffset) objectInput.readObject()).withZoneSameLocal((ZoneId) objectInput.readObject());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    @Override
    public int compareTo(ChronoZonedDateTime chronoZonedDateTime) {
        return ChronoZonedDateTime.-CC.$default$compareTo((ChronoZonedDateTime) this, chronoZonedDateTime);
    }

    @Override
    public int compareTo(ChronoZonedDateTime<?> chronoZonedDateTime) {
        int compareTo;
        compareTo = compareTo((ChronoZonedDateTime) chronoZonedDateTime);
        return compareTo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ChronoZonedDateTime) && compareTo((ChronoZonedDateTime) obj) == 0;
    }

    @Override
    public int get(TemporalField temporalField) {
        return ChronoZonedDateTime.-CC.$default$get(this, temporalField);
    }

    @Override
    public Chronology getChronology() {
        Chronology chronology;
        chronology = toLocalDate().getChronology();
        return chronology;
    }

    @Override
    public long getLong(TemporalField temporalField) {
        return ChronoZonedDateTime.-CC.$default$getLong(this, temporalField);
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
        return (toLocalDateTime().hashCode() ^ getOffset().hashCode()) ^ Integer.rotateLeft(getZone().hashCode(), 3);
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        return (temporalField instanceof ChronoField) || (temporalField != null && temporalField.isSupportedBy(this));
    }

    @Override
    public ChronoZonedDateTime minus(long j, TemporalUnit temporalUnit) {
        ChronoZonedDateTime ensureValid;
        ensureValid = ensureValid(getChronology(), Temporal.-CC.$default$minus(this, j, temporalUnit));
        return ensureValid;
    }

    @Override
    public Temporal minus(long j, TemporalUnit temporalUnit) {
        Temporal minus;
        minus = minus(j, temporalUnit);
        return minus;
    }

    @Override
    public ChronoZonedDateTime plus(long j, TemporalUnit temporalUnit) {
        return temporalUnit instanceof ChronoUnit ? with((TemporalAdjuster) this.dateTime.plus(j, temporalUnit)) : ensureValid(getChronology(), temporalUnit.addTo(this, j));
    }

    @Override
    public Object query(TemporalQuery temporalQuery) {
        return ChronoZonedDateTime.-CC.$default$query(this, temporalQuery);
    }

    @Override
    public ValueRange range(TemporalField temporalField) {
        return ChronoZonedDateTime.-CC.$default$range(this, temporalField);
    }

    @Override
    public long toEpochSecond() {
        return ChronoZonedDateTime.-CC.$default$toEpochSecond(this);
    }

    @Override
    public Instant toInstant() {
        Instant ofEpochSecond;
        ofEpochSecond = Instant.ofEpochSecond(toEpochSecond(), toLocalTime().getNano());
        return ofEpochSecond;
    }

    @Override
    public ChronoLocalDate toLocalDate() {
        ChronoLocalDate localDate;
        localDate = toLocalDateTime().toLocalDate();
        return localDate;
    }

    @Override
    public ChronoLocalDateTime toLocalDateTime() {
        return this.dateTime;
    }

    @Override
    public LocalTime toLocalTime() {
        LocalTime localTime;
        localTime = toLocalDateTime().toLocalTime();
        return localTime;
    }

    public String toString() {
        String str = toLocalDateTime().toString() + getOffset().toString();
        if (getOffset() != getZone()) {
            return str + "[" + getZone().toString() + "]";
        }
        return str;
    }

    @Override
    public ChronoZonedDateTime with(TemporalAdjuster temporalAdjuster) {
        return ChronoZonedDateTime.-CC.$default$with((ChronoZonedDateTime) this, temporalAdjuster);
    }

    @Override
    public ChronoZonedDateTime with(TemporalField temporalField, long j) {
        if (temporalField instanceof ChronoField) {
            ChronoField chronoField = (ChronoField) temporalField;
            int i = fun1.$SwitchMap$java$time$temporal$ChronoField[chronoField.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    return ofBest(this.dateTime.with(temporalField, j), this.zone, this.offset);
                }
                return create(this.dateTime.toInstant(ZoneOffset.ofTotalSeconds(chronoField.checkValidIntValue(j))), this.zone);
            }
            return plus(j - toEpochSecond(), (TemporalUnit) ChronoUnit.SECONDS);
        }
        return ensureValid(getChronology(), temporalField.adjustInto(this, j));
    }

    @Override
    public Temporal with(TemporalAdjuster temporalAdjuster) {
        Temporal with;
        with = with(temporalAdjuster);
        return with;
    }

    @Override
    public ChronoZonedDateTime withZoneSameLocal(ZoneId zoneId) {
        return ofBest(this.dateTime, zoneId, this.offset);
    }

    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeObject(this.dateTime);
        objectOutput.writeObject(this.offset);
        objectOutput.writeObject(this.zone);
    }
}
