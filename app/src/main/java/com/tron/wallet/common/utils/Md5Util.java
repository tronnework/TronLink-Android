package com.tron.wallet.common.utils;

import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Md5Util {
    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String str2 = "";
            for (byte b : MessageDigest.getInstance("MD5").digest(str.getBytes())) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                str2 = str2 + hexString;
            }
            return str2;
        } catch (NoSuchAlgorithmException e) {
            LogUtils.e(e);
            return "";
        }
    }

    public static java.lang.String getMD5(java.lang.String r6) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.Md5Util.getMD5(java.lang.String):java.lang.String");
    }

    public static String getAssetsTronWebMD5() {
        return getAssetsFileMd5(DappJs.AssetsTronWebFileName);
    }

    public static java.lang.String getAssetsFileMd5(java.lang.String r7) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.utils.Md5Util.getAssetsFileMd5(java.lang.String):java.lang.String");
    }
}
