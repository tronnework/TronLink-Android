package com.tron.wallet.business.addassets2.repository;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.wallet.db.SpAPI;
import java.util.HashMap;
import java.util.Map;
import org.greenrobot.greendao.query.QueryBuilder;
public class DbFactory {
    private static final String DB_TRON = "dbTron.db";
    private static Map<String, DbManager> dbStore = new HashMap();

    public static String getEnvPrefix() {
        return IRequest.ENVIRONMENT == IRequest.NET_ENVIRONMENT.RELEASE ? "" : IRequest.ENVIRONMENT.toString();
    }

    public static String getChainPrefix() {
        return SpAPI.THIS.getCurrentChainName().equals("MainChain") ? "" : SpAPI.THIS.getCurrentChainName();
    }

    public static synchronized DbManager getDbTronManager() {
        DbManager dbManager;
        synchronized (DbFactory.class) {
            String str = getEnvPrefix() + getChainPrefix() + DB_TRON;
            dbManager = dbStore.get(str);
            if (dbManager == null) {
                dbManager = new DbManager(str);
                dbStore.put(str, dbManager);
            }
        }
        return dbManager;
    }

    public static synchronized DbManager getDbTronGlobalManager() {
        DbManager dbManager;
        synchronized (DbFactory.class) {
            dbManager = dbStore.get(DB_TRON);
            if (dbManager == null) {
                dbManager = new DbManager(DB_TRON);
                dbStore.put(DB_TRON, dbManager);
            }
        }
        return dbManager;
    }

    public static void setDebug(boolean z) {
        QueryBuilder.LOG_SQL = z;
        QueryBuilder.LOG_VALUES = z;
    }
}
