package com.polidea.rxandroidble2.exceptions;

import java.util.UUID;
public class BleServiceNotFoundException extends BleException {
    private final UUID serviceUUID;

    public UUID getServiceUUID() {
        return this.serviceUUID;
    }

    public BleServiceNotFoundException(UUID uuid) {
        super("BLE Service not found with UUID " + uuid);
        this.serviceUUID = uuid;
    }
}
