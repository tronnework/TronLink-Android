package com.tron.wallet.business.addassets2;

import com.tron.tron_base.frame.utils.ThreadPoolManager;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.MyAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.AssetsDataOutput;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.bean.NftDataOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.addassets2.repository.FollowAssetsSortListController;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public class MyAssetsModel implements MyAssetsContract.Model {
    @Override
    public Observable<AssetsData> getMyAssets() {
        return AssetsManager.MyAssets.getMyAssets();
    }

    @Override
    public Observable<Boolean> saveMyAssets(AssetsData assetsData) {
        return AssetsManager.MyAssets.saveMyAssets(assetsData);
    }

    @Override
    public Observable<AssetsDataOutput> requestMyAssets() {
        return AssetsManager.MyAssets.requestMyAssets();
    }

    @Override
    public Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2) {
        return AssetsManager.getInstance().requestModifiedFollowAssets(list, list2);
    }

    @Override
    public Observable<List<NftTokenBean>> getMyCollections() {
        return AssetsManager.MyCollections.getMyCollections();
    }

    @Override
    public Observable<Boolean> saveMyCollections(List<NftTokenBean> list) {
        return AssetsManager.MyCollections.saveMyCollections(list);
    }

    @Override
    public Observable<NftDataOutput> requestMyCollections() {
        return AssetsManager.MyCollections.requestMyCollections();
    }

    @Override
    public Observable<DelMyAssetsOutput> requestDelMyAssets(List<TokenBean> list) {
        return AssetsManager.MyAssets.requestDelMyAssets(list);
    }

    @Override
    public Observable<DelMyAssetsOutput> requestDelMyCollections(List<NftTokenBean> list) {
        return AssetsManager.MyCollections.requestDelMyCollections(list);
    }

    @Override
    public void removeTokenSortBean(final String str, final TokenBean tokenBean) {
        ThreadPoolManager.newInstance().addExecuteTask(new Runnable() {
            @Override
            public final void run() {
                FollowAssetsSortListController.getInstance().removeSingle(str, tokenBean);
            }
        });
    }
}
