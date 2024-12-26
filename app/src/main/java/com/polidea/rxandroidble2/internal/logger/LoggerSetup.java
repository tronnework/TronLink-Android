package com.polidea.rxandroidble2.internal.logger;

import com.polidea.rxandroidble2.LogOptions;
public class LoggerSetup {
    public final int logLevel;
    public final LogOptions.Logger logger;
    public final int macAddressLogSetting;
    public final boolean shouldLogAttributeValues;
    public final boolean shouldLogScannedPeripherals;
    public final int uuidLogSetting;

    public LoggerSetup(int i, int i2, int i3, boolean z, boolean z2, LogOptions.Logger logger) {
        this.logLevel = i;
        this.macAddressLogSetting = i2;
        this.uuidLogSetting = i3;
        this.shouldLogAttributeValues = z;
        this.shouldLogScannedPeripherals = z2;
        this.logger = logger;
    }

    public LoggerSetup merge(LogOptions logOptions) {
        return new LoggerSetup(logOptions.getLogLevel() != null ? logOptions.getLogLevel().intValue() : this.logLevel, logOptions.getMacAddressLogSetting() != null ? logOptions.getMacAddressLogSetting().intValue() : this.macAddressLogSetting, logOptions.getUuidLogSetting() != null ? logOptions.getUuidLogSetting().intValue() : this.uuidLogSetting, logOptions.getShouldLogAttributeValues() != null ? logOptions.getShouldLogAttributeValues().booleanValue() : this.shouldLogAttributeValues, logOptions.getShouldLogScannedPeripherals() != null ? logOptions.getShouldLogScannedPeripherals().booleanValue() : this.shouldLogScannedPeripherals, logOptions.getLogger() != null ? logOptions.getLogger() : this.logger);
    }

    public String toString() {
        return "LoggerSetup{logLevel=" + this.logLevel + ", macAddressLogSetting=" + this.macAddressLogSetting + ", uuidLogSetting=" + this.uuidLogSetting + ", shouldLogAttributeValues=" + this.shouldLogAttributeValues + ", shouldLogScannedPeripherals=" + this.shouldLogScannedPeripherals + ", logger=" + this.logger + '}';
    }
}
