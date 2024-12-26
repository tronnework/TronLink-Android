package com.tron.wallet.ledger.bleclient;

import com.tron.wallet.ledger.blemodule.Device;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import io.reactivex.Observable;
public abstract class Transport {
    public abstract void disconnect();

    public abstract Device getDevice();

    public abstract String getDeviceId();

    public abstract boolean isConnected();

    public abstract Observable<Device> open();

    public abstract Observable<Device> open(long j);

    public abstract Observable<byte[]> send(byte b, byte b2, byte b3, byte b4, byte[] bArr) throws BleError;
}
