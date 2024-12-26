package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.zxing.client.android.R;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.Util;
public class CameraInstance {
    private static final String TAG = "CameraInstance";
    private CameraManager cameraManager;
    private CameraThread cameraThread;
    private DisplayConfiguration displayConfiguration;
    private Handler mainHandler;
    private Handler readyHandler;
    private CameraSurface surface;
    private boolean open = false;
    private boolean cameraClosed = true;
    private CameraSettings cameraSettings = new CameraSettings();
    private Runnable opener = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Opening camera");
                cameraManager.open();
            } catch (Exception e) {
                notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to open camera", e);
            }
        }
    };
    private Runnable configure = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Configuring camera");
                cameraManager.configure();
                if (readyHandler != null) {
                    readyHandler.obtainMessage(R.id.zxing_prewiew_size_ready, getPreviewSize()).sendToTarget();
                }
            } catch (Exception e) {
                notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to configure camera", e);
            }
        }
    };
    private Runnable previewStarter = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Starting preview");
                cameraManager.setPreviewDisplay(surface);
                cameraManager.startPreview();
            } catch (Exception e) {
                notifyError(e);
                Log.e(CameraInstance.TAG, "Failed to start preview", e);
            }
        }
    };
    private Runnable closer = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(CameraInstance.TAG, "Closing camera");
                cameraManager.stopPreview();
                cameraManager.close();
            } catch (Exception e) {
                Log.e(CameraInstance.TAG, "Failed to close camera", e);
            }
            cameraClosed = true;
            readyHandler.sendEmptyMessage(R.id.zxing_camera_closed);
            cameraThread.decrementInstances();
        }
    };

    protected CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public CameraSettings getCameraSettings() {
        return this.cameraSettings;
    }

    protected CameraThread getCameraThread() {
        return this.cameraThread;
    }

    public DisplayConfiguration getDisplayConfiguration() {
        return this.displayConfiguration;
    }

    protected CameraSurface getSurface() {
        return this.surface;
    }

    public boolean isCameraClosed() {
        return this.cameraClosed;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setReadyHandler(Handler handler) {
        this.readyHandler = handler;
    }

    public void setSurface(CameraSurface cameraSurface) {
        this.surface = cameraSurface;
    }

    public CameraInstance(Context context) {
        Util.validateMainThread();
        this.cameraThread = CameraThread.getInstance();
        CameraManager cameraManager = new CameraManager(context);
        this.cameraManager = cameraManager;
        cameraManager.setCameraSettings(this.cameraSettings);
        this.mainHandler = new Handler();
    }

    public CameraInstance(CameraManager cameraManager) {
        Util.validateMainThread();
        this.cameraManager = cameraManager;
    }

    public void setDisplayConfiguration(DisplayConfiguration displayConfiguration) {
        this.displayConfiguration = displayConfiguration;
        this.cameraManager.setDisplayConfiguration(displayConfiguration);
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        setSurface(new CameraSurface(surfaceHolder));
    }

    public void setCameraSettings(CameraSettings cameraSettings) {
        if (this.open) {
            return;
        }
        this.cameraSettings = cameraSettings;
        this.cameraManager.setCameraSettings(cameraSettings);
    }

    public Size getPreviewSize() {
        return this.cameraManager.getPreviewSize();
    }

    public int getCameraRotation() {
        return this.cameraManager.getCameraRotation();
    }

    public void open() {
        Util.validateMainThread();
        this.open = true;
        this.cameraClosed = false;
        this.cameraThread.incrementAndEnqueue(this.opener);
    }

    public void configureCamera() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.configure);
    }

    public void startPreview() {
        Util.validateMainThread();
        validateOpen();
        this.cameraThread.enqueue(this.previewStarter);
    }

    public void setTorch(final boolean z) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new Runnable() {
                @Override
                public final void run() {
                    lambda$setTorch$0$com-journeyapps-barcodescanner-camera-CameraInstance(z);
                }
            });
        }
    }

    public void lambda$setTorch$0$com-journeyapps-barcodescanner-camera-CameraInstance(boolean z) {
        this.cameraManager.setTorch(z);
    }

    public void changeCameraParameters(final CameraParametersCallback cameraParametersCallback) {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(new Runnable() {
                @Override
                public final void run() {
                    lambda$changeCameraParameters$1$com-journeyapps-barcodescanner-camera-CameraInstance(cameraParametersCallback);
                }
            });
        }
    }

    public void lambda$changeCameraParameters$1$com-journeyapps-barcodescanner-camera-CameraInstance(CameraParametersCallback cameraParametersCallback) {
        this.cameraManager.changeCameraParameters(cameraParametersCallback);
    }

    public void close() {
        Util.validateMainThread();
        if (this.open) {
            this.cameraThread.enqueue(this.closer);
        } else {
            this.cameraClosed = true;
        }
        this.open = false;
    }

    public void requestPreview(final PreviewCallback previewCallback) {
        this.mainHandler.post(new Runnable() {
            @Override
            public final void run() {
                lambda$requestPreview$3$com-journeyapps-barcodescanner-camera-CameraInstance(previewCallback);
            }
        });
    }

    public void lambda$requestPreview$3$com-journeyapps-barcodescanner-camera-CameraInstance(final PreviewCallback previewCallback) {
        if (!this.open) {
            Log.d(TAG, "Camera is closed, not requesting preview");
        } else {
            this.cameraThread.enqueue(new Runnable() {
                @Override
                public final void run() {
                    lambda$requestPreview$2$com-journeyapps-barcodescanner-camera-CameraInstance(previewCallback);
                }
            });
        }
    }

    public void lambda$requestPreview$2$com-journeyapps-barcodescanner-camera-CameraInstance(PreviewCallback previewCallback) {
        this.cameraManager.requestPreviewFrame(previewCallback);
    }

    private void validateOpen() {
        if (!this.open) {
            throw new IllegalStateException("CameraInstance is not open");
        }
    }

    public void notifyError(Exception exc) {
        Handler handler = this.readyHandler;
        if (handler != null) {
            handler.obtainMessage(R.id.zxing_camera_error, exc).sendToTarget();
        }
    }
}
