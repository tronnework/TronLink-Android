package org.tron.common.crypto.cryptohash;
public class Keccak512 extends KeccakCore {
    @Override
    protected byte[] engineDigest() {
        return null;
    }

    @Override
    public int engineGetDigestLength() {
        return 64;
    }

    @Override
    protected void engineUpdate(byte b) {
    }

    @Override
    protected void engineUpdate(byte[] bArr, int i, int i2) {
    }

    @Override
    public int getBlockLength() {
        return super.getBlockLength();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Keccak512() {
        super("tron-keccak-512");
    }

    @Override
    public Digest copy() {
        return copyState((KeccakCore) new Keccak512());
    }
}
