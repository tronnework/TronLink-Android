package com.tron.wallet.business.walletmanager.importwallet;

import android.util.Pair;
import com.alibaba.fastjson.JSON;
import com.tron.wallet.business.walletmanager.importwallet.SelectMnemonicAddressContract;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.db.wallet.WalletUtils;
import j$.util.Collection;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class SelectMnemonicAddressModel extends SelectMnemonicAddressContract.Model {
    private HashMap<String, Wallet> walletMap;

    @Override
    public Pair<WalletPath, Wallet> getNextWallet(String str) {
        this.walletMap = new HashMap<>();
        Collection.-EL.stream(WalletUtils.getAllWallets()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getNextWallet$0((Wallet) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return _getNextWallet(str, new WalletPath(), this.walletMap);
    }

    public void lambda$getNextWallet$0(Wallet wallet) {
        this.walletMap.put(wallet.getAddress(), wallet);
    }

    private Pair<WalletPath, Wallet> _getNextWallet(String str, WalletPath walletPath, Map<String, Wallet> map) {
        Wallet wallet = new Wallet(str, walletPath);
        wallet.setAddress(wallet.getAddress());
        wallet.setCreateTime(System.currentTimeMillis());
        wallet.setCreateType(1);
        wallet.setIconRes(UserIconRandom.THIS.random());
        wallet.setMnemonicPath(JSON.toJSONString(walletPath));
        wallet.setMnemonic(str);
        wallet.setBackUp(true);
        wallet.setMnemonicLength(str.trim().split("\\s+").length);
        wallet.setCreateType(1);
        if (map.containsKey(wallet.getAddress())) {
            walletPath.accountIndex++;
            return _getNextWallet(str, walletPath, map);
        }
        return new Pair<>(walletPath, wallet);
    }
}
