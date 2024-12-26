package org.tron.common.crypto.cryptohash;
public interface Digest {
    Digest copy();

    int digest(byte[] bArr, int i, int i2);

    byte[] digest();

    byte[] digest(byte[] bArr);

    int getBlockLength();

    int getDigestLength();

    void reset();

    String toString();

    void update(byte b);

    void update(byte[] bArr);

    void update(byte[] bArr, int i, int i2);
}
