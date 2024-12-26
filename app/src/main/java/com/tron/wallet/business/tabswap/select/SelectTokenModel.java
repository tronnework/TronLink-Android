package com.tron.wallet.business.tabswap.select;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.business.tabswap.select.SelectTokenContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class SelectTokenModel implements SelectTokenContract.Model {
    private final HttpAPI httpAPI = (HttpAPI) IRequest.getAPI(HttpAPI.class);

    @Override
    public Observable<SwapWhiteListOutput> getWhiteListTokens() {
        return this.httpAPI.querySwapV3TokenList().compose(RxSchedulers.io_main());
    }
}
