package com.tron.wallet.business.samsung;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.sdk.coldwallet.ScwDeepLink;
import com.samsung.android.sdk.coldwallet.ScwService;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.HdPathUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.ToastUtil;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class SamsungSDKWrapper {
    private static final String TAG = "SamsungSDKWrapper";

    public interface SignCallBack {
        void onFailure(String str);

        void onSignSuccess(byte[] bArr);
    }

    public interface getAddressCallBack {
        void onFailure(String str);

        void onSuccess(String str, String str2);
    }

    public interface verifySeedCallBack {
        void invalidSeed(String str);

        void validSeed(Map<String, String> map);
    }

    public static boolean isSupportSamsungSdk() {
        return ScwService.getInstance() != null;
    }

    public static boolean firstImportSamsungWallet(Context context, getAddressCallBack getaddresscallback) {
        if (!isSupportSamsungSdk() || keyStoreIsNeedUpdate(context)) {
            return false;
        }
        String checkSeedHashEmpty = checkSeedHashEmpty(context, true);
        if (TextUtils.isEmpty(checkSeedHashEmpty)) {
            return false;
        }
        getHdPathAddressMapping(getaddresscallback);
        SpAPI.THIS.setSamsung_SEED_HASH(checkSeedHashEmpty);
        return true;
    }

    public static boolean importSamsungWallet(Context context, getAddressCallBack getaddresscallback) {
        if (!isSupportSamsungSdk() || keyStoreIsNeedUpdate(context)) {
            return false;
        }
        String checkSeedHashEmpty = checkSeedHashEmpty(context, false);
        if (TextUtils.isEmpty(checkSeedHashEmpty)) {
            return false;
        }
        getAddressAndMappingHdPath(getaddresscallback, HdPathUtils.getHdPath(checkSeedHashEmpty));
        SpAPI.THIS.setSamsung_SEED_HASH(checkSeedHashEmpty);
        return true;
    }

    public static void getHdPathAddressMapping(getAddressCallBack getaddresscallback) {
        if (getHdpathAndAddressFromCache(getaddresscallback)) {
            return;
        }
        getAddressAndMappingHdPath(getaddresscallback, TronConfig.HD_PATH_DEFAULT);
    }

    private static void getAddressAndMappingHdPath(final getAddressCallBack getaddresscallback, final String str) {
        getAddress(new ScwService.ScwGetAddressListCallback() {
            @Override
            public void onSuccess(List<String> list) {
                if (list == null || list.size() <= 0) {
                    return;
                }
                String str2 = list.get(0);
                Map<String, String> samsung_HD_MAPPING = SpAPI.THIS.getSamsung_HD_MAPPING();
                if (samsung_HD_MAPPING == null) {
                    samsung_HD_MAPPING = new HashMap<>();
                }
                samsung_HD_MAPPING.put(str2, str);
                SpAPI.THIS.setSamsung_HD_MAPPING(samsung_HD_MAPPING);
                getaddresscallback.onSuccess(str, str2);
            }

            @Override
            public void onFailure(int i, String str2) {
                getaddresscallback.onFailure(str2);
                LogUtils.d(SamsungSDKWrapper.TAG, "get address onFailure:" + str2);
            }
        }, str);
    }

    private static boolean getHdpathAndAddressFromCache(getAddressCallBack getaddresscallback) {
        String str;
        Map<String, String> samsung_HD_MAPPING = SpAPI.THIS.getSamsung_HD_MAPPING();
        String str2 = null;
        if (samsung_HD_MAPPING == null || samsung_HD_MAPPING.keySet() == null || samsung_HD_MAPPING.keySet().iterator() == null || !samsung_HD_MAPPING.keySet().iterator().hasNext()) {
            str = null;
        } else {
            str = samsung_HD_MAPPING.keySet().iterator().next();
            if (!TextUtils.isEmpty(str)) {
                str2 = samsung_HD_MAPPING.get(str);
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        getaddresscallback.onSuccess(str2, str);
        return true;
    }

    public static boolean startVerifySeed(Context context, String str, verifySeedCallBack verifyseedcallback) {
        return (!isSupportSamsungSdk() || keyStoreIsNeedUpdate(context) || getSeedHashReturnIsNull(context, verifyseedcallback, str)) ? false : true;
    }

    public static void startSign(final Context context, final String str, final byte[] bArr, final SignCallBack signCallBack) {
        if (startVerifySeed(context, str, new verifySeedCallBack() {
            @Override
            public void validSeed(Map<String, String> map) {
                ScwService.getInstance().signTrxTransaction(new ScwService.ScwSignTrxTransactionCallback() {
                    @Override
                    public void onSuccess(byte[] bArr2) {
                        SignCallBack.this.onSignSuccess(bArr2);
                    }

                    @Override
                    public void onFailure(int i, String str2) {
                        LogUtils.d(SamsungSDKWrapper.TAG, "startVerifySeed error:" + str2);
                        SignCallBack.this.onFailure(str2);
                    }
                }, bArr, map.get(str));
            }

            @Override
            public void invalidSeed(String str2) {
                ToastUtil toastUtil = ToastUtil.getInstance();
                Context context2 = context;
                toastUtil.showToast(context2, context2.getString(R.string.keypair_not_match));
                SignCallBack.this.onFailure(null);
            }
        })) {
            return;
        }
        signCallBack.onFailure(null);
    }

    private static List<String> getAddressHdPath(Map<String, String> map) {
        if (map == null || map.keySet() == null || map.keySet().iterator() == null || !map.keySet().iterator().hasNext()) {
            return null;
        }
        String next = map.keySet().iterator().next();
        String next2 = map.keySet().iterator().next();
        if (TextUtils.isEmpty(next) || TextUtils.isEmpty(next2)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        arrayList.add(next2);
        return arrayList;
    }

    private static boolean getSeedHashReturnIsNull(Context context, verifySeedCallBack verifyseedcallback, String str) {
        String checkSeedHashEmpty = checkSeedHashEmpty(context, true);
        if (TextUtils.isEmpty(checkSeedHashEmpty)) {
            return true;
        }
        verifySeed(verifyseedcallback, str, checkSeedHashEmpty);
        return false;
    }

    public static String checkSeedHashEmpty(Context context, boolean z) {
        String str;
        try {
            str = ScwService.getInstance().getSeedHash();
        } catch (Exception e) {
            SentryUtil.captureException(e);
            str = "";
        }
        if (z && TextUtils.isEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(ScwDeepLink.MAIN));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
        return str;
    }

    public static void getoSamsungKeystoreWallet(Activity activity, int i) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(ScwDeepLink.MAIN));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, i);
    }

    private static void verifySeed(verifySeedCallBack verifyseedcallback, String str, String str2) {
        String samsung_SEED_HASH = SpAPI.THIS.getSamsung_SEED_HASH();
        Map<String, String> samsung_HD_MAPPING = SpAPI.THIS.getSamsung_HD_MAPPING();
        if (samsung_HD_MAPPING == null) {
            samsung_HD_MAPPING = new HashMap<>();
        }
        if (str2.equals(samsung_SEED_HASH)) {
            if (samsung_HD_MAPPING.get(str) != null) {
                verifyseedcallback.validSeed(samsung_HD_MAPPING);
            } else {
                verifyseedcallback.invalidSeed(str);
            }
            SpAPI.THIS.setSamsung_SEED_HASH(str2);
            return;
        }
        verifyAddressEquals(verifyseedcallback, str, str2, samsung_HD_MAPPING);
    }

    private static void verifyAddressEquals(final verifySeedCallBack verifyseedcallback, final String str, final String str2, Map<String, String> map) {
        getAddressAndMappingHdPath(new getAddressCallBack() {
            @Override
            public void onSuccess(String str3, String str4) {
                Map<String, String> samsung_HD_MAPPING = SpAPI.THIS.getSamsung_HD_MAPPING();
                if (str.equals(str4)) {
                    SpAPI.THIS.setSamsung_SEED_HASH(str2);
                    verifyseedcallback.validSeed(samsung_HD_MAPPING);
                    return;
                }
                verifyseedcallback.invalidSeed(str);
            }

            @Override
            public void onFailure(String str3) {
                verifyseedcallback.invalidSeed(str3);
            }
        }, (map == null || map.get(str) == null) ? TronConfig.HD_PATH_DEFAULT : map.get(str));
    }

    public static boolean keyStoreIsNeedUpdate(Context context) {
        int keystoreApiLevel = ScwService.getInstance().getKeystoreApiLevel();
        LogUtils.d(TAG, "keystoreApiLevel:" + keystoreApiLevel);
        if (keystoreApiLevel < 3) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(ScwDeepLink.GALAXY_STORE));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        }
        return false;
    }

    public static void getAddress(ScwService.ScwGetAddressListCallback scwGetAddressListCallback, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str);
        ScwService.getInstance().getAddressList(scwGetAddressListCallback, arrayList);
    }
}
