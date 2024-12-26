package com.tron.wallet.business.addassets2;

import com.tron.wallet.business.addassets2.NewAssetsContract;
import com.tron.wallet.business.addassets2.bean.AssetsData;
import com.tron.wallet.business.addassets2.bean.FollowAssetsOutput;
import com.tron.wallet.business.addassets2.repository.NewAssetsController;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public class NewAssetsModel implements NewAssetsContract.Model {
    @Override
    public AssetsData getNewAssets(String str) {
        return NewAssetsController.getInstance().getNewAssets(str);
    }

    @Override
    public void clearNewAssets(String str) {
        NewAssetsController.getInstance().clear(str);
    }

    @Override
    public void removeNewAssets(String str, TokenBean tokenBean) {
        NewAssetsController.getInstance().removeNewAssets(str, tokenBean);
    }

    @Override
    public Observable<FollowAssetsOutput> followAssets(List<TokenBean> list, List<TokenBean> list2) {
        return AssetsManager.getInstance().requestModifiedFollowAssets(list, list2);
    }
}
