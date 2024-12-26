package com.tron.wallet.business.walletmanager.importwallet;

import android.util.Pair;
import com.tron.tron_base.frame.utils.LogUtils;
import com.tron.wallet.business.walletmanager.importwallet.ImportWalletContract;
import com.tron.wallet.common.utils.UserIconRandom;
import com.tron.wallet.db.SpAPI;
import com.tron.wallet.db.wallet.WalletUtils;
import java.lang.reflect.InvocationTargetException;
import org.tron.net.CipherException;
import org.tron.walletserver.DuplicateNameException;
import org.tron.walletserver.I_TYPE;
import org.tron.walletserver.InvalidAddressException;
import org.tron.walletserver.InvalidNameException;
import org.tron.walletserver.InvalidPasswordException;
import org.tron.walletserver.Wallet;
public class ImportWalletModel implements ImportWalletContract.Model {
    public static int ADDR_EXISTS = 1;
    public static int ERR = -1;
    public static int ERR_OK;

    public Wallet create(boolean z, Object... objArr) {
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        try {
            return (Wallet) Wallet.class.getConstructor(clsArr).newInstance(objArr);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            LogUtils.e(e);
            return null;
        }
    }

    @Override
    public Pair<Integer, String> saveWallet(boolean z, String str, String str2, String str3, int i) {
        try {
            Wallet create = create(z, I_TYPE.PRIVATE, str3);
            if (create == null) {
                return new Pair<>(Integer.valueOf(ERR), "");
            }
            if (addressAlreadyExist(create.getAddress())) {
                return new Pair<>(Integer.valueOf(ADDR_EXISTS), "");
            }
            create.setShieldedWallet(z);
            create.setWalletName(str);
            create.setIconRes(UserIconRandom.THIS.random());
            create.setCreateType(i);
            create.setCreateTime(System.currentTimeMillis());
            WalletUtils.saveWallet(create, str2);
            if (!z) {
                WalletUtils.setSelectedWallet(str);
            }
            if (SpAPI.THIS.isCold()) {
                SpAPI.THIS.setColdWalletSelected(str);
            }
            return new Pair<>(Integer.valueOf(ERR_OK), create.getAddress());
        } catch (Exception e) {
            LogUtils.e(e);
            return new Pair<>(Integer.valueOf(ERR), "");
        }
    }

    @Override
    public boolean saveWalletWithMnemonic(String str, String str2, String str3) {
        try {
            Wallet wallet = new Wallet(I_TYPE.MNEMONIC, str3);
            wallet.setWalletName(str);
            wallet.setIconRes(UserIconRandom.THIS.random());
            wallet.setMnemonic(str3);
            wallet.setCreateTime(System.currentTimeMillis());
            wallet.setCreateType(1);
            wallet.setMnemonicLength(str3.trim().split("\\s+").length);
            WalletUtils.saveWallet(wallet, str2);
            WalletUtils.setSelectedWallet(str);
            return true;
        } catch (CipherException e) {
            LogUtils.e(e);
            return false;
        } catch (DuplicateNameException e2) {
            LogUtils.e(e2);
            return false;
        } catch (InvalidAddressException e3) {
            LogUtils.e(e3);
            return false;
        } catch (InvalidNameException e4) {
            LogUtils.e(e4);
            return false;
        } catch (InvalidPasswordException e5) {
            LogUtils.e(e5);
            return false;
        }
    }

    private boolean addressAlreadyExist(String str) {
        return WalletUtils.getWalletForAddress(str) != null;
    }
}
