package com.tron.wallet.business.assetshome;

import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.functions.Consumer;
import java.util.HashSet;
import org.tron.walletserver.Wallet;
public class AssetsAnimatorHelper {
    private static String currentAddress;
    private static boolean initialized;
    private static HashSet<String> hadStartAnimatorKeys = new HashSet<>();
    private static RxManager rxManager = new RxManager();

    public static void init() {
        if (initialized) {
            return;
        }
        initialized = true;
        final Wallet selectedWallet = WalletUtils.getSelectedWallet();
        if (selectedWallet != null) {
            currentAddress = selectedWallet.getAddress();
        }
        rxManager.on(Event.SELECTEDWALLET, new Consumer() {
            @Override
            public final void accept(Object obj) {
                AssetsAnimatorHelper.lambda$init$0(Wallet.this, obj);
            }
        });
    }

    public static void lambda$init$0(Wallet wallet, Object obj) throws Exception {
        Wallet selectedWallet = WalletUtils.getSelectedWallet();
        String str = currentAddress;
        if (str == null || wallet == null || str.equals(selectedWallet.getAddress())) {
            return;
        }
        currentAddress = selectedWallet.getAddress();
        hadStartAnimatorKeys.clear();
    }

    public static boolean shouldStartAnimator(String str) {
        if (hadStartAnimatorKeys.contains(str)) {
            return false;
        }
        hadStartAnimatorKeys.add(str);
        return true;
    }
}
