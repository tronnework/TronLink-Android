package com.tron.wallet.common.bean.token;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.tron.wallet.common.config.TronConfig;
import java.util.List;
import java.util.Map;
public class TransactionInfoBean {
    private int block;
    private int confirmations;
    private boolean confirmed;
    private ContractDataBean contractData;
    private String contractRet;
    private int contractType;
    private Map<String, Boolean> contract_map;
    private String contract_type;
    private CostBean cost;
    private String data;
    private String hash;
    private InfoBean info;
    private Map internal_transactions;
    private String ownerAddress;
    private boolean revert;
    private boolean riskTransaction;
    private List<?> signature_addresses;
    private long timestamp;
    private String toAddress;
    private List<Trc20ApprovalItem> trc20ApprovalInfo;
    private List<Trc20ApprovalItem> trc20TransferInfo;
    private List<Trc20ApprovalItem> trc721ApprovalInfo;
    private List<Trc20ApprovalItem> trc721TransferInfo;
    private int triggerContractType;
    private TriggerInfoBean trigger_info;

    public int getBlock() {
        return this.block;
    }

    public int getConfirmations() {
        return this.confirmations;
    }

    public ContractDataBean getContractData() {
        return this.contractData;
    }

    public String getContractRet() {
        return this.contractRet;
    }

    public int getContractType() {
        return this.contractType;
    }

    public Map<String, Boolean> getContract_map() {
        return this.contract_map;
    }

    public String getContract_type() {
        return this.contract_type;
    }

    public CostBean getCost() {
        return this.cost;
    }

    public String getData() {
        return this.data;
    }

    public String getHash() {
        return this.hash;
    }

    public InfoBean getInfo() {
        return this.info;
    }

    public Map getInternal_transactions() {
        return this.internal_transactions;
    }

    public String getOwnerAddress() {
        return this.ownerAddress;
    }

