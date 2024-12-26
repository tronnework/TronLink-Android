package j$.time.format;

import j$.time.Clock$OffsetClock$ExternalSyntheticBackport0;
import j$.time.Clock$TickClock$ExternalSyntheticBackport0;
import j$.time.DateTimeException;
import j$.time.DesugarLocalDate$ExternalSyntheticBackport1;
import j$.time.Duration$ExternalSyntheticBackport0;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.Period;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.ChronoLocalDateTime;
import j$.time.chrono.ChronoZonedDateTime;
import j$.time.chrono.Chronology;
import j$.time.temporal.ChronoField;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQueries;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.UnsupportedTemporalTypeException;
import j$.time.temporal.ValueRange;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public final class Parsed implements TemporalAccessor {
    Chronology chrono;
    private ChronoLocalDate date;
    boolean leapSecond;
    private ResolverStyle resolverStyle;
    private LocalTime time;
    ZoneId zone;
    final Map fieldValues = new HashMap();
    Period excessDays = Period.ZERO;

    private void crossCheck() {
        ChronoLocalDate chronoLocalDate = this.date;
        if (chronoLocalDate != null) {
            crossCheck(chronoLocalDate);
        }
        LocalTime localTime = this.time;
        if (localTime != null) {
            crossCheck(localTime);
            if (this.date == null || this.fieldValues.size() <= 0) {
                return;
            }
            crossCheck(this.date.atTime(this.time));
        }
    }

    private void crossCheck(TemporalAccessor temporalAccessor) {
        Iterator it = this.fieldValues.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            TemporalField temporalField = (TemporalField) entry.getKey();
            if (temporalAccessor.isSupported(temporalField)) {
                try {
                    long j = temporalAccessor.getLong(temporalField);
                    long longValue = ((Long) entry.getValue()).longValue();
                    if (j != longValue) {
                        throw new DateTimeException("Conflict found: Field " + temporalField + " " + j + " differs from " + temporalField + " " + longValue + " derived from " + temporalAccessor);
                    }
                    it.remove();
                } catch (RuntimeException unused) {
                }
            }
        }
    }

    private void resolveDateFields() {
        updateCheckConflict(this.chrono.resolveDate(this.fieldValues, this.resolverStyle));
    }

    private void resolveFields() {
        resolveInstantFields();
        resolveDateFields();
        resolveTimeFields();
        if (this.fieldValues.size() > 0) {
            int i = 0;
            loop0: while (i < 50) {
                for (Map.Entry entry : this.fieldValues.entrySet()) {
                    TemporalField temporalField = (TemporalField) entry.getKey();
                    TemporalAccessor resolve = temporalField.resolve(this.fieldValues, this, this.resolverStyle);
                    if (resolve != null) {
                        if (resolve instanceof ChronoZonedDateTime) {
                            ChronoZonedDateTime chronoZonedDateTime = (ChronoZonedDateTime) resolve;
                            ZoneId zoneId = this.zone;
                            if (zoneId == null) {
                                this.zone = chronoZonedDateTime.getZone();
                            } else if (!zoneId.equals(chronoZonedDateTime.getZone())) {
                                ZoneId zoneId2 = this.zone;
                                throw new DateTimeException("ChronoZonedDateTime must use the effective parsed zone: " + zoneId2);
                            }
                            resolve = chronoZonedDateTime.toLocalDateTime();
                        }
                        if (resolve instanceof ChronoLocalDateTime) {
                            ChronoLocalDateTime chronoLocalDateTime = (ChronoLocalDateTime) resolve;
                            updateCheckConflict(chronoLocalDateTime.toLocalTime(), Period.ZERO);
                            updateCheckConflict(chronoLocalDateTime.toLocalDate());
                        } else if (resolve instanceof ChronoLocalDate) {
                            updateCheckConflict((ChronoLocalDate) resolve);
                        } else if (!(resolve instanceof LocalTime)) {
                            throw new DateTimeException("Method resolve() can only return ChronoZonedDateTime, ChronoLocalDateTime, ChronoLocalDate or LocalTime");
                        } else {
                            updateCheckConflict((LocalTime) resolve, Period.ZERO);
                        }
                    } else if (!this.fieldValues.containsKey(temporalField)) {
                        break;
                    }
                    i++;
                }
            }
            if (i == 50) {
                throw new DateTimeException("One of the parsed fields has an incorrectly implemented resolve method");
            }
            if (i > 0) {
                resolveInstantFields();
                resolveDateFields();
                resolveTimeFields();
            }
        }
    }

    private void resolveFractional() {
        if (this.time == null) {
            if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS) || this.fieldValues.containsKey(ChronoField.SECOND_OF_DAY) || this.fieldValues.containsKey(ChronoField.SECOND_OF_MINUTE)) {
                Map map = this.fieldValues;
                ChronoField chronoField = ChronoField.NANO_OF_SECOND;
                if (map.containsKey(chronoField)) {
                    long longValue = ((Long) this.fieldValues.get(chronoField)).longValue();
                    this.fieldValues.put(ChronoField.MICRO_OF_SECOND, Long.valueOf(longValue / 1000));
                    this.fieldValues.put(ChronoField.MILLI_OF_SECOND, Long.valueOf(longValue / 1000000));
                    return;
                }
                this.fieldValues.put(chronoField, 0L);
                this.fieldValues.put(ChronoField.MICRO_OF_SECOND, 0L);
                this.fieldValues.put(ChronoField.MILLI_OF_SECOND, 0L);
            }
        }
    }

    private void resolveInstant() {
        ChronoZonedDateTime atZone;
        if (this.date == null || this.time == null) {
            return;
        }
        Long l = (Long) this.fieldValues.get(ChronoField.OFFSET_SECONDS);
        if (l != null) {
            atZone = this.date.atTime(this.time).atZone(ZoneOffset.ofTotalSeconds(l.intValue()));
        } else if (this.zone == null) {
            return;
        } else {
            atZone = this.date.atTime(this.time).atZone(this.zone);
        }
        this.fieldValues.put(ChronoField.INSTANT_SECONDS, Long.valueOf(atZone.toEpochSecond()));
    }

    private void resolveInstantFields() {
        if (this.fieldValues.containsKey(ChronoField.INSTANT_SECONDS)) {
            ZoneId zoneId = this.zone;
            if (zoneId == null) {
                Long l = (Long) this.fieldValues.get(ChronoField.OFFSET_SECONDS);
                if (l == null) {
                    return;
                }
                zoneId = ZoneOffset.ofTotalSeconds(l.intValue());
            }
            resolveInstantFields0(zoneId);
        }
    }

    private void resolveInstantFields0(ZoneId zoneId) {
        Map map = this.fieldValues;
        ChronoField chronoField = ChronoField.INSTANT_SECONDS;
        ChronoZonedDateTime zonedDateTime = this.chrono.zonedDateTime(Instant.ofEpochSecond(((Long) map.remove(chronoField)).longValue()), zoneId);
        updateCheckConflict(zonedDateTime.toLocalDate());
        updateCheckConflict(chronoField, ChronoField.SECOND_OF_DAY, Long.valueOf(zonedDateTime.toLocalTime().toSecondOfDay()));
    }

    private void resolvePeriod() {
        if (this.date == null || this.time == null || this.excessDays.isZero()) {
            return;
        }
        this.date = this.date.plus(this.excessDays);
        this.excessDays = Period.ZERO;
    }

    private void resolveTime(long j, long j2, long j3, long j4) {
        LocalTime of;
        Period period;
        if (this.resolverStyle == ResolverStyle.LENIENT) {
            long m = Clock$OffsetClock$ExternalSyntheticBackport0.m(Clock$OffsetClock$ExternalSyntheticBackport0.m(Clock$OffsetClock$ExternalSyntheticBackport0.m(Duration$ExternalSyntheticBackport0.m(j, 3600000000000L), Duration$ExternalSyntheticBackport0.m(j2, 60000000000L)), Duration$ExternalSyntheticBackport0.m(j3, 1000000000L)), j4);
            of = LocalTime.ofNanoOfDay(Clock$TickClock$ExternalSyntheticBackport0.m(m, 86400000000000L));
            period = Period.ofDays((int) DesugarLocalDate$ExternalSyntheticBackport1.m(m, 86400000000000L));
        } else {
            int checkValidIntValue = ChronoField.MINUTE_OF_HOUR.checkValidIntValue(j2);
            int checkValidIntValue2 = ChronoField.NANO_OF_SECOND.checkValidIntValue(j4);
            if (this.resolverStyle == ResolverStyle.SMART && j == 24 && checkValidIntValue == 0 && j3 == 0 && checkValidIntValue2 == 0) {
                of = LocalTime.MIDNIGHT;
                period = Period.ofDays(1);
            } else {
                of = LocalTime.of(ChronoField.HOUR_OF_DAY.checkValidIntValue(j), checkValidIntValue, ChronoField.SECOND_OF_MINUTE.checkValidIntValue(j3), checkValidIntValue2);
                period = Period.ZERO;
            }
        }
        updateCheckConflict(of, period);
    }

    private void resolveTimeFields() {
        TemporalField temporalField;
        Long valueOf;
        long m;
        Map map = this.fieldValues;
        ChronoField chronoField = ChronoField.CLOCK_HOUR_OF_DAY;
        if (map.containsKey(chronoField)) {
            long longValue = ((Long) this.fieldValues.remove(chronoField)).longValue();
            ResolverStyle resolverStyle = this.resolverStyle;
            if (resolverStyle == ResolverStyle.STRICT || (resolverStyle == ResolverStyle.SMART && longValue != 0)) {
                chronoField.checkValidValue(longValue);
            }
            TemporalField temporalField2 = ChronoField.HOUR_OF_DAY;
            if (longValue == 24) {
                longValue = 0;
            }
            updateCheckConflict(chronoField, temporalField2, Long.valueOf(longValue));
        }
        Map map2 = this.fieldValues;
        ChronoField chronoField2 = ChronoField.CLOCK_HOUR_OF_AMPM;
        if (map2.containsKey(chronoField2)) {
            long longValue2 = ((Long) this.fieldValues.remove(chronoField2)).longValue();
            ResolverStyle resolverStyle2 = this.resolverStyle;
            if (resolverStyle2 == ResolverStyle.STRICT || (resolverStyle2 == ResolverStyle.SMART && longValue2 != 0)) {
                chronoField2.checkValidValue(longValue2);
            }
            updateCheckConflict(chronoField2, ChronoField.HOUR_OF_AMPM, Long.valueOf(longValue2 != 12 ? longValue2 : 0L));
        }
        Map map3 = this.fieldValues;
        ChronoField chronoField3 = ChronoField.AMPM_OF_DAY;
        if (map3.containsKey(chronoField3)) {
            Map map4 = this.fieldValues;
            ChronoField chronoField4 = ChronoField.HOUR_OF_AMPM;
            if (map4.containsKey(chronoField4)) {
                long longValue3 = ((Long) this.fieldValues.remove(chronoField3)).longValue();
                long longValue4 = ((Long) this.fieldValues.remove(chronoField4)).longValue();
                if (this.resolverStyle == ResolverStyle.LENIENT) {
                    temporalField = ChronoField.HOUR_OF_DAY;
                    m = Duration$ExternalSyntheticBackport0.m(longValue3, 12);
                    valueOf = Long.valueOf(Clock$OffsetClock$ExternalSyntheticBackport0.m(m, longValue4));
                } else {
                    chronoField3.checkValidValue(longValue3);
                    chronoField4.checkValidValue(longValue3);
                    temporalField = ChronoField.HOUR_OF_DAY;
                    valueOf = Long.valueOf((longValue3 * 12) + longValue4);
                }
                updateCheckConflict(chronoField3, temporalField, valueOf);
            }
        }
        Map map5 = this.fieldValues;
        ChronoField chronoField5 = ChronoField.NANO_OF_DAY;
        if (map5.containsKey(chronoField5)) {
            long longValue5 = ((Long) this.fieldValues.remove(chronoField5)).longValue();
            if (this.resolverStyle != ResolverStyle.LENIENT) {
                chronoField5.checkValidValue(longValue5);
            }
            updateCheckConflict(chronoField5, ChronoField.HOUR_OF_DAY, Long.valueOf(longValue5 / 3600000000000L));
            updateCheckConflict(chronoField5, ChronoField.MINUTE_OF_HOUR, Long.valueOf((longValue5 / 60000000000L) % 60));
            updateCheckConflict(chronoField5, ChronoField.SECOND_OF_MINUTE, Long.valueOf((longValue5 / 1000000000) % 60));
            updateCheckConflict(chronoField5, ChronoField.NANO_OF_SECOND, Long.valueOf(longValue5 % 1000000000));
        }
        Map map6 = this.fieldValues;
        ChronoField chronoField6 = ChronoField.MICRO_OF_DAY;
        if (map6.containsKey(chronoField6)) {
            long longValue6 = ((Long) this.fieldValues.remove(chronoField6)).longValue();
            if (this.resolverStyle != ResolverStyle.LENIENT) {
                chronoField6.checkValidValue(longValue6);
            }
            updateCheckConflict(chronoField6, ChronoField.SECOND_OF_DAY, Long.valueOf(longValue6 / 1000000));
            updateCheckConflict(chronoField6, ChronoField.MICRO_OF_SECOND, Long.valueOf(longValue6 % 1000000));
        }
        Map map7 = this.fieldValues;
        ChronoField chronoField7 = ChronoField.MILLI_OF_DAY;
        if (map7.containsKey(chronoField7)) {
            long longValue7 = ((Long) this.fieldValues.remove(chronoField7)).longValue();
            if (this.resolverStyle != ResolverStyle.LENIENT) {
                chronoField7.checkValidValue(longValue7);
            }
            updateCheckConflict(chronoField7, ChronoField.SECOND_OF_DAY, Long.valueOf(longValue7 / 1000));
            updateCheckConflict(chronoField7, ChronoField.MILLI_OF_SECOND, Long.valueOf(longValue7 % 1000));
        }
        Map map8 = this.fieldValues;
        ChronoField chronoField8 = ChronoField.SECOND_OF_DAY;
        if (map8.containsKey(chronoField8)) {
            long longValue8 = ((Long) this.fieldValues.remove(chronoField8)).longValue();
            if (this.resolverStyle != ResolverStyle.LENIENT) {
                chronoField8.checkValidValue(longValue8);
            }
            updateCheckConflict(chronoField8, ChronoField.HOUR_OF_DAY, Long.valueOf(longValue8 / 3600));
            updateCheckConflict(chronoField8, ChronoField.MINUTE_OF_HOUR, Long.valueOf((longValue8 / 60) % 60));
            updateCheckConflict(chronoField8, ChronoField.SECOND_OF_MINUTE, Long.valueOf(longValue8 % 60));
        }
        Map map9 = this.fieldValues;
        ChronoField chronoField9 = ChronoField.MINUTE_OF_DAY;
        if (map9.containsKey(chronoField9)) {
            long longValue9 = ((Long) this.fieldValues.remove(chronoField9)).longValue();
            if (this.resolverStyle != ResolverStyle.LENIENT) {
                chronoField9.checkValidValue(longValue9);
            }
            updateCheckConflict(chronoField9, ChronoField.HOUR_OF_DAY, Long.valueOf(longValue9 / 60));
            updateCheckConflict(chronoField9, ChronoField.MINUTE_OF_HOUR, Long.valueOf(longValue9 % 60));
        }
        Map map10 = this.fieldValues;
        ChronoField chronoField10 = ChronoField.NANO_OF_SECOND;
        if (map10.containsKey(chronoField10)) {
            long longValue10 = ((Long) this.fieldValues.get(chronoField10)).longValue();
            ResolverStyle resolverStyle3 = this.resolverStyle;
            ResolverStyle resolverStyle4 = ResolverStyle.LENIENT;
            if (resolverStyle3 != resolverStyle4) {
                chronoField10.checkValidValue(longValue10);
            }
            Map map11 = this.fieldValues;
            ChronoField chronoField11 = ChronoField.MICRO_OF_SECOND;
            if (map11.containsKey(chronoField11)) {
                long longValue11 = ((Long) this.fieldValues.remove(chronoField11)).longValue();
                if (this.resolverStyle != resolverStyle4) {
                    chronoField11.checkValidValue(longValue11);
                }
                longValue10 = (longValue10 % 1000) + (longValue11 * 1000);
                updateCheckConflict(chronoField11, chronoField10, Long.valueOf(longValue10));
            }
            Map map12 = this.fieldValues;
            ChronoField chronoField12 = ChronoField.MILLI_OF_SECOND;
            if (map12.containsKey(chronoField12)) {
                long longValue12 = ((Long) this.fieldValues.remove(chronoField12)).longValue();
                if (this.resolverStyle != resolverStyle4) {
                    chronoField12.checkValidValue(longValue12);
                }
                updateCheckConflict(chronoField12, chronoField10, Long.valueOf((longValue12 * 1000000) + (longValue10 % 1000000)));
            }
        }
        Map map13 = this.fieldValues;
        ChronoField chronoField13 = ChronoField.HOUR_OF_DAY;
        if (map13.containsKey(chronoField13)) {
            Map map14 = this.fieldValues;
            ChronoField chronoField14 = ChronoField.MINUTE_OF_HOUR;
            if (map14.containsKey(chronoField14)) {
                Map map15 = this.fieldValues;
                ChronoField chronoField15 = ChronoField.SECOND_OF_MINUTE;
                if (map15.containsKey(chronoField15) && this.fieldValues.containsKey(chronoField10)) {
                    resolveTime(((Long) this.fieldValues.remove(chronoField13)).longValue(), ((Long) this.fieldValues.remove(chronoField14)).longValue(), ((Long) this.fieldValues.remove(chronoField15)).longValue(), ((Long) this.fieldValues.remove(chronoField10)).longValue());
                }
            }
        }
    }

    private void resolveTimeLenient() {
        Map map;
        ChronoField chronoField;
        if (this.time == null) {
            Map map2 = this.fieldValues;
            ChronoField chronoField2 = ChronoField.MILLI_OF_SECOND;
            long j = 1000;
            if (map2.containsKey(chronoField2)) {
                long longValue = ((Long) this.fieldValues.remove(chronoField2)).longValue();
                Map map3 = this.fieldValues;
                ChronoField chronoField3 = ChronoField.MICRO_OF_SECOND;
                if (map3.containsKey(chronoField3)) {
                    longValue = (longValue * 1000) + (((Long) this.fieldValues.get(chronoField3)).longValue() % 1000);
                    updateCheckConflict(chronoField2, chronoField3, Long.valueOf(longValue));
                    this.fieldValues.remove(chronoField3);
                    map = this.fieldValues;
                    chronoField = ChronoField.NANO_OF_SECOND;
                } else {
                    map = this.fieldValues;
                    chronoField = ChronoField.NANO_OF_SECOND;
                    j = 1000000;
                }
                map.put(chronoField, Long.valueOf(longValue * j));
            } else {
                Map map4 = this.fieldValues;
                ChronoField chronoField4 = ChronoField.MICRO_OF_SECOND;
                if (map4.containsKey(chronoField4)) {
                    this.fieldValues.put(ChronoField.NANO_OF_SECOND, Long.valueOf(((Long) this.fieldValues.remove(chronoField4)).longValue() * 1000));
                }
            }
            Map map5 = this.fieldValues;
            ChronoField chronoField5 = ChronoField.HOUR_OF_DAY;
            Long l = (Long) map5.get(chronoField5);
            if (l != null) {
                Map map6 = this.fieldValues;
                ChronoField chronoField6 = ChronoField.MINUTE_OF_HOUR;
                Long l2 = (Long) map6.get(chronoField6);
                Map map7 = this.fieldValues;
                ChronoField chronoField7 = ChronoField.SECOND_OF_MINUTE;
                Long l3 = (Long) map7.get(chronoField7);
                Map map8 = this.fieldValues;
                ChronoField chronoField8 = ChronoField.NANO_OF_SECOND;
                Long l4 = (Long) map8.get(chronoField8);
                if (l2 == null && (l3 != null || l4 != null)) {
                    return;
                }
                if (l2 != null && l3 == null && l4 != null) {
                    return;
                }
                resolveTime(l.longValue(), l2 != null ? l2.longValue() : 0L, l3 != null ? l3.longValue() : 0L, l4 != null ? l4.longValue() : 0L);
                this.fieldValues.remove(chronoField5);
                this.fieldValues.remove(chronoField6);
                this.fieldValues.remove(chronoField7);
                this.fieldValues.remove(chronoField8);
            }
        }
        if (this.resolverStyle == ResolverStyle.LENIENT || this.fieldValues.size() <= 0) {
            return;
        }
        for (Map.Entry entry : this.fieldValues.entrySet()) {
            TemporalField temporalField = (TemporalField) entry.getKey();
            if ((temporalField instanceof ChronoField) && temporalField.isTimeBased()) {
                ((ChronoField) temporalField).checkValidValue(((Long) entry.getValue()).longValue());
            }
        }
    }

    private void updateCheckConflict(LocalTime localTime, Period period) {
        LocalTime localTime2 = this.time;
        if (localTime2 == null) {
            this.time = localTime;
        } else if (!localTime2.equals(localTime)) {
            LocalTime localTime3 = this.time;
            throw new DateTimeException("Conflict found: Fields resolved to different times: " + localTime3 + " " + localTime);
        } else if (!this.excessDays.isZero() && !period.isZero() && !this.excessDays.equals(period)) {
            Period period2 = this.excessDays;
            throw new DateTimeException("Conflict found: Fields resolved to different excess periods: " + period2 + " " + period);
        }
        this.excessDays = period;
    }

    private void updateCheckConflict(ChronoLocalDate chronoLocalDate) {
        ChronoLocalDate chronoLocalDate2 = this.date;
        if (chronoLocalDate2 != null) {
            if (chronoLocalDate == null || chronoLocalDate2.equals(chronoLocalDate)) {
                return;
            }
            ChronoLocalDate chronoLocalDate3 = this.date;
            throw new DateTimeException("Conflict found: Fields resolved to two different dates: " + chronoLocalDate3 + " " + chronoLocalDate);
        } else if (chronoLocalDate != null) {
            if (this.chrono.equals(chronoLocalDate.getChronology())) {
                this.date = chronoLocalDate;
                return;
            }
            Chronology chronology = this.chrono;
            throw new DateTimeException("ChronoLocalDate must use the effective parsed chronology: " + chronology);
        }
    }

    private void updateCheckConflict(TemporalField temporalField, TemporalField temporalField2, Long l) {
        Long l2 = (Long) this.fieldValues.put(temporalField2, l);
        if (l2 == null || l2.longValue() == l.longValue()) {
            return;
        }
        throw new DateTimeException("Conflict found: " + temporalField2 + " " + l2 + " differs from " + temporalField2 + " " + l + " while resolving  " + temporalField);
    }

    public Parsed copy() {
        Parsed parsed = new Parsed();
        parsed.fieldValues.putAll(this.fieldValues);
        parsed.zone = this.zone;
        parsed.chrono = this.chrono;
        parsed.leapSecond = this.leapSecond;
        return parsed;
    }

    @Override
    public int get(TemporalField temporalField) {
        return TemporalAccessor.-CC.$default$get(this, temporalField);
    }

    @Override
    public long getLong(TemporalField temporalField) {
        Objects.requireNonNull(temporalField, "field");
        Long l = (Long) this.fieldValues.get(temporalField);
        if (l != null) {
            return l.longValue();
        }
        ChronoLocalDate chronoLocalDate = this.date;
        if (chronoLocalDate == null || !chronoLocalDate.isSupported(temporalField)) {
            LocalTime localTime = this.time;
            if (localTime == null || !localTime.isSupported(temporalField)) {
                if (temporalField instanceof ChronoField) {
                    throw new UnsupportedTemporalTypeException("Unsupported field: " + temporalField);
                }
                return temporalField.getFrom(this);
            }
            return this.time.getLong(temporalField);
        }
        return this.date.getLong(temporalField);
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        if (this.fieldValues.containsKey(temporalField)) {
            return true;
        }
        ChronoLocalDate chronoLocalDate = this.date;
        if (chronoLocalDate == null || !chronoLocalDate.isSupported(temporalField)) {
            LocalTime localTime = this.time;
            if (localTime == null || !localTime.isSupported(temporalField)) {
                return (temporalField == null || (temporalField instanceof ChronoField) || !temporalField.isSupportedBy(this)) ? false : true;
            }
            return true;
        }
        return true;
    }

    @Override
    public Object query(TemporalQuery temporalQuery) {
        if (temporalQuery == TemporalQueries.zoneId()) {
            return this.zone;
        }
        if (temporalQuery == TemporalQueries.chronology()) {
            return this.chrono;
        }
        if (temporalQuery == TemporalQueries.localDate()) {
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null) {
                return LocalDate.from(chronoLocalDate);
            }
            return null;
        } else if (temporalQuery == TemporalQueries.localTime()) {
            return this.time;
        } else {
            if (temporalQuery != TemporalQueries.offset()) {
                if (temporalQuery != TemporalQueries.zone() && temporalQuery == TemporalQueries.precision()) {
                    return null;
                }
                return temporalQuery.queryFrom(this);
            }
            Long l = (Long) this.fieldValues.get(ChronoField.OFFSET_SECONDS);
            if (l != null) {
                return ZoneOffset.ofTotalSeconds(l.intValue());
            }
            ZoneId zoneId = this.zone;
            return zoneId instanceof ZoneOffset ? zoneId : temporalQuery.queryFrom(this);
        }
    }

    @Override
    public ValueRange range(TemporalField temporalField) {
        return TemporalAccessor.-CC.$default$range(this, temporalField);
    }

    public TemporalAccessor resolve(ResolverStyle resolverStyle, Set set) {
        if (set != null) {
            this.fieldValues.keySet().retainAll(set);
        }
        this.resolverStyle = resolverStyle;
        resolveFields();
        resolveTimeLenient();
        crossCheck();
        resolvePeriod();
        resolveFractional();
        resolveInstant();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append(this.fieldValues);
        sb.append(',');
        sb.append(this.chrono);
        if (this.zone != null) {
            sb.append(',');
            sb.append(this.zone);
        }
        if (this.date != null || this.time != null) {
            sb.append(" resolved to ");
            ChronoLocalDate chronoLocalDate = this.date;
            if (chronoLocalDate != null) {
                sb.append(chronoLocalDate);
                if (this.time != null) {
                    sb.append('T');
                }
            }
            sb.append(this.time);
        }
        return sb.toString();
    }
}
