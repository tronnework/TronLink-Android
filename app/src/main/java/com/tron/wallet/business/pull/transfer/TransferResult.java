package com.tron.wallet.business.pull.transfer;

import com.tron.wallet.business.pull.PullResult;
public class TransferResult extends PullResult {
    private String address;
    private String data;
    private boolean isSuccessful;
    private String transactionHash;

    public String getAddress() {
        return this.address;
    }

    public String getData() {
        return this.data;
    }

    public String getTransactionHash() {
        return this.transactionHash;
    }

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setData(String str) {
        this.data = str;
    }

    public void setSuccessful(boolean z) {
        this.isSuccessful = z;
    }

    public void setTransactionHash(String str) {
        this.transactionHash = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof TransferResult;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TransferResult) {
            TransferResult transferResult = (TransferResult) obj;
            if (transferResult.canEqual(this) && isSuccessful() == transferResult.isSuccessful()) {
                String address = getAddress();
                String address2 = transferResult.getAddress();
                if (address != null ? address.equals(address2) : address2 == null) {
                    String data = getData();
                    String data2 = transferResult.getData();
                    if (data != null ? data.equals(data2) : data2 == null) {
                        String transactionHash = getTransactionHash();
                        String transactionHash2 = transferResult.getTransactionHash();
                        return transactionHash != null ? transactionHash.equals(transactionHash2) : transactionHash2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int i = isSuccessful() ? 79 : 97;
        String address = getAddress();
        int hashCode = ((i + 59) * 59) + (address == null ? 43 : address.hashCode());
        String data = getData();
        int hashCode2 = (hashCode * 59) + (data == null ? 43 : data.hashCode());
        String transactionHash = getTransactionHash();
        return (hashCode2 * 59) + (transactionHash != null ? transactionHash.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "TransferResult(address=" + getAddress() + ", data=" + getData() + ", transactionHash=" + getTransactionHash() + ", isSuccessful=" + isSuccessful() + ")";
    }
}
