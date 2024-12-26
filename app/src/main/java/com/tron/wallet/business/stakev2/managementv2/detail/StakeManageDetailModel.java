package com.tron.wallet.business.stakev2.managementv2.detail;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailContract;
import com.tron.wallet.business.stakev2.managementv2.detail.StakeManageDetailModel;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailForMeOutput;
import com.tron.wallet.business.stakev2.managementv2.detail.bean.StakeResourceDetailOutput;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
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
public class StakeManageDetailModel implements StakeManageDetailContract.Model {
    @Override
    public Observable<StakeResourceDetailOutput> getStakeResourceForOther(String str, int i, boolean z, long j) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDelegateForOther(str, i, z, j).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<StakeResourceDetailForMeOutput> getStakeResourceForMe(String str, int i, int i2) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getDelegateForMe(str, i, i2).compose(RxSchedulers.io_main());
    }

    class fun1 implements Callable<HashMap<String, NameAddressExtraBean>> {
        fun1() {
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
                    return StakeManageDetailModel.1.lambda$call$1((Wallet) obj);
                }
            }).sorted(new Comparator() {
                @Override
                public final int compare(Object obj, Object obj2) {
                    return StakeManageDetailModel.1.lambda$call$2((Wallet) obj, (Wallet) obj2);
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
        return Observable.fromCallable(new fun1());
    }
}
