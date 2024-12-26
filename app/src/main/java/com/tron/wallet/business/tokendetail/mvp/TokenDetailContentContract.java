package com.tron.wallet.business.tokendetail.mvp;

import android.content.Intent;
import androidx.fragment.app.Fragment;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import com.tron.wallet.common.bean.token.TransferOutput;
import com.tron.wallet.common.components.LoadMoreRecyclerView;
import io.reactivex.Observable;
import org.tron.protos.Protocol;
public interface TokenDetailContentContract {

    public interface Model extends BaseModel {
        Observable<TransferOutput> getTRX10Transfer(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2, boolean z2);

        Observable<TransferOutput> getTRXTransfer(String str, int i, int i2, long j, long j2, int i3, boolean z, boolean z2, boolean z3);

        Observable<TransferOutput> getTRXTransfer20(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2, boolean z2);

        Observable<TransactionInfoBean> getTransactionInfo(String str);

        Observable<TransferOutput> getWithdrawOrDeposit(String str, String str2, String str3, int i, int i2, String str4);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addLocalHistoryToListAndShow();

        abstract void addSome();

        public abstract String getCurrentAddress();

        public abstract void getData();

        abstract void getLocalData();

        abstract void getTRXTransfer();

        abstract long getTokenId();

        public abstract void init();

        abstract boolean isCurRefresh();

        abstract void netWorkChange();

        public abstract void onActivityResult(int i, int i2, Intent intent);

        abstract void setFilterSmallValue(boolean z);
    }

    public interface View extends BaseView {
        Fragment getContentView();

        android.view.View getEmptyView();

        android.view.View getHolderView();

        int getIndex();

        boolean getIsMapping();

        android.view.View getNoNetView();

        LoadMoreRecyclerView getRecycleView();

        TokenBean getToken();

        String getTokenType();

        boolean getVisible();

        void hideRefresh();

        boolean isIDestroyed();

        void loadMore();

        void onGetAccount(Protocol.Account account);

        void onRefresh();

        void showEmptyView();

        void showNoNetError();

        void showPlaceHolderView();

        void showShastaView();

        void showTokenDetailView();
    }
}
