package org.tron.common.utils;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.tron.common.crypto.Hash;
import org.tron.common.utils.JsonFormat;
import org.tron.protos.Protocol;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.walletserver.AddressUtil;
import org.tron.walletserver.TriggerData;
public class TransactionDataUtils {
    public static final String ApproveSha3 = "095ea7b3";
    public static final String IncreaseApprovalSha3 = "d73dd623";
    public static final String TransferFromSha3 = "23b872dd";
    public static final String TransferSha3 = "a9059cbb";
    public static final String approveMethod = "approve(address,uint256)";
    public static final String increaseApprovalMethod = "increaseApproval(address,uint256)";
    public static final String transferFromMethod = "transferFrom(address,address,uint256)";
    public static final String transferMethod = "transfer(address,uint256)";

    private TransactionDataUtils() {
    }

    public static class SingletonHolder {
        private static final TransactionDataUtils instance = new TransactionDataUtils();

        private SingletonHolder() {
        }
    }

    public static TransactionDataUtils getInstance() {
        return SingletonHolder.instance;
    }

    public TriggerData parseData(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, SmartContractOuterClass.SmartContract.ABI abi) {
        SmartContractOuterClass.SmartContract.ABI.Entry entry = getEntry(abi, getSelector(triggerSmartContract.getData().toByteArray()));
        TriggerData triggerData = new TriggerData();
        triggerData.setMethod(getMethod(entry));
        triggerData.setParameterMap(getParameter(triggerSmartContract.getData().toByteArray(), entry));
        return triggerData;
    }

    public TriggerData parseDataByFun(SmartContractOuterClass.TriggerSmartContract triggerSmartContract, String str) {
        TriggerData triggerData = new TriggerData();
        triggerData.setMethod(str);
        triggerData.setParameterMap(getParameterByFun(triggerSmartContract.getData().toByteArray(), str));
        return triggerData;
    }

    public TriggerData parseConstantDataByFun(byte[] bArr, String str) {
        TriggerData triggerData = new TriggerData();
        triggerData.setMethod(str);
        triggerData.setParameterMap(getConstantParameterByFun(bArr, str));
        return triggerData;
    }

    private Map<String, String> getConstantParameterByFun(byte[] bArr, String str) {
        return TriggerLoad.parseTriggerDataByFun(bArr, str);
    }

    public Map<String, String> getParameterByFun(byte[] bArr, String str) {
        HashMap hashMap = new HashMap();
        return (AddressUtil.isEmpty(str) || bArr.length < 4 || !checkFunValid(str, bArr)) ? hashMap : TriggerLoad.parseTriggerDataByFun(Arrays.copyOfRange(bArr, 4, bArr.length), str);
    }

    private Map<String, String> getParameter(byte[] bArr, SmartContractOuterClass.SmartContract.ABI.Entry entry) {
        return (entry == null || bArr.length < 4) ? new HashMap() : TriggerLoad.parseTriggerData(Arrays.copyOfRange(bArr, 4, bArr.length), entry);
    }

    private String getMethod(SmartContractOuterClass.SmartContract.ABI.Entry entry) {
        if (entry == null) {
            return "()";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(entry.getName());
        sb.append("(");
        StringBuilder sb2 = new StringBuilder();
        for (SmartContractOuterClass.SmartContract.ABI.Entry.Param param : entry.getInputsList()) {
            if (sb2.length() > 0) {
                sb2.append(",");
            }
            sb2.append(param.getType());
        }
        sb.append(sb2.toString());
        sb.append(")");
        return sb.toString();
    }

    private SmartContractOuterClass.SmartContract.ABI.Entry getEntry(SmartContractOuterClass.SmartContract.ABI abi, byte[] bArr) {
        if (abi != null && bArr != null && bArr.length == 4 && abi.getEntrysList().size() != 0) {
            for (SmartContractOuterClass.SmartContract.ABI.Entry entry : abi.getEntrysList()) {
                if (entry.getType() == SmartContractOuterClass.SmartContract.ABI.Entry.EntryType.Function) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(entry.getName());
                    sb.append("(");
                    StringBuilder sb2 = new StringBuilder();
                    for (SmartContractOuterClass.SmartContract.ABI.Entry.Param param : entry.getInputsList()) {
                        if (sb2.length() > 0) {
                            sb2.append(",");
                        }
                        sb2.append(param.getType());
                    }
                    sb.append(sb2.toString());
                    sb.append(")");
                    byte[] bArr2 = new byte[4];
                    System.arraycopy(Hash.sha3(sb.toString().getBytes()), 0, bArr2, 0, 4);
                    if (Arrays.equals(bArr2, bArr)) {
                        return entry;
                    }
                }
            }
        }
        return null;
    }

    public byte[] getSelector(byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        return bArr2;
    }

    public TriggerData getTransferData(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) throws JsonFormat.ParseException {
        return parseDataByFun(triggerSmartContract, transferMethod);
    }

    public TriggerData getTransferNFTData(SmartContractOuterClass.TriggerSmartContract triggerSmartContract) throws JsonFormat.ParseException {
        return parseDataByFun(triggerSmartContract, transferFromMethod);
    }

    public boolean checkFunValid(String str, SmartContractOuterClass.TriggerSmartContract triggerSmartContract) {
        if (AddressUtil.isEmpty(str) || triggerSmartContract == null) {
            return false;
        }
        return checkFunValid(str, triggerSmartContract.getData().toByteArray());
    }

    public boolean checkFunValid(String str, byte[] bArr) {
        byte[] selector = getSelector(bArr);
        byte[] bArr2 = new byte[4];
        System.arraycopy(Hash.sha3(str.getBytes()), 0, bArr2, 0, 4);
        return Arrays.equals(bArr2, selector);
    }

    public boolean checkFunValid(String str, Protocol.Transaction transaction) {
        if (transaction == null || transaction.toString().length() < 1 || transaction.getRawData() == null || transaction.getRawData().getContractCount() < 1) {
            return false;
        }
        Protocol.Transaction.Contract contract = transaction.getRawData().getContract(0);
        if (contract.getType() == Protocol.Transaction.Contract.ContractType.TriggerSmartContract) {
            try {
                return checkFunValid(str, (SmartContractOuterClass.TriggerSmartContract) TransactionUtils.unpackContract(contract, SmartContractOuterClass.TriggerSmartContract.class));
            } catch (InvalidProtocolBufferException e) {
                LogUtils.e(e);
                return false;
            }
        }
        return true;
    }

    public String getEntryName(SmartContractOuterClass.SmartContract.ABI abi, byte[] bArr, int i) {
        try {
            return getEntry(abi, bArr).getInputs(i).getName();
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }
}
