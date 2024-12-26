package com.tron.wallet.common.utils;

import android.text.TextUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import org.tron.protos.Protocol;
import org.tron.walletserver.PermissionException;
import org.tron.walletserver.Wallet;
public class SamsungMultisignUtils {
    public static boolean isSamsungMultisign(String str) {
        Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
        return !TextUtils.equals(str, WalletUtils.getSelectedWallet().getAddress()) && ((walletForAddress != null && walletForAddress.isSamsungWallet()) || WalletUtils.getSelectedWallet().isSamsungWallet());
    }

    public static boolean isSamsungWallet(String str) {
        Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
        return walletForAddress != null && walletForAddress.isSamsungWallet();
    }

    public static boolean isSamsungMultiOwner(String str, Protocol.Permission permission) {
        Wallet walletForAddress = WalletUtils.getWalletForAddress(str);
        if (walletForAddress != null) {
            try {
                if (walletForAddress.isSamsungWallet()) {
                    return !WalletUtils.checkHaveOwnerPermissions(str, permission);
                }
                return false;
            } catch (PermissionException e) {
                LogUtils.e(e);
            }
        }
        return false;
    }
}
