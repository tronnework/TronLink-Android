package com.tron.wallet.ledger.bleclient;

import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tron.wallet.ledger.blemodule.errors.BleErrorId;
import j$.util.DesugarArrays;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
public class BleUtils {
    private static Map<String, String> tokenLedgerSignature;

    static {
        HashMap hashMap = new HashMap();
        tokenLedgerSignature = hashMap;
        hashMap.put("1002000", "0a0a426974546f7272656e7410061a46304402202e2502f36b00e57be785fc79ec4043abcdd4fdd1b58d737ce123599dffad2cb602201702c307f009d014a553503b499591558b3634ceee4c054c61cedd8aca94c02b");
    }

    public static boolean isSupportBle() {
        return AppContextUtil.getmApplication().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static String getTokenLedgerSignature(String str) {
        return tokenLedgerSignature.get(str);
    }

    public static List<Integer> splitPath(String str) {
        final ArrayList arrayList = new ArrayList();
        String[] split = str.split("/");
        if (split != null) {
            DesugarArrays.stream(split).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    BleUtils.lambda$splitPath$0(arrayList, (String) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return arrayList;
    }

    public static void lambda$splitPath$0(List list, String str) {
        int parseInt;
        try {
            if (str.length() > 1 && str.charAt(str.length() - 1) == '\'') {
                parseInt = Integer.parseInt(str.substring(0, str.indexOf(39))) - Integer.MIN_VALUE;
            } else {
                parseInt = Integer.parseInt(str);
            }
            list.add(Integer.valueOf(parseInt));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static void writeUInt32ToByteBufferBe(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i & 255);
        bArr[i2 + 2] = (byte) ((i >> 8) & 255);
        bArr[i2 + 1] = (byte) ((i >> 16) & 255);
        bArr[i2] = (byte) ((i >> 24) & 255);
    }

    public static void writeUInt16ToByteBufferBe(int i, byte[] bArr, int i2) {
        bArr[i2 + 1] = (byte) (i & 255);
        bArr[i2] = (byte) ((i >> 8) & 255);
    }

    public static int readUInt16FromByteBufferBe(byte[] bArr, int i) {
        return (bArr[i + 1] & 255) + ((bArr[i] & 255) << 8);
    }

    public static List<byte[]> chunkBuffer(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = i - 5;
        int i3 = 0;
        int i4 = 0;
        int i5 = 5;
        while (i3 < bArr.length) {
            if (i4 != 0) {
                i2 = i - 3;
                i5 = 3;
            }
            int i6 = i3 + i2;
            int length = i6 >= bArr.length ? (bArr.length - i3) + i5 : i2 + i5;
            byte[] bArr2 = new byte[length];
            bArr2[0] = 5;
            writeUInt16ToByteBufferBe(i4, bArr2, 1);
            if (i4 == 0) {
                writeUInt16ToByteBufferBe(bArr.length, bArr2, 3);
            }
            System.arraycopy(bArr, i3, bArr2, i5, length - i5);
            arrayList.add(bArr2);
            i4++;
            i3 = i6;
        }
        return arrayList;
    }

    public static DecodeResult decodeVarint(byte[] bArr, int i) throws BleError {
        int i2 = 0;
        for (int i3 = 0; i3 < 64 && i < bArr.length; i3 += 7) {
            byte b = bArr[i];
            i2 |= (b & Byte.MAX_VALUE) << i3;
            i++;
            if ((b & 128) == 0) {
                return new DecodeResult(i2, i);
            }
        }
        throw new BleError(BleErrorId.VarintIsTooBig, "varint field is too big");
    }

    public static int getNextFieldLength(byte[] bArr, int i) throws BleError {
        DecodeResult decodeVarint = decodeVarint(bArr, i);
        DecodeResult decodeVarint2 = decodeVarint(bArr, decodeVarint.pos);
        if ((decodeVarint.value & 7) == 0) {
            return decodeVarint2.pos - i;
        }
        return (decodeVarint2.value + decodeVarint2.pos) - i;
    }

    public static byte[] bytesConcat(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2 == null || bArr2.length <= i) {
            return bArr;
        }
        int i2 = 0;
        byte[] bArr3 = new byte[((bArr == null || bArr.length <= 0) ? 0 : bArr.length) + (bArr2.length - i)];
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            i2 = bArr.length;
        }
        System.arraycopy(bArr2, i, bArr3, i2, bArr2.length - i);
        return bArr3;
    }

    public static byte[] bytesConcat(byte b, byte b2, byte b3, byte b4, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 5];
        bArr2[0] = b;
        bArr2[1] = b2;
        bArr2[2] = b3;
        bArr2[3] = b4;
        bArr2[4] = (byte) length;
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 5, length);
        }
        return bArr2;
    }

    public static boolean verifyTransactionData(java.lang.String r5) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.ledger.bleclient.BleUtils.verifyTransactionData(java.lang.String):boolean");
    }

    public static class DecodeResult {
        public int pos;
        public int value;

        public DecodeResult(int i, int i2) {
            this.value = i;
            this.pos = i2;
        }
    }
}
