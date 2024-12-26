package com.tron.wallet.business.transfer;

import android.content.Intent;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.appbar.AppBarLayout;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransferOutput;
import com.tron.wallet.common.components.ptr.PtrHTFrameLayoutV2;
import com.tron.wallet.common.components.xtablayout.XTabLayoutV2;
import io.reactivex.Observable;
import java.util.HashMap;
import org.tron.walletserver.Wallet;
public interface TokenDetailContract {

    public interface Model extends BaseModel {
        Observable<HashMap<String, NameAddressExtraBean>> getAllAddress();

        Observable<TransferOutput> getTRX10Transfer(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2);

        Observable<TransferOutput> getTRXTransfer(String str, int i, int i2, long j, long j2, int i3, boolean z);

        Observable<TransferOutput> getTRXTransfer20(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2);

        Observable<TransferOutput> getWithdrawOrDeposit(String str, String str2, String str3, int i, int i2, String str4);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract void addSome();

        abstract void doReceive();

        abstract void doTransfer();

        abstract long getTokenId();

        abstract Wallet getWallet();

        abstract void historyTask();

        abstract boolean isCurrentRefresh();

        abstract void netWorkChange();

        abstract void onActivityResult(int i, int i2, Intent intent);

        abstract void refreshData(Intent intent);

        abstract void syncCustomToken();

        abstract void updateHeader();
    }

    public interface View extends BaseView {
        AppBarLayout getAppBarLayout();

        PtrHTFrameLayoutV2 getFrameLayout();

        Intent getIIntent();

        int getTypeIndex();

        ViewPager2 getViewPager();

        XTabLayoutV2 getXTablayout();

        void hideSubTitleAndRightIcon();

        boolean isIDestroyed();

        void refreshBottomBarForIsMarket(boolean z, boolean z2, boolean z3);

        void scan();

        void setAddressMap(HashMap<String, NameAddressExtraBean> hashMap);

        void setFilterOptionWidth(int i);

        void setFilterSmallValueEnable(boolean z);

        void setStatusBarBackgroundColor(int i);

        void showFilterSmallValue(boolean z);

        void updateTokenInfo(TokenBean tokenBean);
    }
}
