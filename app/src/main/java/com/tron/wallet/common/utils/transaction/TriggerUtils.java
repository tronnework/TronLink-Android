package com.tron.wallet.common.utils.transaction;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.addassets2.bean.TokenActionType;
import com.tron.wallet.common.bean.ChainBean;
import com.tron.wallet.common.bean.DecimalsBean;
import com.tron.wallet.common.bean.token.TokenBean;
import com.tron.wallet.common.utils.BigDecimalUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bouncycastle.util.encoders.Hex;
import org.tron.api.GrpcAPI;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.common.utils.abi.AbiUtil;
import org.tron.common.utils.abi.CancelException;
import org.tron.common.utils.abi.EncodingException;
import org.tron.net.CipherException;
import org.tron.net.input.TriggerContractRequest;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.TriggerData;
public class TriggerUtils {
    private static Map<String, BigInteger> scalingFactorMap = new HashMap();

    public static SmartContractOuterClass.SmartContract.ABI getABI(byte[] bArr) {
        try {
            return TronAPI.getContract(bArr).getAbi();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static Long getWithdrawFee() throws EncodingException, CancelException, CipherException, IOException {
        TriggerData parseConstantDataByFun;
        String str;
        List<ChainBean> allChainJson = SpAPI.THIS.getAllChainJson();
        ChainBean chainBean = null;
        if (allChainJson != null && allChainJson.size() > 0) {
            for (int i = 0; i < allChainJson.size(); i++) {
                if (!allChainJson.get(i).isMainChain) {
                    chainBean = allChainJson.get(i);
                }
            }
        }
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("withdrawFee()");
        triggerContractRequest.setContractAddrStr(chainBean.sideChainContract);
        triggerContractRequest.setHex(false);
        triggerContractRequest.setOwer(StringTronUtil.decodeFromBase58Check("TRXqSdVE45RUPWcYWcGPAvxYEq3QSM8Yes"));
        long j = 0;
        triggerContractRequest.setTokenCallValue(0L);
        String triggerCallConstantStr = TronAPI.triggerCallConstantStr(triggerContractRequest);
        if (!StringTronUtil.isEmpty(triggerCallConstantStr) && (parseConstantDataByFun = TransactionDataUtils.getInstance().parseConstantDataByFun(ByteArray.fromHexString(triggerCallConstantStr), "withdrawFee(uint256)")) != null && !parseConstantDataByFun.getParameterMap().isEmpty() && (str = parseConstantDataByFun.getParameterMap().get("0")) != null) {
            j = Long.valueOf(str).longValue() / 1000000;
        }
        return Long.valueOf(j);
    }

    public static long getDecimals(String str, String str2) throws EncodingException {
        long j = 6;
        if (StringTronUtil.isEmpty(str, str2)) {
            return 6L;
        }
        List<DecimalsBean> list = SpAPI.THIS.get20Decimals();
        if (list != null && list.size() != 0) {
            for (DecimalsBean decimalsBean : list) {
                if (decimalsBean.getTokenAddress().equals(str)) {
                    return decimalsBean.getDecimals();
                }
            }
        }
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("decimals()");
        triggerContractRequest.setContractAddrStr(str);
        triggerContractRequest.setHex(false);
        try {
            j = Long.valueOf(TronAPI.triggerCallConstantStr(triggerContractRequest)).longValue();
            if (list == null) {
                list = new ArrayList<>();
            }
            DecimalsBean decimalsBean2 = new DecimalsBean();
            decimalsBean2.setDecimals(j);
            decimalsBean2.setShieldAddress(str2);
            decimalsBean2.setTokenAddress(str);
            list.add(decimalsBean2);
            SpAPI.THIS.set20Decimals(list);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return j;
    }

    public static int parseFuncToTokenActionType(Protocol.Transaction transaction, String str, TokenBean tokenBean) throws IllegalAccessException, InvalidProtocolBufferException {
        if (transaction == null || transaction.getRawData() == null || transaction.getRawData().getContractCount() < 1 || transaction.getRawData().getContract(0) == null || transaction.getRawData().getContract(0).getType() != Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
            throw new IllegalAccessException("Transaction Error || Not TriggerSmartContract");
        }
        SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(transaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
        byte[] byteArray = triggerSmartContract.getData().toByteArray();
        String hexString = Hex.toHexString(triggerSmartContract.getContractAddress().toByteArray());
        if (!StringTronUtil.isEmpty(str)) {
            if (TransactionDataUtils.transferMethod.equals(str)) {
                return 0;
            }
            if (TransactionDataUtils.approveMethod.equals(str)) {
                if (tokenBean != null) {
                    int type = tokenBean.getType();
                    if (type != 2) {
                        if (type != 5) {
                            return TokenActionType.APPROVE_PROBABLY;
                        }
                        return 3;
                    }
                    return 1;
                }
                String entryName = TransactionDataUtils.getInstance().getEntryName(getABI(StringTronUtil.decodeFromBase58Check(hexString)), byteArray, 1);
                return entryName == null ? TokenActionType.APPROVE_PROBABLY : "tokenId".equals(entryName) ? 3 : 1;
            } else if (TransactionDataUtils.increaseApprovalMethod.equals(str)) {
                return 4;
            } else {
                if (TransactionDataUtils.transferFromMethod.equals(str)) {
                    if (tokenBean != null) {
                        int type2 = tokenBean.getType();
                        if (type2 != 2) {
                            if (type2 != 5) {
                                return TokenActionType.TRANSFER_FROM_PROBABLY;
                            }
                            return 2;
                        }
                        return 0;
                    }
                    String entryName2 = TransactionDataUtils.getInstance().getEntryName(getABI(StringTronUtil.decodeFromBase58Check(hexString)), byteArray, 2);
                    return (entryName2 == null || "tokenId".equals(entryName2)) ? 2 : 0;
                }
            }
        } else {
            String hexString2 = ByteArray.toHexString(TransactionDataUtils.getInstance().getSelector(byteArray));
            if (StringTronUtil.isEmpty(hexString2)) {
                return -1;
            }
            if (TransactionDataUtils.TransferSha3.equals(hexString2)) {
                return 0;
            }
            if (TransactionDataUtils.ApproveSha3.equals(hexString2)) {
                if (tokenBean != null) {
                    int type3 = tokenBean.getType();
                    if (type3 != 2) {
                        if (type3 != 5) {
                            return TokenActionType.APPROVE_PROBABLY;
                        }
                        return 3;
                    }
                    return 1;
                }
                return TokenActionType.APPROVE_PROBABLY;
            } else if (TransactionDataUtils.IncreaseApprovalSha3.equals(hexString2)) {
                return TokenActionType.INCREASE_APPROVE_PROBABLY;
            } else {
                if (TransactionDataUtils.TransferFromSha3.equals(hexString2)) {
                    if (tokenBean != null) {
                        int type4 = tokenBean.getType();
                        if (type4 != 2) {
                            if (type4 != 5) {
                                return TokenActionType.TRANSFER_FROM_PROBABLY;
                            }
                            return 2;
                        }
                        return 0;
                    }
                    return TokenActionType.TRANSFER_FROM_PROBABLY;
                }
            }
        }
        return -1;
    }

    public static TriggerData parserDataByProbably(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, int i) {
        TriggerData triggerData = new TriggerData();
        String str = (i == 990 || i == 1 || i == 3) ? TransactionDataUtils.approveMethod : i == 993 ? TransactionDataUtils.increaseApprovalMethod : i == 0 ? TransactionDataUtils.transferMethod : (i == 992 || i == 2) ? TransactionDataUtils.transferFromMethod : "";
        triggerData.setMethod(str);
        triggerData.setParameterMap(TransactionDataUtils.getInstance().getParameterByFun(triggerSmartContract.getData().toByteArray(), str));
        return triggerData;
    }

    public List<String> getReserveValues(String str, String str2, String str3) {
        if (StringTronUtil.isEmpty(str, str2, str3)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("T9yD14Nj9j7xAB4dbGeiX9h8unkKHxuWwb");
        arrayList.add(str);
        String str4 = str2 + "," + GsonUtils.toGsonString(arrayList);
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("getBalance(address,address[])");
        triggerContractRequest.setArgsStr(str4);
        triggerContractRequest.setContractAddrStr(str3);
        triggerContractRequest.setHex(false);
        triggerContractRequest.setOwer(StringTronUtil.decodeFromBase58Check(WalletUtils.getSelectedWallet().getAddress()));
        ArrayList arrayList2 = new ArrayList();
        try {
            String triggerCallConstantStr = TronAPI.triggerCallConstantStr(triggerContractRequest);
            TriggerData parseConstantDataByFun = !StringTronUtil.isEmpty(triggerCallConstantStr) ? TransactionDataUtils.getInstance().parseConstantDataByFun(ByteArray.fromHexString(triggerCallConstantStr), "getBalance(uint256[])") : null;
            Map<String, String> parameterMap = parseConstantDataByFun != null ? parseConstantDataByFun.getParameterMap() : null;
            if (parameterMap != null && parameterMap.size() > 2) {
                String str5 = parameterMap.get(String.valueOf(2));
                if (!StringTronUtil.isEmpty(str5)) {
                    arrayList2.add(str5);
                }
                String str6 = parameterMap.get(String.valueOf(3));
                if (!StringTronUtil.isEmpty(str6)) {
                    arrayList2.add(str6);
                }
            }
        } catch (EncodingException e) {
            LogUtils.e(e);
        }
        return arrayList2;
    }

    public static GrpcAPI.TransactionExtention depositTrx(byte[] bArr, byte[] bArr2, long j, long j2) throws CipherException, IOException, CancelException, EncodingException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("depositTRX()");
        triggerContractRequest.setArgsStr("");
        triggerContractRequest.setFeeLimit(j2);
        triggerContractRequest.setCallValue(j);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setContractAddrStr(StringTronUtil.encode58Check(bArr2));
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod("depositTRX()", triggerContract2);
        return triggerContract2;
    }

    public static GrpcAPI.TransactionExtention depositTrc10(byte[] bArr, byte[] bArr2, String str, long j, long j2, long j3) throws CipherException, IOException, CancelException, EncodingException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("depositTRC10(uint64,uint64)");
        triggerContractRequest.setArgsStr(str + "," + j);
        triggerContractRequest.setFeeLimit(j3);
        triggerContractRequest.setTokenCallValue(j);
        triggerContractRequest.setCallValue(j2);
        triggerContractRequest.setTokenId(str);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setContractAddrStr(StringTronUtil.encode58Check(bArr2));
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod("depositTRC10(uint64,uint64)", triggerContract2);
        return triggerContract2;
    }

    public static GrpcAPI.TransactionExtention depositTrc20(byte[] bArr, String str, byte[] bArr2, String str2, long j, long j2) throws EncodingException, CancelException, CipherException, IOException {
        String str3 = str + "," + str2;
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr(TransactionDataUtils.approveMethod);
        triggerContractRequest.setArgsStr(str3);
        triggerContractRequest.setFeeLimit(j2);
        triggerContractRequest.setCallValue(j);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setContractAddrStr(StringTronUtil.encode58Check(bArr2));
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod(TransactionDataUtils.approveMethod, triggerContract2);
        return triggerContract2;
    }

    public static GrpcAPI.TransactionExtention depositTrc20_2(byte[] bArr, byte[] bArr2, String str, String str2, long j, long j2) throws EncodingException, CancelException, CipherException, IOException {
        String str3 = str + "," + str2;
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("depositTRC20(address,uint64)");
        triggerContractRequest.setArgsStr(str3);
        triggerContractRequest.setFeeLimit(j2);
        triggerContractRequest.setCallValue(j);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setContractAddrStr(StringTronUtil.encode58Check(bArr2));
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod("depositTRC20(address,uint64)", triggerContract2);
        return triggerContract2;
    }

    public static GrpcAPI.TransactionExtention withdrawTrx(byte[] bArr, byte[] bArr2, long j, long j2) throws EncodingException, CipherException, IOException, CancelException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("withdrawTRX()");
        triggerContractRequest.setArgsStr("");
        triggerContractRequest.setFeeLimit(j2);
        triggerContractRequest.setCallValue(j);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setContractAddrStr(StringTronUtil.encode58Check(bArr2));
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod("withdrawTRX()", triggerContract2);
        return triggerContract2;
    }

    public static GrpcAPI.TransactionExtention withdrawTrc10(byte[] bArr, byte[] bArr2, String str, long j, long j2, long j3) throws EncodingException, CipherException, IOException, CancelException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("withdrawTRC10(uint256,uint256)");
        triggerContractRequest.setArgsStr(str + "," + j);
        triggerContractRequest.setFeeLimit(j3);
        triggerContractRequest.setCallValue(j2);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setTokenId(str);
        triggerContractRequest.setTokenCallValue(j);
        triggerContractRequest.setContractAddrStr(StringTronUtil.encode58Check(bArr2));
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod("withdrawTRC10(uint256,uint256)", triggerContract2);
        return triggerContract2;
    }

    public static GrpcAPI.TransactionExtention withdrawTrc20(byte[] bArr, String str, String str2, long j, long j2) throws EncodingException, CipherException, IOException, CancelException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("withdrawal(uint256)");
        triggerContractRequest.setArgsStr(str2);
        triggerContractRequest.setFeeLimit(j2);
        triggerContractRequest.setCallValue(j);
        triggerContractRequest.setOwer(bArr);
        triggerContractRequest.setContractAddrStr(str);
        GrpcAPI.TransactionExtention triggerContract2 = TronAPI.triggerContract2(triggerContractRequest);
        addRequestABIMethod("withdrawal(uint256)", triggerContract2);
        return triggerContract2;
    }

