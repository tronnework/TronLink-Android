package com.tron.wallet.ledger.blemodule;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.tron.wallet.ledger.blemodule.utils.ByteUtils;
import com.tron.wallet.ledger.blemodule.utils.Constants;
import com.tron.wallet.ledger.blemodule.utils.IdGenerator;
import com.tron.wallet.ledger.blemodule.utils.IdGeneratorKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class Characteristic {
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
    private final String deviceID;
    final BluetoothGattCharacteristic gattCharacteristic;
    private final int id;
    private final int serviceID;
    private final UUID serviceUUID;
    private byte[] value;

    public String getDeviceId() {
        return this.deviceID;
    }

    public int getId() {
        return this.id;
    }

    public int getServiceID() {
        return this.serviceID;
    }

    public UUID getServiceUUID() {
        return this.serviceUUID;
    }

    public byte[] getValue() {
        return this.value;
    }

    public void setValue(byte[] bArr) {
        this.value = bArr;
    }

    public Characteristic(Service service, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String deviceID = service.getDeviceID();
        this.deviceID = deviceID;
        this.serviceUUID = service.getUuid();
        this.serviceID = service.getId();
        this.gattCharacteristic = bluetoothGattCharacteristic;
        this.id = IdGenerator.getIdForKey(new IdGeneratorKey(deviceID, bluetoothGattCharacteristic.getUuid(), bluetoothGattCharacteristic.getInstanceId()));
    }

    public Characteristic(int i, Service service, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.id = i;
        this.deviceID = service.getDeviceID();
        this.serviceUUID = service.getUuid();
        this.serviceID = service.getId();
        this.gattCharacteristic = bluetoothGattCharacteristic;
    }

    public Characteristic(Characteristic characteristic) {
        this.id = characteristic.id;
        this.serviceID = characteristic.serviceID;
        this.serviceUUID = characteristic.serviceUUID;
        this.deviceID = characteristic.deviceID;
        byte[] bArr = characteristic.value;
        if (bArr != null) {
            this.value = (byte[]) bArr.clone();
        }
        this.gattCharacteristic = characteristic.gattCharacteristic;
    }

    public UUID getUuid() {
        return this.gattCharacteristic.getUuid();
    }

    public int getInstanceId() {
        return this.gattCharacteristic.getInstanceId();
    }

    public BluetoothGattDescriptor getGattDescriptor(UUID uuid) {
        return this.gattCharacteristic.getDescriptor(uuid);
    }

    public void setWriteType(int i) {
        this.gattCharacteristic.setWriteType(i);
    }

    public boolean isReadable() {
        return (this.gattCharacteristic.getProperties() & 2) != 0;
    }

    public boolean isWritableWithResponse() {
        return (this.gattCharacteristic.getProperties() & 8) != 0;
    }

    public boolean isWritableWithoutResponse() {
        return (this.gattCharacteristic.getProperties() & 4) != 0;
    }

    public boolean isNotifiable() {
        return (this.gattCharacteristic.getProperties() & 16) != 0;
    }

    public List<Descriptor> getDescriptors() {
        ArrayList arrayList = new ArrayList(this.gattCharacteristic.getDescriptors().size());
        for (BluetoothGattDescriptor bluetoothGattDescriptor : this.gattCharacteristic.getDescriptors()) {
            arrayList.add(new Descriptor(this, bluetoothGattDescriptor));
        }
        return arrayList;
    }

    public boolean isNotifying() {
        byte[] value;
        BluetoothGattDescriptor descriptor = this.gattCharacteristic.getDescriptor(Constants.CLIENT_CHARACTERISTIC_CONFIG_UUID);
        return (descriptor == null || (value = descriptor.getValue()) == null || (value[0] & 1) == 0) ? false : true;
    }

    public boolean isIndicatable() {
        return (this.gattCharacteristic.getProperties() & 32) != 0;
    }

    public Descriptor getDescriptorByUUID(UUID uuid) {
        BluetoothGattDescriptor descriptor = this.gattCharacteristic.getDescriptor(uuid);
        if (descriptor == null) {
            return null;
        }
        return new Descriptor(this, descriptor);
    }

    public void logValue(String str, byte[] bArr) {
        if (bArr == null) {
            bArr = this.gattCharacteristic.getValue();
        }
        String bytesToHex = bArr != null ? ByteUtils.bytesToHex(bArr) : "(null)";
        RxBleLog.v(str + " Characteristic(uuid: " + this.gattCharacteristic.getUuid().toString() + ", id: " + this.id + ", value: " + bytesToHex + ")", new Object[0]);
    }
}
