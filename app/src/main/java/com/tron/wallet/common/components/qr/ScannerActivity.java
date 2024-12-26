package com.tron.wallet.common.components.qr;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.arasthel.asyncjob.AsyncJob;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.ScanOptions;
import com.tron.tron_base.frame.utils.LanguageUtils;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.tabdapp.browser.BrowserConstant;
import com.tron.wallet.common.utils.NoDoubleClickListener;
import com.tron.wallet.common.utils.UriUtils;
import com.tronlinkpro.wallet.R;
public class ScannerActivity extends AppCompatActivity {
    public int PERMISSION_REQUEST_CODE_WRITE_EXTERNAL_STORAGE = BrowserConstant.REQUEST_CODE_PERMISSION;
    public int REQUEST_CODE_GET_PIC_URI2 = BrowserConstant.REQUEST_CODE_GET_PIC_URI2;
    private DecoratedBarcodeView barcodeScannerView;
    private CaptureManager capture;
    View llBack;
    private Toolbar mToolBar;
    TextView tvGetPic;

    public static void start(Activity activity) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats("QR_CODE");
        intentIntegrator.setPrompt(activity.getString(R.string.scanner_view_tip_text));
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.setCaptureActivity(ScannerActivity.class);
        intentIntegrator.initiateScan();
    }

    public static void start(ActivityResultLauncher activityResultLauncher) {
        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setDesiredBarcodeFormats("QR_CODE");
        scanOptions.setPrompt(StringTronUtil.getResString(R.string.scanner_view_tip_text));
        scanOptions.setCameraId(0);
        scanOptions.setBeepEnabled(false);
        scanOptions.setBarcodeImageEnabled(false);
        scanOptions.setCaptureActivity(ScannerActivity.class);
        activityResultLauncher.launch(scanOptions);
    }

    public static void startFromFragment(Fragment fragment) {
        IntentIntegrator forSupportFragment = IntentIntegrator.forSupportFragment(fragment);
        forSupportFragment.setDesiredBarcodeFormats("QR_CODE");
        forSupportFragment.setPrompt(fragment.getString(R.string.scanner_view_tip_text));
        forSupportFragment.setCameraId(0);
        forSupportFragment.setBeepEnabled(false);
        forSupportFragment.setBarcodeImageEnabled(false);
        forSupportFragment.setCaptureActivity(ScannerActivity.class);
        forSupportFragment.initiateScan();
    }

    public static void startForFragment(Fragment fragment) {
        IntentIntegrator forSupportFragment = IntentIntegrator.forSupportFragment(fragment);
        forSupportFragment.setDesiredBarcodeFormats("QR_CODE");
        forSupportFragment.setPrompt(fragment.getString(R.string.scanner_view_tip_text));
        forSupportFragment.setCameraId(0);
        forSupportFragment.setRequestCode(100);
        forSupportFragment.setBeepEnabled(false);
        forSupportFragment.setBarcodeImageEnabled(false);
        forSupportFragment.setCaptureActivity(ScannerActivity.class);
        forSupportFragment.initiateScan();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LanguageUtils.setLocal(this);
        LanguageUtils.setApplicationLanguage(getApplicationContext());
        this.barcodeScannerView = initializeContent();
        CaptureManager captureManager = new CaptureManager(this, this.barcodeScannerView);
        this.capture = captureManager;
        captureManager.initializeFromIntent(getIntent(), bundle);
        this.capture.decode();
        this.tvGetPic = (TextView) findViewById(R.id.tv_pic);
        this.llBack = findViewById(R.id.ll_back);
        initUI();
        this.tvGetPic.setText(getApplicationContext().getResources().getString(R.string.album));
    }

    private void initUI() {
        this.llBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                finish();
            }
        });
        this.tvGetPic.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                getPic();
            }
        });
    }

    public void getPic() {
        if (Build.VERSION.SDK_INT < 33) {
            if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                goPicture();
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, this.PERMISSION_REQUEST_CODE_WRITE_EXTERNAL_STORAGE);
                return;
            }
        }
        goPicture();
    }

    private void goPicture() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, this.REQUEST_CODE_GET_PIC_URI2);
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 20001) {
            final String imagePath = UriUtils.INSTANCE.getImagePath(this, intent);
            AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
                @Override
                public final void doOnBackground() {
                    lambda$onActivityResult$1(imagePath);
                }
            });
        }
    }

    public void lambda$onActivityResult$1(String str) {
        final String parseCode = CodeUtils.parseCode(str);
        runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                lambda$onActivityResult$0(parseCode);
            }
        });
    }

    public void lambda$onActivityResult$0(String str) {
        Intent intent = new Intent();
        intent.putExtra("SCAN_RESULT", str);
        setResult(-1, intent);
        finish();
    }

    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.layout_activity_scanner);
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.capture.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.capture.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.capture.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.capture.onSaveInstanceState(bundle);
    }

    @Override
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.capture.onRequestPermissionsResult(i, strArr, iArr);
        if (i == this.PERMISSION_REQUEST_CODE_WRITE_EXTERNAL_STORAGE && iArr.length > 0 && iArr[0] == 0) {
            goPicture();
        }
    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.barcodeScannerView.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }
}
