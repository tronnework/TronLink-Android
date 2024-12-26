package com.tron.wallet.business.walletmanager.selectaddress;

import android.os.Bundle;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.walletmanager.adapter.LoadAddressAction;
import com.tron.wallet.business.walletmanager.selectaddress.SelectAddressContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectAddressModel implements SelectAddressContract.Model {
    private LoadAddressAction<? extends Wallet> adapter;

    @Override
    public LoadAddressAction<? extends Wallet> getLoadAddressAction() {
        return this.adapter;
    }

    @Override
    public void setLoadAddressAction(LoadAddressAction<? extends Wallet> loadAddressAction) {
        this.adapter = loadAddressAction;
    }

    @Override
    public Observable<? extends List<? extends Wallet>> deriveAddress(String str, WalletPath walletPath, int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putString(TronConfig.IMPORT_CONTENT, str);
        return this.adapter.createBatchWallets(bundle, walletPath, i, i2);
    }

    @Override
    public Observable<AccountBalanceOutput> getBalances(List<? extends Wallet> list) {
        HashMap hashMap = new HashMap();
        for (Wallet wallet : list) {
            hashMap.put(wallet.getAddress(), 5);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(list.get(0).getAddress(), 2, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(arrayList))).compose(RxSchedulers.io_main());
    }
}
