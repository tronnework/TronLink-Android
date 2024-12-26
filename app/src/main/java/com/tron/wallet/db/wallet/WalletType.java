package com.tron.wallet.db.wallet;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.db.SpAPI;
import java.util.HashMap;
import java.util.Map;
import org.tron.walletserver.Wallet;
public class WalletType {
    private static final int Cold = 9;
    private static final int Ledger_import = 8;
    private static final int Mnemonic_create = 1;
    private static final int Mnemonic_import = 11;
    public static final int NORMAL = 1;
    private static final int Observed_import = 3;
    public static final int SHIELD = 3;
    private static final int WALLET_TYPE_CREATE = 1;
    private static final int WALLET_TYPE_IMPORT = 2;
    private static final int WALLET_TYPE_LEDGER = 8;
    private static final int WALLET_TYPE_WATCH = 3;
    public static final int WATCH = 2;
    public static final String Watch = "2";
    public static final String WatchCold = "1";
    private static final int privateKey_import = 2;

    private static int getWalletNetType(Wallet wallet) {
        if (wallet == null || wallet.getAddress() == null) {
            return -100;
        }
        int createType = wallet.getCreateType();
        Map<String, String> realWatchWalletsMap = SpAPI.THIS.getRealWatchWalletsMap();
        if (wallet.isWatchOnly() && createType != 8) {
            return ((realWatchWalletsMap.containsKey(wallet.getAddress()) && "1".equals(realWatchWalletsMap.get(wallet.getAddress()))) || wallet.getCreateType() == 9) ? 9 : 3;
        }
        switch (createType) {
            case 0:
                return 1;
            case 1:
            case 7:
                return 11;
            case 2:
            case 3:
            case 5:
            case 6:
                return 2;
            case 4:
            default:
                return -100;
            case 8:
                return 8;
        }
    }

    public static int getType(Wallet wallet) {
        if (wallet != null) {
            if (wallet.isWatchCold()) {
                return 9;
            }
            if (wallet.isWatchOnly()) {
                return 2;
            }
            if (wallet.isShieldedWallet()) {
                return 3;
            }
        }
        return 1;
    }

    public static boolean showWatchWalletReminderTip(org.tron.walletserver.Wallet r5) {
        


return true;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.db.wallet.WalletType.showWatchWalletReminderTip(org.tron.walletserver.Wallet):boolean");
    }

    public static void removeWatchWallet(Wallet wallet) {
        try {
            Map<String, String> watchWalletReminderTimeMap = SpAPI.THIS.getWatchWalletReminderTimeMap();
            String address = wallet.getAddress();
            if (watchWalletReminderTimeMap.containsKey(address)) {
                watchWalletReminderTimeMap.remove(address);
                SpAPI.THIS.setWatchWalletReminderTimeMap(watchWalletReminderTimeMap);
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static void setShowWatchWalletReminderTip(Wallet wallet) {
        Map<String, String> watchWalletReminderTimeMap = SpAPI.THIS.getWatchWalletReminderTimeMap();
        if (watchWalletReminderTimeMap == null) {
            watchWalletReminderTimeMap = new HashMap<>();
        }
        String address = wallet.getAddress();
        watchWalletReminderTimeMap.put(address, "" + System.currentTimeMillis());
        SpAPI.THIS.setWatchWalletReminderTimeMap(watchWalletReminderTimeMap);
    }

    public static int getTypeForNodeInfo(Wallet wallet) {
        return getWalletNetType(wallet);
    }
}
