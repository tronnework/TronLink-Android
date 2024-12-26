package org.tron.common.utils;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.protobuf.ByteString;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class Sha256Hash implements Serializable, Comparable<Sha256Hash> {
    public static final int LENGTH = 32;
    public static final Sha256Hash ZERO_HASH = wrap(new byte[32]);
    private final byte[] bytes;

    public byte[] getBytes() {
        return this.bytes;
    }

    private byte[] generateBlockId(long j, Sha256Hash sha256Hash) {
        byte[] byteArray = Longs.toByteArray(j);
        byte[] bArr = new byte[sha256Hash.getBytes().length];
        System.arraycopy(byteArray, 0, bArr, 0, 8);
        System.arraycopy(sha256Hash.getBytes(), 8, bArr, 8, sha256Hash.getBytes().length - 8);
        return bArr;
    }

    private byte[] generateBlockId(long j, byte[] bArr) {
        byte[] byteArray = Longs.toByteArray(j);
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(byteArray, 0, bArr2, 0, 8);
        System.arraycopy(bArr, 8, bArr2, 8, bArr.length - 8);
        return bArr2;
    }

    public Sha256Hash(long j, byte[] bArr) {
        byte[] generateBlockId = generateBlockId(j, bArr);
        Preconditions.checkArgument(generateBlockId.length == 32);
        this.bytes = generateBlockId;
    }

    public Sha256Hash(long j, Sha256Hash sha256Hash) {
        byte[] generateBlockId = generateBlockId(j, sha256Hash);
        Preconditions.checkArgument(generateBlockId.length == 32);
        this.bytes = generateBlockId;
    }

    @Deprecated
    public Sha256Hash(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 32);
        this.bytes = bArr;
    }

    public static Sha256Hash wrap(byte[] bArr) {
        return new Sha256Hash(bArr);
    }

    public static Sha256Hash wrap(ByteString byteString) {
        return wrap(byteString.toByteArray());
    }

    @Deprecated
    public static Sha256Hash create(byte[] bArr) {
        return of(bArr);
    }

    public static Sha256Hash of(byte[] bArr) {
        return wrap(hash(bArr));
    }

    public static Sha256Hash of(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Sha256Hash of = of(ByteStreams.toByteArray(fileInputStream));
            fileInputStream.close();
            return of;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Deprecated
    public static Sha256Hash createDouble(byte[] bArr) {
        return twiceOf(bArr);
    }

    public static Sha256Hash twiceOf(byte[] bArr) {
        return wrap(hashTwice(bArr));
    }

    public static MessageDigest newDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hash(byte[] bArr) {
        return hash(bArr, 0, bArr.length);
    }

    public static byte[] hash(byte[] bArr, int i, int i2) {
        MessageDigest newDigest = newDigest();
        newDigest.update(bArr, i, i2);
        return newDigest.digest();
    }

    public static byte[] hashTwice(byte[] bArr) {
        return hashTwice(bArr, 0, bArr.length);
    }

    public static byte[] hashTwice(byte[] bArr, int i, int i2) {
        MessageDigest newDigest = newDigest();
        newDigest.update(bArr, i, i2);
        return newDigest.digest(newDigest.digest());
    }

    public static byte[] hashTwice(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        MessageDigest newDigest = newDigest();
        newDigest.update(bArr, i, i2);
        newDigest.update(bArr2, i3, i4);
        return newDigest.digest(newDigest.digest());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Sha256Hash)) {
            return false;
        }
        return Arrays.equals(this.bytes, ((Sha256Hash) obj).bytes);
    }

    public String toString() {
        return ByteArray.toHexString(this.bytes);
    }

    public int hashCode() {
        byte[] bArr = this.bytes;
        return Ints.fromBytes(bArr[28], bArr[29], bArr[30], bArr[31]);
    }

    public BigInteger toBigInteger() {
        return new BigInteger(1, this.bytes);
    }

    public ByteString getByteString() {
        return ByteString.copyFrom(this.bytes);
    }

    @Override
    public int compareTo(Sha256Hash sha256Hash) {
        for (int i = 31; i >= 0; i--) {
            int i2 = this.bytes[i] & 255;
            int i3 = sha256Hash.bytes[i] & 255;
            if (i2 > i3) {
                return 1;
            }
            if (i2 < i3) {
                return -1;
            }
        }
        return 0;
    }
}
