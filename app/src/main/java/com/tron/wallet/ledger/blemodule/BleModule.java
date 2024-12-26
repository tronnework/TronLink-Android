package com.tron.wallet.ledger.blemodule;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.polidea.rxandroidble2.LogOptions;
import com.polidea.rxandroidble2.NotificationSetupMode;
import com.polidea.rxandroidble2.RxBleAdapterStateObservable;
import com.polidea.rxandroidble2.RxBleClient;
import com.polidea.rxandroidble2.RxBleConnection;
import com.polidea.rxandroidble2.RxBleDevice;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.scan.ScanFilter;
import com.polidea.rxandroidble2.scan.ScanResult;
import com.polidea.rxandroidble2.scan.ScanSettings;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tron.wallet.ledger.blemodule.errors.BleErrorId;
import com.tron.wallet.ledger.blemodule.errors.BleErrorUtils;
import com.tron.wallet.ledger.blemodule.errors.ErrorConverter;
import com.tron.wallet.ledger.blemodule.exceptions.CannotMonitorCharacteristicException;
import com.tron.wallet.ledger.blemodule.utils.Base64Converter;
import com.tron.wallet.ledger.blemodule.utils.Constants;
import com.tron.wallet.ledger.blemodule.utils.DisposableMap;
import com.tron.wallet.ledger.blemodule.utils.IdGenerator;
import com.tron.wallet.ledger.blemodule.utils.LogLevel;
import com.tron.wallet.ledger.blemodule.utils.RefreshGattCustomOperation;
import com.tron.wallet.ledger.blemodule.utils.SafeExecutor;
import com.tron.wallet.ledger.blemodule.utils.ServiceFactory;
import com.tron.wallet.ledger.blemodule.utils.UUIDConverter;
import com.tron.wallet.ledger.blemodule.utils.mapper.RxBleDeviceToDeviceMapper;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.observers.CallbackCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
public class BleModule implements BleAdapter {
    private static final String TAG = "BleModule";
    private Disposable adapterStateChangesSubscription;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private Context context;
    private RxBleClient rxBleClient;
    private Disposable scanSubscription;
    private final ErrorConverter errorConverter = new ErrorConverter();
    private final DisposableMap pendingTransactions = new DisposableMap();
    private final DisposableMap connectingDevices = new DisposableMap();
    private HashMap<String, Device> discoveredDevices = new HashMap<>();
    private HashMap<String, Device> connectedDevices = new HashMap<>();
    private HashMap<String, RxBleConnection> activeConnections = new HashMap<>();
    private SparseArray<Service> discoveredServices = new SparseArray<>();
    private SparseArray<Characteristic> discoveredCharacteristics = new SparseArray<>();
    private SparseArray<Descriptor> discoveredDescriptors = new SparseArray<>();
    private RxBleDeviceToDeviceMapper rxBleDeviceToDeviceMapper = new RxBleDeviceToDeviceMapper();
    private ServiceFactory serviceFactory = new ServiceFactory();
    private int currentLogLevel = 3;

    private String mapNativeAdapterStateToLocalBluetoothState(int i) {
        switch (i) {
            case 10:
                return Constants.BluetoothState.POWERED_OFF;
            case 11:
            case 13:
                return Constants.BluetoothState.RESETTING;
            case 12:
                return Constants.BluetoothState.POWERED_ON;
            default:
                return Constants.BluetoothState.UNKNOWN;
        }
    }

    public BleModule(Context context) {
        this.context = context;
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.bluetoothManager = bluetoothManager;
        this.bluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    public void createClient(OnEventCallback<String> onEventCallback) {
        this.rxBleClient = RxBleClient.create(this.context);
        this.adapterStateChangesSubscription = monitorAdapterStateChanges(this.context, onEventCallback);
    }

    @Override
    public void destroyClient() {
        Disposable disposable = this.adapterStateChangesSubscription;
        if (disposable != null) {
            disposable.dispose();
            this.adapterStateChangesSubscription = null;
        }
        Disposable disposable2 = this.scanSubscription;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.scanSubscription.dispose();
            this.scanSubscription = null;
        }
        this.pendingTransactions.removeAllDisposables();
        this.connectingDevices.removeAllDisposables();
        this.discoveredServices.clear();
        this.discoveredCharacteristics.clear();
        this.discoveredDescriptors.clear();
        this.connectedDevices.clear();
        this.activeConnections.clear();
        this.discoveredDevices.clear();
        this.rxBleClient = null;
        IdGenerator.clear();
    }

