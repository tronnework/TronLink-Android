package com.tron.wallet.business.stakev2.managementv2;

import com.tron.wallet.business.stakev2.managementv2.ResourceManagementV2Contract;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.rpc.TronAPI;
import io.reactivex.Observable;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.tron.walletserver.Wallet;
public class ResourceManagementV2Model implements ResourceManagementV2Contract.Model {
    @Override
    public Observable<Long> getCanDelegatedResourceMaxSize(final int i, final String str) {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                Long valueOf;
                valueOf = Long.valueOf(TronAPI.getCanDelegatedMaxSize(i, str).getMaxSize());
                return valueOf;
            }
        });
    }

    @Override
    public Observable<HashMap<String, NameAddressExtraBean>> getAllAddress() {
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return ResourceManagementV2Model.lambda$getAllAddress$5();
            }
        });
    }

    public static HashMap lambda$getAllAddress$5() throws Exception {
        HashMap hashMap = new HashMap();
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(AddressController.getInstance().searchAll()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                arrayList.add(NameAddressExtraBean.fromAddressBook((AddressDao) obj));
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        for (int i = 0; i < arrayList.size(); i++) {
            hashMap.put(((NameAddressExtraBean) arrayList.get(i)).getAddress().toString(), (NameAddressExtraBean) arrayList.get(i));
        }
        final ArrayList arrayList2 = new ArrayList();
        Collection.-EL.stream(WalletUtils.getAllWallets()).filter(new Predicate() {
            public Predicate and(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            public Predicate negate() {
                return Predicate$-CC.$default$negate(this);
            }

            public Predicate or(Predicate predicate) {
                return Objects.requireNonNull(predicate);
            }

            @Override
            public final boolean test(Object obj) {
                return ResourceManagementV2Model.lambda$getAllAddress$2((Wallet) obj);
            }
        }).sorted(new Comparator() {
            @Override
            public final int compare(Object obj, Object obj2) {
                return ResourceManagementV2Model.lambda$getAllAddress$3((Wallet) obj, (Wallet) obj2);
            }
        }).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                arrayList2.add(NameAddressExtraBean.fromWallet((Wallet) obj));
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            NameAddressExtraBean nameAddressExtraBean = (NameAddressExtraBean) arrayList2.get(i2);
            if (!hashMap.containsKey(nameAddressExtraBean.getAddress().toString())) {
                hashMap.put(nameAddressExtraBean.getAddress().toString(), nameAddressExtraBean);
            }
        }
        return hashMap;
    }

    public static boolean lambda$getAllAddress$2(Wallet wallet) {
        return (wallet == null || wallet.isShieldedWallet()) ? false : true;
    }

    public static int lambda$getAllAddress$3(Wallet wallet, Wallet wallet2) {
        int value = WalletGroupType.getGroupType(wallet).getValue() - WalletGroupType.getGroupType(wallet2).getValue();
        if (value != 0) {
            return value;
        }
        int i = ((wallet.getCreateTime() - wallet2.getCreateTime()) > 0L ? 1 : ((wallet.getCreateTime() - wallet2.getCreateTime()) == 0L ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        return i < 0 ? -1 : 1;
    }
}
