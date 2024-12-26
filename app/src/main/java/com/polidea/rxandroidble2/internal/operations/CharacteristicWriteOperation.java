package com.polidea.rxandroidble2.internal.operations;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Named;
import com.polidea.rxandroidble2.exceptions.BleGattOperationType;
import com.polidea.rxandroidble2.internal.SingleResponseOperation;
import com.polidea.rxandroidble2.internal.connection.RxBleGattCallback;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.ByteAssociationUtil;
import io.reactivex.Single;
public class CharacteristicWriteOperation extends SingleResponseOperation<byte[]> {
    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;
    private final byte[] data;

    public CharacteristicWriteOperation(RxBleGattCallback rxBleGattCallback, BluetoothGatt bluetoothGatt, @Named("operation-timeout") TimeoutConfiguration timeoutConfiguration, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        super(bluetoothGatt, rxBleGattCallback, BleGattOperationType.CHARACTERISTIC_WRITE, timeoutConfiguration);
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.data = bArr;
    }

    @Override
    protected Single<byte[]> getCallback(RxBleGattCallback rxBleGattCallback) {
        return rxBleGattCallback.getOnCharacteristicWrite().filter(ByteAssociationUtil.characteristicUUIDPredicate(this.bluetoothGattCharacteristic.getUuid())).firstOrError().map(ByteAssociationUtil.getBytesFromAssociation());
    }

    @Override
    protected boolean startOperation(BluetoothGatt bluetoothGatt) {
        this.bluetoothGattCharacteristic.setValue(this.data);
        return bluetoothGatt.writeCharacteristic(this.bluetoothGattCharacteristic);
    }

    @Override
    public String toString() {
        return "CharacteristicWriteOperation{" + super.toString() + ", characteristic=" + new LoggerUtil.AttributeLogWrapper(this.bluetoothGattCharacteristic.getUuid(), this.data, true) + '}';
    }
}
