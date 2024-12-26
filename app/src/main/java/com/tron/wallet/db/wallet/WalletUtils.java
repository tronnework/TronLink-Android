package com.tron.wallet.db.wallet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.JSONLexer;
import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.tron.tron_base.frame.net.IRequest;
import com.tron.tron_base.frame.utils.AppContextUtil;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.tron_base.frame.utils.RxBus;
import com.tron.wallet.TronApplication;
import com.tron.wallet.business.pull.PullConstants;
import com.tron.wallet.common.components.qr.CodeUtils;
import com.tron.wallet.common.config.Event;
import com.tron.wallet.common.config.TronConfig;
import com.tron.wallet.common.utils.AppUtils;
import com.tron.wallet.common.utils.GsonUtils;
import com.tron.wallet.common.utils.SentryUtil;
import com.tron.wallet.common.utils.Sha256Hash;
import com.tron.wallet.common.utils.StringTronUtil;
import com.tron.wallet.common.utils.UIUtils;
import com.tron.wallet.db.SpAPI;
import com.tronlinkpro.wallet.R;
import j$.util.Collection;
import j$.util.Objects;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.text.Typography;
import org.apache.commons.lang3.CharUtils;
import org.bouncycastle.util.encoders.Hex;
import org.tron.api.GrpcAPI;
import org.tron.common.crypto.Hash;
import org.tron.common.crypto.SymmEncoder;
import org.tron.common.utils.ByteArray;
import org.tron.common.utils.JsonFormat;
import org.tron.net.CipherException;
import org.tron.net.KeyStoreUtils;
import org.tron.net.WalletStore;
import org.tron.protos.Protocol;
import org.tron.protos.contract.AccountContract;
import org.tron.protos.contract.AssetIssueContractOuterClass;
import org.tron.protos.contract.BalanceContract;
import org.tron.protos.contract.ExchangeContract;
import org.tron.protos.contract.MarketContract;
import org.tron.protos.contract.ProposalContract;
import org.tron.protos.contract.ShieldContract;
import org.tron.protos.contract.SmartContractOuterClass;
import org.tron.protos.contract.StorageContract;
import org.tron.protos.contract.VoteAssetContractOuterClass;
import org.tron.protos.contract.WitnessContract;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.I_TYPE;
import org.tron.walletserver.InvalidAddressException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.InvalidPasswordException;
import org.tron.walletserver.PermissionException;
import org.tron.walletserver.Wallet;
import org.tron.walletserver.WalletPath;
public class WalletUtils {
    private static GrpcAPI.AccountResourceMessage.Builder accountResMessage;
    private static SharedPreferences.Editor editor;
    private static SharedPreferences sharedPreferences;

    public static Bitmap strToQR(String str, int i, int i2, Bitmap bitmap) {
        try {
            LogUtils.i("WalletUtil", "strToQR... ");
            return new BarcodeEncoder().encodeBitmap(str, BarcodeFormat.QR_CODE, i, i2);
        } catch (WriterException e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return null;
        }
    }

    public static Bitmap strToQRDeleteWhite(String str, int i, int i2) {
        try {
            LogUtils.i("WalletUtil", "strToQRDeleteWhite... ");
            return new BarcodeEncoder().createBitmap(UIUtils.deleteWhite(new BarcodeEncoder().encode(str, BarcodeFormat.QR_CODE, i, i2)));
        } catch (WriterException e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
            return null;
        }
    }

    public static Bitmap strToQRHasLogo(String str, int i, int i2, Bitmap bitmap) {
        return CodeUtils.createQRCode(str, i, bitmap);
    }

    private static String getPrivateKey(String str, String str2) {
        byte[] AES128EcbDec;
        if (!StringTronUtil.isEmpty(str)) {
            byte[] decode = Hex.decode(str.getBytes());
            if (!StringTronUtil.isEmpty(str2) && (AES128EcbDec = SymmEncoder.AES128EcbDec(decode, StringTronUtil.getEncKey(str2))) != null && AES128EcbDec.length != 0) {
                return Hex.toHexString(AES128EcbDec);
            }
        }
        return null;
    }

    private static String getprivateKeyEncrypted(String str, String str2, String str3) {
        return ByteArray.toHexString(SymmEncoder.AES128EcbEnc(Hex.decode(str), StringTronUtil.getEncKey(str3)));
    }

    public static Wallet getWallet(String str, String str2) throws CipherException, IOException {
        String str3;
        String str4;
        String str5;
        Wallet wallet;
        String str6 = str;
        if (str6.contains("/")) {
            str6 = str6.replaceAll("/", "");
        }
        if (existWallet(str6)) {
            Context context = AppContextUtil.getContext();
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(str6, 0);
            String string = sharedPreferences2.getString(context.getString(R.string.wallet_keystore_key), "");
            if (StringTronUtil.isEmpty(str2)) {
                str3 = null;
                str4 = null;
                str5 = null;
            } else {
                str3 = TronApplication.WALlET_AES.get(str6);
                str4 = sharedPreferences2.getString(context.getString(R.string.priv_key), "");
                if (!StringTronUtil.isEmpty(str4)) {
                    str5 = getPrivateKey(str4, str2);
                } else if (!StringTronUtil.isEmpty(str3)) {
                    str5 = getPrivateKey(str3, str2);
                } else {
                    str5 = KeyStoreUtils.getPrivateWithKeyStore(string, str2);
                }
            }
            boolean z = sharedPreferences2.getBoolean(context.getString(R.string.isshield_key), false);
            boolean z2 = sharedPreferences2.getBoolean(context.getString(R.string.is_watch_only_setup_key), false);
            int i = sharedPreferences2.getInt(context.getString(R.string.wallet_createtype_key), 0);
            if (z) {
                wallet = new Wallet();
                wallet.setShieldedWallet(true);
            } else {
                wallet = new Wallet(I_TYPE.PRIVATE, str5);
            }
            if (!StringTronUtil.isEmpty(str2)) {
                if (wallet.getECKey() == null || wallet.getECKey().getPrivKeyBytes() == null) {
                    throw new CipherException("error password");
                }
                if (z && SpAPI.THIS.getWalletAddress(str6).startsWith(ExifInterface.GPS_DIRECTION_TRUE)) {
                    SpAPI.THIS.setWalletAddress(str6, wallet.getAddress());
                } else if (!SpAPI.THIS.getWalletAddress(str6).equals(wallet.getAddress())) {
                    throw new CipherException("error password");
                }
                if (StringTronUtil.isEmpty(str3)) {
                    TronApplication.WALlET_AES.put(str6, getprivateKeyEncrypted(str5, str6, str2));
                }
            }
            wallet.setKeyStore(string);
            wallet.setWatchOnly(z2);
            wallet.setWalletName(sharedPreferences2.getString(context.getString(R.string.wallet_name_key), str6));
            String string2 = sharedPreferences2.getString(context.getString(R.string.pwd_key), "");
            if (!StringTronUtil.isEmpty(string2)) {
                sharedPreferences2.edit().putString(context.getString(R.string.pwd_key), "").commit();
            }
            wallet.setEncryptedPassword(string2);
            wallet.setAddress(sharedPreferences2.getString(context.getString(R.string.wallet_address_key), ""));
            wallet.setBackUp(sharedPreferences2.getBoolean(context.getString(R.string.backup_key), false));
            wallet.setIconRes(sharedPreferences2.getString(context.getString(R.string.wallet_icon_key), ""));
            wallet.setCreateType(i);
            wallet.setColor(sharedPreferences2.getInt(context.getString(R.string.wallet_color_key), -1));
            wallet.setMnemonicLength(sharedPreferences2.getInt(context.getString(R.string.mnemonic_length), 0));
            wallet.setMnemonicPath(sharedPreferences2.getString(context.getString(R.string.wallet_mnemonicpath_key), ""));
            wallet.setEncryptedPrivateKey(null);
            wallet.setShieldedWallet(z);
            String string3 = sharedPreferences2.getString(context.getString(R.string.pub_key), "");
            if (!string3.isEmpty()) {
                wallet.setPublicKey(Hex.decode(string3.getBytes()));
            }
            long j = sharedPreferences2.getLong(context.getString(R.string.wallet_createtime_key), 0L);
            if (j == 0) {
                j = System.currentTimeMillis();
                sharedPreferences2.edit().putLong(context.getString(R.string.wallet_createtime_key), j).commit();
            }
            wallet.setCreateTime(j);
            if (!StringTronUtil.isEmpty(str4, str5)) {
                sharedPreferences2.edit().putString(context.getString(R.string.wallet_keystore_key), KeyStoreUtils.getKeyStoreWithPrivate(str2, wallet)).commit();
                sharedPreferences2.edit().putString(context.getString(R.string.priv_key), "").commit();
            }
            if (StringTronUtil.isEmpty(wallet.getAddress())) {
                return null;
            }
            return wallet;
        }
        return null;
    }

