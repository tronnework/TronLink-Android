package com.journeyapps.barcodescanner;

import android.content.Context;
import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContract;
public class ScanContract extends ActivityResultContract<ScanOptions, ScanIntentResult> {
    @Override
    public Intent createIntent(Context context, ScanOptions scanOptions) {
        return scanOptions.createScanIntent(context);
    }

    @Override
    public ScanIntentResult parseResult(int i, Intent intent) {
        return ScanIntentResult.parseActivityResult(i, intent);
    }
}
