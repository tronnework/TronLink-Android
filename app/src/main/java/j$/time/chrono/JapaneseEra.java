package j$.time.chrono;

import androidx.exifinterface.media.ExifInterface;
import com.tron.wallet.common.config.TronConfig;
import j$.time.DateTimeException;
import j$.time.LocalDate;
import j$.time.chrono.Era;
import j$.time.temporal.ChronoField;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.time.temporal.TemporalField;
import j$.time.temporal.TemporalQuery;
import j$.time.temporal.ValueRange;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import okhttp3.internal.http2.Http2Connection;
public final class JapaneseEra implements Era, Serializable {
    public static final JapaneseEra HEISEI;
    private static final JapaneseEra[] KNOWN_ERAS;
    public static final JapaneseEra MEIJI;
    private static final int N_ERA_CONSTANTS;
    public static final JapaneseEra REIWA;
    public static final JapaneseEra SHOWA;
    public static final JapaneseEra TAISHO;
    private static final long serialVersionUID = 1466499369062886794L;
    private final transient String abbreviation;
    private final transient int eraValue;
    private final transient String name;
    private final transient LocalDate since;

    static {
        JapaneseEra japaneseEra = new JapaneseEra(-1, LocalDate.of(1868, 1, 1), "Meiji", "M");
        MEIJI = japaneseEra;
        JapaneseEra japaneseEra2 = new JapaneseEra(0, LocalDate.of(1912, 7, 30), "Taisho", ExifInterface.GPS_DIRECTION_TRUE);
        TAISHO = japaneseEra2;
        JapaneseEra japaneseEra3 = new JapaneseEra(1, LocalDate.of(1926, 12, 25), "Showa", ExifInterface.LATITUDE_SOUTH);
        SHOWA = japaneseEra3;
        JapaneseEra japaneseEra4 = new JapaneseEra(2, LocalDate.of(1989, 1, 8), "Heisei", "H");
        HEISEI = japaneseEra4;
        JapaneseEra japaneseEra5 = new JapaneseEra(3, LocalDate.of((int) TronConfig.TRANSACTION_SIGN_REQUEST_CODE, 5, 1), "Reiwa", "R");
        REIWA = japaneseEra5;
        int value = japaneseEra5.getValue() + 2;
        N_ERA_CONSTANTS = value;
        JapaneseEra[] japaneseEraArr = new JapaneseEra[value];
        KNOWN_ERAS = japaneseEraArr;
        japaneseEraArr[0] = japaneseEra;
        japaneseEraArr[1] = japaneseEra2;
        japaneseEraArr[2] = japaneseEra3;
        japaneseEraArr[3] = japaneseEra4;
        japaneseEraArr[4] = japaneseEra5;
    }

    private JapaneseEra(int i, LocalDate localDate, String str, String str2) {
        this.eraValue = i;
        this.since = localDate;
        this.name = str;
        this.abbreviation = str2;
    }

    public static JapaneseEra from(LocalDate localDate) {
        if (localDate.isBefore(JapaneseDate.MEIJI_6_ISODATE)) {
            throw new DateTimeException("JapaneseDate before Meiji 6 are not supported");
        }
        for (int length = KNOWN_ERAS.length - 1; length >= 0; length--) {
            JapaneseEra japaneseEra = KNOWN_ERAS[length];
            if (localDate.compareTo((ChronoLocalDate) japaneseEra.since) >= 0) {
                return japaneseEra;
            }
        }
        return null;
    }

    public static JapaneseEra getCurrentEra() {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        return japaneseEraArr[japaneseEraArr.length - 1];
    }

    public static JapaneseEra of(int i) {
        int ordinal = ordinal(i);
        if (ordinal >= 0) {
            JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
            if (ordinal < japaneseEraArr.length) {
                return japaneseEraArr[ordinal];
            }
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    private static int ordinal(int i) {
        return i + 1;
    }

    public static JapaneseEra readExternal(DataInput dataInput) {
        return of(dataInput.readByte());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public static long shortestDaysOfYear() {
        JapaneseEra[] japaneseEraArr;
        long smallestMaximum = ChronoField.DAY_OF_YEAR.range().getSmallestMaximum();
        for (JapaneseEra japaneseEra : KNOWN_ERAS) {
            smallestMaximum = Math.min(smallestMaximum, (japaneseEra.since.lengthOfYear() - japaneseEra.since.getDayOfYear()) + 1);
            if (japaneseEra.next() != null) {
                smallestMaximum = Math.min(smallestMaximum, japaneseEra.next().since.getDayOfYear() - 1);
            }
        }
        return smallestMaximum;
    }

    public static long shortestYearsOfEra() {
        int year = Http2Connection.DEGRADED_PONG_TIMEOUT_NS - getCurrentEra().since.getYear();
        int year2 = KNOWN_ERAS[0].since.getYear();
        int i = 1;
        while (true) {
            JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
            if (i >= japaneseEraArr.length) {
                return year;
            }
            JapaneseEra japaneseEra = japaneseEraArr[i];
            year = Math.min(year, (japaneseEra.since.getYear() - year2) + 1);
            year2 = japaneseEra.since.getYear();
            i++;
        }
    }

    public static JapaneseEra[] values() {
        JapaneseEra[] japaneseEraArr = KNOWN_ERAS;
        return (JapaneseEra[]) Arrays.copyOf(japaneseEraArr, japaneseEraArr.length);
    }

    private Object writeReplace() {
        return new Ser((byte) 5, this);
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

    String getName() {
        return this.name;
    }

    public LocalDate getSince() {
        return this.since;
    }

    @Override
    public int getValue() {
        return this.eraValue;
    }

    @Override
    public boolean isSupported(TemporalField temporalField) {
        return Era.-CC.$default$isSupported(this, temporalField);
    }

    public JapaneseEra next() {
        if (this == getCurrentEra()) {
            return null;
        }
        return of(this.eraValue + 1);
    }

    @Override
    public Object query(TemporalQuery temporalQuery) {
        return Era.-CC.$default$query(this, temporalQuery);
    }

    @Override
    public ValueRange range(TemporalField temporalField) {
        ValueRange $default$range;
        ChronoField chronoField = ChronoField.ERA;
        if (temporalField == chronoField) {
            return JapaneseChronology.INSTANCE.range(chronoField);
        }
        $default$range = TemporalAccessor.-CC.$default$range(this, temporalField);
        return $default$range;
    }

    public String toString() {
        return getName();
    }

    public void writeExternal(DataOutput dataOutput) {
        dataOutput.writeByte(getValue());
    }
}
