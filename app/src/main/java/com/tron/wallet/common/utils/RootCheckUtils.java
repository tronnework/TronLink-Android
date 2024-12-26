package com.tron.wallet.common.utils;
public final class RootCheckUtils {
    private RootCheckUtils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static boolean isSelinuxFlagInEnabled() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return "1".equals((String) cls.getMethod("get", String.class).invoke(cls, "ro.build.selinux"));
        } catch (Exception unused) {
            return false;
        }
    }
}
