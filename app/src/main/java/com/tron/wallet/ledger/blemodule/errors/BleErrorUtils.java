package com.tron.wallet.ledger.blemodule.errors;
public class BleErrorUtils {
    public static BleError cancelled() {
        return new BleError(BleErrorId.OperationCancelled, null, null);
    }

    public static BleError invalidIdentifiers(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(", ");
        }
        BleError bleError = new BleError(BleErrorId.InvalidIdentifiers, null, null);
        bleError.internalMessage = sb.toString();
        return bleError;
    }

    public static BleError deviceNotFound(String str) {
        BleError bleError = new BleError(BleErrorId.DeviceNotFound, null, null);
        bleError.deviceID = str;
        return bleError;
    }

    public static BleError deviceNotConnected(String str) {
        BleError bleError = new BleError(BleErrorId.DeviceNotConnected, null, null);
        bleError.deviceID = str;
        return bleError;
    }

    public static BleError characteristicNotFound(String str) {
        BleError bleError = new BleError(BleErrorId.CharacteristicNotFound, null, null);
        bleError.characteristicUUID = str;
        return bleError;
    }

    public static BleError invalidWriteDataForCharacteristic(String str, String str2) {
        BleError bleError = new BleError(BleErrorId.CharacteristicInvalidDataFormat, null, null);
        bleError.characteristicUUID = str2;
        bleError.internalMessage = str;
        return bleError;
    }

    public static BleError descriptorNotFound(String str) {
        BleError bleError = new BleError(BleErrorId.DescriptorNotFound, null, null);
        bleError.descriptorUUID = str;
        return bleError;
    }

    public static BleError invalidWriteDataForDescriptor(String str, String str2) {
        BleError bleError = new BleError(BleErrorId.DescriptorInvalidDataFormat, null, null);
        bleError.descriptorUUID = str2;
        bleError.internalMessage = str;
        return bleError;
    }

    public static BleError descriptorWriteNotAllowed(String str) {
        BleError bleError = new BleError(BleErrorId.DescriptorWriteNotAllowed, null, null);
        bleError.descriptorUUID = str;
        return bleError;
    }

    public static BleError serviceNotFound(String str) {
        BleError bleError = new BleError(BleErrorId.ServiceNotFound, null, null);
        bleError.serviceUUID = str;
        return bleError;
    }

    public static BleError cannotMonitorCharacteristic(String str, String str2, String str3, String str4) {
        BleError bleError = new BleError(BleErrorId.CharacteristicNotifyChangeFailed, str, null);
        bleError.deviceID = str2;
        bleError.serviceUUID = str3;
        bleError.characteristicUUID = str4;
        return bleError;
    }

    public static BleError deviceServicesNotDiscovered(String str) {
        BleError bleError = new BleError(BleErrorId.ServicesNotDiscovered, null, null);
        bleError.deviceID = str;
        return bleError;
    }
}
