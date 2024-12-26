package org.tron.common.crypto;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.StringTokenizer;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.tron.common.bip39.BIP39;
public class MnemonicUtils {
    private static final int SEED_ITERATIONS = 2048;
    private static final int SEED_KEY_SIZE = 512;
    private static List<String> WORD_LIST;

    private static boolean isBitSet(int i, int i2) {
        return ((i >> i2) & 1) == 1;
    }

    private static boolean toBit(byte b, int i) {
        return ((b >>> (7 - i)) & 1) > 0;
    }

    public static String generateMnemonic(byte[] bArr) {
        if (WORD_LIST == null) {
            WORD_LIST = populateWordList();
        }
        validateEntropy(bArr);
        int length = bArr.length * 8;
        boolean[] convertToBits = convertToBits(bArr, calculateChecksum(bArr));
        int i = (length + (length / 32)) / 11;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(WORD_LIST.get(toInt(nextElevenBits(convertToBits, i2))));
            if (i2 < i - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public static byte[] generateEntropy(String str) {
        if (WORD_LIST == null) {
            WORD_LIST = populateWordList();
        }
        BitSet bitSet = new BitSet();
        int mnemonicToBits = mnemonicToBits(str, bitSet);
        if (mnemonicToBits == 0) {
            throw new IllegalArgumentException("Empty mnemonic");
        }
        int i = (mnemonicToBits * 32) / 33;
        if (i % 8 != 0) {
            throw new IllegalArgumentException("Wrong mnemonic size");
        }
        int i2 = i / 8;
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = readByte(bitSet, i3);
        }
        validateEntropy(bArr);
        if (calculateChecksum(bArr) == readByte(bitSet, i2)) {
            return bArr;
        }
        throw new IllegalArgumentException("Wrong checksum");
    }

    public static byte[] generateSeed(String str, String str2) {
        if (isMnemonicEmpty(str)) {
            throw new IllegalArgumentException("Mnemonic is required to generate a seed");
        }
        if (str2 == null) {
            str2 = "";
        }
        String format = String.format("mnemonic%s", str2);
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA512Digest());
        pKCS5S2ParametersGenerator.init(str.getBytes(StandardCharsets.UTF_8), format.getBytes(StandardCharsets.UTF_8), 2048);
        return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(512)).getKey();
    }

    public static boolean validateMnemonic(String str) {
        try {
            generateEntropy(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean isMnemonicEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private static boolean[] nextElevenBits(boolean[] zArr, int i) {
        int i2 = i * 11;
        return Arrays.copyOfRange(zArr, i2, i2 + 11);
    }

    private static void validateEntropy(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Entropy is required");
        }
        int length = bArr.length * 8;
        if (length < 128 || length > 256 || length % 32 != 0) {
            throw new IllegalArgumentException("The allowed size of ENT is 128-256 bits of multiples of 32");
        }
    }

    private static boolean[] convertToBits(byte[] bArr, byte b) {
        int length = bArr.length * 8;
        int i = length / 32;
        boolean[] zArr = new boolean[length + i];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            for (int i3 = 0; i3 < 8; i3++) {
                zArr[(i2 * 8) + i3] = toBit(bArr[i2], i3);
            }
        }
        for (int i4 = 0; i4 < i; i4++) {
            zArr[length + i4] = toBit(b, i4);
        }
        return zArr;
    }

    private static int toInt(boolean[] zArr) {
        int i = 0;
        for (int i2 = 0; i2 < zArr.length; i2++) {
            if (zArr[i2]) {
                i += 1 << ((zArr.length - i2) - 1);
            }
        }
        return i;
    }

    private static int mnemonicToBits(String str, BitSet bitSet) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ");
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = WORD_LIST.indexOf(nextToken);
            if (indexOf < 0) {
                throw new IllegalArgumentException("Illegal word: " + nextToken);
            }
            int i2 = 0;
            while (i2 < 11) {
                bitSet.set(i, isBitSet(indexOf, 10 - i2));
                i2++;
                i++;
            }
        }
        return i;
    }

    private static byte readByte(BitSet bitSet, int i) {
        byte b = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            if (bitSet.get((i * 8) + i2)) {
                b = (byte) (b | (1 << (7 - i2)));
            }
        }
        return b;
    }

    private static byte calculateChecksum(byte[] bArr) {
        return (byte) (Hash.sha256(bArr)[0] & ((byte) (255 << (8 - ((bArr.length * 8) / 32)))));
    }

    private static List<String> populateWordList() {
        return Arrays.asList(BIP39.english);
    }
}