    public static void addRequestABIMethod(String str, GrpcAPI.TransactionExtention transactionExtention) {
        if (transactionExtention == null || !transactionExtention.hasResult() || transactionExtention.getTransaction().toString().length() <= 0) {
            return;
        }
        TronApplication.FUNCTIONSELECTOR_MAP.put(TransactionUtils.getTriggerHash(transactionExtention.getTransaction()), str);
    }

    public String queryDidAddress(String str) throws Exception {
        if (StringTronUtil.isEmpty(str)) {
            return null;
        }
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("queryAddress(string)");
        triggerContractRequest.setArgsStr(str);
        triggerContractRequest.setContractAddrStr("TTuRXtjadecZGGbVn8tzFD4UJKUHPXdVNC");
        triggerContractRequest.setHex(false);
        triggerContractRequest.setOwer(StringTronUtil.decodeFromBase58Check(WalletUtils.getSelectedWallet().getAddress()));
        String triggerCallConstantStr = TronAPI.triggerCallConstantStr(triggerContractRequest);
        TriggerData parseConstantDataByFun = !StringTronUtil.isEmpty(triggerCallConstantStr) ? TransactionDataUtils.getInstance().parseConstantDataByFun(ByteArray.fromHexString(triggerCallConstantStr), "queryAddress(address)") : null;
        if (parseConstantDataByFun != null) {
            return parseConstantDataByFun.getParameterMap().get(String.valueOf(0));
        }
        return null;
    }

