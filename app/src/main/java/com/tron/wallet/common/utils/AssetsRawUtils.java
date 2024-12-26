package com.tron.wallet.common.utils;

import android.content.Context;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class AssetsRawUtils {
    public static String getFromAssets(Context context, String str) throws IOException {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().getAssets().open(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            LogUtils.e("AssetsRawUtils", "Exception occurs when opening resource " + str);
            throw e;
        }
    }
}
