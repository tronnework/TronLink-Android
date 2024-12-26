package com.journeyapps.barcodescanner;

import com.google.zxing.ResultPoint;
import java.util.List;
public interface BarcodeCallback {

    public final class -CC {
        public static void $default$possibleResultPoints(BarcodeCallback _this, List list) {
        }
    }

    void barcodeResult(BarcodeResult barcodeResult);

    void possibleResultPoints(List<ResultPoint> list);
}
