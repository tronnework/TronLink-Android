package com.tron.wallet.business.walletmanager.backup.record;

import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import java.util.List;
import org.tron.walletserver.Wallet;
public class BackupHistoryManager {
    public static final int MAX_COUNT = 200;
    private BackupHistoryController backupHistoryController;
    private long historyCount;

    public static class Nested {
        private static BackupHistoryManager manager = new BackupHistoryManager();
    }

    private BackupHistoryManager() {
        BackupHistoryController backupHistoryController = BackupHistoryController.getInstance();
        this.backupHistoryController = backupHistoryController;
        this.historyCount = backupHistoryController.getHistoryCount();
    }

    public List<BackupRecordBean> queryHistory() {
        return this.backupHistoryController.getBrowserHistorySortList();
    }

    public static BackupHistoryManager getInstance() {
        return Nested.manager;
    }

    public boolean addNewBackupRecord(int i, boolean z) {
        return addNewBackupRecord(i, z, false);
    }

    public boolean addNewBackupRecord(int i, boolean z, boolean z2) {
        String str;
        String str2;
        if (z2) {
            return false;
        }
        long historyCount = this.backupHistoryController.getHistoryCount();
        this.historyCount = historyCount;
        if (historyCount >= 200) {
            this.backupHistoryController.removeOldestOne();
        }
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            str = selectedWallet.getAddress();
            str2 = selectedWallet.getWalletName();
        } else {
            str = "";
            str2 = "";
        }
        BackupRecordBean backupRecordBean = new BackupRecordBean();
        backupRecordBean.setHasShow(false);
        backupRecordBean.setBackupRecordTYpe(i);
        backupRecordBean.setBackupStamp(System.currentTimeMillis());
        backupRecordBean.setWalletAddress(str);
        backupRecordBean.setWalletName(str2);
        SpAPI.THIS.setBackupLastOne(backupRecordBean);
        return this.backupHistoryController.insertOrReplace(backupRecordBean);
    }

    public void updateShowState(BackupRecordBean backupRecordBean) {
        this.backupHistoryController.deleteEntity(backupRecordBean);
        backupRecordBean.setHasShow(true);
        this.backupHistoryController.insertOrReplace(backupRecordBean);
    }
}
