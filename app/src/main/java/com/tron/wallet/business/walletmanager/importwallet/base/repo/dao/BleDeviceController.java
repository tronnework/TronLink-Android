package com.tron.wallet.business.walletmanager.importwallet.base.repo.dao;

import com.tron.wallet.business.addassets2.repository.BaseController;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceController;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import java.util.List;
public class BleDeviceController extends BaseController<BleRepoDevice> {

    public interface Function0<R> {
        R apply();
    }

    private BleDeviceController() {
        super(true);
    }

    private static class Nested {
        static BleDeviceController instance = new BleDeviceController();

        private Nested() {
        }
    }

    public static BleDeviceController getInstance() {
        return Nested.instance;
    }

    public Boolean lambda$rxInsert$0(Object obj) throws Exception {
        return Boolean.valueOf(super.insertOrReplace(obj));
    }

    public Observable<Boolean> rxInsert(BleRepoDevice bleRepoDevice) {
        return toRx(new Function() {
            @Override
            public final Object apply(Object obj) {
                Boolean lambda$rxInsert$0;
                lambda$rxInsert$0 = lambda$rxInsert$0((BleRepoDevice) obj);
                return lambda$rxInsert$0;
            }
        }, bleRepoDevice);
    }

    public Observable<Boolean> rxRemove(BleRepoDevice bleRepoDevice) {
        return toRx(new Function() {
            @Override
            public final Object apply(Object obj) {
                Boolean lambda$rxRemove$1;
                lambda$rxRemove$1 = lambda$rxRemove$1((BleRepoDevice) obj);
                return lambda$rxRemove$1;
            }
        }, bleRepoDevice);
    }

    public Boolean lambda$rxRemove$1(BleRepoDevice bleRepoDevice) throws Exception {
        super.deleteEntity(bleRepoDevice);
        return true;
    }

    public Boolean lambda$empty$2() {
        return Boolean.valueOf(this.beanDao.count() == 0);
    }

    public Observable<Boolean> empty() {
        return toRx(new Function0() {
            @Override
            public final Object apply() {
                Boolean lambda$empty$2;
                lambda$empty$2 = lambda$empty$2();
                return lambda$empty$2;
            }
        });
    }

    public List lambda$rxQueryAll$3() {
        return super.queryAll();
    }

    public Observable<List<BleRepoDevice>> rxQueryAll() {
        return toRx(new Function0() {
            @Override
            public final Object apply() {
                List lambda$rxQueryAll$3;
                lambda$rxQueryAll$3 = lambda$rxQueryAll$3();
                return lambda$rxQueryAll$3;
            }
        });
    }

    private <R> Observable<R> toRx(final Function0<R> function0) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                BleDeviceController.lambda$toRx$4(BleDeviceController.Function0.this, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public static void lambda$toRx$4(Function0 function0, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(function0.apply());
        observableEmitter.onComplete();
    }

    private <R> Observable<R> toRx(final Function<BleRepoDevice, R> function, final BleRepoDevice bleRepoDevice) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                BleDeviceController.lambda$toRx$5(Function.this, bleRepoDevice, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main());
    }

    public static void lambda$toRx$5(Function function, BleRepoDevice bleRepoDevice, ObservableEmitter observableEmitter) throws Exception {
        observableEmitter.onNext(function.apply(bleRepoDevice));
        observableEmitter.onComplete();
    }
}