    public static BigInteger getScalingFactor(String str) throws EncodingException {
        BigInteger bigInteger = scalingFactorMap.get(str);
        if (bigInteger == null || bigInteger.longValue() == 0) {
            TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
            triggerContractRequest.setMethodStr("scalingFactor()");
            triggerContractRequest.setContractAddrStr(str);
            triggerContractRequest.setHex(false);
            try {
                BigInteger bigInteger2 = new BigInteger(TronAPI.triggerCallConstantStr(triggerContractRequest), 16);
                scalingFactorMap.put(str, bigInteger2);
                return bigInteger2;
            } catch (Exception e) {
                LogUtils.e(e);
                return new BigInteger("1");
            }
        }
        return bigInteger;
    }

    public static String getRootAndPath(String str, long j) throws EncodingException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("getPath(uint256)");
        triggerContractRequest.setArgsStr("000000000000000000000000000000000000000000000000" + ByteArray.toHexString(ByteArray.fromLong(j)));
        triggerContractRequest.setHex(true);
        triggerContractRequest.setContractAddrStr(str);
        return TronAPI.triggerCallConstantStr(triggerContractRequest);
    }

    public static String getRootAndPath(String str, long j, long j2, long j3, int i) throws EncodingException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("getPath(uint256)");
        triggerContractRequest.setArgsStr("000000000000000000000000000000000000000000000000" + ByteArray.toHexString(ByteArray.fromLong(j)));
        triggerContractRequest.setHex(true);
        if (j2 > 0) {
            triggerContractRequest.setCallValue(j2);
        }
        if (j3 > 0) {
            triggerContractRequest.setTokenCallValue(j3);
        }
        if (i != 0) {
            triggerContractRequest.setTokenId(i + "");
        }
        triggerContractRequest.setContractAddrStr(str);
        return TronAPI.triggerCallConstantStr(triggerContractRequest);
    }

    public static String balanceOf(String str, String str2) throws Exception {
        if (StringTronUtil.isEmpty(str, str2)) {
            return null;
        }
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("balanceOf(address)");
        triggerContractRequest.setArgsStr(str2);
        triggerContractRequest.setContractAddrStr(str);
        triggerContractRequest.setHex(false);
        triggerContractRequest.setOwer(StringTronUtil.decodeFromBase58Check(WalletUtils.getSelectedWallet().getAddress()));
        String triggerCallConstantStr = TronAPI.triggerCallConstantStr(triggerContractRequest);
        return (StringTronUtil.isEmpty(triggerCallConstantStr) ? null : TransactionDataUtils.getInstance().parseConstantDataByFun(ByteArray.fromHexString(triggerCallConstantStr), "balanceOf(uint256)")).getParameterMap().get(String.valueOf(0));
    }

    public static boolean deepLinkCompareMethod(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, String str) {
        if (StringTronUtil.isEmpty(str) || triggerSmartContract == null) {
            return false;
        }
        byte[] bArr = new byte[4];
        System.arraycopy(Hash.sha3(str.getBytes()), 0, bArr, 0, 4);
        return Arrays.equals(bArr, TransactionDataUtils.getInstance().getSelector(triggerSmartContract.getData().toByteArray()));
    }

    public static boolean isDeepLinkTransfer(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, String str) {
        if (StringTronUtil.isEmpty(str) || triggerSmartContract == null) {
            return false;
        }
        String hexString = ByteArray.toHexString(TransactionDataUtils.getInstance().getSelector(triggerSmartContract.getData().toByteArray()));
        return TransactionDataUtils.TransferSha3.equals(hexString) || TransactionDataUtils.TransferFromSha3.equals(hexString);
    }

    public static Protocol.Transaction replaceApproveAmount(Protocol.Transaction transaction, String str, String str2) {
        try {
            SmartContractOuterClass.TriggerSmartContract triggerSmartContract = (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(transaction.getRawData().getContract(0), SmartContractOuterClass.TriggerSmartContract.class);
            byte[] byteArray = triggerSmartContract.getContractAddress().toByteArray();
            String str3 = str + "," + str2;
            byte[] byteArray2 = triggerSmartContract.getOwnerAddress().toByteArray();
            byte[] decode = Hex.decode(AbiUtil.parseMethod(TransactionDataUtils.approveMethod, str3, false));
            long callTokenValue = triggerSmartContract.getCallTokenValue();
            long tokenId = triggerSmartContract.getTokenId();
            SmartContractOuterClass.TriggerSmartContract.Builder triggerCallContract = TronAPI.triggerCallContract(byteArray2, byteArray, triggerSmartContract.getCallValue(), decode);
            if (callTokenValue != 0 && tokenId != 0) {
                triggerCallContract.setCallTokenValue(callTokenValue);
                triggerCallContract.setTokenId(tokenId);
            }
            return transaction.toBuilder().clearRawData().setRawData(transaction.toBuilder().getRawDataBuilder().clearContract().addContract(Protocol.Transaction.Contract.newBuilder().setType(Protocol.Transaction.Contract.ContractType.TriggerSmartContract).setParameter(Any.pack(triggerCallContract.build())))).build();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static boolean enoughBandwidthToCreateAccount(String str, Protocol.Transaction transaction) {
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(AppContextUtil.getContext(), str);
        if (accountRes != null && !"".equals(accountRes.toString()) && accountRes.getNetLimit() != 0) {
            if (accountRes.getNetLimit() - accountRes.getNetUsed() >= TransactionUtils.bandwidthCost(transaction)) {
                return true;
            }
        }
        return false;
    }

    public static boolean enoughBandwidthToCreateAccount(String str, long j) {
        GrpcAPI.AccountResourceMessage accountRes = WalletUtils.getAccountRes(AppContextUtil.getContext(), str);
        return (accountRes == null || "".equals(accountRes.toString()) || accountRes.getNetLimit() == 0 || accountRes.getNetLimit() - accountRes.getNetUsed() < j) ? false : true;
    }

    public static boolean enoughBandwidthToCreateAccount(Protocol.Transaction transaction) {
        return enoughBandwidthToCreateAccount(WalletUtils.getSelectedWallet().getWalletName(), transaction);
    }

    public static BigDecimal getApproveAmount(String str, String str2, String str3) {
        TriggerData parseConstantDataByFun;
        if (StringTronUtil.isEmpty(str2, str3)) {
            return BigDecimalUtils.toBigDecimal(0);
        }
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr("allowance(address,address)");
        triggerContractRequest.setArgsStr(str2 + "," + str3);
        triggerContractRequest.setContractAddrStr(str);
        triggerContractRequest.setHex(false);
        try {
            String triggerCallConstantStr = TronAPI.triggerCallConstantStr(triggerContractRequest);
            if (!StringTronUtil.isEmpty(triggerCallConstantStr) && (parseConstantDataByFun = TransactionDataUtils.getInstance().parseConstantDataByFun(ByteArray.fromHexString(triggerCallConstantStr), "allowance(uint256)")) != null && !parseConstantDataByFun.getParameterMap().isEmpty()) {
                String str4 = parseConstantDataByFun.getParameterMap().get("0");
                if (!StringTronUtil.isEmpty(str4)) {
                    return BigDecimalUtils.toBigDecimal(str4);
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return BigDecimalUtils.toBigDecimal(0);
    }

    public static boolean isTransferFrom(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        return TransactionDataUtils.TransferFromSha3.equals(ByteArray.toHexString(TransactionDataUtils.getInstance().getSelector(triggerSmartContract.getData().toByteArray())));
    }
}
