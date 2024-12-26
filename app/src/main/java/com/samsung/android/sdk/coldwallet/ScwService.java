package com.samsung.android.sdk.coldwallet;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.samsung.android.sdk.coldwallet.ICWServiceCallback;
import com.samsung.android.sdk.coldwallet.ICWWallet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ScwService {
    private static final int API_LEVEL_UNKNOWN = -1;
    private static final String BUILD_TYPE_RELEASE = "02";
    private static final Object LOCK = new Object();
    private static final String TAG = "ScwService";
    private static ScwService sInstance;
    private final String mApplicationId;
    private Context mContext;
    private int mApiLevelFromKeystore = -1;
    private final ScwServiceConnection mServiceConnection = new ScwServiceConnection();
    private final ExecutorService mServiceConnectExecutor = Executors.newSingleThreadExecutor();

    public static abstract class ScwCheckForMandatoryAppUpdateCallback {
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 == -8) {
                    ScwCheckForMandatoryAppUpdateCallback.this.onMandatoryAppUpdateNeeded(true);
                } else {
                    ScwCheckForMandatoryAppUpdateCallback.this.onMandatoryAppUpdateNeeded(false);
                }
            }
        };

        public abstract void onMandatoryAppUpdateNeeded(boolean z);
    }

    public static abstract class ScwGetAddressListCallback {
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwGetAddressListCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwGetAddressListCallback.this.onFailure(i3);
                    return;
                }
                ScwGetAddressListCallback.this.onSuccess(bundle2.getStringArrayList(Params.EXTRAS_KEY_RESULT_ADDRESS_LIST));
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(List<String> list);
    }

    public static abstract class ScwGetExtendedPublicKeyListCallback {
        private static final String TAG = "ScwGetExtendedPublicKeyListCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwGetExtendedPublicKeyListCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwGetExtendedPublicKeyListCallback.this.onFailure(i3);
                    return;
                }
                ArrayList<String> stringArrayList = bundle2.getStringArrayList(Params.EXTRAS_KEY_RESULT_EXTENDED_PUBLIC_KEY_LIST);
                if (stringArrayList == null) {
                    ScwGetExtendedPublicKeyListCallback.this.onFailure(-1, null);
                    ScwGetExtendedPublicKeyListCallback.this.onFailure(-1);
                    L.e(ScwGetExtendedPublicKeyListCallback.TAG, "Keystore returns null for list");
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<String> it = stringArrayList.iterator();
                while (it.hasNext()) {
                    arrayList.add(HexUtil.toBytes(it.next()));
                }
                ScwGetExtendedPublicKeyListCallback.this.onSuccess(arrayList);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(List<byte[]> list);
    }

    public static abstract class ScwSignBtcTransactionCallback {
        private static final String TAG = "ScwSignBtcTransactionCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignBtcTransactionCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwSignBtcTransactionCallback.this.onFailure(i3);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignBtcTransactionCallback.this.onFailure(-1, null);
                    ScwSignBtcTransactionCallback.this.onFailure(-1);
                    L.e(ScwSignBtcTransactionCallback.TAG, "Keystore returns null for signed tx");
                    return;
                }
                ScwSignBtcTransactionCallback.this.onSuccess(byteArray);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static abstract class ScwSignEthPersonalMessageCallback {
        private static final String TAG = "ScwSignEthPersonalMessageCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignEthPersonalMessageCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwSignEthPersonalMessageCallback.this.onFailure(i3);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignEthPersonalMessageCallback.this.onFailure(-1, null);
                    ScwSignEthPersonalMessageCallback.this.onFailure(-1);
                    L.e(ScwSignEthPersonalMessageCallback.TAG, "Keystore returns null for signedMessage");
                    return;
                }
                ScwSignEthPersonalMessageCallback.this.onSuccess(byteArray);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static abstract class ScwSignEthTransactionCallback {
        private static final String TAG = "ScwSignEthTransactionCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignEthTransactionCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwSignEthTransactionCallback.this.onFailure(i3);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignEthTransactionCallback.this.onFailure(-1, null);
                    ScwSignEthTransactionCallback.this.onFailure(-1);
                    L.e(ScwSignEthTransactionCallback.TAG, "Keystore returns null for signed tx");
                    return;
                }
                ScwSignEthTransactionCallback.this.onSuccess(byteArray);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static abstract class ScwSignKlayTransactionCallback {
        private static final String TAG = "ScwSignKlayTransactionCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignKlayTransactionCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwSignKlayTransactionCallback.this.onFailure(i3);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignKlayTransactionCallback.this.onFailure(-1, null);
                    ScwSignKlayTransactionCallback.this.onFailure(-1);
                    L.e(ScwSignKlayTransactionCallback.TAG, "Keystore returns null for signed tx");
                    return;
                }
                ScwSignKlayTransactionCallback.this.onSuccess(byteArray);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static abstract class ScwSignTrxPersonalMessageCallback {
        private static final String TAG = "ScwSignTrxPersonalMessageCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignTrxPersonalMessageCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwSignTrxPersonalMessageCallback.this.onFailure(i3);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignTrxPersonalMessageCallback.this.onFailure(-1, null);
                    ScwSignTrxPersonalMessageCallback.this.onFailure(-1);
                    L.e(ScwSignTrxPersonalMessageCallback.TAG, "Keystore returns null for signedMessage");
                    return;
                }
                ScwSignTrxPersonalMessageCallback.this.onSuccess(byteArray);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static abstract class ScwSignTrxTransactionCallback {
        private static final String TAG = "ScwSignTrxTransactionCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignTrxTransactionCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    ScwSignTrxTransactionCallback.this.onFailure(i3);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignTrxTransactionCallback.this.onFailure(-1, null);
                    ScwSignTrxTransactionCallback.this.onFailure(-1);
                    L.e(ScwSignTrxTransactionCallback.TAG, "Keystore returns null for signed tx");
                    return;
                }
                ScwSignTrxTransactionCallback.this.onSuccess(byteArray);
            }
        };

        public void onFailure(int i) {
        }

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static abstract class ScwSignXlmTransactionCallback {
        private static final String TAG = "ScwSignXlmTransactionCallback";
        final ICWServiceCallback.Stub mStub = new ICWServiceCallback.Stub() {
            @Override
            public void onResponse(int i, int i2, int i3, Bundle bundle, Bundle bundle2) {
                if (i3 < 0) {
                    ScwSignXlmTransactionCallback.this.onFailure(i3, bundle2 != null ? bundle2.getString(Params.EXTRAS_KEY_ERROR_MESSAGE) : null);
                    return;
                }
                byte[] byteArray = bundle2.getByteArray(Params.EXTRAS_KEY_RESULT_SIGNED_TRANSACTION);
                if (byteArray == null) {
                    ScwSignXlmTransactionCallback.this.onFailure(-1, null);
                    L.e(ScwSignXlmTransactionCallback.TAG, "Keystore returns null for signed tx");
                    return;
                }
                ScwSignXlmTransactionCallback.this.onSuccess(byteArray);
            }
        };

        public abstract void onFailure(int i, String str);

        public abstract void onSuccess(byte[] bArr);
    }

    public static ScwService getInstance() {
        return sInstance;
    }

    private ScwService(Context context, String str) {
        this.mContext = context;
        this.mApplicationId = str;
    }

    public static ScwService initializeApp(Context context, String str) {
        ScwService scwService;
        synchronized (LOCK) {
            if (sInstance == null) {
                L.d(TAG, "Service initialized");
                sInstance = new ScwService(context, str);
            }
            scwService = sInstance;
        }
        return scwService;
    }

    public int getKeystoreApiLevel() {
        Integer keystoreApiLevel = ScwProp.getKeystoreApiLevel(this.mContext);
        if (keystoreApiLevel == null) {
            return 0;
        }
        return keystoreApiLevel.intValue();
    }

    public String getSeedHash() {
        checkApiLevel(1);
        String seedHash = ScwProp.getSeedHash(this.mContext);
        String str = TAG;
        L.d(str, "ApiLevelFromService : " + this.mApiLevelFromKeystore);
        if (seedHash == null) {
            L.d(str, "Seed hash is not supported by the Keystore");
            return null;
        }
        if (seedHash.length() == 0) {
            L.d(str, "Seed hash is empty");
        }
        return seedHash;
    }

    public boolean isRootSeedBackedUp() {
        checkApiLevel(7);
        String str = TAG;
        L.d(str, "ApiLevelFromService : " + this.mApiLevelFromKeystore);
        Boolean isRootSeedBackedUp = ScwProp.isRootSeedBackedUp(this.mContext);
        L.d(str, "Backup root seed result : " + isRootSeedBackedUp);
        return isRootSeedBackedUp.booleanValue();
    }

    public int[] getSupportedCoins() {
        checkApiLevel(1);
        return ScwProp.getSupportedCoins(this.mContext);
    }

    public void checkForMandatoryAppUpdate(ScwCheckForMandatoryAppUpdateCallback scwCheckForMandatoryAppUpdateCallback) {
        checkApiLevel(1);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1010);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwCheckForMandatoryAppUpdateCallback.mStub);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void getExtendedPublicKeyList(ScwGetExtendedPublicKeyListCallback scwGetExtendedPublicKeyListCallback, ArrayList<String> arrayList) {
        checkApiLevel(1);
        Bundle bundle = new Bundle();
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putInt(Params.EXTRAS_OP_CODE, 1005);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwGetExtendedPublicKeyListCallback.mStub);
        bundle.putStringArrayList(Params.EXTRAS_KEY_HD_PATH_LIST, arrayList);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void getAddressList(ScwGetAddressListCallback scwGetAddressListCallback, ArrayList<String> arrayList) {
        checkApiLevel(1);
        Bundle bundle = new Bundle();
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putInt(Params.EXTRAS_OP_CODE, 1006);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwGetAddressListCallback.mStub);
        bundle.putStringArrayList(Params.EXTRAS_KEY_HD_PATH_LIST, arrayList);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public static String getHdPath(int i, int i2) {
        switch (i) {
            case ScwCoinType.ETH:
                return "m/44'/60'/0'/0/" + i2;
            case ScwCoinType.XLM:
                return "m/44'/148'/" + i2 + "'";
            case ScwCoinType.TRON:
                return "m/44'/195'/0'/0/" + i2;
            case ScwCoinType.KLAY:
                return "m/44'/8217'/0'/0/" + i2;
            default:
                return null;
        }
    }

    @Deprecated
    public void signEthTransaction(ScwSignEthTransactionCallback scwSignEthTransactionCallback, byte[] bArr, String str) {
        checkApiLevel(1);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1009);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignEthTransactionCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_HD_PATH, str);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signEthTransaction(ScwSignEthTransactionCallback scwSignEthTransactionCallback, byte[] bArr, String str, Long l) {
        checkApiLevel(11);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1009);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignEthTransactionCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_HD_PATH, str);
        if (l != null) {
            bundle.putLong(Params.EXTRAS_KEY_CHAIN_ID, l.longValue());
        }
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signEthPersonalMessage(ScwSignEthPersonalMessageCallback scwSignEthPersonalMessageCallback, byte[] bArr, String str) {
        checkApiLevel(1);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1011);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignEthPersonalMessageCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_HD_PATH, str);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signKlayTransaction(ScwSignKlayTransactionCallback scwSignKlayTransactionCallback, byte[] bArr, String str, int i) {
        checkApiLevel(2);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1012);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignKlayTransactionCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_HD_PATH, str);
        bundle.putInt(Params.EXTRAS_KEY_NETWORK_ID, i);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    @Deprecated
    public void signBtcTransaction(ScwSignBtcTransactionCallback scwSignBtcTransactionCallback, byte[] bArr, List<String> list, List<byte[]> list2, String str) {
        checkApiLevel(2);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1008);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignBtcTransactionCallback.mStub);
        ArrayList<String> arrayList = new ArrayList<>(list);
        if (str == null || str.isEmpty()) {
            str = list.get(0);
        }
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putStringArrayList(Params.EXTRAS_KEY_HD_PATH_LIST, arrayList);
        bundle.putString(Params.EXTRAS_KEY_CHANGE_HD_PATH, str);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signBtcTransaction(ScwSignBtcTransactionCallback scwSignBtcTransactionCallback, byte[] bArr, List<String> list, String str) {
        checkApiLevel(4);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1008);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignBtcTransactionCallback.mStub);
        ArrayList<String> arrayList = new ArrayList<>(list);
        if (str == null || str.isEmpty()) {
            str = list.get(0);
        }
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putStringArrayList(Params.EXTRAS_KEY_HD_PATH_LIST, arrayList);
        bundle.putString(Params.EXTRAS_KEY_CHANGE_HD_PATH, str);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signXlmTransaction(ScwSignXlmTransactionCallback scwSignXlmTransactionCallback, byte[] bArr, ArrayList<String> arrayList) {
        checkApiLevel(10);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1014);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignXlmTransactionCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putStringArrayList(Params.EXTRAS_KEY_HD_PATH_LIST, arrayList);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signTrxTransaction(ScwSignTrxTransactionCallback scwSignTrxTransactionCallback, byte[] bArr, String str) {
        checkApiLevel(3);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1013);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignTrxTransactionCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_HD_PATH, str);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void signTrxPersonalMessage(ScwSignTrxPersonalMessageCallback scwSignTrxPersonalMessageCallback, byte[] bArr, String str) {
        checkApiLevel(4);
        Bundle bundle = new Bundle();
        bundle.putInt(Params.EXTRAS_OP_CODE, 1015);
        bundle.putBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK, scwSignTrxPersonalMessageCallback.mStub);
        bundle.putString(Params.EXTRAS_KEY_APP_ID, this.mApplicationId);
        bundle.putString(Params.EXTRAS_KEY_APP_BUILD_TYPE, BUILD_TYPE_RELEASE);
        bundle.putByteArray(Params.EXTRAS_KEY_TRANSACTION, bArr);
        bundle.putString(Params.EXTRAS_KEY_HD_PATH, str);
        this.mServiceConnectExecutor.execute(new ProcessRunnable(bundle));
    }

    public void checkApiLevel(int i) {
        int keystoreApiLevel = getKeystoreApiLevel();
        this.mApiLevelFromKeystore = keystoreApiLevel;
        if (keystoreApiLevel < i) {
            throw new ScwApiLevelException(this.mApiLevelFromKeystore, i);
        }
    }

    public ICWWallet connect() {
        if (this.mServiceConnection.connected()) {
            L.d(TAG, "connect: Service already connected");
            return this.mServiceConnection.getStub();
        } else if (!this.mServiceConnection.bind()) {
            L.e(TAG, "connect: failed to bind service");
            return null;
        } else {
            return this.mServiceConnection.getStub();
        }
    }

    public class ScwServiceConnection implements ServiceConnection {
        private final Object lock;
        private volatile boolean mConnected;
        private volatile ICWWallet mStub;

        boolean connected() {
            return this.mConnected;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            this.mConnected = false;
        }

        private ScwServiceConnection() {
            this.lock = new Object();
        }

        ICWWallet getStub() {
            if (this.mConnected) {
                return this.mStub;
            }
            L.e(ScwService.TAG, "connect: not connected");
            return null;
        }

        @Override
        public void onBindingDied(ComponentName componentName) {
            String str = ScwService.TAG;
            L.v(str, "service: onBindingDied: " + componentName);
        }

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            L.v(ScwService.TAG, "service: onServiceConnected");
            this.mStub = ICWWallet.Stub.asInterface(iBinder);
            this.mConnected = true;
            synchronized (this.lock) {
                L.v(ScwService.TAG, "service: onServiceConnected notify all");
                this.lock.notifyAll();
            }
        }

        boolean bind() {
            Intent intent = new Intent();
            intent.setAction("com.samsung.android.sdk.coldwallet.ICWWallet");
            intent.setClassName("com.samsung.android.coldwalletservice", "com.samsung.android.coldwalletservice.core.CWWalletService");
            L.d(ScwService.TAG, "connect: bind service");
            mContext.bindService(intent, mServiceConnection, 1);
            L.v(ScwService.TAG, "connect: bind ongoing");
            if (!this.mConnected) {
                synchronized (this.lock) {
                    L.v(ScwService.TAG, "connect: wait binding");
                    try {
                        this.lock.wait(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            String str = ScwService.TAG;
            L.d(str, "connect: bind result : " + this.mConnected);
            return this.mConnected;
        }
    }

    private class ProcessRunnable implements Runnable {
        private Bundle mReqInfo;
        private Bundle mUserData;

        ProcessRunnable(Bundle bundle) {
            this.mReqInfo = bundle;
            this.mUserData = null;
        }

        ProcessRunnable(Bundle bundle, Bundle bundle2) {
            this.mReqInfo = bundle;
            this.mUserData = bundle2;
        }

        @Override
        public void run() {
            try {
                ICWWallet connect = connect();
                if (connect != null) {
                    checkApiLevel(1);
                    connect.process(BuildConfig.VERSION_CODE, this.mReqInfo, 0, this.mUserData);
                    L.d(ScwService.TAG, "processRunnable() finish process");
                    return;
                }
                L.e(ScwService.TAG, "processRunnable() stub is null");
                throw new IllegalStateException("service binding failed.");
            } catch (Exception e) {
                L.e(ScwService.TAG, e.getMessage());
                Bundle bundle = new Bundle();
                bundle.putString(Params.EXTRAS_KEY_ERROR_MESSAGE, e.getMessage());
                sendImmediateCallback(0, this.mReqInfo, this.mUserData, bundle);
            }
        }
    }

    public void sendImmediateCallback(int i, Bundle bundle, Bundle bundle2, Bundle bundle3) {
        ICWServiceCallback asInterface;
        L.e(TAG, String.format(Locale.ENGLISH, "ImmediateCallback op:%d errorCode:%d", Integer.valueOf(bundle.getInt(Params.EXTRAS_OP_CODE)), -1));
        IBinder binder = bundle.getBinder(Params.EXTRAS_KEY_SERVICE_CALLBACK);
        if (binder == null || (asInterface = ICWServiceCallback.Stub.asInterface(binder)) == null) {
            return;
        }
        try {
            asInterface.onResponse(-1, i, -1, bundle2, bundle3);
        } catch (RemoteException e) {
            L.e(TAG, e.toString());
        }
    }
}
