package com.tron.wallet.ledger.blemodule.errors;
public class BleError extends Exception {
    public Integer bleCode;
    public String characteristicUUID;
    public String descriptorUUID;
    public String deviceID;
    public BleErrorId errorId;
    public String internalMessage;
    public String reason;
    public String serviceUUID;
    public Integer statusCode;

    public BleError(Throwable th) {
        super(th);
    }

    public BleError(BleErrorId bleErrorId, String str) {
        super(str);
        this.errorId = bleErrorId;
        this.reason = str;
    }

    public BleError(BleErrorId bleErrorId, String str, Integer num) {
        super(str);
        this.errorId = bleErrorId;
        this.reason = str;
        this.bleCode = num;
    }

    public BleError(BleErrorId bleErrorId, String str, Integer num, Integer num2) {
        super(str);
        this.errorId = bleErrorId;
        this.reason = str;
        this.bleCode = num;
        this.statusCode = num2;
    }

    @Override
    public String getMessage() {
        return "Error code: " + this.errorId + ", android code: " + this.bleCode + ", response code: " + this.statusCode + ", reason" + this.reason + ", deviceId" + this.deviceID + ", serviceUuid" + this.serviceUUID + ", characteristicUuid" + this.characteristicUUID + ", descriptorUuid" + this.descriptorUUID + ", internalMessage" + this.internalMessage;
    }
}
