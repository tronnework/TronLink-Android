package com.tron.wallet.business.addassets2.repository;
public class KVGlobalController {
    private static KVGlobalController instance;
    private KVController kvController = new KVController(DbFactory.getDbTronGlobalManager());

    private KVGlobalController() {
    }

    public static KVGlobalController getInstance() {
        if (instance == null) {
            synchronized (KVGlobalController.class) {
                if (instance == null) {
                    instance = new KVGlobalController();
                }
            }
        }
        return instance;
    }

    public String getValue(String str) {
        return this.kvController.getValue(str);
    }

    public void setValue(String str, String str2) {
        this.kvController.setValue(str, str2);
    }
}
