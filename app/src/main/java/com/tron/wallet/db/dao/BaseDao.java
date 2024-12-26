package com.tron.wallet.db.dao;

import android.content.Context;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.db.greendao.DaoSession;
import java.util.List;
import java.util.Random;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;
public class BaseDao<T> {
    public static final boolean DEBUG = false;
    private final String TAG = "greenDAO";
    public DaoManager daoManager;
    public DaoSession daoSession;
    public DicDaoManager dicDaoManager;
    public LightDaoManager lightDaoManager;

    public void handleITron(Context context) {
        DaoManager daoManager = DaoManager.getInstance(context);
        this.daoManager = daoManager;
        this.daoSession = daoManager.getDaoSession();
        this.daoManager.setDebug(false);
    }

    public void handleDic() {
        DicDaoManager dicDaoManager = DicDaoManager.getInstance();
        this.dicDaoManager = dicDaoManager;
        this.daoSession = dicDaoManager.getDaoSession();
        this.dicDaoManager.setDebug(false);
    }

    public void handleLight() {
        LightDaoManager lightDaoManager = LightDaoManager.getInstance();
        this.lightDaoManager = lightDaoManager;
        this.daoSession = lightDaoManager.getDaoSession();
        this.lightDaoManager.setDebug(false);
    }

    public boolean insertObject(T t) {
        try {
            return this.daoSession.insertOrReplace(t) != -1;
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return false;
        }
    }

    public boolean insertMultObject(List<T> list) {
        return insertMultObject(list, false);
    }

    public boolean insertMultObject(final List<T> list, final boolean z) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        try {
            this.daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    if (z) {
                        daoSession.deleteAll(TokenBean.class);
                    }
                    for (Object obj : list) {
                        if (obj instanceof TokenBean) {
                            if (((TokenBean) obj).type != 3) {
                                daoSession.insert(obj);
                            }
                        } else {
                            daoSession.insert(obj);
                        }
                    }
                }
            });
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.d("greenDAO", "fail,exception:" + e.toString());
            LogUtils.e("greenDAO", e.toString());
            return false;
        }
    }

    public void updateObject(T t) {
        if (t == null) {
            return;
        }
        try {
            this.daoSession.update(t);
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
        }
    }

    public void updateMultObject(List<T> list, Class cls) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            this.daoSession.getDao(cls).updateInTx(list);
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
        }
    }

    public void insertMultiObject(final List<T> list, Class cls) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            this.daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Object obj : list) {
                        if (obj instanceof TokenBean) {
                            if (((TokenBean) obj).type != 3) {
                                daoSession.insertOrReplace(obj);
                            }
                        } else {
                            daoSession.insertOrReplace(obj);
                        }
                    }
                }
            });
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
        }
    }

    public boolean deleteAll(Class cls) {
        try {
            this.daoSession.deleteAll(cls);
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return false;
        }
    }

    public void deleteObject(T t) {
        try {
            this.daoSession.delete(t);
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
        }
    }

    public boolean deleteTokenBeansByAddressAndIsMainChain(String str, String str2) {
        try {
            Database database = this.daoSession.getDatabase();
            database.execSQL("DELETE FROM TOKEN_BEAN WHERE ADDRESS = \"" + str + "\" AND IN_MAIN_CHAIN = \"" + str2 + " \"");
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return false;
        }
    }

    public boolean deleteObjectWithWhere(String str, String str2, String... strArr) {
        try {
            Database database = this.daoSession.getDatabase();
            database.execSQL("DELETE FROM " + str + " " + str2, strArr);
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return false;
        }
    }

    public boolean deleteMultObject(List<T> list, Class cls) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        try {
            this.daoSession.getDao(cls).deleteInTx(list);
            return true;
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return false;
        }
    }

    public String getTableName(Class cls) {
        return this.daoSession.getDao(cls).getTablename();
    }

    public T QueryById(long j, Class cls) {
        try {
            return (T) this.daoSession.getDao(cls).loadByRowId(j);
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e(e);
            return null;
        }
    }

    public List<T> QueryObject(Class cls, String str, String... strArr) {
        try {
            if (this.daoSession.getDao(cls) == null) {
                return null;
            }
            return (List<T>) this.daoSession.getDao(cls).queryRaw(str, strArr);
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return null;
        }
    }

    public List<T> QueryListByDesc(Class cls, Property property, int i, String str, Object... objArr) {
        try {
            if (this.daoSession.getDao(cls) == null) {
                return null;
            }
            return (List<T>) this.daoSession.getDao(cls).queryBuilder().where(new WhereCondition.StringCondition(str, objArr), new WhereCondition[0]).orderDesc(property).limit(i).list();
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return null;
        }
    }

    public List<T> QueryAll(Class cls) {
        try {
            return (List<T>) this.daoSession.getDao(cls).loadAll();
        } catch (Exception e) {
            randomReportSentry(e);
            LogUtils.e("greenDAO", e.toString());
            return null;
        }
    }

    public void closeDataBase() {
        this.daoManager.closeDataBase();
    }

    public void CloseDic() {
        this.dicDaoManager.closeDataBase();
    }

    public void closeLight() {
        this.lightDaoManager.closeDataBase();
    }

    public void randomReportSentry(Exception exc) {
        if (new Random(System.currentTimeMillis()).nextInt(100) <= 10) {
            SentryUtil.captureException(exc);
        }
    }
}
