package com.tron.wallet.ledger.blemodule.utils;

import com.tron.wallet.ledger.blemodule.OnErrorCallback;
import com.tron.wallet.ledger.blemodule.OnSuccessCallback;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import java.util.concurrent.atomic.AtomicBoolean;
public class SafeExecutor<T> {
    private OnErrorCallback errorCallback;
    private OnSuccessCallback<T> successCallback;
    private AtomicBoolean wasExecuted = new AtomicBoolean(false);

    public SafeExecutor(OnSuccessCallback<T> onSuccessCallback, OnErrorCallback onErrorCallback) {
        this.successCallback = onSuccessCallback;
        this.errorCallback = onErrorCallback;
    }

    public void success(T t) {
        if (this.wasExecuted.compareAndSet(false, true)) {
            this.successCallback.onSuccess(t);
        }
    }

    public void error(BleError bleError) {
        if (this.wasExecuted.compareAndSet(false, true)) {
            this.errorCallback.onError(bleError);
        }
    }
}
