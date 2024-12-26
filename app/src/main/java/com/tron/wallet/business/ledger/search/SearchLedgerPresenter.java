package com.tron.wallet.business.ledger.search;

import com.polidea.rxandroidble2.scan.ScanResult;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.ledger.manage.EquipmentBean;
import com.tron.wallet.business.ledger.search.SearchLedgerContract;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceController;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.dao.BleDeviceDaoManager;
import com.tron.wallet.business.walletmanager.importwallet.base.repo.entity.BleRepoDevice;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tron.wallet.ledger.bleclient.BleErrorHelper;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tron.wallet.ledger.bleclient.Transport;
import com.tron.wallet.ledger.blemodule.Device;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import j$.util.Collection;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
public class SearchLedgerPresenter extends SearchLedgerContract.Presenter {
    private static final String TAG = "BleSearchLedgerPresenter";
    private BleClientManager bleClientManager;
    private Transport transport;
    private List<EquipmentBean> equipmentBeans = new CopyOnWriteArrayList();
    private List<BleRepoDevice> cachedDevices = new ArrayList();

    @Override
    protected void onStart() {
        this.bleClientManager = BleClientManager.getInstance();
    }

    @Override
    public void connect(final EquipmentBean equipmentBean, int i) {
        if (equipmentBean.isConnecting() || equipmentBean.isConnected()) {
            return;
        }
        BleClientManager.getInstance().disconnectAllTransports();
        Collection.-EL.stream(this.equipmentBeans).filter(new Predicate() {
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
                return SearchLedgerPresenter.lambda$connect$0((EquipmentBean) obj);
            }
        }).filter(new Predicate() {
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
                return SearchLedgerPresenter.lambda$connect$1(EquipmentBean.this, (EquipmentBean) obj);
            }
        }).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                SearchLedgerPresenter.lambda$connect$2((EquipmentBean) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        this.transport = BleClientManager.getInstance().getTransport(equipmentBean.getDevice().getMac());
        equipmentBean.setConnecting(true);
        ((SearchLedgerContract.View) this.mView).updateData(this.equipmentBeans);
        addDisposable(this.transport.open().compose(RxSchedulers2.io_main()).subscribe(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$connect$3(equipmentBean, (Device) obj);
            }
        }, new BleErrorHelper.OnConnectErrorHandler(new BleErrorHelper.OnConnectErrorCallback() {
            @Override
            public void onConnectionDisconnected(BleError bleError) {
            }

            @Override
            public void onDeviceNotFound(BleError bleError) {
            }

            @Override
            public void onTimeout(BleError bleError) {
            }

            @Override
            public void onUnKnowError(BleError bleError) {
            }

            @Override
            public void onPreError(BleError bleError) {
                equipmentBean.setConnecting(false);
                equipmentBean.setConnected(false);
                ((SearchLedgerContract.View) mView).updateData(equipmentBeans);
                ((SearchLedgerActivity) ((SearchLedgerContract.View) mView).getIContext()).onDeviceConnected(equipmentBean);
            }
        })));
    }

    public static boolean lambda$connect$0(EquipmentBean equipmentBean) {
        return equipmentBean.isConnecting() || equipmentBean.isConnected();
    }

    public static boolean lambda$connect$1(EquipmentBean equipmentBean, EquipmentBean equipmentBean2) {
        return !equipmentBean2.getDevice().getMac().equals(equipmentBean.getDevice().getMac());
    }

    public static void lambda$connect$2(EquipmentBean equipmentBean) {
        equipmentBean.setConnecting(false);
        equipmentBean.setConnected(false);
    }

    public void lambda$connect$3(EquipmentBean equipmentBean, Device device) throws Exception {
        BleDeviceDaoManager.getInstance().insert(device);
        equipmentBean.setConnecting(false);
        equipmentBean.setConnected(true);
        ((SearchLedgerContract.View) this.mView).updateData(this.equipmentBeans);
        ((SearchLedgerActivity) ((SearchLedgerContract.View) this.mView).getIContext()).onDeviceConnected(equipmentBean);
    }

    @Override
    public void startScan() {
        addDisposable(Observable.interval(10L, TimeUnit.SECONDS).take(6L).observeOn(AndroidSchedulers.mainThread()).subscribe(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$startScan$4((Long) obj);
            }
        }, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e(SearchLedgerPresenter.TAG, (Throwable) obj);
            }
        }));
        addDisposable(Observable.defer(new Callable() {
            @Override
            public final Object call() {
                ObservableSource onErrorReturn;
                onErrorReturn = BleDeviceController.getInstance().rxQueryAll().doOnError(new io.reactivex.functions.Consumer() {
                    @Override
                    public final void accept(Object obj) {
                        LogUtils.e("BleSearchLedgerPresenter startScan", (Throwable) obj);
                    }
                }).onErrorReturn(new Function() {
                    @Override
                    public final Object apply(Object obj) {
                        return SearchLedgerPresenter.lambda$startScan$7((Throwable) obj);
                    }
                });
                return onErrorReturn;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$startScan$9;
                lambda$startScan$9 = lambda$startScan$9((List) obj);
                return lambda$startScan$9;
            }
        }).compose(RxSchedulers2.io_main()).subscribe(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$startScan$12((ScanResult) obj);
            }
        }, new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$startScan$13((Throwable) obj);
            }
        }));
    }

    public void lambda$startScan$4(Long l) throws Exception {
        if (l.longValue() == 0 && this.equipmentBeans.size() == 0) {
            ((SearchLedgerContract.View) this.mView).showNoDataTips();
        } else if (l.longValue() == 5) {
            this.equipmentBeans.size();
        }
    }

    public static List lambda$startScan$7(Throwable th) throws Exception {
        return new ArrayList();
    }

    public ObservableSource lambda$startScan$9(List list) throws Exception {
        this.cachedDevices.addAll(list);
        return this.bleClientManager.startDeviceScan();
    }

    public void lambda$startScan$12(final ScanResult scanResult) throws Exception {
        if (scanResult != null) {
            boolean anyMatch = Collection.-EL.stream(this.cachedDevices).anyMatch(new Predicate() {
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
                    boolean equals;
                    equals = ScanResult.this.getBleDevice().getMacAddress().equals(((BleRepoDevice) obj).getMac());
                    return equals;
                }
            });
            if (!anyMatch) {
                anyMatch = Collection.-EL.stream(this.equipmentBeans).anyMatch(new Predicate() {
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
                        boolean equals;
                        equals = ScanResult.this.getBleDevice().getMacAddress().equals(((EquipmentBean) obj).getDevice().getMac());
                        return equals;
                    }
                });
            }
            if (anyMatch) {
                return;
            }
            LogUtils.d(TAG, " Find ble device: " + scanResult.getBleDevice().getName());
            BleRepoDevice bleRepoDevice = new BleRepoDevice();
            bleRepoDevice.setMac(scanResult.getBleDevice().getMacAddress());
            bleRepoDevice.setName(scanResult.getBleDevice().getName());
            this.equipmentBeans.add(new EquipmentBean(bleRepoDevice));
            ((SearchLedgerContract.View) this.mView).updateData(this.equipmentBeans);
        }
    }

    public void lambda$startScan$13(Throwable th) throws Exception {
        LogUtils.e(TAG, th);
        ((SearchLedgerContract.View) this.mView).showErrorView();
        BleClientManager.getInstance().stopDeviceScan();
    }

    @Override
    public void stopScan() {
        BleClientManager.getInstance().stopDeviceScan();
    }
}
