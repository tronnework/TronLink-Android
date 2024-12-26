package org.tron.common.crypto.cryptohash;

import java.security.MessageDigest;
public abstract class DigestEngine extends MessageDigest implements Digest {
    private long blockCount;
    private int blockLen;
    private int digestLen;
    private byte[] inputBuf;
    private int inputLen;
    private byte[] outputBuf;

    protected abstract void doInit();

    protected abstract void doPadding(byte[] bArr, int i);

    @Override
    protected abstract void engineReset();

    public final int flush() {
        return this.inputLen;
    }

    public final byte[] getBlockBuffer() {
        return this.inputBuf;
    }

    protected long getBlockCount() {
        return this.blockCount;
    }

    protected abstract void processBlock(byte[] bArr);

    public DigestEngine(String str) {
        super(str);
        doInit();
        this.digestLen = engineGetDigestLength();
        int internalBlockLength = getInternalBlockLength();
        this.blockLen = internalBlockLength;
        this.inputBuf = new byte[internalBlockLength];
        this.outputBuf = new byte[this.digestLen];
        this.inputLen = 0;
        this.blockCount = 0L;
    }

    private void adjustDigestLen() {
        if (this.digestLen == 0) {
            int engineGetDigestLength = engineGetDigestLength();
            this.digestLen = engineGetDigestLength;
            this.outputBuf = new byte[engineGetDigestLength];
        }
    }

    @Override
    public byte[] digest() {
        adjustDigestLen();
        int i = this.digestLen;
        byte[] bArr = new byte[i];
        digest(bArr, 0, i);
        return bArr;
    }

    @Override
    public byte[] digest(byte[] bArr) {
        update(bArr, 0, bArr.length);
        return digest();
    }

    @Override
    public int digest(byte[] bArr, int i, int i2) {
        adjustDigestLen();
        if (i2 >= this.digestLen) {
            doPadding(bArr, i);
            reset();
            return this.digestLen;
        }
        doPadding(this.outputBuf, 0);
        System.arraycopy(this.outputBuf, 0, bArr, i, i2);
        reset();
        return i2;
    }

    @Override
    public void reset() {
        engineReset();
        this.inputLen = 0;
        this.blockCount = 0L;
    }

    @Override
    public void update(byte b) {
        byte[] bArr = this.inputBuf;
        int i = this.inputLen;
        int i2 = i + 1;
        this.inputLen = i2;
        bArr[i] = b;
        if (i2 == this.blockLen) {
            processBlock(bArr);
            this.blockCount++;
            this.inputLen = 0;
        }
    }

    @Override
    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    @Override
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int i3 = this.blockLen;
            int i4 = this.inputLen;
            int i5 = i3 - i4;
            if (i5 > i2) {
                i5 = i2;
            }
            System.arraycopy(bArr, i, this.inputBuf, i4, i5);
            i += i5;
            int i6 = this.inputLen + i5;
            this.inputLen = i6;
            i2 -= i5;
            if (i6 == this.blockLen) {
                processBlock(this.inputBuf);
                this.blockCount++;
                this.inputLen = 0;
            }
        }
    }

    protected int getInternalBlockLength() {
        return getBlockLength();
    }

    public Digest copyState(DigestEngine digestEngine) {
        digestEngine.inputLen = this.inputLen;
        digestEngine.blockCount = this.blockCount;
        byte[] bArr = this.inputBuf;
        System.arraycopy(bArr, 0, digestEngine.inputBuf, 0, bArr.length);
        adjustDigestLen();
        digestEngine.adjustDigestLen();
        byte[] bArr2 = this.outputBuf;
        System.arraycopy(bArr2, 0, digestEngine.outputBuf, 0, bArr2.length);
        return digestEngine;
    }
}
