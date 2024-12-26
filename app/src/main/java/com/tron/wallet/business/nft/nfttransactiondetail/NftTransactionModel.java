package com.tron.wallet.business.nft.nfttransactiondetail;

import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionContract;
import com.tron.wallet.business.nft.nfttransactiondetail.NftTransactionModel;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Stream;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.tron.walletserver.Wallet;
public class NftTransactionModel implements NftTransactionContract.Model {

    class fun1 implements ObservableSource<NameAddressResultBean> {
        final String val$address;

        fun1(String str) {
            this.val$address = str;
        }

        @Override
        public void subscribe(Observer<? super NameAddressResultBean> observer) {
            AddressDao addressDaoByAddress = AddressController.getInstance().getAddressDaoByAddress(this.val$address);
            if (addressDaoByAddress == null) {
                final NameAddressResultBean nameAddressResultBean = new NameAddressResultBean(null);
                Stream sorted = Collection.-EL.stream(WalletUtils.getAllWallets()).filter(new Predicate() {
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
                        return NftTransactionModel.1.lambda$subscribe$0((Wallet) obj);
                    }
                }).sorted(new Comparator() {
                    @Override
                    public final int compare(Object obj, Object obj2) {
                        return NftTransactionModel.1.lambda$subscribe$1((Wallet) obj, (Wallet) obj2);
                    }
                });
                final String str = this.val$address;
                sorted.forEach(new Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        NftTransactionModel.1.lambda$subscribe$2(str, nameAddressResultBean, (Wallet) obj);
                    }

                    public Consumer andThen(Consumer consumer) {
                        return Objects.requireNonNull(consumer);
                    }
                });
                observer.onNext(nameAddressResultBean);
            } else {
                observer.onNext(new NameAddressResultBean(NameAddressExtraBean.fromAddressBook(addressDaoByAddress)));
            }
            observer.onComplete();
        }

        public static boolean lambda$subscribe$0(Wallet wallet) {
            return (wallet == null || wallet.isShieldedWallet()) ? false : true;
        }

        public static int lambda$subscribe$1(Wallet wallet, Wallet wallet2) {
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

        public static void lambda$subscribe$2(String str, NameAddressResultBean nameAddressResultBean, Wallet wallet) {
            if (StringTronUtil.equals(wallet.getAddress(), str)) {
                nameAddressResultBean.setNameAddressExtraBean(NameAddressExtraBean.fromWallet(wallet));
            }
        }
    }

    @Override
    public Observable<NameAddressResultBean> getNameByAddress(String str) {
        return Observable.unsafeCreate(new fun1(str)).compose(RxSchedulers.io_main());
    }
}
