package com.tron.wallet.business.tabmy.myhome.settings.bean;

import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import j$.util.Objects;
public class NodeBean {
    public static final int FullNode = 0;
    public static final int SolidityNoe = 1;
    private String address;
    private String ip;
    private boolean isCustomed;
    private boolean isSelected;
    private int latency;
    private int port;
    private int status = 0;
    private int type;

    public int getLatency() {
        return this.latency;
    }

    public int getPort() {
        return this.port;
    }

    public int getStatus() {
        return this.status;
    }

    public int getType() {
        return this.type;
    }

    public boolean isCustomed() {
        return this.isCustomed;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setCustomed(boolean z) {
        this.isCustomed = z;
    }

    public void setLatency(int i) {
        this.latency = i;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public NodeBean() {
    }

    public NodeBean(String str) {
        this.address = str;
        String[] split = str.split(":");
        if (split == null || split.length == 0) {
            LogUtils.e("Exception", "NodeBean-split.lengh ==0");
            return;
        }
        this.ip = split[0];
        this.port = split.length > 1 ? Integer.parseInt(split[1]) : 50051;
    }

    public String getAddress() {
        if (TextUtils.isEmpty(this.address)) {
            this.address = this.ip + ":" + this.port;
        }
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
        if (str.contains(":")) {
            String[] split = str.split(":");
            this.ip = split[0];
            this.port = Integer.parseInt(split[1]);
        }
    }

    public String getIp() {
        if (TextUtils.isEmpty(this.ip) && !TextUtils.isEmpty(this.address) && this.address.contains(":")) {
            String[] split = this.address.split(":");
            this.ip = split[0];
            this.port = Integer.parseInt(split[1]);
        }
        return this.ip;
    }

    public void setIp(String str) {
        if (!str.contains(":")) {
            this.ip = str;
            return;
        }
        String[] split = str.split(":");
        this.ip = split[0];
        this.port = Integer.parseInt(split[1]);
        this.address = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.address.equals(((NodeBean) obj).address);
    }

    public int hashCode() {
        return Objects.hash(this.address);
    }
}