    @Override
    public void enable(String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback) {
        changeAdapterState(RxBleAdapterStateObservable.BleAdapterState.STATE_ON, str, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void disable(String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback) {
        changeAdapterState(RxBleAdapterStateObservable.BleAdapterState.STATE_OFF, str, onSuccessCallback, onErrorCallback);
    }

    @Override
    public String getCurrentState() {
        return bluetoothLowEnergyNotSupported() ? Constants.BluetoothState.UNSUPPORTED : this.bluetoothManager == null ? Constants.BluetoothState.POWERED_OFF : mapNativeAdapterStateToLocalBluetoothState(this.bluetoothAdapter.getState());
    }

    @Override
    public void startDeviceScan(String[] strArr, int i, int i2, OnEventCallback<ScanResult> onEventCallback, OnErrorCallback onErrorCallback) {
        UUID[] uuidArr;
        if (strArr != null) {
            uuidArr = UUIDConverter.convert(strArr);
            if (uuidArr == null) {
                onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(strArr));
                return;
            }
        } else {
            uuidArr = null;
        }
        safeStartDeviceScan(uuidArr, i, i2, onEventCallback, onErrorCallback);
    }

    @Override
    public void stopDeviceScan() {
        Disposable disposable = this.scanSubscription;
        if (disposable != null) {
            disposable.dispose();
            this.scanSubscription = null;
        }
    }

    @Override
    public void requestConnectionPriorityForDevice(String str, int i, final String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            final Device deviceById = getDeviceById(str);
            RxBleConnection connectionOrEmitError = getConnectionOrEmitError(deviceById.getId(), onErrorCallback);
            if (connectionOrEmitError == null) {
                return;
            }
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            this.pendingTransactions.replaceDisposable(str2, connectionOrEmitError.requestConnectionPriority(i, 1L, TimeUnit.MILLISECONDS).doOnDispose(new Action() {
                @Override
                public void run() {
                    safeExecutor.error(BleErrorUtils.cancelled());
                    pendingTransactions.removeDisposable(str2);
                }
            }).subscribe(new Action() {
                @Override
                public void run() {
                    safeExecutor.success(deviceById);
                    pendingTransactions.removeDisposable(str2);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable th) {
                    safeExecutor.error(errorConverter.toError(th));
                    pendingTransactions.removeDisposable(str2);
                }
            }));
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void readRSSIForDevice(String str, final String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            final Device deviceById = getDeviceById(str);
            RxBleConnection connectionOrEmitError = getConnectionOrEmitError(deviceById.getId(), onErrorCallback);
            if (connectionOrEmitError == null) {
                return;
            }
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            Single<Integer> doOnDispose = connectionOrEmitError.readRssi().doOnDispose(new Action() {
                @Override
                public void run() {
                    safeExecutor.error(BleErrorUtils.cancelled());
                    pendingTransactions.removeDisposable(str2);
                }
            });
            DisposableSingleObserver<Integer> disposableSingleObserver = new DisposableSingleObserver<Integer>() {
                @Override
                public void onSuccess(Integer num) {
                    deviceById.setRssi(num);
                    safeExecutor.success(deviceById);
                    pendingTransactions.removeDisposable(str2);
                }

                @Override
                public void onError(Throwable th) {
                    safeExecutor.error(errorConverter.toError(th));
                    pendingTransactions.removeDisposable(str2);
                }
            };
            doOnDispose.subscribe(disposableSingleObserver);
            this.pendingTransactions.replaceDisposable(str2, disposableSingleObserver);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void requestMTUForDevice(String str, int i, final String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            final Device deviceById = getDeviceById(str);
            RxBleConnection connectionOrEmitError = getConnectionOrEmitError(deviceById.getId(), onErrorCallback);
            if (connectionOrEmitError == null) {
                return;
            }
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            Single<Integer> doOnDispose = connectionOrEmitError.requestMtu(i).doOnDispose(new Action() {
                @Override
                public void run() {
                    safeExecutor.error(BleErrorUtils.cancelled());
                    pendingTransactions.removeDisposable(str2);
                }
            });
            DisposableSingleObserver<Integer> disposableSingleObserver = new DisposableSingleObserver<Integer>() {
                @Override
                public void onSuccess(Integer num) {
                    deviceById.setMtu(num);
                    safeExecutor.success(deviceById);
                    pendingTransactions.removeDisposable(str2);
                }

                @Override
                public void onError(Throwable th) {
                    safeExecutor.error(errorConverter.toError(th));
                    pendingTransactions.removeDisposable(str2);
                }
            };
            doOnDispose.subscribe(disposableSingleObserver);
            this.pendingTransactions.replaceDisposable(str2, disposableSingleObserver);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public BluetoothDevice getBluetoothDevice(String str) {
        RxBleClient rxBleClient = this.rxBleClient;
        if (rxBleClient == null) {
            return null;
        }
        return rxBleClient.getBleDevice(str).getBluetoothDevice();
    }

    @Override
    public void getKnownDevices(String[] strArr, OnSuccessCallback<Device[]> onSuccessCallback, OnErrorCallback onErrorCallback) {
        if (this.rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorId.BluetoothManagerDestroyed, "BleManager not created when tried to get known devices", null));
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (str == null) {
                onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(strArr));
                return;
            }
            Device device = this.discoveredDevices.get(str);
            if (device != null) {
                arrayList.add(device);
            }
        }
        onSuccessCallback.onSuccess((Device[]) arrayList.toArray(new Device[0]));
    }

    @Override
    public Device[] getConnectedDevices(String[] strArr) throws BleError {
        if (this.rxBleClient == null) {
            throw new BleError(BleErrorId.BluetoothManagerDestroyed, "BleManager not created when tried to get connected devices", null);
        }
        if (strArr.length == 0) {
            return new Device[0];
        }
        int length = strArr.length;
        UUID[] uuidArr = new UUID[length];
        for (int i = 0; i < strArr.length; i++) {
            UUID convert = UUIDConverter.convert(strArr[i]);
            if (convert != null) {
                uuidArr[i] = convert;
            }
        }
        ArrayList arrayList = new ArrayList();
        for (Device device : this.connectedDevices.values()) {
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (device.getServiceByUUID(uuidArr[i2]) != null) {
                    arrayList.add(device);
                    break;
                } else {
                    i2++;
                }
            }
        }
        return (Device[]) arrayList.toArray(new Device[0]);
    }

