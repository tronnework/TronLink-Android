package com.tron.wallet.business.transfer.deposit;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabdapp.bean.DappToMainFeeResponse;
import com.tron.wallet.business.transfer.deposit.DepositContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class DepositModel implements DepositContract.Model {
    @Override
    public Observable<DappToMainFeeResponse> getDappToMainFee() {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDappToMainFee().compose(RxSchedulers.io_main());
    }
}
