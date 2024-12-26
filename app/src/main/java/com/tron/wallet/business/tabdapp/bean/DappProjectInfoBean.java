package com.tron.wallet.business.tabdapp.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
public class DappProjectInfoBean implements Parcelable {
    public static final Parcelable.Creator<DappProjectInfoBean> CREATOR = new Parcelable.Creator<DappProjectInfoBean>() {
        @Override
        public DappProjectInfoBean createFromParcel(Parcel parcel) {
            return new DappProjectInfoBean(parcel);
        }

        @Override
        public DappProjectInfoBean[] newArray(int i) {
            return new DappProjectInfoBean[i];
        }
    };
    @JsonProperty("contractAddress")
    private String contractAddress;
    @JsonProperty("projectLogo")
    private String projectLogo;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("projectUrl")
    private String projectUrl;
    @JsonProperty("white")
    private boolean white;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContractAddress() {
        return this.contractAddress;
    }

    public String getProjectLogo() {
        return this.projectLogo;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectUrl() {
        return this.projectUrl;
    }

    public boolean isWhite() {
        return this.white;
    }

    @JsonProperty("contractAddress")
    public void setContractAddress(String str) {
        this.contractAddress = str;
    }

    @JsonProperty("projectLogo")
    public void setProjectLogo(String str) {
        this.projectLogo = str;
    }

    @JsonProperty("projectName")
    public void setProjectName(String str) {
        this.projectName = str;
    }

    @JsonProperty("projectUrl")
    public void setProjectUrl(String str) {
        this.projectUrl = str;
    }

    @JsonProperty("white")
    public void setWhite(boolean z) {
        this.white = z;
    }

    public DappProjectInfoBean() {
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof DappProjectInfoBean;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DappProjectInfoBean) {
            DappProjectInfoBean dappProjectInfoBean = (DappProjectInfoBean) obj;
            if (dappProjectInfoBean.canEqual(this) && isWhite() == dappProjectInfoBean.isWhite()) {
                String contractAddress = getContractAddress();
                String contractAddress2 = dappProjectInfoBean.getContractAddress();
                if (contractAddress != null ? contractAddress.equals(contractAddress2) : contractAddress2 == null) {
                    String projectName = getProjectName();
                    String projectName2 = dappProjectInfoBean.getProjectName();
                    if (projectName != null ? projectName.equals(projectName2) : projectName2 == null) {
                        String projectLogo = getProjectLogo();
                        String projectLogo2 = dappProjectInfoBean.getProjectLogo();
                        if (projectLogo != null ? projectLogo.equals(projectLogo2) : projectLogo2 == null) {
                            String projectUrl = getProjectUrl();
                            String projectUrl2 = dappProjectInfoBean.getProjectUrl();
                            return projectUrl != null ? projectUrl.equals(projectUrl2) : projectUrl2 == null;
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
        int i = isWhite() ? 79 : 97;
        String contractAddress = getContractAddress();
        int hashCode = ((i + 59) * 59) + (contractAddress == null ? 43 : contractAddress.hashCode());
        String projectName = getProjectName();
        int hashCode2 = (hashCode * 59) + (projectName == null ? 43 : projectName.hashCode());
        String projectLogo = getProjectLogo();
        int hashCode3 = (hashCode2 * 59) + (projectLogo == null ? 43 : projectLogo.hashCode());
        String projectUrl = getProjectUrl();
        return (hashCode3 * 59) + (projectUrl != null ? projectUrl.hashCode() : 43);
    }

    public String toString() {
        return "DappProjectInfoBean(contractAddress=" + getContractAddress() + ", projectName=" + getProjectName() + ", projectLogo=" + getProjectLogo() + ", projectUrl=" + getProjectUrl() + ", white=" + isWhite() + ")";
    }

    protected DappProjectInfoBean(Parcel parcel) {
        this.contractAddress = parcel.readString();
        this.projectName = parcel.readString();
        this.projectLogo = parcel.readString();
        this.projectUrl = parcel.readString();
        this.white = parcel.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.contractAddress);
        parcel.writeString(this.projectName);
        parcel.writeString(this.projectLogo);
        parcel.writeString(this.projectUrl);
        parcel.writeByte(this.white ? (byte) 1 : (byte) 0);
    }
}