    public static void cleanWalletCache() {
        if (SpAPI.THIS.getResetWalletsPwdFlag()) {
            return;
        }
        Context context = AppContextUtil.getContext();
        Set<String> walletNames = getWalletNames();
        if (walletNames != null) {
            for (String str : walletNames) {
                if (!StringTronUtil.isEmpty(str)) {
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences(str, 0);
                    if (!StringTronUtil.isEmpty(sharedPreferences2.getString(context.getString(R.string.pwd_key), ""))) {
                        sharedPreferences2.edit().putString(context.getString(R.string.pwd_key), "").commit();
                    }
                }
            }
        }
        SpAPI.THIS.setResetWalletsPwdFlag(true);
    }

    public static Wallet getWallet(String str) {
        try {
            return getWallet(str, null);
        } catch (Exception e) {
            LogUtils.e(e);
            Object[] objArr = new Object[1];
            if (str == null) {
                str = "";
            }
            objArr[0] = str;
            SentryUtil.captureException(new Exception(String.format("Failed to get wallet with name : %s", objArr), e));
            return null;
        }
    }

    public static Wallet getWalletForAddress(String str) {
        for (String str2 : getWalletNames()) {
            Wallet wallet = getWallet(str2);
            if (wallet != null && str.equals(wallet.getAddress())) {
                return wallet;
            }
        }
        return null;
    }

