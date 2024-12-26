package com.tron.wallet.net.rpc;

import android.text.TextUtils;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.config.TronSetting;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.transaction.TriggerUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.util.encoders.Hex;
import org.tron.api.GrpcAPI;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.ByteUtil;
import org.tron.common.utils.TransactionDataUtils;
import org.tron.common.utils.TransactionUtils;
import org.tron.common.utils.abi.AbiUtil;
import org.tron.common.utils.abi.EncodingException;
import org.tron.config.Parameter;
import org.tron.net.exceptions.ZTronException;
import org.tron.net.input.TriggerContractRequest;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AccountContract;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.Common;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.protos.contract.WitnessContract;
import org.tron.walletserver.ConnectErrorException;
public class TronAPI {
    public static long getFeeLimit() {
        return 225000000L;
    }

    public static Protocol.Account queryAccount(byte[] bArr, boolean z) throws ConnectErrorException {
        return IGrpcClient.THIS.getCli().queryAccount(bArr, z);
    }

    public static Protocol.Account queryAccount(String str, boolean z) throws ConnectErrorException {
        return IGrpcClient.THIS.getCli().queryAccount(StringTronUtil.decode58Check(str), z);
    }

    public static GrpcAPI.TransactionExtention createTransferAssetTransaction(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        AssetIssueContractOuterClass.TransferAssetContract createTransferAssetContract = createTransferAssetContract(bArr, bArr2, bArr3, j);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.TransferAssetContract, Any.pack(createTransferAssetContract));
        }
        return IGrpcClient.THIS.getCli().createTransferAssetTransaction2(createTransferAssetContract);
    }

    public static GrpcAPI.TransactionExtention createTransferAssetTransaction(AssetIssueContractOuterClass.TransferAssetContract transferAssetContract) {
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.TransferAssetContract, Any.pack(transferAssetContract));
        }
        return IGrpcClient.THIS.getCli().createTransferAssetTransaction2(transferAssetContract);
    }

    public static Protocol.Transaction createParticipateAssetIssueTransaction(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        return IGrpcClient.THIS.getCli().createParticipateAssetIssueTransaction(createParticipateAssetIssueContract(bArr, bArr2, bArr3, j));
    }

    public static Protocol.Transaction createUpdateAccountTransaction(byte[] bArr, String str) {
        return IGrpcClient.THIS.getCli().createTransaction(createAccountUpdateContract(ByteArray.fromString(str), bArr));
    }

    public static boolean broadcastTransaction(Protocol.Transaction transaction) throws ConnectErrorException {
        GrpcAPI.Return broadcastTransaction2 = broadcastTransaction2(transaction);
        if (TransactionUtils.validTransaction(transaction) && broadcastTransaction2 == null) {
            return false;
        }
        return broadcastTransaction2.getResult();
    }

    public static GrpcAPI.Return broadcastTransaction2(Protocol.Transaction transaction) throws ConnectErrorException {
        if (TransactionUtils.validTransaction(transaction)) {
            return IGrpcClient.THIS.getCli().broadcastTransaction(transaction);
        }
        return null;
    }

    public static GrpcAPI.TransactionSignWeight getTransactionSignWeight(Protocol.Transaction transaction) {
        return IGrpcClient.THIS.getCli().getTransactionSignWeight(transaction);
    }

    public static Protocol.Transaction createWitnessTransaction(byte[] bArr, byte[] bArr2) {
        return IGrpcClient.THIS.getCli().createWitness(createWitnessCreateContract(bArr, bArr2));
    }

    public static Protocol.Transaction createUpdateWitnessTransaction(byte[] bArr, byte[] bArr2) {
        return IGrpcClient.THIS.getCli().updateWitness(createWitnessUpdateContract(bArr, bArr2));
    }

    public static Protocol.Transaction createVoteWitnessTransaction(byte[] bArr, HashMap<String, String> hashMap) {
        return IGrpcClient.THIS.getCli().voteWitnessAccount(createVoteWitnessContract(bArr, hashMap));
    }

    public static GrpcAPI.TransactionExtention createVoteWitnessTransaction2(byte[] bArr, HashMap<String, String> hashMap) {
        WitnessContract.VoteWitnessContract createVoteWitnessContract = createVoteWitnessContract(bArr, hashMap);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.VoteWitnessContract, Any.pack(createVoteWitnessContract));
        }
        return IGrpcClient.THIS.getCli().voteWitnessAccount2(createVoteWitnessContract);
    }

    public static Protocol.Transaction createAssetIssueTransaction(AssetIssueContractOuterClass.AssetIssueContract assetIssueContract) {
        return IGrpcClient.THIS.getCli().createAssetIssue(assetIssueContract);
    }

    public static Protocol.Block getBlock(long j) {
        return IGrpcClient.THIS.getCli().getBlock(j);
    }

    public static Protocol.Transaction createTransaction4Transfer(BalanceContract.TransferContract transferContract) {
        return IGrpcClient.THIS.getCli().createTransaction(transferContract);
    }

    public static GrpcAPI.TransactionExtention createTransaction4Transfer2(BalanceContract.TransferContract transferContract) {
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.TransferContract, Any.pack(transferContract));
        }
        return IGrpcClient.THIS.getCli().createTransaction2(transferContract);
    }

    public static GrpcAPI.AssetIssueList getAssetIssueList() {
        return IGrpcClient.THIS.getCli().getAssetIssueList();
    }

    public static GrpcAPI.NodeList listNodes() throws ConnectErrorException {
        return IGrpcClient.THIS.getCli().listNodes();
    }

    public static GrpcAPI.DelegatedResourceList delegatedResourceList(String str, String str2) throws ConnectErrorException {
        return IGrpcClient.THIS.getCli().queryDgList(str, str2);
    }

    public static GrpcAPI.AssetIssueList getAssetIssueByAccount(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getAssetIssueByAccount(bArr);
    }

    public static GrpcAPI.AccountNetMessage getAccountNet(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getAccountNet(bArr);
    }

    public static GrpcAPI.AccountResourceMessage getAccountRes(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getAccountRes(bArr);
    }

    public static GrpcAPI.AccountResourceMessage getAccountRes(String str) {
        return IGrpcClient.THIS.getCli().getAccountRes(StringTronUtil.decode58Check(str));
    }

    public static AssetIssueContractOuterClass.AssetIssueContract getAssetIssueByName(String str) {
        return IGrpcClient.THIS.getCli().getAssetIssueByName(str);
    }

    public static GrpcAPI.NumberMessage getTotalTransaction() {
        return IGrpcClient.THIS.getCli().getTotalTransaction();
    }

    public static GrpcAPI.NumberMessage getNextMaintenanceTime() {
        return IGrpcClient.THIS.getCli().getNextMaintenanceTime();
    }

    public static GrpcAPI.TransactionList getTransactionsFromThis(byte[] bArr, int i, int i2) {
        return IGrpcClient.THIS.getCli().getTransactionsFromThis(bArr, i, i2);
    }

    public static GrpcAPI.TransactionList getTransactionsToThis(byte[] bArr, int i, int i2) {
        return IGrpcClient.THIS.getCli().getTransactionsToThis(bArr, i, i2);
    }

    public static Protocol.Transaction getTransactionById(String str) {
        return IGrpcClient.THIS.getCli().getTransactionById(str);
    }

    public static Protocol.TransactionInfo getTransactionInfoById(String str) {
        return IGrpcClient.THIS.getCli().getTransactionInfoById(str);
    }

    public static Protocol.TransactionInfo getTransactionInfoByIdSo(String str) {
        return IGrpcClient.THIS.getCli().getTransactionInfoByIdSo(str);
    }

    public static GrpcAPI.TransactionExtention createFreezeBalanceTransaction(byte[] bArr, long j, long j2, byte[] bArr2, Common.ResourceCode resourceCode) {
        BalanceContract.FreezeBalanceContract createFreezeBalanceContract = createFreezeBalanceContract(bArr, j, j2, bArr2, resourceCode);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.UnfreezeBalanceContract, Any.pack(createFreezeBalanceContract));
        }
        return IGrpcClient.THIS.getCli().createTransaction2(createFreezeBalanceContract);
    }

    public static GrpcAPI.TransactionExtention createUnfreezeBalanceTransaction(byte[] bArr, Common.ResourceCode resourceCode) {
        BalanceContract.UnfreezeBalanceContract createUnfreezeBalanceContract = createUnfreezeBalanceContract(bArr, resourceCode);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.UnfreezeBalanceContract, Any.pack(createUnfreezeBalanceContract));
        }
        return IGrpcClient.THIS.getCli().createTransaction2(createUnfreezeBalanceContract);
    }

    public static GrpcAPI.TransactionExtention createUnfreezeBalanceTransaction(byte[] bArr, byte[] bArr2, Common.ResourceCode resourceCode) {
        BalanceContract.UnfreezeBalanceContract createUnfreezeBalanceContract = createUnfreezeBalanceContract(bArr, bArr2, resourceCode);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.UnfreezeBalanceContract, Any.pack(createUnfreezeBalanceContract));
        }
        return IGrpcClient.THIS.getCli().createTransaction2(createUnfreezeBalanceContract);
    }

    public static GrpcAPI.TransactionExtention withdrawExpireUnfreeze(String str) {
        BalanceContract.WithdrawExpireUnfreezeContract createWithdrawExpireUnfreezeContract = createWithdrawExpireUnfreezeContract(str);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.WithdrawExpireUnfreezeContract, Any.pack(createWithdrawExpireUnfreezeContract));
        }
        return IGrpcClient.THIS.getCli().WithdrawExpireUnfreeze(createWithdrawExpireUnfreezeContract);
    }

    public static GrpcAPI.TransactionExtention delegateResource(String str, String str2, Common.ResourceCode resourceCode, long j, boolean z, long j2) {
        BalanceContract.DelegateResourceContract createDelegateResourceContract = createDelegateResourceContract(str, str2, resourceCode, j, z, j2);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.DelegateResourceContract, Any.pack(createDelegateResourceContract));
        }
        return IGrpcClient.THIS.getCli().delegateResource(createDelegateResourceContract);
    }

    public static GrpcAPI.TransactionExtention unDelegateResource(String str, String str2, Common.ResourceCode resourceCode, long j) {
        BalanceContract.UnDelegateResourceContract createUnDelegateResourceContract = createUnDelegateResourceContract(str, str2, resourceCode, j);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.UnDelegateResourceContract, Any.pack(createUnDelegateResourceContract));
        }
        return IGrpcClient.THIS.getCli().unDelegateResource(createUnDelegateResourceContract);
    }

    public static GrpcAPI.TransactionExtention freezeBalanceV2(String str, long j, Common.ResourceCode resourceCode) {
        BalanceContract.FreezeBalanceV2Contract createFreezeBalanceV2Contract = createFreezeBalanceV2Contract(str, j, resourceCode);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract, Any.pack(createFreezeBalanceV2Contract));
        }
        return IGrpcClient.THIS.getCli().freezeBalanceV2(createFreezeBalanceV2Contract);
    }

    public static GrpcAPI.TransactionExtention UnfreezeBalanceV2(String str, long j, Common.ResourceCode resourceCode) {
        BalanceContract.UnfreezeBalanceV2Contract createUnfreezeBalanceV2Contract = createUnfreezeBalanceV2Contract(str, j, resourceCode);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract, Any.pack(createUnfreezeBalanceV2Contract));
        }
        return IGrpcClient.THIS.getCli().unfreezeBalanceV2(createUnfreezeBalanceV2Contract);
    }

    public static GrpcAPI.GetAvailableUnfreezeCountResponseMessage getAvailableUnfreezeCount(String str) {
        return IGrpcClient.THIS.getCli().getAvailableUnfreezeCount(createGetAvailableUnfreezeCountRequestMessage(str));
    }

    public static GrpcAPI.CanDelegatedMaxSizeResponseMessage getCanDelegatedMaxSize(int i, String str) {
        GrpcAPI.CanDelegatedMaxSizeRequestMessage.Builder newBuilder = GrpcAPI.CanDelegatedMaxSizeRequestMessage.newBuilder();
        newBuilder.setType(i);
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        return IGrpcClient.THIS.getCli().getCanDelegatedMaxSize(newBuilder.build());
    }

    public static GrpcAPI.CanWithdrawUnfreezeAmountResponseMessage getCanWithdrawUnfreezeAmount(String str) {
        return IGrpcClient.THIS.getCli().getCanWithdrawUnfreezeAmount(createCanWithdrawUnfreezeAmountRequestMessage(0L, str));
    }

    public static GrpcAPI.TransactionExtention cancelAllUnfreezeV2(String str) {
        BalanceContract.CancelAllUnfreezeV2Contract createCancelAllUnfreezeV2Contract = createCancelAllUnfreezeV2Contract(str);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.CancelAllUnfreezeV2Contract, Any.pack(createCancelAllUnfreezeV2Contract));
        }
        return IGrpcClient.THIS.getCli().cancelAllUnfreezeV2(createCancelAllUnfreezeV2Contract);
    }

    public static Protocol.Block getBlockById(String str) {
        return IGrpcClient.THIS.getCli().getBlockById(str);
    }

    public static GrpcAPI.BlockList getBlockByLimitNext(long j, long j2) {
        return IGrpcClient.THIS.getCli().getBlockByLimitNext(j, j2);
    }

    public static GrpcAPI.BlockList getBlockByLatestNum(long j) {
        return IGrpcClient.THIS.getCli().getBlockByLatestNum(j);
    }

    public static SmartContractOuterClass.SmartContract getContract(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getContract(bArr);
    }

    public static GrpcAPI.DelegatedResourceList getDelegatedResource(byte[] bArr, byte[] bArr2) {
        return IGrpcClient.THIS.getCli().getDelegatedResource(bArr, bArr2);
    }

    public static GrpcAPI.DelegatedResourceList getDelegatedResourceV2(byte[] bArr, byte[] bArr2) {
        return IGrpcClient.THIS.getCli().getDelegatedResourceV2(bArr, bArr2);
    }

    public static Protocol.DelegatedResourceAccountIndex getDelegatedResourceAccountIndex(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getDelegatedResourceAccountIndex(bArr);
    }

    public static GrpcAPI.TransactionExtention triggerContract(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.TriggerSmartContract, Any.pack(triggerSmartContract));
        }
        return IGrpcClient.THIS.getCli().triggerContract(triggerSmartContract);
    }

    public static GrpcAPI.TransactionExtention triggerConstantContractOnline(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        return IGrpcClient.THIS.getCli().triggerConstantContract(triggerSmartContract);
    }

    private static GrpcAPI.TransactionExtention triggerContract2(byte[] bArr, byte[] bArr2, long j, byte[] bArr3, long j2, long j3, String str, boolean z) {
        GrpcAPI.TransactionExtention triggerContract;
        SmartContractOuterClass.TriggerSmartContract.Builder triggerCallContract = triggerCallContract(bArr, bArr2, j, bArr3);
        if (str != null && !StringTronUtil.isEmpty(str)) {
            triggerCallContract.setCallTokenValue(j3);
            triggerCallContract.setTokenId(Long.parseLong(str));
        }
        if (z) {
            triggerContract = triggerConstantContractOnline(triggerCallContract.build());
        } else {
            triggerContract = triggerContract(triggerCallContract.build());
        }
        if (triggerContract == null || !triggerContract.getResult().getResult()) {
            return null;
        }
        return TransactionUtils.checkTransactionEmpty(triggerContract) ? triggerContract : (triggerContract.getTransaction().getRetCount() == 0 || triggerContract.getConstantResult(0) == null || triggerContract.getResult() == null || !z) ? triggerContractTransactionExtention(j2, triggerContract) : triggerContract;
    }

    private static GrpcAPI.TransactionExtention triggerContractTransactionExtention(long j, GrpcAPI.TransactionExtention transactionExtention) {
        GrpcAPI.TransactionExtention.Builder newBuilder = GrpcAPI.TransactionExtention.newBuilder();
        Protocol.Transaction.Builder newBuilder2 = Protocol.Transaction.newBuilder();
        Protocol.Transaction.raw.Builder builder = transactionExtention.getTransaction().getRawData().toBuilder();
        builder.setFeeLimit(j);
        newBuilder2.setRawData(builder);
        if (transactionExtention.getTransaction().getSignatureCount() > 0) {
            for (int i = 0; i < transactionExtention.getTransaction().getSignatureCount(); i++) {
                newBuilder2.setSignature(i, transactionExtention.getTransaction().getSignature(i));
            }
        }
        newBuilder.setTransaction(newBuilder2);
        newBuilder.setResult(transactionExtention.getResult());
        newBuilder.setTxid(transactionExtention.getTxid());
        return newBuilder.build();
    }

    public static String triggerCallConstantStr(TriggerContractRequest triggerContractRequest) throws EncodingException {
        GrpcAPI.TransactionExtention triggerCallConstant = triggerCallConstant(triggerContractRequest);
        StringBuffer stringBuffer = new StringBuffer();
        if (triggerCallConstant != null && triggerCallConstant.hasResult() && triggerCallConstant.hasTransaction() && triggerCallConstant.getConstantResultCount() > 0) {
            if (triggerCallConstant.getResult().getMessage() != null && "REVERT opcode executed".equals(new String(triggerCallConstant.getResult().getMessage().toByteArray()))) {
                return "";
            }
            for (int i = 0; i < triggerCallConstant.getConstantResultCount(); i++) {
                stringBuffer.append(ByteArray.toHexString(triggerCallConstant.getConstantResult(i).toByteArray()));
            }
        }
        return stringBuffer.toString();
    }

    public static GrpcAPI.TransactionExtention triggerCallConstant(TriggerContractRequest triggerContractRequest) throws EncodingException {
        return triggerContract2(triggerContractRequest, true);
    }

    public static GrpcAPI.TransactionExtention triggerContract2(TriggerContractRequest triggerContractRequest) throws EncodingException {
        int i = Parameter.NetConstant.triggerType;
        return triggerContract2(triggerContractRequest, false);
    }

    public static GrpcAPI.TransactionExtention triggerContract2(TriggerContractRequest triggerContractRequest, boolean z) throws EncodingException {
        byte[] decode;
        String contractAddrStr = triggerContractRequest.getContractAddrStr();
        String methodStr = triggerContractRequest.getMethodStr();
        String argsStr = triggerContractRequest.getArgsStr();
        byte[] ower = triggerContractRequest.getOwer();
        boolean isHex = triggerContractRequest.isHex();
        long feeLimit = triggerContractRequest.getFeeLimit();
        long callValue = triggerContractRequest.getCallValue();
        long tokenCallValue = triggerContractRequest.getTokenCallValue();
        String tokenId = triggerContractRequest.getTokenId();
        argsStr = (StringTronUtil.isEmpty(argsStr) || argsStr.equalsIgnoreCase("#")) ? "" : "";
        String str = (StringTronUtil.isEmpty(tokenId) || tokenId.equalsIgnoreCase("#")) ? "" : tokenId;
        if (triggerContractRequest.isAbiPro()) {
            String methodABI = triggerContractRequest.getMethodABI();
            String hexString = ByteArray.toHexString(AbiUtil.encodeInput(triggerContractRequest.getMethodStr(), triggerContractRequest.getArgsStr()));
            decode = ByteArray.fromHexString(methodABI + hexString);
        } else {
            decode = Hex.decode(AbiUtil.parseMethod(methodStr, argsStr, isHex));
        }
        return triggerContract2(ower, StringTronUtil.decodeFromBase58Check(contractAddrStr), callValue, decode, feeLimit, tokenCallValue, str, z);
    }

    public static SmartContractOuterClass.TriggerSmartContract.Builder triggerCallContract(byte[] bArr, byte[] bArr2, long j, byte[] bArr3) {
        SmartContractOuterClass.TriggerSmartContract.Builder newBuilder = SmartContractOuterClass.TriggerSmartContract.newBuilder();
        if (!ByteUtil.isNullOrZeroArray(bArr)) {
            newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        }
        if (!ByteUtil.isNullOrZeroArray(bArr2)) {
            newBuilder.setContractAddress(ByteString.copyFrom(bArr2));
        }
        if (!ByteUtil.isNullOrZeroArray(bArr3)) {
            newBuilder.setData(ByteString.copyFrom(bArr3));
        }
        newBuilder.setCallValue(j);
        return newBuilder;
    }

    public static SmartContractOuterClass.TriggerSmartContract.Builder triggerCallContractWithTokenId(byte[] bArr, byte[] bArr2, long j, byte[] bArr3, long j2) {
        SmartContractOuterClass.TriggerSmartContract.Builder newBuilder = SmartContractOuterClass.TriggerSmartContract.newBuilder();
        if (!ByteUtil.isNullOrZeroArray(bArr)) {
            newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        }
        if (!ByteUtil.isNullOrZeroArray(bArr2)) {
            newBuilder.setContractAddress(ByteString.copyFrom(bArr2));
        }
        if (!ByteUtil.isNullOrZeroArray(bArr3)) {
            newBuilder.setData(ByteString.copyFrom(bArr3));
        }
        newBuilder.setCallValue(j);
        newBuilder.setTokenId(j2);
        return newBuilder;
    }

    public static String encodeMintParamsToHexString(GrpcAPI.ShieldedTRC20Parameters shieldedTRC20Parameters, BigInteger bigInteger) {
        ShieldContract.ReceiveDescription receiveDescription = shieldedTRC20Parameters.getReceiveDescription(0);
        return ByteArray.toHexString(ByteUtil.merge(ByteUtil.bigIntegerToBytes(bigInteger, 32), receiveDescription.getNoteCommitment().toByteArray(), receiveDescription.getValueCommitment().toByteArray(), receiveDescription.getEpk().toByteArray(), receiveDescription.getZkproof().toByteArray(), shieldedTRC20Parameters.getBindingSignature().toByteArray(), receiveDescription.getCEnc().toByteArray(), receiveDescription.getCOut().toByteArray(), new byte[12]));
    }

    public static String encodeTransferParamsToHexString(GrpcAPI.ShieldedTRC20Parameters shieldedTRC20Parameters, List<String> list) {
        byte[] merge;
        byte[] bArr = new byte[0];
        byte[] bArr2 = new byte[0];
        byte[] bArr3 = new byte[0];
        List<ShieldContract.SpendDescription> spendDescriptionList = shieldedTRC20Parameters.getSpendDescriptionList();
        for (ShieldContract.SpendDescription spendDescription : spendDescriptionList) {
            bArr = ByteUtil.merge(bArr, spendDescription.getNullifier().toByteArray(), spendDescription.getAnchor().toByteArray(), spendDescription.getValueCommitment().toByteArray(), spendDescription.getRk().toByteArray(), spendDescription.getZkproof().toByteArray());
        }
        long size = spendDescriptionList.size();
        int i = (size > 1L ? 1 : (size == 1L ? 0 : -1));
        if (i < 0 || size > 2) {
            throw new IllegalArgumentException("invalid transfer input number");
        }
        if (i == 0) {
            merge = ByteArray.fromHexString(list.get(0));
        } else {
            merge = ByteUtil.merge(ByteArray.fromHexString(list.get(0)), ByteArray.fromHexString(list.get(1)));
        }
        byte[] longTo32Bytes = StringTronUtil.longTo32Bytes(192L);
        byte[] longTo32Bytes2 = StringTronUtil.longTo32Bytes(size);
        long j = 320 * size;
        byte[] longTo32Bytes3 = StringTronUtil.longTo32Bytes(j + 224);
        List<ShieldContract.ReceiveDescription> receiveDescriptionList = shieldedTRC20Parameters.getReceiveDescriptionList();
        for (ShieldContract.ReceiveDescription receiveDescription : receiveDescriptionList) {
            bArr2 = ByteUtil.merge(bArr2, receiveDescription.getNoteCommitment().toByteArray(), receiveDescription.getValueCommitment().toByteArray(), receiveDescription.getEpk().toByteArray(), receiveDescription.getZkproof().toByteArray());
            bArr3 = ByteUtil.merge(bArr3, receiveDescription.getCEnc().toByteArray(), receiveDescription.getCOut().toByteArray(), new byte[12]);
        }
        long size2 = receiveDescriptionList.size();
        byte[] longTo32Bytes4 = StringTronUtil.longTo32Bytes(size2);
        long j2 = j + 256 + (size * 64);
        return Hex.toHexString(ByteUtil.merge(longTo32Bytes, longTo32Bytes3, StringTronUtil.longTo32Bytes(j2), shieldedTRC20Parameters.getBindingSignature().toByteArray(), StringTronUtil.longTo32Bytes(j2 + 32 + (size2 * 288)), longTo32Bytes2, bArr, longTo32Bytes2, merge, longTo32Bytes4, bArr2, longTo32Bytes4, bArr3));
    }

    public static String encodeBurnParamsToHexString(GrpcAPI.ShieldedTRC20Parameters shieldedTRC20Parameters, List<String> list, BigInteger bigInteger, byte[] bArr) {
        byte[] merge;
        byte[] bArr2 = new byte[32];
        if (bigInteger.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("the value must be positive");
        }
        if (ArrayUtils.isEmpty(bArr)) {
            throw new IllegalArgumentException("the transparent payTo address is null");
        }
        bArr2[11] = Parameter.CommonConstant.ADD_PRE_FIX_BYTE;
        System.arraycopy(bArr, 0, bArr2, 12, 20);
        ShieldContract.SpendDescription spendDescription = shieldedTRC20Parameters.getSpendDescription(0);
        byte[] merge2 = ByteUtil.merge(spendDescription.getNullifier().toByteArray(), spendDescription.getAnchor().toByteArray(), spendDescription.getValueCommitment().toByteArray(), spendDescription.getRk().toByteArray(), spendDescription.getZkproof().toByteArray(), ByteArray.fromHexString(list.get(0)), ByteUtil.bigIntegerToBytes(bigInteger, 32), shieldedTRC20Parameters.getBindingSignature().toByteArray(), bArr2, new byte[80], new byte[16]);
        if (shieldedTRC20Parameters.getReceiveDescriptionList().size() == 0) {
            merge = ByteUtil.merge(merge2, StringTronUtil.longTo32Bytes(merge2.length + 64), StringTronUtil.longTo32Bytes(merge2.length + 96), StringTronUtil.longTo32Bytes(0L), StringTronUtil.longTo32Bytes(0L));
        } else {
            byte[] longTo32Bytes = StringTronUtil.longTo32Bytes(merge2.length + 64);
            byte[] longTo32Bytes2 = StringTronUtil.longTo32Bytes(1L);
            byte[] longTo32Bytes3 = StringTronUtil.longTo32Bytes(merge2.length + 96 + 288);
            byte[] longTo32Bytes4 = StringTronUtil.longTo32Bytes(1L);
            ShieldContract.ReceiveDescription receiveDescription = shieldedTRC20Parameters.getReceiveDescription(0);
            merge = ByteUtil.merge(merge2, longTo32Bytes, longTo32Bytes3, longTo32Bytes2, receiveDescription.getNoteCommitment().toByteArray(), receiveDescription.getValueCommitment().toByteArray(), receiveDescription.getEpk().toByteArray(), receiveDescription.getZkproof().toByteArray(), longTo32Bytes4, receiveDescription.getCEnc().toByteArray(), receiveDescription.getCOut().toByteArray(), new byte[12]);
        }
        return Hex.toHexString(merge);
    }

    public static GrpcAPI.WitnessList listWitnesses() {
        GrpcAPI.WitnessList listWitnesses = IGrpcClient.THIS.getCli().listWitnesses();
        if (listWitnesses != null) {
            List<Protocol.Witness> witnessesList = listWitnesses.getWitnessesList();
            ArrayList<Protocol.Witness> arrayList = new ArrayList();
            arrayList.addAll(witnessesList);
            Collections.sort(arrayList, new WitnessComparator());
            GrpcAPI.WitnessList.Builder newBuilder = GrpcAPI.WitnessList.newBuilder();
            for (Protocol.Witness witness : arrayList) {
                newBuilder.addWitnesses(witness);
            }
            return newBuilder.build();
        }
        return listWitnesses;
    }

    public static AssetIssueContractOuterClass.AssetIssueContract createAssetIssueContract(byte[] bArr, String str, String str2, long j, int i, int i2, long j2, long j3, int i3, String str3, String str4, long j4, long j5, List<AssetIssueContractOuterClass.AssetIssueContract.FrozenSupply> list) {
        AssetIssueContractOuterClass.AssetIssueContract.Builder newBuilder = AssetIssueContractOuterClass.AssetIssueContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.setName(ByteString.copyFrom(str.getBytes()));
        newBuilder.setAbbr(ByteString.copyFrom(str2.getBytes()));
        if (j <= 0) {
            return null;
        }
        newBuilder.setTotalSupply(j);
        if (i <= 0) {
            return null;
        }
        newBuilder.setTrxNum(i);
        if (i2 <= 0) {
            return null;
        }
        newBuilder.setNum(i2);
        if (j2 > System.currentTimeMillis() && j3 > j2 && j4 >= 0 && j5 >= 0) {
            newBuilder.setStartTime(j2);
            newBuilder.setEndTime(j3);
            newBuilder.setVoteScore(i3);
            newBuilder.setDescription(ByteString.copyFrom(str3.getBytes()));
            newBuilder.setUrl(ByteString.copyFrom(str4.getBytes()));
            newBuilder.setFreeAssetNetLimit(j4);
            newBuilder.setPublicFreeAssetNetLimit(j5);
            newBuilder.addAllFrozenSupply(list);
            return newBuilder.build();
        }
        return null;
    }

    public static BalanceContract.FreezeBalanceContract createFreezeBalanceContract(byte[] bArr, long j, long j2, byte[] bArr2, Common.ResourceCode resourceCode) {
        BalanceContract.FreezeBalanceContract.Builder newBuilder = BalanceContract.FreezeBalanceContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr)).setFrozenBalance(j).setFrozenDuration(j2).setResource(resourceCode);
        if (bArr2 != null && bArr2.length != 0) {
            newBuilder.setReceiverAddress(ByteString.copyFrom(bArr2));
        }
        return newBuilder.build();
    }

    public static AccountContract.AccountUpdateContract createAccountUpdateContract(byte[] bArr, byte[] bArr2) {
        AccountContract.AccountUpdateContract.Builder newBuilder = AccountContract.AccountUpdateContract.newBuilder();
        ByteString copyFrom = ByteString.copyFrom(bArr2);
        newBuilder.setAccountName(ByteString.copyFrom(bArr));
        newBuilder.setOwnerAddress(copyFrom);
        return newBuilder.build();
    }

    public static AssetIssueContractOuterClass.UpdateAssetContract createUpdateAssetContract(byte[] bArr, byte[] bArr2, byte[] bArr3, long j, long j2) {
        AssetIssueContractOuterClass.UpdateAssetContract.Builder newBuilder = AssetIssueContractOuterClass.UpdateAssetContract.newBuilder();
        ByteString copyFrom = ByteString.copyFrom(bArr);
        newBuilder.setDescription(ByteString.copyFrom(bArr2));
        newBuilder.setUrl(ByteString.copyFrom(bArr3));
        newBuilder.setNewLimit(j);
        newBuilder.setNewPublicLimit(j2);
        newBuilder.setOwnerAddress(copyFrom);
        return newBuilder.build();
    }

    public static WitnessContract.WitnessCreateContract createWitnessCreateContract(byte[] bArr, byte[] bArr2) {
        WitnessContract.WitnessCreateContract.Builder newBuilder = WitnessContract.WitnessCreateContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.setUrl(ByteString.copyFrom(bArr2));
        return newBuilder.build();
    }

    public static WitnessContract.WitnessUpdateContract createWitnessUpdateContract(byte[] bArr, byte[] bArr2) {
        WitnessContract.WitnessUpdateContract.Builder newBuilder = WitnessContract.WitnessUpdateContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.setUpdateUrl(ByteString.copyFrom(bArr2));
        return newBuilder.build();
    }

    public static WitnessContract.VoteWitnessContract createVoteWitnessContract(byte[] bArr, HashMap<String, String> hashMap) {
        WitnessContract.VoteWitnessContract.Builder newBuilder = WitnessContract.VoteWitnessContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        for (String str : hashMap.keySet()) {
            long parseLong = Long.parseLong(hashMap.get(str));
            WitnessContract.VoteWitnessContract.Vote.Builder newBuilder2 = WitnessContract.VoteWitnessContract.Vote.newBuilder();
            byte[] decodeFromBase58Check = StringTronUtil.decodeFromBase58Check(str);
            if (decodeFromBase58Check != null) {
                newBuilder2.setVoteAddress(ByteString.copyFrom(decodeFromBase58Check));
                newBuilder2.setVoteCount(parseLong);
                newBuilder.addVotes(newBuilder2.build());
            }
        }
        return newBuilder.build();
    }

    public static BalanceContract.UnfreezeBalanceContract createUnfreezeBalanceContract(byte[] bArr, Common.ResourceCode resourceCode) {
        BalanceContract.UnfreezeBalanceContract.Builder newBuilder = BalanceContract.UnfreezeBalanceContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.setResource(resourceCode);
        return newBuilder.build();
    }

    public static BalanceContract.UnfreezeBalanceContract createUnfreezeBalanceContract(byte[] bArr, byte[] bArr2, Common.ResourceCode resourceCode) {
        BalanceContract.UnfreezeBalanceContract.Builder newBuilder = BalanceContract.UnfreezeBalanceContract.newBuilder();
        ByteString copyFrom = ByteString.copyFrom(bArr);
        ByteString copyFrom2 = ByteString.copyFrom(bArr2);
        newBuilder.setOwnerAddress(copyFrom);
        newBuilder.setReceiverAddress(copyFrom2);
        newBuilder.setResource(resourceCode);
        return newBuilder.build();
    }

    public static boolean isTransactionConfirmed(Protocol.Transaction transaction) {
        String hexString = Hex.toHexString(Hash.sha256(transaction.getRawData().toByteArray()));
        Protocol.Transaction transaction2 = null;
        int i = 0;
        while (true) {
            if ((transaction2 == null || !transaction2.hasRawData()) && i <= 5) {
                try {
                    transaction2 = getTransactionById(hexString);
                    i++;
                } catch (NullPointerException e) {
                    LogUtils.e(e);
                }
            }
        }
        return transaction2.hasRawData() && hexString.equals(Hex.toHexString(Hash.sha256(transaction2.getRawData().toByteArray())));
    }

    public static BalanceContract.TransferContract createTransferContract(byte[] bArr, byte[] bArr2, long j) {
        BalanceContract.TransferContract.Builder newBuilder = BalanceContract.TransferContract.newBuilder();
        ByteString copyFrom = ByteString.copyFrom(bArr);
        ByteString copyFrom2 = ByteString.copyFrom(bArr2);
        newBuilder.setToAddress(copyFrom);
        newBuilder.setOwnerAddress(copyFrom2);
        newBuilder.setAmount(j);
        return newBuilder.build();
    }

    public static AssetIssueContractOuterClass.TransferAssetContract createTransferAssetContract(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        AssetIssueContractOuterClass.TransferAssetContract.Builder newBuilder = AssetIssueContractOuterClass.TransferAssetContract.newBuilder();
        ByteString copyFrom = ByteString.copyFrom(bArr);
        ByteString copyFrom2 = ByteString.copyFrom(bArr2);
        ByteString copyFrom3 = ByteString.copyFrom(bArr3);
        newBuilder.setToAddress(copyFrom);
        newBuilder.setAssetName(copyFrom2);
        newBuilder.setOwnerAddress(copyFrom3);
        newBuilder.setAmount(j);
        return newBuilder.build();
    }

    public static AssetIssueContractOuterClass.ParticipateAssetIssueContract createParticipateAssetIssueContract(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        AssetIssueContractOuterClass.ParticipateAssetIssueContract.Builder newBuilder = AssetIssueContractOuterClass.ParticipateAssetIssueContract.newBuilder();
        ByteString copyFrom = ByteString.copyFrom(bArr);
        ByteString copyFrom2 = ByteString.copyFrom(bArr2);
        ByteString copyFrom3 = ByteString.copyFrom(bArr3);
        newBuilder.setToAddress(copyFrom);
        newBuilder.setAssetName(copyFrom2);
        newBuilder.setOwnerAddress(copyFrom3);
        newBuilder.setAmount(j);
        return newBuilder.build();
    }

    static class WitnessComparator implements Comparator {
        WitnessComparator() {
        }

        @Override
        public int compare(Object obj, Object obj2) {
            return Long.compare(((Protocol.Witness) obj2).getVoteCount(), ((Protocol.Witness) obj).getVoteCount());
        }
    }

    public static GrpcAPI.TransactionExtention createAccountPermissionUpdateContract(byte[] bArr, Protocol.Permission permission, Protocol.Permission permission2, List<Protocol.Permission> list) {
        ByteString copyFrom = ByteString.copyFrom(bArr);
        AccountContract.AccountPermissionUpdateContract.Builder newBuilder = AccountContract.AccountPermissionUpdateContract.newBuilder();
        newBuilder.setOwnerAddress(copyFrom);
        newBuilder.setOwner(permission);
        if (permission2 != null && !TextUtils.isEmpty(permission2.getPermissionName())) {
            newBuilder.setWitness(permission2);
        }
        newBuilder.addAllActives(list);
        AccountContract.AccountPermissionUpdateContract build = newBuilder.build();
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.AccountPermissionUpdateContract, Any.pack(build));
        }
        return IGrpcClient.THIS.getCli().accountPermissionUpdate(build);
    }

    public static BalanceContract.WithdrawBalanceContract createWithdrawBalanceContract(byte[] bArr) {
        BalanceContract.WithdrawBalanceContract.Builder newBuilder = BalanceContract.WithdrawBalanceContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        return newBuilder.build();
    }

    public static GrpcAPI.CanDelegatedMaxSizeRequestMessage createCanDelegatedMaxSizeRequestMessage(int i, String str) {
        GrpcAPI.CanDelegatedMaxSizeRequestMessage.Builder newBuilder = GrpcAPI.CanDelegatedMaxSizeRequestMessage.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        newBuilder.setType(i);
        return newBuilder.build();
    }

    public static GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage createCanWithdrawUnfreezeAmountRequestMessage(long j, String str) {
        GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage.Builder newBuilder = GrpcAPI.CanWithdrawUnfreezeAmountRequestMessage.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        newBuilder.setTimestamp(j);
        return newBuilder.build();
    }

    public static GrpcAPI.GetAvailableUnfreezeCountRequestMessage createGetAvailableUnfreezeCountRequestMessage(String str) {
        GrpcAPI.GetAvailableUnfreezeCountRequestMessage.Builder newBuilder = GrpcAPI.GetAvailableUnfreezeCountRequestMessage.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        return newBuilder.build();
    }

    public static BalanceContract.UnfreezeBalanceV2Contract createUnfreezeBalanceV2Contract(String str, long j, Common.ResourceCode resourceCode) {
        BalanceContract.UnfreezeBalanceV2Contract.Builder newBuilder = BalanceContract.UnfreezeBalanceV2Contract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        newBuilder.setUnfreezeBalance(j);
        newBuilder.setResource(resourceCode);
        return newBuilder.build();
    }

    public static BalanceContract.CancelAllUnfreezeV2Contract createCancelAllUnfreezeV2Contract(String str) {
        BalanceContract.CancelAllUnfreezeV2Contract.Builder newBuilder = BalanceContract.CancelAllUnfreezeV2Contract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        return newBuilder.build();
    }

    public static BalanceContract.FreezeBalanceV2Contract createFreezeBalanceV2Contract(String str, long j, Common.ResourceCode resourceCode) {
        BalanceContract.FreezeBalanceV2Contract.Builder newBuilder = BalanceContract.FreezeBalanceV2Contract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        newBuilder.setFrozenBalance(j);
        newBuilder.setResource(resourceCode);
        return newBuilder.build();
    }

    public static BalanceContract.WithdrawExpireUnfreezeContract createWithdrawExpireUnfreezeContract(String str) {
        BalanceContract.WithdrawExpireUnfreezeContract.Builder newBuilder = BalanceContract.WithdrawExpireUnfreezeContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        return newBuilder.build();
    }

    public static BalanceContract.DelegateResourceContract createDelegateResourceContract(String str, String str2, Common.ResourceCode resourceCode, long j, boolean z, long j2) {
        BalanceContract.DelegateResourceContract.Builder newBuilder = BalanceContract.DelegateResourceContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        newBuilder.setReceiverAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str2)));
        newBuilder.setResource(resourceCode);
        newBuilder.setBalance(j);
        newBuilder.setLockPeriod(j2);
        newBuilder.setLock(z);
        return newBuilder.build();
    }

    public static BalanceContract.UnDelegateResourceContract createUnDelegateResourceContract(String str, String str2, Common.ResourceCode resourceCode, long j) {
        BalanceContract.UnDelegateResourceContract.Builder newBuilder = BalanceContract.UnDelegateResourceContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str)));
        newBuilder.setReceiverAddress(ByteString.copyFrom(StringTronUtil.decode58Check(str2)));
        newBuilder.setResource(resourceCode);
        newBuilder.setBalance(j);
        return newBuilder.build();
    }

    public static GrpcAPI.NumberMessage getRewardInfo(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getRewardInfo(bArr);
    }

    public static GrpcAPI.NumberMessage getBrokerageInfo(byte[] bArr) {
        return IGrpcClient.THIS.getCli().getBrokerageInfo(bArr);
    }

    public static GrpcAPI.TransactionExtention createWithdrawBalanceTransaction(byte[] bArr) {
        BalanceContract.WithdrawBalanceContract createWithdrawBalanceContract = createWithdrawBalanceContract(bArr);
        if (TronSetting.CreateByLocal) {
            return TransactionCapsule.createLocalTransaction(Protocol.Transaction.Contract.ContractType.WithdrawBalanceContract, Any.pack(createWithdrawBalanceContract));
        }
        return IGrpcClient.THIS.getCli().withdrawBalance2(createWithdrawBalanceContract);
    }

    public static GrpcAPI.TransactionExtention createProposalTransaction(byte[] bArr, HashMap<Long, Long> hashMap) {
        ProposalContract.ProposalCreateContract.Builder newBuilder = ProposalContract.ProposalCreateContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.putAllParameters(hashMap);
        return IGrpcClient.THIS.getCli().proposalCreate(newBuilder.build());
    }

    public static GrpcAPI.TransactionExtention createProposalApproveTranscation(byte[] bArr, long j, boolean z) {
        ProposalContract.ProposalApproveContract.Builder newBuilder = ProposalContract.ProposalApproveContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.setProposalId(j);
        newBuilder.setIsAddApproval(z);
        return IGrpcClient.THIS.getCli().proposalApprove(newBuilder.build());
    }

    public static GrpcAPI.TransactionExtention createProposalDeleteTranscation(byte[] bArr, long j) {
        ProposalContract.ProposalDeleteContract.Builder newBuilder = ProposalContract.ProposalDeleteContract.newBuilder();
        newBuilder.setOwnerAddress(ByteString.copyFrom(bArr));
        newBuilder.setProposalId(j);
        return IGrpcClient.THIS.getCli().proposalDelete(newBuilder.build());
    }

    public static GrpcAPI.ProposalList listProposals() {
        return IGrpcClient.THIS.getCli().listProposals();
    }

    public static Protocol.ChainParameters getChainParameters() {
        return IGrpcClient.THIS.getCli().getChainParameters();
    }

    public static Protocol.Proposal getproposalbyid(String str) {
        return IGrpcClient.THIS.getCli().getProposal(str);
    }

    public static Protocol.Block getNowBlock() {
        Protocol.Block nowBlock = IGrpcClient.THIS.getCli().getNowBlock();
        return nowBlock == null ? IGrpcClient.THIS.getCli().getBlock(-1L) : nowBlock;
    }

    public static GrpcAPI.BlockExtention getNowBlock2() {
        return IGrpcClient.THIS.getCli().getNowBlock2();
    }

    public static void connectNode() {
        IGrpcClient.THIS.initGRpc();
    }

    public static GrpcAPI.IvkDecryptParameters createIvkDecryptParameters(long j, long j2, byte[] bArr) {
        GrpcAPI.IvkDecryptParameters.Builder newBuilder = GrpcAPI.IvkDecryptParameters.newBuilder();
        newBuilder.setStartBlockIndex(j);
        newBuilder.setEndBlockIndex(j2);
        newBuilder.setIvk(ByteString.copyFrom(bArr));
        return newBuilder.build();
    }

    public static GrpcAPI.OvkDecryptParameters createOvkDecryptParameters(long j, long j2, byte[] bArr) {
        GrpcAPI.OvkDecryptParameters.Builder newBuilder = GrpcAPI.OvkDecryptParameters.newBuilder();
        newBuilder.setStartBlockIndex(j);
        newBuilder.setEndBlockIndex(j2);
        newBuilder.setOvk(ByteString.copyFrom(bArr));
        return newBuilder.build();
    }

    public static GrpcAPI.DecryptNotes scanNoteByIvk(long j, long j2, byte[] bArr) {
        return IGrpcClient.THIS.getCli().ScanNoteByIvk(createIvkDecryptParameters(j, j2, bArr));
    }

    public static GrpcAPI.DecryptNotesMarked scanAndMarkNoteByIvk(long j, long j2, byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        return IGrpcClient.THIS.getCli().scanAndMarkNoteByIvk(createIvkDecryptAndMarkParameters(j, j2, bArr, bArr2, bArr3));
    }

    public static GrpcAPI.DecryptNotes scanNoteByOvk(long j, long j2, byte[] bArr) {
        return IGrpcClient.THIS.getCli().ScanNoteByOvk(createOvkDecryptParameters(j, j2, bArr));
    }

    public static GrpcAPI.DecryptNotesMarked scanAndMarkNoteByOvk(long j, long j2, byte[] bArr, byte[] bArr2, byte[] bArr3) throws Exception {
        return IGrpcClient.THIS.getCli().scanAndMarkNoteByIvk(createOvkDecryptAndMarkParameters(j, j2, bArr, bArr2, bArr3));
    }

    public static ShieldContract.OutputPointInfo createOutputPointInfo(int i, int i2, byte[] bArr) {
        ShieldContract.OutputPointInfo.Builder newBuilder = ShieldContract.OutputPointInfo.newBuilder();
        ShieldContract.OutputPoint.Builder newBuilder2 = ShieldContract.OutputPoint.newBuilder();
        newBuilder2.setHash(ByteString.copyFrom(bArr));
        newBuilder2.setIndex(i2);
        newBuilder.setBlockNum(i);
        newBuilder.addOutPoints(newBuilder2.build());
        return newBuilder.build();
    }

    public static GrpcAPI.IvkDecryptAndMarkParameters createIvkDecryptAndMarkParameters(long j, long j2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        GrpcAPI.IvkDecryptAndMarkParameters.Builder newBuilder = GrpcAPI.IvkDecryptAndMarkParameters.newBuilder();
        if (!ArrayUtils.isEmpty(bArr2)) {
            newBuilder.setAk(ByteString.copyFrom(bArr2));
        }
        if (!ArrayUtils.isEmpty(bArr3)) {
            newBuilder.setNk(ByteString.copyFrom(bArr3));
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            newBuilder.setIvk(ByteString.copyFrom(bArr));
        }
        newBuilder.setStartBlockIndex(j);
        newBuilder.setEndBlockIndex(j2);
        return newBuilder.build();
    }

    public static GrpcAPI.IvkDecryptAndMarkParameters createOvkDecryptAndMarkParameters(long j, long j2, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        GrpcAPI.IvkDecryptAndMarkParameters.Builder newBuilder = GrpcAPI.IvkDecryptAndMarkParameters.newBuilder();
        if (!ArrayUtils.isEmpty(bArr2)) {
            newBuilder.setAk(ByteString.copyFrom(bArr2));
        }
        if (!ArrayUtils.isEmpty(bArr3)) {
            newBuilder.setNk(ByteString.copyFrom(bArr3));
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            newBuilder.setIvk(ByteString.copyFrom(bArr));
        }
        newBuilder.setStartBlockIndex(j);
        newBuilder.setEndBlockIndex(j2);
        return newBuilder.build();
    }

    public static ShieldContract.IncrementalMerkleVoucherInfo getMerkleTreeVoucherInfo(int i, int i2, byte[] bArr) {
        return IGrpcClient.THIS.getCli().GetMerkleTreeVoucherInfo(createOutputPointInfo(i, i2, bArr));
    }

    public static GrpcAPI.PrivateParametersWithoutAsk createPrivateParametersWithoutAsk(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, long j, long j2, List<GrpcAPI.SpendNote> list, List<GrpcAPI.ReceiveNote> list2) {
        GrpcAPI.PrivateParametersWithoutAsk.Builder newBuilder = GrpcAPI.PrivateParametersWithoutAsk.newBuilder();
        if (list2 != null && !list2.isEmpty()) {
            for (int i = 0; i < list2.size(); i++) {
                newBuilder.addShieldedReceives(i, list2.get(i));
            }
        }
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                newBuilder.addShieldedSpends(i2, list.get(i2));
            }
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            newBuilder.setAk(ByteString.copyFrom(bArr));
        }
        if (!ArrayUtils.isEmpty(bArr2)) {
            newBuilder.setNsk(ByteString.copyFrom(bArr2));
        }
        if (!ArrayUtils.isEmpty(bArr3)) {
            newBuilder.setOvk(ByteString.copyFrom(bArr3));
        }
        if (!ArrayUtils.isEmpty(bArr4)) {
            newBuilder.setTransparentFromAddress(ByteString.copyFrom(bArr4));
        }
        if (!ArrayUtils.isEmpty(bArr5)) {
            newBuilder.setTransparentToAddress(ByteString.copyFrom(bArr5));
        }
        newBuilder.setTimeout(300L);
        newBuilder.setFromAmount(j);
        newBuilder.setToAmount(j2);
        return newBuilder.build();
    }

    public static GrpcAPI.TransactionExtention createShieldedTransactionWithoutSpendAuthSig(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, long j, long j2, List<GrpcAPI.SpendNote> list, List<GrpcAPI.ReceiveNote> list2) {
        return IGrpcClient.THIS.getCli().createShieldedTransactionWithoutSpendAuthSig(createPrivateParametersWithoutAsk(bArr, bArr2, bArr3, bArr4, bArr5, j, j2, list, list2));
    }

    public static GrpcAPI.TransactionExtention createShieldedTransactionWithoutSpendAuthSig(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, long j, long j2, GrpcAPI.SpendNote spendNote, GrpcAPI.ReceiveNote... receiveNoteArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (spendNote != null) {
            arrayList.add(spendNote);
        }
        if (receiveNoteArr != null && receiveNoteArr.length != 0) {
            for (GrpcAPI.ReceiveNote receiveNote : receiveNoteArr) {
                arrayList2.add(receiveNote);
            }
        }
        return IGrpcClient.THIS.getCli().createShieldedTransactionWithoutSpendAuthSig(createPrivateParametersWithoutAsk(bArr, bArr2, bArr3, bArr4, bArr5, j, j2, arrayList, arrayList2));
    }

    public static GrpcAPI.SpendResult noteIsSpend(byte[] bArr, byte[] bArr2, GrpcAPI.Note note, byte[] bArr3, int i) {
        GrpcAPI.NoteParameters.Builder newBuilder = GrpcAPI.NoteParameters.newBuilder();
        newBuilder.setAk(ByteString.copyFrom(bArr));
        newBuilder.setNk(ByteString.copyFrom(bArr2));
        newBuilder.setNote(note);
        newBuilder.setTxid(ByteString.copyFrom(bArr3));
        newBuilder.setIndex(i);
        return IGrpcClient.THIS.getCli().isSpend(newBuilder.build());
    }

    public static AssetIssueContractOuterClass.AssetIssueContract getAssetIssueById(int i) {
        GrpcAPI.BytesMessage.Builder newBuilder = GrpcAPI.BytesMessage.newBuilder();
        newBuilder.setValue(ByteString.copyFrom((i + "").getBytes()));
        return IGrpcClient.THIS.getCli().getAssetIssueById(newBuilder.build());
    }

    public static GrpcAPI.IvkDecryptTRC20Parameters createIvkDecryptTRC20Parameters(long j, long j2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        GrpcAPI.IvkDecryptTRC20Parameters.Builder newBuilder = GrpcAPI.IvkDecryptTRC20Parameters.newBuilder();
        if (!ArrayUtils.isEmpty(bArr2)) {
            newBuilder.setAk(ByteString.copyFrom(bArr2));
        }
        if (!ArrayUtils.isEmpty(bArr3)) {
            newBuilder.setNk(ByteString.copyFrom(bArr3));
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            newBuilder.setIvk(ByteString.copyFrom(bArr));
        }
        if (!ArrayUtils.isEmpty(bArr4)) {
            newBuilder.setShieldedTRC20ContractAddress(ByteString.copyFrom(bArr4));
        }
        newBuilder.setStartBlockIndex(j);
        newBuilder.setEndBlockIndex(j2);
        return newBuilder.build();
    }

    public static GrpcAPI.OvkDecryptTRC20Parameters createOvkDecryptTRC20Parameters(long j, long j2, byte[] bArr) {
        GrpcAPI.OvkDecryptTRC20Parameters.Builder newBuilder = GrpcAPI.OvkDecryptTRC20Parameters.newBuilder();
        newBuilder.setStartBlockIndex(j);
        newBuilder.setEndBlockIndex(j2);
        newBuilder.setOvk(ByteString.copyFrom(bArr));
        return newBuilder.build();
    }

    public static GrpcAPI.ShieldedTRC20TriggerContractParameters createShieldedTRC20TriggerContractParameters(GrpcAPI.ShieldedTRC20Parameters shieldedTRC20Parameters, List<String> list, BigDecimal bigDecimal, byte[] bArr) {
        GrpcAPI.ShieldedTRC20TriggerContractParameters.Builder newBuilder = GrpcAPI.ShieldedTRC20TriggerContractParameters.newBuilder();
        newBuilder.setAmount(bigDecimal.toBigInteger().toString());
        newBuilder.setShieldedTRC20Parameters(shieldedTRC20Parameters);
        if (!ArrayUtils.isEmpty(bArr)) {
            newBuilder.setTransparentToAddress(ByteString.copyFrom(bArr));
        }
        for (int i = 0; i < list.size(); i++) {
            newBuilder.addSpendAuthoritySignature(i, GrpcAPI.BytesMessage.newBuilder().setValue(ByteString.copyFrom(ByteArray.fromHexString(list.get(i)))).build());
        }
        return newBuilder.build();
    }

    public static GrpcAPI.ShieldedTRC20TriggerContractParameters createShieldedTRC20TriggerContractParameters(GrpcAPI.ShieldedTRC20Parameters shieldedTRC20Parameters, byte[] bArr, BigInteger bigInteger, byte[] bArr2) {
        GrpcAPI.ShieldedTRC20TriggerContractParameters.Builder newBuilder = GrpcAPI.ShieldedTRC20TriggerContractParameters.newBuilder();
        newBuilder.setShieldedTRC20Parameters(shieldedTRC20Parameters);
        newBuilder.setAmount(bigInteger.toString());
        if (!ArrayUtils.isEmpty(bArr2)) {
            newBuilder.setTransparentToAddress(ByteString.copyFrom(bArr2));
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            GrpcAPI.BytesMessage.Builder newBuilder2 = GrpcAPI.BytesMessage.newBuilder();
            newBuilder2.setValue(ByteString.copyFrom(bArr));
            newBuilder.addSpendAuthoritySignature(newBuilder2.build());
        }
        return newBuilder.build();
    }

    public static GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk createPrivateShieldedTRC20ParametersWithoutAsk(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, BigInteger bigInteger, BigInteger bigInteger2, List<GrpcAPI.SpendNoteTRC20> list, List<GrpcAPI.ReceiveNote> list2) {
        GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk.Builder newBuilder = GrpcAPI.PrivateShieldedTRC20ParametersWithoutAsk.newBuilder();
        if (list2 != null && !list2.isEmpty()) {
            if (list2.size() == 1) {
                newBuilder.addShieldedReceives(list2.get(0));
            } else {
                for (int i = 0; i < list2.size(); i++) {
                    newBuilder.addShieldedReceives(i, list2.get(i));
                }
            }
        }
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                newBuilder.addShieldedSpends(i2, list.get(i2));
            }
        }
        if (!ArrayUtils.isEmpty(bArr)) {
            newBuilder.setAk(ByteString.copyFrom(bArr));
        }
        if (!ArrayUtils.isEmpty(bArr2)) {
            newBuilder.setNsk(ByteString.copyFrom(bArr2));
        }
        if (!ArrayUtils.isEmpty(bArr3)) {
            newBuilder.setOvk(ByteString.copyFrom(bArr3));
        }
        if (!ArrayUtils.isEmpty(bArr4)) {
            newBuilder.setShieldedTRC20ContractAddress(ByteString.copyFrom(bArr4));
        }
        if (!ArrayUtils.isEmpty(bArr5)) {
            newBuilder.setTransparentToAddress(ByteString.copyFrom(bArr5));
        }
        newBuilder.setFromAmount(bigInteger.toString());
        newBuilder.setToAmount(bigInteger2.toString());
        return newBuilder.build();
    }

    public static GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesbyOvk(long j, long j2, byte[] bArr) throws Exception {
        return IGrpcClient.THIS.getCli().scanShieldedTRC20NotesbyOvk(createOvkDecryptTRC20Parameters(j, j2, bArr));
    }

    public static GrpcAPI.DecryptNotesTRC20 scanShieldedTRC20NotesbyIvk(long j, long j2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws Exception {
        return IGrpcClient.THIS.getCli().scanShieldedTRC20NotesbyIvk(createIvkDecryptTRC20Parameters(j, j2, bArr, bArr2, bArr3, bArr4));
    }

    public static GrpcAPI.NullifierResult isShieldedTRC20ContractNoteSpent(byte[] bArr, byte[] bArr2, GrpcAPI.Note note, int i, byte[] bArr3) {
        GrpcAPI.NfTRC20Parameters.Builder newBuilder = GrpcAPI.NfTRC20Parameters.newBuilder();
        newBuilder.setAk(ByteString.copyFrom(bArr));
        newBuilder.setNk(ByteString.copyFrom(bArr2));
        newBuilder.setNote(note);
        newBuilder.setShieldedTRC20ContractAddress(ByteString.copyFrom(bArr3));
        newBuilder.setPosition(i);
        return IGrpcClient.THIS.getCli().isShieldedTRC20ContractNoteSpent(newBuilder.build());
    }

    public static GrpcAPI.ShieldedTRC20Parameters createShieldedContractParametersWithoutAsk(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, BigInteger bigInteger, BigInteger bigInteger2, List<GrpcAPI.SpendNoteTRC20> list, List<GrpcAPI.ReceiveNote> list2) throws ZTronException, EncodingException {
        if (ArrayUtils.isEmpty(bArr4)) {
            throw new ZTronException("shieldedTRC20ContractAddress is null.");
        }
        BigInteger scalingFactor = TriggerUtils.getScalingFactor(StringTronUtil.encode58Check(bArr4));
        if (scalingFactor.longValue() == 0) {
            scalingFactor = BigInteger.valueOf(10L);
        }
        if (bigInteger.compareTo(BigInteger.ZERO) > 0 && list2.size() == 1 && ((list == null || list.size() == 0) && bigInteger2.compareTo(BigInteger.ZERO) == 0)) {
            LogUtils.i("This is an MINT. ");
            if (BigInteger.valueOf(list2.get(0).getNote().getValue()).multiply(scalingFactor).compareTo(bigInteger) != 0) {
                throw new ZTronException("MINT: fromPublicAmount must be equal to note amount.");
            }
        }
        if (bigInteger.compareTo(BigInteger.ZERO) == 0 && ((list2 == null || list2.size() == 0) && list.size() == 1 && bigInteger2.compareTo(BigInteger.ZERO) > 0)) {
            LogUtils.i("This is an BURN. ");
            if (ArrayUtils.isEmpty(bArr5)) {
                throw new ZTronException("transparentToAddress is null.");
            }
            if (BigInteger.valueOf(list.get(0).getNote().getValue()).multiply(scalingFactor).compareTo(bigInteger2) != 0) {
                throw new ZTronException("BURN: toPublicAmount must be equal to note amount.");
            }
        }
        return IGrpcClient.THIS.getCli().createShieldedContractParametersWithoutAsk(createPrivateShieldedTRC20ParametersWithoutAsk(bArr, bArr2, bArr3, bArr4, bArr5, bigInteger, bigInteger2, list, list2));
    }

    public static GrpcAPI.BytesMessage getTriggerInputForShieldedTRC20Contract(GrpcAPI.ShieldedTRC20Parameters shieldedTRC20Parameters, List<String> list, BigDecimal bigDecimal, byte[] bArr) {
        return IGrpcClient.THIS.getCli().getTriggerInputForShieldedTRC20Contract(createShieldedTRC20TriggerContractParameters(shieldedTRC20Parameters, list, bigDecimal, bArr));
    }

    public static GrpcAPI.TransactionExtention approve(String str, String str2, String str3, String str4) throws EncodingException {
        TriggerContractRequest triggerContractRequest = new TriggerContractRequest();
        triggerContractRequest.setMethodStr(TransactionDataUtils.approveMethod);
        triggerContractRequest.setArgsStr(str2 + "," + str);
        triggerContractRequest.setContractAddrStr(str3);
        triggerContractRequest.setHex(false);
        triggerContractRequest.setOwer(StringTronUtil.decodeFromBase58Check(str4));
        triggerContractRequest.setTokenCallValue(0L);
        triggerContractRequest.setFeeLimit(225000000L);
        return triggerContract2(triggerContractRequest);
    }
}
