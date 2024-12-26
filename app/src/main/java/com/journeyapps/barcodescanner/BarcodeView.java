package com.journeyapps.barcodescanner;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.R;
import java.util.HashMap;
import java.util.List;
public class BarcodeView extends CameraPreview {
    private BarcodeCallback callback;
    private DecodeMode decodeMode;
    private DecoderFactory decoderFactory;
    private DecoderThread decoderThread;
    private final Handler.Callback resultCallback;
    private Handler resultHandler;

    public enum DecodeMode {
        NONE,
        SINGLE,
        CONTINUOUS
    }

    public DecoderFactory getDecoderFactory() {
        return this.decoderFactory;
    }

    public BarcodeView(Context context) {
        super(context);
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        this.resultCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == R.id.zxing_decode_succeeded) {
                    BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                    if (barcodeResult != null && callback != null && decodeMode != DecodeMode.NONE) {
                        callback.barcodeResult(barcodeResult);
                        if (decodeMode == DecodeMode.SINGLE) {
                            stopDecoding();
                        }
                    }
                    return true;
                } else if (message.what == R.id.zxing_decode_failed) {
                    return true;
                } else {
                    if (message.what == R.id.zxing_possible_result_points) {
                        List<ResultPoint> list = (List) message.obj;
                        if (callback != null && decodeMode != DecodeMode.NONE) {
                            callback.possibleResultPoints(list);
                        }
                        return true;
                    }
                    return false;
                }
            }
        };
        initialize();
    }

    public BarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        this.resultCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == R.id.zxing_decode_succeeded) {
                    BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                    if (barcodeResult != null && callback != null && decodeMode != DecodeMode.NONE) {
                        callback.barcodeResult(barcodeResult);
                        if (decodeMode == DecodeMode.SINGLE) {
                            stopDecoding();
                        }
                    }
                    return true;
                } else if (message.what == R.id.zxing_decode_failed) {
                    return true;
                } else {
                    if (message.what == R.id.zxing_possible_result_points) {
                        List<ResultPoint> list = (List) message.obj;
                        if (callback != null && decodeMode != DecodeMode.NONE) {
                            callback.possibleResultPoints(list);
                        }
                        return true;
                    }
                    return false;
                }
            }
        };
        initialize();
    }

    public BarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        this.resultCallback = new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == R.id.zxing_decode_succeeded) {
                    BarcodeResult barcodeResult = (BarcodeResult) message.obj;
                    if (barcodeResult != null && callback != null && decodeMode != DecodeMode.NONE) {
                        callback.barcodeResult(barcodeResult);
                        if (decodeMode == DecodeMode.SINGLE) {
                            stopDecoding();
                        }
                    }
                    return true;
                } else if (message.what == R.id.zxing_decode_failed) {
                    return true;
                } else {
                    if (message.what == R.id.zxing_possible_result_points) {
                        List<ResultPoint> list = (List) message.obj;
                        if (callback != null && decodeMode != DecodeMode.NONE) {
                            callback.possibleResultPoints(list);
                        }
                        return true;
                    }
                    return false;
                }
            }
        };
        initialize();
    }

    private void initialize() {
        this.decoderFactory = new DefaultDecoderFactory();
        this.resultHandler = new Handler(this.resultCallback);
    }

    public void setDecoderFactory(DecoderFactory decoderFactory) {
        Util.validateMainThread();
        this.decoderFactory = decoderFactory;
        DecoderThread decoderThread = this.decoderThread;
        if (decoderThread != null) {
            decoderThread.setDecoder(createDecoder());
        }
    }

    private Decoder createDecoder() {
        if (this.decoderFactory == null) {
            this.decoderFactory = createDefaultDecoderFactory();
        }
        DecoderResultPointCallback decoderResultPointCallback = new DecoderResultPointCallback();
        HashMap hashMap = new HashMap();
        hashMap.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, decoderResultPointCallback);
        Decoder createDecoder = this.decoderFactory.createDecoder(hashMap);
        decoderResultPointCallback.setDecoder(createDecoder);
        return createDecoder;
    }

    public void decodeSingle(BarcodeCallback barcodeCallback) {
        this.decodeMode = DecodeMode.SINGLE;
        this.callback = barcodeCallback;
        startDecoderThread();
    }

    public void decodeContinuous(BarcodeCallback barcodeCallback) {
        this.decodeMode = DecodeMode.CONTINUOUS;
        this.callback = barcodeCallback;
        startDecoderThread();
    }

    public void stopDecoding() {
        this.decodeMode = DecodeMode.NONE;
        this.callback = null;
        stopDecoderThread();
    }

    protected DecoderFactory createDefaultDecoderFactory() {
        return new DefaultDecoderFactory();
    }

    private void startDecoderThread() {
        stopDecoderThread();
        if (this.decodeMode == DecodeMode.NONE || !isPreviewActive()) {
            return;
        }
        DecoderThread decoderThread = new DecoderThread(getCameraInstance(), createDecoder(), this.resultHandler);
        this.decoderThread = decoderThread;
        decoderThread.setCropRect(getPreviewFramingRect());
        this.decoderThread.start();
    }

    @Override
    public void previewStarted() {
        super.previewStarted();
        startDecoderThread();
    }

    private void stopDecoderThread() {
        DecoderThread decoderThread = this.decoderThread;
        if (decoderThread != null) {
            decoderThread.stop();
            this.decoderThread = null;
        }
    }

    @Override
    public void pause() {
        stopDecoderThread();
        super.pause();
    }
}
