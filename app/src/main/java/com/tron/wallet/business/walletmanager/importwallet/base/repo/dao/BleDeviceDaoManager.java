package com.tron.wallet.business.walletmanager.importwallet.base.repo.dao;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.ledger.blemodule.Device;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.List;
public class BleDeviceDaoManager {
    private BleDeviceDaoManager() {
    }

    public static BleDeviceDaoManager getInstance() {
        return Nested.instance;
    }

    public static class Nested {
        static BleDeviceDaoManager instance = new BleDeviceDaoManager();

        private Nested() {
        }
    }

    public void insert(final Device device) {
        final BleRepoDevice fromDevice = BleRepoDevice.fromDevice(device);
        BleDeviceController.getInstance().rxInsert(fromDevice).subscribe(new Observer<Boolean>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(Boolean bool) {
                LogUtils.w("BleDeviceDaoManager", String.format("insert: device name: %s, mac: %s", device.getName(), device.getId()));
                LogUtils.w("BleDeviceDaoManager", String.format("insert: repo device name: %s, mac: %s", fromDevice.getName(), fromDevice.getId()));
                LogUtils.w("BleDeviceDaoManager", String.format("onNext: insert device success? %b", bool));
            }

            @Override
            public void onError(Throwable th) {
                LogUtils.e(th);
            }
        });
    }

    public void remove(BleRepoDevice bleRepoDevice) {
        BleDeviceController.getInstance().rxRemove(bleRepoDevice).subscribe(new Observer<Boolean>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onSubscribe(Disposable disposable) {
            }

            @Override
            public void onNext(Boolean bool) {
                LogUtils.w("BleDeviceDaoManager", String.format("onNext: remove device success? %b", bool));
            }

            @Override
            public void onError(Throwable th) {
                LogUtils.e(th);
            }
        });
    }

    public Observable<List<BleRepoDevice>> queryAll() {
        return BleDeviceController.getInstance().rxQueryAll();
    }
}
