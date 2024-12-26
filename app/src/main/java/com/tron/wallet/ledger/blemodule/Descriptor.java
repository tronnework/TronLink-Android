package com.tron.wallet.ledger.blemodule;

import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.tron.wallet.ledger.blemodule.utils.ByteUtils;
import com.tron.wallet.ledger.blemodule.utils.IdGenerator;
import com.tron.wallet.ledger.blemodule.utils.IdGeneratorKey;
import java.util.UUID;
public class Descriptor {
    private final int characteristicId;
    private final UUID characteristicUuid;
    private final BluetoothGattDescriptor descriptor;
    private final String deviceId;
    private final int id;
    private final int serviceId;
    private final UUID serviceUuid;
    private final UUID uuid;
    private byte[] value;

    public int getCharacteristicId() {
        return this.characteristicId;
    }

    public UUID getCharacteristicUuid() {
        return this.characteristicUuid;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int getId() {
        return this.id;
    }

    public BluetoothGattDescriptor getNativeDescriptor() {
        return this.descriptor;
    }

    public int getServiceId() {
        return this.serviceId;
    }

    public UUID getServiceUuid() {
        return this.serviceUuid;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public byte[] getValue() {
        return this.value;
    }

    public void setValue(byte[] bArr) {
        this.value = bArr;
    }

    public Descriptor(Characteristic characteristic, BluetoothGattDescriptor bluetoothGattDescriptor) {
        this.value = null;
        int id = characteristic.getId();
        this.characteristicId = id;
        this.characteristicUuid = characteristic.getUuid();
        this.serviceId = characteristic.getServiceID();
        this.serviceUuid = characteristic.getServiceUUID();
        this.descriptor = bluetoothGattDescriptor;
        String deviceId = characteristic.getDeviceId();
        this.deviceId = deviceId;
        this.id = IdGenerator.getIdForKey(new IdGeneratorKey(deviceId, bluetoothGattDescriptor.getUuid(), id));
        this.uuid = bluetoothGattDescriptor.getUuid();
    }

    public Descriptor(int i, int i2, UUID uuid, UUID uuid2, String str, BluetoothGattDescriptor bluetoothGattDescriptor, int i3, UUID uuid3) {
        this.value = null;
        this.characteristicId = i;
        this.serviceId = i2;
        this.characteristicUuid = uuid;
        this.serviceUuid = uuid2;
        this.deviceId = str;
        this.descriptor = bluetoothGattDescriptor;
        this.id = i3;
        this.uuid = uuid3;
    }

    public Descriptor(Descriptor descriptor) {
        this.value = null;
        this.characteristicUuid = descriptor.characteristicUuid;
        this.characteristicId = descriptor.characteristicId;
        this.serviceUuid = descriptor.serviceUuid;
        this.serviceId = descriptor.serviceId;
        this.deviceId = descriptor.deviceId;
        this.descriptor = descriptor.descriptor;
        this.id = descriptor.id;
        this.uuid = descriptor.uuid;
        byte[] bArr = descriptor.value;
        if (bArr != null) {
            this.value = (byte[]) bArr.clone();
        }
    }

    public void setValueFromCache() {
        this.value = this.descriptor.getValue();
    }

    public void logValue(String str, byte[] bArr) {
        if (bArr == null) {
            bArr = this.descriptor.getValue();
        }
        String bytesToHex = bArr != null ? ByteUtils.bytesToHex(bArr) : "(null)";
        RxBleLog.v(str + " Descriptor(uuid: " + this.descriptor.getUuid().toString() + ", id: " + this.id + ", value: " + bytesToHex + ")", new Object[0]);
    }
}
