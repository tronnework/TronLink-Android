package com.tron.wallet.ledger.blemodule;

import com.tron.wallet.ledger.blemodule.errors.BleError;
public interface OnErrorCallback {
    void onError(BleError bleError);
}
