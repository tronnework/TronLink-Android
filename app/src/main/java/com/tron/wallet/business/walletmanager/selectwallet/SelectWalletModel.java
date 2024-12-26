package com.tron.wallet.business.walletmanager.selectwallet;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.wallet.business.walletmanager.selectwallet.SelectWalletContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.Controller.TRXAccountBalanceController;
import com.tron.wallet.db.bean.TRXAccountBalanceBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
public class SelectWalletModel implements SelectWalletContract.Model {
    @Override
    public Observable<AccountBalanceOutput> getAccountInfosList(String str, RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(str, 1, requestBody);
    }

    @Override
    public List<TRXAccountBalanceBean> getAccountBalance() {
        return TRXAccountBalanceController.getInstance(AppContextUtil.getContext()).queryAll();
    }
}
