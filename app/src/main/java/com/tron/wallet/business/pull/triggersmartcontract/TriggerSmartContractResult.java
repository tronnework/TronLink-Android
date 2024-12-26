package com.tron.wallet.business.pull.triggersmartcontract;

import com.tron.wallet.business.pull.PullResult;
public class TriggerSmartContractResult extends PullResult {
    private String transactionHash;

    public String getTransactionHash() {
        return this.transactionHash;
    }

    public void setTransactionHash(String str) {
        this.transactionHash = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof TriggerSmartContractResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TriggerSmartContractResult) {
            TriggerSmartContractResult triggerSmartContractResult = (TriggerSmartContractResult) obj;
            if (triggerSmartContractResult.canEqual(this)) {
                String transactionHash = getTransactionHash();
                String transactionHash2 = triggerSmartContractResult.getTransactionHash();
                return transactionHash != null ? transactionHash.equals(transactionHash2) : transactionHash2 == null;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        String transactionHash = getTransactionHash();
        return 59 + (transactionHash == null ? 43 : transactionHash.hashCode());
    }

    @Override
    public String toString() {
        return "TriggerSmartContractResult(transactionHash=" + getTransactionHash() + ")";
    }
}
