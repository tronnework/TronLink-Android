package com.tron.wallet.common.utils;

import com.tron.wallet.common.bean.NameAddressExtraBean;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.HashMap;
public class AddressMapUtils {
    private ConcurrentHashMap<String, NameAddressExtraBean> addressMap;

    private AddressMapUtils() {
        this.addressMap = new ConcurrentHashMap<>();
    }

    public static AddressMapUtils getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final AddressMapUtils instance = new AddressMapUtils();

        private SingletonHolder() {
        }
    }

    public void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap) {
        this.addressMap.clear();
        this.addressMap.putAll(hashMap);
    }

    public boolean isEmpty() {
        return this.addressMap.isEmpty();
    }

    public void addANameAddressExtraBean(String str, NameAddressExtraBean nameAddressExtraBean) {
        this.addressMap.put(str, nameAddressExtraBean);
    }

    public boolean isContainsAddress(String str) {
        return this.addressMap.containsKey(str);
    }

    public NameAddressExtraBean getNameAddress(String str) {
        return this.addressMap.get(str);
    }
}
