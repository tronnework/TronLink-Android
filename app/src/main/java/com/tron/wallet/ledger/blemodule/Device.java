package com.tron.wallet.ledger.blemodule;

import com.polidea.rxandroidble2.RxBleDevice;
import java.util.List;
import java.util.UUID;
public class Device {
    private RxBleDevice bleDevice;
    private String id;
    private Integer mtu;
    private String name;
    private Integer rssi;
    private List<Service> services;

    public RxBleDevice getBleDevice() {
        return this.bleDevice;
    }

    public String getId() {
        return this.id;
    }

    public Integer getMtu() {
        return this.mtu;
    }

    public String getName() {
        return this.name;
    }

    public Integer getRssi() {
        return this.rssi;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void setBleDevice(RxBleDevice rxBleDevice) {
        this.bleDevice = rxBleDevice;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMtu(Integer num) {
        this.mtu = num;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRssi(Integer num) {
        this.rssi = num;
    }

    public void setServices(List<Service> list) {
        this.services = list;
    }

    public Device(String str, String str2, RxBleDevice rxBleDevice) {
        this.id = str;
        this.name = str2;
        this.bleDevice = rxBleDevice;
    }

    public Service getServiceByUUID(UUID uuid) {
        List<Service> list = this.services;
        if (list == null) {
            return null;
        }
        for (Service service : list) {
            if (uuid.equals(service.getUuid())) {
                return service;
            }
        }
        return null;
    }
}
