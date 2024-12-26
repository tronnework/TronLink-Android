package com.tron.wallet.common.utils;

import android.app.Activity;
import com.tron.wallet.common.interfaces.PermissionInterface;
public class PermissionHelper {
    private Activity mActivity;
    private PermissionInterface mPermissionInterface;

    public PermissionHelper(Activity activity, PermissionInterface permissionInterface) {
        this.mActivity = activity;
        this.mPermissionInterface = permissionInterface;
    }

    public void requestPermissions() {
        String[] deniedPermissions = PermissionUtil.getDeniedPermissions(this.mActivity, this.mPermissionInterface.getPermissions());
        if (deniedPermissions != null && deniedPermissions.length > 0) {
            PermissionUtil.requestPermissions(this.mActivity, deniedPermissions, this.mPermissionInterface.getPermissionsRequestCode());
        } else {
            this.mPermissionInterface.requestPermissionsSuccess();
        }
    }

    public boolean requestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == this.mPermissionInterface.getPermissionsRequestCode()) {
            for (int i2 : iArr) {
                if (i2 == -1) {
                    this.mPermissionInterface.requestPermissionsFail();
                    return true;
                }
            }
            this.mPermissionInterface.requestPermissionsSuccess();
            return true;
        }
        return false;
    }
}
