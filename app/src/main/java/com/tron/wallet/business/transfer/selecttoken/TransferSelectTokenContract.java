package com.tron.wallet.business.transfer.selecttoken;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.confirm.parambuilder.bean.TransferParam;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.math.BigDecimal;
import org.tron.api.GrpcAPI;
import org.tron.walletserver.Wallet;
public interface TransferSelectTokenContract {

    public interface Model extends BaseModel {
        Observable<GrpcAPI.AccountResourceMessage> getAccountResource(String str);

        Observable<NftItemInfo> getDefaultNftItemInfo(String str, String str2);

        Observable<AssetsQueryOutput> queryAssets(String str, int i, String str2);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void getDefaultNftItem(String str);

        abstract GrpcAPI.AccountResourceMessage getSendAccountResource();

        abstract Wallet getWallet();

        abstract Observable<AssetsQueryOutput> queryAssets(String str, int i, String str2);

        abstract void send(String str, BigDecimal bigDecimal, NftItemInfo nftItemInfo);

        abstract void setMultiSign(boolean z);

        abstract void setParam(TransferParam transferParam);

        abstract void setTokenBean(TokenBean tokenBean);

        abstract void start();
    }

    public interface View extends BaseView {
        void setSendAccountResource(GrpcAPI.AccountResourceMessage accountResourceMessage);

        void setSendResult(boolean z, int i);

        void updateDefaultNftItemInfo(NftItemInfo nftItemInfo);
    }
}