    public List<?> getSignature_addresses() {
        return this.signature_addresses;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public List<Trc20ApprovalItem> getTrc20ApprovalInfo() {
        return this.trc20ApprovalInfo;
    }

    public List getTrc20TransferInfo() {
        return this.trc20TransferInfo;
    }

    public List<Trc20ApprovalItem> getTrc721ApprovalInfo() {
        return this.trc721ApprovalInfo;
    }

    public List<Trc20ApprovalItem> getTrc721TransferInfo() {
        return this.trc721TransferInfo;
    }

    public int getTriggerContractType() {
        return this.triggerContractType;
    }

    public TriggerInfoBean getTrigger_info() {
        return this.trigger_info;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public boolean isRevert() {
        return this.revert;
    }

    public boolean isRiskTransaction() {
        return this.riskTransaction;
    }

    public void setBlock(int i) {
        this.block = i;
    }

    public void setConfirmations(int i) {
        this.confirmations = i;
    }

    public void setConfirmed(boolean z) {
        this.confirmed = z;
    }

    public void setContractData(ContractDataBean contractDataBean) {
        this.contractData = contractDataBean;
    }

    public void setContractRet(String str) {
        this.contractRet = str;
    }

    public void setContractType(int i) {
        this.contractType = i;
    }

    public void setContract_map(Map<String, Boolean> map) {
        this.contract_map = map;
    }

    public void setContract_type(String str) {
        this.contract_type = str;
    }

    public void setCost(CostBean costBean) {
        this.cost = costBean;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setInfo(InfoBean infoBean) {
        this.info = infoBean;
    }

    public void setInternal_transactions(Map map) {
        this.internal_transactions = map;
    }

    public void setOwnerAddress(String str) {
        this.ownerAddress = str;
    }

    public void setRevert(boolean z) {
        this.revert = z;
    }

    public void setRiskTransaction(boolean z) {
        this.riskTransaction = z;
    }

    public void setSignature_addresses(List<?> list) {
        this.signature_addresses = list;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setToAddress(String str) {
        this.toAddress = str;
    }

    public void setTrc20ApprovalInfo(List<Trc20ApprovalItem> list) {
        this.trc20ApprovalInfo = list;
    }

    public void setTrc20TransferInfo(List<Trc20ApprovalItem> list) {
        this.trc20TransferInfo = list;
    }

    public void setTrc721ApprovalInfo(List<Trc20ApprovalItem> list) {
        this.trc721ApprovalInfo = list;
    }

    public void setTrc721TransferInfo(List<Trc20ApprovalItem> list) {
        this.trc721TransferInfo = list;
    }

    public void setTriggerContractType(int i) {
        this.triggerContractType = i;
    }

    public void setTrigger_info(TriggerInfoBean triggerInfoBean) {
        this.trigger_info = triggerInfoBean;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof TransactionInfoBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TransactionInfoBean) {
            TransactionInfoBean transactionInfoBean = (TransactionInfoBean) obj;
            if (transactionInfoBean.canEqual(this) && getBlock() == transactionInfoBean.getBlock() && getTimestamp() == transactionInfoBean.getTimestamp() && getContractType() == transactionInfoBean.getContractType() && getConfirmations() == transactionInfoBean.getConfirmations() && isConfirmed() == transactionInfoBean.isConfirmed() && isRevert() == transactionInfoBean.isRevert() && isRiskTransaction() == transactionInfoBean.isRiskTransaction() && getTriggerContractType() == transactionInfoBean.getTriggerContractType()) {
                String hash = getHash();
                String hash2 = transactionInfoBean.getHash();
                if (hash != null ? hash.equals(hash2) : hash2 == null) {
                    String ownerAddress = getOwnerAddress();
                    String ownerAddress2 = transactionInfoBean.getOwnerAddress();
                    if (ownerAddress != null ? ownerAddress.equals(ownerAddress2) : ownerAddress2 == null) {
                        String toAddress = getToAddress();
                        String toAddress2 = transactionInfoBean.getToAddress();
                        if (toAddress != null ? toAddress.equals(toAddress2) : toAddress2 == null) {
                            String contractRet = getContractRet();
                            String contractRet2 = transactionInfoBean.getContractRet();
                            if (contractRet != null ? contractRet.equals(contractRet2) : contractRet2 == null) {
                                ContractDataBean contractData = getContractData();
                                ContractDataBean contractData2 = transactionInfoBean.getContractData();
                                if (contractData != null ? contractData.equals(contractData2) : contractData2 == null) {
                                    String data = getData();
                                    String data2 = transactionInfoBean.getData();
                                    if (data != null ? data.equals(data2) : data2 == null) {
                                        CostBean cost = getCost();
                                        CostBean cost2 = transactionInfoBean.getCost();
                                        if (cost != null ? cost.equals(cost2) : cost2 == null) {
                                            TriggerInfoBean trigger_info = getTrigger_info();
                                            TriggerInfoBean trigger_info2 = transactionInfoBean.getTrigger_info();
                                            if (trigger_info != null ? trigger_info.equals(trigger_info2) : trigger_info2 == null) {
                                                InfoBean info = getInfo();
                                                InfoBean info2 = transactionInfoBean.getInfo();
                                                if (info != null ? info.equals(info2) : info2 == null) {
                                                    Map<String, Boolean> contract_map = getContract_map();
                                                    Map<String, Boolean> contract_map2 = transactionInfoBean.getContract_map();
                                                    if (contract_map != null ? contract_map.equals(contract_map2) : contract_map2 == null) {
                                                        List<?> signature_addresses = getSignature_addresses();
                                                        List<?> signature_addresses2 = transactionInfoBean.getSignature_addresses();
                                                        if (signature_addresses != null ? signature_addresses.equals(signature_addresses2) : signature_addresses2 == null) {
                                                            String contract_type = getContract_type();
                                                            String contract_type2 = transactionInfoBean.getContract_type();
                                                            if (contract_type != null ? contract_type.equals(contract_type2) : contract_type2 == null) {
                                                                List trc20TransferInfo = getTrc20TransferInfo();
                                                                List trc20TransferInfo2 = transactionInfoBean.getTrc20TransferInfo();
                                                                if (trc20TransferInfo != null ? trc20TransferInfo.equals(trc20TransferInfo2) : trc20TransferInfo2 == null) {
                                                                    List<Trc20ApprovalItem> trc20ApprovalInfo = getTrc20ApprovalInfo();
                                                                    List<Trc20ApprovalItem> trc20ApprovalInfo2 = transactionInfoBean.getTrc20ApprovalInfo();
                                                                    if (trc20ApprovalInfo != null ? trc20ApprovalInfo.equals(trc20ApprovalInfo2) : trc20ApprovalInfo2 == null) {
                                                                        List<Trc20ApprovalItem> trc721TransferInfo = getTrc721TransferInfo();
                                                                        List<Trc20ApprovalItem> trc721TransferInfo2 = transactionInfoBean.getTrc721TransferInfo();
                                                                        if (trc721TransferInfo != null ? trc721TransferInfo.equals(trc721TransferInfo2) : trc721TransferInfo2 == null) {
                                                                            List<Trc20ApprovalItem> trc721ApprovalInfo = getTrc721ApprovalInfo();
                                                                            List<Trc20ApprovalItem> trc721ApprovalInfo2 = transactionInfoBean.getTrc721ApprovalInfo();
                                                                            if (trc721ApprovalInfo != null ? trc721ApprovalInfo.equals(trc721ApprovalInfo2) : trc721ApprovalInfo2 == null) {
                                                                                Map internal_transactions = getInternal_transactions();
                                                                                Map internal_transactions2 = transactionInfoBean.getInternal_transactions();
                                                                                return internal_transactions != null ? internal_transactions.equals(internal_transactions2) : internal_transactions2 == null;
                                                                            }
                                                                            return false;
                                                                        }
                                                                        return false;
                                                                    }
                                                                    return false;
                                                                }
                                                                return false;
                                                            }
                                                            return false;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        long timestamp = getTimestamp();
        int block = ((((((((((((((getBlock() + 59) * 59) + ((int) (timestamp ^ (timestamp >>> 32)))) * 59) + getContractType()) * 59) + getConfirmations()) * 59) + (isConfirmed() ? 79 : 97)) * 59) + (isRevert() ? 79 : 97)) * 59) + (isRiskTransaction() ? 79 : 97)) * 59) + getTriggerContractType();
        String hash = getHash();
        int hashCode = (block * 59) + (hash == null ? 43 : hash.hashCode());
        String ownerAddress = getOwnerAddress();
        int hashCode2 = (hashCode * 59) + (ownerAddress == null ? 43 : ownerAddress.hashCode());
        String toAddress = getToAddress();
        int hashCode3 = (hashCode2 * 59) + (toAddress == null ? 43 : toAddress.hashCode());
        String contractRet = getContractRet();
        int hashCode4 = (hashCode3 * 59) + (contractRet == null ? 43 : contractRet.hashCode());
        ContractDataBean contractData = getContractData();
        int hashCode5 = (hashCode4 * 59) + (contractData == null ? 43 : contractData.hashCode());
        String data = getData();
        int hashCode6 = (hashCode5 * 59) + (data == null ? 43 : data.hashCode());
        CostBean cost = getCost();
        int hashCode7 = (hashCode6 * 59) + (cost == null ? 43 : cost.hashCode());
        TriggerInfoBean trigger_info = getTrigger_info();
        int hashCode8 = (hashCode7 * 59) + (trigger_info == null ? 43 : trigger_info.hashCode());
        InfoBean info = getInfo();
        int hashCode9 = (hashCode8 * 59) + (info == null ? 43 : info.hashCode());
        Map<String, Boolean> contract_map = getContract_map();
        int hashCode10 = (hashCode9 * 59) + (contract_map == null ? 43 : contract_map.hashCode());
        List<?> signature_addresses = getSignature_addresses();
        int hashCode11 = (hashCode10 * 59) + (signature_addresses == null ? 43 : signature_addresses.hashCode());
        String contract_type = getContract_type();
        int hashCode12 = (hashCode11 * 59) + (contract_type == null ? 43 : contract_type.hashCode());
        List trc20TransferInfo = getTrc20TransferInfo();
        int hashCode13 = (hashCode12 * 59) + (trc20TransferInfo == null ? 43 : trc20TransferInfo.hashCode());
        List<Trc20ApprovalItem> trc20ApprovalInfo = getTrc20ApprovalInfo();
        int hashCode14 = (hashCode13 * 59) + (trc20ApprovalInfo == null ? 43 : trc20ApprovalInfo.hashCode());
        List<Trc20ApprovalItem> trc721TransferInfo = getTrc721TransferInfo();
        int hashCode15 = (hashCode14 * 59) + (trc721TransferInfo == null ? 43 : trc721TransferInfo.hashCode());
        List<Trc20ApprovalItem> trc721ApprovalInfo = getTrc721ApprovalInfo();
        int hashCode16 = (hashCode15 * 59) + (trc721ApprovalInfo == null ? 43 : trc721ApprovalInfo.hashCode());
        Map internal_transactions = getInternal_transactions();
        return (hashCode16 * 59) + (internal_transactions != null ? internal_transactions.hashCode() : 43);
    }

    public static class ContractDataBean {
        private double amount;
        private long call_token_value;
        private long call_value;
        private String contract_address;
        private long frozen_balance;
        private String owner_address;
        private String resource;
        private String to_address;
        private long unfreeze_balance;

        public double getAmount() {
            return this.amount;
        }

        public long getCall_token_value() {
            return this.call_token_value;
        }

        public long getCall_value() {
            return this.call_value;
        }

        public String getContract_address() {
            return this.contract_address;
        }

        public long getFrozen_balance() {
            return this.frozen_balance;
        }

        public String getOwner_address() {
            return this.owner_address;
        }

        public String getResource() {
            return this.resource;
        }

        public String getTo_address() {
            return this.to_address;
        }

        public long getUnfreeze_balance() {
            return this.unfreeze_balance;
        }

        public void setAmount(double d) {
            this.amount = d;
        }

        public void setCall_token_value(long j) {
            this.call_token_value = j;
        }

        public void setCall_value(long j) {
            this.call_value = j;
        }

        public void setContract_address(String str) {
            this.contract_address = str;
        }

        public void setFrozen_balance(long j) {
            this.frozen_balance = j;
        }

        public void setOwner_address(String str) {
            this.owner_address = str;
        }

        public void setResource(String str) {
            this.resource = str;
        }

        public void setTo_address(String str) {
            this.to_address = str;
        }

        public void setUnfreeze_balance(long j) {
            this.unfreeze_balance = j;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof ContractDataBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ContractDataBean) {
                ContractDataBean contractDataBean = (ContractDataBean) obj;
                if (contractDataBean.canEqual(this) && Double.compare(getAmount(), contractDataBean.getAmount()) == 0 && getFrozen_balance() == contractDataBean.getFrozen_balance() && getUnfreeze_balance() == contractDataBean.getUnfreeze_balance() && getCall_value() == contractDataBean.getCall_value() && getCall_token_value() == contractDataBean.getCall_token_value()) {
                    String resource = getResource();
                    String resource2 = contractDataBean.getResource();
                    if (resource != null ? resource.equals(resource2) : resource2 == null) {
                        String owner_address = getOwner_address();
                        String owner_address2 = contractDataBean.getOwner_address();
                        if (owner_address != null ? owner_address.equals(owner_address2) : owner_address2 == null) {
                            String to_address = getTo_address();
                            String to_address2 = contractDataBean.getTo_address();
                            if (to_address != null ? to_address.equals(to_address2) : to_address2 == null) {
                                String contract_address = getContract_address();
                                String contract_address2 = contractDataBean.getContract_address();
                                return contract_address != null ? contract_address.equals(contract_address2) : contract_address2 == null;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(getAmount());
            long frozen_balance = getFrozen_balance();
            long unfreeze_balance = getUnfreeze_balance();
            long call_value = getCall_value();
            long call_token_value = getCall_token_value();
            String resource = getResource();
            int hashCode = ((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 59) * 59) + ((int) (frozen_balance ^ (frozen_balance >>> 32)))) * 59) + ((int) (unfreeze_balance ^ (unfreeze_balance >>> 32)))) * 59) + ((int) (call_value ^ (call_value >>> 32)))) * 59) + ((int) ((call_token_value >>> 32) ^ call_token_value))) * 59) + (resource == null ? 43 : resource.hashCode());
            String owner_address = getOwner_address();
            int hashCode2 = (hashCode * 59) + (owner_address == null ? 43 : owner_address.hashCode());
            String to_address = getTo_address();
            int hashCode3 = (hashCode2 * 59) + (to_address == null ? 43 : to_address.hashCode());
            String contract_address = getContract_address();
            return (hashCode3 * 59) + (contract_address != null ? contract_address.hashCode() : 43);
        }

        public String toString() {
            return "TransactionInfoBean.ContractDataBean(resource=" + getResource() + ", amount=" + getAmount() + ", owner_address=" + getOwner_address() + ", to_address=" + getTo_address() + ", contract_address=" + getContract_address() + ", frozen_balance=" + getFrozen_balance() + ", unfreeze_balance=" + getUnfreeze_balance() + ", call_value=" + getCall_value() + ", call_token_value=" + getCall_token_value() + ")";
        }
    }

    public static class CostBean {
        private int account_create_fee;
        private int asset_issue_fee;
        private int energy_fee;
        private int energy_fee_cost;
        private int energy_usage;
        private int energy_usage_total;
        private int exchange_create_fee;
        private int fee;
        private long memoFee;
        private int multi_sign_fee;
        private int net_fee;
        private int net_fee_cost;
        private int net_usage;
        private int origin_energy_usage;
        private int update_account_permission_fee;
        private long witness_create_fee;

        public int getAccount_create_fee() {
            return this.account_create_fee;
        }

        public int getAsset_issue_fee() {
            return this.asset_issue_fee;
        }

        public int getEnergy_fee() {
            return this.energy_fee;
        }

        public int getEnergy_fee_cost() {
            return this.energy_fee_cost;
        }

        public int getEnergy_usage() {
            return this.energy_usage;
        }

        public int getEnergy_usage_total() {
            return this.energy_usage_total;
        }

        public int getExchange_create_fee() {
            return this.exchange_create_fee;
        }

        public int getFee() {
            return this.fee;
        }

        public long getMemoFee() {
            return this.memoFee;
        }

        public int getMulti_sign_fee() {
            return this.multi_sign_fee;
        }

        public int getNet_fee() {
            return this.net_fee;
        }

        public int getNet_fee_cost() {
            return this.net_fee_cost;
        }

        public int getNet_usage() {
            return this.net_usage;
        }

        public int getOrigin_energy_usage() {
            return this.origin_energy_usage;
        }

        public int getUpdate_account_permission_fee() {
            return this.update_account_permission_fee;
        }

        public long getWitness_create_fee() {
            return this.witness_create_fee;
        }

        public void setAccount_create_fee(int i) {
            this.account_create_fee = i;
        }

        public void setAsset_issue_fee(int i) {
            this.asset_issue_fee = i;
        }

        public void setEnergy_fee(int i) {
            this.energy_fee = i;
        }

        public void setEnergy_fee_cost(int i) {
            this.energy_fee_cost = i;
        }

        public void setEnergy_usage(int i) {
            this.energy_usage = i;
        }

        public void setEnergy_usage_total(int i) {
            this.energy_usage_total = i;
        }

        public void setExchange_create_fee(int i) {
            this.exchange_create_fee = i;
        }

        public void setFee(int i) {
            this.fee = i;
        }

        public void setMemoFee(long j) {
            this.memoFee = j;
        }

        public void setMulti_sign_fee(int i) {
            this.multi_sign_fee = i;
        }

        public void setNet_fee(int i) {
            this.net_fee = i;
        }

        public void setNet_fee_cost(int i) {
            this.net_fee_cost = i;
        }

        public void setNet_usage(int i) {
            this.net_usage = i;
        }

        public void setOrigin_energy_usage(int i) {
            this.origin_energy_usage = i;
        }

        public void setUpdate_account_permission_fee(int i) {
            this.update_account_permission_fee = i;
        }

        public void setWitness_create_fee(long j) {
            this.witness_create_fee = j;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof CostBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof CostBean) {
                CostBean costBean = (CostBean) obj;
                return costBean.canEqual(this) && getNet_usage() == costBean.getNet_usage() && getNet_fee() == costBean.getNet_fee() && getEnergy_usage() == costBean.getEnergy_usage() && getEnergy_fee() == costBean.getEnergy_fee() && getOrigin_energy_usage() == costBean.getOrigin_energy_usage() && getEnergy_usage_total() == costBean.getEnergy_usage_total() && getAccount_create_fee() == costBean.getAccount_create_fee() && getMulti_sign_fee() == costBean.getMulti_sign_fee() && getNet_fee_cost() == costBean.getNet_fee_cost() && getFee() == costBean.getFee() && getEnergy_fee_cost() == costBean.getEnergy_fee_cost() && getAsset_issue_fee() == costBean.getAsset_issue_fee() && getWitness_create_fee() == costBean.getWitness_create_fee() && getExchange_create_fee() == costBean.getExchange_create_fee() && getUpdate_account_permission_fee() == costBean.getUpdate_account_permission_fee() && getMemoFee() == costBean.getMemoFee();
            }
            return false;
        }

        public int hashCode() {
            int net_usage = ((((((((((((((((((((((getNet_usage() + 59) * 59) + getNet_fee()) * 59) + getEnergy_usage()) * 59) + getEnergy_fee()) * 59) + getOrigin_energy_usage()) * 59) + getEnergy_usage_total()) * 59) + getAccount_create_fee()) * 59) + getMulti_sign_fee()) * 59) + getNet_fee_cost()) * 59) + getFee()) * 59) + getEnergy_fee_cost()) * 59) + getAsset_issue_fee();
            long witness_create_fee = getWitness_create_fee();
            int exchange_create_fee = (((((net_usage * 59) + ((int) (witness_create_fee ^ (witness_create_fee >>> 32)))) * 59) + getExchange_create_fee()) * 59) + getUpdate_account_permission_fee();
            long memoFee = getMemoFee();
            return (exchange_create_fee * 59) + ((int) ((memoFee >>> 32) ^ memoFee));
        }

        public String toString() {
            return "TransactionInfoBean.CostBean(net_usage=" + getNet_usage() + ", net_fee=" + getNet_fee() + ", energy_usage=" + getEnergy_usage() + ", energy_fee=" + getEnergy_fee() + ", origin_energy_usage=" + getOrigin_energy_usage() + ", energy_usage_total=" + getEnergy_usage_total() + ", account_create_fee=" + getAccount_create_fee() + ", multi_sign_fee=" + getMulti_sign_fee() + ", net_fee_cost=" + getNet_fee_cost() + ", fee=" + getFee() + ", energy_fee_cost=" + getEnergy_fee_cost() + ", asset_issue_fee=" + getAsset_issue_fee() + ", witness_create_fee=" + getWitness_create_fee() + ", exchange_create_fee=" + getExchange_create_fee() + ", update_account_permission_fee=" + getUpdate_account_permission_fee() + ", memoFee=" + getMemoFee() + ")";
        }
    }

    public static class TriggerInfoBean {
        private Integer call_value;
        private String contract_address;
        private String method;
        private Parameter parameter;

        public Integer getCall_value() {
            return this.call_value;
        }

        public String getContract_address() {
            return this.contract_address;
        }

        public String getMethod() {
            return this.method;
        }

        public Parameter getParameter() {
            return this.parameter;
        }

        public void setCall_value(Integer num) {
            this.call_value = num;
        }

        public void setContract_address(String str) {
            this.contract_address = str;
        }

        public void setMethod(String str) {
            this.method = str;
        }

        public void setParameter(Parameter parameter) {
            this.parameter = parameter;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof TriggerInfoBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TriggerInfoBean) {
                TriggerInfoBean triggerInfoBean = (TriggerInfoBean) obj;
                if (triggerInfoBean.canEqual(this)) {
                    Integer call_value = getCall_value();
                    Integer call_value2 = triggerInfoBean.getCall_value();
                    if (call_value != null ? call_value.equals(call_value2) : call_value2 == null) {
                        String method = getMethod();
                        String method2 = triggerInfoBean.getMethod();
                        if (method != null ? method.equals(method2) : method2 == null) {
                            Parameter parameter = getParameter();
                            Parameter parameter2 = triggerInfoBean.getParameter();
                            if (parameter != null ? parameter.equals(parameter2) : parameter2 == null) {
                                String contract_address = getContract_address();
                                String contract_address2 = triggerInfoBean.getContract_address();
                                return contract_address != null ? contract_address.equals(contract_address2) : contract_address2 == null;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            Integer call_value = getCall_value();
            int hashCode = call_value == null ? 43 : call_value.hashCode();
            String method = getMethod();
            int hashCode2 = ((hashCode + 59) * 59) + (method == null ? 43 : method.hashCode());
            Parameter parameter = getParameter();
            int hashCode3 = (hashCode2 * 59) + (parameter == null ? 43 : parameter.hashCode());
            String contract_address = getContract_address();
            return (hashCode3 * 59) + (contract_address != null ? contract_address.hashCode() : 43);
        }

        public String toString() {
            return "TransactionInfoBean.TriggerInfoBean(method=" + getMethod() + ", parameter=" + getParameter() + ", contract_address=" + getContract_address() + ", call_value=" + getCall_value() + ")";
        }

        public static class Parameter {
            private String _approved;
            private String _operator;

            public String get_approved() {
                return this._approved;
            }

            public String get_operator() {
                return this._operator;
            }

            public void set_approved(String str) {
                this._approved = str;
            }

            public void set_operator(String str) {
                this._operator = str;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof Parameter;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof Parameter) {
                    Parameter parameter = (Parameter) obj;
                    if (parameter.canEqual(this)) {
                        String str = get_operator();
                        String str2 = parameter.get_operator();
                        if (str != null ? str.equals(str2) : str2 == null) {
                            String str3 = get_approved();
                            String str4 = parameter.get_approved();
                            return str3 != null ? str3.equals(str4) : str4 == null;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }

            public int hashCode() {
                String str = get_operator();
                int hashCode = str == null ? 43 : str.hashCode();
                String str2 = get_approved();
                return ((hashCode + 59) * 59) + (str2 != null ? str2.hashCode() : 43);
            }

            public String toString() {
                return "TransactionInfoBean.TriggerInfoBean.Parameter(_operator=" + get_operator() + ", _approved=" + get_approved() + ")";
            }
        }
    }

    public class TokenTransferInfo {
        private String amount_str;
        private String contract_address;
        private Integer decimals;
        private String from_address;
        private String icon_url;
        private String level;
        private String name;
        private String symbol;
        private String to_address;
        private String type;
        private Boolean vip;

        public String getAmount_str() {
            return this.amount_str;
        }

        public String getContract_address() {
            return this.contract_address;
        }

        public Integer getDecimals() {
            return this.decimals;
        }

        public String getFrom_address() {
            return this.from_address;
        }

        public String getIcon_url() {
            return this.icon_url;
        }

        public String getLevel() {
            return this.level;
        }

        public String getName() {
            return this.name;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public String getTo_address() {
            return this.to_address;
        }

        public String getType() {
            return this.type;
        }

        public Boolean getVip() {
            return this.vip;
        }

        public void setAmount_str(String str) {
            this.amount_str = str;
        }

        public void setContract_address(String str) {
            this.contract_address = str;
        }

        public void setDecimals(Integer num) {
            this.decimals = num;
        }

        public void setFrom_address(String str) {
            this.from_address = str;
        }

        public void setIcon_url(String str) {
            this.icon_url = str;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setSymbol(String str) {
            this.symbol = str;
        }

        public void setTo_address(String str) {
            this.to_address = str;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setVip(Boolean bool) {
            this.vip = bool;
        }

        public TokenTransferInfo() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof TokenTransferInfo;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof TokenTransferInfo) {
                TokenTransferInfo tokenTransferInfo = (TokenTransferInfo) obj;
                if (tokenTransferInfo.canEqual(this)) {
                    Integer decimals = getDecimals();
                    Integer decimals2 = tokenTransferInfo.getDecimals();
                    if (decimals != null ? decimals.equals(decimals2) : decimals2 == null) {
                        Boolean vip = getVip();
                        Boolean vip2 = tokenTransferInfo.getVip();
                        if (vip != null ? vip.equals(vip2) : vip2 == null) {
                            String icon_url = getIcon_url();
                            String icon_url2 = tokenTransferInfo.getIcon_url();
                            if (icon_url != null ? icon_url.equals(icon_url2) : icon_url2 == null) {
                                String symbol = getSymbol();
                                String symbol2 = tokenTransferInfo.getSymbol();
                                if (symbol != null ? symbol.equals(symbol2) : symbol2 == null) {
                                    String level = getLevel();
                                    String level2 = tokenTransferInfo.getLevel();
                                    if (level != null ? level.equals(level2) : level2 == null) {
                                        String name = getName();
                                        String name2 = tokenTransferInfo.getName();
                                        if (name != null ? name.equals(name2) : name2 == null) {
                                            String to_address = getTo_address();
                                            String to_address2 = tokenTransferInfo.getTo_address();
                                            if (to_address != null ? to_address.equals(to_address2) : to_address2 == null) {
                                                String contract_address = getContract_address();
                                                String contract_address2 = tokenTransferInfo.getContract_address();
                                                if (contract_address != null ? contract_address.equals(contract_address2) : contract_address2 == null) {
                                                    String type = getType();
                                                    String type2 = tokenTransferInfo.getType();
                                                    if (type != null ? type.equals(type2) : type2 == null) {
                                                        String from_address = getFrom_address();
                                                        String from_address2 = tokenTransferInfo.getFrom_address();
                                                        if (from_address != null ? from_address.equals(from_address2) : from_address2 == null) {
                                                            String amount_str = getAmount_str();
                                                            String amount_str2 = tokenTransferInfo.getAmount_str();
                                                            return amount_str != null ? amount_str.equals(amount_str2) : amount_str2 == null;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            Integer decimals = getDecimals();
            int hashCode = decimals == null ? 43 : decimals.hashCode();
            Boolean vip = getVip();
            int hashCode2 = ((hashCode + 59) * 59) + (vip == null ? 43 : vip.hashCode());
            String icon_url = getIcon_url();
            int hashCode3 = (hashCode2 * 59) + (icon_url == null ? 43 : icon_url.hashCode());
            String symbol = getSymbol();
            int hashCode4 = (hashCode3 * 59) + (symbol == null ? 43 : symbol.hashCode());
            String level = getLevel();
            int hashCode5 = (hashCode4 * 59) + (level == null ? 43 : level.hashCode());
            String name = getName();
            int hashCode6 = (hashCode5 * 59) + (name == null ? 43 : name.hashCode());
            String to_address = getTo_address();
            int hashCode7 = (hashCode6 * 59) + (to_address == null ? 43 : to_address.hashCode());
            String contract_address = getContract_address();
            int hashCode8 = (hashCode7 * 59) + (contract_address == null ? 43 : contract_address.hashCode());
            String type = getType();
            int hashCode9 = (hashCode8 * 59) + (type == null ? 43 : type.hashCode());
            String from_address = getFrom_address();
            int hashCode10 = (hashCode9 * 59) + (from_address == null ? 43 : from_address.hashCode());
            String amount_str = getAmount_str();
            return (hashCode10 * 59) + (amount_str != null ? amount_str.hashCode() : 43);
        }

        public String toString() {
            return "TransactionInfoBean.TokenTransferInfo(icon_url=" + getIcon_url() + ", symbol=" + getSymbol() + ", level=" + getLevel() + ", decimals=" + getDecimals() + ", name=" + getName() + ", to_address=" + getTo_address() + ", contract_address=" + getContract_address() + ", type=" + getType() + ", vip=" + getVip() + ", from_address=" + getFrom_address() + ", amount_str=" + getAmount_str() + ")";
        }
    }

    public class Trc20ApprovalItem {
        @SerializedName("amount_str")
        private String amountStr;
        @SerializedName(TronConfig.CONTRACT_ADDRESS)
        private String contractAddress;
        @SerializedName("decimals")
        private Integer decimals;
        @SerializedName("from_address")
        private String fromAddress;
        @SerializedName("icon_url")
        private String iconUrl;
        @SerializedName(FirebaseAnalytics.Param.LEVEL)
        private String level;
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
        private String name;
        @SerializedName(NotificationCompat.CATEGORY_STATUS)
        private int status;
        @SerializedName("symbol")
        private String symbol;
        @SerializedName("to_address")
        private String toAddress;
        @SerializedName("type")
        private String type;
        @SerializedName("vip")
        private Boolean vip;

        public String getAmountStr() {
            return this.amountStr;
        }

        public String getContractAddress() {
            return this.contractAddress;
        }

        public Integer getDecimals() {
            return this.decimals;
        }

        public String getFromAddress() {
            return this.fromAddress;
        }

        public String getIconUrl() {
            return this.iconUrl;
        }

        public String getLevel() {
            return this.level;
        }

        public String getName() {
            return this.name;
        }

        public int getStatus() {
            return this.status;
        }

        public String getSymbol() {
            return this.symbol;
        }

        public String getToAddress() {
            return this.toAddress;
        }

        public String getType() {
            return this.type;
        }

        public Boolean getVip() {
            return this.vip;
        }

        public void setAmountStr(String str) {
            this.amountStr = str;
        }

        public void setContractAddress(String str) {
            this.contractAddress = str;
        }

        public void setDecimals(Integer num) {
            this.decimals = num;
        }

        public void setFromAddress(String str) {
            this.fromAddress = str;
        }

        public void setIconUrl(String str) {
            this.iconUrl = str;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public void setSymbol(String str) {
            this.symbol = str;
        }

        public void setToAddress(String str) {
            this.toAddress = str;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setVip(Boolean bool) {
            this.vip = bool;
        }

        public Trc20ApprovalItem() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Trc20ApprovalItem;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Trc20ApprovalItem) {
                Trc20ApprovalItem trc20ApprovalItem = (Trc20ApprovalItem) obj;
                if (trc20ApprovalItem.canEqual(this) && getStatus() == trc20ApprovalItem.getStatus()) {
                    Integer decimals = getDecimals();
                    Integer decimals2 = trc20ApprovalItem.getDecimals();
                    if (decimals != null ? decimals.equals(decimals2) : decimals2 == null) {
                        Boolean vip = getVip();
                        Boolean vip2 = trc20ApprovalItem.getVip();
                        if (vip != null ? vip.equals(vip2) : vip2 == null) {
                            String iconUrl = getIconUrl();
                            String iconUrl2 = trc20ApprovalItem.getIconUrl();
                            if (iconUrl != null ? iconUrl.equals(iconUrl2) : iconUrl2 == null) {
                                String symbol = getSymbol();
                                String symbol2 = trc20ApprovalItem.getSymbol();
                                if (symbol != null ? symbol.equals(symbol2) : symbol2 == null) {
                                    String level = getLevel();
                                    String level2 = trc20ApprovalItem.getLevel();
                                    if (level != null ? level.equals(level2) : level2 == null) {
                                        String name = getName();
                                        String name2 = trc20ApprovalItem.getName();
                                        if (name != null ? name.equals(name2) : name2 == null) {
                                            String toAddress = getToAddress();
                                            String toAddress2 = trc20ApprovalItem.getToAddress();
                                            if (toAddress != null ? toAddress.equals(toAddress2) : toAddress2 == null) {
                                                String contractAddress = getContractAddress();
                                                String contractAddress2 = trc20ApprovalItem.getContractAddress();
                                                if (contractAddress != null ? contractAddress.equals(contractAddress2) : contractAddress2 == null) {
                                                    String type = getType();
                                                    String type2 = trc20ApprovalItem.getType();
                                                    if (type != null ? type.equals(type2) : type2 == null) {
                                                        String fromAddress = getFromAddress();
                                                        String fromAddress2 = trc20ApprovalItem.getFromAddress();
                                                        if (fromAddress != null ? fromAddress.equals(fromAddress2) : fromAddress2 == null) {
                                                            String amountStr = getAmountStr();
                                                            String amountStr2 = trc20ApprovalItem.getAmountStr();
                                                            return amountStr != null ? amountStr.equals(amountStr2) : amountStr2 == null;
                                                        }
                                                        return false;
                                                    }
                                                    return false;
                                                }
                                                return false;
                                            }
                                            return false;
                                        }
                                        return false;
                                    }
                                    return false;
                                }
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            Integer decimals = getDecimals();
            int status = ((getStatus() + 59) * 59) + (decimals == null ? 43 : decimals.hashCode());
            Boolean vip = getVip();
            int hashCode = (status * 59) + (vip == null ? 43 : vip.hashCode());
            String iconUrl = getIconUrl();
            int hashCode2 = (hashCode * 59) + (iconUrl == null ? 43 : iconUrl.hashCode());
            String symbol = getSymbol();
            int hashCode3 = (hashCode2 * 59) + (symbol == null ? 43 : symbol.hashCode());
            String level = getLevel();
            int hashCode4 = (hashCode3 * 59) + (level == null ? 43 : level.hashCode());
            String name = getName();
            int hashCode5 = (hashCode4 * 59) + (name == null ? 43 : name.hashCode());
            String toAddress = getToAddress();
            int hashCode6 = (hashCode5 * 59) + (toAddress == null ? 43 : toAddress.hashCode());
            String contractAddress = getContractAddress();
            int hashCode7 = (hashCode6 * 59) + (contractAddress == null ? 43 : contractAddress.hashCode());
            String type = getType();
            int hashCode8 = (hashCode7 * 59) + (type == null ? 43 : type.hashCode());
            String fromAddress = getFromAddress();
            int hashCode9 = (hashCode8 * 59) + (fromAddress == null ? 43 : fromAddress.hashCode());
            String amountStr = getAmountStr();
            return (hashCode9 * 59) + (amountStr != null ? amountStr.hashCode() : 43);
        }

        public String toString() {
            return "TransactionInfoBean.Trc20ApprovalItem(iconUrl=" + getIconUrl() + ", symbol=" + getSymbol() + ", level=" + getLevel() + ", decimals=" + getDecimals() + ", name=" + getName() + ", toAddress=" + getToAddress() + ", contractAddress=" + getContractAddress() + ", type=" + getType() + ", vip=" + getVip() + ", fromAddress=" + getFromAddress() + ", amountStr=" + getAmountStr() + ", status=" + getStatus() + ")";
        }
    }

    public static class InfoBean {
        private long cancelAllUnfreezeV2Amount;
        private long unfreeze_amount;
        private long withdraw_amount;
        private long withdraw_expire_amount;

        public long getCancelAllUnfreezeV2Amount() {
            return this.cancelAllUnfreezeV2Amount;
        }

        public long getUnfreeze_amount() {
            return this.unfreeze_amount;
        }

        public long getWithdraw_amount() {
            return this.withdraw_amount;
        }

        public long getWithdraw_expire_amount() {
            return this.withdraw_expire_amount;
        }

        public void setCancelAllUnfreezeV2Amount(long j) {
            this.cancelAllUnfreezeV2Amount = j;
        }

        public void setUnfreeze_amount(long j) {
            this.unfreeze_amount = j;
        }

        public void setWithdraw_amount(long j) {
            this.withdraw_amount = j;
        }

        public void setWithdraw_expire_amount(long j) {
            this.withdraw_expire_amount = j;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof InfoBean;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof InfoBean) {
                InfoBean infoBean = (InfoBean) obj;
                return infoBean.canEqual(this) && getWithdraw_amount() == infoBean.getWithdraw_amount() && getUnfreeze_amount() == infoBean.getUnfreeze_amount() && getWithdraw_expire_amount() == infoBean.getWithdraw_expire_amount() && getCancelAllUnfreezeV2Amount() == infoBean.getCancelAllUnfreezeV2Amount();
            }
            return false;
        }

        public int hashCode() {
            long withdraw_amount = getWithdraw_amount();
            long unfreeze_amount = getUnfreeze_amount();
            long withdraw_expire_amount = getWithdraw_expire_amount();
            long cancelAllUnfreezeV2Amount = getCancelAllUnfreezeV2Amount();
            return ((((((((int) (withdraw_amount ^ (withdraw_amount >>> 32))) + 59) * 59) + ((int) (unfreeze_amount ^ (unfreeze_amount >>> 32)))) * 59) + ((int) (withdraw_expire_amount ^ (withdraw_expire_amount >>> 32)))) * 59) + ((int) ((cancelAllUnfreezeV2Amount >>> 32) ^ cancelAllUnfreezeV2Amount));
        }

        public String toString() {
            return "TransactionInfoBean.InfoBean(withdraw_amount=" + getWithdraw_amount() + ", unfreeze_amount=" + getUnfreeze_amount() + ", withdraw_expire_amount=" + getWithdraw_expire_amount() + ", cancelAllUnfreezeV2Amount=" + getCancelAllUnfreezeV2Amount() + ")";
        }
    }

    public static class ContractMapBean {
        private boolean TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo;
        private boolean TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY;

        public boolean isTGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo() {
            return this.TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo;
        }

        public boolean isTNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY() {
            return this.TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY;
        }

        public void setTGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo(boolean z) {
            this.TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo = z;
        }

        public void setTNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY(boolean z) {
            this.TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY = z;
        }

        public String toString() {
            return "ContractMapBean{TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY=" + this.TNh7swaW1BnYbJJoe6M36VaRbwh6vbhuoY + ", TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo=" + this.TGjdouJUAuCLbtf8dJtDHuQGfhuPfWcuGo + '}';
        }
    }

    public String toString() {
        return "TransactionInfoBean{block=" + this.block + ", hash='" + this.hash + "', timestamp=" + this.timestamp + ", ownerAddress='" + this.ownerAddress + "', contractType=" + this.contractType + ", toAddress='" + this.toAddress + "', riskTransaction='" + this.riskTransaction + "', confirmations=" + this.confirmations + ", confirmed=" + this.confirmed + ", revert=" + this.revert + ", contractRet='" + this.contractRet + "', contractData=" + this.contractData + ", data='" + this.data + "', cost=" + this.cost + ", trigger_info=" + this.trigger_info + ", info=" + this.info + ", contract_map=" + this.contract_map + ", signature_addresses=" + this.signature_addresses + '}';
    }
}
