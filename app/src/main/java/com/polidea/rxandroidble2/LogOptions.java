package com.polidea.rxandroidble2;
public class LogOptions {
    private final Integer logLevel;
    private final Logger logger;
    private final Integer macAddressLogSetting;
    private final Boolean shouldLogAttributeValues;
    private final Boolean shouldLogScannedPeripherals;
    private final Integer uuidLogSetting;

    public interface Logger {
        void log(int i, String str, String str2);
    }

    public Integer getLogLevel() {
        return this.logLevel;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Integer getMacAddressLogSetting() {
        return this.macAddressLogSetting;
    }

    public Boolean getShouldLogAttributeValues() {
        return this.shouldLogAttributeValues;
    }

    public Boolean getShouldLogScannedPeripherals() {
        return this.shouldLogScannedPeripherals;
    }

    public Integer getUuidLogSetting() {
        return this.uuidLogSetting;
    }

    LogOptions(Integer num, Integer num2, Integer num3, Boolean bool, Boolean bool2, Logger logger) {
        this.logLevel = num;
        this.macAddressLogSetting = num2;
        this.uuidLogSetting = num3;
        this.shouldLogAttributeValues = bool;
        this.shouldLogScannedPeripherals = bool2;
        this.logger = logger;
    }

    public String toString() {
        return "LogOptions{logLevel=" + this.logLevel + ", macAddressLogSetting=" + this.macAddressLogSetting + ", uuidLogSetting=" + this.uuidLogSetting + ", shouldLogAttributeValues=" + this.shouldLogAttributeValues + ", shouldLogScannedPeripherals=" + this.shouldLogScannedPeripherals + ", logger=" + this.logger + '}';
    }

    public static class Builder {
        private Integer logLevel;
        private Logger logger;
        private Integer macAddressLogSetting;
        private Boolean shouldLogAttributeValues;
        private Boolean shouldLogScannedPeripherals;
        private Integer uuidsLogSetting;

        public Builder setLogLevel(Integer num) {
            this.logLevel = num;
            return this;
        }

        public Builder setLogger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public Builder setMacAddressLogSetting(Integer num) {
            this.macAddressLogSetting = num;
            return this;
        }

        public Builder setShouldLogAttributeValues(Boolean bool) {
            this.shouldLogAttributeValues = bool;
            return this;
        }

        public Builder setShouldLogScannedPeripherals(Boolean bool) {
            this.shouldLogScannedPeripherals = bool;
            return this;
        }

        public Builder setUuidsLogSetting(Integer num) {
            this.uuidsLogSetting = num;
            return this;
        }

        public LogOptions build() {
            return new LogOptions(this.logLevel, this.macAddressLogSetting, this.uuidsLogSetting, this.shouldLogAttributeValues, this.shouldLogScannedPeripherals, this.logger);
        }
    }
}
