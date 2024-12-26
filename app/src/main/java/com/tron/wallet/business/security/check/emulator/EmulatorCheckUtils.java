package com.tron.wallet.business.security.check.emulator;

import android.content.Context;
import android.hardware.SensorManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.security.ResultStatusEnum;
public class EmulatorCheckUtils {

    public interface OnEmulatorCheckCallback {
        void onEmulatorResult(String str);
    }

    public static class CheckResult {
        private static final int EMULATOR = 0;
        private static final int POSSIBLE = 2;
        private static final int UNKNOWN = 1;
        private final int level;
        private final String property;

        CheckResult(int i, String str) {
            this.level = i;
            this.property = str;
        }

        public String toString() {
            int i = this.level;
            String str = i == 0 ? "emulator" : i == 2 ? "may be" : EnvironmentCompat.MEDIA_UNKNOWN;
            return "CheckResult{level=" + str + ", value='" + this.property + "'}";
        }
    }

    private static String getProperty(String str) {
        try {
            Object invoke = Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
            if (invoke instanceof String) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e) {
            LogUtils.e("EmulatorCheck", e);
            return null;
        }
    }

    private static com.tron.wallet.business.security.check.emulator.EmulatorCheckUtils.CheckResult checkFeaturesByHardware() {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.security.check.emulator.EmulatorCheckUtils.checkFeaturesByHardware():com.tron.wallet.business.security.check.emulator.EmulatorCheckUtils$CheckResult");
    }

    private static CheckResult checkFeaturesByFlavor() {
        String property = getProperty("ro.build.flavor");
        if (property == null) {
            return new CheckResult(2, "ro.build.flavor");
        }
        String lowerCase = property.toLowerCase();
        if (lowerCase.contains("vbox") || lowerCase.contains("sdk_gphone")) {
            return new CheckResult(0, "ro.build.flavor");
        }
        return new CheckResult(1, "ro.build.flavor");
    }

    private static CheckResult checkFeaturesByModel() {
        String property = getProperty("ro.product.model");
        if (property == null) {
            return new CheckResult(2, "ro.product.model");
        }
        String lowerCase = property.toLowerCase();
        if (lowerCase.contains("google_sdk") || lowerCase.contains("emulator") || lowerCase.contains("android sdk built for x86")) {
            return new CheckResult(0, "ro.product.model");
        }
        return new CheckResult(1, "ro.product.model");
    }

    private static CheckResult checkFeatureByManufacturer() {
        String property = getProperty("ro.product.manufacturer");
        if (property == null) {
            return new CheckResult(2, "ro.product.manufacturer");
        }
        String lowerCase = property.toLowerCase();
        if (lowerCase.contains("genymotion") || lowerCase.contains("netease")) {
            return new CheckResult(0, "ro.product.manufacturer");
        }
        return new CheckResult(1, "ro.product.manufacturer");
    }

    private static CheckResult checkFeatureByBoard() {
        String property = getProperty("ro.product.board");
        if (property == null) {
            return new CheckResult(2, "ro.product.board");
        }
        String lowerCase = property.toLowerCase();
        if (lowerCase.contains("android") || lowerCase.contains("goldfish")) {
            return new CheckResult(0, "ro.product.board");
        }
        return new CheckResult(1, "ro.product.board");
    }

    private static CheckResult checkFeatureByPlatform() {
        String property = getProperty("ro.product.board");
        if (property == null) {
            return new CheckResult(2, "ro.product.board");
        }
        if (property.toLowerCase().contains("android")) {
            return new CheckResult(0, "ro.product.board");
        }
        return new CheckResult(1, "ro.product.board");
    }

    private static CheckResult checkFeaturesByBaseBand() {
        String property = getProperty("gsm.version.baseband");
        if (property == null) {
            return new CheckResult(2, "gsm.version.baseband");
        }
        if (property.contains("1.0.0.0")) {
            return new CheckResult(0, "gsm.version.baseband");
        }
        return new CheckResult(1, "gsm.version.baseband");
    }

    private static int getSensorNumber(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getSensorList(-1).size();
    }

    private static boolean supportCamera(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.any");
    }

    private static boolean supportFlash(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
    }

    private static boolean supportBluetooth(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth");
    }

    private static boolean hasLightSensor(Context context) {
        return ((SensorManager) context.getSystemService("sensor")).getDefaultSensor(5) != null;
    }

    private static CheckResult checkFeaturesByCGroup() {
        if (CommandUtils.exc("cat /proc/self/cgroup") == null) {
            return new CheckResult(2, "cat /proc/self/cgroup");
        }
        return new CheckResult(1, "cat /proc/self/cgroup");
    }

    private static int getUserAppNum() {
        String exc = CommandUtils.exc("pm list package -3");
        if (TextUtils.isEmpty(exc)) {
            return 0;
        }
        return exc.split("package:").length;
    }

    public static ResultStatusEnum checkEmulator(Context context, OnEmulatorCheckCallback onEmulatorCheckCallback) {
        if (context == null) {
            return ResultStatusEnum.Waring;
        }
        CheckResult checkFeaturesByHardware = checkFeaturesByHardware();
        if (checkFeaturesByHardware.level == 0) {
            return ResultStatusEnum.Waring;
        }
        int i = checkFeaturesByHardware.level == 2 ? 1 : 0;
        CheckResult checkFeaturesByFlavor = checkFeaturesByFlavor();
        if (checkFeaturesByFlavor.level == 0) {
            return ResultStatusEnum.Waring;
        }
        if (checkFeaturesByFlavor.level == 2) {
            i++;
        }
        CheckResult checkFeaturesByModel = checkFeaturesByModel();
        if (checkFeaturesByModel.level == 0) {
            return ResultStatusEnum.Waring;
        }
        if (checkFeaturesByModel.level == 2) {
            i++;
        }
        CheckResult checkFeatureByManufacturer = checkFeatureByManufacturer();
        if (checkFeatureByManufacturer.level == 0) {
            return ResultStatusEnum.Waring;
        }
        if (checkFeatureByManufacturer.level == 2) {
            i++;
        }
        CheckResult checkFeatureByBoard = checkFeatureByBoard();
        if (checkFeatureByBoard.level == 0) {
            return ResultStatusEnum.Waring;
        }
        if (checkFeatureByBoard.level == 2) {
            i++;
        }
        CheckResult checkFeatureByPlatform = checkFeatureByPlatform();
        if (checkFeatureByPlatform.level == 0) {
            return ResultStatusEnum.Waring;
        }
        if (checkFeatureByPlatform.level == 2) {
            i++;
        }
        CheckResult checkFeaturesByBaseBand = checkFeaturesByBaseBand();
        if (checkFeaturesByBaseBand.level == 0) {
            return ResultStatusEnum.Waring;
        }
        if (checkFeaturesByBaseBand.level == 2) {
            i += 2;
        }
        if (getSensorNumber(context) <= 7) {
            i++;
        }
        if (!supportFlash(context)) {
            i++;
        }
        if (!supportCamera(context)) {
            i++;
        }
        if (!supportBluetooth(context)) {
            i++;
        }
        if (!hasLightSensor(context)) {
            i++;
        }
        if (checkFeaturesByCGroup().level == 2) {
            i++;
        }
        if (Build.VERSION.SDK_INT < 30 && getUserAppNum() <= 5) {
            i++;
        }
        if (i > 3) {
            return ResultStatusEnum.Waring;
        }
        return ResultStatusEnum.Safe;
    }
}
