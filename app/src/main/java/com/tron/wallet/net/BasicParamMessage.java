package com.tron.wallet.net;

import java.util.List;
public class BasicParamMessage extends NetMessage {
    private AddressInfo addressInfo;
    private List<AddressInfo> addressInfoList;
    private List<Integer> firebaseAddrTypes;
    private List<Integer> msgTypes;
    private List<Integer> wssAddrTypes;

    public AddressInfo getAddressInfo() {
        return this.addressInfo;
    }

    public List<AddressInfo> getAddressInfoList() {
        return this.addressInfoList;
    }

    public List<Integer> getFirebaseAddrTypes() {
        return this.firebaseAddrTypes;
    }

    public List<Integer> getMsgTypes() {
        return this.msgTypes;
    }

    public List<Integer> getWssAddrTypes() {
        return this.wssAddrTypes;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
    }

    public void setAddressInfoList(List<AddressInfo> list) {
        this.addressInfoList = list;
    }

    public void setFirebaseAddrTypes(List<Integer> list) {
        this.firebaseAddrTypes = list;
    }

    public void setMsgTypes(List<Integer> list) {
        this.msgTypes = list;
    }

    public void setWssAddrTypes(List<Integer> list) {
        this.wssAddrTypes = list;
    }

    @Override
    public String toString() {
        return "BasicParamMessage(addressInfo=" + getAddressInfo() + ", addressInfoList=" + getAddressInfoList() + ", msgTypes=" + getMsgTypes() + ", wssAddrTypes=" + getWssAddrTypes() + ", firebaseAddrTypes=" + getFirebaseAddrTypes() + ")";
    }

    @Override
    protected boolean canEqual(Object obj) {
        return obj instanceof BasicParamMessage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BasicParamMessage) {
            BasicParamMessage basicParamMessage = (BasicParamMessage) obj;
            if (basicParamMessage.canEqual(this) && super.equals(obj)) {
                AddressInfo addressInfo = getAddressInfo();
                AddressInfo addressInfo2 = basicParamMessage.getAddressInfo();
                if (addressInfo != null ? addressInfo.equals(addressInfo2) : addressInfo2 == null) {
                    List<AddressInfo> addressInfoList = getAddressInfoList();
                    List<AddressInfo> addressInfoList2 = basicParamMessage.getAddressInfoList();
                    if (addressInfoList != null ? addressInfoList.equals(addressInfoList2) : addressInfoList2 == null) {
                        List<Integer> msgTypes = getMsgTypes();
                        List<Integer> msgTypes2 = basicParamMessage.getMsgTypes();
                        if (msgTypes != null ? msgTypes.equals(msgTypes2) : msgTypes2 == null) {
                            List<Integer> wssAddrTypes = getWssAddrTypes();
                            List<Integer> wssAddrTypes2 = basicParamMessage.getWssAddrTypes();
                            if (wssAddrTypes != null ? wssAddrTypes.equals(wssAddrTypes2) : wssAddrTypes2 == null) {
                                List<Integer> firebaseAddrTypes = getFirebaseAddrTypes();
                                List<Integer> firebaseAddrTypes2 = basicParamMessage.getFirebaseAddrTypes();
                                return firebaseAddrTypes != null ? firebaseAddrTypes.equals(firebaseAddrTypes2) : firebaseAddrTypes2 == null;
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
        int hashCode = super.hashCode();
        AddressInfo addressInfo = getAddressInfo();
        int hashCode2 = (hashCode * 59) + (addressInfo == null ? 43 : addressInfo.hashCode());
        List<AddressInfo> addressInfoList = getAddressInfoList();
        int hashCode3 = (hashCode2 * 59) + (addressInfoList == null ? 43 : addressInfoList.hashCode());
        List<Integer> msgTypes = getMsgTypes();
        int hashCode4 = (hashCode3 * 59) + (msgTypes == null ? 43 : msgTypes.hashCode());
        List<Integer> wssAddrTypes = getWssAddrTypes();
        int hashCode5 = (hashCode4 * 59) + (wssAddrTypes == null ? 43 : wssAddrTypes.hashCode());
        List<Integer> firebaseAddrTypes = getFirebaseAddrTypes();
        return (hashCode5 * 59) + (firebaseAddrTypes != null ? firebaseAddrTypes.hashCode() : 43);
    }
}
