package com.tron.wallet.business.ledger.manage;

import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import java.io.Serializable;
public class EquipmentBean implements Serializable {
    private static final long serialVersionUID = 4371108678472351921L;
    BleRepoDevice device;
    private boolean isConnected;
    private boolean isConnecting;
    private boolean isExpland;

    public BleRepoDevice getDevice() {
        return this.device;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public boolean isConnecting() {
        return this.isConnecting;
    }

    public boolean isExpland() {
        return this.isExpland;
    }

    public void setConnected(boolean z) {
        this.isConnected = z;
    }

    public void setConnecting(boolean z) {
        this.isConnecting = z;
    }

    public void setDevice(BleRepoDevice bleRepoDevice) {
        this.device = bleRepoDevice;
    }

    public void setExpland(boolean z) {
        this.isExpland = z;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof EquipmentBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EquipmentBean) {
            EquipmentBean equipmentBean = (EquipmentBean) obj;
            if (equipmentBean.canEqual(this) && isConnected() == equipmentBean.isConnected() && isExpland() == equipmentBean.isExpland() && isConnecting() == equipmentBean.isConnecting()) {
                BleRepoDevice device = getDevice();
                BleRepoDevice device2 = equipmentBean.getDevice();
                return device != null ? device.equals(device2) : device2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int i = (((((isConnected() ? 79 : 97) + 59) * 59) + (isExpland() ? 79 : 97)) * 59) + (isConnecting() ? 79 : 97);
        BleRepoDevice device = getDevice();
        return (i * 59) + (device == null ? 43 : device.hashCode());
    }

    public String toString() {
        return "EquipmentBean(device=" + getDevice() + ", isConnected=" + isConnected() + ", isExpland=" + isExpland() + ", isConnecting=" + isConnecting() + ")";
    }

    public EquipmentBean(BleRepoDevice bleRepoDevice) {
        this.device = bleRepoDevice;
    }
}
