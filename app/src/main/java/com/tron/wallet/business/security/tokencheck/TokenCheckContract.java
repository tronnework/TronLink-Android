package com.tron.wallet.business.security.tokencheck;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.DelMyAssetsOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftTokenBean;
import com.tron.wallet.business.security.tokencheck.bean.TokenCheckBean;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
public interface TokenCheckContract {

    public interface Model extends BaseModel {
        Observable<DelMyAssetsOutput> requestDelMyAssets(List<TokenBean> list);

        Observable<DelMyAssetsOutput> requestDelMyCollections(List<NftTokenBean> list);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void deleteToken(TokenCheckBean tokenCheckBean);

        public abstract void ignoreToken(TokenCheckBean tokenCheckBean);
    }

    public interface View extends BaseView {
    }
}
