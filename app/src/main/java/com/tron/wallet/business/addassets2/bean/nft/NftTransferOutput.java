package com.tron.wallet.business.addassets2.bean.nft;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
public class NftTransferOutput {
    private int rangeTotal;
    private List<NftTransfer> token_transfers;
    private int total;

    public int getRangeTotal() {
        return this.rangeTotal;
    }

    public List<NftTransfer> getToken_transfers() {
        return this.token_transfers;
    }

    public int getTotal() {
        return this.total;
    }

    public void setRangeTotal(int i) {
        this.rangeTotal = i;
    }

    public void setToken_transfers(List<NftTransfer> list) {
        this.token_transfers = list;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NftTransferOutput;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NftTransferOutput) {
            NftTransferOutput nftTransferOutput = (NftTransferOutput) obj;
            if (nftTransferOutput.canEqual(this) && getTotal() == nftTransferOutput.getTotal() && getRangeTotal() == nftTransferOutput.getRangeTotal()) {
                List<NftTransfer> token_transfers = getToken_transfers();
                List<NftTransfer> token_transfers2 = nftTransferOutput.getToken_transfers();
                return token_transfers != null ? token_transfers.equals(token_transfers2) : token_transfers2 == null;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int total = ((getTotal() + 59) * 59) + getRangeTotal();
        List<NftTransfer> token_transfers = getToken_transfers();
        return (total * 59) + (token_transfers == null ? 43 : token_transfers.hashCode());
    }

    public String toString() {
        return "NftTransferOutput(total=" + getTotal() + ", rangeTotal=" + getRangeTotal() + ", token_transfers=" + getToken_transfers() + ")";
    }

    public static class NftTransfer implements Parcelable, Comparable<NftTransfer> {
        public static final Parcelable.Creator<NftTransfer> CREATOR = new Parcelable.Creator<NftTransfer>() {
            @Override
            public NftTransfer createFromParcel(Parcel parcel) {
                return new NftTransfer(parcel);
            }

            @Override
            public NftTransfer[] newArray(int i) {
                return new NftTransfer[i];
            }
        };
        private String approval_amount;
        private long block;
        private long block_ts;
        private Boolean confirmed;
        private String contractRet;
        private String contract_address;
        private String contract_type;
        private String event_type;
        private String finalResult;
        private Boolean fromAddressIsContract;
        private String from_address;
        private int nftConfirm;
        private String quant;
        private Boolean revert;
        private Boolean toAddressIsContract;
        private String to_address;
        private NftTokenInfo tokenInfo;
        private String transaction_id;

        @Override
        public int describeContents() {
            return 0;
        }

        public String getApproval_amount() {
            return this.approval_amount;
        }

        public long getBlock() {
            return this.block;
        }

        public long getBlock_ts() {
            return this.block_ts;
        }

        public Boolean getConfirmed() {
            return this.confirmed;
        }

        public String getContractRet() {
            return this.contractRet;
        }

        public String getContract_address() {
            return this.contract_address;
        }

        public String getContract_type() {
            return this.contract_type;
        }

        public String getEvent_type() {
            return this.event_type;
        }

        public String getFinalResult() {
            return this.finalResult;
        }

        public Boolean getFromAddressIsContract() {
            return this.fromAddressIsContract;
        }

        public String getFrom_address() {
            return this.from_address;
        }

        public int getNftConfirm() {
            return this.nftConfirm;
        }

        public String getQuant() {
            return this.quant;
        }

        public Boolean getRevert() {
            return this.revert;
        }

        public Boolean getToAddressIsContract() {
            return this.toAddressIsContract;
        }

        public String getTo_address() {
            return this.to_address;
        }

        public NftTokenInfo getTokenInfo() {
            return this.tokenInfo;
        }

        public String getTransaction_id() {
            return this.transaction_id;
        }

        public void setApproval_amount(String str) {
            this.approval_amount = str;
        }

        public void setBlock(long j) {
            this.block = j;
        }

        public void setBlock_ts(long j) {
            this.block_ts = j;
        }

        public void setConfirmed(Boolean bool) {
            this.confirmed = bool;
        }

        public void setContractRet(String str) {
            this.contractRet = str;
        }

        public void setContract_address(String str) {
            this.contract_address = str;
        }

        public void setContract_type(String str) {
            this.contract_type = str;
        }

        public void setEvent_type(String str) {
            this.event_type = str;
        }

        public void setFinalResult(String str) {
            this.finalResult = str;
        }

        public void setFromAddressIsContract(Boolean bool) {
            this.fromAddressIsContract = bool;
        }

        public void setFrom_address(String str) {
            this.from_address = str;
        }

        public void setNftConfirm(int i) {
            this.nftConfirm = i;
        }

        public void setQuant(String str) {
            this.quant = str;
        }

        public void setRevert(Boolean bool) {
            this.revert = bool;
        }

        public void setToAddressIsContract(Boolean bool) {
            this.toAddressIsContract = bool;
        }

        public void setTo_address(String str) {
            this.to_address = str;
        }

        public void setTokenInfo(NftTokenInfo nftTokenInfo) {
            this.tokenInfo = nftTokenInfo;
        }

        public void setTransaction_id(String str) {
            this.transaction_id = str;
        }

        public NftTransfer() {
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof NftTransfer;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof NftTransfer) {
                NftTransfer nftTransfer = (NftTransfer) obj;
                if (nftTransfer.canEqual(this) && getBlock_ts() == nftTransfer.getBlock_ts() && getBlock() == nftTransfer.getBlock() && getNftConfirm() == nftTransfer.getNftConfirm()) {
                    Boolean confirmed = getConfirmed();
                    Boolean confirmed2 = nftTransfer.getConfirmed();
                    if (confirmed != null ? confirmed.equals(confirmed2) : confirmed2 == null) {
                        Boolean fromAddressIsContract = getFromAddressIsContract();
                        Boolean fromAddressIsContract2 = nftTransfer.getFromAddressIsContract();
                        if (fromAddressIsContract != null ? fromAddressIsContract.equals(fromAddressIsContract2) : fromAddressIsContract2 == null) {
                            Boolean toAddressIsContract = getToAddressIsContract();
                            Boolean toAddressIsContract2 = nftTransfer.getToAddressIsContract();
                            if (toAddressIsContract != null ? toAddressIsContract.equals(toAddressIsContract2) : toAddressIsContract2 == null) {
                                Boolean revert = getRevert();
                                Boolean revert2 = nftTransfer.getRevert();
                                if (revert != null ? revert.equals(revert2) : revert2 == null) {
                                    String transaction_id = getTransaction_id();
                                    String transaction_id2 = nftTransfer.getTransaction_id();
                                    if (transaction_id != null ? transaction_id.equals(transaction_id2) : transaction_id2 == null) {
                                        String from_address = getFrom_address();
                                        String from_address2 = nftTransfer.getFrom_address();
                                        if (from_address != null ? from_address.equals(from_address2) : from_address2 == null) {
                                            String to_address = getTo_address();
                                            String to_address2 = nftTransfer.getTo_address();
                                            if (to_address != null ? to_address.equals(to_address2) : to_address2 == null) {
                                                String contract_address = getContract_address();
                                                String contract_address2 = nftTransfer.getContract_address();
                                                if (contract_address != null ? contract_address.equals(contract_address2) : contract_address2 == null) {
                                                    String quant = getQuant();
                                                    String quant2 = nftTransfer.getQuant();
                                                    if (quant != null ? quant.equals(quant2) : quant2 == null) {
                                                        String approval_amount = getApproval_amount();
                                                        String approval_amount2 = nftTransfer.getApproval_amount();
                                                        if (approval_amount != null ? approval_amount.equals(approval_amount2) : approval_amount2 == null) {
                                                            String event_type = getEvent_type();
                                                            String event_type2 = nftTransfer.getEvent_type();
                                                            if (event_type != null ? event_type.equals(event_type2) : event_type2 == null) {
                                                                String contract_type = getContract_type();
                                                                String contract_type2 = nftTransfer.getContract_type();
                                                                if (contract_type != null ? contract_type.equals(contract_type2) : contract_type2 == null) {
                                                                    String contractRet = getContractRet();
                                                                    String contractRet2 = nftTransfer.getContractRet();
                                                                    if (contractRet != null ? contractRet.equals(contractRet2) : contractRet2 == null) {
                                                                        String finalResult = getFinalResult();
                                                                        String finalResult2 = nftTransfer.getFinalResult();
                                                                        if (finalResult != null ? finalResult.equals(finalResult2) : finalResult2 == null) {
                                                                            NftTokenInfo tokenInfo = getTokenInfo();
                                                                            NftTokenInfo tokenInfo2 = nftTransfer.getTokenInfo();
                                                                            return tokenInfo != null ? tokenInfo.equals(tokenInfo2) : tokenInfo2 == null;
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
            long block_ts = getBlock_ts();
            long block = getBlock();
            int nftConfirm = ((((((int) (block_ts ^ (block_ts >>> 32))) + 59) * 59) + ((int) ((block >>> 32) ^ block))) * 59) + getNftConfirm();
            Boolean confirmed = getConfirmed();
            int hashCode = (nftConfirm * 59) + (confirmed == null ? 43 : confirmed.hashCode());
            Boolean fromAddressIsContract = getFromAddressIsContract();
            int hashCode2 = (hashCode * 59) + (fromAddressIsContract == null ? 43 : fromAddressIsContract.hashCode());
            Boolean toAddressIsContract = getToAddressIsContract();
            int hashCode3 = (hashCode2 * 59) + (toAddressIsContract == null ? 43 : toAddressIsContract.hashCode());
            Boolean revert = getRevert();
            int hashCode4 = (hashCode3 * 59) + (revert == null ? 43 : revert.hashCode());
            String transaction_id = getTransaction_id();
            int hashCode5 = (hashCode4 * 59) + (transaction_id == null ? 43 : transaction_id.hashCode());
            String from_address = getFrom_address();
            int hashCode6 = (hashCode5 * 59) + (from_address == null ? 43 : from_address.hashCode());
            String to_address = getTo_address();
            int hashCode7 = (hashCode6 * 59) + (to_address == null ? 43 : to_address.hashCode());
            String contract_address = getContract_address();
            int hashCode8 = (hashCode7 * 59) + (contract_address == null ? 43 : contract_address.hashCode());
            String quant = getQuant();
            int hashCode9 = (hashCode8 * 59) + (quant == null ? 43 : quant.hashCode());
            String approval_amount = getApproval_amount();
            int hashCode10 = (hashCode9 * 59) + (approval_amount == null ? 43 : approval_amount.hashCode());
            String event_type = getEvent_type();
            int hashCode11 = (hashCode10 * 59) + (event_type == null ? 43 : event_type.hashCode());
            String contract_type = getContract_type();
            int hashCode12 = (hashCode11 * 59) + (contract_type == null ? 43 : contract_type.hashCode());
            String contractRet = getContractRet();
            int hashCode13 = (hashCode12 * 59) + (contractRet == null ? 43 : contractRet.hashCode());
            String finalResult = getFinalResult();
            int hashCode14 = (hashCode13 * 59) + (finalResult == null ? 43 : finalResult.hashCode());
            NftTokenInfo tokenInfo = getTokenInfo();
            return (hashCode14 * 59) + (tokenInfo != null ? tokenInfo.hashCode() : 43);
        }

        public String toString() {
            return "NftTransferOutput.NftTransfer(transaction_id=" + getTransaction_id() + ", block_ts=" + getBlock_ts() + ", from_address=" + getFrom_address() + ", to_address=" + getTo_address() + ", block=" + getBlock() + ", contract_address=" + getContract_address() + ", quant=" + getQuant() + ", approval_amount=" + getApproval_amount() + ", event_type=" + getEvent_type() + ", contract_type=" + getContract_type() + ", confirmed=" + getConfirmed() + ", nftConfirm=" + getNftConfirm() + ", contractRet=" + getContractRet() + ", finalResult=" + getFinalResult() + ", tokenInfo=" + getTokenInfo() + ", fromAddressIsContract=" + getFromAddressIsContract() + ", toAddressIsContract=" + getToAddressIsContract() + ", revert=" + getRevert() + ")";
        }

        @Override
        public int compareTo(NftTransfer nftTransfer) {
            int i = ((nftTransfer.getBlock_ts() - getBlock_ts()) > 0L ? 1 : ((nftTransfer.getBlock_ts() - getBlock_ts()) == 0L ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }

        public static class NftTokenInfo implements Parcelable {
            public static final Parcelable.Creator<NftTokenInfo> CREATOR = new Parcelable.Creator<NftTokenInfo>() {
                @Override
                public NftTokenInfo createFromParcel(Parcel parcel) {
                    return new NftTokenInfo(parcel);
                }

                @Override
                public NftTokenInfo[] newArray(int i) {
                    return new NftTokenInfo[i];
                }
            };
            private String tokenAbbr;
            private int tokenCanShow;
            private int tokenDecimal;
            private String tokenId;
            private String tokenLogo;
            private String tokenName;
            private String tokenType;
            private Boolean vip;

            @Override
            public int describeContents() {
                return 0;
            }

            public String getTokenAbbr() {
                return this.tokenAbbr;
            }

            public int getTokenCanShow() {
                return this.tokenCanShow;
            }

            public int getTokenDecimal() {
                return this.tokenDecimal;
            }

            public String getTokenId() {
                return this.tokenId;
            }

            public String getTokenLogo() {
                return this.tokenLogo;
            }

            public String getTokenName() {
                return this.tokenName;
            }

            public String getTokenType() {
                return this.tokenType;
            }

            public Boolean getVip() {
                return this.vip;
            }

            public void setTokenAbbr(String str) {
                this.tokenAbbr = str;
            }

            public void setTokenCanShow(int i) {
                this.tokenCanShow = i;
            }

            public void setTokenDecimal(int i) {
                this.tokenDecimal = i;
            }

            public void setTokenId(String str) {
                this.tokenId = str;
            }

            public void setTokenLogo(String str) {
                this.tokenLogo = str;
            }

            public void setTokenName(String str) {
                this.tokenName = str;
            }

            public void setTokenType(String str) {
                this.tokenType = str;
            }

            public void setVip(Boolean bool) {
                this.vip = bool;
            }

            public NftTokenInfo() {
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof NftTokenInfo;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof NftTokenInfo) {
                    NftTokenInfo nftTokenInfo = (NftTokenInfo) obj;
                    if (nftTokenInfo.canEqual(this) && getTokenDecimal() == nftTokenInfo.getTokenDecimal() && getTokenCanShow() == nftTokenInfo.getTokenCanShow()) {
                        Boolean vip = getVip();
                        Boolean vip2 = nftTokenInfo.getVip();
                        if (vip != null ? vip.equals(vip2) : vip2 == null) {
                            String tokenId = getTokenId();
                            String tokenId2 = nftTokenInfo.getTokenId();
                            if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                                String tokenAbbr = getTokenAbbr();
                                String tokenAbbr2 = nftTokenInfo.getTokenAbbr();
                                if (tokenAbbr != null ? tokenAbbr.equals(tokenAbbr2) : tokenAbbr2 == null) {
                                    String tokenName = getTokenName();
                                    String tokenName2 = nftTokenInfo.getTokenName();
                                    if (tokenName != null ? tokenName.equals(tokenName2) : tokenName2 == null) {
                                        String tokenType = getTokenType();
                                        String tokenType2 = nftTokenInfo.getTokenType();
                                        if (tokenType != null ? tokenType.equals(tokenType2) : tokenType2 == null) {
                                            String tokenLogo = getTokenLogo();
                                            String tokenLogo2 = nftTokenInfo.getTokenLogo();
                                            return tokenLogo != null ? tokenLogo.equals(tokenLogo2) : tokenLogo2 == null;
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
                int tokenDecimal = ((getTokenDecimal() + 59) * 59) + getTokenCanShow();
                Boolean vip = getVip();
                int hashCode = (tokenDecimal * 59) + (vip == null ? 43 : vip.hashCode());
                String tokenId = getTokenId();
                int hashCode2 = (hashCode * 59) + (tokenId == null ? 43 : tokenId.hashCode());
                String tokenAbbr = getTokenAbbr();
                int hashCode3 = (hashCode2 * 59) + (tokenAbbr == null ? 43 : tokenAbbr.hashCode());
                String tokenName = getTokenName();
                int hashCode4 = (hashCode3 * 59) + (tokenName == null ? 43 : tokenName.hashCode());
                String tokenType = getTokenType();
                int hashCode5 = (hashCode4 * 59) + (tokenType == null ? 43 : tokenType.hashCode());
                String tokenLogo = getTokenLogo();
                return (hashCode5 * 59) + (tokenLogo != null ? tokenLogo.hashCode() : 43);
            }

            public String toString() {
                return "NftTransferOutput.NftTransfer.NftTokenInfo(tokenId=" + getTokenId() + ", tokenAbbr=" + getTokenAbbr() + ", tokenName=" + getTokenName() + ", tokenDecimal=" + getTokenDecimal() + ", tokenCanShow=" + getTokenCanShow() + ", tokenType=" + getTokenType() + ", tokenLogo=" + getTokenLogo() + ", vip=" + getVip() + ")";
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(this.tokenId);
                parcel.writeString(this.tokenAbbr);
                parcel.writeString(this.tokenName);
                parcel.writeValue(Integer.valueOf(this.tokenDecimal));
                parcel.writeValue(Integer.valueOf(this.tokenCanShow));
                parcel.writeString(this.tokenType);
                parcel.writeString(this.tokenLogo);
                parcel.writeValue(this.vip);
            }

            public void readFromParcel(Parcel parcel) {
                this.tokenId = parcel.readString();
                this.tokenAbbr = parcel.readString();
                this.tokenName = parcel.readString();
                this.tokenDecimal = ((Integer) parcel.readValue(Integer.class.getClassLoader())).intValue();
                this.tokenCanShow = ((Integer) parcel.readValue(Integer.class.getClassLoader())).intValue();
                this.tokenType = parcel.readString();
                this.tokenLogo = parcel.readString();
                this.vip = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            }

            protected NftTokenInfo(Parcel parcel) {
                this.tokenId = parcel.readString();
                this.tokenAbbr = parcel.readString();
                this.tokenName = parcel.readString();
                this.tokenDecimal = ((Integer) parcel.readValue(Integer.class.getClassLoader())).intValue();
                this.tokenCanShow = ((Integer) parcel.readValue(Integer.class.getClassLoader())).intValue();
                this.tokenType = parcel.readString();
                this.tokenLogo = parcel.readString();
                this.vip = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            }
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.transaction_id);
            parcel.writeValue(Long.valueOf(this.block_ts));
            parcel.writeString(this.from_address);
            parcel.writeString(this.to_address);
            parcel.writeValue(Long.valueOf(this.block));
            parcel.writeString(this.contract_address);
            parcel.writeString(this.quant);
            parcel.writeString(this.approval_amount);
            parcel.writeString(this.event_type);
            parcel.writeString(this.contract_type);
            parcel.writeValue(this.confirmed);
            parcel.writeString(this.contractRet);
            parcel.writeString(this.finalResult);
            parcel.writeParcelable(this.tokenInfo, i);
            parcel.writeValue(this.fromAddressIsContract);
            parcel.writeValue(this.toAddressIsContract);
            parcel.writeValue(this.revert);
        }

        public void readFromParcel(Parcel parcel) {
            this.transaction_id = parcel.readString();
            this.block_ts = ((Long) parcel.readValue(Long.class.getClassLoader())).longValue();
            this.from_address = parcel.readString();
            this.to_address = parcel.readString();
            this.block = ((Long) parcel.readValue(Long.class.getClassLoader())).longValue();
            this.contract_address = parcel.readString();
            this.quant = parcel.readString();
            this.approval_amount = parcel.readString();
            this.event_type = parcel.readString();
            this.contract_type = parcel.readString();
            this.confirmed = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            this.contractRet = parcel.readString();
            this.finalResult = parcel.readString();
            this.tokenInfo = (NftTokenInfo) parcel.readParcelable(NftTokenInfo.class.getClassLoader());
            this.fromAddressIsContract = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            this.toAddressIsContract = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            this.revert = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        }

        protected NftTransfer(Parcel parcel) {
            this.transaction_id = parcel.readString();
            this.block_ts = ((Long) parcel.readValue(Long.class.getClassLoader())).longValue();
            this.from_address = parcel.readString();
            this.to_address = parcel.readString();
            this.block = ((Long) parcel.readValue(Integer.class.getClassLoader())).longValue();
            this.contract_address = parcel.readString();
            this.quant = parcel.readString();
            this.approval_amount = parcel.readString();
            this.event_type = parcel.readString();
            this.contract_type = parcel.readString();
            this.confirmed = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            this.contractRet = parcel.readString();
            this.finalResult = parcel.readString();
            this.tokenInfo = (NftTokenInfo) parcel.readParcelable(NftTokenInfo.class.getClassLoader());
            this.fromAddressIsContract = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            this.toAddressIsContract = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            this.revert = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        }
    }
}
