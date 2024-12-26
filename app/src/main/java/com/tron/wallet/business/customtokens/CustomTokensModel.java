package com.tron.wallet.business.customtokens;

import com.google.gson.Gson;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.customtokens.CustomTokensContract;
import com.tron.wallet.business.customtokens.bean.AddCustomTokenInput;
import com.tron.wallet.business.customtokens.bean.AddCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenInput;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenOutput;
import com.tron.wallet.business.customtokens.bean.SyncCustomTokenOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
public class CustomTokensModel implements CustomTokensContract.Model {
    @Override
    public Observable<QueryCustomTokenOutput> queryCustomToken(String str, String str2) {
        QueryCustomTokenInput queryCustomTokenInput = new QueryCustomTokenInput();
        queryCustomTokenInput.setAddress(str);
        queryCustomTokenInput.setTokenAddress(str2);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestQueryCustomToken(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(queryCustomTokenInput))).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<AddCustomTokenOutput> addCustomToken(String str, TokenBean tokenBean) {
        AddCustomTokenInput addCustomTokenInput = new AddCustomTokenInput();
        addCustomTokenInput.setAddress(str);
        addCustomTokenInput.setTokenAddress(tokenBean.getContractAddress());
        addCustomTokenInput.setName(tokenBean.getName());
        addCustomTokenInput.setSymbol(tokenBean.getShortName());
        addCustomTokenInput.setDecimal(tokenBean.getPrecision());
        addCustomTokenInput.setType(tokenBean.getType());
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestAddCustomToken(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(addCustomTokenInput))).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<SyncCustomTokenOutput> syncCustomToken(String str, String str2) {
        QueryCustomTokenInput queryCustomTokenInput = new QueryCustomTokenInput();
        queryCustomTokenInput.setAddress(str);
        queryCustomTokenInput.setTokenAddress(str2);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestSyncCustomToken(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(queryCustomTokenInput))).compose(RxSchedulers.io_main());
    }
}
