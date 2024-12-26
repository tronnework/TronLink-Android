package j$.time;

import com.google.common.base.Ascii;
import j$.time.chrono.Chronology;
import j$.time.chrono.IsoChronology;
import j$.time.format.DateTimeParseException;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalAmount;
import j$.time.temporal.TemporalQueries;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bouncycastle.i18n.TextBundle;
public final class Period implements TemporalAmount, Serializable {
    private static final List SUPPORTED_UNITS;
    private static final long serialVersionUID = -3587258372562876L;
    private final int days;
    private final int months;
    private final int years;
    public static final Period ZERO = new Period(0, 0, 0);
    private static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);

    static {
        List m;
        m = Duration$DurationUnits$ExternalSyntheticBackport1.m(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
        SUPPORTED_UNITS = m;
    }

    private Period(int i, int i2, int i3) {
        this.years = i;
        this.months = i2;
        this.days = i3;
    }

    private static boolean charMatch(CharSequence charSequence, int i, int i2, char c) {
        return i >= 0 && i2 == i + 1 && charSequence.charAt(i) == c;
    }

    private static Period create(int i, int i2, int i3) {
        return ((i | i2) | i3) == 0 ? ZERO : new Period(i, i2, i3);
    }

    public static Period of(int i, int i2, int i3) {
        return create(i, i2, i3);
    }

    public static Period ofDays(int i) {
        return create(0, 0, i);
    }

    public static Period parse(CharSequence charSequence) {
        Objects.requireNonNull(charSequence, TextBundle.TEXT_ENTRY);
        Matcher matcher = PATTERN.matcher(charSequence);
        if (matcher.matches()) {
            int i = charMatch(charSequence, matcher.start(1), matcher.end(1), '-') ? -1 : 1;
            int start = matcher.start(2);
            int end = matcher.end(2);
            int start2 = matcher.start(3);
            int end2 = matcher.end(3);
            int start3 = matcher.start(4);
            int end3 = matcher.end(4);
            int start4 = matcher.start(5);
            int end4 = matcher.end(5);
            if (start >= 0 || start2 >= 0 || start3 >= 0 || start4 >= 0) {
                try {
                    return create(parseNumber(charSequence, start, end, i), parseNumber(charSequence, start2, end2, i), Period$ExternalSyntheticBackport3.m(parseNumber(charSequence, start4, end4, i), Period$ExternalSyntheticBackport1.m(parseNumber(charSequence, start3, end3, i), 7)));
                } catch (NumberFormatException e) {
                    throw new DateTimeParseException("Text cannot be parsed to a Period", charSequence, 0, e);
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Period", charSequence, 0);
    }

    private static int parseNumber(CharSequence charSequence, int i, int i2, int i3) {
        int parseInt;
        if (i < 0 || i2 < 0) {
            return 0;
        }
        if (charSequence.charAt(i) == '+') {
            i++;
        }
        parseInt = Integer.parseInt(charSequence.subSequence(i, i2).toString(), 10);
        try {
            return Period$ExternalSyntheticBackport1.m(parseInt, i3);
        } catch (ArithmeticException e) {
            throw new DateTimeParseException("Text cannot be parsed to a Period", charSequence, 0, e);
        }
    }

    public static Period readExternal(DataInput dataInput) {
        return of(dataInput.readInt(), dataInput.readInt(), dataInput.readInt());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private void validateChrono(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        Chronology chronology = (Chronology) temporalAccessor.query(TemporalQueries.chronology());
        if (chronology == null || IsoChronology.INSTANCE.equals(chronology)) {
            return;
        }
        String id = chronology.getId();
        throw new DateTimeException("Chronology mismatch, expected: ISO, actual: " + id);
    }

    private Object writeReplace() {
        return new Ser(Ascii.SO, this);
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        long totalMonths;
        ChronoUnit chronoUnit;
        validateChrono(temporal);
        if (this.months == 0) {
            int i = this.years;
            if (i != 0) {
                totalMonths = i;
                chronoUnit = ChronoUnit.YEARS;
                temporal = temporal.plus(totalMonths, chronoUnit);
            }
        } else {
            totalMonths = toTotalMonths();
            if (totalMonths != 0) {
                chronoUnit = ChronoUnit.MONTHS;
                temporal = temporal.plus(totalMonths, chronoUnit);
            }
        }
        int i2 = this.days;
        return i2 != 0 ? temporal.plus(i2, ChronoUnit.DAYS) : temporal;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Period) {
            Period period = (Period) obj;
            return this.years == period.years && this.months == period.months && this.days == period.days;
        }
        return false;
    }

    public int getDays() {
        return this.days;
    }

    public int hashCode() {
        return this.years + Integer.rotateLeft(this.months, 8) + Integer.rotateLeft(this.days, 16);
    }

    public boolean isZero() {
        return this == ZERO;
    }

    public String toString() {
        if (this == ZERO) {
            return "P0D";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('P');
        int i = this.years;
        if (i != 0) {
            sb.append(i);
            sb.append('Y');
        }
        int i2 = this.months;
        if (i2 != 0) {
            sb.append(i2);
            sb.append('M');
        }
        int i3 = this.days;
        if (i3 != 0) {
            sb.append(i3);
            sb.append('D');
        }
        return sb.toString();
    }

    public long toTotalMonths() {
        return (this.years * 12) + this.months;
    }

    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeInt(this.years);
        dataOutput.writeInt(this.months);
        dataOutput.writeInt(this.days);
    }
}
