package com.tron.tron_base.frame.utils.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import com.tron.tron_base.frame.utils.IOUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.SystemUtils;
import com.tron.tron_base.frame.utils.cache.DiskLruCache;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class DiskLruCacheHelper {
    private static final int DEFAULT_APP_VERSION = 1;
    private static final int DEFAULT_VALUE_COUNT = 1;
    private static final String DIR_NAME = "diskCache";
    private static final int MAX_COUNT = 5242880;
    private static final String TAG = "DiskLruCacheHelper";
    private DiskLruCache mDiskLruCache;

    public DiskLruCacheHelper(Context context) throws IOException {
        this.mDiskLruCache = generateCache(context, DIR_NAME, MAX_COUNT);
    }

    public DiskLruCacheHelper(Context context, String str) throws IOException {
        this.mDiskLruCache = generateCache(context, str, MAX_COUNT);
    }

    public DiskLruCacheHelper(Context context, String str, int i) throws IOException {
        this.mDiskLruCache = generateCache(context, str, i);
    }

    public DiskLruCacheHelper(File file) throws IOException {
        this.mDiskLruCache = generateCache((Context) null, file, MAX_COUNT);
    }

    public DiskLruCacheHelper(Context context, File file) throws IOException {
        this.mDiskLruCache = generateCache(context, file, MAX_COUNT);
    }

    public DiskLruCacheHelper(Context context, File file, int i) throws IOException {
        this.mDiskLruCache = generateCache(context, file, i);
    }

    private DiskLruCache generateCache(Context context, File file, int i) throws IOException {
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException(file + " is not a directory or does not exists. ");
        }
        return DiskLruCache.open(file, context == null ? 1 : SystemUtils.getAppVersion(context), 1, i);
    }

    private DiskLruCache generateCache(Context context, String str, int i) throws IOException {
        return DiskLruCache.open(getDiskCacheDir(context, str), SystemUtils.getAppVersion(context), 1, i);
    }

    public void put(String str, String str2) {
        BufferedWriter bufferedWriter;
        DiskLruCache.Editor editor;
        BufferedWriter bufferedWriter2 = null;
        DiskLruCache.Editor editor2 = null;
        try {
            try {
                try {
                    try {
                        editor = editor(str);
                    } catch (IOException e) {
                        e = e;
                        bufferedWriter = null;
                    }
                    if (editor == null) {
                        return;
                    }
                    try {
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(editor.newOutputStream(0)));
                    } catch (IOException e2) {
                        e = e2;
                        bufferedWriter = null;
                    }
                    try {
                        bufferedWriter.write(str2);
                        editor.commit();
                        bufferedWriter.close();
                    } catch (IOException e3) {
                        e = e3;
                        editor2 = editor;
                        LogUtils.e(e);
                        try {
                            editor2.abort();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    if (bufferedWriter2 != null) {
                        try {
                            bufferedWriter2.close();
                        } catch (IOException e5) {
                            LogUtils.e(e5);
                        }
                    }
                    throw th;
                }
            } catch (IOException e6) {
                LogUtils.e(e6);
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
        }
    }

    public String getAsString(String str) {
        InputStream inputStream = get(str);
        if (inputStream == null) {
            return null;
        }
        try {
            return IOUtils.readFully(new InputStreamReader(inputStream, IOUtils.UTF_8));
        } catch (IOException e) {
            LogUtils.e(e);
            try {
                inputStream.close();
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject.toString());
    }

    public JSONObject getAsJson(String str) {
        String asString = getAsString(str);
        if (asString != null) {
            try {
                return new JSONObject(asString);
            } catch (JSONException e) {
                LogUtils.e(e);
                return null;
            }
        }
        return null;
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray.toString());
    }

    public JSONArray getAsJSONArray(String str) {
        try {
            return new JSONArray(getAsString(str));
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public void put(java.lang.String r4, byte[] r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.tron_base.frame.utils.cache.DiskLruCacheHelper.put(java.lang.String, byte[]):void");
    }

    public byte[] getAsBytes(String str) {
        InputStream inputStream = get(str);
        if (inputStream == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[256];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException e) {
            LogUtils.e(e);
            return null;
        }
    }

    public void put(String str, Serializable serializable) {
        ObjectOutputStream objectOutputStream;
        DiskLruCache.Editor editor = editor(str);
        if (editor == null) {
            return;
        }
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(editor.newOutputStream(0));
                } catch (IOException e) {
                    LogUtils.e(e);
                    return;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(serializable);
            objectOutputStream.flush();
            editor.commit();
            objectOutputStream.close();
        } catch (IOException e3) {
            e = e3;
            objectOutputStream2 = objectOutputStream;
            LogUtils.e(e);
            try {
                editor.abort();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException e5) {
                    LogUtils.e(e5);
                }
            }
            throw th;
        }
    }

    public <T> T getAsSerializable(String str) {
        ObjectInputStream objectInputStream;
        InputStream inputStream = get(str);
        ?? r0 = (T) false;
        try {
            try {
            } catch (IOException e) {
                LogUtils.e(e);
            }
            if (inputStream == null) {
                return null;
            }
            try {
                objectInputStream = new ObjectInputStream(inputStream);
                try {
                    T t = (T) objectInputStream.readObject();
                    objectInputStream.close();
                    r0 = t;
                } catch (IOException e2) {
                    e = e2;
                    LogUtils.e(e);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                        r0 = r0;
                    }
                    return (T) r0;
                } catch (ClassNotFoundException e3) {
                    e = e3;
                    LogUtils.e(e);
                    if (objectInputStream != null) {
                        objectInputStream.close();
                        r0 = r0;
                    }
                    return (T) r0;
                }
            } catch (IOException e4) {
                e = e4;
                objectInputStream = null;
            } catch (ClassNotFoundException e5) {
                e = e5;
                objectInputStream = null;
            } catch (Throwable th) {
                th = th;
                if (r0 != 0) {
                    try {
                        r0.close();
                    } catch (IOException e6) {
                        LogUtils.e(e6);
                    }
                }
                throw th;
            }
            return (T) r0;
        } catch (Throwable th2) {
            th = th2;
            r0 = (T) objectInputStream;
        }
    }

    public void put(String str, Bitmap bitmap) {
        put(str, IOUtils.bitmap2Bytes(bitmap));
    }

    public Bitmap getAsBitmap(String str) {
        byte[] asBytes = getAsBytes(str);
        if (asBytes == null) {
            return null;
        }
        return IOUtils.bytes2Bitmap(asBytes);
    }

    public void put(String str, Drawable drawable) {
        put(str, drawable2Bitmap(drawable));
    }

    public Drawable getAsDrawable(String str) {
        byte[] asBytes = getAsBytes(str);
        if (asBytes == null) {
            return null;
        }
        return bitmap2Drawable(IOUtils.bytes2Bitmap(asBytes));
    }

    public boolean remove(String str) {
        try {
            return this.mDiskLruCache.remove(IOUtils.hashKeyForDisk(str));
        } catch (IOException e) {
            LogUtils.e(e);
            return false;
        }
    }

    public void close() throws IOException {
        this.mDiskLruCache.close();
    }

    public void delete() throws IOException {
        this.mDiskLruCache.delete();
    }

    public void flush() throws IOException {
        this.mDiskLruCache.flush();
    }

    public boolean isClosed() {
        return this.mDiskLruCache.isClosed();
    }

    public long size() {
        return this.mDiskLruCache.size();
    }

    public void setMaxSize(long j) {
        this.mDiskLruCache.setMaxSize(j);
    }

    public File getDirectory() {
        return this.mDiskLruCache.getDirectory();
    }

    public long getMaxSize() {
        return this.mDiskLruCache.getMaxSize();
    }

    public DiskLruCache.Editor editor(String str) {
        try {
            String hashKeyForDisk = IOUtils.hashKeyForDisk(str);
            DiskLruCache.Editor edit = this.mDiskLruCache.edit(hashKeyForDisk);
            if (edit == null) {
                LogUtils.w(TAG, "the entry spcified key:" + hashKeyForDisk + " is editing by other . ");
            }
            return edit;
        } catch (IOException e) {
            LogUtils.e(e);
            return null;
        }
    }

    public InputStream get(String str) {
        try {
            DiskLruCache.Snapshot snapshot = this.mDiskLruCache.get(IOUtils.hashKeyForDisk(str));
            if (snapshot == null) {
                LogUtils.e(TAG, "not find entry , or entry.readable = false");
                return null;
            }
            return snapshot.getInputStream(0);
        } catch (IOException e) {
            LogUtils.e(e);
            return null;
        }
    }

    private File getDiskCacheDir(Context context, String str) {
        String internalCacheDirPath;
        if (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) && hasPermission(context, "android.permission.READ_EXTERNAL_STORAGE") && hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                internalCacheDirPath = getExternalCacheDirPath(context);
            } catch (Exception e) {
                LogUtils.e(e);
                internalCacheDirPath = getInternalCacheDirPath(context);
            }
        } else {
            internalCacheDirPath = getInternalCacheDirPath(context);
        }
        return new File(internalCacheDirPath + File.separator + str);
    }

    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        new BitmapDrawable(bitmap).setTargetDensity(bitmap.getDensity());
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        drawable.draw(canvas);
        return createBitmap;
    }

    public static boolean hasPermission(Context context, String str) {
        int checkSelfPermission;
        if (Build.VERSION.SDK_INT >= 23) {
            checkSelfPermission = context.checkSelfPermission(str);
            return checkSelfPermission == 0;
        }
        return true;
    }

    private static String getExternalCacheDirPath(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir == null) {
            return null;
        }
        if (!externalCacheDir.exists()) {
            externalCacheDir.mkdirs();
        }
        return externalCacheDir.getPath();
    }

    private static String getInternalCacheDirPath(Context context) {
        File cacheDir = context.getCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        return cacheDir.getPath();
    }
}
