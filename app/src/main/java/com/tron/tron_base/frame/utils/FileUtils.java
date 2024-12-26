package com.tron.tron_base.frame.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public class FileUtils {
    public static final String CACHE_DIR = "cache";
    public static final String DOWNLOAD_DIR = "download";
    public static final String ICON_DIR = "icon";
    public static final String ROOT_DIR = "";

    public static boolean isSDCardAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getDownloadDir() {
        return getDir(DOWNLOAD_DIR);
    }

    public static String getChannelFile(Context context) {
        String externalPath = getExternalPath(context);
        if (externalPath == null) {
            return null;
        }
        return externalPath + "channel.txt";
    }

    public static String getHashFile(Context context) {
        String externalPath = getExternalPath(context);
        if (externalPath == null) {
            return null;
        }
        return externalPath + "hash.txt";
    }

    public static String getCacheDir() {
        return getDir(CACHE_DIR);
    }

    public static String getIconDir() {
        return getDir("icon");
    }

    public static String getDir(String str) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath());
        }
        sb.append(str);
        sb.append(File.separator);
        String sb2 = sb.toString();
        return createDirs(sb2) ? sb2 : "";
    }

    public static String getExternalStoragePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "" + File.separator;
    }

    public static String getExternalPath(Context context) {
        try {
            if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
                return context.getExternalFilesDir(null).getAbsolutePath() + File.separator;
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return null;
    }

    public static String getDir(Context context) {
        String str;
        if (Build.VERSION.SDK_INT >= 29) {
            str = context.getExternalFilesDir("") + File.separator + "Media" + File.separator;
        } else {
            str = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "tronlink" + File.separator;
        }
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static String getRootDir(Context context) {
        try {
            return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        } catch (Exception e) {
            LogUtils.e(e);
            if (Build.VERSION.SDK_INT >= 29) {
                return context.getExternalFilesDir("") + "";
            }
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

    public static String getCacheDir(Context context) {
        File file = new File(context.getExternalCacheDir(), "svideo");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.exists() ? file.getPath() : "";
    }

    public static String getCachePath() {
        File cacheDir = AppContextUtil.getContext().getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return cacheDir.getAbsolutePath() + "/";
    }

    public static boolean createDirs(String str) {
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        return file.mkdirs();
    }

    public static boolean copyFile(String str, String str2, boolean z) {
        return copyFile(new File(str), new File(str2), z);
    }

    public static boolean copyFile(File file, File file2, boolean z) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        FileOutputStream fileOutputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    fileOutputStream.flush();
                }
                if (z) {
                    file.delete();
                }
                IOUtils.close(fileOutputStream);
                IOUtils.close(fileInputStream);
                return true;
            } catch (Exception e2) {
                e = e2;
                fileOutputStream2 = fileOutputStream;
                LogUtils.e(e);
                IOUtils.close(fileOutputStream2);
                IOUtils.close(fileInputStream);
                return false;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                IOUtils.close(fileOutputStream2);
                IOUtils.close(fileInputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static boolean isWriteable(String str) {
        try {
            if (StringTronUtil.isEmpty(str)) {
                return false;
            }
            File file = new File(str);
            if (file.exists()) {
                return file.canWrite();
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }

    public static void chmod(String str, String str2) {
        try {
            Runtime.getRuntime().exec("chmod " + str2 + " " + str);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static boolean writeFile(InputStream inputStream, String str, boolean z) {
        Throwable th;
        Exception e;
        File file = new File(str);
        boolean z2 = false;
        FileOutputStream fileOutputStream = null;
        if (z) {
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Exception e2) {
                    e = e2;
                    LogUtils.e(e);
                    IOUtils.close(fileOutputStream);
                    IOUtils.close(inputStream);
                    return z2;
                }
            } catch (Throwable th2) {
                th = th2;
                IOUtils.close(fileOutputStream);
                IOUtils.close(inputStream);
                throw th;
            }
        }
        if (!file.exists() && inputStream != null) {
            new File(file.getParent()).mkdirs();
            byte[] bArr = new byte[1024];
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream2.write(bArr, 0, read);
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = fileOutputStream2;
                    LogUtils.e(e);
                    IOUtils.close(fileOutputStream);
                    IOUtils.close(inputStream);
                    return z2;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = fileOutputStream2;
                    IOUtils.close(fileOutputStream);
                    IOUtils.close(inputStream);
                    throw th;
                }
            }
            z2 = true;
            fileOutputStream = fileOutputStream2;
        }
        IOUtils.close(fileOutputStream);
        IOUtils.close(inputStream);
        return z2;
    }

    public static boolean writeFile(byte[] bArr, String str, boolean z) {
        File file = new File(str);
        boolean z2 = false;
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                } else if (!z) {
                    file.delete();
                    file.createNewFile();
                }
                if (file.canWrite()) {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    try {
                        randomAccessFile2.seek(randomAccessFile2.length());
                        randomAccessFile2.write(bArr);
                        randomAccessFile = randomAccessFile2;
                        z2 = true;
                    } catch (Exception e) {
                        e = e;
                        randomAccessFile = randomAccessFile2;
                        LogUtils.e(e);
                        IOUtils.close(randomAccessFile);
                        return z2;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        IOUtils.close(randomAccessFile);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
            IOUtils.close(randomAccessFile);
            return z2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFile(String str, File file, boolean z) {
        boolean z2 = false;
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                } else if (!z) {
                    file.delete();
                    file.createNewFile();
                }
                if (file.canWrite()) {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    try {
                        randomAccessFile2.seek(randomAccessFile2.length());
                        randomAccessFile2.write(str.getBytes());
                        randomAccessFile = randomAccessFile2;
                        z2 = true;
                    } catch (Exception e) {
                        e = e;
                        randomAccessFile = randomAccessFile2;
                        LogUtils.e(e);
                        IOUtils.close(randomAccessFile);
                        return z2;
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        IOUtils.close(randomAccessFile);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
            IOUtils.close(randomAccessFile);
            return z2;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean writeFile(String str, String str2, boolean z) {
        return writeFile(str.getBytes(), str2, z);
    }

    public static void writeProperties(String str, String str2, String str3, String str4) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception exc;
        if (StringTronUtil.isEmpty(str2) || StringTronUtil.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        FileInputStream fileInputStream = null;
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream2);
                properties.setProperty(str2, str3);
                fileOutputStream = new FileOutputStream(file);
                try {
                    properties.store(fileOutputStream, str4);
                    IOUtils.close(fileInputStream2);
                } catch (Exception e) {
                    exc = e;
                    fileInputStream = fileInputStream2;
                    try {
                        LogUtils.e(exc);
                        IOUtils.close(fileInputStream);
                        IOUtils.close(fileOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        IOUtils.close(fileInputStream);
                        IOUtils.close(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream = fileInputStream2;
                    IOUtils.close(fileInputStream);
                    IOUtils.close(fileOutputStream);
                    throw th;
                }
            } catch (Exception e2) {
                exc = e2;
                fileOutputStream = null;
            } catch (Throwable th4) {
                th = th4;
                fileOutputStream = null;
            }
        } catch (Exception e3) {
            exc = e3;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
        }
        IOUtils.close(fileOutputStream);
    }

    public static String readProperties(String str, String str2, String str3) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        String str4 = null;
        if (StringTronUtil.isEmpty(str2) || StringTronUtil.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            fileInputStream2 = new FileInputStream(file);
        } catch (IOException e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream2);
            str4 = properties.getProperty(str2, str3);
            IOUtils.close(fileInputStream2);
        } catch (IOException e2) {
            fileInputStream = fileInputStream2;
            e = e2;
            try {
                LogUtils.e(e);
                IOUtils.close(fileInputStream);
                return str4;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream3 = fileInputStream;
                IOUtils.close(fileInputStream3);
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream3 = fileInputStream2;
            th = th3;
            IOUtils.close(fileInputStream3);
            throw th;
        }
        return str4;
    }

    public static void writeMap(String str, Map<String, String> map, boolean z, String str2) {
        Throwable th;
        FileOutputStream fileOutputStream;
        Exception exc;
        FileInputStream fileInputStream;
        if (map == null || map.size() == 0 || StringTronUtil.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        FileInputStream fileInputStream2 = null;
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            Properties properties = new Properties();
            if (z) {
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                } catch (Exception e) {
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    exc = e;
                    try {
                        LogUtils.e(exc);
                        IOUtils.close(fileInputStream2);
                        IOUtils.close(fileOutputStream);
                    } catch (Throwable th2) {
                        th = th2;
                        IOUtils.close(fileInputStream2);
                        IOUtils.close(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    th = th3;
                    IOUtils.close(fileInputStream2);
                    IOUtils.close(fileOutputStream);
                    throw th;
                }
            } else {
                fileInputStream = null;
            }
            properties.putAll(map);
            fileOutputStream = new FileOutputStream(file);
            try {
                properties.store(fileOutputStream, str2);
                IOUtils.close(fileInputStream);
            } catch (Exception e2) {
                FileInputStream fileInputStream3 = fileInputStream;
                exc = e2;
                fileInputStream2 = fileInputStream3;
                LogUtils.e(exc);
                IOUtils.close(fileInputStream2);
                IOUtils.close(fileOutputStream);
            } catch (Throwable th4) {
                FileInputStream fileInputStream4 = fileInputStream;
                th = th4;
                fileInputStream2 = fileInputStream4;
                IOUtils.close(fileInputStream2);
                IOUtils.close(fileOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            exc = e3;
            fileOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            fileOutputStream = null;
        }
        IOUtils.close(fileOutputStream);
    }

    public static Map<String, String> readMap(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        FileInputStream fileInputStream3 = null;
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        try {
            if (!file.exists() || !file.isFile()) {
                file.createNewFile();
            }
            fileInputStream2 = new FileInputStream(file);
        } catch (Exception e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream2);
            HashMap hashMap = new HashMap(properties);
            IOUtils.close(fileInputStream2);
            return hashMap;
        } catch (Exception e2) {
            fileInputStream = fileInputStream2;
            e = e2;
            try {
                LogUtils.e(e);
                IOUtils.close(fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream3 = fileInputStream;
                IOUtils.close(fileInputStream3);
                throw th;
            }
        } catch (Throwable th3) {
            fileInputStream3 = fileInputStream2;
            th = th3;
            IOUtils.close(fileInputStream3);
            throw th;
        }
    }

    public static boolean copy(String str, String str2, boolean z) {
        FileOutputStream fileOutputStream;
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        File file2 = new File(str2);
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Exception e) {
                e = e;
                fileOutputStream = null;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                    fileOutputStream.flush();
                }
                IOUtils.close(fileInputStream2);
                IOUtils.close(fileOutputStream);
                if (z) {
                    file.delete();
                    return true;
                }
                return true;
            } catch (Exception e2) {
                e = e2;
                fileInputStream = fileInputStream2;
                try {
                    LogUtils.e(e);
                    IOUtils.close(fileInputStream);
                    IOUtils.close(fileOutputStream);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.close(fileInputStream);
                    IOUtils.close(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = fileInputStream2;
                IOUtils.close(fileInputStream);
                IOUtils.close(fileOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    public static File getCacheFile() {
        return new File(getCacheDir());
    }
}
