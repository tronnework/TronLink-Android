package com.tron.wallet.business.confirm.parambuilder.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.common.bean.token.TokenBean;
public class NFTParam extends BaseParam implements Parcelable {
    public static final Parcelable.Creator<NFTParam> CREATOR = new Parcelable.Creator<NFTParam>() {
        @Override
        public NFTParam createFromParcel(Parcel parcel) {
            return new NFTParam(parcel);
        }

        @Override
        public NFTParam[] newArray(int i) {
            return new NFTParam[i];
        }
    };
    private String contractAddress;
    private String fromAddress;
    private String name;
    private String nftImage;
    private String nftTokenImage;
    private String note;
    private String shortName;
    private String toAddress;
    public TokenBean tokenBean;
    private String tokenId;
    private String tokenShortName;
    private String walletAddress;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public String getFromAddress() {
        return this.fromAddress;
    }

    public String getName() {
        return this.name;
    }

    public String getNftImage() {
        return this.nftImage;
    }

    public String getNftTokenImage() {
        return this.nftTokenImage;
    }

    public String getNote() {
        return this.note;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getToAddress() {
        return this.toAddress;
    }

    public TokenBean getTokenBean() {
        return this.tokenBean;
    }

    public String getTokenId() {
        return this.tokenId;
    }

    public String getTokenShortName() {
        return this.tokenShortName;
    }

    public String getWalletAddress() {
        return this.walletAddress;
    }

    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    public void setFromAddress(String str) {
        this.fromAddress = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNftImage(String str) {
        this.nftImage = str;
    }

    public void setNftTokenImage(String str) {
        this.nftTokenImage = str;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public void setToAddress(String str) {
        this.toAddress = str;
    }

    public void setTokenBean(TokenBean tokenBean) {
        this.tokenBean = tokenBean;
    }

    public void setTokenId(String str) {
        this.tokenId = str;
    }

    public void setTokenShortName(String str) {
        this.tokenShortName = str;
    }

    public void setWalletAddress(String str) {
        this.walletAddress = str;
    }

    public String toString() {
        return "NFTParam(walletAddress=" + getWalletAddress() + ", contractAddress=" + getContractAddress() + ", tokenId=" + getTokenId() + ", shortName=" + getShortName() + ", nftImage=" + getNftImage() + ", nftTokenImage=" + getNftTokenImage() + ", fromAddress=" + getFromAddress() + ", toAddress=" + getToAddress() + ", name=" + getName() + ", tokenShortName=" + getTokenShortName() + ", note=" + getNote() + ", tokenBean=" + getTokenBean() + ")";
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof NFTParam;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof NFTParam) {
            NFTParam nFTParam = (NFTParam) obj;
            if (nFTParam.canEqual(this) && super.equals(obj)) {
                String walletAddress = getWalletAddress();
                String walletAddress2 = nFTParam.getWalletAddress();
                if (walletAddress != null ? walletAddress.equals(walletAddress2) : walletAddress2 == null) {
                    String contractAddress = getContractAddress();
                    String contractAddress2 = nFTParam.getContractAddress();
                    if (contractAddress != null ? contractAddress.equals(contractAddress2) : contractAddress2 == null) {
                        String tokenId = getTokenId();
                        String tokenId2 = nFTParam.getTokenId();
                        if (tokenId != null ? tokenId.equals(tokenId2) : tokenId2 == null) {
                            String shortName = getShortName();
                            String shortName2 = nFTParam.getShortName();
                            if (shortName != null ? shortName.equals(shortName2) : shortName2 == null) {
                                String nftImage = getNftImage();
                                String nftImage2 = nFTParam.getNftImage();
                                if (nftImage != null ? nftImage.equals(nftImage2) : nftImage2 == null) {
                                    String nftTokenImage = getNftTokenImage();
                                    String nftTokenImage2 = nFTParam.getNftTokenImage();
                                    if (nftTokenImage != null ? nftTokenImage.equals(nftTokenImage2) : nftTokenImage2 == null) {
                                        String fromAddress = getFromAddress();
                                        String fromAddress2 = nFTParam.getFromAddress();
                                        if (fromAddress != null ? fromAddress.equals(fromAddress2) : fromAddress2 == null) {
                                            String toAddress = getToAddress();
                                            String toAddress2 = nFTParam.getToAddress();
                                            if (toAddress != null ? toAddress.equals(toAddress2) : toAddress2 == null) {
                                                String name = getName();
                                                String name2 = nFTParam.getName();
                                                if (name != null ? name.equals(name2) : name2 == null) {
                                                    String tokenShortName = getTokenShortName();
                                                    String tokenShortName2 = nFTParam.getTokenShortName();
                                                    if (tokenShortName != null ? tokenShortName.equals(tokenShortName2) : tokenShortName2 == null) {
                                                        String note = getNote();
                                                        String note2 = nFTParam.getNote();
                                                        if (note != null ? note.equals(note2) : note2 == null) {
                                                            TokenBean tokenBean = getTokenBean();
                                                            TokenBean tokenBean2 = nFTParam.getTokenBean();
                                                            return tokenBean != null ? tokenBean.equals(tokenBean2) : tokenBean2 == null;
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
        int hashCode = super.hashCode();
        String walletAddress = getWalletAddress();
        int hashCode2 = (hashCode * 59) + (walletAddress == null ? 43 : walletAddress.hashCode());
        String contractAddress = getContractAddress();
        int hashCode3 = (hashCode2 * 59) + (contractAddress == null ? 43 : contractAddress.hashCode());
        String tokenId = getTokenId();
        int hashCode4 = (hashCode3 * 59) + (tokenId == null ? 43 : tokenId.hashCode());
        String shortName = getShortName();
        int hashCode5 = (hashCode4 * 59) + (shortName == null ? 43 : shortName.hashCode());
        String nftImage = getNftImage();
        int hashCode6 = (hashCode5 * 59) + (nftImage == null ? 43 : nftImage.hashCode());
        String nftTokenImage = getNftTokenImage();
        int hashCode7 = (hashCode6 * 59) + (nftTokenImage == null ? 43 : nftTokenImage.hashCode());
        String fromAddress = getFromAddress();
        int hashCode8 = (hashCode7 * 59) + (fromAddress == null ? 43 : fromAddress.hashCode());
        String toAddress = getToAddress();
        int hashCode9 = (hashCode8 * 59) + (toAddress == null ? 43 : toAddress.hashCode());
        String name = getName();
        int hashCode10 = (hashCode9 * 59) + (name == null ? 43 : name.hashCode());
        String tokenShortName = getTokenShortName();
        int hashCode11 = (hashCode10 * 59) + (tokenShortName == null ? 43 : tokenShortName.hashCode());
        String note = getNote();
        int hashCode12 = (hashCode11 * 59) + (note == null ? 43 : note.hashCode());
        TokenBean tokenBean = getTokenBean();
        return (hashCode12 * 59) + (tokenBean != null ? tokenBean.hashCode() : 43);
    }

    public NFTParam() {
    }

    protected NFTParam(Parcel parcel) {
        super(parcel);
        this.walletAddress = parcel.readString();
        this.contractAddress = parcel.readString();
        this.tokenBean = (TokenBean) parcel.readParcelable(TokenBean.class.getClassLoader());
        this.tokenId = parcel.readString();
        this.shortName = parcel.readString();
        this.nftImage = parcel.readString();
        this.nftTokenImage = parcel.readString();
        this.fromAddress = parcel.readString();
        this.toAddress = parcel.readString();
        this.note = parcel.readString();
        this.name = parcel.readString();
        this.tokenShortName = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.walletAddress);
        parcel.writeString(this.contractAddress);
        parcel.writeParcelable(this.tokenBean, i);
        parcel.writeString(this.tokenId);
        parcel.writeString(this.shortName);
        parcel.writeString(this.nftImage);
        parcel.writeString(this.nftTokenImage);
        parcel.writeString(this.fromAddress);
        parcel.writeString(this.toAddress);
        parcel.writeString(this.note);
        parcel.writeString(this.name);
        parcel.writeString(this.tokenShortName);
    }
}
