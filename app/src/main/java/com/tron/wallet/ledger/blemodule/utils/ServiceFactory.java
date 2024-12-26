package com.tron.wallet.ledger.blemodule.utils;

import android.bluetooth.BluetoothGattService;
import com.tron.wallet.ledger.blemodule.Service;
public class ServiceFactory {
    public Service create(String str, BluetoothGattService bluetoothGattService) {
        return new Service(IdGenerator.getIdForKey(new IdGeneratorKey(str, bluetoothGattService.getUuid(), bluetoothGattService.getInstanceId())), str, bluetoothGattService);
    }
}
