package org.tron.walletserver;

import com.google.gson.Gson;
import j$.util.Objects;
import java.io.Serializable;
import org.tron.common.utils.GsonFormatUtils;
import org.tron.common.utils.LogUtils;
public class WalletPath implements Serializable {
    private static final long serialVersionUID = 95504495;
    public int account;
    public int accountIndex;
    public int change;
    public int purpose = 44;
    public int coinType = 195;

    public int getAccount() {
        return this.account;
    }

    public int getAccountIndex() {
        return this.accountIndex;
    }

    public int getChange() {
        return this.change;
    }

    public int getCoinType() {
        return this.coinType;
    }

    public int getPurpose() {
        return this.purpose;
    }

    public void setAccount(int i) {
        this.account = i;
    }

    public void setAccountIndex(int i) {
        this.accountIndex = i;
    }

    public void setChange(int i) {
        this.change = i;
    }

    public void setCoinType(int i) {
        this.coinType = i;
    }

    public void setPurpose(int i) {
        this.purpose = i;
    }

    public String toString() {
        return "WalletPath(purpose=" + getPurpose() + ", coinType=" + getCoinType() + ", account=" + getAccount() + ", change=" + getChange() + ", accountIndex=" + getAccountIndex() + ")";
    }

    public static WalletPath createDefault() {
        return createDefault(0);
    }

    public static WalletPath createDefault(int i) {
        WalletPath walletPath = new WalletPath();
        walletPath.account = 0;
        walletPath.change = 0;
        walletPath.accountIndex = i;
        return walletPath;
    }

    public boolean equals(Object obj) {
        if (obj instanceof WalletPath) {
            WalletPath walletPath = (WalletPath) obj;
            return this.purpose == walletPath.purpose && this.coinType == walletPath.coinType && this.accountIndex == walletPath.accountIndex && this.account == walletPath.account && this.change == walletPath.change;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.purpose), Integer.valueOf(this.coinType), Integer.valueOf(this.accountIndex), Integer.valueOf(this.account), Integer.valueOf(this.change));
    }

    public static String buildPathString(WalletPath walletPath) {
        if (walletPath == null) {
            return "";
        }
        return "m/" + buildPath(walletPath);
    }

    public static String buildPath(String str) {
        WalletPath walletPath;
        if (AddressUtil.isEmpty(str)) {
            return "";
        }
        try {
            walletPath = (WalletPath) GsonFormatUtils.gsonToBean(str, WalletPath.class);
        } catch (Exception e) {
            LogUtils.e(e);
            walletPath = new WalletPath();
        }
        return buildPath(walletPath);
    }

    public static String buildPath(WalletPath walletPath) {
        if (walletPath == null) {
            return "";
        }
        return walletPath.purpose + "'/" + walletPath.coinType + "'/" + walletPath.account + "'/" + walletPath.change + "/" + walletPath.accountIndex;
    }

    public static WalletPath buildWalletPath(String str) {
        if (!AddressUtil.isEmpty(str)) {
            try {
                return (WalletPath) new Gson().fromJson(str,  WalletPath.class);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return new WalletPath();
    }
}