    public static List<Wallet> getWalletsForAddress(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : getWalletNames()) {
            try {
                Wallet wallet = getWallet(str2);
                if (wallet != null && str.equals(wallet.getAddress())) {
                    arrayList.add(wallet);
                }
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return arrayList;
    }

    public static String mnemonic(String str, String str2) throws CipherException, IOException {
        if (StringTronUtil.isEmpty(str, str2)) {
            return null;
        }
        Context context = AppContextUtil.getContext();
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(str, 0);
        String string = sharedPreferences2.getString(context.getString(R.string.mnemonic_key), "");
        String string2 = sharedPreferences2.getString(context.getString(R.string.wallet_newmnemonic_key), "");
        if (!StringTronUtil.isEmpty(string)) {
            byte[] AESEcbDec = SymmEncoder.AESEcbDec(Hex.decode(string.getBytes()), StringTronUtil.getEncKey(str2));
            if (AESEcbDec == null || AESEcbDec.length == 0) {
                return null;
            }
            String str3 = new String(AESEcbDec);
            Wallet wallet = new Wallet(I_TYPE.MNEMONIC, str3);
            if (!StringTronUtil.isEmpty(str2) && wallet.getECKey() != null && !SpAPI.THIS.getWalletAddress(str).equals(wallet.getAddress())) {
                throw new CipherException("error password");
            }
            sharedPreferences2.edit().putString(context.getString(R.string.wallet_newmnemonic_key), KeyStoreUtils.getKeyStoreWithMnemonic(str2, str3, getWallet(str).getAddress())).commit();
            sharedPreferences2.edit().putString(context.getString(R.string.mnemonic_key), "").commit();
            return str3;
        } else if (StringTronUtil.isEmpty(string2)) {
            return null;
        } else {
            return KeyStoreUtils.getMnemonicWithKeyStore(string2, str2);
        }
    }

    public static boolean hasMnemonic(String str) {
        if (StringTronUtil.isEmpty(str)) {
            return false;
        }
        Context context = AppContextUtil.getContext();
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(str, 0);
        return (StringTronUtil.isEmpty(sharedPreferences2.getString(context.getString(R.string.mnemonic_key), "")) && StringTronUtil.isEmpty(sharedPreferences2.getString(context.getString(R.string.wallet_newmnemonic_key), ""))) ? false : true;
    }

    public static Wallet getSelectedWallet() {
        return getSelectedPublicWallet();
    }

    public static void setSelectedWallet(String str) {
        SpAPI.THIS.setSelectedWallet(str);
        RxBus.getInstance().post(Event.SELECTEDWALLET, str);
    }

    public static Wallet getSelectedPublicWallet() {
        Wallet wallet = getWallet(SpAPI.THIS.getSelectedWallet());
        if (wallet == null || wallet.isShieldedWallet()) {
            for (String str : SpAPI.THIS.getAllWallets()) {
                Wallet wallet2 = getWallet(str);
                if (wallet2 != null && !wallet2.isShieldedWallet()) {
                    SpAPI.THIS.setSelectedWallet(str);
                    return wallet2;
                }
            }
            return null;
        }
        return wallet;
    }

    public static boolean existWallet(String str) {
        return SpAPI.THIS.existWallet(str);
    }

    public static Set<String> getWalletNames() {
        return SpAPI.THIS.getAllWallets();
    }

    public static List<Wallet> getAllWallets() {
        Set<String> walletNames = getWalletNames();
        final ArrayList arrayList = new ArrayList();
        Collection.-EL.stream(walletNames).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletUtils.lambda$getAllWallets$0(arrayList, (String) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    public static void lambda$getAllWallets$0(List list, String str) {
        try {
            Wallet wallet = getWallet(str);
            if (wallet == null || wallet.isShieldedWallet()) {
                return;
            }
            list.add(wallet);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static Set<String> getAllWalletsAddress() {
        Set<String> walletNames = getWalletNames();
        final HashSet hashSet = new HashSet();
        Collection.-EL.stream(walletNames).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletUtils.lambda$getAllWalletsAddress$1(hashSet, (String) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return hashSet;
    }

    public static void lambda$getAllWalletsAddress$1(Set set, String str) {
        try {
            Wallet wallet = getWallet(str);
            if (wallet == null || wallet.isShieldedWallet()) {
                return;
            }
            set.add(wallet.getAddress());
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static Set<String> getPublicWalletNames() {
        Set<String> allWallets = SpAPI.THIS.getAllWallets();
        HashSet hashSet = new HashSet();
        for (String str : allWallets) {
            Wallet wallet = getWallet(str);
            if (wallet != null && !wallet.isShieldedWallet()) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    public static List<String> saveWallet(List<Wallet> list, final String str) {
        final ArrayList arrayList = new ArrayList();
        final HashMap hashMap = new HashMap();
        final AtomicReference atomicReference = new AtomicReference();
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletUtils.lambda$saveWallet$2(atomicReference, str, hashMap, (Wallet) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        Collection.-EL.stream(list).forEach(new Consumer() {
            @Override
            public final void accept(Object obj) {
                WalletUtils.lambda$saveWallet$3(hashMap, str, arrayList, (Wallet) obj);
            }

            public Consumer andThen(Consumer consumer) {
                return Objects.requireNonNull(consumer);
            }
        });
        return arrayList;
    }

    public static void lambda$saveWallet$2(AtomicReference atomicReference, String str, HashMap hashMap, Wallet wallet) {
        String keyStoreWithMnemonic;
        try {
            WalletStore walletStore = new WalletStore();
            walletStore.setWalletName(wallet.getWalletName());
            walletStore.setWalletAddress(wallet.getAddress());
            walletStore.setMnemonic(wallet.getMnemonic());
            if (!StringTronUtil.isEmpty(wallet.getMnemonic())) {
                if (atomicReference != null && atomicReference.get() != null && ((WalletStore) atomicReference.get()).getMnemonic().equals(wallet.getMnemonic())) {
                    keyStoreWithMnemonic = ((WalletStore) atomicReference.get()).getMnemonicEncrypted();
                } else {
                    keyStoreWithMnemonic = KeyStoreUtils.getKeyStoreWithMnemonic(str, wallet.getMnemonic(), wallet.getAddress());
                }
                walletStore.setMnemonicEncrypted(keyStoreWithMnemonic);
            }
            if (!wallet.isWatchOnly()) {
                walletStore.setPrivateKeyEncrypted(KeyStoreUtils.getKeyStoreWithPrivate(str, wallet));
            }
            atomicReference.set(walletStore);
            hashMap.put(wallet.getWalletName(), walletStore);
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    public static void lambda$saveWallet$3(HashMap hashMap, String str, List list, Wallet wallet) {
        try {
            WalletStore walletStore = (WalletStore) hashMap.get(wallet.getWalletName());
            if (walletStore != null) {
                saveWallet(wallet, walletStore.getPrivateKeyEncrypted(), walletStore.getMnemonicEncrypted(), str);
            } else {
                saveWallet(wallet, null, null, str);
            }
        } catch (Exception e) {
            LogUtils.e(e);
            if (wallet != null) {
                list.add(wallet.getAddress());
            }
        }
    }

    public static void saveWallet(Wallet wallet, String str) throws InvalidPasswordException, InvalidNameException, DuplicateNameException, CipherException, InvalidAddressException {
        saveWallet(wallet, null, null, str);
    }

    public static void saveWallet(Wallet wallet, String str, String str2, String str3) throws InvalidPasswordException, InvalidNameException, DuplicateNameException, CipherException, InvalidAddressException {
        if (!wallet.isWatchOnly() && (wallet.getECKey() == null || wallet.getECKey().getPrivKey() == null)) {
            throw new NullPointerException("Private Key is null");
        }
        if (!wallet.isWatchOnly() && StringTronUtil.isEmpty(str3)) {
            throw new InvalidPasswordException("");
        }
        if (!StringTronUtil.validataLegalString2(wallet.getWalletName())) {
            throw new InvalidNameException("");
        }
        if (StringTronUtil.isEmpty(wallet.getAddress())) {
            throw new InvalidAddressException("");
        }
        Set<String> allWalletsAddress = getAllWalletsAddress();
        if (allWalletsAddress != null && allWalletsAddress.contains(wallet.getAddress())) {
            throw new InvalidAddressException("");
        }
        Context context = AppContextUtil.getContext();
        SharedPreferences.Editor edit = context.getSharedPreferences(wallet.getWalletName(), 0).edit();
        edit.putBoolean(context.getString(R.string.is_watch_only_setup_key), wallet.isWatchOnly());
        edit.putBoolean(context.getString(R.string.isshield_key), wallet.isShieldedWallet());
        edit.putString(context.getString(R.string.wallet_name_key), wallet.getWalletName());
        edit.putString(context.getString(R.string.wallet_address_key), wallet.getAddress());
        edit.putString(context.getString(R.string.wallet_icon_key), wallet.getIconRes());
        edit.putInt(context.getString(R.string.wallet_createtype_key), wallet.getCreateType());
        edit.putLong(context.getString(R.string.wallet_createtime_key), wallet.getCreateTime());
        edit.putInt(context.getString(R.string.wallet_color_key), wallet.getColor());
        edit.putInt(context.getString(R.string.mnemonic_length), wallet.getMnemonicLength());
        if (!wallet.isWatchOnly()) {
            edit.putString(context.getString(R.string.pub_key), ByteArray.toHexString(wallet.getECKey().getPubKey()));
            edit.putBoolean(context.getString(R.string.backup_key), wallet.isBackUp());
            if (StringTronUtil.isEmpty(str)) {
                str = KeyStoreUtils.getKeyStoreWithPrivate(str3, wallet);
            }
            edit.putString(context.getString(R.string.wallet_keystore_key), str);
            if (!StringTronUtil.isEmpty(wallet.getMnemonic())) {
                if (StringTronUtil.isEmpty(str2)) {
                    str2 = KeyStoreUtils.getKeyStoreWithMnemonic(str3, wallet.getMnemonic(), wallet.getAddress());
                }
                edit.putString(context.getString(R.string.wallet_newmnemonic_key), str2);
                edit.putString(context.getString(R.string.wallet_mnemonicpath_key), wallet.getMnemonicPathString());
            }
        } else if (wallet.getCreateType() == 8) {
            edit.putString(context.getString(R.string.wallet_mnemonicpath_key), wallet.getMnemonicPathString());
        }
        edit.commit();
        SpAPI.THIS.setWallet(wallet.getWalletName());
        RxBus.getInstance().post(Event.SDK_FINISH_CREATE_WALLET, "");
    }

    public static void changePassword(Wallet wallet, String str, String str2) throws InvalidPasswordException, InvalidNameException, CipherException, IOException {
        if (!wallet.isWatchOnly() && (wallet.getECKey() == null || wallet.getECKey().getPrivKey() == null)) {
            throw new NullPointerException("Private Key is null");
        }
        if (!wallet.isWatchOnly() && !StringTronUtil.isOkPasswordTwo(str)) {
            throw new InvalidPasswordException("");
        }
        if (!StringTronUtil.validataLegalString2(wallet.getWalletName())) {
            throw new InvalidNameException("");
        }
        if (wallet.isWatchOnly()) {
            return;
        }
        Context context = AppContextUtil.getContext();
        SharedPreferences.Editor edit = context.getSharedPreferences(wallet.getWalletName(), 0).edit();
        edit.putBoolean(context.getString(R.string.is_watch_only_setup_key), wallet.isWatchOnly());
        edit.putBoolean(context.getString(R.string.isshield_key), wallet.isShieldedWallet());
        edit.putString(context.getString(R.string.wallet_name_key), wallet.getWalletName());
        edit.putString(context.getString(R.string.wallet_address_key), wallet.getAddress());
        edit.putString(context.getString(R.string.wallet_icon_key), wallet.getIconRes());
        edit.putInt(context.getString(R.string.wallet_createtype_key), wallet.getCreateType());
        edit.putLong(context.getString(R.string.wallet_createtime_key), wallet.getCreateTime());
        String hexString = ByteArray.toHexString(wallet.getECKey().getPubKey());
        String str3 = null;
        String mnemonic = hasMnemonic(wallet.getWalletName()) ? mnemonic(wallet.getWalletName(), str2) : null;
        edit.putString(context.getString(R.string.pub_key), hexString);
        edit.putString(context.getString(R.string.wallet_keystore_key), KeyStoreUtils.getKeyStoreWithPrivate(str, wallet));
        if (!StringTronUtil.isEmpty(mnemonic)) {
            try {
                str3 = KeyStoreUtils.getKeyStoreWithMnemonic(str, mnemonic, wallet.getAddress());
            } catch (CipherException e) {
                SentryUtil.captureException(e);
                LogUtils.e(e);
            }
            edit.putString(context.getString(R.string.wallet_newmnemonic_key), str3);
            edit.putString(context.getString(R.string.mnemonic_key), "");
            edit.putString(context.getString(R.string.wallet_mnemonicpath_key), wallet.getMnemonicPathString());
        }
        TronApplication.WALlET_AES.put(wallet.getWalletName(), "");
        edit.commit();
    }

    public static void changeWalletName(String str, String str2) throws DuplicateNameException {
        if (existWallet(str)) {
            Context context = AppContextUtil.getContext();
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(str, 0);
            SharedPreferences.Editor edit = context.getSharedPreferences(str2, 0).edit();
            boolean z = sharedPreferences2.getBoolean(context.getString(R.string.is_watch_only_setup_key), false);
            sharedPreferences2.getBoolean(context.getString(R.string.isshield_key), false);
            edit.putBoolean(context.getString(R.string.is_watch_only_setup_key), z);
            edit.putBoolean(context.getString(R.string.isshield_key), sharedPreferences2.getBoolean(context.getString(R.string.isshield_key), false));
            edit.putBoolean(context.getString(R.string.is_cold_wallet_key), sharedPreferences2.getBoolean(context.getString(R.string.is_cold_wallet_key), false));
            edit.putString(context.getString(R.string.wallet_name_key), str2);
            edit.putString(context.getString(R.string.wallet_address_key), sharedPreferences2.getString(context.getString(R.string.wallet_address_key), ""));
            edit.putString(context.getString(R.string.wallet_icon_key), sharedPreferences2.getString(context.getString(R.string.wallet_icon_key), ""));
            edit.putInt(context.getString(R.string.wallet_createtype_key), sharedPreferences2.getInt(context.getString(R.string.wallet_createtype_key), 0));
            edit.putLong(context.getString(R.string.wallet_createtime_key), sharedPreferences2.getLong(context.getString(R.string.wallet_createtime_key), 0L));
            edit.putStringSet(context.getString(R.string.set_trc10_key), sharedPreferences2.getStringSet(context.getString(R.string.set_trc10_key), new HashSet()));
            edit.putStringSet(context.getString(R.string.set_trc20_key), sharedPreferences2.getStringSet(context.getString(R.string.set_trc20_key), new HashSet()));
            edit.putBoolean(context.getString(R.string.set_alltrc10_key), sharedPreferences2.getBoolean(context.getString(R.string.set_alltrc10_key), true));
            edit.putBoolean(context.getString(R.string.set_alltrc20_key), sharedPreferences2.getBoolean(context.getString(R.string.set_alltrc20_key), true));
            edit.putBoolean(context.getString(R.string.set_hasaccount_key), sharedPreferences2.getBoolean(context.getString(R.string.set_hasaccount_key), false));
            if (!z) {
                edit.putString(context.getString(R.string.pwd_key), sharedPreferences2.getString(context.getString(R.string.pwd_key), ""));
                edit.putString(context.getString(R.string.pub_key), sharedPreferences2.getString(context.getString(R.string.pub_key), ""));
                edit.putString(context.getString(R.string.priv_key), sharedPreferences2.getString(context.getString(R.string.priv_key), ""));
                edit.putString(context.getString(R.string.mnemonic_key), sharedPreferences2.getString(context.getString(R.string.mnemonic_key), ""));
                edit.putBoolean(context.getString(R.string.backup_key), sharedPreferences2.getBoolean(context.getString(R.string.backup_key), false));
                edit.putString(context.getString(R.string.wallet_keystore_key), sharedPreferences2.getString(context.getString(R.string.wallet_keystore_key), ""));
                edit.putString(context.getString(R.string.wallet_newmnemonic_key), sharedPreferences2.getString(context.getString(R.string.wallet_newmnemonic_key), ""));
                TronApplication.WALlET_AES.put(str, "");
            }
            edit.putString(context.getString(R.string.wallet_mnemonicpath_key), sharedPreferences2.getString(context.getString(R.string.wallet_mnemonicpath_key), ""));
            edit.commit();
            SpAPI.THIS.setWallet(str2, str);
        }
    }

    public static void saveWatchOnly(Wallet wallet) throws InvalidNameException, DuplicateNameException, CipherException {
        wallet.setWatchOnly(true);
        try {
            saveWallet(wallet, (String) null);
        } catch (InvalidAddressException | InvalidPasswordException e) {
            SentryUtil.captureException(e);
            LogUtils.e(e);
        }
    }

    public static void updateWatchWalletType(String str, int i) {
        if (existWallet(str)) {
            Context context = AppContextUtil.getContext();
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(context.getString(R.string.wallet_createtype_key), i);
            edit.commit();
        }
    }

    public static synchronized void saveAccount(Context context, String str, Protocol.Account account) {
        synchronized (WalletUtils.class) {
            if (context != null && account != null) {
                if (existWallet(str)) {
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences(IRequest.getFileNaneWithChain(str), 0);
                    sharedPreferences = sharedPreferences2;
                    editor = sharedPreferences2.edit();
                    byte[] byteArray = account.toByteArray();
                    if (!ByteArray.isEmpty(byteArray)) {
                        editor.putString(context.getString(R.string.account_bytes_key), ByteArray.toHexString(byteArray));
                    }
                    editor.apply();
                }
            }
        }
    }

    public static void saveShieldAccount(Context context, String str, double d) {
        if (context == null || !existWallet(str)) {
            return;
        }
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(IRequest.getFileNaneWithChain(str), 0);
        sharedPreferences = sharedPreferences2;
        SharedPreferences.Editor edit = sharedPreferences2.edit();
        editor = edit;
        edit.putLong(context.getString(R.string.balance_key), (long) d);
        editor.apply();
    }

    public static synchronized void saveAccountRes(Context context, String str, GrpcAPI.AccountResourceMessage accountResourceMessage) {
        synchronized (WalletUtils.class) {
            if (context != null && accountResourceMessage != null) {
                if (existWallet(str)) {
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences(IRequest.getFileNaneWithChain(str), 0);
                    sharedPreferences = sharedPreferences2;
                    SharedPreferences.Editor edit = sharedPreferences2.edit();
                    edit.putLong(context.getString(R.string.net_limit_key), accountResourceMessage.getNetLimit());
                    edit.putLong(context.getString(R.string.net_used_key), accountResourceMessage.getNetUsed());
                    edit.putLong(context.getString(R.string.net_free_limit_key), accountResourceMessage.getFreeNetLimit());
                    edit.putLong(context.getString(R.string.net_free_used_key), accountResourceMessage.getFreeNetUsed());
                    edit.putLong(context.getString(R.string.energy_limit_key), accountResourceMessage.getEnergyLimit());
                    edit.putLong(context.getString(R.string.energy_used_key), accountResourceMessage.getEnergyUsed());
                    edit.putLong(context.getString(R.string.total_energy_limit_key), accountResourceMessage.getTotalEnergyLimit());
                    edit.putLong(context.getString(R.string.total_energy_weight_key), accountResourceMessage.getTotalEnergyWeight());
                    edit.putLong(context.getString(R.string.total_net_limit_key), accountResourceMessage.getTotalNetLimit());
                    edit.putLong(context.getString(R.string.total_net_weight_key), accountResourceMessage.getTotalNetWeight());
                    edit.apply();
                }
            }
        }
    }

    public static synchronized GrpcAPI.AccountResourceMessage getAccountRes(Context context, String str) {
        synchronized (WalletUtils.class) {
            if (context != null) {
                if (existWallet(str)) {
                    sharedPreferences = context.getSharedPreferences(IRequest.getFileNaneWithChain(str), 0);
                    GrpcAPI.AccountResourceMessage.Builder newBuilder = GrpcAPI.AccountResourceMessage.newBuilder();
                    accountResMessage = newBuilder;
                    newBuilder.setNetLimit(sharedPreferences.getLong(context.getString(R.string.net_limit_key), 0L));
                    accountResMessage.setNetUsed(sharedPreferences.getLong(context.getString(R.string.net_used_key), 0L));
                    accountResMessage.setFreeNetLimit(sharedPreferences.getLong(context.getString(R.string.net_free_limit_key), 0L));
                    accountResMessage.setFreeNetUsed(sharedPreferences.getLong(context.getString(R.string.net_free_used_key), 0L));
                    accountResMessage.setEnergyLimit(sharedPreferences.getLong(context.getString(R.string.energy_limit_key), 0L));
                    accountResMessage.setEnergyUsed(sharedPreferences.getLong(context.getString(R.string.energy_used_key), 0L));
                    accountResMessage.setTotalEnergyLimit(sharedPreferences.getLong(context.getString(R.string.total_energy_limit_key), 0L));
                    accountResMessage.setTotalEnergyWeight(sharedPreferences.getLong(context.getString(R.string.total_energy_weight_key), 0L));
                    accountResMessage.setTotalNetLimit(sharedPreferences.getLong(context.getString(R.string.total_net_limit_key), 0L));
                    accountResMessage.setTotalNetWeight(sharedPreferences.getLong(context.getString(R.string.total_net_weight_key), 0L));
                    return accountResMessage.build();
                }
            }
            return GrpcAPI.AccountResourceMessage.getDefaultInstance();
        }
    }

    public static synchronized Protocol.Account getAccount(Context context, String str) {
        synchronized (WalletUtils.class) {
            if (context != null) {
                if (existWallet(str)) {
                    SharedPreferences sharedPreferences2 = context.getSharedPreferences(IRequest.getFileNaneWithChain(str), 0);
                    sharedPreferences = sharedPreferences2;
                    String string = sharedPreferences2.getString(context.getString(R.string.account_bytes_key), "");
                    if (!StringTronUtil.isEmpty(string)) {
                        try {
                            return Protocol.Account.parseFrom(ByteArray.fromHexString(string));
                        } catch (Exception e) {
                            LogUtils.e(e);
                            return Protocol.Account.getDefaultInstance();
                        }
                    }
                    return Protocol.Account.getDefaultInstance();
                }
            }
            return Protocol.Account.getDefaultInstance();
        }
    }

    public static Protocol.Transaction packTransaction(String str) {
        char c;
        Any pack;
        try {
            JSONObject parseObject = JSONObject.parseObject(str);
            JSONObject jSONObject = parseObject.getJSONObject("raw_data");
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = jSONObject.getJSONArray("contract");
            for (int i = 0; i < jSONArray2.size(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("parameter");
                    String string = jSONObject2.getString("type");
                    switch (string.hashCode()) {
                        case -1705044092:
                            if (string.equals("WithdrawBalanceContract")) {
                                c = '\f';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1656305420:
                            if (string.equals("MarketSellAssetContract")) {
                                c = ' ';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1632790850:
                            if (string.equals("UnDelegateResourceContract")) {
                                c = Typography.amp;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1520674133:
                            if (string.equals("WithdrawExpireUnfreezeContract")) {
                                c = '$';
                                break;
                            }
                            c = 65535;
                            break;
                        case -1485407205:
                            if (string.equals("AssetIssueContract")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case -1048760864:
                            if (string.equals("ProposalCreateContract")) {
                                c = 16;
                                break;
                            }
                            c = 65535;
                            break;
                        case -845990611:
                            if (string.equals("ClearABIContract")) {
                                c = 29;
                                break;
                            }
                            c = 65535;
                            break;
                        case -703089577:
                            if (string.equals("FreezeBalanceContract")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case -651921570:
                            if (string.equals("UnfreezeBalanceContract")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        case -611256278:
                            if (string.equals("MarketCancelOrderContract")) {
                                c = '!';
                                break;
                            }
                            c = 65535;
                            break;
                        case -544448037:
                            if (string.equals("SmartContract")) {
                                c = 15;
                                break;
                            }
                            c = 65535;
                            break;
                        case -492394392:
                            if (string.equals("AccountUpdateContract")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case -453939044:
                            if (string.equals("UpdateEnergyLimitContract")) {
                                c = 27;
                                break;
                            }
                            c = 65535;
                            break;
                        case -439997029:
                            if (string.equals("AccountCreateContract")) {
                                c = 0;
                                break;
                            }
                            c = 65535;
                            break;
                        case -243778867:
                            if (string.equals("ExchangeTransactionContract")) {
                                c = 25;
                                break;
                            }
                            c = 65535;
                            break;
                        case -219262664:
                            if (string.equals("SetAccountIdContract")) {
                                c = 19;
                                break;
                            }
                            c = 65535;
                            break;
                        case -82956877:
                            if (string.equals("FreezeBalanceV2Contract")) {
                                c = Typography.quote;
                                break;
                            }
                            c = 65535;
                            break;
                        case -2225434:
                            if (string.equals("ExchangeInjectContract")) {
                                c = 24;
                                break;
                            }
                            c = 65535;
                            break;
                        case 16441433:
                            if (string.equals("ParticipateAssetIssueContract")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case 39138117:
                            if (string.equals("CancelAllUnfreezeV2Contract")) {
                                c = '\'';
                                break;
                            }
                            c = 65535;
                            break;
                        case 180125197:
                            if (string.equals("ProposalApproveContract")) {
                                c = 17;
                                break;
                            }
                            c = 65535;
                            break;
                        case 270660495:
                            if (string.equals("ProposalDeleteContract")) {
                                c = 18;
                                break;
                            }
                            c = 65535;
                            break;
                        case 336992568:
                            if (string.equals("VoteAssetContract")) {
                                c = 3;
                                break;
                            }
                            c = 65535;
                            break;
                        case 611663823:
                            if (string.equals("UpdateBrokerageContract")) {
                                c = 30;
                                break;
                            }
                            c = 65535;
                            break;
                        case 706457047:
                            if (string.equals("TransferAssetContract")) {
                                c = 2;
                                break;
                            }
                            c = 65535;
                            break;
                        case 710366781:
                            if (string.equals("TransferContract")) {
                                c = 1;
                                break;
                            }
                            c = 65535;
                            break;
                        case 762691543:
                            if (string.equals("AccountPermissionUpdateContract")) {
                                c = 28;
                                break;
                            }
                            c = 65535;
                            break;
                        case 885253893:
                            if (string.equals("DelegateResourceContract")) {
                                c = '%';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1147615065:
                            if (string.equals("UpdateSettingContract")) {
                                c = 22;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1190060069:
                            if (string.equals("ShieldedTransferContract")) {
                                c = 31;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1252602738:
                            if (string.equals("UnfreezeAssetContract")) {
                                c = CharUtils.CR;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1286958708:
                            if (string.equals("WitnessUpdateContract")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1339356071:
                            if (string.equals("WitnessCreateContract")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1392453279:
                            if (string.equals("ExchangeWithdrawContract")) {
                                c = JSONLexer.EOI;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1421429571:
                            if (string.equals("TriggerSmartContract")) {
                                c = 20;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1532035647:
                            if (string.equals("CreateSmartContract")) {
                                c = 21;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1699052801:
                            if (string.equals("VoteWitnessContract")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1844857594:
                            if (string.equals("UnfreezeBalanceV2Contract")) {
                                c = '#';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1892384185:
                            if (string.equals("UpdateAssetContract")) {
                                c = 14;
                                break;
                            }
                            c = 65535;
                            break;
                        case 2106222417:
                            if (string.equals("ExchangeCreateContract")) {
                                c = 23;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            c = 65535;
                            break;
                    }
                    switch (c) {
                        case 0:
                            AccountContract.AccountCreateContract.Builder newBuilder = AccountContract.AccountCreateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder);
                            pack = Any.pack(newBuilder.build());
                            break;
                        case 1:
                            BalanceContract.TransferContract.Builder newBuilder2 = BalanceContract.TransferContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder2);
                            pack = Any.pack(newBuilder2.build());
                            break;
                        case 2:
                            AssetIssueContractOuterClass.TransferAssetContract.Builder newBuilder3 = AssetIssueContractOuterClass.TransferAssetContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder3);
                            pack = Any.pack(newBuilder3.build());
                            break;
                        case 3:
                            VoteAssetContractOuterClass.VoteAssetContract.Builder newBuilder4 = VoteAssetContractOuterClass.VoteAssetContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder4);
                            pack = Any.pack(newBuilder4.build());
                            break;
                        case 4:
                            WitnessContract.VoteWitnessContract.Builder newBuilder5 = WitnessContract.VoteWitnessContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder5);
                            pack = Any.pack(newBuilder5.build());
                            break;
                        case 5:
                            WitnessContract.WitnessCreateContract.Builder newBuilder6 = WitnessContract.WitnessCreateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder6);
                            pack = Any.pack(newBuilder6.build());
                            break;
                        case 6:
                            AssetIssueContractOuterClass.AssetIssueContract.Builder newBuilder7 = AssetIssueContractOuterClass.AssetIssueContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder7);
                            pack = Any.pack(newBuilder7.build());
                            break;
                        case 7:
                            WitnessContract.WitnessUpdateContract.Builder newBuilder8 = WitnessContract.WitnessUpdateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder8);
                            pack = Any.pack(newBuilder8.build());
                            break;
                        case '\b':
                            AssetIssueContractOuterClass.ParticipateAssetIssueContract.Builder newBuilder9 = AssetIssueContractOuterClass.ParticipateAssetIssueContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder9);
                            pack = Any.pack(newBuilder9.build());
                            break;
                        case '\t':
                            AccountContract.AccountUpdateContract.Builder newBuilder10 = AccountContract.AccountUpdateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder10);
                            pack = Any.pack(newBuilder10.build());
                            break;
                        case '\n':
                            BalanceContract.FreezeBalanceContract.Builder newBuilder11 = BalanceContract.FreezeBalanceContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder11);
                            pack = Any.pack(newBuilder11.build());
                            break;
                        case 11:
                            BalanceContract.UnfreezeBalanceContract.Builder newBuilder12 = BalanceContract.UnfreezeBalanceContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder12);
                            pack = Any.pack(newBuilder12.build());
                            break;
                        case '\f':
                            BalanceContract.WithdrawBalanceContract.Builder newBuilder13 = BalanceContract.WithdrawBalanceContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder13);
                            pack = Any.pack(newBuilder13.build());
                            break;
                        case '\r':
                            AssetIssueContractOuterClass.UnfreezeAssetContract.Builder newBuilder14 = AssetIssueContractOuterClass.UnfreezeAssetContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder14);
                            pack = Any.pack(newBuilder14.build());
                            break;
                        case 14:
                            AssetIssueContractOuterClass.UpdateAssetContract.Builder newBuilder15 = AssetIssueContractOuterClass.UpdateAssetContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder15);
                            pack = Any.pack(newBuilder15.build());
                            break;
                        case 15:
                            SmartContractOuterClass.SmartContract.Builder newBuilder16 = SmartContractOuterClass.SmartContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder16);
                            pack = Any.pack(newBuilder16.build());
                            break;
                        case 16:
                            ProposalContract.ProposalCreateContract.Builder newBuilder17 = ProposalContract.ProposalCreateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder17);
                            pack = Any.pack(newBuilder17.build());
                            break;
                        case 17:
                            ProposalContract.ProposalApproveContract.Builder newBuilder18 = ProposalContract.ProposalApproveContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder18);
                            pack = Any.pack(newBuilder18.build());
                            break;
                        case 18:
                            ProposalContract.ProposalDeleteContract.Builder newBuilder19 = ProposalContract.ProposalDeleteContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder19);
                            pack = Any.pack(newBuilder19.build());
                            break;
                        case 19:
                            AccountContract.SetAccountIdContract.Builder newBuilder20 = AccountContract.SetAccountIdContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder20);
                            pack = Any.pack(newBuilder20.build());
                            break;
                        case 20:
                            SmartContractOuterClass.TriggerSmartContract.Builder newBuilder21 = SmartContractOuterClass.TriggerSmartContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder21);
                            pack = Any.pack(newBuilder21.build());
                            break;
                        case 21:
                            SmartContractOuterClass.CreateSmartContract.Builder newBuilder22 = SmartContractOuterClass.CreateSmartContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder22);
                            pack = Any.pack(newBuilder22.build());
                            break;
                        case 22:
                            SmartContractOuterClass.UpdateSettingContract.Builder newBuilder23 = SmartContractOuterClass.UpdateSettingContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder23);
                            pack = Any.pack(newBuilder23.build());
                            break;
                        case 23:
                            ExchangeContract.ExchangeCreateContract.Builder newBuilder24 = ExchangeContract.ExchangeCreateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder24);
                            pack = Any.pack(newBuilder24.build());
                            break;
                        case 24:
                            ExchangeContract.ExchangeInjectContract.Builder newBuilder25 = ExchangeContract.ExchangeInjectContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder25);
                            pack = Any.pack(newBuilder25.build());
                            break;
                        case 25:
                            ExchangeContract.ExchangeTransactionContract.Builder newBuilder26 = ExchangeContract.ExchangeTransactionContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder26);
                            pack = Any.pack(newBuilder26.build());
                            break;
                        case 26:
                            ExchangeContract.ExchangeWithdrawContract.Builder newBuilder27 = ExchangeContract.ExchangeWithdrawContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder27);
                            pack = Any.pack(newBuilder27.build());
                            break;
                        case 27:
                            SmartContractOuterClass.UpdateEnergyLimitContract.Builder newBuilder28 = SmartContractOuterClass.UpdateEnergyLimitContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder28);
                            pack = Any.pack(newBuilder28.build());
                            break;
                        case 28:
                            AccountContract.AccountPermissionUpdateContract.Builder newBuilder29 = AccountContract.AccountPermissionUpdateContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder29);
                            pack = Any.pack(newBuilder29.build());
                            break;
                        case 29:
                            SmartContractOuterClass.ClearABIContract.Builder newBuilder30 = SmartContractOuterClass.ClearABIContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder30);
                            pack = Any.pack(newBuilder30.build());
                            break;
                        case 30:
                            StorageContract.UpdateBrokerageContract.Builder newBuilder31 = StorageContract.UpdateBrokerageContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder31);
                            pack = Any.pack(newBuilder31.build());
                            break;
                        case 31:
                            ShieldContract.ShieldedTransferContract.Builder newBuilder32 = ShieldContract.ShieldedTransferContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder32);
                            pack = Any.pack(newBuilder32.build());
                            break;
                        case ' ':
                            MarketContract.MarketSellAssetContract.Builder newBuilder33 = MarketContract.MarketSellAssetContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder33);
                            pack = Any.pack(newBuilder33.build());
                            break;
                        case '!':
                            MarketContract.MarketCancelOrderContract.Builder newBuilder34 = MarketContract.MarketCancelOrderContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder34);
                            pack = Any.pack(newBuilder34.build());
                            break;
                        case '\"':
                            BalanceContract.FreezeBalanceV2Contract.Builder newBuilder35 = BalanceContract.FreezeBalanceV2Contract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder35);
                            pack = Any.pack(newBuilder35.build());
                            break;
                        case '#':
                            BalanceContract.UnfreezeBalanceV2Contract.Builder newBuilder36 = BalanceContract.UnfreezeBalanceV2Contract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder36);
                            pack = Any.pack(newBuilder36.build());
                            break;
                        case '$':
                            BalanceContract.WithdrawExpireUnfreezeContract.Builder newBuilder37 = BalanceContract.WithdrawExpireUnfreezeContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder37);
                            pack = Any.pack(newBuilder37.build());
                            break;
                        case '%':
                            BalanceContract.DelegateResourceContract.Builder newBuilder38 = BalanceContract.DelegateResourceContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder38);
                            pack = Any.pack(newBuilder38.build());
                            break;
                        case '&':
                            BalanceContract.UnDelegateResourceContract.Builder newBuilder39 = BalanceContract.UnDelegateResourceContract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder39);
                            pack = Any.pack(newBuilder39.build());
                            break;
                        case '\'':
                            BalanceContract.CancelAllUnfreezeV2Contract.Builder newBuilder40 = BalanceContract.CancelAllUnfreezeV2Contract.newBuilder();
                            JsonFormat.merge(jSONObject3.getJSONObject("value").toJSONString(), newBuilder40);
                            pack = Any.pack(newBuilder40.build());
                            break;
                        default:
                            pack = null;
                            break;
                    }
                    if (pack != null) {
                        jSONObject3.put("value", ByteArray.toHexString(pack.getValue().toByteArray()));
                        jSONObject2.put("parameter", (Object) jSONObject3);
                        jSONArray.add(jSONObject2);
                    }
                } catch (JsonFormat.ParseException e) {
                    AppUtils.randomReportSentry(e);
                    LogUtils.e(e);
                } catch (IOException e2) {
                    AppUtils.randomReportSentry(e2);
                    LogUtils.e(e2);
                }
            }
            jSONObject.put("contract", (Object) jSONArray);
            parseObject.put("raw_data", (Object) jSONObject);
            Protocol.Transaction.Builder newBuilder41 = Protocol.Transaction.newBuilder();
            try {
                JsonFormat.merge(parseObject.toJSONString(), newBuilder41);
                return newBuilder41.build();
            } catch (Exception e3) {
                AppUtils.randomReportSentry(e3);
                return null;
            }
        } catch (JSONException unused) {
            return null;
        } catch (Exception e4) {
            AppUtils.randomReportSentry(e4);
            return null;
        }
    }

    public static Protocol.Transaction packTransaction_sign(String str) {
        Protocol.Transaction.Builder newBuilder = Protocol.Transaction.newBuilder();
        try {
            JsonFormat.merge(str, newBuilder);
            return newBuilder.build();
        } catch (Exception e) {
            SentryUtil.captureException(e);
            return null;
        }
    }

    public static String printTransaction(Protocol.Transaction transaction) {
        return printTransactionToJSONs(transaction).toJSONString();
    }

    public static JSONObject printTransactionToJSONs(Protocol.Transaction transaction) {
        JSONObject parseObject = JSONObject.parseObject(JsonFormat.printToString(transaction));
        JSONArray jSONArray = new JSONArray();
        for (Protocol.Transaction.Contract contract : transaction.getRawData().getContractList()) {
            try {
                JSONObject printContract = printContract(transaction, contract, parseObject);
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("value", (Object) printContract);
                jSONObject.put("type_url", (Object) contract.getParameterOrBuilder().getTypeUrl());
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("parameter", (Object) jSONObject);
                jSONObject2.put("type", (Object) contract.getType());
                jSONObject2.put("Permission_id", (Object) Integer.valueOf(contract.getPermissionId()));
                jSONArray.add(jSONObject2);
            } catch (Exception e) {
                SentryUtil.captureException(e);
            }
        }
        JSONObject parseObject2 = JSONObject.parseObject(parseObject.get("raw_data").toString());
        parseObject2.put("contract", (Object) jSONArray);
        parseObject.put("raw_data", (Object) parseObject2);
        parseObject.put("txID", ByteArray.toHexString(Sha256Hash.hash(transaction.getRawData().toByteArray())));
        return parseObject;
    }

    public static String printContract(Protocol.Transaction transaction) {
        return (transaction == null || transaction.getRawData() == null || transaction.getRawData().getContractCount() < 1 || transaction.getRawData().getContract(0) == null) ? "" : printContract(transaction, transaction.getRawData().getContract(0), null).toString();
    }

    public static class fun1 {
        static final int[] $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType;

        static {
            int[] iArr = new int[Protocol.Transaction.Contract.ContractType.values().length];
            $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType = iArr;
            try {
                iArr[Protocol.Transaction.Contract.ContractType.AccountCreateContract.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferContract.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TransferAssetContract.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.VoteAssetContract.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.VoteWitnessContract.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WitnessCreateContract.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AssetIssueContract.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WitnessUpdateContract.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ParticipateAssetIssueContract.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AccountUpdateContract.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.FreezeBalanceContract.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceContract.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeAssetContract.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawBalanceContract.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateAssetContract.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalCreateContract.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalApproveContract.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ProposalDeleteContract.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.SetAccountIdContract.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CreateSmartContract.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.TriggerSmartContract.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateSettingContract.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeCreateContract.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeInjectContract.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeWithdrawContract.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ExchangeTransactionContract.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateEnergyLimitContract.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.AccountPermissionUpdateContract.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ClearABIContract.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.ShieldedTransferContract.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UpdateBrokerageContract.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.MarketSellAssetContract.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.MarketCancelOrderContract.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.FreezeBalanceV2Contract.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnfreezeBalanceV2Contract.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.WithdrawExpireUnfreezeContract.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.DelegateResourceContract.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.UnDelegateResourceContract.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[Protocol.Transaction.Contract.ContractType.CancelAllUnfreezeV2Contract.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
        }
    }

    private static JSONObject printContract(Protocol.Transaction transaction, Protocol.Transaction.Contract contract, JSONObject jSONObject) {
        try {
            Any parameter = contract.getParameter();
            switch (fun1.$SwitchMap$org$tron$protos$Protocol$Transaction$Contract$ContractType[contract.getType().ordinal()]) {
                case 1:
                    return JSONObject.parseObject(JsonFormat.printToString((AccountContract.AccountCreateContract) parameter.unpack(AccountContract.AccountCreateContract.class)));
                case 2:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.TransferContract) parameter.unpack(BalanceContract.TransferContract.class)));
                case 3:
                    return JSONObject.parseObject(JsonFormat.printToString((AssetIssueContractOuterClass.TransferAssetContract) parameter.unpack(AssetIssueContractOuterClass.TransferAssetContract.class)));
                case 4:
                    return JSONObject.parseObject(JsonFormat.printToString((VoteAssetContractOuterClass.VoteAssetContract) parameter.unpack(VoteAssetContractOuterClass.VoteAssetContract.class)));
                case 5:
                    return JSONObject.parseObject(JsonFormat.printToString((WitnessContract.VoteWitnessContract) parameter.unpack(WitnessContract.VoteWitnessContract.class)));
                case 6:
                    return JSONObject.parseObject(JsonFormat.printToString((WitnessContract.WitnessCreateContract) parameter.unpack(WitnessContract.WitnessCreateContract.class)));
                case 7:
                    return JSONObject.parseObject(JsonFormat.printToString((AssetIssueContractOuterClass.AssetIssueContract) parameter.unpack(AssetIssueContractOuterClass.AssetIssueContract.class)));
                case 8:
                    return JSONObject.parseObject(JsonFormat.printToString((WitnessContract.WitnessUpdateContract) parameter.unpack(WitnessContract.WitnessUpdateContract.class)));
                case 9:
                    return JSONObject.parseObject(JsonFormat.printToString((AssetIssueContractOuterClass.ParticipateAssetIssueContract) parameter.unpack(AssetIssueContractOuterClass.ParticipateAssetIssueContract.class)));
                case 10:
                    return JSONObject.parseObject(JsonFormat.printToString((AccountContract.AccountUpdateContract) parameter.unpack(AccountContract.AccountUpdateContract.class)));
                case 11:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.FreezeBalanceContract) parameter.unpack(BalanceContract.FreezeBalanceContract.class)));
                case 12:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.UnfreezeBalanceContract) parameter.unpack(BalanceContract.UnfreezeBalanceContract.class)));
                case 13:
                    return JSONObject.parseObject(JsonFormat.printToString((AssetIssueContractOuterClass.UnfreezeAssetContract) parameter.unpack(AssetIssueContractOuterClass.UnfreezeAssetContract.class)));
                case 14:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.WithdrawBalanceContract) parameter.unpack(BalanceContract.WithdrawBalanceContract.class)));
                case 15:
                    return JSONObject.parseObject(JsonFormat.printToString((AssetIssueContractOuterClass.UpdateAssetContract) parameter.unpack(AssetIssueContractOuterClass.UpdateAssetContract.class)));
                case 16:
                    return JSONObject.parseObject(JsonFormat.printToString((ProposalContract.ProposalCreateContract) parameter.unpack(ProposalContract.ProposalCreateContract.class)));
                case 17:
                    return JSONObject.parseObject(JsonFormat.printToString((ProposalContract.ProposalApproveContract) parameter.unpack(ProposalContract.ProposalApproveContract.class)));
                case 18:
                    return JSONObject.parseObject(JsonFormat.printToString((ProposalContract.ProposalDeleteContract) parameter.unpack(ProposalContract.ProposalDeleteContract.class)));
                case 19:
                    return JSONObject.parseObject(JsonFormat.printToString((AccountContract.SetAccountIdContract) parameter.unpack(AccountContract.SetAccountIdContract.class)));
                case 20:
                    SmartContractOuterClass.CreateSmartContract createSmartContract = (SmartContractOuterClass.CreateSmartContract) parameter.unpack(SmartContractOuterClass.CreateSmartContract.class);
                    JSONObject parseObject = JSONObject.parseObject(JsonFormat.printToString(createSmartContract));
                    if (jSONObject != null) {
                        jSONObject.put(TronConfig.CONTRACT_ADDRESS, (Object) ByteArray.toHexString(generateContractAddress(transaction, createSmartContract.getOwnerAddress().toByteArray())));
                    }
                    return parseObject;
                case 21:
                    return JSONObject.parseObject(JsonFormat.printToString((SmartContractOuterClass.TriggerSmartContract) parameter.unpack(SmartContractOuterClass.TriggerSmartContract.class)));
                case 22:
                    return JSONObject.parseObject(JsonFormat.printToString((SmartContractOuterClass.UpdateSettingContract) parameter.unpack(SmartContractOuterClass.UpdateSettingContract.class)));
                case 23:
                    return JSONObject.parseObject(JsonFormat.printToString((ExchangeContract.ExchangeCreateContract) parameter.unpack(ExchangeContract.ExchangeCreateContract.class)));
                case 24:
                    return JSONObject.parseObject(JsonFormat.printToString((ExchangeContract.ExchangeInjectContract) parameter.unpack(ExchangeContract.ExchangeInjectContract.class)));
                case 25:
                    return JSONObject.parseObject(JsonFormat.printToString((ExchangeContract.ExchangeWithdrawContract) parameter.unpack(ExchangeContract.ExchangeWithdrawContract.class)));
                case 26:
                    return JSONObject.parseObject(JsonFormat.printToString((ExchangeContract.ExchangeTransactionContract) parameter.unpack(ExchangeContract.ExchangeTransactionContract.class)));
                case 27:
                    return JSONObject.parseObject(JsonFormat.printToString((SmartContractOuterClass.UpdateEnergyLimitContract) parameter.unpack(SmartContractOuterClass.UpdateEnergyLimitContract.class)));
                case 28:
                    return JSONObject.parseObject(JsonFormat.printToString((AccountContract.AccountPermissionUpdateContract) parameter.unpack(AccountContract.AccountPermissionUpdateContract.class)));
                case 29:
                    return JSONObject.parseObject(JsonFormat.printToString((SmartContractOuterClass.ClearABIContract) parameter.unpack(SmartContractOuterClass.ClearABIContract.class)));
                case 30:
                    return JSONObject.parseObject(JsonFormat.printToString((ShieldContract.ShieldedTransferContract) parameter.unpack(ShieldContract.ShieldedTransferContract.class)));
                case 31:
                    return JSONObject.parseObject(JsonFormat.printToString((StorageContract.UpdateBrokerageContract) parameter.unpack(StorageContract.UpdateBrokerageContract.class)));
                case 32:
                    return JSONObject.parseObject(JsonFormat.printToString((MarketContract.MarketSellAssetContract) parameter.unpack(MarketContract.MarketSellAssetContract.class)));
                case 33:
                    return JSONObject.parseObject(JsonFormat.printToString((MarketContract.MarketCancelOrderContract) parameter.unpack(MarketContract.MarketCancelOrderContract.class)));
                case 34:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.FreezeBalanceV2Contract) parameter.unpack(BalanceContract.FreezeBalanceV2Contract.class)));
                case 35:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.UnfreezeBalanceV2Contract) parameter.unpack(BalanceContract.UnfreezeBalanceV2Contract.class)));
                case 36:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.WithdrawExpireUnfreezeContract) parameter.unpack(BalanceContract.WithdrawExpireUnfreezeContract.class)));
                case 37:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.DelegateResourceContract) parameter.unpack(BalanceContract.DelegateResourceContract.class)));
                case 38:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.UnDelegateResourceContract) parameter.unpack(BalanceContract.UnDelegateResourceContract.class)));
                case 39:
                    return JSONObject.parseObject(JsonFormat.printToString((BalanceContract.CancelAllUnfreezeV2Contract) parameter.unpack(BalanceContract.CancelAllUnfreezeV2Contract.class)));
                default:
                    return null;
            }
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static byte[] generateContractAddress(Protocol.Transaction transaction, byte[] bArr) {
        byte[] bytes = Sha256Hash.of(transaction.getRawData().toByteArray()).getBytes();
        byte[] bArr2 = new byte[bytes.length + bArr.length];
        System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
        System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
        return Hash.sha3omit12(bArr2);
    }

    public static boolean checkPermissionOperations(Protocol.Permission permission, Protocol.Transaction.Contract contract) throws PermissionException {
        ByteString operations = permission.getOperations();
        if (operations.size() != 32) {
            throw new PermissionException("operations size must 32");
        }
        int typeValue = contract.getTypeValue();
        return (operations.byteAt(typeValue / 8) & (1 << (typeValue % 8))) != 0;
    }

    public static boolean checkPermissionOperations(String str, int i) throws PermissionException {
        ByteString copyFrom = ByteString.copyFrom(ByteArray.fromHexString(str));
        if (copyFrom.size() == 32) {
            return (copyFrom.byteAt(i / 8) & (1 << (i % 8))) != 0;
        }
        throw new PermissionException("operations size must 32");
    }

    public static boolean checkPermissionOperations(Protocol.Permission permission, int i) throws PermissionException {
        ByteString operations = permission.getOperations();
        if (operations.size() == 32) {
            return (operations.byteAt(i / 8) & (1 << (i % 8))) != 0;
        }
        throw new PermissionException("operations size must 32");
    }

    public static List<Integer> getPermissionOperations(Protocol.Permission permission) throws PermissionException {
        ByteString operations = permission.getOperations();
        if (operations.size() != 32) {
            throw new PermissionException("operations size must 32");
        }
        Integer[] numArr = {0, 1, 2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 30, 31, 32, 33, 41, 42, 43, 44, 45, 46, 48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59};
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 41; i++) {
            if ((operations.byteAt(numArr[i].intValue() / 8) & (1 << (numArr[i].intValue() % 8))) != 0) {
                arrayList.add(numArr[i]);
            }
        }
        return arrayList;
    }

    public static ByteString getPermissionOperationsBytes(List<Integer> list) throws PermissionException {
        if (list == null || list.size() <= 0) {
            return null;
        }
        byte[] bArr = new byte[32];
        for (int i = 0; i < list.size(); i++) {
            int intValue = list.get(i).intValue();
            int i2 = intValue / 8;
            bArr[i2] = (byte) ((1 << (intValue % 8)) | bArr[i2]);
        }
        return ByteString.copyFrom(bArr);
    }

    public static String printReturnToJSONs(GrpcAPI.Return r3) {
        if (r3 == null) {
            return null;
        }
        String printToString = JsonFormat.printToString(r3);
        if (StringTronUtil.isEmpty(printToString)) {
            return null;
        }
        JSONObject parseObject = JSONObject.parseObject(printToString);
        parseObject.put(PullConstants.RESULT_MESSAGE, (Object) new String(r3.getMessage().toByteArray()));
        return parseObject.toJSONString();
    }

    public static boolean checkHavePermissions(String str, List<Protocol.Permission> list) {
        if (list != null && list.size() != 0) {
            for (Protocol.Permission permission : list) {
                for (int i = 0; i < permission.getKeysList().size(); i++) {
                    Protocol.Key key = permission.getKeysList().get(i);
                    if (StringTronUtil.encode58Check(key.getAddress().toByteArray()).equals(str) && key.getWeight() >= permission.getThreshold()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkHavePermissions(String str, List<Protocol.Permission> list, int i) throws PermissionException {
        if (list != null && list.size() != 0) {
            for (Protocol.Permission permission : list) {
                for (int i2 = 0; i2 < permission.getKeysList().size(); i2++) {
                    if (checkPermissionOperations(permission, i)) {
                        Protocol.Key key = permission.getKeysList().get(i2);
                        if (StringTronUtil.encode58Check(key.getAddress().toByteArray()).equals(str) && key.getWeight() >= permission.getThreshold()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkHaveActivePermissions(String str, List<Protocol.Permission> list, int i, int i2) throws PermissionException {
        if (list != null && list.size() != 0) {
            Protocol.Permission permission = null;
            for (Protocol.Permission permission2 : list) {
                if (permission2.getId() == i2) {
                    permission = permission2;
                }
            }
            if (permission == null) {
                return false;
            }
            for (int i3 = 0; i3 < permission.getKeysList().size(); i3++) {
                if (checkPermissionOperations(permission, i)) {
                    Protocol.Key key = permission.getKeysList().get(i3);
                    if (StringTronUtil.encode58Check(key.getAddress().toByteArray()).equals(str) && key.getWeight() >= permission.getThreshold()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean checkHavePermissions(String str, Protocol.Permission permission, int i) throws PermissionException {
        if (permission == null) {
            return false;
        }
        for (int i2 = 0; i2 < permission.getKeysList().size(); i2++) {
            if (checkPermissionOperations(permission, i)) {
                Protocol.Key key = permission.getKeysList().get(i2);
                if (StringTronUtil.encode58Check(key.getAddress().toByteArray()).equals(str) && key.getWeight() >= permission.getThreshold()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean inPermissionGroup(String str, Protocol.Permission permission, Protocol.Transaction.Contract contract) throws PermissionException {
        if (permission == null) {
            return false;
        }
        for (int i = 0; i < permission.getKeysList().size(); i++) {
            if ((permission.getType() != Protocol.Permission.PermissionType.Active || checkPermissionOperations(permission, contract)) && StringTronUtil.encode58Check(permission.getKeysList().get(i).getAddress().toByteArray()).equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkHaveOwnerPermissions(String str, Protocol.Permission permission) throws PermissionException {
        if (permission == null || permission.toString().length() < 1) {
            return true;
        }
        for (int i = 0; i < permission.getKeysList().size(); i++) {
            Protocol.Key key = permission.getKeysList().get(i);
            if (StringTronUtil.encode58Check(key.getAddress().toByteArray()).equals(str) && key.getWeight() >= permission.getThreshold()) {
                return true;
            }
        }
        return false;
    }

    public static WalletPath getMnemonicPath(String str, String str2) {
        try {
            if (!StringTronUtil.isEmpty(str)) {
                return (WalletPath) GsonUtils.gsonToBean(str, WalletPath.class);
            }
            if (hasMnemonic(str2)) {
                return new WalletPath();
            }
            return null;
        } catch (Exception e) {
            LogUtils.e(e);
            return null;
        }
    }

    public static boolean isNonHDWallet(Wallet wallet) {
        return (wallet.getCreateType() == 7 || wallet.isWatchOnly() || hasMnemonic(wallet.getWalletName())) ? false : true;
    }
}
