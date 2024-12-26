package com.tron.wallet.business.message.db;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.db.greendao.TransactionMessageDao;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.query.WhereCondition;
public class TransactionMessageDbController extends BaseController<TransactionMessage> {
    public static final int DEFAULT_CACHE_COUNT = 500;
    public static final int MAX_CACHE_COUNT = 600;
    private DatabaseStatement updateStatement;

    private TransactionMessageDbController() {
        super(true);
    }

    public static TransactionMessageDbController get() {
        return Nested.controller;
    }

    @Override
    public boolean insertMultiObject(List<TransactionMessage> list) {
        boolean z;
        try {
            this.beanDao.insertOrReplaceInTx(list);
            z = true;
        } catch (Exception e) {
            LogUtils.e(e);
            z = false;
        }
        getAllInRange();
        return z;
    }

    @Override
    public boolean insertOrReplace(TransactionMessage transactionMessage) {
        boolean insertOrReplace = super.insertOrReplace((TransactionMessageDbController) transactionMessage);
        getAllInRange();
        return insertOrReplace;
    }

    @Override
    public List<TransactionMessage> queryAll() {
        return getAllInRange();
    }

    public List<TransactionMessage> query(int i, int i2) {
        if (i < 0 || i > 500) {
            return new ArrayList();
        }
        try {
            return this.beanDao.queryBuilder().limit(i2).offset(i).orderDesc(TransactionMessageDao.Properties.TransferTime).list();
        } catch (Exception e) {
            LogUtils.e(e);
            return new ArrayList();
        }
    }

    public long queryUnread() {
        return this.beanDao.queryBuilder().where(TransactionMessageDao.Properties.State.eq(0), new WhereCondition[0]).buildCount().count();
    }

    public void remove(List<TransactionMessage> list) {
        this.beanDao.deleteInTx(list);
    }

    private List<TransactionMessage> getAllInRange() {
        List<TransactionMessage> list = this.beanDao.queryBuilder().orderDesc(TransactionMessageDao.Properties.TransferTime).list();
        if (list == null) {
            return new ArrayList();
        }
        try {
            if (list.size() < 600) {
                return list.size() >= 500 ? list.subList(0, 500) : list;
            }
            List<TransactionMessage> subList = list.subList(500, list.size());
            super.deleteMultiObject(subList);
            list.removeAll(subList);
            return list;
        } catch (Exception e) {
            randomReportSentry(e);
            return new ArrayList();
        }
    }

    public void setAllRead() {
        Database database = this.daoSession.getDatabase();
        if (this.updateStatement == null) {
            this.updateStatement = database.compileStatement(SqlUtils.createSqlUpdate(this.beanDao.getTablename(), new String[]{TransactionMessageDao.Properties.State.columnName}, new String[]{TransactionMessageDao.Properties.State.columnName}));
        }
        this.updateStatement.bindLong(1, 1L);
        this.updateStatement.bindLong(2, 0L);
        this.updateStatement.execute();
        this.beanDao.detachAll();
    }

    private static class Nested {
        static TransactionMessageDbController controller = new TransactionMessageDbController();

        private Nested() {
        }
    }
}
