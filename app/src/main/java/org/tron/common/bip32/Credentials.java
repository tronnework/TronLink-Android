package org.tron.common.bip32;
public class Credentials {
    private final String address;
    private final ECKeyPair ecKeyPair;

    public String getAddress() {
        return this.address;
    }

    public ECKeyPair getEcKeyPair() {
        return this.ecKeyPair;
    }

    private Credentials(ECKeyPair eCKeyPair, String str) {
        this.ecKeyPair = eCKeyPair;
        this.address = str;
    }

    public static Credentials create(ECKeyPair eCKeyPair) {
        return new Credentials(eCKeyPair, Numeric.prependHexPrefix(Keys.getAddress(eCKeyPair)));
    }

    public static Credentials create(String str, String str2) {
        return create(new ECKeyPair(Numeric.toBigInt(str), Numeric.toBigInt(str2)));
    }

    public static Credentials create(String str) {
        return create(ECKeyPair.create(Numeric.toBigInt(str)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Credentials credentials = (Credentials) obj;
        ECKeyPair eCKeyPair = this.ecKeyPair;
        if (eCKeyPair == null ? credentials.ecKeyPair == null : eCKeyPair.equals(credentials.ecKeyPair)) {
            String str = this.address;
            String str2 = credentials.address;
            return str != null ? str.equals(str2) : str2 == null;
        }
        return false;
    }

    public int hashCode() {
        ECKeyPair eCKeyPair = this.ecKeyPair;
        int hashCode = (eCKeyPair != null ? eCKeyPair.hashCode() : 0) * 31;
        String str = this.address;
        return hashCode + (str != null ? str.hashCode() : 0);
    }
}
