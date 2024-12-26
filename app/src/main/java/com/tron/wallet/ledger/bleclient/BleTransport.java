package com.tron.wallet.ledger.bleclient;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.ledger.bleclient.BleTransport;
import com.tron.wallet.ledger.bleclient.LedgerDeviceModel;
import com.tron.wallet.ledger.blemodule.Characteristic;
import com.tron.wallet.ledger.blemodule.Device;
import com.tron.wallet.ledger.blemodule.Service;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tron.wallet.ledger.blemodule.errors.BleErrorId;
import com.tron.wallet.ledger.blemodule.utils.Base64Converter;
import com.tron.wallet.ledger.blemodule.utils.DisposableMap;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import j$.util.DesugarArrays;
import j$.util.Objects;
import j$.util.function.Predicate$-CC;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.bouncycastle.util.encoders.Hex;
public class BleTransport extends Transport {
    private static final byte CLA_MTU = 8;
    private static final String TAG = "BleTransport";
    private static final byte TagId = 5;
    private static final byte TagId_MTU = 8;
    private static final int delayAfterFirstPairing = 4000;
    private static final int pairingThreshold = 1000;
    private Device activeDevice;
    private String deviceIdentifier;
    private Characteristic notifyC;
    private Observable<Characteristic> notifyObservable;
    private Characteristic writeC;
    private DisposableMap pendingTransactions = new DisposableMap();
    private int mtuSize = 20;
    private AtomicBoolean isBusy = new AtomicBoolean(false);
    private int currPairingThreshold = 1000;
    private BleClientManager bleManager = BleClientManager.getInstance();

    public void lambda$open$1(Device device) {
        this.activeDevice = device;
    }

    @Override
    public Device getDevice() {
        return this.activeDevice;
    }

    @Override
    public String getDeviceId() {
        return this.deviceIdentifier;
    }

    public BleTransport(String str) {
        this.deviceIdentifier = str;
    }

    public void reInit() {
        LogUtils.d(TAG, "reInit");
        this.pendingTransactions.removeAllDisposables();
        this.writeC = null;
        this.notifyC = null;
        this.notifyObservable = null;
        this.activeDevice = null;
        this.isBusy.set(false);
    }

    @Override
    public boolean isConnected() {
        try {
            if (this.bleManager.isDeviceConnected(this.deviceIdentifier)) {
                return this.activeDevice != null;
            }
            return false;
        } catch (BleError e) {
            LogUtils.e(e);
            return false;
        }
    }

    @Override
    public Observable<Device> open() {
        return open(20000L);
    }

