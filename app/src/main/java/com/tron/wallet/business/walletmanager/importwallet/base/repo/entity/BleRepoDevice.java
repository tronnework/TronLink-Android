package com.tron.wallet.business.walletmanager.importwallet.base.repo.entity;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.ledger.blemodule.Device;
import java.io.Serializable;
public class BleRepoDevice implements Serializable {
    private static final long serialVersionUID = 8965521860026607258L;
    private Long id;
    private String mac;
    private String name;

    public Long getId() {
        return this.id;
    }

    public String getMac() {
        return this.mac;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BleRepoDevice;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BleRepoDevice) {
            BleRepoDevice bleRepoDevice = (BleRepoDevice) obj;
            if (bleRepoDevice.canEqual(this)) {
                Long id = getId();
                Long id2 = bleRepoDevice.getId();
                if (id != null ? id.equals(id2) : id2 == null) {
                    String mac = getMac();
                    String mac2 = bleRepoDevice.getMac();
                    if (mac != null ? mac.equals(mac2) : mac2 == null) {
                        String name = getName();
                        String name2 = bleRepoDevice.getName();
                        return name != null ? name.equals(name2) : name2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Long id = getId();
        int hashCode = id == null ? 43 : id.hashCode();
        String mac = getMac();
        int hashCode2 = ((hashCode + 59) * 59) + (mac == null ? 43 : mac.hashCode());
        String name = getName();
        return (hashCode2 * 59) + (name != null ? name.hashCode() : 43);
    }

    public String toString() {
        return "BleRepoDevice(id=" + getId() + ", mac=" + getMac() + ", name=" + getName() + ")";
    }

    public BleRepoDevice(Long l, String str, String str2) {
        this.id = l;
        this.mac = str;
        this.name = str2;
    }

    public BleRepoDevice() {
    }

    public static BleRepoDevice fromDevice(Device device) {
        BleRepoDevice bleRepoDevice = new BleRepoDevice();
        bleRepoDevice.setMac(device.getId());
        bleRepoDevice.setName(device.getName());
        LogUtils.w("BleRepoDevice", String.format("fromDevice: create new: \n Device mac: %s, name: %s", device.getId(), device.getName()));
        return bleRepoDevice;
    }
}
