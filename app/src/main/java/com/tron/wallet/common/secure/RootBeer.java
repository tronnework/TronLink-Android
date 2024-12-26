package com.tron.wallet.common.secure;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.tron.tron_base.frame.utils.LogUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
public class RootBeer {
    private boolean loggingEnabled = true;
    private final Context mContext;

    public RootBeer(Context context) {
        this.mContext = context;
    }

    public boolean isRooted() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForDangerousProps() || checkForRWPaths() || detectTestKeys() || checkSuExists() || checkForMagiskBinary();
    }

    @Deprecated
    public boolean isRootedWithoutBusyBoxCheck() {
        return isRooted();
    }

    public boolean isRootedWithBusyBoxCheck() {
        return detectRootManagementApps() || detectPotentiallyDangerousApps() || checkForBinary("su") || checkForBinary("busybox") || checkForDangerousProps() || checkForRWPaths() || checkRootWhichSU() || detectTestKeys() || checkSuExists() || checkForMagiskBinary();
    }

    public boolean detectTestKeys() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public boolean detectRootManagementApps() {
        return detectRootManagementApps(null);
    }

    public boolean detectRootManagementApps(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(Const.knownRootAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectPotentiallyDangerousApps() {
        return detectPotentiallyDangerousApps(null);
    }

    public boolean detectPotentiallyDangerousApps(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(Const.knownDangerousAppsPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean detectRootCloakingApps() {
        return detectRootCloakingApps(null);
    }

    public boolean detectRootCloakingApps(String[] strArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList(Const.knownRootCloakingPackages));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return isAnyPackageFromListInstalled(arrayList);
    }

    public boolean checkForSuBinary() {
        return checkForBinary("su");
    }

    public boolean checkForMagiskBinary() {
        return checkForBinary("magisk");
    }

    public boolean checkForBusyBoxBinary() {
        return checkForBinary("busybox");
    }

    public boolean checkForBinary(String str) {
        String[] paths;
        boolean z = false;
        for (String str2 : Const.getPaths()) {
            String str3 = str2 + str;
            if (new File(str2, str).exists()) {
                LogUtils.v(str3 + " binary detected!");
                z = true;
            }
        }
        return z;
    }

    public static boolean checkRootWhichSU() {
        ArrayList<String> executeCommand = executeCommand(new String[]{"/system/xbin/which", "su"});
        if (executeCommand != null) {
            LogUtils.d("TAG", "execResult=" + executeCommand.toString());
            return true;
        }
        LogUtils.d("TAG", "execResult=null");
        return false;
    }

    public static ArrayList<String> executeCommand(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            LogUtils.i("TAG", "to shell exec which for find su :");
            Process exec = Runtime.getRuntime().exec(strArr);
            if (exec == null) {
                return arrayList;
            }
            new BufferedWriter(new OutputStreamWriter(exec.getOutputStream()));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    LogUtils.e("TAG", "–> Line received: " + readLine);
                    arrayList.add(readLine);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LogUtils.d("TAG", "–> Full response was: " + arrayList);
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    private String[] propsReader() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("getprop").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split(StringUtils.LF);
        } catch (IOException | NoSuchElementException e) {
            LogUtils.e(e);
            return null;
        }
    }

    private String[] mountReader() {
        try {
            InputStream inputStream = Runtime.getRuntime().exec("mount").getInputStream();
            if (inputStream == null) {
                return null;
            }
            return new Scanner(inputStream).useDelimiter("\\A").next().split(StringUtils.LF);
        } catch (IOException | NoSuchElementException e) {
            LogUtils.e(e);
            return null;
        }
    }

    private boolean isAnyPackageFromListInstalled(List<String> list) {
        PackageManager packageManager = this.mContext.getPackageManager();
        boolean z = false;
        for (String str : list) {
            try {
                packageManager.getPackageInfo(str, 0);
                LogUtils.e(str + " ROOT management app detected!");
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    public boolean checkForDangerousProps() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        String[] propsReader = propsReader();
        if (propsReader == null) {
            return false;
        }
        boolean z = false;
        for (String str : propsReader) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    String str3 = "[" + ((String) hashMap.get(str2)) + "]";
                    if (str.contains(str3)) {
                        LogUtils.v(str2 + " = " + str3 + " detected!");
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    public boolean checkForRWPaths() {
        String str;
        String str2;
        String[] strArr;
        String[] mountReader = mountReader();
        if (mountReader == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        int length = mountReader.length;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            String str3 = mountReader[i2];
            String[] split = str3.split(" ");
            int i3 = 23;
            if ((i <= 23 && split.length < 4) || (i > 23 && split.length < 6)) {
                LogUtils.e("Error formatting mount line: " + str3);
            } else {
                if (i > 23) {
                    str = split[2];
                    str2 = split[5];
                } else {
                    str = split[1];
                    str2 = split[3];
                }
                String[] strArr2 = Const.pathsThatShouldNotBeWritable;
                int length2 = strArr2.length;
                int i4 = 0;
                while (i4 < length2) {
                    String str4 = strArr2[i4];
                    if (str.equalsIgnoreCase(str4)) {
                        if (Build.VERSION.SDK_INT > i3) {
                            str2 = str2.replace("(", "").replace(")", "");
                        }
                        String[] split2 = str2.split(",");
                        int length3 = split2.length;
                        int i5 = 0;
                        while (i5 < length3) {
                            strArr = mountReader;
                            if (split2[i5].equalsIgnoreCase("rw")) {
                                LogUtils.v(str4 + " path is mounted with rw permissions! " + str3);
                                z = true;
                                break;
                            }
                            i5++;
                            mountReader = strArr;
                        }
                    }
                    strArr = mountReader;
                    i4++;
                    mountReader = strArr;
                    i3 = 23;
                }
            }
            i2++;
            mountReader = mountReader;
        }
        return z;
    }

    public boolean checkSuExists() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"which", "su"});
            boolean z = new BufferedReader(new InputStreamReader(process.getInputStream())).readLine() != null;
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }
}
