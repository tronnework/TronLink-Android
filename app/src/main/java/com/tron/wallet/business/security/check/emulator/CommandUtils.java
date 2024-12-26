package com.tron.wallet.business.security.check.emulator;

import com.tron.tron_base.frame.utils.LogUtils;
import java.io.BufferedInputStream;
public class CommandUtils {
    public static java.lang.String exc(java.lang.String r5) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.security.check.emulator.CommandUtils.exc(java.lang.String):java.lang.String");
    }

    private static String getStringFromBufferedInputStream(BufferedInputStream bufferedInputStream) {
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[512];
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                int read = bufferedInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                sb.append(new String(bArr, 0, read));
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return sb.toString();
    }
}
