package com.tron.wallet.business.tabswap;

import com.tron.tron_base.frame.base.BaseModel;
import com.tron.tron_base.frame.base.BasePresenter;
import com.tron.tron_base.frame.base.BaseView;
import com.tron.wallet.business.tabswap.bean.SwapInfoOutput;
import com.tron.wallet.business.tabswap.bean.SwapTokenBean;
import java.util.List;
import org.tron.api.GrpcAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public interface SwapConfirmMockContract {

    public interface Model extends BaseModel {
        List<Protocol.Transaction> submitSwap(SwapInfoOutput swapInfoOutput, int i, SwapTokenBean swapTokenBean, SwapTokenBean swapTokenBean2, Wallet wallet);
    }

    public static abstract class Presenter extends BasePresenter<Model, View> {
        public abstract void approve(String str, String str2, String str3, String str4);

        public abstract void requestCheckApproved(SwapTokenBean swapTokenBean, String str);
    }

    public interface View extends BaseView {
        void requestSwap();

        void startConfirmApprove(GrpcAPI.TransactionExtention transactionExtention);
    }
}
