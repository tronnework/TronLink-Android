package com.tron.wallet.business.walletmanager.createaccount;

import android.util.Pair;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.walletmanager.createaccount.CreateAccountContract;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class CreateAccountModel implements CreateAccountContract.Model {
    private Map<String, Wallet> walletMap;

    @Override
    public Pair<WalletPath, Wallet> getNextWallet(String str) {
        this.walletMap = new HashMap();
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

    @Override
    public Wallet getHDWallet(String str) {
        Map<String, Wallet> map = this.walletMap;
        if (map == null || map.size() == 0) {
            this.walletMap = new HashMap();
            Collection.-EL.stream(WalletUtils.getAllWallets()).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getHDWallet$1((Wallet) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        return getNextHdWallet(str, new WalletPath());
    }

    public void lambda$getHDWallet$1(Wallet wallet) {
        this.walletMap.put(wallet.getAddress(), wallet);
    }

    private Wallet getNextHdWallet(String str, WalletPath walletPath) {
        Wallet wallet = new Wallet(str, walletPath);
        if (this.walletMap.containsKey(wallet.getAddress())) {
            return WalletUtils.getWalletForAddress(wallet.getAddress());
        }
        walletPath.accountIndex++;
        return getNextHdWallet(str, walletPath);
    }

    private Pair<WalletPath, Wallet> _getNextWallet(String str, WalletPath walletPath, Map<String, Wallet> map) {
        Wallet wallet = new Wallet(str, walletPath);
        if (map.containsKey(wallet.getAddress())) {
            walletPath.accountIndex++;
            return _getNextWallet(str, walletPath, map);
        }
        return new Pair<>(walletPath, wallet);
    }

    @Override
    public Observable<AccountBalanceOutput> getBalances(List<? extends Wallet> list) {
        HashMap hashMap = new HashMap();
        for (Wallet wallet : list) {
            hashMap.put(wallet.getAddress(), 5);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(list.get(0).getAddress(), 2, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(arrayList))).compose(RxSchedulers.io_main());
    }

    @Override
    public boolean existWallet(String str) {
        Map<String, Wallet> map = this.walletMap;
        if (map != null) {
            return map.containsKey(str);
        }
        return true;
    }
}