    @Override
    public void connectToDevice(String str, ConnectionOptions connectionOptions, OnSuccessCallback<Device> onSuccessCallback, OnEventCallback<ConnectionState> onEventCallback, OnErrorCallback onErrorCallback) {
        RxBleClient rxBleClient = this.rxBleClient;
        if (rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorId.BluetoothManagerDestroyed, "BleManager not created when tried to connect to device", null));
            return;
        }
        RxBleDevice bleDevice = rxBleClient.getBleDevice(str);
        if (bleDevice == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotFound(str));
        } else {
            safeConnectToDevice(bleDevice, connectionOptions.getAutoConnect().booleanValue(), connectionOptions.getRequestMTU(), connectionOptions.getRefreshGattMoment(), connectionOptions.getTimeoutInMillis(), connectionOptions.getConnectionPriority(), onSuccessCallback, onEventCallback, onErrorCallback);
        }
    }

    @Override
    public void cancelDeviceConnection(String str) {
        if (this.rxBleClient == null) {
            return;
        }
        this.connectingDevices.removeDisposable(str);
    }

    @Override
    public boolean isDeviceConnected(String str) throws BleError {
        RxBleClient rxBleClient = this.rxBleClient;
        if (rxBleClient == null) {
            throw new BleError(BleErrorId.BluetoothManagerDestroyed, "BleManager not created when tried to check if device is connected", null);
        }
        RxBleDevice bleDevice = rxBleClient.getBleDevice(str);
        if (bleDevice == null) {
            return false;
        }
        return bleDevice.getConnectionState().equals(RxBleConnection.RxBleConnectionState.CONNECTED);
    }

    @Override
    public void discoverAllServicesAndCharacteristicsForDevice(String str, String str2, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeDiscoverAllServicesAndCharacteristicsForDevice(getDeviceById(str), str2, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public List<Service> getServicesForDevice(String str) throws BleError {
        Device deviceById = getDeviceById(str);
        List<Service> services = deviceById.getServices();
        if (services != null) {
            return services;
        }
        throw BleErrorUtils.deviceServicesNotDiscovered(deviceById.getId());
    }

    @Override
    public List<Characteristic> getCharacteristicsForDevice(String str, String str2) throws BleError {
        UUID convert = UUIDConverter.convert(str2);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str2);
        }
        Service serviceByUUID = getDeviceById(str).getServiceByUUID(convert);
        if (serviceByUUID == null) {
            throw BleErrorUtils.serviceNotFound(str2);
        }
        return serviceByUUID.getCharacteristics();
    }

    @Override
    public List<Characteristic> getCharacteristicsForService(int i) throws BleError {
        Service service = this.discoveredServices.get(i);
        if (service == null) {
            throw BleErrorUtils.serviceNotFound(Integer.toString(i));
        }
        return service.getCharacteristics();
    }

    @Override
    public List<Descriptor> descriptorsForDevice(String str, String str2, String str3) throws BleError {
        UUID[] convert = UUIDConverter.convert(str2, str3);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str2, str3);
        }
        Service serviceByUUID = getDeviceById(str).getServiceByUUID(convert[0]);
        if (serviceByUUID == null) {
            throw BleErrorUtils.serviceNotFound(str2);
        }
        Characteristic characteristicByUUID = serviceByUUID.getCharacteristicByUUID(convert[1]);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str3);
        }
        return characteristicByUUID.getDescriptors();
    }

    @Override
    public List<Descriptor> descriptorsForService(int i, String str) throws BleError {
        UUID convert = UUIDConverter.convert(str);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str);
        }
        Service service = this.discoveredServices.get(i);
        if (service == null) {
            throw BleErrorUtils.serviceNotFound(Integer.toString(i));
        }
        Characteristic characteristicByUUID = service.getCharacteristicByUUID(convert);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str);
        }
        return characteristicByUUID.getDescriptors();
    }

    @Override
    public List<Descriptor> descriptorsForCharacteristic(int i) throws BleError {
        Characteristic characteristic = this.discoveredCharacteristics.get(i);
        if (characteristic == null) {
            throw BleErrorUtils.characteristicNotFound(Integer.toString(i));
        }
        return characteristic.getDescriptors();
    }

    @Override
    public void readCharacteristicForDevice(String str, String str2, String str3, String str4, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(str, str2, str3, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeReadCharacteristicForDevice(characteristicOrEmitError, str4, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void readCharacteristicForService(int i, String str, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(i, str, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeReadCharacteristicForDevice(characteristicOrEmitError, str2, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void readCharacteristic(int i, String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(i, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeReadCharacteristicForDevice(characteristicOrEmitError, str, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void writeCharacteristicForDevice(String str, String str2, String str3, String str4, boolean z, String str5, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(str, str2, str3, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        writeCharacteristicWithValue(characteristicOrEmitError, str4, Boolean.valueOf(z), str5, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void writeCharacteristicForService(int i, String str, String str2, boolean z, String str3, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(i, str, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        writeCharacteristicWithValue(characteristicOrEmitError, str2, Boolean.valueOf(z), str3, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void writeCharacteristic(int i, String str, boolean z, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(i, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        writeCharacteristicWithValue(characteristicOrEmitError, str, Boolean.valueOf(z), str2, onSuccessCallback, onErrorCallback);
    }

    @Override
    public void monitorCharacteristicForDevice(String str, String str2, String str3, String str4, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(str, str2, str3, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeMonitorCharacteristicForDevice(characteristicOrEmitError, str4, onEventCallback, onErrorCallback);
    }

    @Override
    public void monitorCharacteristicForService(int i, String str, String str2, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(i, str, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeMonitorCharacteristicForDevice(characteristicOrEmitError, str2, onEventCallback, onErrorCallback);
    }

    @Override
    public void monitorCharacteristic(int i, String str, OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        Characteristic characteristicOrEmitError = getCharacteristicOrEmitError(i, onErrorCallback);
        if (characteristicOrEmitError == null) {
            return;
        }
        safeMonitorCharacteristicForDevice(characteristicOrEmitError, str, onEventCallback, onErrorCallback);
    }

    @Override
    public void readDescriptorForDevice(String str, String str2, String str3, String str4, String str5, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(str, str2, str3, str4), str5, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void readDescriptorForService(int i, String str, String str2, String str3, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(i, str, str2), str3, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void readDescriptorForCharacteristic(int i, String str, String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(i, str), str2, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void readDescriptor(int i, String str, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeReadDescriptorForDevice(getDescriptor(i), str, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    private void safeReadDescriptorForDevice(final Descriptor descriptor, final String str, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(descriptor.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Single<byte[]> doOnDispose = connectionOrEmitError.readDescriptor(descriptor.getNativeDescriptor()).doOnDispose(new Action() {
            @Override
            public void run() {
                safeExecutor.error(BleErrorUtils.cancelled());
                pendingTransactions.removeDisposable(str);
            }
        });
        DisposableSingleObserver<byte[]> disposableSingleObserver = new DisposableSingleObserver<byte[]>() {
            @Override
            public void onSuccess(byte[] bArr) {
                descriptor.logValue("Read from", bArr);
                descriptor.setValue(bArr);
                safeExecutor.success(new Descriptor(descriptor));
                pendingTransactions.removeDisposable(str);
            }

            @Override
            public void onError(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                pendingTransactions.removeDisposable(str);
            }
        };
        doOnDispose.subscribe(disposableSingleObserver);
        this.pendingTransactions.replaceDisposable(str, disposableSingleObserver);
    }

    @Override
    public void writeDescriptorForDevice(String str, String str2, String str3, String str4, String str5, String str6, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(str, str2, str3, str4), str5, str6, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void writeDescriptorForService(int i, String str, String str2, String str3, String str4, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(i, str, str2), str3, str4, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void writeDescriptorForCharacteristic(int i, String str, String str2, String str3, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(i, str), str2, str3, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    @Override
    public void writeDescriptor(int i, String str, String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            safeWriteDescriptorForDevice(getDescriptor(i), str, str2, onSuccessCallback, onErrorCallback);
        } catch (BleError e) {
            onErrorCallback.onError(e);
        }
    }

    private void safeWriteDescriptorForDevice(final Descriptor descriptor, String str, final String str2, OnSuccessCallback<Descriptor> onSuccessCallback, OnErrorCallback onErrorCallback) {
        BluetoothGattDescriptor nativeDescriptor = descriptor.getNativeDescriptor();
        if (nativeDescriptor.getUuid().equals(Constants.CLIENT_CHARACTERISTIC_CONFIG_UUID)) {
            onErrorCallback.onError(BleErrorUtils.descriptorWriteNotAllowed(UUIDConverter.fromUUID(nativeDescriptor.getUuid())));
            return;
        }
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(descriptor.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        try {
            final byte[] decode = Base64Converter.decode(str);
            final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
            Completable doOnDispose = connectionOrEmitError.writeDescriptor(nativeDescriptor, decode).doOnDispose(new Action() {
                @Override
                public void run() {
                    safeExecutor.error(BleErrorUtils.cancelled());
                    pendingTransactions.removeDisposable(str2);
                }
            });
            CallbackCompletableObserver callbackCompletableObserver = new CallbackCompletableObserver(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable th) {
                    safeExecutor.error(errorConverter.toError(th));
                    pendingTransactions.removeDisposable(str2);
                }
            }, new Action() {
                @Override
                public void run() {
                    descriptor.logValue("Write to", decode);
                    descriptor.setValue(decode);
                    safeExecutor.success(new Descriptor(descriptor));
                    pendingTransactions.removeDisposable(str2);
                }
            });
            doOnDispose.subscribe(callbackCompletableObserver);
            this.pendingTransactions.replaceDisposable(str2, callbackCompletableObserver);
        } catch (Throwable unused) {
            onErrorCallback.onError(BleErrorUtils.invalidWriteDataForDescriptor(str, UUIDConverter.fromUUID(nativeDescriptor.getUuid())));
        }
    }

    private Descriptor getDescriptor(String str, String str2, String str3, String str4) throws BleError {
        UUID[] convert = UUIDConverter.convert(str2, str3, str4);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str2, str3, str4);
        }
        Device device = this.connectedDevices.get(str);
        if (device == null) {
            throw BleErrorUtils.deviceNotConnected(str);
        }
        Service serviceByUUID = device.getServiceByUUID(convert[0]);
        if (serviceByUUID == null) {
            throw BleErrorUtils.serviceNotFound(str2);
        }
        Characteristic characteristicByUUID = serviceByUUID.getCharacteristicByUUID(convert[1]);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str3);
        }
        Descriptor descriptorByUUID = characteristicByUUID.getDescriptorByUUID(convert[2]);
        if (descriptorByUUID != null) {
            return descriptorByUUID;
        }
        throw BleErrorUtils.descriptorNotFound(str4);
    }

    private Descriptor getDescriptor(int i, String str, String str2) throws BleError {
        UUID[] convert = UUIDConverter.convert(str, str2);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str, str2);
        }
        Service service = this.discoveredServices.get(i);
        if (service == null) {
            throw BleErrorUtils.serviceNotFound(Integer.toString(i));
        }
        Characteristic characteristicByUUID = service.getCharacteristicByUUID(convert[0]);
        if (characteristicByUUID == null) {
            throw BleErrorUtils.characteristicNotFound(str);
        }
        Descriptor descriptorByUUID = characteristicByUUID.getDescriptorByUUID(convert[1]);
        if (descriptorByUUID != null) {
            return descriptorByUUID;
        }
        throw BleErrorUtils.descriptorNotFound(str2);
    }

    private Descriptor getDescriptor(int i, String str) throws BleError {
        UUID convert = UUIDConverter.convert(str);
        if (convert == null) {
            throw BleErrorUtils.invalidIdentifiers(str);
        }
        Characteristic characteristic = this.discoveredCharacteristics.get(i);
        if (characteristic == null) {
            throw BleErrorUtils.characteristicNotFound(Integer.toString(i));
        }
        Descriptor descriptorByUUID = characteristic.getDescriptorByUUID(convert);
        if (descriptorByUUID != null) {
            return descriptorByUUID;
        }
        throw BleErrorUtils.descriptorNotFound(str);
    }

    private Descriptor getDescriptor(int i) throws BleError {
        Descriptor descriptor = this.discoveredDescriptors.get(i);
        if (descriptor != null) {
            return descriptor;
        }
        throw BleErrorUtils.descriptorNotFound(Integer.toString(i));
    }

    @Override
    public void cancelTransaction(String str) {
        this.pendingTransactions.removeDisposable(str);
    }

    @Override
    public String getLogLevel() {
        return LogLevel.fromLogLevel(this.currentLogLevel);
    }

    @Override
    public void setLogLevel(String str) {
        this.currentLogLevel = LogLevel.toLogLevel(str);
        RxBleLog.updateLogOptions(new LogOptions.Builder().setLogLevel(Integer.valueOf(this.currentLogLevel)).build());
    }

    private Disposable monitorAdapterStateChanges(Context context, final OnEventCallback<String> onEventCallback) {
        if (bluetoothLowEnergyNotSupported()) {
            return null;
        }
        return new RxBleAdapterStateObservable(context).map(new Function<RxBleAdapterStateObservable.BleAdapterState, String>() {
            @Override
            public String apply(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
                return mapRxBleAdapterStateToLocalBluetoothState(bleAdapterState);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) {
                onEventCallback.onEvent(str);
            }
        }, new Consumer() {
            @Override
            public final void accept(Object obj) {
                LogUtils.e(BleModule.TAG, (Throwable) obj);
            }
        });
    }

    private boolean bluetoothLowEnergyNotSupported() {
        return !this.context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public String mapRxBleAdapterStateToLocalBluetoothState(RxBleAdapterStateObservable.BleAdapterState bleAdapterState) {
        return bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_ON ? Constants.BluetoothState.POWERED_ON : bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_OFF ? Constants.BluetoothState.POWERED_OFF : Constants.BluetoothState.RESETTING;
    }

    private void changeAdapterState(final RxBleAdapterStateObservable.BleAdapterState bleAdapterState, final String str, OnSuccessCallback<Void> onSuccessCallback, OnErrorCallback onErrorCallback) {
        boolean disable;
        if (this.bluetoothManager == null) {
            onErrorCallback.onError(new BleError(BleErrorId.BluetoothStateChangeFailed, "BluetoothManager is null", null));
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Disposable subscribe = new RxBleAdapterStateObservable(this.context).takeUntil(new Predicate<RxBleAdapterStateObservable.BleAdapterState>() {
            @Override
            public boolean test(RxBleAdapterStateObservable.BleAdapterState bleAdapterState2) {
                return bleAdapterState == bleAdapterState2;
            }
        }).ignoreElements().doOnDispose(new Action() {
            @Override
            public void run() {
                safeExecutor.error(BleErrorUtils.cancelled());
                pendingTransactions.removeDisposable(str);
            }
        }).subscribe(new Action() {
            @Override
            public void run() {
                safeExecutor.success(null);
                pendingTransactions.removeDisposable(str);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                pendingTransactions.removeDisposable(str);
            }
        });
        if (bleAdapterState == RxBleAdapterStateObservable.BleAdapterState.STATE_ON) {
            disable = this.bluetoothAdapter.enable();
        } else {
            disable = this.bluetoothAdapter.disable();
        }
        if (!disable) {
            subscribe.dispose();
            onErrorCallback.onError(new BleError(BleErrorId.BluetoothStateChangeFailed, String.format("Couldn't set bluetooth adapter state to %s", bleAdapterState.toString()), null));
            return;
        }
        this.pendingTransactions.replaceDisposable(str, subscribe);
    }

    private void safeStartDeviceScan(UUID[] uuidArr, int i, int i2, final OnEventCallback<ScanResult> onEventCallback, final OnErrorCallback onErrorCallback) {
        if (this.rxBleClient == null) {
            onErrorCallback.onError(new BleError(BleErrorId.BluetoothManagerDestroyed, "BleManager not created when tried to start device scan", null));
            return;
        }
        ScanSettings build = new ScanSettings.Builder().setScanMode(i).setCallbackType(i2).build();
        int length = uuidArr == null ? 0 : uuidArr.length;
        ScanFilter[] scanFilterArr = new ScanFilter[length];
        for (int i3 = 0; i3 < length; i3++) {
            scanFilterArr[i3] = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(uuidArr[i3].toString())).build();
        }
        this.scanSubscription = this.rxBleClient.scanBleDevices(build, scanFilterArr).subscribe(new Consumer<ScanResult>() {
            @Override
            public void accept(ScanResult scanResult) {
                String macAddress = scanResult.getBleDevice().getMacAddress();
                if (!discoveredDevices.containsKey(macAddress)) {
                    discoveredDevices.put(macAddress, rxBleDeviceToDeviceMapper.map(scanResult.getBleDevice()));
                }
                onEventCallback.onEvent(scanResult);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable th) {
                onErrorCallback.onError(errorConverter.toError(th));
            }
        });
    }

    private Device getDeviceById(String str) throws BleError {
        Device device = this.connectedDevices.get(str);
        if (device != null) {
            return device;
        }
        throw BleErrorUtils.deviceNotConnected(str);
    }

    private RxBleConnection getConnectionOrEmitError(String str, OnErrorCallback onErrorCallback) {
        RxBleConnection rxBleConnection = this.activeConnections.get(str);
        if (rxBleConnection == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotConnected(str));
            return null;
        }
        return rxBleConnection;
    }

    private void safeConnectToDevice(final RxBleDevice rxBleDevice, boolean z, final int i, RefreshGattMoment refreshGattMoment, Long l, final int i2, OnSuccessCallback<Device> onSuccessCallback, final OnEventCallback<ConnectionState> onEventCallback, OnErrorCallback onErrorCallback) {
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Observable doOnDispose = rxBleDevice.establishConnection(z).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) {
                onEventCallback.onEvent(ConnectionState.CONNECTING);
            }
        }).doOnDispose(new Action() {
            @Override
            public void run() {
                safeExecutor.error(BleErrorUtils.cancelled());
                LogUtils.d(BleModule.TAG, "connection doOnDispose");
                onDeviceDisconnected(rxBleDevice);
                onEventCallback.onEvent(ConnectionState.DISCONNECTED);
            }
        });
        Observable observable = doOnDispose;
        if (refreshGattMoment == RefreshGattMoment.ON_CONNECTED) {
            observable = doOnDispose.flatMap(new Function<RxBleConnection, Observable<RxBleConnection>>() {
                @Override
                public Observable<RxBleConnection> apply(final RxBleConnection rxBleConnection) {
                    return rxBleConnection.queue(new RefreshGattCustomOperation()).map(new Function<Boolean, RxBleConnection>() {
                        @Override
                        public RxBleConnection apply(Boolean bool) {
                            return rxBleConnection;
                        }
                    });
                }
            });
        }
        if (i2 > 0) {
            observable = observable.flatMap(new Function<RxBleConnection, Observable<RxBleConnection>>() {
                @Override
                public Observable<RxBleConnection> apply(RxBleConnection rxBleConnection) {
                    return rxBleConnection.requestConnectionPriority(i2, 1L, TimeUnit.MILLISECONDS).andThen(Observable.just(rxBleConnection));
                }
            });
        }
        if (i > 0) {
            observable = observable.flatMap(new Function<RxBleConnection, Observable<RxBleConnection>>() {
                @Override
                public Observable<RxBleConnection> apply(final RxBleConnection rxBleConnection) {
                    return rxBleConnection.requestMtu(i).map(new Function<Integer, RxBleConnection>() {
                        @Override
                        public RxBleConnection apply(Integer num) {
                            return rxBleConnection;
                        }
                    }).toObservable();
                }
            });
        }
        if (l != null) {
            observable = observable.timeout(l.longValue(), TimeUnit.MILLISECONDS, Observable.never());
        }
        DisposableObserver<RxBleConnection> disposableObserver = new DisposableObserver<RxBleConnection>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                LogUtils.d(BleModule.TAG, "connection onError");
                onDeviceDisconnected(rxBleDevice);
                onEventCallback.onEvent(ConnectionState.DISCONNECTED);
            }

            @Override
            public void onNext(RxBleConnection rxBleConnection) {
                Device map = rxBleDeviceToDeviceMapper.map(rxBleDevice);
                onEventCallback.onEvent(ConnectionState.CONNECTED);
                cleanServicesAndCharacteristicsForDevice(map);
                connectedDevices.put(rxBleDevice.getMacAddress(), map);
                activeConnections.put(rxBleDevice.getMacAddress(), rxBleConnection);
                safeExecutor.success(map);
            }
        };
        observable.subscribe(disposableObserver);
        this.connectingDevices.replaceDisposable(rxBleDevice.getMacAddress(), disposableObserver);
    }

    public void onDeviceDisconnected(RxBleDevice rxBleDevice) {
        LogUtils.d(TAG, "onDeviceDisconnected " + rxBleDevice.getMacAddress());
        this.activeConnections.remove(rxBleDevice.getMacAddress());
        Device remove = this.connectedDevices.remove(rxBleDevice.getMacAddress());
        if (remove == null) {
            return;
        }
        cleanServicesAndCharacteristicsForDevice(remove);
        this.connectingDevices.removeDisposable(remove.getId());
        this.pendingTransactions.removeAllDisposables();
    }

    private void safeDiscoverAllServicesAndCharacteristicsForDevice(final Device device, final String str, OnSuccessCallback<Device> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(device.getId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Single<RxBleDeviceServices> doOnDispose = connectionOrEmitError.discoverServices().doOnDispose(new Action() {
            @Override
            public void run() {
                safeExecutor.error(BleErrorUtils.cancelled());
                pendingTransactions.removeDisposable(str);
            }
        });
        DisposableSingleObserver<RxBleDeviceServices> disposableSingleObserver = new DisposableSingleObserver<RxBleDeviceServices>() {
            @Override
            public void onSuccess(RxBleDeviceServices rxBleDeviceServices) {
                ArrayList arrayList = new ArrayList();
                for (BluetoothGattService bluetoothGattService : rxBleDeviceServices.getBluetoothGattServices()) {
                    Service create = serviceFactory.create(device.getId(), bluetoothGattService);
                    discoveredServices.put(create.getId(), create);
                    arrayList.add(create);
                    for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                        Characteristic characteristic = new Characteristic(create, bluetoothGattCharacteristic);
                        discoveredCharacteristics.put(characteristic.getId(), characteristic);
                        for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                            Descriptor descriptor = new Descriptor(characteristic, bluetoothGattDescriptor);
                            discoveredDescriptors.put(descriptor.getId(), descriptor);
                        }
                    }
                }
                device.setServices(arrayList);
                safeExecutor.success(device);
                pendingTransactions.removeDisposable(str);
            }

            @Override
            public void onError(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                pendingTransactions.removeDisposable(str);
            }
        };
        doOnDispose.subscribe(disposableSingleObserver);
        this.pendingTransactions.replaceDisposable(str, disposableSingleObserver);
    }

    private void safeReadCharacteristicForDevice(final Characteristic characteristic, final String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(characteristic.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Single<byte[]> doOnDispose = connectionOrEmitError.readCharacteristic(characteristic.gattCharacteristic).doOnDispose(new Action() {
            @Override
            public void run() {
                safeExecutor.error(BleErrorUtils.cancelled());
                pendingTransactions.removeDisposable(str);
            }
        });
        DisposableSingleObserver<byte[]> disposableSingleObserver = new DisposableSingleObserver<byte[]>() {
            @Override
            public void onSuccess(byte[] bArr) {
                characteristic.logValue("Read from", bArr);
                characteristic.setValue(bArr);
                safeExecutor.success(new Characteristic(characteristic));
                pendingTransactions.removeDisposable(str);
            }

            @Override
            public void onError(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                pendingTransactions.removeDisposable(str);
            }
        };
        doOnDispose.subscribe(disposableSingleObserver);
        this.pendingTransactions.replaceDisposable(str, disposableSingleObserver);
    }

    private void writeCharacteristicWithValue(Characteristic characteristic, String str, Boolean bool, String str2, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        try {
            byte[] decode = Base64Converter.decode(str);
            characteristic.setWriteType(bool.booleanValue() ? 2 : 1);
            safeWriteCharacteristicForDevice(characteristic, decode, str2, onSuccessCallback, onErrorCallback);
        } catch (Throwable unused) {
            onErrorCallback.onError(BleErrorUtils.invalidWriteDataForCharacteristic(str, UUIDConverter.fromUUID(characteristic.getUuid())));
        }
    }

    private void safeWriteCharacteristicForDevice(final Characteristic characteristic, byte[] bArr, final String str, OnSuccessCallback<Characteristic> onSuccessCallback, OnErrorCallback onErrorCallback) {
        RxBleConnection connectionOrEmitError = getConnectionOrEmitError(characteristic.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(onSuccessCallback, onErrorCallback);
        Single<byte[]> doOnDispose = connectionOrEmitError.writeCharacteristic(characteristic.gattCharacteristic, bArr).doOnDispose(new Action() {
            @Override
            public void run() {
                LogUtils.d(BleModule.TAG, "writeCharacteristic doOnDispose " + str);
                safeExecutor.error(BleErrorUtils.cancelled());
                pendingTransactions.removeDisposable(str);
            }
        });
        DisposableSingleObserver<byte[]> disposableSingleObserver = new DisposableSingleObserver<byte[]>() {
            @Override
            public void onSuccess(byte[] bArr2) {
                characteristic.logValue("Write to", bArr2);
                characteristic.setValue(bArr2);
                safeExecutor.success(new Characteristic(characteristic));
                pendingTransactions.removeDisposable(str);
            }

            @Override
            public void onError(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                pendingTransactions.removeDisposable(str);
            }
        };
        doOnDispose.subscribe(disposableSingleObserver);
        this.pendingTransactions.replaceDisposable(str, disposableSingleObserver);
    }

    private void safeMonitorCharacteristicForDevice(final Characteristic characteristic, final String str, final OnEventCallback<Characteristic> onEventCallback, OnErrorCallback onErrorCallback) {
        final RxBleConnection connectionOrEmitError = getConnectionOrEmitError(characteristic.getDeviceId(), onErrorCallback);
        if (connectionOrEmitError == null) {
            return;
        }
        final SafeExecutor safeExecutor = new SafeExecutor(null, onErrorCallback);
        Observable doOnDispose = Observable.defer(new Callable<Observable<Observable<byte[]>>>() {
            @Override
            public Observable<Observable<byte[]>> call() {
                NotificationSetupMode notificationSetupMode;
                if (characteristic.getGattDescriptor(Constants.CLIENT_CHARACTERISTIC_CONFIG_UUID) != null) {
                    notificationSetupMode = NotificationSetupMode.QUICK_SETUP;
                } else {
                    notificationSetupMode = NotificationSetupMode.COMPAT;
                }
                if (characteristic.isNotifiable()) {
                    return connectionOrEmitError.setupNotification(characteristic.gattCharacteristic, notificationSetupMode);
                }
                if (characteristic.isIndicatable()) {
                    return connectionOrEmitError.setupIndication(characteristic.gattCharacteristic, notificationSetupMode);
                }
                return Observable.error(new CannotMonitorCharacteristicException(characteristic));
            }
        }).flatMap(new Function<Observable<byte[]>, Observable<byte[]>>() {
            @Override
            public Observable<byte[]> apply(Observable<byte[]> observable) {
                return observable;
            }
        }).observeOn(Schedulers.computation()).doOnDispose(new Action() {
            @Override
            public void run() {
                safeExecutor.error(BleErrorUtils.cancelled());
                pendingTransactions.removeDisposable(str);
            }
        });
        DisposableObserver<byte[]> disposableObserver = new DisposableObserver<byte[]>() {
            @Override
            public void onComplete() {
                pendingTransactions.removeDisposable(str);
            }

            @Override
            public void onError(Throwable th) {
                safeExecutor.error(errorConverter.toError(th));
                pendingTransactions.removeDisposable(str);
            }

            @Override
            public void onNext(byte[] bArr) {
                characteristic.logValue("Notification from", bArr);
                characteristic.setValue(bArr);
                onEventCallback.onEvent(new Characteristic(characteristic));
            }
        };
        doOnDispose.subscribe(disposableObserver);
        this.pendingTransactions.replaceDisposable(str, disposableObserver);
    }

    private Characteristic getCharacteristicOrEmitError(String str, String str2, String str3, OnErrorCallback onErrorCallback) {
        UUID[] convert = UUIDConverter.convert(str2, str3);
        if (convert == null) {
            onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(str2, str3));
            return null;
        }
        Device device = this.connectedDevices.get(str);
        if (device == null) {
            onErrorCallback.onError(BleErrorUtils.deviceNotConnected(str));
            return null;
        }
        Service serviceByUUID = device.getServiceByUUID(convert[0]);
        if (serviceByUUID == null) {
            onErrorCallback.onError(BleErrorUtils.serviceNotFound(str2));
            return null;
        }
        Characteristic characteristicByUUID = serviceByUUID.getCharacteristicByUUID(convert[1]);
        if (characteristicByUUID == null) {
            onErrorCallback.onError(BleErrorUtils.characteristicNotFound(str3));
            return null;
        }
        return characteristicByUUID;
    }

    private Characteristic getCharacteristicOrEmitError(int i, String str, OnErrorCallback onErrorCallback) {
        UUID convert = UUIDConverter.convert(str);
        if (convert == null) {
            onErrorCallback.onError(BleErrorUtils.invalidIdentifiers(str));
            return null;
        }
        Service service = this.discoveredServices.get(i);
        if (service == null) {
            onErrorCallback.onError(BleErrorUtils.serviceNotFound(Integer.toString(i)));
            return null;
        }
        Characteristic characteristicByUUID = service.getCharacteristicByUUID(convert);
        if (characteristicByUUID == null) {
            onErrorCallback.onError(BleErrorUtils.characteristicNotFound(str));
            return null;
        }
        return characteristicByUUID;
    }

    private Characteristic getCharacteristicOrEmitError(int i, OnErrorCallback onErrorCallback) {
        Characteristic characteristic = this.discoveredCharacteristics.get(i);
        if (characteristic == null) {
            onErrorCallback.onError(BleErrorUtils.characteristicNotFound(Integer.toString(i)));
            return null;
        }
        return characteristic;
    }

    public void cleanServicesAndCharacteristicsForDevice(Device device) {
        for (int size = this.discoveredServices.size() - 1; size >= 0; size--) {
            int keyAt = this.discoveredServices.keyAt(size);
            if (this.discoveredServices.get(keyAt).getDeviceID().equals(device.getId())) {
                this.discoveredServices.remove(keyAt);
            }
        }
        for (int size2 = this.discoveredCharacteristics.size() - 1; size2 >= 0; size2--) {
            int keyAt2 = this.discoveredCharacteristics.keyAt(size2);
            if (this.discoveredCharacteristics.get(keyAt2).getDeviceId().equals(device.getId())) {
                this.discoveredCharacteristics.remove(keyAt2);
            }
        }
        for (int size3 = this.discoveredDescriptors.size() - 1; size3 >= 0; size3--) {
            int keyAt3 = this.discoveredDescriptors.keyAt(size3);
            if (this.discoveredDescriptors.get(keyAt3).getDeviceId().equals(device.getId())) {
                this.discoveredDescriptors.remove(keyAt3);
            }
        }
    }
}
