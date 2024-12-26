package com.tron.wallet.business.walletmanager.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.common.AddressItem;
import com.tron.wallet.business.walletmanager.common.CommonKey;
import com.tron.wallet.business.walletmanager.selectaddress.SelectAnotherAddressActivity;
import com.tron.wallet.common.bean.AccountBalanceOutput;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public abstract class BaseLoadAddressAction<T extends Wallet> implements LoadAddressAction<T> {
    protected Set<String> walletAddresses;

    @Override
    public Observable<AccountBalanceOutput> getBalance(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, 5);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(str, 2, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(arrayList))).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<AccountBalanceOutput> getBalances(ArrayList<String> arrayList) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < arrayList.size(); i++) {
            hashMap.put(arrayList.get(i), 5);
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(hashMap);
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getAccountInfosList(arrayList.get(0), 2, RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(arrayList2))).compose(RxSchedulers.io_main());
    }

    @Override
    public WalletPath getWalletPath(Wallet wallet) {
        if (wallet != null && !TextUtils.isEmpty(wallet.getMnemonicPathString())) {
            try {
                return (WalletPath) JSON.parseObject(wallet.getMnemonicPathString(), WalletPath.class);
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return null;
    }

    public void startChooseAddress(Context context, Bundle bundle, WalletPath walletPath, AddressItem addressItem, boolean z, int i) {
        if (bundle == null || context == null) {
            return;
        }
        if (walletPath == null) {
            WalletPath.createDefault();
        }
        String string = bundle.getString(TronConfig.IMPORT_CONTENT);
        String string2 = bundle.getString(TronConfig.IMPORT_NAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add(addressItem);
        SelectAnotherAddressActivity.start(context, string, arrayList, z, false, CommonKey.REQUEST_CODE_CHANGE_ADDRESS, i, false, string2, "", false);
    }

    @Override
    public Observable<Set<String>> getAllExistsWalletAddress() {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$getAllExistsWalletAddress$1(observableEmitter);
            }
        });
    }

    public void lambda$getAllExistsWalletAddress$1(ObservableEmitter observableEmitter) throws Exception {
        if (this.walletAddresses == null) {
            this.walletAddresses = new HashSet();
            Collection.-EL.stream(WalletUtils.getWalletNames()).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$getAllExistsWalletAddress$0((String) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        observableEmitter.onNext(this.walletAddresses);
        observableEmitter.onComplete();
    }

    public void lambda$getAllExistsWalletAddress$0(String str) {
        Wallet wallet = WalletUtils.getWallet(str);
        if (wallet != null) {
            this.walletAddresses.add(wallet.getAddress());
        }
    }
}
