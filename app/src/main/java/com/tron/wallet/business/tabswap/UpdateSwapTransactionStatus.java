package com.tron.wallet.business.tabswap;

import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.Controller.JustSwapTransactionController;
import com.tron.wallet.db.bean.JustSwapBean;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.util.List;
import org.tron.protos.Protocol;
public class UpdateSwapTransactionStatus {
    public void start() {
        try {
            String address = WalletUtils.getSelectedPublicWallet().getAddress();
            if (address == null) {
                return;
            }
            for (JustSwapBean justSwapBean : JustSwapTransactionController.getInstance().getPendingStateTransactions(address)) {
                try {
                    Protocol.TransactionInfo transactionInfoById = TronAPI.getTransactionInfoById(justSwapBean.getHash());
                    LogUtils.d("SwapPresenter", "getTransactionInfoById");
                    if (transactionInfoById != null) {
                        LogUtils.d("SwapPresenter", "getTransactionInfoById  " + justSwapBean.getHash() + "  " + transactionInfoById.getResult().getNumber());
                        if (transactionInfoById.getResult() != null && transactionInfoById.getResult() != Protocol.TransactionInfo.code.UNRECOGNIZED) {
                            if (transactionInfoById.getResult() == Protocol.TransactionInfo.code.SUCESS) {
                                List<ByteString> contractResultList = transactionInfoById.getContractResultList();
                                LogUtils.d("SwapPresenter", "info.getLogCount()" + transactionInfoById.getLogCount() + "  getContractResultCount:  " + transactionInfoById.getContractResultCount());
                                if (transactionInfoById.getContractResultCount() > 0) {
                                    LogUtils.d("SwapPresenter", "" + new String(contractResultList.get(0).toByteArray()));
                                    LogUtils.d("SwapPresenter", "" + new String(contractResultList.get(transactionInfoById.getContractResultCount() - 1).toByteArray()));
                                    justSwapBean.setStatus(1);
                                } else {
                                    LogUtils.d("SwapPresenter", "  getContractResultCount:  " + transactionInfoById.getContractResultCount());
                                }
                            } else if (transactionInfoById.getResult() == Protocol.TransactionInfo.code.FAILED) {
                                justSwapBean.setStatus(2);
                            }
                            justSwapBean.setBlockNum(transactionInfoById.getBlockNumber());
                            if (transactionInfoById.getBlockTimeStamp() != 0) {
                                justSwapBean.setTimestamp(transactionInfoById.getBlockTimeStamp());
                            }
                            JustSwapTransactionController.getInstance().update(justSwapBean);
                            new RxManager().post(Event.SWAP_TRANSACTION_UPDATE, "");
                        }
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        } catch (Exception unused) {
        }
    }
}
