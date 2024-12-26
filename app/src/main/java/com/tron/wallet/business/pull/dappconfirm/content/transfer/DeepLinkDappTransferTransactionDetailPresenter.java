package com.tron.wallet.business.pull.dappconfirm.content.transfer;

import com.tron.tron_base.frame.interfaces.OnBackground;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.pull.dappconfirm.content.transfer.DeepLinkDappTransferTransactionDetailContract;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.net.rpc.TronAPI;
import org.tron.protos.Protocol;
import org.tron.walletserver.ConnectErrorException;
public class DeepLinkDappTransferTransactionDetailPresenter extends DeepLinkDappTransferTransactionDetailContract.Presenter {
    @Override
    protected void onStart() {
    }

    @Override
    public void getAccount(final String str) {
        if (StringTronUtil.isEmpty(str)) {
            return;
        }
        ((DeepLinkDappTransferTransactionDetailContract.View) this.mView).runOnThreeThread(new OnBackground() {
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
                ((DeepLinkDappTransferTransactionDetailContract.View) this.mView).bindDataToResourceUI(1);
            }
            ((DeepLinkDappTransferTransactionDetailContract.View) this.mView).bindDataToResourceUI(0);
        } catch (ConnectErrorException e) {
            LogUtils.e(e);
            ((DeepLinkDappTransferTransactionDetailContract.View) this.mView).bindDataToResourceUI(1);
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
