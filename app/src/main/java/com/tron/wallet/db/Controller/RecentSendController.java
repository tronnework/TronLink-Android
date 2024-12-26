package com.tron.wallet.db.Controller;

import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.common.bean.RecentSendBean;
import com.tron.wallet.db.greendao.RecentSendBeanDao;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;
public class RecentSendController extends BaseController<RecentSendBean> {
    public static final int MAX_RECORD = 30;
    private static RecentSendController instance;

    private RecentSendController() {
        super(true);
    }

    public static RecentSendController getInstance() {
        if (instance == null) {
            synchronized (RecentSendController.class) {
                if (instance == null) {
                    instance = new RecentSendController();
                }
            }
        }
        return instance;
    }

    public List<RecentSendBean> queryByAddress(String str) {
        return queryByAddress(0, str);
    }

    public List<RecentSendBean> queryByAddress(int i, String str) {
        return this.beanDao.queryBuilder().where(RecentSendBeanDao.Properties.SendAddress.eq(str), new WhereCondition[0]).where(RecentSendBeanDao.Properties.TransactionType.eq(Integer.valueOf(i)), new WhereCondition[0]).orderDesc(RecentSendBeanDao.Properties.Timestamp).list();
    }

    @Override
    public boolean insertOrReplace(RecentSendBean recentSendBean) {
        boolean insertOrReplace = super.insertOrReplace((RecentSendController) recentSendBean);
        List<RecentSendBean> queryByAddress = queryByAddress(recentSendBean.getTransactionType(), recentSendBean.getSendAddress());
        if (queryByAddress.size() > 30) {
            this.beanDao.deleteInTx(queryByAddress.subList(30, queryByAddress.size()));
        }
        return insertOrReplace;
    }
}
