package com.tron.wallet.business.transfer.selecttoken;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.transfer.selecttoken.TransferSelectTokenContract;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.net.HttpAPI;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import org.tron.api.GrpcAPI;
public class TransferSelectTokenModel implements TransferSelectTokenContract.Model {
    @Override
    public Observable<GrpcAPI.AccountResourceMessage> getAccountResource(String str) {
        return Observable.just(str).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource just;
                just = Observable.just(TronAPI.getAccountRes(StringTronUtil.decode58Check((String) obj)));
                return just;
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<NftItemInfo> getDefaultNftItemInfo(String str, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollection(str, str2, 0, 10).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                return TransferSelectTokenModel.lambda$getDefaultNftItemInfo$1((NftInfoOutput) obj);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static NftItemInfo lambda$getDefaultNftItemInfo$1(NftInfoOutput nftInfoOutput) throws Exception {
        if (nftInfoOutput != null && nftInfoOutput.getData() != null && nftInfoOutput.getData().getCollectionInfoList() != null && nftInfoOutput.getData().getCollectionInfoList().size() > 0) {
            return nftInfoOutput.getData().getCollectionInfoList().get(0);
        }
        return new NftItemInfo();
    }

    @Override
    public Observable<AssetsQueryOutput> queryAssets(String str, int i, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).requestGetAsset(str, i, str2).compose(RxSchedulers.io_main());
    }
}
