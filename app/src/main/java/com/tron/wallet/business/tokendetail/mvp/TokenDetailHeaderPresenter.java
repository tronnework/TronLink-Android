package com.tron.wallet.business.tokendetail.mvp;

import android.text.TextUtils;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.addassets2.AssetsManager;
import com.tron.wallet.business.addassets2.bean.AssetsQueryOutput;
import com.tron.wallet.business.tokendetail.mvp.TokenDetailHeaderContract;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.M;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
import java.util.Timer;
import java.util.TimerTask;
import org.tron.walletserver.Wallet;
public class TokenDetailHeaderPresenter extends TokenDetailHeaderContract.Presenter {
    private Wallet selectedWallet;
    private Timer timer;
    private TokenBean token;
    private String tokenType;

    @Override
    protected void onStart() {
    }

    @Override
    public void addSome() {
        this.selectedWallet = WalletUtils.getSelectedWallet();
        this.tokenType = ((TokenDetailHeaderContract.View) this.mView).getTokenType();
        this.token = ((TokenDetailHeaderContract.View) this.mView).getToken();
        this.selectedWallet = WalletUtils.getSelectedWallet();
    }

    @Override
    public void addTimer() {
        if (this.timer != null || ((TokenDetailHeaderContract.View) this.mView).isIDestroyed()) {
            return;
        }
        Timer timer = new Timer();
        this.timer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ((TokenDetailHeaderContract.View) mView).refresh();
            }
        }, 5000L, 5000L);
    }

    @Override
    public void requestHomeAssets() {
        Wallet wallet = this.selectedWallet;
        if ((wallet == null || TextUtils.isEmpty(wallet.address) || !this.selectedWallet.isShieldedWallet()) && this.mRxManager != null) {
            String str = "";
            int i = 0;
            if (!M.M_TRX.equals(this.tokenType)) {
                if (M.M_TRC10.equals(this.tokenType)) {
                    str = this.token.id + "";
                    i = 1;
                } else if (M.M_TRC20.equals(this.tokenType)) {
                    str = this.token.contractAddress;
                    i = 2;
                }
            }
            AssetsManager.getInstance().requestGetAsset(i, str).subscribe(new IObserver(new ICallback<AssetsQueryOutput>() {
                @Override
                public void onResponse(String str2, AssetsQueryOutput assetsQueryOutput) {
                    if (assetsQueryOutput.getCode() == 0 && assetsQueryOutput.data != null) {
                        if (selectedWallet != null) {
                            assetsQueryOutput.data.address = selectedWallet.getAddress();
                        }
                        if (M.M_TRX.equals(tokenType)) {
                            token.balance = assetsQueryOutput.data.balance;
                            token.totalBalance = assetsQueryOutput.data.totalBalance;
                            token.balanceStr = assetsQueryOutput.data.balanceStr;
                            mRxManager.post(Event.BALANCE, token.balanceStr);
                        } else {
                            assetsQueryOutput.data.address = selectedWallet.getAddress();
                            if (M.M_TRC10.equals(tokenType) && token.id.equals(assetsQueryOutput.data.id)) {
                                token.balance = assetsQueryOutput.data.balance;
                                token.balanceStr = assetsQueryOutput.data.balanceStr;
                                mRxManager.post(Event.BALANCE, token.balanceStr);
                            } else if (M.M_TRC20.equals(tokenType)) {
                                token.balance = assetsQueryOutput.data.balance;
                                token.balanceStr = assetsQueryOutput.data.balanceStr;
                                mRxManager.post(Event.BALANCE, token.balanceStr);
                            }
                        }
                    }
                    ((TokenDetailHeaderContract.View) mView).bindHolder();
                    ((TokenDetailHeaderContract.View) mView).showPriceLoading(false);
                }

                @Override
                public void onFailure(String str2, String str3) {
                    ((TokenDetailHeaderContract.View) mView).showPriceLoading(false);
                    ((TokenDetailHeaderContract.View) mView).bindHolder();
                }

                @Override
                public void onSubscribe(Disposable disposable) {
                    mRxManager.add(disposable);
                }
            }, "requestGetAsset"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
        }
    }
}
