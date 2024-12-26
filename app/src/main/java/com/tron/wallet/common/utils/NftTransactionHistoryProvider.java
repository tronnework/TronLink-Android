package com.tron.wallet.common.utils;

import android.os.Handler;
import android.os.Looper;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.addassets2.bean.nft.NftTransferOutput;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
public class NftTransactionHistoryProvider {
    private static final long BLOCK_TIMESTAMP_PENDING = 600000;
    private static Handler mTaskHandler;
    private static TransactionConfirmRunnable transactionConfirmRunnable;

    public static List<NftTransferOutput.NftTransfer> getLocalTransaction(List<NftTransferOutput.NftTransfer> list, String str, String str2, String str3) {
        Map<String, NftTransferOutput.NftTransfer> localData_ = getLocalData_();
        if (localData_.size() == 0) {
            return list;
        }
        removeWithDate(localData_, list, str, str2, str3);
        try {
            Collections.sort(list);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return list;
    }

    private static void removeWithDate(Map<String, NftTransferOutput.NftTransfer> map, List<NftTransferOutput.NftTransfer> list, String str, String str2, String str3) {
        NftTransferOutput.NftTransfer nftTransfer;
        Map<String, NftTransferOutput.NftTransfer> map2 = map;
        if (list == null || map2 == null) {
            return;
        }
        if (list.size() == 0) {
            for (String str4 : map.keySet()) {
                try {
                    NftTransferOutput.NftTransfer nftTransfer2 = map2.get(str4);
                    if (nftTransfer2.getContract_address().equals(str2) && nftTransfer2.getFrom_address().equals(WalletUtils.getSelectedPublicWallet().getAddress())) {
                        nftTransfer2.setConfirmed(false);
                        list.add(nftTransfer2);
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            return;
        }
        NftTransferOutput.NftTransfer nftTransfer3 = list.get(0);
        long block_ts = nftTransfer3.getBlock_ts();
        String str5 = str;
        for (String str6 : map.keySet()) {
            try {
                nftTransfer = map2.get(str6);
            } catch (Exception e2) {
                e = e2;
            }
            if (block_ts - nftTransfer.getBlock_ts() > 120000 && nftTransfer3.getConfirmed().booleanValue() && nftTransfer3.getNftConfirm() != -1) {
                SpAPI.THIS.removeNftTransactionLocalHistory(str6);
            } else {
                try {
                } catch (Exception e3) {
                    e = e3;
                    LogUtils.e(e);
                    SentryUtil.captureException(e);
                    map2 = map;
                }
                if (str3.equals(nftTransfer.getFrom_address())) {
                    if ((SpAPI.THIS.getBlockTimeInterval() + System.currentTimeMillis()) - nftTransfer.getBlock_ts() <= BLOCK_TIMESTAMP_PENDING || nftTransfer.getConfirmed().booleanValue()) {
                        int i = 0;
                        boolean z = false;
                        boolean z2 = false;
                        while (true) {
                            if (i >= list.size()) {
                                break;
                            }
                            NftTransferOutput.NftTransfer nftTransfer4 = list.get(i);
                            if (nftTransfer4 != null) {
                                if (str5 == null) {
                                    str5 = "";
                                }
                                if (nftTransfer4.getTransaction_id().equals(nftTransfer.getTransaction_id())) {
                                    z2 = true;
                                }
                                if (str6.equals(nftTransfer4.getTransaction_id()) && nftTransfer4.getConfirmed().booleanValue()) {
                                    SpAPI.THIS.removeNftTransactionLocalHistory(str6);
                                    break;
                                } else if (str2.equals(nftTransfer.getContract_address()) && i == list.size() - 1) {
                                    z = true;
                                }
                            }
                            i++;
                        }
                        if (z && !z2) {
                            list.add(nftTransfer);
                        }
                        map2 = map;
                    } else {
                        SpAPI.THIS.removeNftTransactionLocalHistory(str6);
                    }
                }
            }
        }
    }

    public static void removeLocalTransaction(String str) {
        SpAPI.THIS.removeNftTransactionLocalHistory(str);
    }

    public static void addLocalTransaction(Protocol.Transaction... transactionArr) {
        String transactionHash;
        NftTransferOutput.NftTransfer nftTransfer;
        String str;
        String str2;
        String str3;
        String str4;
        Map<String, String> parameterMap;
        Protocol.Transaction[] transactionArr2 = transactionArr;
        if (transactionArr2 == null || transactionArr2.length == 0) {
            return;
        }
        int length = transactionArr2.length;
        int i = 0;
        int i2 = 0;
        long j = 0;
        while (i2 < length) {
            Protocol.Transaction transaction = transactionArr2[i2];
            if (transaction == null) {
                return;
            }
            String str5 = "";
            if ("".equals(transaction.toString()) || transaction.getRawData().getContractCount() < 1) {
                return;
            }
            try {
                transactionHash = TransactionUtils.getTransactionHash(transaction);
                Protocol.Transaction.Contract contract = transaction.getRawData().getContract(i);
                long refBlockNum = transaction.getRawData().getRefBlockNum();
                long timestamp = transaction.getRawData().getTimestamp();
                if (timestamp > j) {
                    j = timestamp;
                }
                nftTransfer = new NftTransferOutput.NftTransfer();
                if (fun1.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[contract.getType().ordinal()] != 1) {
                    str4 = "";
                    str2 = str4;
                } else {
                    SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(contract, SmartContractOuterClass.TriggerSmartContract.class);
                    String encode58Check = StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray());
                    TriggerData transferNFTData = TransactionDataUtils.getInstance().getTransferNFTData(triggerSmartContract);
                    if (transferNFTData == null || transferNFTData.getParameterMap().isEmpty() || (parameterMap = transferNFTData.getParameterMap()) == null || parameterMap.size() <= 2) {
                        str = "";
                        str2 = str;
                        str3 = encode58Check;
                    } else {
                        str2 = parameterMap.get("1");
                        String str6 = parameterMap.get("2");
                        str3 = parameterMap.get("0");
                        str = str6;
                    }
                    nftTransfer.setContract_address(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()));
                    String str7 = str3;
                    str5 = str;
                    str4 = str7;
                }
                nftTransfer.setNftConfirm(-1);
                nftTransfer.setQuant(str5);
                nftTransfer.setBlock_ts(timestamp);
                nftTransfer.setTransaction_id(transactionHash);
                nftTransfer.setTo_address(str2);
                nftTransfer.setFrom_address(str4);
                nftTransfer.setBlock(refBlockNum);
            } catch (Exception e) {
                e = e;
            }
            try {
                nftTransfer.setRevert(false);
                SpAPI.THIS.addNftTransactionLocalHistory(transactionHash, nftTransfer);
            } catch (Exception e2) {
                e = e2;
                LogUtils.e(e);
                i2++;
                transactionArr2 = transactionArr;
                i = 0;
            }
            i2++;
            transactionArr2 = transactionArr;
            i = 0;
        }
        if (j > 0) {
            SpAPI.THIS.setBlockTimeInterval(j - System.currentTimeMillis());
        }
    }

    static class fun1 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static Map<String, NftTransferOutput.NftTransfer> getLocalData_() {
        Map<String, NftTransferOutput.NftTransfer> nftTransactionLocalHistory = SpAPI.THIS.getNftTransactionLocalHistory();
        return nftTransactionLocalHistory == null ? new HashMap() : nftTransactionLocalHistory;
    }

    public static void startRunnableDefault() {
        if (mTaskHandler == null) {
            mTaskHandler = new Handler(Looper.getMainLooper());
        }
        if (transactionConfirmRunnable == null) {
            transactionConfirmRunnable = new TransactionConfirmRunnable();
        }
        mTaskHandler.postDelayed(transactionConfirmRunnable, 10000L);
    }

    public static void startRunnable(long j) {
        if (mTaskHandler == null) {
            mTaskHandler = new Handler(Looper.getMainLooper());
        }
        if (transactionConfirmRunnable == null) {
            transactionConfirmRunnable = new TransactionConfirmRunnable();
        }
        mTaskHandler.postDelayed(transactionConfirmRunnable, j);
    }

    public static void stopRunnable() {
        mTaskHandler.removeCallbacks(transactionConfirmRunnable);
    }

    private static class TransactionConfirmRunnable implements Runnable {
        private TransactionConfirmRunnable() {
        }

        @Override
        public void run() {
            Map localData_ = NftTransactionHistoryProvider.getLocalData_();
            if (localData_ == null || localData_.size() == 0) {
                return;
            }
            for (String str : localData_.keySet()) {
                try {
                    Protocol.TransactionInfo transactionInfoById = TronAPI.getTransactionInfoById(str);
                    if (transactionInfoById != null && TransactionMessage.TYPE_SUCCESS.equals(transactionInfoById.getResult().name())) {
                        localData_.remove(str);
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }
    }
}
