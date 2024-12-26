package com.tron.wallet.ledger.bleclient;

import android.bluetooth.BluetoothDevice;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxManager;
import com.tron.wallet.ledger.bleclient.BleClientManager;
import com.tron.wallet.ledger.bleclient.BleEvent;
import com.tron.wallet.ledger.blemodule.BleAdapter;
import com.tron.wallet.ledger.blemodule.BleAdapterFactory;
import com.tron.wallet.ledger.blemodule.Characteristic;
import com.tron.wallet.ledger.blemodule.ConnectionOptions;
import com.tron.wallet.ledger.blemodule.ConnectionState;
import com.tron.wallet.ledger.blemodule.Device;
import com.tron.wallet.ledger.blemodule.OnErrorCallback;
import com.tron.wallet.ledger.blemodule.OnEventCallback;
import com.tron.wallet.ledger.blemodule.OnSuccessCallback;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tron.wallet.ledger.blemodule.errors.BleErrorId;
import com.tron.wallet.ledger.blemodule.utils.Constants;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import java.util.HashMap;
public class BleClientManager {
    private static final int DEFAULT_CALLBACK_TYPE_ALL_MATCHES = 1;
    private static final int DEFAULT_REQUEST_MTU_SIZE = 156;
    private static final int DEFAULT_SCAN_MODE_LOW_POWER = 0;
    private static final String TAG = "BleClientManager";
    private static BleClientManager instance;
    private static long uuid;
    private BleAdapter bleAdapter;
    private int requestMtuSize;
    private HashMap<String, Transport> transports = new HashMap<>();
    private RxManager rxManager = new RxManager();

    private BleClientManager() {
    }

    public static BleClientManager getInstance() {
        if (instance == null) {
            synchronized (BleClientManager.class) {
                if (instance == null) {
                    instance = new BleClientManager();
                }
            }
        }
        instance.createClient();
        return instance;
    }

