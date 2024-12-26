package com.tron.wallet.business.transfer;

import com.google.gson.Gson;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.transfer.TokenDetailContract;
import com.tron.wallet.business.transfer.TokenDetailModel;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.bean.token.TransferOutput;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
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
public class TokenDetailModel implements TokenDetailContract.Model {
    @Override
    public Observable<TransferOutput> getTRXTransfer(String str, int i, int i2, long j, long j2, int i3, boolean z) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRXTransfer(str, i, i2, j, j2, i3, z, true, false, 1).flatMap(new Function<Object, Observable<TransferOutput>>() {
            @Override
            public Observable<TransferOutput> apply(Object obj) throws Exception {
                return Observable.just((TransferOutput) new Gson().fromJson((String) obj,  TransferOutput.class));
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransferOutput> getTRX10Transfer(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRX10Transfer(str, i, i2, j, j2, i3, z, true, false, str2, 1).flatMap(new Function<Object, Observable<TransferOutput>>() {
            @Override
            public Observable<TransferOutput> apply(Object obj) throws Exception {
                return Observable.just((TransferOutput) new Gson().fromJson((String) obj,  TransferOutput.class));
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransferOutput> getTRXTransfer20(String str, int i, int i2, long j, long j2, int i3, boolean z, String str2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getTRX20Transfer(str, i, i2, j, j2, i3, z, true, false, str2, 1).flatMap(new Function<Object, Observable<TransferOutput>>() {
            @Override
            public Observable<TransferOutput> apply(Object obj) throws Exception {
                return Observable.just((TransferOutput) new Gson().fromJson((String) obj,  TransferOutput.class));
            }
        }).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<TransferOutput> getWithdrawOrDeposit(String str, String str2, String str3, int i, int i2, String str4) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).withdrawOrdeposit(str, str2, str3, i, i2, str4).compose(RxSchedulers.io_main());
    }

    class fun4 implements Callable<HashMap<String, NameAddressExtraBean>> {
        fun4() {
        }

        @Override
        public HashMap<String, NameAddressExtraBean> call() throws Exception {
            HashMap<String, NameAddressExtraBean> hashMap = new HashMap<>();
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
                    return TokenDetailModel.4.lambda$call$1((Wallet) obj);
                }
            }).sorted(new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return TokenDetailModel.4.lambda$call$2((Wallet) obj, (Wallet) obj2);
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

        public static boolean lambda$call$1(Wallet wallet) {
            return (wallet == null || wallet.isShieldedWallet()) ? false : true;
        }

        public static int lambda$call$2(Wallet wallet, Wallet wallet2) {
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

    @Override
    public Observable<HashMap<String, NameAddressExtraBean>> getAllAddress() {
        return Observable.fromCallable(new fun4());
    }
}
