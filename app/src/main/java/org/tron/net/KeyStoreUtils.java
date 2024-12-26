package org.tron.net;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.crypto.ECKey;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.RomUtils;
import org.tron.net.WalletFile;
import org.tron.walletserver.AddressUtil;
import org.tron.walletserver.Wallet;
public class KeyStoreUtils {
    private static final ObjectMapper objectMapper;

    static {
        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper = objectMapper2;
        objectMapper2.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String getKeyStoreWithPrivate(String str, Wallet wallet) throws CipherException {
        return getKeyStore(str, wallet.getECKey().getPrivKeyBytes(), wallet.getAddress());
    }

    public static String getKeyStoreWithMnemonic(String str, String str2, String str3) throws CipherException {
        return getKeyStore(str, str2.getBytes(), str3);
    }

    public static String getKeyStore(String str, byte[] bArr, String str2) throws CipherException {
        int i = RomUtils.getTotalMemory() > 2 ? 65536 : 16384;
        int length = bArr.length;
        byte[] generateRandomBytes = generateRandomBytes(bArr.length);
        byte[] generateDerivedScryptKey = generateDerivedScryptKey(str.getBytes(StandardCharsets.UTF_8), generateRandomBytes, i, 8, 1, length);
        byte[] copyOfRange = Arrays.copyOfRange(generateDerivedScryptKey, 0, 16);
        byte[] generateRandomBytes2 = generateRandomBytes(16);
        byte[] performCipherOperation = performCipherOperation(1, generateRandomBytes2, copyOfRange, bArr);
        byte[] generateMac = generateMac(generateDerivedScryptKey, performCipherOperation);
        if (AddressUtil.isEmpty(str2)) {
            return "";
        }
        if (AddressUtil.isAddressValid(str2)) {
            str2 = Hex.toHexString(AddressUtil.decodeFromBase58Check(str2));
        }
        return new Gson().toJson(createWalletFile(str2, performCipherOperation, generateRandomBytes2, generateRandomBytes, generateMac, i, 1));
    }

    public static String getPrivateWithKeyStore(String str, String str2) throws CipherException, IOException {
        return ByteArray.toHexString(decrypt(str2, (WalletFile) objectMapper.readValue(str, WalletFile.class)).getPrivKeyBytes());
    }

    public static String getMnemonicWithKeyStore(String str, String str2) throws CipherException, IOException {
        return new String(decryptToByte(str2, (WalletFile) objectMapper.readValue(str, WalletFile.class)));
    }

    private static ECKey decrypt(String str, WalletFile walletFile) throws CipherException {
        return ECKey.fromPrivate(decryptToByte(str, walletFile));
    }

    private static byte[] decryptToByte(String str, WalletFile walletFile) throws CipherException {
        byte[] generateAes128CtrDerivedKey;
        validate(walletFile);
        WalletFile.Crypto crypto = walletFile.getCrypto();
        byte[] fromHexString = ByteArray.fromHexString(crypto.getMac());
        byte[] fromHexString2 = ByteArray.fromHexString(crypto.getCipherparams().getIv());
        byte[] fromHexString3 = ByteArray.fromHexString(crypto.getCiphertext());
        WalletFile.KdfParams kdfparams = crypto.getKdfparams();
        if (kdfparams instanceof WalletFile.ScryptKdfParams) {
            WalletFile.ScryptKdfParams scryptKdfParams = (WalletFile.ScryptKdfParams) crypto.getKdfparams();
            int dklen = scryptKdfParams.getDklen();
            int n = scryptKdfParams.getN();
            int p = scryptKdfParams.getP();
            int r = scryptKdfParams.getR();
            generateAes128CtrDerivedKey = generateDerivedScryptKey(str.getBytes(StandardCharsets.UTF_8), ByteArray.fromHexString(scryptKdfParams.getSalt()), n, r, p, dklen);
        } else if (kdfparams instanceof WalletFile.Aes128CtrKdfParams) {
            WalletFile.Aes128CtrKdfParams aes128CtrKdfParams = (WalletFile.Aes128CtrKdfParams) crypto.getKdfparams();
            int c = aes128CtrKdfParams.getC();
            String prf = aes128CtrKdfParams.getPrf();
            generateAes128CtrDerivedKey = generateAes128CtrDerivedKey(str.getBytes(StandardCharsets.UTF_8), ByteArray.fromHexString(aes128CtrKdfParams.getSalt()), c, prf);
        } else {
            throw new CipherException("Unable to deserialize params: " + crypto.getKdf());
        }
        if (!Arrays.equals(generateMac(generateAes128CtrDerivedKey, fromHexString3), fromHexString)) {
            throw new CipherException("Invalid password provided");
        }
        return performCipherOperation(2, fromHexString2, Arrays.copyOfRange(generateAes128CtrDerivedKey, 0, 16), fromHexString3);
    }

    private static byte[] generateAes128CtrDerivedKey(byte[] bArr, byte[] bArr2, int i, String str) throws CipherException {
        if (!str.equals("hmac-sha256")) {
            throw new CipherException("Unsupported prf:" + str);
        }
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA256Digest());
        pKCS5S2ParametersGenerator.init(bArr, bArr2, i);
        return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(256)).getKey();
    }

    private static void validate(WalletFile walletFile) throws CipherException {
        WalletFile.Crypto crypto = walletFile.getCrypto();
        if (walletFile.getVersion() != 3) {
            throw new CipherException("Wallet version is not supported");
        }
        if (!crypto.getCipher().equals("aes-128-ctr")) {
            throw new CipherException("Wallet cipher is not supported");
        }
        if (!crypto.getKdf().equals("pbkdf2") && !crypto.getKdf().equals("scrypt")) {
            throw new CipherException("KDF type is not supported");
        }
    }

    private static WalletFile createWalletFile(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, int i2) {
        int length = bArr3.length;
        WalletFile walletFile = new WalletFile();
        walletFile.setAddress(str);
        WalletFile.Crypto crypto = new WalletFile.Crypto();
        crypto.setCipher("aes-128-ctr");
        crypto.setCiphertext(ByteArray.toHexString(bArr));
        walletFile.setCrypto(crypto);
        WalletFile.CipherParams cipherParams = new WalletFile.CipherParams();
        cipherParams.setIv(ByteArray.toHexString(bArr2));
        crypto.setCipherparams(cipherParams);
        crypto.setKdf("scrypt");
        WalletFile.ScryptKdfParams scryptKdfParams = new WalletFile.ScryptKdfParams();
        scryptKdfParams.setDklen(length);
        scryptKdfParams.setN(i);
        scryptKdfParams.setP(i2);
        scryptKdfParams.setR(8);
        scryptKdfParams.setSalt(ByteArray.toHexString(bArr3));
        crypto.setKdfparams(scryptKdfParams);
        crypto.setMac(ByteArray.toHexString(bArr4));
        walletFile.setCrypto(crypto);
        walletFile.setId(UUID.randomUUID().toString());
        walletFile.setVersion(3);
        return walletFile;
    }

    public static byte[] generateRandomBytes(int i) {
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private static byte[] generateDerivedScryptKey(byte[] bArr, byte[] bArr2, int i, int i2, int i3, int i4) throws CipherException {
        return SCrypt.generate(bArr, bArr2, i, i2, i3, i4);
    }

    private static byte[] performCipherOperation(int i, byte[] bArr, byte[] bArr2, byte[] bArr3) throws CipherException {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(i, new SecretKeySpec(bArr2, "AES"), ivParameterSpec);
            return cipher.doFinal(bArr3);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new CipherException("Error performing cipher operation", e);
        }
    }

    private static byte[] generateMac(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length + 16];
        System.arraycopy(bArr, 16, bArr3, 0, 16);
        System.arraycopy(bArr2, 0, bArr3, 16, bArr2.length);
        return Hash.sha3(bArr3);
    }
}
