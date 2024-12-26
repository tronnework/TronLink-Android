package com.tron.wallet.ledger.blemodule.exceptions;

import com.tron.wallet.ledger.blemodule.Characteristic;
public class CannotMonitorCharacteristicException extends RuntimeException {
    private Characteristic characteristic;

    public Characteristic getCharacteristic() {
        return this.characteristic;
    }

    public CannotMonitorCharacteristicException(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
}
