package com.tron.wallet.common.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.bouncycastle.util.encoders.Base64;
public class ApkCheck {
    public static final String PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVJ9X7DTPPuuVRcwgwfteJiA8UbThKcA6PXbqdwWcjp2pQo6qEvbxpSQbB3X941YRTZlUhnDQ1kstYovSYdTJ9t+NroPafm0SxwBaqGSnDQgQmjOfR22LTeJf8bhuGbZmgFopM5AJs7y6FvH0S6+pbBA4gVTiQEWvvsYaqxWr7NQIDAQAB";

    public static RSAPublicKey loadPublicKeyByStr(String str) throws Exception {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str)));
        } catch (NullPointerException unused) {
            throw new Exception("public key data is empty");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("no such algorithm");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("invalid public key");
        }
    }

    public static byte[] decrypt(RSAPublicKey rSAPublicKey, byte[] bArr) throws Exception {
        if (rSAPublicKey == null) {
            throw new Exception("the decryption public key is empty please set");
        }
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, rSAPublicKey);
            return cipher.doFinal(bArr);
        } catch (InvalidKeyException unused) {
            throw new Exception("The decryption public key is invalid, please check");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("no such decryption algorithm");
        } catch (BadPaddingException unused3) {
            throw new Exception("The ciphertext data is corrupted");
        } catch (IllegalBlockSizeException unused4) {
            throw new Exception("illegal ciphertext length");
        } catch (NoSuchPaddingException e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static boolean apkVerify(String str, String str2, String str3) {
        boolean z = false;
        if (str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            String str4 = new String(decrypt(loadPublicKeyByStr(PUB_KEY), Base64.decode(str2)), "utf8");
            LogUtils.d("apkVerify", "decoded: ".concat(str4));
            int indexOf = str4.indexOf(32);
            if (indexOf >= 0) {
                str4 = str4.substring(0, indexOf);
            }
            boolean equals = str.equals(str4);
            if (equals) {
                try {
                    return str.equals(Md5Util.getMD5(str3).toLowerCase());
                } catch (Exception e) {
                    e = e;
                    z = equals;
                    SentryUtil.captureException(e);
                    return z;
                }
            }
            return equals;
        } catch (Exception e2) {
            e = e2;
        }
    }
}
