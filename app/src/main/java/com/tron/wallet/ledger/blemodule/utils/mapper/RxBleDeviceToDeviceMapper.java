package com.tron.wallet.ledger.blemodule.utils.mapper;

import com.polidea.rxandroidble2.RxBleDevice;
import com.tron.wallet.ledger.blemodule.Device;
public class RxBleDeviceToDeviceMapper {
    public Device map(RxBleDevice rxBleDevice) {
        return new Device(rxBleDevice.getMacAddress(), rxBleDevice.getName(), rxBleDevice);
    }
}
