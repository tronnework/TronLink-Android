package com.tron.wallet.business.walletmanager.detail;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.detail.WalletDetailContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
public class WalletDetailModel implements WalletDetailContract.Model {
    private double totalTRX;

    @Override
    public double getTotalTRX() {
        return this.totalTRX;
    }

    @Override
    public Observable<AccountBalanceOutput> getAccountInfosList(String str, RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(str, 1, requestBody).compose(RxSchedulers.io_main());
    }

    @Override
    public Map<String, AccountBalanceOutput.DataBean.BalanceListBean> getLocalData(ArrayList<Map<String, Integer>> arrayList) {
        try {
            List<TRXAccountBalanceBean> queryAll = TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryAll();
            HashMap hashMap = new HashMap();
            if (queryAll != null && queryAll.size() != 0) {
                for (TRXAccountBalanceBean tRXAccountBalanceBean : queryAll) {
                    AccountBalanceOutput.DataBean.BalanceListBean balanceListBean = new AccountBalanceOutput.DataBean.BalanceListBean();
                    balanceListBean.setBalance(tRXAccountBalanceBean.getTotalTrx());
                    hashMap.put(tRXAccountBalanceBean.getAddress(), balanceListBean);
                    if (getWalletType(arrayList, tRXAccountBalanceBean.getAddress()) != 3) {
                        this.totalTRX += tRXAccountBalanceBean.getTotalTrx();
                    }
                }
                return hashMap;
            }
            return null;
        } catch (Exception e) {
            LogUtils.i("WalletManager", "getLocalData" + e.getMessage());
            LogUtils.e(e);
            return null;
        }
    }

    public int getWalletType(ArrayList<Map<String, Integer>> arrayList, String str) {
        Integer num;
        Iterator<Map<String, Integer>> it = arrayList.iterator();
        int i = 3;
        while (it.hasNext()) {
            Map<String, Integer> next = it.next();
            if (i != 1 && i != 2 && (num = next.get(str)) != null) {
                i = num.intValue();
            }
        }
        return i;
    }
}
