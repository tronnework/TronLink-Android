package com.tron.wallet.business.nft.contract;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.addassets2.bean.nft.NftTransferOutput;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import io.reactivex.Observable;
import org.tron.protos.Protocol;
public interface NftHistoryContentContract {

    public interface Model extends BaseModel {
        Observable<NftTransferOutput> getCollectionTransferList(String str, String str2, String str3, int i, int i2, String str4);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void addLocalHistoryToListAndShow();

        abstract void addSome();

        public abstract String getCurrentAddress();

        public abstract void getData();

        abstract void getLocalData();

        abstract void getNftTransfer();

        abstract long getTokenId();

        public abstract void init();

        abstract void netWorkChange();

        public abstract void onActivityResult(int i, int i2, Intent intent);

        public abstract void setCanRefresh();
    }

    public interface View extends BaseView {
        Fragment getContentView();

        String getContractAddress();

        android.view.View getEmptyView();

        android.view.View getHolderView();

        int getIndex();

        LoadMoreRecyclerView getRecycleView();

        TokenBean getToken();

        String getTokenType();

        void hideRefresh();

        boolean isIDestroyed();

        void loadMore();

        void onGetAccount(Protocol.Account account);

        void onRefresh();

        void setCanRefresh(boolean z);

        void showEmptyView(boolean z);

        void showNoNetError(boolean z);

        void showShastaView();
    }
}
