package com.tron.wallet.business.migrate;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.FileProvider;
import com.alibaba.fastjson.JSONObject;
import com.tron.tron_base.frame.utils.FileUtils;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.migrate.Contract;
import com.tron.wallet.business.migrate.component.MigrateConfig;
import com.tron.wallet.business.tabdapp.jsbridge.dappconfirm.messagesign.DappLocalActivity;
import com.tron.wallet.common.bean.DataTransferOutput;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.bean.HdTronRelationshipBean;
import com.tron.wallet.db.dao.DaoUtils;
import com.tron.wallet.db.wallet.WalletUtils;
import com.tron.wallet.ledger.bleclient.RxSchedulers2;
import com.tronlinkpro.wallet.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
public class MigrateModel implements Contract.Model {
    private String selectedWalletName;

    @Override
    public int getCurrentPackageId() {
        return TextUtils.equals("com.tronlinkpro.wallet", MigrateConfig.APP_ID_GLOBAL) ? 10 : 11;
    }

    @Override
    public int checkTargetWalletApp(Context context) {
        try {
            int currentPackageId = getCurrentPackageId();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo("com.tronlinkpro.wallet", 1);
            PackageInfo packageInfo2 = packageManager.getPackageInfo(MigrateConfig.APP_ID_GLOBAL, 1);
            if (currentPackageId == 11) {
                if (packageInfo.versionCode < 2012221238 || packageInfo2.versionCode >= 2012221238) {
                    return (((long) packageInfo.versionCode) < 2012221230 || ((long) packageInfo2.versionCode) >= 2012221230) ? 0 : 2;
                }
                return 4;
            } else if (currentPackageId == 10) {
                if (packageInfo.versionCode < 2012221230) {
                    return 2;
                }
                return (((long) packageInfo2.versionCode) >= 2012221238 || ((long) packageInfo.versionCode) < 2012221238) ? 0 : 4;
            } else {
                return 0;
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return 1;
        }
    }

    @Override
    public Observable<Float> readMigrateData(final Context context, final Uri uri, final Uri uri2) {
        return Observable.create(new ObservableOnSubscribe() {
            @Override
            public final void subscribe(ObservableEmitter observableEmitter) {
                lambda$readMigrateData$1(context, uri, uri2, observableEmitter);
            }
        }).compose(RxSchedulers2.io_main()).doOnComplete(new Action() {
            @Override
            public final void run() {
                lambda$readMigrateData$2();
            }
        }).doOnError(new Consumer() {
            @Override
            public final void accept(Object obj) {
                lambda$readMigrateData$3(context, uri2, (Throwable) obj);
            }
        });
    }

    public void lambda$readMigrateData$1(Context context, Uri uri, Uri uri2, final ObservableEmitter observableEmitter) throws Exception {
        ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
        FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        final AtomicReference atomicReference = new AtomicReference(Float.valueOf(0.0f));
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        this.selectedWalletName = loadData(byteArrayOutputStream.toString(), context, new Consumer() {
            @Override
            public final void accept(Object obj) {
                MigrateModel.lambda$readMigrateData$0(ObservableEmitter.this, atomicReference, (Float) obj);
            }
        });
        byteArrayOutputStream.close();
        fileInputStream.close();
        openFileDescriptor.close();
        writeResult(context, ((Float) atomicReference.get()).floatValue() >= 1.0f ? 1 : 0, uri2);
        observableEmitter.onComplete();
    }

    public static void lambda$readMigrateData$0(ObservableEmitter observableEmitter, AtomicReference atomicReference, Float f) throws Exception {
        observableEmitter.onNext(f);
        atomicReference.set(f);
    }

    public void lambda$readMigrateData$2() throws Exception {
        if (TextUtils.isEmpty(this.selectedWalletName)) {
            return;
        }
        WalletUtils.setSelectedWallet(this.selectedWalletName);
    }

    public void lambda$readMigrateData$3(Context context, Uri uri, Throwable th) throws Exception {
        th.printStackTrace();
        writeResult(context, 0, uri);
        try {
            Set<String> allWallets = SpAPI.THIS.getAllWallets();
            if (allWallets.isEmpty()) {
                return;
            }
            for (String str : allWallets) {
                SpAPI.THIS.removeWallet(str);
                if (Build.VERSION.SDK_INT >= 24) {
                    context.deleteSharedPreferences(str);
                }
            }
            DaoUtils.getLightInstance().deleteAll(HdTronRelationshipBean.class);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private void writeResult(android.content.Context r3, int r4, android.net.Uri r5) {
        


return;

//throw new UnsupportedOperationException(
Method not decompiled: com.tron.wallet.business.migrate.MigrateModel.writeResult(android.content.Context, int, android.net.Uri):void");
    }

    @Override
    public Single<Pair<Uri, Uri>> writeMigrateData(final Context context) {
        return Single.create(new SingleOnSubscribe() {
            @Override
            public final void subscribe(SingleEmitter singleEmitter) {
                MigrateModel.lambda$writeMigrateData$4(context, singleEmitter);
            }
        }).compose(RxSchedulers2.io_main_single());
    }

    public static void lambda$writeMigrateData$4(Context context, SingleEmitter singleEmitter) throws Exception {
        File cacheDir = context.getCacheDir();
        File file = new File(cacheDir, MigrateConfig.CACHE_FILE);
        Set<String> walletNames = WalletUtils.getWalletNames();
        ArrayList arrayList = new ArrayList();
        for (String str : walletNames) {
            if (!TextUtils.isEmpty(str)) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
                DataTransferOutput.WalletData walletData = new DataTransferOutput.WalletData();
                walletData.is_watch_only_setup_key = sharedPreferences.getBoolean("is_watch_only_setup_key", false);
                walletData.isshield_key = sharedPreferences.getBoolean("isshield_key", false);
                walletData.backup_key = sharedPreferences.getBoolean("backup_key", false);
                walletData.wallet_name_key = sharedPreferences.getString(DappLocalActivity.WALLET_NAME_KEY, "");
                walletData.wallet_address_key = sharedPreferences.getString("wallet_address_key", "");
                walletData.shield__ob_key = sharedPreferences.getString("shield__ob_key", "");
                walletData.pub_key = sharedPreferences.getString("pub_key", "");
                walletData.wallet_keystore_key = sharedPreferences.getString("wallet_keystore_key", "");
                walletData.wallet_newmnemonic_key = sharedPreferences.getString("wallet_newmnemonic_key", "");
                walletData.wallet_mnemonicpath_key = sharedPreferences.getString("wallet_mnemonicpath_key", "");
                walletData.wallet_createtype_key = sharedPreferences.getInt("wallet_createtype_key", 0);
                walletData.mnemonic_length = sharedPreferences.getInt("mnemonic_length", 0);
                walletData.wallet_createtime_key = sharedPreferences.getLong("wallet_createtime_key", 0L);
                arrayList.add(walletData);
            }
        }
        List<HdTronRelationshipBean> QueryAll = DaoUtils.getLightInstance().QueryAll(HdTronRelationshipBean.class);
        DataTransferOutput dataTransferOutput = new DataTransferOutput();
        dataTransferOutput.dbData = QueryAll;
        dataTransferOutput.walletData = arrayList;
        dataTransferOutput.selectWalletName = SpAPI.THIS.getSelectedWallet();
        FileUtils.writeFile(GsonUtils.toGsonString(dataTransferOutput), file, false);
        Uri uriForFile = FileProvider.getUriForFile(context, MigrateConfig.PROVIDER_AUTH_PRO, file);
        File file2 = new File(cacheDir, MigrateConfig.RESULT_FILE);
        if (file2.exists()) {
            file2.delete();
        }
        file2.createNewFile();
        singleEmitter.onSuccess(new Pair(uriForFile, FileProvider.getUriForFile(context, MigrateConfig.PROVIDER_AUTH_PRO, file2)));
    }

    @Override
    public void installFromGoogle(Context context, int i) {
        installFromGoogle(context, i == 11 ? MigrateConfig.APP_ID_GLOBAL : "com.tronlinkpro.wallet");
    }

    @Override
    public void installFromGoogle(Context context, String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + str));
            intent.setPackage("com.android.vending");
            context.startActivity(intent);
        } catch (Exception e) {
            LogUtils.e(e);
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(String.format("https://play.google.com/store/apps/details?id=%s", str)));
                context.startActivity(intent2);
            } catch (Exception unused) {
                LogUtils.e(e);
            }
        }
    }

    @Override
    public void startMigrateGlobal(Context context, Uri uri, Uri uri2) {
        try {
            Intent intent = new Intent(MigrateConfig.ACTION_MIGRATE_TO_GLOBAL);
            intent.setPackage(MigrateConfig.APP_ID_GLOBAL);
            intent.addFlags(3);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(67108864);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setDataAndType(uri, "share/text");
            intent.setClipData(ClipData.newRawUri("", uri2));
            context.startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(context, R.anim.transition_in, R.anim.transition_out).toBundle());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public void startMigratePro(Context context) {
        try {
            Intent intent = new Intent(MigrateConfig.ACTION_MIGRATE_FROM_PRO);
            intent.setPackage("com.tronlinkpro.wallet");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(67108864);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setType("share/text");
            context.startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(context, R.anim.transition_in, R.anim.transition_out).toBundle());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    @Override
    public int getChannelPro(Context context) {
        try {
            return TextUtils.equals(context.getPackageManager().getPackageInfo("com.tronlinkpro.wallet", 128).applicationInfo.metaData.getString("CHANNEL"), "googleplay") ? 13 : 12;
        } catch (Exception e) {
            LogUtils.e(e);
            return 12;
        }
    }

    private String loadData(String str, Context context, Consumer<Float> consumer) throws Exception {
        DataTransferOutput dataTransferOutput = (DataTransferOutput) JSONObject.parseObject(str, DataTransferOutput.class);
        List<DataTransferOutput.WalletData> list = dataTransferOutput.walletData;
        String str2 = dataTransferOutput.selectWalletName;
        if (list != null && !list.isEmpty()) {
            int[] iArr = {0};
            Set<String> allWallets = SpAPI.THIS.getAllWallets();
            Set<String> allWalletsAddress = WalletUtils.getAllWalletsAddress();
            for (DataTransferOutput.WalletData walletData : list) {
                String str3 = walletData.wallet_name_key;
                String str4 = walletData.wallet_address_key;
                if (!allWallets.contains(str3) && !allWalletsAddress.contains(str4)) {
                    SharedPreferences.Editor edit = context.getSharedPreferences(str3, 0).edit();
                    edit.putBoolean("is_watch_only_setup_key", walletData.is_watch_only_setup_key);
                    edit.putBoolean("isshield_key", walletData.isshield_key);
                    edit.putBoolean("backup_key", walletData.backup_key);
                    edit.putString(DappLocalActivity.WALLET_NAME_KEY, walletData.wallet_name_key);
                    edit.putString("wallet_address_key", walletData.wallet_address_key);
                    edit.putString("shield__ob_key", walletData.shield__ob_key);
                    edit.putString("pub_key", walletData.pub_key);
                    edit.putString("wallet_keystore_key", walletData.wallet_keystore_key);
                    edit.putString("wallet_newmnemonic_key", walletData.wallet_newmnemonic_key);
                    edit.putString("wallet_mnemonicpath_key", walletData.wallet_mnemonicpath_key);
                    edit.putInt("wallet_createtype_key", walletData.wallet_createtype_key);
                    edit.putInt("mnemonic_length", walletData.mnemonic_length);
                    edit.putLong("wallet_createtime_key", walletData.wallet_createtime_key);
                    edit.commit();
                    SpAPI.THIS.setWallet(str3);
                    if (consumer != null) {
                        int i = iArr[0] + 1;
                        iArr[0] = i;
                        consumer.accept(Float.valueOf(i / list.size()));
                    }
                }
            }
        }
        if (dataTransferOutput.dbData != null && !dataTransferOutput.dbData.isEmpty()) {
            DaoUtils.getLightInstance().insertMultObject(dataTransferOutput.dbData);
        }
        return str2;
    }
}
