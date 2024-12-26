package com.tron.wallet.business.transfer.deposit;

import com.arasthel.asyncjob.AsyncJob;
import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.wallet.business.tabdapp.bean.DappToMainFeeResponse;
import com.tron.wallet.business.transfer.deposit.DepositContract;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import com.tron.wallet.db.SpAPI;
import io.reactivex.disposables.Disposable;
public class DepositPresenter extends DepositContract.Presenter implements ICallback<DappToMainFeeResponse> {
    @Override
    protected void onStart() {
    }

    @Override
    public void getDappToMainFee() {
        ChainBean currentChain = SpAPI.THIS.getCurrentChain();
        if (currentChain == null || currentChain.isMainChain) {
            return;
        }
        ((DepositContract.Model) this.mModel).getDappToMainFee().subscribe(new IObserver(this, "0"));
    }

    @Override
    public void onResponse(String str, DappToMainFeeResponse dappToMainFeeResponse) {
        if (dappToMainFeeResponse != null) {
            SpAPI.THIS.setDappToMainFee(dappToMainFeeResponse.getDappToMainFee());
            ((DepositContract.View) this.mView).setDappToMainFee(dappToMainFeeResponse.getDappToMainFee());
            return;
        }
        getWithDrawFeeByGRPC();
    }

    @Override
    public void onFailure(String str, String str2) {
        getWithDrawFeeByGRPC();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        this.mRxManager.add(disposable);
    }

    public void getWithDrawFeeByGRPC() {
        AsyncJob.doInBackground(new AsyncJob.OnBackgroundJob() {
            @Override
            public void doOnBackground() {
                try {
                    final long longValue = TriggerUtils.getWithdrawFee().longValue();
                    SpAPI.THIS.setDappToMainFee(longValue);
                    AsyncJob.doOnMainThread(new AsyncJob.OnMainThreadJob() {
                        @Override
                        public void doInUIThread() {
                            ((DepositContract.View) mView).setDappToMainFee(longValue);
                        }
                    });
                } catch (Exception unused) {
                    ((DepositContract.View) mView).setDappToMainFee(SpAPI.THIS.getDappToMainFee());
                }
            }
        });
    }
}