    @Override
    public Observable<Device> open(long j) {
        Observable<Device> connectToDevice;
        Device device;
        LogUtils.d(TAG, "open: " + j);
        if (this.writeC != null && this.notifyC != null && (device = this.activeDevice) != null) {
            return Observable.just(device);
        }
        reInit();
        Device[] connectedDevices = this.bleManager.connectedDevices();
        if (connectedDevices != null) {
            DesugarArrays.stream(connectedDevices).filter(new Predicate() {
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
                    boolean lambda$open$0;
                    lambda$open$0 = lambda$open$0((Device) obj);
                    return lambda$open$0;
                }
            }).findFirst().ifPresent(new Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$open$1((Device) obj);
                }

                public Consumer andThen(Consumer consumer) {
                    return Objects.requireNonNull(consumer);
                }
            });
        }
        if (this.activeDevice != null) {
            LogUtils.d(TAG, "found connected device: " + this.activeDevice.getName());
            connectToDevice = Observable.just(this.activeDevice);
        } else {
            connectToDevice = this.bleManager.connectToDevice(this.deviceIdentifier);
        }
        return connectToDevice.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$open$2;
                lambda$open$2 = lambda$open$2((Device) obj);
                return lambda$open$2;
            }
        }).doOnNext(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$open$3((Device) obj);
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$open$4;
                lambda$open$4 = lambda$open$4((Device) obj);
                return lambda$open$4;
            }
        }).retryWhen(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$open$7;
                lambda$open$7 = lambda$open$7((Observable) obj);
                return lambda$open$7;
            }
        }).timeout(j, TimeUnit.MILLISECONDS, Schedulers.io()).doOnError(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$open$8((Throwable) obj);
            }
        });
    }

    public boolean lambda$open$0(Device device) {
        return device.getId().equals(this.deviceIdentifier);
    }

    public ObservableSource lambda$open$2(Device device) throws Exception {
        LogUtils.d(TAG, "connected:" + device.getName() + " , mac: " + device.getId());
        this.activeDevice = device;
        return this.bleManager.discoverAllServicesAndCharacteristicsForDevice(device.getId());
    }

    public void lambda$open$3(Device device) throws Exception {
        for (Service service : device.getServices()) {
            LogUtils.d(TAG, "Service uuid: " + service.getUuid());
            LedgerDeviceModel.BluetoothSpec bluetoothSpecForServiceUuid = LedgerDeviceModel.getBluetoothSpecForServiceUuid(service.getUuid().toString());
            if (bluetoothSpecForServiceUuid != null) {
                for (Characteristic characteristic : service.getCharacteristics()) {
                    LogUtils.d(TAG, "Characteristic uuid: " + characteristic.getUuid());
                    if (bluetoothSpecForServiceUuid.getWriteUuid().equals(characteristic.getUuid().toString())) {
                        LogUtils.d(TAG, "found Characteristic uuid: " + characteristic.getUuid());
                        this.writeC = characteristic;
                    } else if (bluetoothSpecForServiceUuid.getNotifyUuid().equals(characteristic.getUuid().toString())) {
                        LogUtils.d(TAG, "monitor Characteristic uuid: " + characteristic.getUuid());
                        this.notifyC = characteristic;
                    }
                }
            }
        }
        Characteristic characteristic2 = this.writeC;
        if (characteristic2 == null) {
            throw new BleError(BleErrorId.BleChracteristicNotFound, "write characteristic not found");
        }
        if (this.notifyC == null) {
            throw new BleError(BleErrorId.BleChracteristicNotFound, "notify characteristic not found");
        }
        if (!characteristic2.isWritableWithResponse()) {
            throw new BleError(BleErrorId.BleChracteristicInvalid, "write characteristic not writableWithResponse");
        }
        if (!this.notifyC.isNotifiable()) {
            throw new BleError(BleErrorId.BleChracteristicInvalid, "notify characteristic not notifiable");
        }
        LogUtils.d(TAG, "device mtu: " + device.getMtu());
    }

    public ObservableSource lambda$open$4(Device device) throws Exception {
        monitorCharacteristic();
        return inferMTU();
    }

    public ObservableSource lambda$open$7(Observable observable) throws Exception {
        return observable.flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$open$6;
                lambda$open$6 = lambda$open$6((Throwable) obj);
                return lambda$open$6;
            }
        });
    }

    public ObservableSource lambda$open$6(Throwable th) throws Exception {
        LogUtils.d(TAG, "retryWhen:" + th.getMessage());
        if ((th instanceof BleError) && ((BleError) th).errorId == BleErrorId.ReconnectAfterFirstPaired) {
            Observable.timer(100L, TimeUnit.MILLISECONDS).subscribe(new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    lambda$open$5((Long) obj);
                }
            });
            return Observable.timer(4000L, TimeUnit.MILLISECONDS);
        }
        return Observable.error(th);
    }

    public void lambda$open$5(Long l) throws Exception {
        disconnect();
    }

    public void lambda$open$8(Throwable th) throws Exception {
        disconnect();
    }

    @Override
    Observable<byte[]> send(final byte b, final byte b2, final byte b3, final byte b4, final byte[] bArr) throws BleError {
        return open().flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$send$10;
                lambda$send$10 = lambda$send$10(b, b2, b3, b4, bArr, (Device) obj);
                return lambda$send$10;
            }
        });
    }

    public ObservableSource lambda$send$10(byte b, byte b2, byte b3, byte b4, byte[] bArr, Device device) throws Exception {
        return write(b, b2, b3, b4, bArr, true, (byte) 5).doOnNext(new io.reactivex.functions.Consumer() {
            @Override
            public final void accept(Object obj) {
                BleTransport.lambda$send$9((byte[]) obj);
            }
        });
    }

    public static void lambda$send$9(byte[] bArr) throws Exception {
        if (bArr != null && bArr.length >= 2) {
            int readUInt16FromByteBufferBe = BleUtils.readUInt16FromByteBufferBe(bArr, bArr.length - 2);
            if (readUInt16FromByteBufferBe != 36864) {
                throw new BleError(BleErrorId.BleErrorCode, String.format("BLE: received error code: 0x%x", Integer.valueOf(readUInt16FromByteBufferBe)), null, Integer.valueOf(readUInt16FromByteBufferBe));
            }
            return;
        }
        throw new BleError(BleErrorId.BleTooLittleData, "BLE: received too little data.");
    }

    Observable<byte[]> write(byte b, byte b2, byte b3, byte b4, byte[] bArr, boolean z, byte b5) throws BleError {
        if (!this.isBusy.compareAndSet(false, true)) {
            throw new BleError(BleErrorId.BleIsBusy, "An action was already pending on the Ledger device. Please deny or reconnect.");
        }
        byte[] bytesConcat = BleUtils.bytesConcat(b, b2, b3, b4, bArr);
        if (bytesConcat == null || bytesConcat.length == 0) {
            throw new BleError(BleErrorId.DataIsNull, "apdu is null");
        }
        if (bytesConcat.length > 256) {
            throw new BleError(BleErrorId.DataLengthTooBig, "apdu is too long");
        }
        return Single.create(new fun1(b5, bytesConcat, z)).doOnDispose(new Action() {
            @Override
            public final void run() {
                lambda$write$11();
            }
        }).doOnTerminate(new Action() {
            @Override
            public final void run() {
                lambda$write$12();
            }
        }).toObservable().subscribeOn(Schedulers.io());
    }

    public class fun1 implements SingleOnSubscribe<byte[]> {
        final boolean val$chunk;
        final byte[] val$data;
        final byte val$tagId;

        fun1(byte b, byte[] bArr, boolean z) {
            this.val$tagId = b;
            this.val$data = bArr;
            this.val$chunk = z;
        }

        @Override
        public void subscribe(final SingleEmitter<byte[]> singleEmitter) throws Exception {
            final Receiver receiver = new Receiver(this.val$tagId, singleEmitter);
            pendingTransactions.replaceDisposable("receiveAPDU", notifyObservable.subscribe(new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    BleTransport.Receiver.this.receiveAPDU(((Characteristic) obj).getValue());
                }
            }, new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    BleTransport.1.lambda$subscribe$1(SingleEmitter.this, (Throwable) obj);
                }
            }));
            pendingTransactions.replaceDisposable("sendAPDU", sendAPDU(this.val$data, this.val$chunk).subscribe(new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    LogUtils.d(BleTransport.TAG, "apdu => " + ((Boolean) obj));
                }
            }, new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    BleTransport.1.lambda$subscribe$3(SingleEmitter.this, (Throwable) obj);
                }
            }));
        }

        public static void lambda$subscribe$1(SingleEmitter singleEmitter, Throwable th) throws Exception {
            if (singleEmitter.isDisposed()) {
                return;
            }
            singleEmitter.onError(th);
        }

        public static void lambda$subscribe$3(SingleEmitter singleEmitter, Throwable th) throws Exception {
            LogUtils.d(BleTransport.TAG, "apdu => " + th.getMessage());
            if (singleEmitter.isDisposed()) {
                return;
            }
            singleEmitter.onError(th);
        }
    }

    public void lambda$write$11() throws Exception {
        LogUtils.d(TAG, "write dispose");
        this.isBusy.compareAndSet(true, false);
        this.pendingTransactions.removeDisposable("sendAPDU");
        this.pendingTransactions.removeDisposable("receiveAPDU");
    }

    public void lambda$write$12() throws Exception {
        LogUtils.d(TAG, "write terminate");
        this.isBusy.compareAndSet(true, false);
        this.pendingTransactions.removeDisposable("sendAPDU");
        this.pendingTransactions.removeDisposable("receiveAPDU");
    }

    @Override
    public void disconnect() {
        LogUtils.d(TAG, "disconnect");
        this.bleManager.cancelDeviceConnection(this.deviceIdentifier);
        reInit();
    }

    private Observable<Device> inferMTU() throws BleError {
        LogUtils.d(TAG, "inferMTU");
        final long currentTimeMillis = System.currentTimeMillis();
        return write((byte) 8, (byte) 0, (byte) 0, (byte) 0, null, false, (byte) 8).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$inferMTU$13;
                lambda$inferMTU$13 = lambda$inferMTU$13(currentTimeMillis, (byte[]) obj);
                return lambda$inferMTU$13;
            }
        });
    }

    public ObservableSource lambda$inferMTU$13(long j, byte[] bArr) throws Exception {
        if (bArr != null && bArr.length == 1) {
            int i = bArr[0] & 255;
            int i2 = i + 3;
            this.mtuSize = i2;
            if (i2 > 23) {
                this.mtuSize = i;
            }
        }
        LogUtils.d(TAG, "infer mtu size: " + this.mtuSize);
        long currentTimeMillis = System.currentTimeMillis() - j;
        int i3 = this.currPairingThreshold;
        if (currentTimeMillis > i3) {
            this.currPairingThreshold = i3 + 1000;
            return Observable.error(new BleError(BleErrorId.ReconnectAfterFirstPaired, "reconnect after first paired"));
        }
        return Observable.just(this.activeDevice);
    }

    public Observable<Boolean> sendAPDU(byte[] bArr, boolean z) {
        LogUtils.d(TAG, "apdu => " + Hex.toHexString(bArr));
        return new Sender(this.mtuSize, z).sendAPDU(bArr).subscribeOn(Schedulers.io());
    }

    public Observable<Characteristic> writeCharacteristic(byte[] bArr) {
        return BleClientManager.getInstance().writeCharacteristic(this.writeC.getId(), Base64Converter.encode(bArr));
    }

    private void monitorCharacteristic() {
        LogUtils.d(TAG, "set up monitorCharacteristic");
        this.notifyObservable = BleClientManager.getInstance().monitorCharacteristic(this.notifyC.getId()).observeOn(Schedulers.io());
        DisposableObserver<Characteristic> disposableObserver = new DisposableObserver<Characteristic>() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onNext(Characteristic characteristic) {
                LogUtils.d(BleTransport.TAG, "monitor:" + Hex.toHexString(characteristic.getValue()));
            }

            @Override
            public void onError(Throwable th) {
                LogUtils.e("BleTransport monitorCharacteristic", th);
                reInit();
            }
        };
        this.notifyObservable.subscribe(disposableObserver);
        this.pendingTransactions.replaceDisposable("monitorAPDU", disposableObserver);
    }

    public class Receiver {
        private byte[] notifiedData;
        private volatile int notifiedDataLength;
        private volatile int notifiedIndex;
        private volatile int receivedDataLength;
        private SingleEmitter<byte[]> sourceObserver;
        private byte tagId;

        public Receiver(byte b, SingleEmitter<byte[]> singleEmitter) {
            this.tagId = b;
            this.sourceObserver = singleEmitter;
        }

        public void receiveAPDU(byte[] bArr) {
            LogUtils.d(BleTransport.TAG, "notify data length: " + this.notifiedDataLength + " received index: " + this.notifiedIndex);
            if (bArr == null) {
                if (this.sourceObserver.isDisposed()) {
                    return;
                }
                this.sourceObserver.onError(new BleError(BleErrorId.InvalidDataNull, "receive data null"));
                return;
            }
            byte b = bArr[0];
            int readUInt16FromByteBufferBe = BleUtils.readUInt16FromByteBufferBe(bArr, 1);
            LogUtils.d(BleTransport.TAG, "tag: " + ((int) b) + " index: " + readUInt16FromByteBufferBe);
            if (b != this.tagId) {
                LogUtils.d(BleTransport.TAG, "error tag");
                if (this.sourceObserver.isDisposed()) {
                    return;
                }
                this.sourceObserver.onError(new BleError(BleErrorId.InvalidTag, "Invalid tag " + ((int) b)));
            } else if (this.notifiedIndex != readUInt16FromByteBufferBe) {
                LogUtils.d(BleTransport.TAG, "error index");
                if (this.sourceObserver.isDisposed()) {
                    return;
                }
                this.sourceObserver.onError(new BleError(BleErrorId.InvalidSequence, "BLE: Invalid sequence number. discontinued chunk. Received " + readUInt16FromByteBufferBe + " but expected " + this.notifiedIndex));
            } else {
                int i = 3;
                if (readUInt16FromByteBufferBe == 0) {
                    this.notifiedDataLength = BleUtils.readUInt16FromByteBufferBe(bArr, 3);
                    LogUtils.d(BleTransport.TAG, "get notify data length: " + this.notifiedDataLength);
                    i = 5;
                }
                this.notifiedIndex++;
                if ((this.receivedDataLength + bArr.length) - i > this.notifiedDataLength) {
                    if (this.sourceObserver.isDisposed()) {
                        return;
                    }
                    this.sourceObserver.onError(new BleError(BleErrorId.BleTooMuchData, "BLE: received too much data. discontinued chunk. Received " + this.notifiedData.length + " but expected " + this.notifiedDataLength));
                    return;
                }
                this.notifiedData = BleUtils.bytesConcat(this.notifiedData, bArr, i);
                this.receivedDataLength += bArr.length - i;
                LogUtils.d(BleTransport.TAG, "notify data length: " + this.notifiedDataLength + " received length: " + this.receivedDataLength);
                if (this.receivedDataLength == this.notifiedDataLength) {
                    LogUtils.d(BleTransport.TAG, "apdu <= " + Hex.toHexString(this.notifiedData));
                    this.sourceObserver.onSuccess(this.notifiedData);
                }
            }
        }
    }

    public class Sender {
        private boolean chunk;
        private int mtuSize;
        ObservableEmitter<Boolean> observerSource;
        private List<byte[]> sendBytes;
        private volatile int sendIndex;

        public Sender(int i, boolean z) {
            this.chunk = z;
            this.mtuSize = i;
        }

        public void sendChunkAPDU() {
            if (this.sendIndex >= this.sendBytes.size()) {
                LogUtils.d(BleTransport.TAG, "write data: finish");
                this.observerSource.onNext(true);
                this.observerSource.onComplete();
                return;
            }
            byte[] bArr = this.sendBytes.get(this.sendIndex);
            LogUtils.d(BleTransport.TAG, "ble-frame => " + Hex.toHexString(bArr));
            writeCharacteristic(bArr).subscribeOn(Schedulers.io()).subscribe(new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    BleTransport.Sender.this.lambda$sendChunkAPDU$0((Characteristic) obj);
                }
            }, new io.reactivex.functions.Consumer() {
                @Override
                public final void accept(Object obj) {
                    BleTransport.Sender.this.lambda$sendChunkAPDU$1((Throwable) obj);
                }
            });
        }

        public void lambda$sendChunkAPDU$0(Characteristic characteristic) throws Exception {
            LogUtils.d(BleTransport.TAG, "write success:" + characteristic.getUuid());
            this.sendIndex = this.sendIndex + 1;
            sendChunkAPDU();
        }

        public void lambda$sendChunkAPDU$1(Throwable th) throws Exception {
            LogUtils.d(BleTransport.TAG, "write fail:" + th.getMessage());
            if (this.observerSource.isDisposed()) {
                return;
            }
            this.observerSource.onError(th);
        }

        public Observable<Boolean> sendAPDU(final byte[] bArr) {
            return Observable.create(new ObservableOnSubscribe<Boolean>() {
                @Override
                public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                    Sender.this.observerSource = observableEmitter;
                    if (Sender.this.chunk) {
                        Sender sender = Sender.this;
                        sender.sendBytes = BleUtils.chunkBuffer(bArr, sender.mtuSize);
                    } else {
                        byte[] bArr2 = bArr;
                        byte[] bArr3 = new byte[bArr2.length];
                        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                        Sender.this.sendBytes = new ArrayList();
                        Sender.this.sendBytes.add(bArr3);
                    }
                    Sender.this.sendIndex = 0;
                    Sender.this.sendChunkAPDU();
                }
            });
        }
    }
}
