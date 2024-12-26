package com.tron.wallet.ledger.bleclient;

import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.ledger.bleclient.LedgerTrx;
import com.tron.wallet.ledger.blemodule.Device;
import com.tron.wallet.ledger.blemodule.errors.BleError;
import com.tron.wallet.ledger.blemodule.errors.BleErrorId;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.CharEncoding;
import org.bouncycastle.util.encoders.Hex;
import org.tron.common.utils.ByteUtil;
public class LedgerTrx {
    private static final String APP_NAME = "Tron";
    public static final int CHUNK_SIZE = 250;
    private static final byte CLA = -32;
    private static final byte COMMON_CLA = -80;
    private static final byte INS_GET_APP_CONFIGURATION = 6;
    private static final byte INS_GET_ECDH_SECRET = 10;
    private static final byte INS_GET_PUBLIC_KEY = 2;
    private static final byte INS_OPEN_APP = -40;
    private static final byte INS_SIGN = 4;
    private static final byte INS_SIGN_PERSONAL_MESSAGE = 8;
    private static final byte INS_SIGN_TXN_HASH = 5;
    private static final byte P1_CONFIRM = 1;
    private static final byte P1_FIRST = 0;
    private static final byte P1_LAST = -112;
    private static final byte P1_MORE = Byte.MIN_VALUE;
    private static final byte P1_NON_CONFIRM = 0;
    private static final byte P1_P2_DEFAULT = 0;
    private static final byte P1_SIGN = 16;
    private static final byte P1_TRC10_NAME = -96;
    private static final byte P2_CHAINCODE = 1;
    private static final byte P2_NO_CHAINCODE = 0;
    private static final int PATHS_LENGTH_SIZE = 1;
    private static final int PATH_SIZE = 4;
    private static final String TAG = "BleTrx";
    private Transport transport;
    private String tronVersion;

    public LedgerTrx(Transport transport) {
        this.transport = transport;
    }

