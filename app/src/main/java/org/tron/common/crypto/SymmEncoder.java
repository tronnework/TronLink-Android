package org.tron.common.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
public class SymmEncoder {
    public static SecretKey restoreSecretKey(byte[] bArr, String str) {
        return new SecretKeySpec(bArr, str);
    }

    public static byte[] AES128EcbEnc(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr2.length != 16 || bArr == null || (bArr.length & 15) != 0) {
            return null;
        }
        return AesEcbEncode(bArr, restoreSecretKey(bArr2, "AES"));
    }

    public static byte[] AES128EcbDec(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr2.length != 16 || bArr == null || (bArr.length & 15) != 0) {
            return null;
        }
        return AesEcbDecode(bArr, restoreSecretKey(bArr2, "AES"));
    }

    private static byte[] AesEcbEncode(byte[] bArr, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(1, secretKey);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] AesEcbDecode(byte[] bArr, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(2, secretKey);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] AESEcbEnc(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr2.length != 16 || bArr == null) {
            return null;
        }
        return AesEcbPKCS7Encode(bArr, restoreSecretKey(bArr2, "AES"));
    }

    public static byte[] AESEcbDec(byte[] bArr, byte[] bArr2) {
        if (bArr2 == null || bArr2.length != 16 || bArr == null) {
            return null;
        }
        return AesEcbPKCS7Decode(bArr, restoreSecretKey(bArr2, "AES"));
    }

    private static byte[] AesEcbPKCS7Encode(byte[] bArr, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(1, secretKey);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] AesEcbPKCS7Decode(byte[] bArr, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(2, secretKey);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
