package com.tron.wallet.business.pull.transfer;

import com.tron.wallet.business.pull.PullParam;
public class TransferParam extends PullParam {
    private String amount;
    private String contract;
    private String from;
    private String memo;
    private String symbol;
    private String to;
    private String tokenId;

    public String getAmount() {
        return this.amount;
    }

    public String getContract() {
        return this.contract;
    }

    public String getFrom() {
        return this.from;
    }

    public String getMemo() {
        return this.memo;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getTo() {
        return this.to;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setContract(String str) {
        this.contract = str;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setMemo(String str) {
        this.memo = str;
    }

    public void setSymbol(String str) {
        this.symbol = str;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof TransferParam;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof TransferParam) {
            TransferParam transferParam = (TransferParam) obj;
            if (transferParam.canEqual(this)) {
                String symbol = getSymbol();
                String symbol2 = transferParam.getSymbol();
                if (symbol != null ? symbol.equals(symbol2) : symbol2 == null) {
                    String amount = getAmount();
                    String amount2 = transferParam.getAmount();
                    if (amount != null ? amount.equals(amount2) : amount2 == null) {
                        String from = getFrom();
                        String from2 = transferParam.getFrom();
                        if (from != null ? from.equals(from2) : from2 == null) {
                            String to = getTo();
                            String to2 = transferParam.getTo();
                            if (to != null ? to.equals(to2) : to2 == null) {
                                String memo = getMemo();
                                String memo2 = transferParam.getMemo();
                                if (memo != null ? memo.equals(memo2) : memo2 == null) {
                                    String tokenId = getTokenId();
                                    String tokenId2 = transferParam.getTokenId();
                                    if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                                        String contract = getContract();
                                        String contract2 = transferParam.getContract();
                                        return contract != null ? contract.equals(contract2) : contract2 == null;
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

    @Override
    public int hashCode() {
        String symbol = getSymbol();
        int hashCode = symbol == null ? 43 : symbol.hashCode();
        String amount = getAmount();
        int hashCode2 = ((hashCode + 59) * 59) + (amount == null ? 43 : amount.hashCode());
        String from = getFrom();
        int hashCode3 = (hashCode2 * 59) + (from == null ? 43 : from.hashCode());
        String to = getTo();
        int hashCode4 = (hashCode3 * 59) + (to == null ? 43 : to.hashCode());
        String memo = getMemo();
        int hashCode5 = (hashCode4 * 59) + (memo == null ? 43 : memo.hashCode());
        String tokenId = getTokenId();
        int hashCode6 = (hashCode5 * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String contract = getContract();
        return (hashCode6 * 59) + (contract != null ? contract.hashCode() : 43);
    }

    @Override
    public String toString() {
        return "TransferParam(symbol=" + getSymbol() + ", amount=" + getAmount() + ", from=" + getFrom() + ", to=" + getTo() + ", memo=" + getMemo() + ", tokenId=" + getTokenId() + ", contract=" + getContract() + ")";
    }
}
