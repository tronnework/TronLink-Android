package com.journeyapps.barcodescanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.InactivityTimer;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.CameraPreview;
import com.journeyapps.barcodescanner.CaptureManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public class CaptureManager {
    private static final String SAVED_ORIENTATION_LOCK = "SAVED_ORIENTATION_LOCK";
    private static final String TAG = "CaptureManager";
    private static int cameraPermissionReqCode = 250;
    private Activity activity;
    private boolean askedPermission;
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private Handler handler;
    private InactivityTimer inactivityTimer;
    private final CameraPreview.StateListener stateListener;
    private int orientationLock = -1;
    private boolean returnBarcodeImagePath = false;
    private boolean showDialogIfMissingCameraPermission = true;
    private String missingCameraPermissionDialogMessage = "";
    private boolean destroyed = false;
    private boolean finishWhenClosed = false;
    private BarcodeCallback callback = new fun1();

    public static int getCameraPermissionReqCode() {
        return cameraPermissionReqCode;
    }

    public static void setCameraPermissionReqCode(int i) {
        cameraPermissionReqCode = i;
    }

    public void setShowMissingCameraPermissionDialog(boolean z, String str) {
        this.showDialogIfMissingCameraPermission = z;
        if (str == null) {
            str = "";
        }
        this.missingCameraPermissionDialogMessage = str;
    }

    public class fun1 implements BarcodeCallback {
        @Override
        public void possibleResultPoints(List<ResultPoint> list) {
        }

        fun1() {
        }

        @Override
        public void barcodeResult(final BarcodeResult barcodeResult) {
            barcodeView.pause();
            beepManager.playBeepSoundAndVibrate();
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    CaptureManager.1.this.lambda$barcodeResult$0$com-journeyapps-barcodescanner-CaptureManager$1(barcodeResult);
                }
            });
        }

        public void lambda$barcodeResult$0$com-journeyapps-barcodescanner-CaptureManager$1(BarcodeResult barcodeResult) {
            returnResult(barcodeResult);
        }
    }

    public CaptureManager(Activity activity, DecoratedBarcodeView decoratedBarcodeView) {
        CameraPreview.StateListener stateListener = new CameraPreview.StateListener() {
            @Override
            public void previewSized() {
            }

            @Override
            public void previewStarted() {
            }

            @Override
            public void previewStopped() {
            }

            @Override
            public void cameraError(Exception exc) {
                CaptureManager captureManager = CaptureManager.this;
                captureManager.displayFrameworkBugMessageAndExit(captureManager.activity.getString(R.string.zxing_msg_camera_framework_bug));
            }

            @Override
            public void cameraClosed() {
                if (finishWhenClosed) {
                    Log.d(CaptureManager.TAG, "Camera closed; finishing activity");
                    finish();
                }
            }
        };
        this.stateListener = stateListener;
        this.askedPermission = false;
        this.activity = activity;
        this.barcodeView = decoratedBarcodeView;
        decoratedBarcodeView.getBarcodeView().addStateListener(stateListener);
        this.handler = new Handler();
        this.inactivityTimer = new InactivityTimer(activity, new Runnable() {
            @Override
            public final void run() {
                lambda$new$0$com-journeyapps-barcodescanner-CaptureManager();
            }
        });
        this.beepManager = new BeepManager(activity);
    }

    public void lambda$new$0$com-journeyapps-barcodescanner-CaptureManager() {
        Log.d(TAG, "Finishing due to inactivity");
        finish();
    }

    public void initializeFromIntent(Intent intent, Bundle bundle) {
        this.activity.getWindow().addFlags(128);
        if (bundle != null) {
            this.orientationLock = bundle.getInt(SAVED_ORIENTATION_LOCK, -1);
        }
        if (intent != null) {
            if (intent.getBooleanExtra(Intents.Scan.ORIENTATION_LOCKED, true)) {
                lockOrientation();
            }
            if (Intents.Scan.ACTION.equals(intent.getAction())) {
                this.barcodeView.initializeFromIntent(intent);
            }
            if (!intent.getBooleanExtra(Intents.Scan.BEEP_ENABLED, true)) {
                this.beepManager.setBeepEnabled(false);
            }
            if (intent.hasExtra(Intents.Scan.SHOW_MISSING_CAMERA_PERMISSION_DIALOG)) {
                setShowMissingCameraPermissionDialog(intent.getBooleanExtra(Intents.Scan.SHOW_MISSING_CAMERA_PERMISSION_DIALOG, true), intent.getStringExtra(Intents.Scan.MISSING_CAMERA_PERMISSION_DIALOG_MESSAGE));
            }
            if (intent.hasExtra(Intents.Scan.TIMEOUT)) {
                this.handler.postDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        returnResultTimeout();
                    }
                }, intent.getLongExtra(Intents.Scan.TIMEOUT, 0L));
            }
            if (intent.getBooleanExtra(Intents.Scan.BARCODE_IMAGE_ENABLED, false)) {
                this.returnBarcodeImagePath = true;
            }
        }
    }

    protected void lockOrientation() {
        if (this.orientationLock == -1) {
            int rotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
            int i = this.activity.getResources().getConfiguration().orientation;
            int i2 = 0;
            if (i == 2) {
                if (rotation != 0 && rotation != 1) {
                    i2 = 8;
                }
            } else if (i == 1) {
                i2 = (rotation == 0 || rotation == 3) ? 1 : 9;
            }
            this.orientationLock = i2;
        }
        this.activity.setRequestedOrientation(this.orientationLock);
    }

    public void decode() {
        this.barcodeView.decodeSingle(this.callback);
    }

    public void onResume() {
        if (Build.VERSION.SDK_INT >= 23) {
            openCameraWithPermission();
        } else {
            this.barcodeView.resume();
        }
        this.inactivityTimer.start();
    }

    private void openCameraWithPermission() {
        if (ContextCompat.checkSelfPermission(this.activity, "android.permission.CAMERA") == 0) {
            this.barcodeView.resume();
        } else if (this.askedPermission) {
        } else {
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.CAMERA"}, cameraPermissionReqCode);
            this.askedPermission = true;
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == cameraPermissionReqCode) {
            if (iArr.length > 0 && iArr[0] == 0) {
                this.barcodeView.resume();
                return;
            }
            setMissingCameraPermissionResult();
            if (this.showDialogIfMissingCameraPermission) {
                displayFrameworkBugMessageAndExit(this.missingCameraPermissionDialogMessage);
            } else {
                closeAndFinish();
            }
        }
    }

    public void onPause() {
        this.inactivityTimer.cancel();
        this.barcodeView.pauseAndWait();
    }

    public void onDestroy() {
        this.destroyed = true;
        this.inactivityTimer.cancel();
        this.handler.removeCallbacksAndMessages(null);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt(SAVED_ORIENTATION_LOCK, this.orientationLock);
    }

    public static Intent resultIntent(BarcodeResult barcodeResult, String str) {
        Intent intent = new Intent(Intents.Scan.ACTION);
        intent.addFlags(524288);
        intent.putExtra("SCAN_RESULT", barcodeResult.toString());
        intent.putExtra(Intents.Scan.RESULT_FORMAT, barcodeResult.getBarcodeFormat().toString());
        byte[] rawBytes = barcodeResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra(Intents.Scan.RESULT_BYTES, rawBytes);
        }
        Map<ResultMetadataType, Object> resultMetadata = barcodeResult.getResultMetadata();
        if (resultMetadata != null) {
            if (resultMetadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra(Intents.Scan.RESULT_UPC_EAN_EXTENSION, resultMetadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number number = (Number) resultMetadata.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra(Intents.Scan.RESULT_ORIENTATION, number.intValue());
            }
            String str2 = (String) resultMetadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str2 != null) {
                intent.putExtra(Intents.Scan.RESULT_ERROR_CORRECTION_LEVEL, str2);
            }
            Iterable<byte[]> iterable = (Iterable) resultMetadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                int i = 0;
                for (byte[] bArr : iterable) {
                    intent.putExtra(Intents.Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, bArr);
                    i++;
                }
            }
        }
        if (str != null) {
            intent.putExtra(Intents.Scan.RESULT_BARCODE_IMAGE_PATH, str);
        }
        return intent;
    }

    private String getBarcodeImagePath(BarcodeResult barcodeResult) {
        if (this.returnBarcodeImagePath) {
            Bitmap bitmap = barcodeResult.getBitmap();
            try {
                File createTempFile = File.createTempFile("barcodeimage", ".jpg", this.activity.getCacheDir());
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.close();
                return createTempFile.getAbsolutePath();
            } catch (IOException e) {
                String str = TAG;
                Log.w(str, "Unable to create temporary file and store bitmap! " + e);
            }
        }
        return null;
    }

    public void finish() {
        this.activity.finish();
    }

    protected void closeAndFinish() {
        if (this.barcodeView.getBarcodeView().isCameraClosed()) {
            finish();
        } else {
            this.finishWhenClosed = true;
        }
        this.barcodeView.pause();
        this.inactivityTimer.cancel();
    }

    private void setMissingCameraPermissionResult() {
        Intent intent = new Intent(Intents.Scan.ACTION);
        intent.putExtra(Intents.Scan.MISSING_CAMERA_PERMISSION, true);
        this.activity.setResult(0, intent);
    }

    public void returnResultTimeout() {
        Intent intent = new Intent(Intents.Scan.ACTION);
        intent.putExtra(Intents.Scan.TIMEOUT, true);
        this.activity.setResult(0, intent);
        closeAndFinish();
    }

    protected void returnResult(BarcodeResult barcodeResult) {
        this.activity.setResult(-1, resultIntent(barcodeResult, getBarcodeImagePath(barcodeResult)));
        closeAndFinish();
    }

    protected void displayFrameworkBugMessageAndExit(String str) {
        if (this.activity.isFinishing() || this.destroyed || this.finishWhenClosed) {
            return;
        }
        if (str.isEmpty()) {
            str = this.activity.getString(R.string.zxing_msg_camera_framework_bug);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
        builder.setTitle(this.activity.getString(R.string.zxing_app_name));
        builder.setMessage(str);
        builder.setPositiveButton(R.string.zxing_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public final void onClick(DialogInterface dialogInterface, int i) {
                lambda$displayFrameworkBugMessageAndExit$1$com-journeyapps-barcodescanner-CaptureManager(dialogInterface, i);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public final void onCancel(DialogInterface dialogInterface) {
                lambda$displayFrameworkBugMessageAndExit$2$com-journeyapps-barcodescanner-CaptureManager(dialogInterface);
            }
        });
        builder.show();
    }

    public void lambda$displayFrameworkBugMessageAndExit$1$com-journeyapps-barcodescanner-CaptureManager(DialogInterface dialogInterface, int i) {
        finish();
    }

    public void lambda$displayFrameworkBugMessageAndExit$2$com-journeyapps-barcodescanner-CaptureManager(DialogInterface dialogInterface) {
        finish();
    }

    public void setShowMissingCameraPermissionDialog(boolean z) {
        setShowMissingCameraPermissionDialog(z, "");
    }
}
