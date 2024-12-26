package com.polidea.rxandroidble2.exceptions;

import java.util.UUID;
public class BleConflictingNotificationAlreadySetException extends BleException {
    private final boolean alreadySetIsIndication;
    private final UUID characteristicUuid;

    public UUID getCharacteristicUuid() {
        return this.characteristicUuid;
    }

    public boolean indicationAlreadySet() {
        return this.alreadySetIsIndication;
    }

    public boolean notificationAlreadySet() {
        return !this.alreadySetIsIndication;
    }

    public BleConflictingNotificationAlreadySetException(java.util.UUID r3, boolean r4) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.polidea.rxandroidble2.exceptions.BleConflictingNotificationAlreadySetException.<init>(java.util.UUID, boolean):void");
    }
}
