package com.tron.wallet.business.nft.contract;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.bean.nft.NftTransferOutput;
import com.tron.wallet.business.nft.contract.NftHistoryContentContract;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
public class NftHistoryContentModel implements NftHistoryContentContract.Model {
    @Override
    public Observable<NftTransferOutput> getCollectionTransferList(String str, String str2, String str3, int i, int i2, String str4) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionTransferList(str, str2, str3, i, i2, str4, 1).compose(RxSchedulers.io_main());
    }
}
