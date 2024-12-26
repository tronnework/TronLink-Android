package com.tron.wallet.business.nft.contract;

import android.text.TextUtils;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfoOutput;
import com.tron.wallet.net.HttpAPI;
public class NftItemPresenter extends EmptyPresenter {
    public void refreshTokenItemInfo(String str, NftItemInfo nftItemInfo, ICallback<NftItemInfoOutput> iCallback) {
        if (TextUtils.isEmpty(str) || nftItemInfo == null || TextUtils.isEmpty(nftItemInfo.getTokenAddress()) || TextUtils.isEmpty(nftItemInfo.getAssetId())) {
            return;
        }
        ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getCollectionInfo(str, nftItemInfo.getTokenAddress(), nftItemInfo.getAssetId()).compose(RxSchedulers.io_main()).subscribe(new IObserver(iCallback, ""));
    }
}
