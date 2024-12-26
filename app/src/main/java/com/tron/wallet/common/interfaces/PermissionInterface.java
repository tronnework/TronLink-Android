package com.tron.wallet.common.interfaces;
public interface PermissionInterface {
    String[] getPermissions();

    int getPermissionsRequestCode();

    void requestPermissionsFail();

    void requestPermissionsSuccess();
}
