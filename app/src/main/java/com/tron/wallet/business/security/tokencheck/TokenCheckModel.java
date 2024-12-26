package com.tron.wallet.business.security.tokencheck;

import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.security.tokencheck.TokenCheckContract;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public class TokenCheckModel implements TokenCheckContract.Model {
    @Override
    public Observable<DelMyAssetsOutput> requestDelMyCollections(List<NftTokenBean> list) {
        return AssetsManager.MyCollections.requestDelMyCollections(list);
    }

    @Override
    public Observable<DelMyAssetsOutput> requestDelMyAssets(List<TokenBean> list) {
        return AssetsManager.MyAssets.requestDelMyAssets(list);
    }
}
