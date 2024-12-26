package com.tron.wallet.business.customtokens;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.customtokens.CustomTokensContract;
import com.tron.wallet.business.customtokens.bean.CustomTokenBean;
import com.tron.wallet.business.customtokens.bean.QueryCustomTokenOutput;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
import org.tron.walletserver.Wallet;
public class CustomTokensPresenter extends CustomTokensContract.Presenter {
    private String address;
    private Disposable getTokenDisposable;

    @Override
    protected void onStart() {
        Wallet selectedPublicWallet = WalletUtils.getSelectedPublicWallet();
        if (selectedPublicWallet != null) {
            this.address = selectedPublicWallet.getAddress();
        }
    }

    @Override
    public void getToken(final String str) {
        disposeObserver();
        ((CustomTokensContract.Model) this.mModel).queryCustomToken(this.address, str).subscribe(new IObserver(new ICallback<QueryCustomTokenOutput>() {
            @Override
            public void onResponse(String str2, QueryCustomTokenOutput queryCustomTokenOutput) {
                if (queryCustomTokenOutput != null && queryCustomTokenOutput.getCode() == 0 && queryCustomTokenOutput.getData() != null) {
                    CustomTokenBean data = queryCustomTokenOutput.getData();
                    data.setTokenAddress(str);
                    if (data.getAssetInfo() != null) {
                        data.getAssetInfo().setAddress(address);
                        data.getAssetInfo().setContractAddress(str);
                    }
                    ((CustomTokensContract.View) mView).updateToken(data, str);
                    return;
                }
                ((CustomTokensContract.View) mView).showNetError(str);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((CustomTokensContract.View) mView).showNetError(str);
            }

            @Override
            public void onSubscribe(Disposable disposable) {
                getTokenDisposable = disposable;
            }
        }, "getToken"));
    }

    private void disposeObserver() {
        Disposable disposable = this.getTokenDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.getTokenDisposable.dispose();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeObserver();
    }
}
