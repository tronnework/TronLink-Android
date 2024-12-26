package com.tron.wallet.ledger.bleclient;

import com.tron.wallet.ledger.blemodule.errors.BleError;
import io.reactivex.functions.Consumer;
public interface OnBleError extends Consumer<Throwable> {
    void accept(Throwable th) throws Exception;

    void onError(BleError bleError);

    public final class -CC {
        public static void $default$accept(OnBleError _this, Throwable th) throws Exception {
            if (th instanceof BleError) {
                _this.onError((BleError) th);
            } else {
                _this.onError(new BleError(th));
            }
        }
    }
}
