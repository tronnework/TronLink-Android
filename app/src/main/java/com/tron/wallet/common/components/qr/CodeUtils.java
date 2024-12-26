package com.tron.wallet.common.components.qr;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.SentryUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
public class CodeUtils {
    private CodeUtils() {
        throw new AssertionError();
    }

    public static Bitmap createQRCode(String str, int i) {
        return createQRCode(str, i, null);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap) {
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashMap.put(EncodeHintType.MARGIN, 1);
        return createQRCode(str, i, bitmap, hashMap);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, Map<EncodeHintType, ?> map) {
        try {
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, map);
            int[] iArr = new int[i * i];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (encode.get(i3, i2)) {
                        iArr[(i2 * i) + i3] = -16777216;
                    } else {
                        iArr[(i2 * i) + i3] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return bitmap != null ? addLogo(createBitmap, bitmap) : createBitmap;
        } catch (WriterException | OutOfMemoryError e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return null;
        }
    }

    private static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f = ((width * 1.0f) / 6.0f) / width2;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f, f, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            return null;
        }
    }

    public static String parseQRCode(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(DecodeHintType.CHARACTER_SET, "utf-8");
        return parseQRCode(str, hashMap);
    }

    public static String parseQRCode(String str, Map<DecodeHintType, ?> map) {
        Result result;
        try {
            QRCodeReader qRCodeReader = new QRCodeReader();
            RGBLuminanceSource rGBLuminanceSource = getRGBLuminanceSource(compressBitmap(str));
            if (rGBLuminanceSource != null) {
                try {
                    result = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)), map);
                } catch (Exception unused) {
                    try {
                        result = qRCodeReader.decode(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)));
                    } catch (NotFoundException e) {
                        SentryUtil.captureException(e);
                        result = null;
                    }
                }
                qRCodeReader.reset();
            } else {
                result = null;
            }
            return result.getText();
        } catch (Exception e2) {
            SentryUtil.captureException(e2);
            LogUtils.e(e2);
            return null;
        }
    }

    public static String parseCode(String str) {
        HashMap hashMap = new HashMap();
        Vector vector = new Vector();
        vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
        vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        vector.addAll(DecodeFormatManager.AZTEC_FORMATS);
        vector.addAll(DecodeFormatManager.PDF417_FORMATS);
        vector.addAll(DecodeFormatManager.PDF417_FORMATS);
        hashMap.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        return parseCode(str, hashMap);
    }

    public static String parseCode(String str, Map<DecodeHintType, Object> map) {
        Result result;
        try {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            multiFormatReader.setHints(map);
            RGBLuminanceSource rGBLuminanceSource = getRGBLuminanceSource(compressBitmap(str));
            if (rGBLuminanceSource != null) {
                try {
                    result = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)));
                } catch (Exception unused) {
                    map.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
                    multiFormatReader.setHints(map);
                    try {
                        result = multiFormatReader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)));
                    } catch (NotFoundException e) {
                        SentryUtil.captureException(e);
                        result = null;
                    }
                }
                multiFormatReader.reset();
            } else {
                result = null;
            }
            if (result == null) {
                return null;
            }
            return result.getText();
        } catch (Exception e2) {
            SentryUtil.captureException(e2);
            LogUtils.e(e2);
            return null;
        }
    }

    private static android.graphics.Bitmap compressBitmap(java.lang.String r6) {
        


return null;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.common.components.qr.CodeUtils.compressBitmap(java.lang.String):android.graphics.Bitmap");
    }

    private static RGBLuminanceSource getRGBLuminanceSource(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return new RGBLuminanceSource(width, height, iArr);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return createBarCode(str, barcodeFormat, i, i2, null);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        return createBarCode(str, barcodeFormat, i, i2, map, false, 40, ViewCompat.MEASURED_STATE_MASK);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z) {
        return createBarCode(str, barcodeFormat, i, i2, map, z, 40, ViewCompat.MEASURED_STATE_MASK);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z, int i3, int i4) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, barcodeFormat, i, i2, map);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i5 = 0; i5 < height; i5++) {
                int i6 = i5 * width;
                for (int i7 = 0; i7 < width; i7++) {
                    iArr[i6 + i7] = encode.get(i7, i5) ? ViewCompat.MEASURED_STATE_MASK : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return z ? addCode(createBitmap, str, i3, i4, i3 / 2) : createBitmap;
        } catch (WriterException e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return null;
        }
    }

    private static Bitmap addCode(Bitmap bitmap, String str, int i, int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height + i + (i3 * 2), Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(i);
            textPaint.setColor(i2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, width / 2, height + (i / 2) + i3, textPaint);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            LogUtils.e(e);
            SentryUtil.captureException(e);
            return null;
        }
    }
}
