package com.tron.wallet.business.tabswap.mvp;

import android.content.Context;
import android.view.View;
import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import com.tron.wallet.business.tabswap.bean.SwapTokenInfoBean;
import com.tron.wallet.business.tabswap.bean.SwapWhiteListOutput;
import com.tron.wallet.common.adapter.SwapAdapter;
import com.tron.wallet.common.adapter.holder.swap.TokenHolder;
import io.reactivex.Observable;
import java.math.BigDecimal;
import java.util.List;
import okhttp3.RequestBody;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface Contract {

    public interface Model extends BaseModel {
        Observable<BigDecimal> getBalanceByAddress(String str, String[] strArr);

        Observable<SwapWhiteListOutput> getWhiteListTokens(boolean z, Context context);

        Observable<SwapInfoOutput> querySwapInfo(SwapTokenBean swapTokenBean, String str, String str2);

        Observable<Object> recordTransaction(RequestBody requestBody);

        List<Protocol.Transaction> submitSwap(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, Wallet wallet);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        abstract TokenHolder.OnAmountChangedListener getAmountChangedListener();

        abstract void getInitTokens(boolean z);

        abstract View.OnClickListener getOnBtnConfirmClickListener();

        abstract void getRecord();

        abstract TokenHolder.OnTokenChangedCallback getTokenChangedListener();

        abstract void startCheckTranscationState();

        abstract void stopCheckTranscation();
    }

    public interface View extends BaseView {
        SwapAdapter getAdapter();

        void notifyInfoVisible(boolean z);

        void onGetInitTokens(SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, boolean z);

        void showLoading(boolean z);

        void showLoadingWindow(boolean z);

        void showNoNetNotice();

        void updateBalance(String str, String str2);

        void updateSwapTokenInfo(SwapTokenInfoBean swapTokenInfoBean, List<SwapInfoOutput.InfoData> list, int i, View.OnClickListener onClickListener, boolean z);

        void updateSwapTokenValues(int i, String str, String str2);
    }
}
