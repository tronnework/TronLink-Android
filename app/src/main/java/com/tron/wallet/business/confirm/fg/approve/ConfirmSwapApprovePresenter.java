package com.tron.wallet.business.confirm.fg.approve;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.confirm.fg.approve.ConfirmSwapApproveContract;
import com.tron.wallet.business.tabdapp.bean.DappProjectIOutput;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.disposables.Disposable;
public class ConfirmSwapApprovePresenter extends ConfirmSwapApproveContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getProjectInfo(String str) {
        ((ConfirmSwapApproveContract.Model) this.mModel).getProjectInfo(str, WalletUtils.getSelectedPublicWallet().getAddress()).compose(RxSchedulers2.io_main()).subscribe(new IObserver(new ICallback<DappProjectIOutput>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, DappProjectIOutput dappProjectIOutput) {
                if (dappProjectIOutput != null) {
                    ((ConfirmSwapApproveContract.View) mView).updateHeaderInfoForProject(dappProjectIOutput.getData(), false);
                } else {
                    ((ConfirmSwapApproveContract.View) mView).updateHeaderInfoForProject(null, false);
                }
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((ConfirmSwapApproveContract.View) mView).updateHeaderInfoForProject(null, false);
            }
        }, "getProjectInfo"));
    }
}
