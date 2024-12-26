package com.tron.wallet.common.components;

import android.content.Context;
import android.view.View;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BottomPopupView;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.nft.nfttransactiondetail.NameAddressResultBean;
import com.tron.wallet.business.walletmanager.selectwallet.bean.WalletGroupType;
import com.tron.wallet.common.bean.NameAddressExtraBean;
import com.tron.wallet.common.components.AddressBottomView;
import com.tron.wallet.common.utils.Action;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.databinding.AddressBottomViewBinding;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import j$.util.stream.Stream;
import java.util.Comparator;
import java.util.function.Predicate;
import org.tron.walletserver.Wallet;
public class AddressBottomView extends BottomPopupView implements View.OnClickListener {
    View addAddressView;
    AddressBottomViewBinding binding;
    private boolean isInAddress;
    View lineView;
    private Action<View> onClickAddUrl;
    private Action<View> onClickTronScan;

    @Override
    public int getImplLayoutId() {
        return R.layout.address_bottom_view;
    }

    public void setOnClickCallback(Action<View> action, Action<View> action2) {
        this.onClickAddUrl = action;
        this.onClickTronScan = action2;
    }

    public AddressBottomView(Context context) {
        super(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.binding = AddressBottomViewBinding.bind(getPopupImplView());
        mappingId();
        initClickListener();
        if (this.isInAddress) {
            this.addAddressView.setVisibility(View.GONE);
            this.lineView.setVisibility(View.GONE);
        }
    }

    private void initClickListener() {
        this.binding.addToAddressBook.setOnClickListener(this);
        this.binding.viewOnTronscan.setOnClickListener(this);
        this.binding.tvCancel.setOnClickListener(this);
    }

    private void mappingId() {
        this.addAddressView = this.binding.addToAddressBook;
        this.lineView = this.binding.line;
    }

    public static void showPop(final Context context, String str, final Action<View> action, final Action<View> action2) {
        getNameByAddress(str).compose(RxSchedulers.io_main()).subscribe(new Consumer<NameAddressResultBean>() {
            @Override
            public void accept(NameAddressResultBean nameAddressResultBean) throws Exception {
                LogUtils.e("NameAddressResultBean", "NameAddressResultBean = " + nameAddressResultBean.toString());
                AddressBottomView addressBottomView = (AddressBottomView) new XPopup.Builder(context).asCustom(new AddressBottomView(context));
                addressBottomView.isInAddress = nameAddressResultBean.getNameAddressExtraBean() != null;
                addressBottomView.setOnClickCallback(action, action2);
                addressBottomView.show();
            }
        });
    }

    public class fun2 implements ObservableSource<NameAddressResultBean> {
        final String val$address;

        fun2(String str) {
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
                        return AddressBottomView.2.lambda$subscribe$0((Wallet) obj);
                    }
                }).sorted(new Comparator() {
                    @Override
                    public final int compare(Object obj, Object obj2) {
                        return AddressBottomView.2.lambda$subscribe$1((Wallet) obj, (Wallet) obj2);
                    }
                });
                final String str = this.val$address;
                sorted.forEach(new java.util.function.Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        AddressBottomView.2.lambda$subscribe$2(str, nameAddressResultBean, (Wallet) obj);
                    }

                    public java.util.function.Consumer andThen(java.util.function.Consumer consumer) {
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

    public static Observable<NameAddressResultBean> getNameByAddress(String str) {
        return Observable.unsafeCreate(new fun2(str)).compose(RxSchedulers.io_main());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.add_to_address_book) {
            Action<View> action = this.onClickAddUrl;
            if (action != null) {
                action.apply(view, 0);
            }
            dismiss();
        } else if (id == R.id.tv_cancel) {
            dismiss();
        } else if (id != R.id.view_on_tronscan) {
        } else {
            Action<View> action2 = this.onClickTronScan;
            if (action2 != null) {
                action2.apply(view, 1);
            }
            dismiss();
        }
    }
}
