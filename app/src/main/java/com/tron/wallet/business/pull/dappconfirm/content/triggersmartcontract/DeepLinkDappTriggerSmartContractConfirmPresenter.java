package com.tron.wallet.business.pull.dappconfirm.content.triggersmartcontract;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.pull.dappconfirm.content.triggersmartcontract.DeepLinkDappTriggerSmartContractConfirmContract;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.disposables.Disposable;
public class DeepLinkDappTriggerSmartContractConfirmPresenter extends DeepLinkDappTriggerSmartContractConfirmContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    void getProjectInfo(String str) {
        ((DeepLinkDappTriggerSmartContractConfirmContract.Model) this.mModel).getProjectInfo(str, WalletUtils.getSelectedPublicWallet().getAddress()).subscribe(new IObserver(new ICallback<DappProjectIOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, DappProjectIOutput dappProjectIOutput) {
                LogUtils.e("getProjectInfo", dappProjectIOutput.getData().toString());
                if (dappProjectIOutput != null) {
                    ((DeepLinkDappTriggerSmartContractConfirmContract.View) mView).updateHeaderInfoForProject(dappProjectIOutput.getData(), false);
                } else {
                    ((DeepLinkDappTriggerSmartContractConfirmContract.View) mView).updateHeaderInfoForProject(null, false);
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((DeepLinkDappTriggerSmartContractConfirmContract.View) mView).updateHeaderInfoForProject(null, false);
            }
        }, "getProjectInfo"));
    }
}
