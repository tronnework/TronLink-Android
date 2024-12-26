package com.tron.wallet.business.tabmy.myhome.settings.unittest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.tron.tron_base.frame.base.BaseFragment;
import com.tron.tron_base.frame.base.EmptyModel;
import com.tron.tron_base.frame.base.EmptyPresenter;
import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.interfaces.OnMainThread;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.components.MyWebView;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.databinding.AcGetTrxTestBinding;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.math.BigDecimal;
import org.tron.common.utils.TransactionUtils;
import org.tron.walletserver.I_TYPE;
import org.tron.walletserver.Wallet;
public class GetTrxTestFragment extends BaseFragment<EmptyPresenter, EmptyModel> {
    private AcGetTrxTestBinding binding;
    String key2 = "0fc3105b4375ab30b6334a888b139f9dcb4e05607d1def53187e973051c5bd3f";
    private Wallet selectedPublicWallet;
    private Wallet wallet;
    MyWebView webView;

    @Override
    protected void processData() {
        setHeaderBarAndRightTv("GetTRX", "Get");
        this.webView.loadUrl("https://nileex.io/join/getJoinPage");
        showLoadingDialog();
        this.wallet = new Wallet(I_TYPE.PRIVATE, this.key2);
        this.selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        dismissLoadingDialog();
        UIUtils.copy(this.selectedPublicWallet.getAddress());
        Toast("The address has been copied");
    }

    @Override
    protected View setLayoutView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AcGetTrxTestBinding inflate = AcGetTrxTestBinding.inflate(layoutInflater, viewGroup, false);
        this.binding = inflate;
        RelativeLayout root = inflate.getRoot();
        mappingId();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.binding = null;
    }

    public void mappingId() {
        this.webView = this.binding.webView;
    }

    @Override
    public void onLeftButtonClick() {
        super.onLeftButtonClick();
    }

    @Override
    public void onRightTextClick() {
        super.onRightTextClick();
        final long longValue = BigDecimalUtils.mul_((Object) 999, (Object) BigDecimal.valueOf(1000000.0d)).longValue();
        showLoadingDialog();
        runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$onRightTextClick$1(longValue);
            }
        });
    }

    public void lambda$onRightTextClick$1(long j) {
        try {
            boolean broadcastTransaction = TronAPI.broadcastTransaction(TransactionUtils.sign(TronAPI.createTransaction4Transfer2(TronAPI.createTransferContract(StringTronUtil.decode58Check(this.selectedPublicWallet.getAddress()), StringTronUtil.decode58Check(this.wallet.getAddress()), j)).getTransaction(), this.wallet.getECKey(), null, true));
            runOnUIThread(new OnMainThread() {
                @Override
                public final void doInUIThread() {
                    lambda$onRightTextClick$0();
                }
            });
            if (broadcastTransaction) {
                Toast("Success");
            } else {
                Toast("Failed");
            }
        } catch (Exception e) {
            LogUtils.e(e);
            Toast("Failed");
        }
    }

    public void lambda$onRightTextClick$0() {
        dismissLoadingDialog();
    }
}
