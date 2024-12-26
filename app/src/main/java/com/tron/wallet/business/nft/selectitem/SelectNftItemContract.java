package com.tron.wallet.business.nft.selectitem;

import com.tron.wallet.business.addassets2.bean.nft.NftItemInfo;
import com.tron.wallet.common.bean.token.TokenBean;
import java.util.List;
public interface SelectNftItemContract {

    public interface Presenter {
        void destroy();

        String getSearchedKeyword();

        void loadMoreSearchResult(String str);

        void searchAssets(String str, int i, int i2);

        void start(View view, TokenBean tokenBean, NftItemInfo nftItemInfo, String str);
    }

    public interface View {
        void loadMoreComplete(List<NftItemInfo> list);

        void loadMoreDone();

        void loadMoreFail();

        void showNoNetView();

        void updateSearchedAssets(List<NftItemInfo> list);
    }
}
