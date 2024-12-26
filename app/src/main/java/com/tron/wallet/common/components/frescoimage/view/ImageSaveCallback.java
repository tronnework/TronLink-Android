package com.tron.wallet.common.components.frescoimage.view;
public interface ImageSaveCallback {
    void onFail(Throwable th);

    void onSuccess(String str);
}
