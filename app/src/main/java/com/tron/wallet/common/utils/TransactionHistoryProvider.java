package com.tron.wallet.common.utils;

import android.os.Handler;
import android.os.Looper;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.message.TransactionMessage;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.bean.token.TransactionHistoryBean;
import com.tron.wallet.common.config.M;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
public class TransactionHistoryProvider {
    private static final long BLOCK_TIMESTAMP_PENDING = 600000;
    private static Handler mTaskHandler;
    private static TransactionConfirmRunnable transactionConfirmRunnable;

    public static String getType(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 4 ? M.M_TRX : M.M_TRZ : M.M_TRC20 : M.M_TRC10 : M.M_TRX;
    }

    public static List<TransactionHistoryBean> getLocalTransaction(List<TransactionHistoryBean> list, String str, TokenBean tokenBean, String str2, String str3, boolean z) {
        if (list == null) {
            return list;
        }
        Map<String, TransactionHistoryBean> localData_ = getLocalData_();
        if (localData_.size() == 0) {
            return list;
        }
        removeWithDate(localData_, list, str, tokenBean, str2, str3, z);
        try {
            Collections.sort(list);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return list;
    }

    public static List<TransactionHistoryBean> getPendingTransactionByToken(String str, TokenBean tokenBean) {
        ArrayList arrayList = new ArrayList();
        Map<String, TransactionHistoryBean> localData_ = getLocalData_();
        if (localData_.size() > 0) {
            for (String str2 : localData_.keySet()) {
                try {
                    TransactionHistoryBean transactionHistoryBean = localData_.get(str2);
                    if (str.equals(transactionHistoryBean.from) && (SpAPI.THIS.getBlockTimeInterval() + System.currentTimeMillis()) - transactionHistoryBean.getBlock_timestamp() > BLOCK_TIMESTAMP_PENDING && transactionHistoryBean.getConfirmed() == -1 && transactionHistoryBean.getTokenType().equals(getType(tokenBean.getType())) && transactionHistoryBean.from.equals(WalletUtils.getSelectedPublicWallet().getAddress()) && ((transactionHistoryBean.getTrc20Id() != null && transactionHistoryBean.getTrc20Id().equals(tokenBean.getContractAddress())) || transactionHistoryBean.getTrc10id() == tokenBean.getTokenId().longValue() || M.M_TRX.equals(transactionHistoryBean.getTokenType()))) {
                        transactionHistoryBean.setConfirmed(-1);
                        arrayList.add(transactionHistoryBean);
                    }
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
        }
        return arrayList;
    }

    private static void removeWithDate(Map<String, TransactionHistoryBean> map, List<TransactionHistoryBean> list, String str, TokenBean tokenBean, String str2, String str3, boolean z) {
        List<TransactionHistoryBean> list2;
        TransactionHistoryBean transactionHistoryBean;
        Map<String, TransactionHistoryBean> map2 = map;
        List<TransactionHistoryBean> list3 = list;
        String str4 = str2;
        if (list3 == null || map2 == null) {
            return;
        }
        int i = -1;
        if (list.size() == 0) {
            for (String str5 : map.keySet()) {
                try {
                    TransactionHistoryBean transactionHistoryBean2 = map2.get(str5);
                    if (transactionHistoryBean2.getTokenType().equals(getType(tokenBean.getType())) && transactionHistoryBean2.from.equals(WalletUtils.getSelectedPublicWallet().getAddress()) && (transactionHistoryBean2.getTrc20Id().equals(tokenBean.getContractAddress()) || transactionHistoryBean2.getTrc10id() == tokenBean.getTokenId().longValue() || transactionHistoryBean2.getTokenType().equals(M.M_TRX))) {
                        transactionHistoryBean2.setConfirmed(-1);
                        if (z) {
                            try {
                                if (M.M_TRX.equals(str)) {
                                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.mul_(BigDecimalUtils.div(new BigDecimal(transactionHistoryBean2.getAmount()), new BigDecimal(Math.pow(10.0d, 6.0d))), tokenBean.getUsdPrice()), new BigDecimal(TronConfig.filterSmallValue))) {
                                        list3.add(transactionHistoryBean2);
                                    }
                                } else if (BigDecimalUtils.MoreThan(BigDecimalUtils.mul_(BigDecimalUtils.div(new BigDecimal(transactionHistoryBean2.getAmount()), new BigDecimal(Math.pow(10.0d, tokenBean.getPrecision()))), tokenBean.getUsdPrice()), new BigDecimal(TronConfig.filterSmallValue))) {
                                    list3.add(transactionHistoryBean2);
                                }
                            } catch (Exception e) {
                                e = e;
                                LogUtils.e(e);
                            }
                        } else {
                            list3.add(transactionHistoryBean2);
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            return;
        }
        String str6 = str;
        TransactionHistoryBean transactionHistoryBean3 = list3.get(0);
        long block_timestamp = transactionHistoryBean3.getBlock_timestamp();
        for (String str7 : map.keySet()) {
            try {
                transactionHistoryBean = map2.get(str7);
            } catch (Exception e3) {
                e = e3;
                list2 = list3;
            }
            if (block_timestamp - transactionHistoryBean.getBlock_timestamp() > 120000 && transactionHistoryBean3.getConfirmed() != i) {
                SpAPI.THIS.removeTransactionLocalHistory(str7);
            } else if (str3.equals(transactionHistoryBean.from)) {
                if ((SpAPI.THIS.getBlockTimeInterval() + System.currentTimeMillis()) - transactionHistoryBean.getBlock_timestamp() <= BLOCK_TIMESTAMP_PENDING || transactionHistoryBean.getConfirmed() != i) {
                    int i2 = 0;
                    boolean z2 = false;
                    boolean z3 = false;
                    while (true) {
                        try {
                            if (i2 >= list.size()) {
                                break;
                            }
                            try {
                                TransactionHistoryBean transactionHistoryBean4 = list3.get(i2);
                                if (transactionHistoryBean4 != null) {
                                    if (str6 == null) {
                                        str6 = "";
                                    }
                                    if (str7.equals(transactionHistoryBean4.getHash())) {
                                        try {
                                            if (transactionHistoryBean4.getConfirmed() != -1) {
                                                SpAPI.THIS.removeTransactionLocalHistory(str7);
                                                break;
                                            }
                                        } catch (Exception e4) {
                                            e = e4;
                                            list2 = list3;
                                            LogUtils.e(e);
                                            SentryUtil.captureException(e);
                                            str4 = str2;
                                            list3 = list2;
                                            i = -1;
                                            map2 = map;
                                        }
                                    }
                                    if (transactionHistoryBean4.getHash().equals(transactionHistoryBean.getHash())) {
                                        z3 = true;
                                    } else if (str6.equals(transactionHistoryBean.getTokenType())) {
                                        if (!M.M_TRX.equals(str6)) {
                                            if (!str4.equals(transactionHistoryBean.getTrc10id() + "") && !str4.equals(transactionHistoryBean.getTrc20Id())) {
                                            }
                                        }
                                        if (i2 == list.size() - 1) {
                                            z2 = true;
                                        }
                                    }
                                }
                                i2++;
                                list3 = list;
                            } catch (Exception e5) {
                                e = e5;
                                list2 = list;
                                LogUtils.e(e);
                                SentryUtil.captureException(e);
                                str4 = str2;
                                list3 = list2;
                                i = -1;
                                map2 = map;
                            }
                        } catch (Exception e6) {
                            e = e6;
                        }
                    }
                    if (z2 && !z3) {
                        if (z) {
                            if (M.M_TRX.equals(str6)) {
                                try {
                                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.mul_(BigDecimalUtils.div(new BigDecimal(transactionHistoryBean.getAmount()), new BigDecimal(Math.pow(10.0d, 6.0d))), tokenBean.getUsdPrice()), new BigDecimal(TronConfig.filterSmallValue))) {
                                        list2 = list;
                                        try {
                                            list2.add(transactionHistoryBean);
                                        } catch (Exception e7) {
                                            e = e7;
                                            LogUtils.e(e);
                                            SentryUtil.captureException(e);
                                            str4 = str2;
                                            list3 = list2;
                                            i = -1;
                                            map2 = map;
                                        }
                                    }
                                } catch (Exception e8) {
                                    e = e8;
                                    list2 = list;
                                }
                            } else {
                                list2 = list;
                                try {
                                    if (BigDecimalUtils.MoreThan(BigDecimalUtils.mul_(BigDecimalUtils.div(new BigDecimal(transactionHistoryBean.getAmount()), new BigDecimal(Math.pow(10.0d, tokenBean.getPrecision()))), tokenBean.getUsdPrice()), new BigDecimal(TronConfig.filterSmallValue))) {
                                        list2.add(transactionHistoryBean);
                                    }
                                } catch (Exception e9) {
                                    e = e9;
                                    LogUtils.e(e);
                                    SentryUtil.captureException(e);
                                    str4 = str2;
                                    list3 = list2;
                                    i = -1;
                                    map2 = map;
                                }
                            }
                        } else {
                            list2 = list;
                            list2.add(transactionHistoryBean);
                        }
                        str4 = str2;
                        list3 = list2;
                        i = -1;
                        map2 = map;
                    }
                    list2 = list;
                    str4 = str2;
                    list3 = list2;
                    i = -1;
                    map2 = map;
                } else {
                    SpAPI.THIS.removeTransactionLocalHistory(str7);
                }
            }
        }
    }

    public static void removeLocalTransaction(String str) {
        SpAPI.THIS.removeTransactionLocalHistory(str);
    }

    public static void addLocalTransaction(TokenBean tokenBean, Protocol.Transaction... transactionArr) {
        int i;
        long j;
        String encode58Check;
        String encode58Check2;
        String str;
        AssetIssueContractOuterClass.TransferAssetContract transferAssetContract;
        StringBuilder sb;
        String str2;
        Map<String, String> parameterMap;
        Protocol.Transaction[] transactionArr2 = transactionArr;
        if (transactionArr2 == null || transactionArr2.length == 0) {
            return;
        }
        int length = transactionArr2.length;
        int i2 = 0;
        int i3 = 0;
        long j2 = 0;
        while (i3 < length) {
            Protocol.Transaction transaction = transactionArr2[i3];
            if (transaction == null) {
                return;
            }
            String str3 = "";
            if ("".equals(transaction.toString()) || transaction.getRawData().getContractCount() < 1) {
                return;
            }
            try {
                String transactionHash = TransactionUtils.getTransactionHash(transaction);
                Protocol.Transaction.Contract contract = transaction.getRawData().getContract(i2);
                long refBlockNum = transaction.getRawData().getRefBlockNum();
                long timestamp = transaction.getRawData().getTimestamp();
                if (timestamp > j2) {
                    j2 = timestamp;
                }
                try {
                    TransactionHistoryBean transactionHistoryBean = new TransactionHistoryBean();
                    int i4 = fun1.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[contract.getType().ordinal()];
                    if (i4 == 1) {
                        i = length;
                        j = j2;
                        BalanceContract.TransferContract transferContract = (BalanceContract.TransferContract) TransactionUtils.unpackContract(contract, BalanceContract.TransferContract.class);
                        encode58Check = StringTronUtil.encode58Check(transferContract.getOwnerAddress().toByteArray());
                        encode58Check2 = StringTronUtil.encode58Check(transferContract.getToAddress().toByteArray());
                        str3 = transferContract.getAmount() + "";
                        transactionHistoryBean.setContract_type("TransferContract");
                        str = M.M_TRX;
                    } else if (i4 == 2) {
                        i = length;
                        try {
                            transferAssetContract = (AssetIssueContractOuterClass.TransferAssetContract) TransactionUtils.unpackContract(contract, AssetIssueContractOuterClass.TransferAssetContract.class);
                            encode58Check = StringTronUtil.encode58Check(transferAssetContract.getOwnerAddress().toByteArray());
                            encode58Check2 = StringTronUtil.encode58Check(transferAssetContract.getToAddress().toByteArray());
                            str = M.M_TRC10;
                            sb = new StringBuilder();
                            j = j2;
                        } catch (Exception e) {
                            e = e;
                            LogUtils.e(e);
                            i3++;
                            transactionArr2 = transactionArr;
                            length = i;
                            i2 = 0;
                        }
                        try {
                            sb.append(transferAssetContract.getAmount());
                            sb.append("");
                            str3 = sb.toString();
                            transactionHistoryBean.setContract_type("TransferAssetContract");
                            transactionHistoryBean.setTrc10id(Long.parseLong(new String(transferAssetContract.getAssetName().toByteArray())));
                        } catch (Exception e2) {
                            e = e2;
                            j2 = j;
                            LogUtils.e(e);
                            i3++;
                            transactionArr2 = transactionArr;
                            length = i;
                            i2 = 0;
                        }
                    } else if (i4 != 3) {
                        i = length;
                        j = j2;
                        encode58Check = "";
                        encode58Check2 = encode58Check;
                        str = encode58Check2;
                    } else {
                        SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(contract, SmartContractOuterClass.TriggerSmartContract.class);
                        String encode58Check3 = StringTronUtil.encode58Check(triggerSmartContract.getOwnerAddress().toByteArray());
                        TriggerData transferData = TransactionDataUtils.getInstance().getTransferData(triggerSmartContract);
                        transactionHistoryBean.setContract_type("TriggerSmartContract");
                        transactionHistoryBean.setEvent_type("Transfer");
                        if (transferData == null || transferData.getParameterMap().isEmpty() || (parameterMap = transferData.getParameterMap()) == null) {
                            i = length;
                        } else {
                            i = length;
                            if (parameterMap.size() > 1) {
                                try {
                                    str2 = parameterMap.get("1");
                                    str3 = parameterMap.get("0");
                                    String str4 = (tokenBean == null && tokenBean.getType() == 0) ? M.M_TRX : M.M_TRC20;
                                    transactionHistoryBean.setContract_address(tokenBean.contractAddress);
                                    transactionHistoryBean.setTrc20Id(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()));
                                    j = j2;
                                    encode58Check2 = str3;
                                    str3 = str2;
                                    str = str4;
                                    encode58Check = encode58Check3;
                                } catch (Exception e3) {
                                    e = e3;
                                    LogUtils.e(e);
                                    i3++;
                                    transactionArr2 = transactionArr;
                                    length = i;
                                    i2 = 0;
                                }
                            }
                        }
                        str2 = "";
                        if (tokenBean == null) {
                        }
                        transactionHistoryBean.setContract_address(tokenBean.contractAddress);
                        transactionHistoryBean.setTrc20Id(StringTronUtil.encode58Check(triggerSmartContract.getContractAddress().toByteArray()));
                        j = j2;
                        encode58Check2 = str3;
                        str3 = str2;
                        str = str4;
                        encode58Check = encode58Check3;
                    }
                    transactionHistoryBean.setConfirmed(-1);
                    transactionHistoryBean.setAmount(str3);
                    transactionHistoryBean.setDirection(1);
                    transactionHistoryBean.setBlock_timestamp(timestamp);
                    transactionHistoryBean.setHash(transactionHash);
                    transactionHistoryBean.setTo(encode58Check2);
                    transactionHistoryBean.setDecimals(tokenBean.getPrecision());
                    transactionHistoryBean.setFrom(encode58Check);
                    transactionHistoryBean.setBlock(refBlockNum);
                    transactionHistoryBean.setTokenType(str);
                    SpAPI.THIS.addTransactionLocalHistory(transactionHash, transactionHistoryBean);
                    j2 = j;
                } catch (Exception e4) {
                    e = e4;
                    i = length;
                }
            } catch (Exception e5) {
                e = e5;
                i = length;
            }
            i3++;
            transactionArr2 = transactionArr;
            length = i;
            i2 = 0;
        }
        if (j2 > 0) {
            SpAPI.THIS.setBlockTimeInterval(j2 - System.currentTimeMillis());
        }
    }

    static class fun1 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.TransferContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferAssetContract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static Map<String, TransactionHistoryBean> getLocalData_() {
        Map<String, TransactionHistoryBean> transactionLocalHistory = SpAPI.THIS.getTransactionLocalHistory();
        return transactionLocalHistory == null ? new HashMap() : transactionLocalHistory;
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
            Map localData_ = TransactionHistoryProvider.getLocalData_();
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
