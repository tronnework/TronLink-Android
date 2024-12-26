package com.tron.wallet.business.walletmanager.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.tron.wallet.ledger.LedgerWallet;
import java.io.Serializable;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class AddressItem implements Serializable, Parcelable {
    public static final Parcelable.Creator<AddressItem> CREATOR = new Parcelable.Creator<AddressItem>() {
        @Override
        public AddressItem createFromParcel(Parcel parcel) {
            return new AddressItem(parcel);
        }

        @Override
        public AddressItem[] newArray(int i) {
            return new AddressItem[i];
        }
    };
    public static final double INIT_BALANCE = -1.0d;
    private static final long serialVersionUID = 191547936;
    private String address;
    private double balance;
    private boolean hasImported;
    private int index;
    private transient boolean isCheckBoxEnable;
    private boolean isExisted;
    private transient boolean isNonHd;
    private boolean isSelected;
    private WalletPath mnemonicPath;
    private String pathStr;

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.address;
    }

    public double getBalance() {
        return this.balance;
    }

    public int getIndex() {
        return this.index;
    }

    public WalletPath getMnemonicPath() {
        return this.mnemonicPath;
    }

    public String getPathStr() {
        return this.pathStr;
    }

    public boolean isCheckBoxEnable() {
        return this.isCheckBoxEnable;
    }

    public boolean isExisted() {
        return this.isExisted;
    }

    public boolean isHasImported() {
        return this.hasImported;
    }

    public boolean isNonHd() {
        return this.isNonHd;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setBalance(double d) {
        this.balance = d;
    }

    public void setCheckBoxEnable(boolean z) {
        this.isCheckBoxEnable = z;
    }

    public void setExisted(boolean z) {
        this.isExisted = z;
    }

    public void setHasImported(boolean z) {
        this.hasImported = z;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setMnemonicPath(WalletPath walletPath) {
        this.mnemonicPath = walletPath;
    }

    public void setNonHd(boolean z) {
        this.isNonHd = z;
    }

    public void setPathStr(String str) {
        this.pathStr = str;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String toString() {
        return "AddressItem(address=" + getAddress() + ", balance=" + getBalance() + ", index=" + getIndex() + ", isSelected=" + isSelected() + ", isExisted=" + isExisted() + ", mnemonicPath=" + getMnemonicPath() + ", pathStr=" + getPathStr() + ", hasImported=" + isHasImported() + ", isCheckBoxEnable=" + isCheckBoxEnable() + ", isNonHd=" + isNonHd() + ")";
    }

    protected AddressItem() {
        this.address = "";
        this.balance = -1.0d;
        this.isExisted = false;
        this.pathStr = "";
        this.hasImported = false;
        this.isCheckBoxEnable = true;
        this.isNonHd = false;
    }

    protected AddressItem(Parcel parcel) {
        this.address = "";
        this.balance = -1.0d;
        this.isExisted = false;
        this.pathStr = "";
        this.hasImported = false;
        this.isCheckBoxEnable = true;
        this.isNonHd = false;
        this.address = parcel.readString();
        this.balance = parcel.readDouble();
        this.index = parcel.readInt();
        this.isSelected = parcel.readByte() != 0;
        this.isExisted = parcel.readByte() != 0;
        this.pathStr = parcel.readString();
        this.hasImported = parcel.readByte() != 0;
        this.mnemonicPath = (WalletPath) parcel.readSerializable();
    }

    public static AddressItem fromWallet(Wallet wallet) {
        WalletPath walletPath;
        if (wallet == null) {
            return new AddressItem();
        }
        AddressItem addressItem = new AddressItem();
        addressItem.setSelected(false);
        addressItem.setAddress(wallet.getAddress());
        addressItem.setBalance(-1.0d);
        if (!TextUtils.isEmpty(wallet.getMnemonicPathString()) && (walletPath = (WalletPath) JSON.parseObject(wallet.getMnemonicPathString(), WalletPath.class)) != null) {
            addressItem.setMnemonicPath(walletPath);
            if (LedgerWallet.isLedger(wallet)) {
                addressItem.setIndex(walletPath.account);
            } else {
                addressItem.setIndex(walletPath.accountIndex);
            }
            addressItem.setPathStr(WalletPath.buildPathString(walletPath));
        }
        return addressItem;
    }

    public boolean isAddressEqual(AddressItem addressItem) {
        String str = this.address;
        return (str != null) && (addressItem != null) && str.equals(addressItem.getAddress());
    }

    public boolean equals(Object obj) {
        if (obj instanceof AddressItem) {
            AddressItem addressItem = (AddressItem) obj;
            return this.address == addressItem.address && this.pathStr == addressItem.pathStr && this.isSelected == addressItem.isSelected && this.isExisted == addressItem.isExisted && this.mnemonicPath == addressItem.mnemonicPath;
        }
        return false;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.address);
        parcel.writeDouble(this.balance);
        parcel.writeInt(this.index);
        parcel.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.isExisted ? (byte) 1 : (byte) 0);
        parcel.writeString(this.pathStr);
        parcel.writeByte(this.hasImported ? (byte) 1 : (byte) 0);
        parcel.writeSerializable(this.mnemonicPath);
    }
}
