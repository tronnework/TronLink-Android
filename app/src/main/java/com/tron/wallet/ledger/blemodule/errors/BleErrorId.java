package com.tron.wallet.ledger.blemodule.errors;

import com.facebook.stetho.server.http.HttpStatus;
import com.tron.wallet.business.transfer.TransactionDetailActivity;
public enum BleErrorId {
    BleErrorCode(-1),
    BleTooLittleData(-2),
    BleIsBusy(-3),
    DataIsNull(-4),
    DataLengthTooBig(-5),
    BleDateParseError(-6),
    BleTooMuchData(-7),
    InvalidSequence(-8),
    InvalidTag(-9),
    InvalidDataNull(-10),
    BleChracteristicNotFound(-11),
    BleChracteristicInvalid(-12),
    VarintIsTooBig(-13),
    ReconnectAfterFirstPaired(-14),
    CannotOpenApp(-15),
    UnknownError(0),
    BluetoothManagerDestroyed(1),
    OperationCancelled(2),
    OperationTimedOut(3),
    OperationStartFailed(4),
    InvalidIdentifiers(5),
    BluetoothUnsupported(100),
    BluetoothUnauthorized(101),
    BluetoothPoweredOff(102),
    BluetoothInUnknownState(103),
    BluetoothResetting(104),
    BluetoothStateChangeFailed(105),
    DeviceConnectionFailed(200),
    DeviceDisconnected(201),
    DeviceRSSIReadFailed(202),
    DeviceAlreadyConnected(203),
    DeviceNotFound(204),
    DeviceNotConnected(205),
    DeviceMTUChangeFailed(206),
    ServicesDiscoveryFailed(300),
    IncludedServicesDiscoveryFailed(301),
    ServiceNotFound(302),
    ServicesNotDiscovered(303),
    CharacteristicsDiscoveryFailed(400),
    CharacteristicWriteFailed(401),
    CharacteristicReadFailed(402),
    CharacteristicNotifyChangeFailed(403),
    CharacteristicNotFound(HttpStatus.HTTP_NOT_FOUND),
    CharacteristicsNotDiscovered(405),
    CharacteristicInvalidDataFormat(406),
    DescriptorsDiscoveryFailed(500),
    DescriptorWriteFailed(501),
    DescriptorReadFailed(TransactionDetailActivity.TriggerContractType.APPROVAL),
    DescriptorNotFound(503),
    DescriptorsNotDiscovered(504),
    DescriptorInvalidDataFormat(505),
    DescriptorWriteNotAllowed(506),
    ScanStartFailed(600),
    LocationServicesDisabled(601);
    
    public final int code;

    BleErrorId(int i) {
        this.code = i;
    }
}
