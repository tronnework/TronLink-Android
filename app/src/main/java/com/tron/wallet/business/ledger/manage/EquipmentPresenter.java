package com.tron.wallet.business.ledger.manage;

import android.widget.PopupWindow;
import com.tron.wallet.business.ledger.manage.EquipmentContract;
import com.tron.wallet.business.ledger.manage.LedgerProgressView;
import com.tron.wallet.business.walletmanager.importwallet.base.SelectAddressActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.UnableGetAddressActivity;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceDaoManager;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.common.interfaces.CloseClickListener;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tron.wallet.ledger.bleclient.LedgerTrx;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.bleclient.Transport;
import com.tron.wallet.ledger.blemodule.Device;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.List;
public class EquipmentPresenter extends EquipmentContract.Presenter {
    Disposable connectDisposable;
    Disposable importDisposable;
    private final EquipmentModel mModel = new EquipmentModel();

    public static void lambda$getDevices$6(Throwable th) throws Exception {
    }

    @Override
    protected void onStart() {
    }

    @Override
    public void connectEquipment(final BleRepoDevice bleRepoDevice, final int i) {
        final PopupWindow showLoadingPop = ((EquipmentContract.View) this.mView).showLoadingPop(bleRepoDevice, LedgerProgressView.STATUS.LOADING, new CloseClickListener() {
            @Override
            public void onClose() {
                if (connectDisposable.isDisposed()) {
                    return;
                }
                connectDisposable.dispose();
            }
        });
        Disposable subscribe = getTransport(bleRepoDevice.getMac()).open().doOnDispose(new Action() {
            @Override
            public final void run() {
                lambda$connectEquipment$0(bleRepoDevice);
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$connectEquipment$1(showLoadingPop, i, (Device) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$connectEquipment$2(showLoadingPop, i, bleRepoDevice, (Throwable) obj);
            }
        });
        this.connectDisposable = subscribe;
        addDisposable(subscribe);
    }

    public void lambda$connectEquipment$0(BleRepoDevice bleRepoDevice) throws Exception {
        getTransport(bleRepoDevice.getMac()).disconnect();
    }

    public void lambda$connectEquipment$1(PopupWindow popupWindow, int i, Device device) throws Exception {
        popupWindow.dismiss();
        ((EquipmentContract.View) this.mView).updateItemStatus(i, true);
    }

    public void lambda$connectEquipment$2(PopupWindow popupWindow, int i, BleRepoDevice bleRepoDevice, Throwable th) throws Exception {
        popupWindow.dismiss();
        ((EquipmentContract.View) this.mView).connectedFail(i, bleRepoDevice);
    }

    @Override
    public void removeEquipment(BleRepoDevice bleRepoDevice, int i) {
        BleClientManager.getInstance().cancelDeviceConnection(bleRepoDevice.getMac());
        BleDeviceDaoManager.getInstance().remove(bleRepoDevice);
    }

    @Override
    public void importAddress(final BleRepoDevice bleRepoDevice) {
        final PopupWindow showLoadingPop = ((EquipmentContract.View) this.mView).showLoadingPop(bleRepoDevice, LedgerProgressView.STATUS.OPEN, new CloseClickListener() {
            @Override
            public void onClose() {
                if (importDisposable.isDisposed()) {
                    return;
                }
                importDisposable.dispose();
            }
        });
        try {
            Disposable subscribe = new LedgerTrx(getTransport(bleRepoDevice.getMac())).openApp().compose(RxSchedulers2.io_main()).subscribe(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$importAddress$3(showLoadingPop, bleRepoDevice, (Boolean) obj);
                }
            }, new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$importAddress$4(showLoadingPop, bleRepoDevice, (Throwable) obj);
                }
            });
            this.importDisposable = subscribe;
            addDisposable(subscribe);
        } catch (BleError e) {
            showLoadingPop.dismiss();
            e.printStackTrace();
        }
    }

    public void lambda$importAddress$3(PopupWindow popupWindow, BleRepoDevice bleRepoDevice, Boolean bool) throws Exception {
        popupWindow.dismiss();
        SelectAddressActivity.startForLedger(((EquipmentContract.View) this.mView).getIContext(), bleRepoDevice.getMac(), bleRepoDevice.getName(), false);
    }

    public void lambda$importAddress$4(PopupWindow popupWindow, BleRepoDevice bleRepoDevice, Throwable th) throws Exception {
        popupWindow.dismiss();
        UnableGetAddressActivity.start(((EquipmentContract.View) this.mView).getIContext(), bleRepoDevice.getName(), false);
    }

    @Override
    public void disconnectEquipment(BleRepoDevice bleRepoDevice, int i) {
        BleClientManager.getInstance().cancelDeviceConnection(bleRepoDevice.getMac());
        ((EquipmentContract.View) this.mView).updateItemStatus(i, false);
    }

    @Override
    public void getDevices() {
        addDisposable(this.mModel.getDevices().subscribe(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$getDevices$5((List) obj);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                EquipmentPresenter.lambda$getDevices$6((Throwable) obj);
            }
        }));
    }

    public void lambda$getDevices$5(List list) throws Exception {
        ((EquipmentContract.View) this.mView).updateEquipmentList(list);
    }

    private Transport getTransport(String str) {
        return BleClientManager.getInstance().getTransport(str);
    }

    @Override
    public void refresh() {
        getDevices();
    }
}
