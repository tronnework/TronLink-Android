package com.tron.wallet.business.tabdapp.jsbridge.finance.beans;
public class RecordDetailInput {
    private String amount;
    private int decimals;
    private String from;
    private String hash;
    private int jump;
    private String to;
    private String tokenContractAddress;
    private String tokenId;
    private String tokenSymbol;
    private int tokenType;

    public String getAmount() {
        return this.amount;
    }

    public int getDecimals() {
        return this.decimals;
    }

    public String getFrom() {
        return this.from;
    }

    public String getHash() {
        return this.hash;
    }

    public int getJump() {
        return this.jump;
    }

    public String getTo() {
        return this.to;
    }

    public String getTokenContractAddress() {
        return this.tokenContractAddress;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public String getTokenSymbol() {
        return this.tokenSymbol;
    }

    public int getTokenType() {
        return this.tokenType;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public void setDecimals(int i) {
        this.decimals = i;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setHash(String str) {
        this.hash = str;
    }

    public void setJump(int i) {
        this.jump = i;
    }

    public void setTo(String str) {
        this.to = str;
    }

    public void setTokenContractAddress(String str) {
        this.tokenContractAddress = str;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setTokenSymbol(String str) {
        this.tokenSymbol = str;
    }

    public void setTokenType(int i) {
        this.tokenType = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof RecordDetailInput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RecordDetailInput) {
            RecordDetailInput recordDetailInput = (RecordDetailInput) obj;
            if (recordDetailInput.canEqual(this) && getTokenType() == recordDetailInput.getTokenType() && getDecimals() == recordDetailInput.getDecimals() && getJump() == recordDetailInput.getJump()) {
                String tokenId = getTokenId();
                String tokenId2 = recordDetailInput.getTokenId();
                if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                    String tokenContractAddress = getTokenContractAddress();
                    String tokenContractAddress2 = recordDetailInput.getTokenContractAddress();
                    if (tokenContractAddress != null ? tokenContractAddress.equals(tokenContractAddress2) : tokenContractAddress2 == null) {
                        String amount = getAmount();
                        String amount2 = recordDetailInput.getAmount();
                        if (amount != null ? amount.equals(amount2) : amount2 == null) {
                            String hash = getHash();
                            String hash2 = recordDetailInput.getHash();
                            if (hash != null ? hash.equals(hash2) : hash2 == null) {
                                String from = getFrom();
                                String from2 = recordDetailInput.getFrom();
                                if (from != null ? from.equals(from2) : from2 == null) {
                                    String to = getTo();
                                    String to2 = recordDetailInput.getTo();
                                    if (to != null ? to.equals(to2) : to2 == null) {
                                        String tokenSymbol = getTokenSymbol();
                                        String tokenSymbol2 = recordDetailInput.getTokenSymbol();
                                        return tokenSymbol != null ? tokenSymbol.equals(tokenSymbol2) : tokenSymbol2 == null;
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
        int tokenType = ((((getTokenType() + 59) * 59) + getDecimals()) * 59) + getJump();
        String tokenId = getTokenId();
        int hashCode = (tokenType * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String tokenContractAddress = getTokenContractAddress();
        int hashCode2 = (hashCode * 59) + (tokenContractAddress == null ? 43 : tokenContractAddress.hashCode());
        String amount = getAmount();
        int hashCode3 = (hashCode2 * 59) + (amount == null ? 43 : amount.hashCode());
        String hash = getHash();
        int hashCode4 = (hashCode3 * 59) + (hash == null ? 43 : hash.hashCode());
        String from = getFrom();
        int hashCode5 = (hashCode4 * 59) + (from == null ? 43 : from.hashCode());
        String to = getTo();
        int hashCode6 = (hashCode5 * 59) + (to == null ? 43 : to.hashCode());
        String tokenSymbol = getTokenSymbol();
        return (hashCode6 * 59) + (tokenSymbol != null ? tokenSymbol.hashCode() : 43);
    }

    public String toString() {
        return "RecordDetailInput(tokenType=" + getTokenType() + ", tokenId=" + getTokenId() + ", tokenContractAddress=" + getTokenContractAddress() + ", amount=" + getAmount() + ", hash=" + getHash() + ", decimals=" + getDecimals() + ", from=" + getFrom() + ", to=" + getTo() + ", tokenSymbol=" + getTokenSymbol() + ", jump=" + getJump() + ")";
    }
}
