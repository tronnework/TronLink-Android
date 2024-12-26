package j$.time;

import j$.time.format.DateTimeParseException;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAmount;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ClassUtils;
import org.bouncycastle.i18n.TextBundle;
public final class Duration implements TemporalAmount, Comparable<Duration>, Serializable {
    private static final long serialVersionUID = 3078945930695997490L;
    private final int nanos;
    private final long seconds;
    public static final Duration ZERO = new Duration(0, 0);
    private static final BigInteger BI_NANOS_PER_SECOND = BigInteger.valueOf(1000000000);

    private static class Lazy {
        static final Pattern PATTERN = Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)D)?(T(?:([-+]?[0-9]+)H)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)(?:[.,]([0-9]{0,9}))?S)?)?", 2);
    }

    private Duration(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    private static boolean charMatch(CharSequence charSequence, int i, int i2, char c) {
        return i >= 0 && i2 == i + 1 && charSequence.charAt(i) == c;
    }

    private static Duration create(long j, int i) {
        return (((long) i) | j) == 0 ? ZERO : new Duration(j, i);
    }

    private static Duration create(BigDecimal bigDecimal) {
        BigInteger bigIntegerExact = bigDecimal.movePointRight(9).toBigIntegerExact();
        BigInteger[] divideAndRemainder = bigIntegerExact.divideAndRemainder(BI_NANOS_PER_SECOND);
        if (divideAndRemainder[0].bitLength() <= 63) {
            return ofSeconds(divideAndRemainder[0].longValue(), divideAndRemainder[1].intValue());
        }
        throw new ArithmeticException("Exceeds capacity of Duration: " + bigIntegerExact);
    }

    private static Duration create(boolean z, long j, long j2, long j3, long j4, int i) {
        long m = Clock$OffsetClock$ExternalSyntheticBackport0.m(j, Clock$OffsetClock$ExternalSyntheticBackport0.m(j2, Clock$OffsetClock$ExternalSyntheticBackport0.m(j3, j4)));
        long j5 = i;
        return z ? ofSeconds(m, j5).negated() : ofSeconds(m, j5);
    }

    public static Duration ofNanos(long j) {
        long j2 = j / 1000000000;
        int i = (int) (j % 1000000000);
        if (i < 0) {
            i = (int) (i + 1000000000);
            j2--;
        }
        return create(j2, i);
    }

    public static Duration ofSeconds(long j) {
        return create(j, 0);
    }

    public static Duration ofSeconds(long j, long j2) {
        return create(Clock$OffsetClock$ExternalSyntheticBackport0.m(j, DesugarLocalDate$ExternalSyntheticBackport1.m(j2, 1000000000L)), (int) Clock$TickClock$ExternalSyntheticBackport0.m(j2, 1000000000L));
    }

    public static Duration parse(CharSequence charSequence) {
        Objects.requireNonNull(charSequence, TextBundle.TEXT_ENTRY);
        Matcher matcher = Lazy.PATTERN.matcher(charSequence);
        if (matcher.matches() && !charMatch(charSequence, matcher.start(3), matcher.end(3), 'T')) {
            int i = 1;
            boolean charMatch = charMatch(charSequence, matcher.start(1), matcher.end(1), '-');
            int start = matcher.start(2);
            int end = matcher.end(2);
            int start2 = matcher.start(4);
            int end2 = matcher.end(4);
            int start3 = matcher.start(5);
            int end3 = matcher.end(5);
            int start4 = matcher.start(6);
            int end4 = matcher.end(6);
            int start5 = matcher.start(7);
            int end5 = matcher.end(7);
            if (start >= 0 || start2 >= 0 || start3 >= 0 || start4 >= 0) {
                long parseNumber = parseNumber(charSequence, start, end, 86400, "days");
                long parseNumber2 = parseNumber(charSequence, start2, end2, 3600, "hours");
                long parseNumber3 = parseNumber(charSequence, start3, end3, 60, "minutes");
                long parseNumber4 = parseNumber(charSequence, start4, end4, 1, "seconds");
                if (start4 >= 0 && charSequence.charAt(start4) == '-') {
                    i = -1;
                }
                try {
                    return create(charMatch, parseNumber, parseNumber2, parseNumber3, parseNumber4, parseFraction(charSequence, start5, end5, i));
                } catch (ArithmeticException e) {
                    throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: overflow", charSequence, 0).initCause(e));
                }
            }
        }
        throw new DateTimeParseException("Text cannot be parsed to a Duration", charSequence, 0);
    }

    private static int parseFraction(CharSequence charSequence, int i, int i2, int i3) {
        int i4;
        int parseInt;
        if (i < 0 || i2 < 0 || (i4 = i2 - i) == 0) {
            return 0;
        }
        try {
            parseInt = Integer.parseInt(charSequence.subSequence(i, i2).toString(), 10);
            for (i4 = i2 - i; i4 < 9; i4++) {
                parseInt *= 10;
            }
            return parseInt * i3;
        } catch (ArithmeticException | NumberFormatException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: fraction", charSequence, 0).initCause(e));
        }
    }

    private static long parseNumber(CharSequence charSequence, int i, int i2, int i3, String str) {
        long parseLong;
        long m;
        if (i < 0 || i2 < 0) {
            return 0L;
        }
        try {
            parseLong = Long.parseLong(charSequence.subSequence(i, i2).toString(), 10);
            m = Duration$ExternalSyntheticBackport0.m(parseLong, i3);
            return m;
        } catch (ArithmeticException | NumberFormatException e) {
            throw ((DateTimeParseException) new DateTimeParseException("Text cannot be parsed to a Duration: " + str, charSequence, 0).initCause(e));
        }
    }

    public static Duration readExternal(DataInput dataInput) {
        return ofSeconds(dataInput.readLong(), dataInput.readInt());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private BigDecimal toBigDecimalSeconds() {
        return BigDecimal.valueOf(this.seconds).add(BigDecimal.valueOf(this.nanos, 9));
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    @Override
    public Temporal addTo(Temporal temporal) {
        long j = this.seconds;
        if (j != 0) {
            temporal = temporal.plus(j, ChronoUnit.SECONDS);
        }
        int i = this.nanos;
        return i != 0 ? temporal.plus(i, ChronoUnit.NANOS) : temporal;
    }

    @Override
    public int compareTo(Duration duration) {
        int compare = Long.compare(this.seconds, duration.seconds);
        return compare != 0 ? compare : this.nanos - duration.nanos;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Duration) {
            Duration duration = (Duration) obj;
            return this.seconds == duration.seconds && this.nanos == duration.nanos;
        }
        return false;
    }

    public int getNano() {
        return this.nanos;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int hashCode() {
        long j = this.seconds;
        return ((int) (j ^ (j >>> 32))) + (this.nanos * 51);
    }

    public Duration multipliedBy(long j) {
        return j == 0 ? ZERO : j == 1 ? this : create(toBigDecimalSeconds().multiply(BigDecimal.valueOf(j)));
    }

    public Duration negated() {
        return multipliedBy(-1L);
    }

    public long toMillis() {
        long m;
        long j = this.seconds;
        long j2 = this.nanos;
        if (j < 0) {
            j++;
            j2 -= 1000000000;
        }
        m = Duration$ExternalSyntheticBackport0.m(j, 1000);
        return Clock$OffsetClock$ExternalSyntheticBackport0.m(m, j2 / 1000000);
    }

    public String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        long j = this.seconds;
        if (j < 0 && this.nanos > 0) {
            j++;
        }
        long j2 = j / 3600;
        int i = (int) ((j % 3600) / 60);
        int i2 = (int) (j % 60);
        StringBuilder sb = new StringBuilder(24);
        sb.append("PT");
        if (j2 != 0) {
            sb.append(j2);
            sb.append('H');
        }
        if (i != 0) {
            sb.append(i);
            sb.append('M');
        }
        if (i2 == 0 && this.nanos == 0 && sb.length() > 2) {
            return sb.toString();
        }
        if (this.seconds >= 0 || this.nanos <= 0 || i2 != 0) {
            sb.append(i2);
        } else {
            sb.append("-0");
        }
        if (this.nanos > 0) {
            int length = sb.length();
            int i3 = (this.seconds > 0L ? 1 : (this.seconds == 0L ? 0 : -1));
            long j3 = this.nanos;
            if (i3 < 0) {
                sb.append(2000000000 - j3);
            } else {
                sb.append(j3 + 1000000000);
            }
            while (sb.charAt(sb.length() - 1) == '0') {
                sb.setLength(sb.length() - 1);
            }
            sb.setCharAt(length, ClassUtils.PACKAGE_SEPARATOR_CHAR);
        }
        sb.append('S');
        return sb.toString();
    }

    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeLong(this.seconds);
        dataOutput.writeInt(this.nanos);
    }
}
