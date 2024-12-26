package com.tron.wallet.common.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.tron.wallet.business.tabmy.myhome.settings.bean.MainNodeOutput;
import com.tron.wallet.common.utils.StringTronUtil;
public class ChainBean implements Parcelable {
    public static final Parcelable.Creator<ChainBean> CREATOR = new Parcelable.Creator<ChainBean>() {
        @Override
        public ChainBean createFromParcel(Parcel parcel) {
            return new ChainBean(parcel);
        }

        @Override
        public ChainBean[] newArray(int i) {
            return new ChainBean[i];
        }
    };
    public String chainId;
    public String chainName;
    public String eventServer;
    public String fullNode;
    public boolean isMainChain;
    public boolean isSelect;
    public String mainChainContract;
    public String sideChainContract;
    public String solidityNode;

    @Override
    public int describeContents() {
        return 0;
    }

    public ChainBean() {
    }

    public byte[] getSideChainContract() {
        if (StringTronUtil.isEmpty(this.sideChainContract)) {
            return null;
        }
        return StringTronUtil.decode58Check(this.sideChainContract);
    }

    public byte[] getMainChainContract() {
        if (StringTronUtil.isEmpty(this.mainChainContract)) {
            return null;
        }
        return StringTronUtil.decode58Check(this.mainChainContract);
    }

    public String toString() {
        return "ChainBean{chainName='" + this.chainName + "', fullNode='" + this.fullNode + "', solidityNode='" + this.solidityNode + "', chainId='" + this.chainId + "', isSelect=" + this.isSelect + ", isMainChain=" + this.isMainChain + ", sideChainContract='" + this.sideChainContract + "', mainChainContract='" + this.mainChainContract + "', eventServer='" + this.eventServer + "'}";
    }

    public ChainBean(MainNodeOutput.DataBean dataBean) {
        if (dataBean != null) {
            this.chainName = dataBean.chainName;
            this.fullNode = (dataBean.fullNode == null || dataBean.fullNode.size() == 0) ? "" : dataBean.fullNode.get(0);
            this.solidityNode = (dataBean.solidityNode == null || dataBean.solidityNode.size() == 0) ? "" : dataBean.solidityNode.get(0);
            this.chainId = dataBean.chainId != null ? dataBean.chainId : "";
            this.isMainChain = dataBean.isMainChain == 1;
            this.sideChainContract = dataBean.sideChainContract;
            this.mainChainContract = dataBean.mainChainContract;
            this.eventServer = dataBean.eventServer;
        }
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.chainName);
        parcel.writeString(this.fullNode);
        parcel.writeString(this.solidityNode);
        parcel.writeString(this.chainId);
        parcel.writeByte(this.isSelect ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isMainChain ? (byte) 1 : (byte) 0);
        parcel.writeString(this.sideChainContract);
        parcel.writeString(this.mainChainContract);
        parcel.writeString(this.eventServer);
    }

    protected ChainBean(Parcel parcel) {
        this.chainName = parcel.readString();
        this.fullNode = parcel.readString();
        this.solidityNode = parcel.readString();
        this.chainId = parcel.readString();
        this.isSelect = parcel.readByte() != 0;
        this.isMainChain = parcel.readByte() != 0;
        this.sideChainContract = parcel.readString();
        this.mainChainContract = parcel.readString();
        this.eventServer = parcel.readString();
    }
}
