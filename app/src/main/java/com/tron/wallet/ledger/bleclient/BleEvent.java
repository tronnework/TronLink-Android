package com.tron.wallet.ledger.bleclient;

import com.tron.wallet.ledger.blemodule.ConnectionState;
public class BleEvent {
    public static final String BLE_STATE_CHANGED = "bleStateChanged";
    public static final String CONNECT_STATE_CHANGED = "connectStateChanged";

    public static class ConnectionStateEvent {
        private ConnectionState connectionState;
        private String deviceId;

        public ConnectionState getConnectionState() {
            return this.connectionState;
        }

        public String getDeviceId() {
            return this.deviceId;
        }

        public void setConnectionState(ConnectionState connectionState) {
            this.connectionState = connectionState;
        }

        public void setDeviceId(String str) {
            this.deviceId = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ConnectionStateEvent;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ConnectionStateEvent) {
                ConnectionStateEvent connectionStateEvent = (ConnectionStateEvent) obj;
                if (connectionStateEvent.canEqual(this)) {
                    ConnectionState connectionState = getConnectionState();
                    ConnectionState connectionState2 = connectionStateEvent.getConnectionState();
                    if (connectionState != null ? connectionState.equals(connectionState2) : connectionState2 == null) {
                        String deviceId = getDeviceId();
                        String deviceId2 = connectionStateEvent.getDeviceId();
                        return deviceId != null ? deviceId.equals(deviceId2) : deviceId2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            ConnectionState connectionState = getConnectionState();
            int hashCode = connectionState == null ? 43 : connectionState.hashCode();
            String deviceId = getDeviceId();
            return ((hashCode + 59) * 59) + (deviceId != null ? deviceId.hashCode() : 43);
        }

        public String toString() {
            return "BleEvent.ConnectionStateEvent(connectionState=" + getConnectionState() + ", deviceId=" + getDeviceId() + ")";
        }

        public ConnectionStateEvent(String str, ConnectionState connectionState) {
            this.deviceId = str;
            this.connectionState = connectionState;
        }
    }
}
