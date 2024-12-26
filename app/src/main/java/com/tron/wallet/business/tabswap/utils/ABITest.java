package com.tron.wallet.business.tabswap.utils;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.crypto.OutputLengthException;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.abi.AbiUtil;
public class ABITest {
    public static void test2(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (StringTronUtil.isEmpty(str)) {
                str = "00000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000005f5e10000000000000000000000000000000000000000000000000000000000007717f1000000000000000000000000b42b84bad413dde093e27d01bb02ed9eede52c430000000000000000000000000000000000000000000000000000000065374bfc";
            }
            byte[] fromHexString = ByteArray.fromHexString(str);
            int i = 0;
            for (int i2 = 0; i2 < (fromHexString.length / 32) + 1; i2++) {
                arrayList.add(ByteArray.toHexString(subBytes(fromHexString, i, 32)));
                i += 32;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        LogUtils.i("SwapV3ParamBuild", Arrays.deepToString(arrayList.toArray()));
    }

    public static void test3() {
        try {
            LogUtils.i("SwapV3ParamBuild", "v2:" + TransactionDataUtils.getInstance().parseConstantDataByFun(ByteArray.fromHexString("0000000000000000000000000000000000000000000000000000000000000280"), "transfer(uint256)").getParameterMap().get("0"));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static void test4() {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            arrayList.add("T9yD14Nj9j7xAB4dbGeiX9h8unkKHxuWwb");
            arrayList.add("TYsbWxNnyTgsZaTFaue9hqpxkU3Fkco94a");
            arrayList.add("TLBaRhANQoJFTqre9Nf1mjuwNWjCJeYqUL");
            arrayList.add("TXYZopYRdj2D9XRtbG411XZZ3kM5VkAeBf");
            arrayList2.add("v2");
            arrayList2.add("v3");
            arrayList3.add(2);
            arrayList3.add(2);
            arrayList4.add(0);
            arrayList4.add(500);
            arrayList4.add(100);
            arrayList4.add(0);
            arrayList5.add(new BigInteger(ByteArray.fromHexString("0x5f5e100")));
            arrayList5.add(new BigInteger(ByteArray.fromHexString("0x7717f1")));
            arrayList5.add("TSPrmJetAMo6S6RxMd4tswzeRCFVegBNig");
            arrayList5.add(1698122748);
            String str = GsonUtils.toGsonString(arrayList) + "," + GsonUtils.toGsonString(arrayList2) + "," + GsonUtils.toGsonString(arrayList3) + "," + GsonUtils.toGsonString(arrayList4) + "," + GsonUtils.toGsonString(arrayList5);
            new ArrayList();
            LogUtils.i("SwapV3ParamBuild", "test4:" + str);
            String parseMethod = AbiUtil.parseMethod("swapExactInput(address[],string[],uint256[],uint24[],tuple(uint256,uint256,address,uint256))", str, false);
            LogUtils.i("SwapV3ParamBuild", "test4:" + parseMethod);
            test2(parseMethod.substring(8, parseMethod.length()));
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    private static byte[] subBytes(byte[] bArr, int i, int i2) {
        if (ArrayUtils.isEmpty(bArr) || i >= bArr.length || i2 < 0) {
            throw new OutputLengthException("data start:" + i + ", length:" + i2);
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, Math.min(i2, bArr.length - i));
        return bArr2;
    }
}