    public boolean supportStakeV2() {
        if (!StringTronUtil.isEmpty(this.tronVersion)) {
            try {
                String[] split = this.tronVersion.split("\\.");
                if (split != null && split.length >= 3) {
                    int i = (Long.parseLong(split[0]) > 0L ? 1 : (Long.parseLong(split[0]) == 0L ? 0 : -1));
                    if (i > 0) {
                        return true;
                    }
                    if (i == 0) {
                        int i2 = (Long.parseLong(split[1]) > 4L ? 1 : (Long.parseLong(split[1]) == 4L ? 0 : -1));
                        if (i2 > 0) {
                            return true;
                        }
                        if (i2 == 0) {
                            if (Long.parseLong(split[2]) >= 2) {
                                return true;
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                LogUtils.e(TAG, th);
            }
        }
        return false;
    }

    private Observable<String> getAppName() throws BleError {
        return this.transport.send(COMMON_CLA, (byte) 1, (byte) 0, (byte) 0, null).map(new Function() {
            @Override
            public final Object apply(Object obj) {
                String lambda$getAppName$0;
                lambda$getAppName$0 = lambda$getAppName$0((byte[]) obj);
                return lambda$getAppName$0;
            }
        });
    }

    public String lambda$getAppName$0(byte[] bArr) throws Exception {
        if (bArr == null || bArr[0] != 1) {
            return null;
        }
        byte b = bArr[1];
        String str = new String(bArr, 2, b, CharEncoding.US_ASCII);
        LogUtils.d(TAG, "getAppName: ".concat(str));
        int i = 2 + b;
        try {
            this.tronVersion = new String(bArr, b + 3, bArr[i], CharEncoding.US_ASCII);
            LogUtils.d(TAG, "getAppName version: " + this.tronVersion);
        } catch (Throwable th) {
            LogUtils.e(th);
        }
        return str;
    }

    public Observable<String> disconnectResumeObservable(Throwable th) {
        if (th instanceof BleError) {
            BleError bleError = (BleError) th;
            if (bleError.errorId == BleErrorId.DeviceDisconnected || bleError.errorId == BleErrorId.CharacteristicWriteFailed) {
                LogUtils.e(TAG, th);
                LogUtils.d(TAG, "delay 1 seconds and reconnect");
                return Observable.timer(1L, TimeUnit.SECONDS).flatMap(new Function() {
                    @Override
                    public final Object apply(Object obj) {
                        ObservableSource lambda$disconnectResumeObservable$2;
                        lambda$disconnectResumeObservable$2 = lambda$disconnectResumeObservable$2((Long) obj);
                        return lambda$disconnectResumeObservable$2;
                    }
                });
            }
        }
        return Observable.error(th);
    }

    public ObservableSource lambda$disconnectResumeObservable$2(Long l) throws Exception {
        return this.transport.open().flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$disconnectResumeObservable$1;
                lambda$disconnectResumeObservable$1 = lambda$disconnectResumeObservable$1((Device) obj);
                return lambda$disconnectResumeObservable$1;
            }
        });
    }

    public ObservableSource lambda$disconnectResumeObservable$1(Device device) throws Exception {
        return getAppName();
    }

    public Observable<Boolean> isAppOpened() throws BleError {
        return getAppName().flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$isAppOpened$3((String) obj);
            }
        }).onErrorResumeNext(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$isAppOpened$5;
                lambda$isAppOpened$5 = lambda$isAppOpened$5((Throwable) obj);
                return lambda$isAppOpened$5;
            }
        });
    }

    public static ObservableSource lambda$isAppOpened$3(String str) throws Exception {
        if (str != null) {
            return Observable.just(Boolean.valueOf(str.equals(APP_NAME)));
        }
        return Observable.error(new BleError(BleErrorId.CannotOpenApp, "can not open Tron App, please Open the Tron App"));
    }

    public ObservableSource lambda$isAppOpened$5(Throwable th) throws Exception {
        return disconnectResumeObservable(th).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource just;
                just = Observable.just(Boolean.valueOf(LedgerTrx.APP_NAME.equals((String) obj)));
                return just;
            }
        });
    }

    public Observable<Boolean> openApp() throws BleError {
        return getAppName().onErrorResumeNext(new Function() {
            @Override
            public final Object apply(Object obj) {
                Observable disconnectResumeObservable;
                disconnectResumeObservable = disconnectResumeObservable((Throwable) obj);
                return disconnectResumeObservable;
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                ObservableSource lambda$openApp$7;
                lambda$openApp$7 = lambda$openApp$7((String) obj);
                return lambda$openApp$7;
            }
        }).timeout(20L, TimeUnit.SECONDS, Schedulers.io()).doOnError(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$openApp$8((Throwable) obj);
            }
        });
    }

    public ObservableSource lambda$openApp$7(String str) throws Exception {
        if (str != null) {
            if (str.equals(APP_NAME)) {
                return Observable.just(true).delay(1L, TimeUnit.SECONDS);
            }
            if (str.contains("OLOS")) {
                return this.transport.send(CLA, INS_OPEN_APP, (byte) 0, (byte) 0, APP_NAME.getBytes(CharEncoding.US_ASCII)).flatMap(new Function() {
                    @Override
                    public final Object apply(Object obj) {
                        ObservableSource lambda$openApp$6;
                        lambda$openApp$6 = lambda$openApp$6((byte[]) obj);
                        return lambda$openApp$6;
                    }
                });
            }
        }
        return Observable.error(new BleError(BleErrorId.CannotOpenApp, "can not open Tron App, please Open the Tron App"));
    }

    public ObservableSource lambda$openApp$6(byte[] bArr) throws Exception {
        return isAppOpened();
    }

    public void lambda$openApp$8(Throwable th) throws Exception {
        this.transport.disconnect();
    }

    public Observable<Address> getAddress(String str) throws BleError {
        return this.transport.send(CLA, (byte) 2, (byte) 0, (byte) 0, generatePathData(str)).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$getAddress$9((byte[]) obj);
            }
        });
    }

    public static ObservableSource lambda$getAddress$9(byte[] bArr) throws Exception {
        LogUtils.d(TAG, "getAddress: received bytes: " + Hex.toHexString(bArr));
        int i = bArr[0];
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 1, bArr2, 0, i);
        int i2 = bArr[i + 1];
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, i + 2, bArr3, 0, i2);
        try {
            String str = new String(bArr3, CharEncoding.US_ASCII);
            LogUtils.d(TAG, "getAddress: received address: ".concat(str));
            LogUtils.d(TAG, "getAddress: received publicKey: " + Hex.toHexString(bArr2));
            return Observable.just(new Address(str, Hex.toHexString(bArr2)));
        } catch (UnsupportedEncodingException e) {
            return Observable.error(e);
        }
    }

    public Observable<Boolean> preSignTransactionCheck(final String str) throws BleError {
        LogUtils.d(TAG, "preSignTransactionCheck:");
        return Observable.fromCallable(new Callable() {
            @Override
            public final Object call() {
                return LedgerTrx.lambda$preSignTransactionCheck$10(str);
            }
        });
    }

    public static Boolean lambda$preSignTransactionCheck$10(String str) throws Exception {
        byte[] decode = Hex.decode(str);
        int i = 0;
        while (i < decode.length) {
            int nextFieldLength = BleUtils.getNextFieldLength(decode, i);
            if (nextFieldLength > 250) {
                LogUtils.d(TAG, "index: " + i + " newpos: " + nextFieldLength);
                return false;
            }
            i += nextFieldLength;
            if (i > decode.length) {
                return false;
            }
        }
        return true;
    }

    public Observable<byte[]> signTransaction(String str, String str2) throws BleError {
        return signTransaction(str, str2, null);
    }

    public Observable<byte[]> signTransaction(String str, String str2, String[] strArr) throws BleError {
        LogUtils.d(TAG, "signTransaction:");
        byte[] generatePathData = generatePathData(str);
        byte[] decode = Hex.decode(str2);
        final ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < decode.length) {
            int nextFieldLength = BleUtils.getNextFieldLength(decode, i);
            if (nextFieldLength > 250) {
                LogUtils.d(TAG, "index: " + i + " newpos: " + nextFieldLength);
                throw new BleError(BleErrorId.VarintIsTooBig, "varint field is too big");
            } else if (generatePathData.length + nextFieldLength > 250) {
                arrayList.add(generatePathData);
                generatePathData = new byte[0];
            } else {
                if (i + nextFieldLength > decode.length) {
                    nextFieldLength = decode.length - i;
                }
                generatePathData = ByteUtil.appendBytes(generatePathData, decode, i, nextFieldLength);
                i += nextFieldLength;
            }
        }
        arrayList.add(generatePathData);
        int size = arrayList.size();
        if (strArr != null && strArr.length > 0) {
            for (String str3 : strArr) {
                arrayList.add(Hex.decode(str3));
            }
        }
        final byte[] bArr = new byte[arrayList.size()];
        if (arrayList.size() == 1) {
            bArr[0] = 16;
        } else {
            bArr[0] = 0;
            for (int i2 = 1; i2 < arrayList.size() - 1; i2++) {
                if (i2 >= size) {
                    bArr[i2] = (byte) ((i2 - size) | 160);
                } else {
                    bArr[i2] = Byte.MIN_VALUE;
                }
            }
            if (strArr != null && strArr.length > 0) {
                bArr[arrayList.size() - 1] = (byte) ((strArr.length - 1) | 168);
            } else {
                bArr[arrayList.size() - 1] = P1_LAST;
            }
        }
        return Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(final ObservableEmitter<byte[]> observableEmitter) throws Exception {
                new Sender((byte) 4, arrayList, bArr).send().subscribe(new DisposableObserver<byte[]>() {
                    private byte[] response;

                    @Override
                    public void onNext(byte[] bArr2) {
                        this.response = bArr2;
                        LogUtils.d(LedgerTrx.TAG, "signTransaction: received bytes: " + Hex.toHexString(this.response));
                    }

                    @Override
                    public void onError(Throwable th) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onError(th);
                    }

                    @Override
                    public void onComplete() {
                        observableEmitter.onNext(this.response);
                        observableEmitter.onComplete();
                    }
                });
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$signTransaction$11((byte[]) obj);
            }
        });
    }

    public static ObservableSource lambda$signTransaction$11(byte[] bArr) throws Exception {
        if (bArr != null && bArr.length >= 65) {
            byte[] bArr2 = new byte[65];
            System.arraycopy(bArr, 0, bArr2, 0, 65);
            return Observable.just(bArr2);
        }
        return Observable.error(new BleError(BleErrorId.BleDateParseError, "BLE: data error"));
    }

    public Observable<byte[]> signTransactionHash(String str, String str2) throws BleError {
        LogUtils.d(TAG, "signTransactionHash:");
        return this.transport.send(CLA, (byte) 5, (byte) 0, (byte) 0, BleUtils.bytesConcat(generatePathData(str), Hex.decode(str2), 0)).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$signTransactionHash$12((byte[]) obj);
            }
        });
    }

    public static ObservableSource lambda$signTransactionHash$12(byte[] bArr) throws Exception {
        if (bArr != null && bArr.length >= 65) {
            byte[] bArr2 = new byte[65];
            System.arraycopy(bArr, 0, bArr2, 0, 65);
            return Observable.just(bArr2);
        }
        return Observable.error(new BleError(BleErrorId.BleDateParseError, "BLE: data error"));
    }

    public Observable<AppConfiguration> getAppConfiguration() throws BleError {
        return this.transport.send(CLA, (byte) 6, (byte) 0, (byte) 0, null).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$getAppConfiguration$13((byte[]) obj);
            }
        });
    }

    public static ObservableSource lambda$getAppConfiguration$13(byte[] bArr) throws Exception {
        boolean z = false;
        byte b = bArr[0];
        boolean z2 = (b & 8) > 0;
        boolean z3 = (b & 4) > 0;
        boolean z4 = (b & 2) > 0;
        boolean z5 = (b & 1) > 0;
        byte b2 = bArr[1];
        if (b2 == 0 && bArr[2] == 1 && bArr[3] < 2) {
            z5 = true;
            z4 = false;
        }
        if (b2 != 0 || bArr[2] != 1 || bArr[3] >= 5) {
            z = z3;
        }
        AppConfiguration appConfiguration = new AppConfiguration();
        appConfiguration.setSignByHash(z2);
        appConfiguration.setTruncateAddress(z);
        appConfiguration.setAllowContract(z4);
        appConfiguration.setAllowData(z5);
        return Observable.just(appConfiguration);
    }

    public Observable<byte[]> signPersonalMessage(String str, String str2) {
        LogUtils.d(TAG, str2);
        byte[] generatePathData = generatePathData(str);
        byte[] decode = Hex.decode(str2);
        String format = String.format("%x", Integer.valueOf(decode.length));
        byte[] appendBytes = ByteUtil.appendBytes(Hex.decode("00000000".substring(0, 8 - format.length()) + format), decode, 0, decode.length);
        final ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < appendBytes.length) {
            int length = i == 0 ? 250 - generatePathData.length : 250;
            if (i + length > appendBytes.length) {
                length = appendBytes.length - i;
            }
            byte[] bArr = new byte[i == 0 ? generatePathData.length + length : length];
            if (i == 0) {
                System.arraycopy(generatePathData, 0, bArr, 0, generatePathData.length);
                System.arraycopy(appendBytes, i, bArr, generatePathData.length, length);
            } else {
                System.arraycopy(appendBytes, i, bArr, i + length, length);
            }
            arrayList.add(bArr);
            i += length;
        }
        return Observable.create(new ObservableOnSubscribe<byte[]>() {
            @Override
            public void subscribe(final ObservableEmitter<byte[]> observableEmitter) throws Exception {
                new Sender((byte) 8, arrayList, null).send().subscribe(new DisposableObserver<byte[]>() {
                    private byte[] response;

                    @Override
                    public void onNext(byte[] bArr2) {
                        this.response = bArr2;
                        LogUtils.d(LedgerTrx.TAG, "signTransaction: received bytes: " + Hex.toHexString(this.response));
                    }

                    @Override
                    public void onError(Throwable th) {
                        if (observableEmitter.isDisposed()) {
                            return;
                        }
                        observableEmitter.onError(th);
                    }

                    @Override
                    public void onComplete() {
                        observableEmitter.onNext(this.response);
                        observableEmitter.onComplete();
                    }
                });
            }
        }).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$signPersonalMessage$14((byte[]) obj);
            }
        });
    }

    public static ObservableSource lambda$signPersonalMessage$14(byte[] bArr) throws Exception {
        if (bArr != null && bArr.length >= 65) {
            byte[] bArr2 = new byte[65];
            System.arraycopy(bArr, 0, bArr2, 0, 65);
            return Observable.just(bArr2);
        }
        return Observable.error(new BleError(BleErrorId.BleDateParseError, "BLE: data error"));
    }

    public Observable<byte[]> getECDHPairKey(String str, String str2) throws BleError {
        return this.transport.send(CLA, (byte) 10, (byte) 0, (byte) 1, BleUtils.bytesConcat(generatePathData(str), Hex.decode(str2), 0)).flatMap(new Function() {
            @Override
            public final Object apply(Object obj) {
                return LedgerTrx.lambda$getECDHPairKey$15((byte[]) obj);
            }
        });
    }

    public static ObservableSource lambda$getECDHPairKey$15(byte[] bArr) throws Exception {
        if (bArr != null && bArr.length >= 65) {
            byte[] bArr2 = new byte[65];
            System.arraycopy(bArr, 0, bArr2, 0, 65);
            return Observable.just(bArr2);
        }
        return Observable.error(new BleError(BleErrorId.BleDateParseError, "BLE: data error"));
    }

    private byte[] generatePathData(String str) {
        if (str == null) {
            str = "44'/195'/0'/0/0";
        }
        List<Integer> splitPath = BleUtils.splitPath(str);
        int i = 1;
        byte[] bArr = new byte[(splitPath.size() * 4) + 1];
        bArr[0] = (byte) splitPath.size();
        for (Integer num : splitPath) {
            BleUtils.writeUInt32ToByteBufferBe(num.intValue(), bArr, i);
            i += 4;
        }
        return bArr;
    }

    public static class AppConfiguration {
        private boolean allowContract;
        private boolean allowData;
        private boolean signByHash;
        private boolean truncateAddress;
        private String version;
        private String versionN;

        public String getVersion() {
            return this.version;
        }

        public String getVersionN() {
            return this.versionN;
        }

        public boolean isAllowContract() {
            return this.allowContract;
        }

        public boolean isAllowData() {
            return this.allowData;
        }

        public boolean isSignByHash() {
            return this.signByHash;
        }

        public boolean isTruncateAddress() {
            return this.truncateAddress;
        }

        public void setAllowContract(boolean z) {
            this.allowContract = z;
        }

        public void setAllowData(boolean z) {
            this.allowData = z;
        }

        public void setSignByHash(boolean z) {
            this.signByHash = z;
        }

        public void setTruncateAddress(boolean z) {
            this.truncateAddress = z;
        }

        public void setVersion(String str) {
            this.version = str;
        }

        public void setVersionN(String str) {
            this.versionN = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof AppConfiguration;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof AppConfiguration) {
                AppConfiguration appConfiguration = (AppConfiguration) obj;
                if (appConfiguration.canEqual(this) && isAllowContract() == appConfiguration.isAllowContract() && isTruncateAddress() == appConfiguration.isTruncateAddress() && isAllowData() == appConfiguration.isAllowData() && isSignByHash() == appConfiguration.isSignByHash()) {
                    String version = getVersion();
                    String version2 = appConfiguration.getVersion();
                    if (version != null ? version.equals(version2) : version2 == null) {
                        String versionN = getVersionN();
                        String versionN2 = appConfiguration.getVersionN();
                        return versionN != null ? versionN.equals(versionN2) : versionN2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            int i = (((((((isAllowContract() ? 79 : 97) + 59) * 59) + (isTruncateAddress() ? 79 : 97)) * 59) + (isAllowData() ? 79 : 97)) * 59) + (isSignByHash() ? 79 : 97);
            String version = getVersion();
            int hashCode = (i * 59) + (version == null ? 43 : version.hashCode());
            String versionN = getVersionN();
            return (hashCode * 59) + (versionN != null ? versionN.hashCode() : 43);
        }

        public String toString() {
            return "LedgerTrx.AppConfiguration(allowContract=" + isAllowContract() + ", truncateAddress=" + isTruncateAddress() + ", allowData=" + isAllowData() + ", signByHash=" + isSignByHash() + ", version=" + getVersion() + ", versionN=" + getVersionN() + ")";
        }
    }

    public static class Address {
        private String address;
        private String publicKey;

        public String getAddress() {
            return this.address;
        }

        public String getPublicKey() {
            return this.publicKey;
        }

        public void setAddress(String str) {
            this.address = str;
        }

        public void setPublicKey(String str) {
            this.publicKey = str;
        }

        protected boolean canEqual(Object obj) {
            return obj instanceof Address;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Address) {
                Address address = (Address) obj;
                if (address.canEqual(this)) {
                    String address2 = getAddress();
                    String address3 = address.getAddress();
                    if (address2 != null ? address2.equals(address3) : address3 == null) {
                        String publicKey = getPublicKey();
                        String publicKey2 = address.getPublicKey();
                        return publicKey != null ? publicKey.equals(publicKey2) : publicKey2 == null;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            String address = getAddress();
            int hashCode = address == null ? 43 : address.hashCode();
            String publicKey = getPublicKey();
            return ((hashCode + 59) * 59) + (publicKey != null ? publicKey.hashCode() : 43);
        }

        public String toString() {
            return "LedgerTrx.Address(address=" + getAddress() + ", publicKey=" + getPublicKey() + ")";
        }

        public Address(String str, String str2) {
            this.address = str;
            this.publicKey = str2;
        }
    }

    public class Sender {
        private List<byte[]> data;
        private byte ins;
        private byte[] startBytes;

        public Sender(byte b, List<byte[]> list, byte[] bArr) {
            this.ins = b;
            this.data = list;
            this.startBytes = bArr;
        }

        public Observable<byte[]> send() {
            return Observable.fromArray(getIndexArray()).concatMap(new Function() {
                @Override
                public final Object apply(Object obj) {
                    ObservableSource lambda$send$0;
                    lambda$send$0 = LedgerTrx.Sender.this.lambda$send$0((Integer) obj);
                    return lambda$send$0;
                }
            });
        }

        public ObservableSource lambda$send$0(Integer num) throws Exception {
            byte b;
            Transport transport = transport;
            byte b2 = this.ins;
            byte[] bArr = this.startBytes;
            if (bArr != null) {
                b = bArr[num.intValue()];
            } else {
                b = num.intValue() == 0 ? (byte) 0 : Byte.MIN_VALUE;
            }
            return transport.send(LedgerTrx.CLA, b2, b, (byte) 0, this.data.get(num.intValue()));
        }

        private Integer[] getIndexArray() {
            Integer[] numArr = new Integer[this.data.size()];
            for (int i = 0; i < this.data.size(); i++) {
                numArr[i] = Integer.valueOf(i);
            }
            return numArr;
        }
    }
}
