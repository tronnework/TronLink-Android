package j$.time;
public abstract class DesugarLocalDate$ExternalSyntheticBackport1 {
    public static long m(long j, long j2) {
        long j3 = j / j2;
        return (j - (j2 * j3) != 0 && (((j ^ j2) >> 63) | 1) < 0) ? j3 - 1 : j3;
    }
}
