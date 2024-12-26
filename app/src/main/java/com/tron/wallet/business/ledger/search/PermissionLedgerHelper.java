package com.tron.wallet.business.ledger.search;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import com.lxj.xpopup.core.BasePopupView;
import com.tron.wallet.common.components.ConfirmCustomPopupView;
import com.tron.wallet.common.interfaces.PermissionInterface;
import com.tron.wallet.common.utils.PermissionHelper;
import com.tronlinkpro.wallet.R;
public class PermissionLedgerHelper implements PermissionInterface {
    private static final int REQ_CODE_LOCATION = 2000;
    private Fragment fragment;
    private ActivityResultLauncher<Intent> launchBle;
    private ActivityResultLauncher<Intent> launchLocation;
    private OnPermissionResultCallback onPermissionResultCallback;
    private PermissionHelper permissionHelper;
    private BasePopupView popForLocation;
    private boolean reqBlePermission;

    public interface OnPermissionResultCallback {
        void onBluetoothFail();

        void onLocationFail();

        void onSuccess();
    }

    public PermissionHelper getPermissionHelper() {
        return this.permissionHelper;
    }

    @Override
    public int getPermissionsRequestCode() {
        return 2000;
    }

    public PermissionLedgerHelper(Fragment fragment) {
        this.fragment = fragment;
        this.launchBle = fragment.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                if (activityResult.getResultCode() == -1) {
                    onPermissionResultCallback.onSuccess();
                } else {
                    onPermissionResultCallback.onBluetoothFail();
                }
            }
        });
        this.launchLocation = fragment.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult activityResult) {
                if (checkPermission()) {
                    checkBleScanPermission();
                } else {
                    onPermissionResultCallback.onLocationFail();
                }
            }
        });
    }

    public void checkPermissions(OnPermissionResultCallback onPermissionResultCallback) {
        if (onPermissionResultCallback == null) {
            return;
        }
        this.onPermissionResultCallback = onPermissionResultCallback;
        if (checkPermission()) {
            checkBleScanPermission();
        } else {
            showPopForLocation();
        }
    }

    private void checkBluetoothEnable() {
        if (BluetoothAdapter.getDefaultAdapter() == null || !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            this.launchBle.launch(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"));
            return;
        }
        this.onPermissionResultCallback.onSuccess();
    }

    public void checkBleScanPermission() {
        if (Build.VERSION.SDK_INT >= 31) {
            Context context = this.fragment.getContext();
            if (context.getPackageManager().checkPermission("android.permission.BLUETOOTH_SCAN", context.getPackageName()) == 0 && context.getPackageManager().checkPermission("android.permission.BLUETOOTH_CONNECT", context.getPackageName()) == 0 && context.getPackageManager().checkPermission("android.permission.BLUETOOTH_ADVERTISE", context.getPackageName()) == 0) {
                checkBluetoothEnable();
                return;
            }
            this.reqBlePermission = true;
            PermissionHelper permissionHelper = new PermissionHelper(this.fragment.getActivity(), this);
            this.permissionHelper = permissionHelper;
            permissionHelper.requestPermissions();
            return;
        }
        checkBluetoothEnable();
    }

    private void showPopForLocation() {
        BasePopupView basePopupView = this.popForLocation;
        if (basePopupView == null) {
            final Context context = this.fragment.getContext();
            this.popForLocation = ConfirmCustomPopupView.getBuilder(context).setTitle(context.getString(R.string.access_location)).setTitleSize(18).setInfo(context.getResources().getString(R.string.access_location_description)).setConfirmText(context.getResources().getString(R.string.to_open)).setConfirmListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$showPopForLocation$0(context, view);
                }
            }).setCancelText(context.getString(R.string.cancel)).setCancleListener(new View.OnClickListener() {
                @Override
                public final void onClick(View view) {
                    lambda$showPopForLocation$1(view);
                }
            }).build().show();
        } else if (basePopupView.isShow()) {
        } else {
            this.popForLocation.show();
        }
    }

    public void lambda$showPopForLocation$0(Context context, View view) {
        if (this.fragment.shouldShowRequestPermissionRationale("android.permission.ACCESS_COARSE_LOCATION")) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            this.launchLocation.launch(intent);
            return;
        }
        PermissionHelper permissionHelper = new PermissionHelper(this.fragment.getActivity(), this);
        this.permissionHelper = permissionHelper;
        permissionHelper.requestPermissions();
    }

    public void lambda$showPopForLocation$1(View view) {
        this.onPermissionResultCallback.onLocationFail();
    }

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            Context context = this.fragment.getContext();
            return context.getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName()) == 0 && context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", context.getPackageName()) == 0;
        }
        return true;
    }

    @Override
    public String[] getPermissions() {
        if (this.reqBlePermission) {
            return new String[]{"android.permission.BLUETOOTH_SCAN", "android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_ADVERTISE"};
        }
        return new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    }

    @Override
    public void requestPermissionsSuccess() {
        if (!this.reqBlePermission) {
            checkBleScanPermission();
        } else {
            checkBluetoothEnable();
        }
    }

    @Override
    public void requestPermissionsFail() {
        this.onPermissionResultCallback.onLocationFail();
    }
}
