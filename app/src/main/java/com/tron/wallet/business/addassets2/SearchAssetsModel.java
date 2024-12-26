package com.tron.wallet.business.addassets2;

import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.SearchAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.asset.RecommendAssetsHomeOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public class SearchAssetsModel implements SearchAssetsContract.Model {
    @Override
    public Observable<RecommendAssetsHomeOutput> requestHotAssets() {
        return AssetsManager.HotAssets.requestHotAssets();
    }

    @Override
    public Observable<AssetsData> getHotAssets() {
        return AssetsManager.HotAssets.getHotAssets();
    }

    @Override
    public Observable<Boolean> saveHotAssets(AssetsData assetsData) {
        return AssetsManager.HotAssets.saveHotAssets(assetsData);
    }

    @Override
    public Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2) {
        return AssetsManager.getInstance().requestModifiedFollowAssets(list, list2);
    }

    @Override
    public Observable<AssetsDataOutput> requestSearchAssets(String str, int i, int i2, int i3) {
        return AssetsManager.getInstance().requestSearchAssets(str, i, i2, i3);
    }
}
