package j$.time.chrono;

import j$.time.DateTimeException;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.ValueRange;
public enum ThaiBuddhistEra implements Era {
    BEFORE_BE,
    BE;

    public static ThaiBuddhistEra of(int i) {
        if (i != 0) {
            if (i == 1) {
                return BE;
            }
            throw new DateTimeException("Invalid era: " + i);
        }
        return BEFORE_BE;
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
        Temporal with;
        with = temporal.with(ChronoField.ERA, getValue());
        return with;
    }

    @Override
    public int get(TemporalField temporalField) {
        return Era.-CC.$default$get(this, temporalField);
    }

    @Override
    public long getLong(TemporalField temporalField) {
        return Era.-CC.$default$getLong(this, temporalField);
    }

    @Override
    public int getValue() {
        return ordinal();
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        return Era.-CC.$default$isSupported(this, temporalField);
    }

    @Override
    public Object query(TemporalQuery temporalQuery) {
        return Era.-CC.$default$query(this, temporalQuery);
    }

    @Override
    public ValueRange range(TemporalField temporalField) {
        ValueRange $default$range;
        $default$range = TemporalAccessor.-CC.$default$range(this, temporalField);
        return $default$range;
    }
}
