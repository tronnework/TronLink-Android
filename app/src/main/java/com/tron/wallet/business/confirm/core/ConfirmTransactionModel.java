package com.tron.wallet.business.confirm.core;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.confirm.core.ConfirmTransactionContract;
import com.tron.wallet.common.bean.token.TransactionResult;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
public class ConfirmTransactionModel implements ConfirmTransactionContract.Model {
    @Override
    public GrpcAPI.AccountResourceMessage getAccountResourceMessage() {
        return null;
    }

    @Override
    public long getBandwidthCost(Protocol.Transaction.Contract contract) {
        return 0L;
    }

    @Override
    public long getCurrentBandwidth() {
        return 0L;
    }

    @Override
    public long getEnergy() {
        return 0L;
    }

    @Override
    public long getNewBandwidth() {
        return 0L;
    }

    @Override
    public void init(GrpcAPI.AccountResourceMessage accountResourceMessage) {
    }

    @Override
    public boolean isEnoughBandwidth() {
        return false;
    }

    @Override
    public Observable<TransactionResult> transaction(RequestBody requestBody) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).transaction(requestBody).compose(RxSchedulers.io_main());
    }
}
