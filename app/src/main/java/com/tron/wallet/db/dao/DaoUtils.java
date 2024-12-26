package com.tron.wallet.db.dao;

import android.content.Context;
import com.tron.tron_base.frame.utils.AppContextUtil;
public class DaoUtils {
    private static BaseDao baseDao;
    private static BaseDao dicBaseDao;
    private static BaseDao lightBaseDao;

    public static synchronized BaseDao getInstance(Context context) {
        BaseDao baseDao2;
        synchronized (DaoUtils.class) {
            if (baseDao == null) {
                BaseDao baseDao3 = new BaseDao();
                baseDao = baseDao3;
                baseDao3.handleITron(context);
            }
            baseDao2 = baseDao;
        }
        return baseDao2;
    }

    public static synchronized BaseDao getInstance() {
        BaseDao baseDao2;
        synchronized (DaoUtils.class) {
            if (baseDao == null) {
                BaseDao baseDao3 = new BaseDao();
                baseDao = baseDao3;
                baseDao3.handleITron(AppContextUtil.getContext());
            }
            baseDao2 = baseDao;
        }
        return baseDao2;
    }

    public static synchronized BaseDao getDicInstance() {
        BaseDao baseDao2;
        synchronized (DaoUtils.class) {
            if (dicBaseDao == null) {
                BaseDao baseDao3 = new BaseDao();
                dicBaseDao = baseDao3;
                baseDao3.handleDic();
            }
            baseDao2 = dicBaseDao;
        }
        return baseDao2;
    }

    public static synchronized BaseDao getLightInstance() {
        BaseDao baseDao2;
        synchronized (DaoUtils.class) {
            if (lightBaseDao == null) {
                BaseDao baseDao3 = new BaseDao();
                lightBaseDao = baseDao3;
                baseDao3.handleLight();
            }
            baseDao2 = lightBaseDao;
        }
        return baseDao2;
    }

    public static void closeDataBase() {
        BaseDao baseDao2 = baseDao;
        if (baseDao2 != null) {
            baseDao2.closeDataBase();
            baseDao = null;
        }
        BaseDao baseDao3 = dicBaseDao;
        if (baseDao3 != null) {
            baseDao3.CloseDic();
            dicBaseDao = null;
        }
        BaseDao baseDao4 = lightBaseDao;
        if (baseDao4 != null) {
            baseDao4.closeLight();
            lightBaseDao = null;
        }
    }

    public static void closeDicBase() {
        dicBaseDao.CloseDic();
    }
}