    public synchronized void createClient() {
        if (this.bleAdapter != null) {
            return;
        }
        LogUtils.d(TAG, "<<<<<<<<<createClient<<<<<<<<<<");
        BleAdapter newAdapter = BleAdapterFactory.getNewAdapter(AppContextUtil.getmApplication());
        this.bleAdapter = newAdapter;
        newAdapter.setLogLevel(Constants.BluetoothLogLevel.NONE);
        this.bleAdapter.createClient(new OnEventCallback() {
            @Override
            public final void onEvent(Object obj) {
                lambda$createClient$0((String) obj);
            }
        });
        this.rxManager.on(BleEvent.CONNECT_STATE_CHANGED, new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$createClient$1(obj);
            }
        });
    }

    public void lambda$createClient$0(String str) {
        this.rxManager.post(BleEvent.BLE_STATE_CHANGED, str);
    }

    public void lambda$createClient$1(Object obj) throws Exception {
        if (obj instanceof BleEvent.ConnectionStateEvent) {
            BleEvent.ConnectionStateEvent connectionStateEvent = (BleEvent.ConnectionStateEvent) obj;
            if (connectionStateEvent.getConnectionState() == ConnectionState.DISCONNECTED || connectionStateEvent.getConnectionState() == ConnectionState.DISCONNECTING) {
                LogUtils.d(TAG, "onDisConnectedEvent:" + connectionStateEvent.getDeviceId() + connectionStateEvent.getConnectionState());
                Transport transport = this.transports.get(connectionStateEvent.getDeviceId());
                if (transport != null) {
                    transport.disconnect();
                }
            }
        }
    }

    public synchronized void destroyClient() {
        LogUtils.d(TAG, "<<<<<<<<<destroyClient<<<<<<<<<<");
        for (String str : this.transports.keySet()) {
            this.transports.get(str).disconnect();
        }
        BleAdapter bleAdapter = this.bleAdapter;
        if (bleAdapter != null) {
            bleAdapter.destroyClient();
            this.bleAdapter = null;
        }
    }

    public synchronized void disconnectAllTransports() {
        LogUtils.d(TAG, "disconnectAllTransports");
        for (String str : this.transports.keySet()) {
            this.transports.get(str).disconnect();
        }
    }

    public synchronized Transport getTransport(String str) {
        Transport transport;
        transport = this.transports.get(str);
        if (transport == null) {
            transport = new BleTransport(str);
            this.transports.put(str, transport);
        }
        return transport;
    }

    public String getState() {
        return this.bleAdapter.getCurrentState();
    }

    class fun1 implements ObservableOnSubscribe<ScanResult> {
        fun1() {
        }

        @Override
        public void subscribe(final ObservableEmitter<ScanResult> observableEmitter) throws Exception {
            bleAdapter.stopDeviceScan();
            bleAdapter.startDeviceScan(LedgerDeviceModel.getBluetoothServiceUuids(), 0, 1, new OnEventCallback() {
                @Override
                public final void onEvent(Object obj) {
                    ObservableEmitter.this.onNext((ScanResult) obj);
                }
            }, new OnErrorCallback() {
                @Override
                public final void onError(BleError bleError) {
                    BleClientManager.1.lambda$subscribe$1(ObservableEmitter.this, bleError);
                }
            });
        }

        public static void lambda$subscribe$1(ObservableEmitter observableEmitter, BleError bleError) {
            if (observableEmitter.isDisposed()) {
                return;
            }
            observableEmitter.onError(bleError);
        }
    }

    public Observable<ScanResult> startDeviceScan() {
        return Observable.create(new fun1());
    }

    public Device getDevice(String str) {
        BluetoothDevice bluetoothDevice = this.bleAdapter.getBluetoothDevice(str);
        if (bluetoothDevice != null) {
            return new Device(bluetoothDevice.getAddress(), bluetoothDevice.getName(), null);
        }
        return null;
    }

    public void stopDeviceScan() {
        this.bleAdapter.stopDeviceScan();
    }

    public Device[] connectedDevices() {
        try {
            return this.bleAdapter.getConnectedDevices(LedgerDeviceModel.getBluetoothServiceUuids());
        } catch (BleError e) {
            e.printStackTrace();
            return new Device[0];
        }
    }

    public class fun2 implements ObservableOnSubscribe<Device> {
        final String val$deviceId;

        fun2(String str) {
            this.val$deviceId = str;
        }

        @Override
        public void subscribe(final ObservableEmitter<Device> observableEmitter) throws Exception {
            BleAdapter bleAdapter = bleAdapter;
            String str = this.val$deviceId;
            ConnectionOptions connectionOptions = new ConnectionOptions(false, requestMtuSize, null, null, 0);
            OnSuccessCallback<Device> onSuccessCallback = new OnSuccessCallback() {
                @Override
                public final void onSuccess(Object obj) {
                    BleClientManager.2.lambda$subscribe$0(ObservableEmitter.this, (Device) obj);
                }
            };
            final String str2 = this.val$deviceId;
            bleAdapter.connectToDevice(str, connectionOptions, onSuccessCallback, new OnEventCallback() {
                @Override
                public final void onEvent(Object obj) {
                    BleClientManager.2.this.lambda$subscribe$1(str2, (ConnectionState) obj);
                }
            }, new OnErrorCallback() {
                @Override
                public final void onError(BleError bleError) {
                    BleClientManager.2.lambda$subscribe$2(ObservableEmitter.this, bleError);
                }
            });
        }

        public static void lambda$subscribe$0(ObservableEmitter observableEmitter, Device device) {
            LogUtils.w(BleClientManager.TAG, String.format("connectToDevice: name: %s, mac: %s", device.getName(), device.getId()));
            observableEmitter.onNext(device);
            observableEmitter.onComplete();
        }

        public void lambda$subscribe$1(String str, ConnectionState connectionState) {
            rxManager.post(BleEvent.CONNECT_STATE_CHANGED, new BleEvent.ConnectionStateEvent(str, connectionState));
        }

        public static void lambda$subscribe$2(ObservableEmitter observableEmitter, BleError bleError) {
            LogUtils.e(BleClientManager.TAG, "connectToDevice:" + bleError);
            if (!observableEmitter.isDisposed()) {
                observableEmitter.onError(bleError);
            } else {
                LogUtils.e(BleClientManager.TAG, bleError);
            }
        }
    }

    public Observable<Device> connectToDevice(String str) {
        this.requestMtuSize = DEFAULT_REQUEST_MTU_SIZE;
        return Observable.create(new fun2(str)).retry(1L, new Predicate() {
            @Override
            public final boolean test(Object obj) {
                boolean lambda$connectToDevice$2;
                lambda$connectToDevice$2 = lambda$connectToDevice$2((Throwable) obj);
                return lambda$connectToDevice$2;
            }
        });
    }

    public boolean lambda$connectToDevice$2(Throwable th) throws Exception {
        if ((th instanceof BleError) && ((BleError) th).errorId == BleErrorId.DeviceMTUChangeFailed) {
            this.requestMtuSize = 0;
            return true;
        }
        return false;
    }

    public void cancelDeviceConnection(String str) {
        BleAdapter bleAdapter = this.bleAdapter;
        if (bleAdapter != null) {
            bleAdapter.cancelDeviceConnection(str);
        }
    }

    public boolean isDeviceConnected(String str) throws BleError {
        BleAdapter bleAdapter = this.bleAdapter;
        if (bleAdapter != null) {
            return bleAdapter.isDeviceConnected(str);
        }
        return true;
    }

    public Observable<Device> discoverAllServicesAndCharacteristicsForDevice(final String str) {
        return Observable.create(new ObservableOnSubscribe<Device>() {
            @Override
            public void subscribe(final ObservableEmitter<Device> observableEmitter) throws Exception {
                bleAdapter.discoverAllServicesAndCharacteristicsForDevice(str, "discoverAllServicesAndCharacteristicsForDevice", new OnSuccessCallback<Device>() {
                    @Override
                    public void onSuccess(Device device) {
                        observableEmitter.onNext(device);
                        observableEmitter.onComplete();
                    }
                }, new OnErrorCallback() {
                    @Override
                    public void onError(BleError bleError) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onError(bleError);
                    }
                });
            }
        });
    }

    public Observable<Characteristic> writeCharacteristic(final int i, final String str) {
        return Observable.create(new ObservableOnSubscribe<Characteristic>() {
            @Override
            public void subscribe(final ObservableEmitter<Characteristic> observableEmitter) throws Exception {
                BleAdapter bleAdapter = bleAdapter;
                int i2 = i;
                String str2 = str;
                StringBuilder sb = new StringBuilder("writeCharacteristic");
                long j = BleClientManager.uuid;
                BleClientManager.uuid = 1 + j;
                sb.append(j);
                bleAdapter.writeCharacteristic(i2, str2, true, sb.toString(), new OnSuccessCallback<Characteristic>() {
                    @Override
                    public void onSuccess(Characteristic characteristic) {
                        observableEmitter.onNext(characteristic);
                        observableEmitter.onComplete();
                    }
                }, new OnErrorCallback() {
                    @Override
                    public void onError(BleError bleError) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onError(bleError);
                    }
                });
            }
        });
    }

    public Observable<Characteristic> monitorCharacteristic(final int i) {
        return Observable.create(new ObservableOnSubscribe<Characteristic>() {
            @Override
            public void subscribe(final ObservableEmitter<Characteristic> observableEmitter) throws Exception {
                bleAdapter.monitorCharacteristic(i, "monitorCharacteristic", new OnEventCallback<Characteristic>() {
                    @Override
                    public void onEvent(Characteristic characteristic) {
                        observableEmitter.onNext(characteristic);
                    }
                }, new OnErrorCallback() {
                    @Override
                    public void onError(BleError bleError) {
                        LogUtils.e(BleClientManager.TAG, "monitorCharacteristic:" + bleError);
                        if (!observableEmitter.isDisposed()) {
                            observableEmitter.onError(bleError);
                        } else {
                            LogUtils.e(BleClientManager.TAG, bleError);
                        }
                    }
                });
            }
        }).share();
    }
}
