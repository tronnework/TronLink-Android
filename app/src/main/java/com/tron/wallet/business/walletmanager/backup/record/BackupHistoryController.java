package com.tron.wallet.business.walletmanager.backup.record;

import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.db.greendao.BackupRecordBeanDao;
import java.util.List;
public class BackupHistoryController extends BaseController<BackupRecordBean> {
    private static BackupHistoryController instance;

    private BackupHistoryController() {
        super(true);
    }

    public static BackupHistoryController getInstance() {
        if (instance == null) {
            synchronized (BackupHistoryController.class) {
                if (instance == null) {
                    instance = new BackupHistoryController();
                }
            }
        }
        return instance;
    }

    public List<BackupRecordBean> getBrowserHistorySortList() {
        return this.beanDao.queryBuilder().orderDesc(BackupRecordBeanDao.Properties.BackupStamp).list();
    }

    public long getHistoryCount() {
        return this.beanDao.queryBuilder().count();
    }

    public void removeOldestOne() {
        BackupRecordBean oldestSingle = getOldestSingle();
        if (oldestSingle != null) {
            this.beanDao.delete(oldestSingle);
        }
    }

    public BackupRecordBean getOldestSingle() {
        return (BackupRecordBean) this.beanDao.queryBuilder().orderAsc(BackupRecordBeanDao.Properties.BackupStamp).limit(1).unique();
    }

    public BackupRecordBean getLastSingle() {
        return (BackupRecordBean) this.beanDao.queryBuilder().orderDesc(BackupRecordBeanDao.Properties.BackupStamp).limit(1).unique();
    }
}
