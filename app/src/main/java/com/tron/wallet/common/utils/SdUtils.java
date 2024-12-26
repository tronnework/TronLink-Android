package com.tron.wallet.common.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.File;
public class SdUtils {
    public static String getRootPath() {
        return Environment.getExternalStorageDirectory() + File.separator;
    }

    public static String getDownloadPath() {
        return AppContextUtil.getContext().getCacheDir() + File.separator + Environment.DIRECTORY_DOWNLOADS + File.separator;
    }

    public static String getCameraPath() {
        return Environment.getExternalStorageDirectory() + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera" + File.separator;
    }

    public static String getResultPhotoPath(Context context, Uri uri) {
        String[] strArr = {"_data"};
        Cursor query = context.getContentResolver().query(uri, strArr, null, null, null);
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex(strArr[0]));
        query.close();
        return string;
    }

    public static boolean checkFreeSpace() {
        try {
            if (getSDFreeSpace() > 5242880) {
                LogUtils.d("alex", "sd card check ok");
                return true;
            }
            LogUtils.d("alex", "sd card check fail");
            return false;
        } catch (Exception e) {
            SentryUtil.captureException(e);
            return false;
        }
    }

    public static long getSDFreeSpace() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }
}
