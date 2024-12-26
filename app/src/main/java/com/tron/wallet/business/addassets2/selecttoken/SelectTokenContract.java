package com.tron.wallet.business.addassets2.selecttoken;

import com.tron.wallet.common.bean.token.TokenBean;
import java.util.List;
public interface SelectTokenContract {

    public interface Presenter {
        void destroy();

        String getSearchedKeyword();

        void loadMoreSearchResult(String str);

        void searchAssets(String str, int i, int i2);

        void setFilterType(int i);

        void start(View view, TokenBean tokenBean, String str, boolean z);
    }

    public interface View {
        void loadMoreComplete(List<TokenBean> list);

        void loadMoreDone();

        void loadMoreFail();

        void showNoNetView();

        void updateSearchedAssets(List<TokenBean> list);
    }
}
