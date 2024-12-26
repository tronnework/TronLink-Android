package com.tron.wallet.db.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.db.greendao.DaoMaster;
import com.tron.wallet.db.greendao.DaoSession;
import com.tron.wallet.db.greendao.TRXAccountBalanceBeanDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class TRXAccountBalanceController {
    public static final String TAG = "AccountBalanceController";
    private static volatile TRXAccountBalanceController mDbController;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper mHelper;
    private TRXAccountBalanceBeanDao trxAccountBalanceBeanDao;

    private TRXAccountBalanceController(Context context) {
        Context context2 = AppContextUtil.getContext();
        this.mHelper = new DaoMaster.DevOpenHelper(context2, IRequest.ENVIRONMENT.toString() + "trx_balance.db", null);
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        this.mDaoMaster = daoMaster;
        DaoSession newSession = daoMaster.newSession();
        this.mDaoSession = newSession;
        this.trxAccountBalanceBeanDao = newSession.getTRXAccountBalanceBeanDao();
    }

    public static TRXAccountBalanceController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (AddressController.class) {
                if (mDbController == null) {
                    mDbController = new TRXAccountBalanceController(context);
                }
            }
        }
        return mDbController;
    }

    private SQLiteDatabase getWritableDatabase() {
        if (this.mHelper == null) {
            Context context = AppContextUtil.getContext();
            this.mHelper = new DaoMaster.DevOpenHelper(context, IRequest.ENVIRONMENT.toString() + "trx_balance.db", null);
        }
        return this.mHelper.getWritableDatabase();
    }

    public void insertObject(final String str, final double d, final double d2) {
        LogUtils.d("AccountBalance", "insertObject:  ");
        final String currentChainId = SpAPI.THIS.getCurrentChainId();
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$insertObject$0(str, d, d2, currentChainId);
            }
        });
    }

    public void lambda$insertObject$0(String str, double d, double d2, String str2) {
        try {
            TRXAccountBalanceBean queryByAddress = queryByAddress(str);
            TRXAccountBalanceBean tRXAccountBalanceBean = new TRXAccountBalanceBean();
            tRXAccountBalanceBean.setAddress(str);
            tRXAccountBalanceBean.setTotalTrx(d);
            tRXAccountBalanceBean.setTrxNum(d2);
            tRXAccountBalanceBean.setChainId(str2);
            if (queryByAddress != null) {
                tRXAccountBalanceBean.setAccountType(queryByAddress.getAccountType());
            }
            tRXAccountBalanceBean.setNetType(SpAPI.THIS.getCurrentChainName());
            this.trxAccountBalanceBeanDao.insertOrReplace(tRXAccountBalanceBean);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public void insertMultObject(final List<AccountBalanceOutput.DataBean.BalanceListBean> list) {
        LogUtils.d("AccountBalance", "insertMultObject:  ");
        if (list == null || list.size() == 0) {
            return;
        }
        final String currentChainId = SpAPI.THIS.getCurrentChainId();
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                lambda$insertMultObject$1(list, currentChainId);
            }
        });
    }

    public void lambda$insertMultObject$1(List list, String str) {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AccountBalanceOutput.DataBean.BalanceListBean balanceListBean = (AccountBalanceOutput.DataBean.BalanceListBean) it.next();
                if (!BigDecimalUtils.equalsZero((Number) Double.valueOf(balanceListBean.getBalance()))) {
                    TRXAccountBalanceBean tRXAccountBalanceBean = new TRXAccountBalanceBean();
                    tRXAccountBalanceBean.setAddress(balanceListBean.getAddress());
                    tRXAccountBalanceBean.setTotalTrx(balanceListBean.getBalance());
                    tRXAccountBalanceBean.setChainId(str);
                    tRXAccountBalanceBean.setNetType(SpAPI.THIS.getCurrentChainName());
                    tRXAccountBalanceBean.setAccountType(balanceListBean.getAccountType());
                    arrayList.add(tRXAccountBalanceBean);
                }
            }
            this.trxAccountBalanceBeanDao.insertOrReplaceInTx(arrayList);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public List<TRXAccountBalanceBean> queryAll() {
        LogUtils.d("AccountBalance", "queryBalanceAll:  ");
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        try {
            List<TRXAccountBalanceBean> loadAll = this.trxAccountBalanceBeanDao.loadAll();
            if (loadAll != null && loadAll.size() > 0) {
                String currentChainName = SpAPI.THIS.getCurrentChainName();
                for (int i = 0; i < loadAll.size(); i++) {
                    if (loadAll.get(i).getNetType().equals(currentChainName)) {
                        arrayList.add(loadAll.get(i));
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            LogUtils.e(e);
            return arrayList;
        }
    }

    public TRXAccountBalanceBean queryByAddress(String str) {
        try {
            return this.trxAccountBalanceBeanDao.queryBuilder().where(TRXAccountBalanceBeanDao.Properties.Address.eq(str), TRXAccountBalanceBeanDao.Properties.NetType.eq(SpAPI.THIS.getCurrentChainName())).build().unique();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }
}
