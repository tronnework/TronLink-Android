package com.tron.wallet.common.utils;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import com.facebook.common.util.UriUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import java.io.File;
import java.text.DecimalFormat;
public enum UriUtils {
    INSTANCE;

    public String getImagePath(Context context, Intent intent) {
        String imagePath;
        Uri data = intent.getData();
        LogUtils.d("uri=intent.getData :", "" + data);
        String str = null;
        if (DocumentsContract.isDocumentUri(context, data)) {
            String documentId = DocumentsContract.getDocumentId(data);
            LogUtils.d("getDocumentId(uri) :", "" + documentId);
            LogUtils.d("uri.getAuthority() :", "" + data.getAuthority());
            if ("com.android.providers.media.documents".equals(data.getAuthority())) {
                String str2 = documentId.split(":")[1];
                imagePath = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=" + str2);
            } else if ("com.android.providers.downloads.documents".equals(data.getAuthority())) {
                try {
                    if (Build.VERSION.SDK_INT < 26) {
                        imagePath = getImagePath(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null);
                    } else {
                        imagePath = getImagePath(context, data, null);
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            str = imagePath;
        } else if ("content".equalsIgnoreCase(data.getScheme())) {
            try {
                str = getImagePath(context, data, null);
            } catch (Exception e2) {
                SentryUtil.captureException(e2);
                LogUtils.e(e2);
            }
        }
        return StringTronUtil.isEmpty(str) ? "" : str;
    }

    public Uri getImageUri(Context context, Intent intent) {
        try {
            String imagePath = getImagePath(context, intent);
            if (StringTronUtil.isEmpty(imagePath)) {
                return null;
            }
            File file = new File(imagePath, "");
            if (file.exists()) {
                return Uri.fromFile(file);
            }
            return null;
        } catch (Exception e) {
            SentryUtil.captureException(e);
            return null;
        }
    }

    private String getImagePath(Context context, Uri uri, String str) {
        String str2;
        str2 = "";
        try {
            Cursor query = context.getContentResolver().query(uri, null, str, null, null);
            if (query != null && query.getCount() != 0) {
                str2 = query.moveToFirst() ? query.getString(query.getColumnIndex("_data")) : "";
                query.close();
            }
            return str2;
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            return str2;
        }
    }

    public static String readFileSize(String str) {
        return readableFileSize(new File(str).length());
    }

    public static String readableFileSize(long j) {
        double d;
        if (j <= 0) {
            return "0";
        }
        int log10 = (int) (Math.log10(j) / Math.log10(1024.0d));
        return new DecimalFormat("#,##0.#").format(d / Math.pow(1024.0d, log10)) + " " + new String[]{"B", "KB", "MB", "GB", "TB"}[log10];
    }

    public static Uri getUriFromFile(Context context, File file) {
        if (context == null || file == null) {
            throw null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(context.getApplicationContext(), MigrateConfig.PROVIDER_AUTH_PRO, file);
        }
        return Uri.fromFile(file);
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else if (isMediaDocument(uri)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str = split2[0];
                if ("image".equals(str)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(str)) {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else if ("document".equals(str)) {
                    uri2 = MediaStore.Files.getContentUri("external");
                }
                return getDataColumn(context, uri2, "_id=?", new String[]{split2[1]});
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            if (isQQMediaDocument(uri)) {
                String path = uri.getPath();
                File file = new File(Environment.getExternalStorageDirectory(), path.substring(10, path.length()));
                if (file.exists()) {
                    return file.toString();
                }
                return null;
            }
            return getDataColumn(context, uri, null, null);
        } else if (UriUtil.LOCAL_FILE_SCHEME.equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query != null) {
                            query.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getRealPathFromURI(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isQQMediaDocument(Uri uri) {
        return "com.tencent.mtt.fileprovider".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
