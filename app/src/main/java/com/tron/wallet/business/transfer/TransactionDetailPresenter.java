package com.tron.wallet.business.transfer;

import com.tron.tron_base.frame.net.ICallback;
import com.tron.tron_base.frame.net.IObserver;
import com.tron.tron_base.frame.utils.StringTronUtil;
import com.tron.wallet.business.transfer.TransactionDetailContract;
import com.tron.wallet.common.bean.token.TransactionInfoBean;
import io.reactivex.disposables.Disposable;
import java.util.List;
public class TransactionDetailPresenter extends TransactionDetailContract.Presenter {
    @Override
    void addSome() {
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void getFee(String str) {
        ((TransactionDetailContract.Model) this.mModel).getTransactionInfo(str).subscribe(new IObserver(new ICallback<TransactionInfoBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onResponse(String str2, TransactionInfoBean transactionInfoBean) {
                if (transactionInfoBean != null && !StringTronUtil.isEmpty(transactionInfoBean.getHash()) && transactionInfoBean.getTimestamp() > 0) {
                    handleScam(transactionInfoBean);
                    ((TransactionDetailContract.View) mView).dismissLoadingDialog();
                    ((TransactionDetailContract.View) mView).updateContent(transactionInfoBean);
                    return;
                }
                ((TransactionDetailContract.View) mView).dismissLoadingDialog();
                ((TransactionDetailContract.View) mView).updateDefaultView(transactionInfoBean);
            }

            @Override
            public void onFailure(String str2, String str3) {
                ((TransactionDetailContract.View) mView).dismissLoadingDialog();
                ((TransactionDetailContract.View) mView).updateDefaultView(null);
            }
        }, "getFee"));
    }

    @Override
    protected void handleScam(TransactionInfoBean transactionInfoBean) {
        List trc20TransferInfo;
        if (!com.tron.wallet.common.utils.StringTronUtil.equals(transactionInfoBean.getContract_type(), "trc20") || (trc20TransferInfo = transactionInfoBean.getTrc20TransferInfo()) == null || trc20TransferInfo.size() <= 0) {
            return;
        }
        ((TransactionDetailContract.View) this.mView).updateScam(((TransactionInfoBean.Trc20ApprovalItem) trc20TransferInfo.get(0)).getStatus());
    }
}
