package org.tron.common.crypto.cryptohash;
public class Keccak256 extends KeccakCore {
    @Override
    protected byte[] engineDigest() {
        return null;
    }

    @Override
    public int engineGetDigestLength() {
        return 32;
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

    public Keccak256() {
        super("tron-keccak-256");
    }

    @Override
    public Digest copy() {
        return copyState((KeccakCore) new Keccak256());
    }
}
