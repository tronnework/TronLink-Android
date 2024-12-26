package com.tron.wallet.business.mutil;

import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.net.RxSchedulers;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.mutil.MultiSelectAddressContract;
import com.tron.wallet.business.mutil.bean.MultiAddressOutput;
import com.tron.wallet.common.bean.Result2;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.db.Controller.AddressController;
import com.tron.wallet.db.bean.AddressDao;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.net.HttpAPI;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import j$.util.Collection;
import j$.util.Objects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.tron.protos.Protocol;
import org.tron.walletserver.Wallet;
public class MultiSelectAddressModel implements MultiSelectAddressContract.Model {
    @Override
    public Observable<Result2<List<MultiAddressOutput>>> getMultiAddress(String str) {
        return ((HttpAPI) IRequest.getAPI(HttpAPI.class)).getMultiAddress(str).compose(RxSchedulers.io_main());
    }

    @Override
    public Observable<Map<String, String>> getAddressName() {
        final HashMap hashMap = new HashMap();
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                MultiSelectAddressModel.lambda$getAddressName$2(hashMap, observableEmitter);
            }
        }).compose(RxSchedulers.io_main());
    }

    public static void lambda$getAddressName$2(final Map map, ObservableEmitter observableEmitter) throws Exception {
        Collection.-EL.stream(WalletUtils.getAllWallets()).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                map.put(r2.getAddress(), ((Wallet) obj).getWalletName());
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        List<AddressDao> searchAll = AddressController.getInstance().searchAll();
        if (searchAll != null && !searchAll.isEmpty()) {
            Collection.-EL.stream(searchAll).forEach(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    map.put(r2.getAddress(), ((AddressDao) obj).getName());
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        observableEmitter.onNext(map);
    }

    @Override
    public boolean checkCurrentAccountPermission(String str, String str2) {
        try {
            Protocol.Account account = WalletUtils.getAccount(AppContextUtil.getContext(), str2);
            if (account != null) {
                if (account.getOwnerPermission() == null || account.getOwnerPermission().getKeysList() == null || account.getOwnerPermission().getKeysList().isEmpty()) {
                    return true;
                }
                for (Protocol.Key key : account.getOwnerPermission().getKeysList()) {
                    if (str.equals(StringTronUtil.encode58Check(key.getAddress().toByteArray()))) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            LogUtils.e(e);
            return false;
        }
    }
}
