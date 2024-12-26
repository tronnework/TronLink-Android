package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.approve.DappApproveConfirmContract;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
public class DappApproveConfirmPresenter extends DappApproveConfirmContract.Presenter {
    @Override
    protected void onStart() {
    }

    void getProjectInfo(String str) {
        ((DappApproveConfirmContract.Model) this.mModel).getProjectInfo(str, WalletUtils.getSelectedPublicWallet().getAddress()).subscribe(new IObserver(new ICallback<DappProjectIOutput>() {
            @Override
            public void onFailure(String str2, String str3) {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, DappProjectIOutput dappProjectIOutput) {
                LogUtils.e("getProjectInfo", dappProjectIOutput.getData().toString());
            }
        }, "getProjectInfo"));
    }
}
