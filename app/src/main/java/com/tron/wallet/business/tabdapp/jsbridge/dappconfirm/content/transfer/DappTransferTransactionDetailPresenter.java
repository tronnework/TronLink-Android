package com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer;

import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.content.transfer.DappTransferTransactionDetailContract;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.net.rpc.TronAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
public class DappTransferTransactionDetailPresenter extends DappTransferTransactionDetailContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getAccount(final String str) {
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        ((DappTransferTransactionDetailContract.View) this.mView).runOnThreeThread(new OnBackground() {
            @Override
            public final void doOnBackground() {
                lambda$getAccount$0(str);
            }
        });
    }

    public void lambda$getAccount$0(String str) {
        try {
            Protocol.Account account = getAccount(StringTronUtil.decode58Check(str));
            if (account != null && account.toString().length() != 0) {
                ((DappTransferTransactionDetailContract.View) this.mView).bindDataToResourceUI(1);
            }
            ((DappTransferTransactionDetailContract.View) this.mView).bindDataToResourceUI(0);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            ((DappTransferTransactionDetailContract.View) this.mView).bindDataToResourceUI(1);
        }
    }

    private Protocol.Account getAccount(byte[] bArr) throws ConnectErrorException {
        try {
            return TronAPI.queryAccount(bArr, false);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            throw new ConnectErrorException(e.getMessage());
        }
    }
}
