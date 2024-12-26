package com.tron.wallet.business.nft.contract;

import android.content.Context;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.wallet.business.addassets2.bean.nft.NftDetailOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftInfoOutput;
import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.business.addassets2.bean.nft.NftTypeInfo;
import com.tron.wallet.common.bean.token.TokenBean;
import io.reactivex.Observable;
import java.util.List;
import okhttp3.RequestBody;
public interface Contract {

    public interface Model extends BaseModel {
        Observable<NftDetailOutput> getNftItemInfos(String str, RequestBody requestBody);

        Observable<NftInfoOutput> getNftTokenInfo(Context context, String str, String str2);

        Observable<NftInfoOutput> requestNftTokenInfo(String str, String str2, int i, int i2);

        void saveNftToken(Context context, NftTypeInfo nftTypeInfo, ICallback<Boolean> iCallback);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void getCustomTokenInfo(String str, TokenBean tokenBean);

        public abstract void getNftTokenInfo(String str, String str2);

        public abstract void refresh(String str, String str2);

        public abstract void syncCustomToken(String str, TokenBean tokenBean);
    }

    public interface View extends BaseView {
        public static final String KEY_CONTRACT_ADDRESS = "CONTRACT_ADDRESS";
        public static final String KEY_HOME_PAGE = "homePage";
        public static final String KEY_SHORT_NAME = "SHORT_NAME";
        public static final String KEY_WALLET_ADDRESS = "WALLET_ADDRESS";

        List<NftItemInfo> getCurrentData();

        void loadMoreComplete();

        void loadMoreEnd();

        void loadMoreFailed();

        void onBroadcastSuccess(Object obj);

        void onGetTokenInfo(NftInfoOutput nftInfoOutput, boolean z, boolean z2);

        void updateTokenInfo(TokenBean tokenBean);

        void updateTokenNoFunctions(List<String> list);
    }
}
