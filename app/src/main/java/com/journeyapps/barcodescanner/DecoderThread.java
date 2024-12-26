package com.journeyapps.barcodescanner;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.google.zxing.LuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.PreviewCallback;
public class DecoderThread {
    private static final String TAG = "DecoderThread";
    private CameraInstance cameraInstance;
    private Rect cropRect;
    private Decoder decoder;
    private Handler handler;
    private Handler resultHandler;
    private HandlerThread thread;
    private boolean running = false;
    private final Object LOCK = new Object();
    private final Handler.Callback callback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (message.what == R.id.zxing_decode) {
                decode((SourceData) message.obj);
                return true;
            } else if (message.what == R.id.zxing_preview_failed) {
                requestNextPreview();
                return true;
            } else {
                return true;
            }
        }
    };
    private final PreviewCallback previewCallback = new PreviewCallback() {
        @Override
        public void onPreview(SourceData sourceData) {
            synchronized (LOCK) {
                if (running) {
                    handler.obtainMessage(R.id.zxing_decode, sourceData).sendToTarget();
                }
            }
        }

        @Override
        public void onPreviewError(Exception exc) {
            synchronized (LOCK) {
                if (running) {
                    handler.obtainMessage(R.id.zxing_preview_failed).sendToTarget();
                }
            }
        }
    };

    public Rect getCropRect() {
        return this.cropRect;
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public void setCropRect(Rect rect) {
        this.cropRect = rect;
    }

    public void setDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    public DecoderThread(CameraInstance cameraInstance, Decoder decoder, Handler handler) {
        Util.validateMainThread();
        this.cameraInstance = cameraInstance;
        this.decoder = decoder;
        this.resultHandler = handler;
    }

    public void start() {
        Util.validateMainThread();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.thread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.thread.getLooper(), this.callback);
        this.running = true;
        requestNextPreview();
    }

    public void stop() {
        Util.validateMainThread();
        synchronized (this.LOCK) {
            this.running = false;
            this.handler.removeCallbacksAndMessages(null);
            this.thread.quit();
        }
    }

    public void requestNextPreview() {
        this.cameraInstance.requestPreview(this.previewCallback);
    }

    protected LuminanceSource createSource(SourceData sourceData) {
        if (this.cropRect == null) {
            return null;
        }
        return sourceData.createSource();
    }

    public void decode(SourceData sourceData) {
        long currentTimeMillis = System.currentTimeMillis();
        sourceData.setCropRect(this.cropRect);
        LuminanceSource createSource = createSource(sourceData);
        Result decode = createSource != null ? this.decoder.decode(createSource) : null;
        if (decode != null) {
            long currentTimeMillis2 = System.currentTimeMillis();
            String str = TAG;
            Log.d(str, "Found barcode in " + (currentTimeMillis2 - currentTimeMillis) + " ms");
            if (this.resultHandler != null) {
                Message obtain = Message.obtain(this.resultHandler, R.id.zxing_decode_succeeded, new BarcodeResult(decode, sourceData));
                obtain.setData(new Bundle());
                obtain.sendToTarget();
            }
        } else {
            Handler handler = this.resultHandler;
            if (handler != null) {
                Message.obtain(handler, R.id.zxing_decode_failed).sendToTarget();
            }
        }
        if (this.resultHandler != null) {
            Message.obtain(this.resultHandler, R.id.zxing_possible_result_points, BarcodeResult.transformResultPoints(this.decoder.getPossibleResultPoints(), sourceData)).sendToTarget();
        }
        requestNextPreview();
    }
}
