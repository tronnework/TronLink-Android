package org.tron.common.crypto;

import java.security.SignatureException;
public interface SignInterface {
    byte[] Base64toBytes(String str);

    byte[] getAddress();

    byte[] getNodeId();

    byte[] getPrivKeyBytes();

    byte[] getPrivateKey();

    byte[] getPubKey();

    byte[] hash(byte[] bArr);

    SignatureInterface sign(byte[] bArr);

    String signHash(byte[] bArr);

    byte[] signToAddress(byte[] bArr, String str) throws SignatureException;
}
