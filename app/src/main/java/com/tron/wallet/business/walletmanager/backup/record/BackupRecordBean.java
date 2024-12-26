package com.tron.wallet.business.walletmanager.backup.record;
public class BackupRecordBean {
    public int backupRecordTYpe;
    public long backupStamp;
    public boolean hasShow;
    public Long id;
    public String walletAddress;
    public String walletName;

    public int getBackupRecordTYpe() {
        return this.backupRecordTYpe;
    }

    public long getBackupStamp() {
        return this.backupStamp;
    }

    public boolean getHasShow() {
        return this.hasShow;
    }

    public Long getId() {
        return this.id;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public String getWalletName() {
        return this.walletName;
    }

    public void setBackupRecordTYpe(int i) {
        this.backupRecordTYpe = i;
    }

    public void setBackupStamp(long j) {
        this.backupStamp = j;
    }

    public void setHasShow(boolean z) {
        this.hasShow = z;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public void setWalletName(String str) {
        this.walletName = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof BackupRecordBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BackupRecordBean) {
            BackupRecordBean backupRecordBean = (BackupRecordBean) obj;
            if (backupRecordBean.canEqual(this) && getBackupRecordTYpe() == backupRecordBean.getBackupRecordTYpe() && getBackupStamp() == backupRecordBean.getBackupStamp() && getHasShow() == backupRecordBean.getHasShow()) {
                Long id = getId();
                Long id2 = backupRecordBean.getId();
                if (id != null ? id.equals(id2) : id2 == null) {
                    String walletName = getWalletName();
                    String walletName2 = backupRecordBean.getWalletName();
                    if (walletName != null ? walletName.equals(walletName2) : walletName2 == null) {
                        String walletAddress = getWalletAddress();
                        String walletAddress2 = backupRecordBean.getWalletAddress();
                        return walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null;
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
        long backupStamp = getBackupStamp();
        int backupRecordTYpe = ((((getBackupRecordTYpe() + 59) * 59) + ((int) (backupStamp ^ (backupStamp >>> 32)))) * 59) + (getHasShow() ? 79 : 97);
        Long id = getId();
        int hashCode = (backupRecordTYpe * 59) + (id == null ? 43 : id.hashCode());
        String walletName = getWalletName();
        int hashCode2 = (hashCode * 59) + (walletName == null ? 43 : walletName.hashCode());
        String walletAddress = getWalletAddress();
        return (hashCode2 * 59) + (walletAddress != null ? walletAddress.hashCode() : 43);
    }

    public String toString() {
        return "BackupRecordBean(id=" + getId() + ", backupRecordTYpe=" + getBackupRecordTYpe() + ", walletName=" + getWalletName() + ", walletAddress=" + getWalletAddress() + ", backupStamp=" + getBackupStamp() + ", hasShow=" + getHasShow() + ")";
    }

    public BackupRecordBean(Long l, int i, String str, String str2, long j, boolean z) {
        this.id = l;
        this.backupRecordTYpe = i;
        this.walletName = str;
        this.walletAddress = str2;
        this.backupStamp = j;
        this.hasShow = z;
    }

    public BackupRecordBean() {
    }
}
