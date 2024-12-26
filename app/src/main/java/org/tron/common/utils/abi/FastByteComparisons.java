package org.tron.common.utils.abi;
public abstract class FastByteComparisons {

    public interface Comparer<T> {
        int compareTo(T t, int i, int i2, T t2, int i3, int i4);
    }

    public static boolean isEqual(byte[] bArr, byte[] bArr2) {
        return bArr.length == bArr2.length && compareTo(bArr, 0, bArr.length, bArr2, 0, bArr2.length) == 0;
    }

    public static int compareTo(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        return LexicographicalComparerHolder.BEST_COMPARER.compareTo(bArr, i, i2, bArr2, i3, i4);
    }

    public static Comparer<byte[]> lexicographicalComparerJavaImpl() {
        return LexicographicalComparerHolder.PureJavaComparer.INSTANCE;
    }

    public static class LexicographicalComparerHolder {
        static final String UNSAFE_COMPARER_NAME = LexicographicalComparerHolder.class.getName() + "$UnsafeComparer";
        static final Comparer<byte[]> BEST_COMPARER = getBestComparer();

        private LexicographicalComparerHolder() {
        }

        static Comparer<byte[]> getBestComparer() {
            try {
                return (Comparer) Class.forName(UNSAFE_COMPARER_NAME).getEnumConstants()[0];
            } catch (Throwable unused) {
                return FastByteComparisons.lexicographicalComparerJavaImpl();
            }
        }

        public enum PureJavaComparer implements Comparer<byte[]> {
            INSTANCE;

            @Override
            public int compareTo(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
                if (bArr == bArr2 && i == i3 && i2 == i4) {
                    return 0;
                }
                int i5 = i + i2;
                int i6 = i3 + i4;
                while (i < i5 && i3 < i6) {
                    int i7 = bArr[i] & 255;
                    int i8 = bArr2[i3] & 255;
                    if (i7 != i8) {
                        return i7 - i8;
                    }
                    i++;
                    i3++;
                }
                return i2 - i4;
            }
        }
    }
}
